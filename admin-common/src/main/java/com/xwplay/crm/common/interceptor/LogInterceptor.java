package com.xwplay.crm.common.interceptor;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.xwplay.crm.common.constants.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 日志拦截器
 */
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 增加日志流水号
        MDC.put(AppConstants.LOG_ID, IdWorker.getIdStr());
        return true;
    }

}
