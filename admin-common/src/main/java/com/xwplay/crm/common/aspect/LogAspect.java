package com.xwplay.crm.common.aspect;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.xwplay.crm.common.constants.AppConstants;
import com.xwplay.crm.common.utils.LogUtil;
import com.xwplay.crm.common.utils.AuthUtil;
import com.xwplay.crm.common.utils.RequestUtil;
import com.xwplay.crm.common.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPatternParser;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class LogAspect {

    private static final String[] IGNORE_PATHS ={
            "/login",
            "/logout"
    };

    private final PathPatternParser pathPatternParser = new PathPatternParser();

    @Pointcut("execution(public * com.xwplay.crm..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object handler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = RequestUtil.getServletRequest();
        if (Objects.isNull(request)){
            return proceedingJoinPoint.proceed();
        }
        var servletPath = request.getServletPath();
        var protocol = request.getProtocol();
        var serverPort = request.getServerPort();
        var remotePort = request.getRemotePort();
        var isIgnored = false;
        for (String path : IGNORE_PATHS) {
            boolean matchResult = pathPatternParser.parse(path).matches(
                    PathContainer.parsePath(servletPath)
            );
            if (matchResult) {
                isIgnored = true;
                break;
            }
        }
        if (isIgnored){
            return proceedingJoinPoint.proceed();
        }
        long startTime = System.currentTimeMillis();

        //构建Spring表达式参数
        Map<String, Object> variables =
                getAopMethodContext(proceedingJoinPoint);
        Object parameters = "";
        if (MapUtil.isNotEmpty(variables)){
            StringBuilder elExpression = new StringBuilder("#{");
            var i = 0;
            for (String key : variables.keySet()) {
                if (i == 0) {
                    elExpression.append("'");
                } else {
                    elExpression.append("+',");
                }
                elExpression.append(key).append("='+#").append(key);
                i++;
            }
            elExpression.append("}");
            // 解析Spring表达式的值
            try {
                parameters  = parseExpressionValue(
                        elExpression.toString(), variables);
            } catch (Exception e) {
                LogUtil.error("LogAspect el expression compile failed!",e);
            }
        }

        var log = new LogData();
        String ip = ServletUtil.getClientIP(request);
        String logId = MDC.get(AppConstants.LOG_ID);
        boolean isInnerIp = StrUtil.equalsAny(ip, "127.0.0.1","0:0:0:0:0:0:0:1","localhost");
        String saveIp = isInnerIp ? "127.0.0.1":ip;
        var headerNames = request.getHeaderNames();
        var headerVals = MapUtil.<String,Object>newHashMap();
        while(headerNames.hasMoreElements()){
            var name = headerNames.nextElement();
            var value = request.getHeader(name);
            headerVals.put(name,value);
        }
        var userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        var ua = UserAgentUtil.parse(userAgent);
        var device = ua.getPlatform().toString();
        log
                .setLogId(Long.parseLong(logId))
                .setIp(saveIp)
                .setDevice(device)
                .setProtocol(protocol)
                .setPath(servletPath)
                .setReferer(request.getHeader(HttpHeaders.REFERER))
                .setMethod(request.getMethod())
                .setUserAgent(request.getHeader(HttpHeaders.USER_AGENT))
                .setCreateTime(ZonedDateTime.now())
                .setServerPort(serverPort)
                .setRemotePort(remotePort);
        if (MapUtil.isNotEmpty(headerVals)){
           log.setHeaders(JSONUtil.toJsonStr(headerVals));
        }
        if (Objects.nonNull(parameters)){
            log.setParameters(JSONUtil.toJsonStr(parameters ));
        } else {
            log.setParameters("-");
        }
        if (AuthUtil.isLogin()) {
            log.setUsername(AuthUtil.getUsername());
        } else {
            log.setUsername("-");
        }
        try {
            var obj = proceedingJoinPoint.proceed();
            log.setStatus(AppConstants.REQUEST_SUCCESS);
            return obj;
        } catch (Exception e) {
            log.setStatus(AppConstants.REQUEST_ERROR);
            try (var sw = new StringWriter();
                 var pw = new PrintWriter(sw)) {
                e.printStackTrace(pw);
                log.setFailReason(sw.toString());
            }
            LogUtil.error("ActionLogAspect error occurred!",e);
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            log.setResponseTime(totalTime);
            // todo 存储统计数据
        }
    }

    /**
     * 获取切面中的方法上下文
     */
    private Map<String, Object> getAopMethodContext(ProceedingJoinPoint proceedingJoinPoint) {
        //获取方法签名，参数名和参数值
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object[] args = proceedingJoinPoint.getArgs();
        String[] params = methodSignature.getParameterNames();
        //构建Spring表达式参数
        Map<String, Object> variables = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            variables.put(params[i], args[i]);
        }
        return variables;
    }

    /**
     * 解析并返回Spring表达式的值
     */
    private <T> T parseExpressionValue(String expr, Map<String, Object> variables) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        variables.forEach(context::setVariable);
        Expression expression = parser.parseExpression(
                expr, new TemplateParserContext());
        return expression.getValue(context, (Class<T>) Object.class);
    }


}
