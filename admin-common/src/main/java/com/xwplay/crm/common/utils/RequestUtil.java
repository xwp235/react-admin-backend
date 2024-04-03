package com.xwplay.crm.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class RequestUtil {

    /**
     * 获取请求上下文路径
     */
    public static String getContextPath() {
        String contextPath = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            HttpServletRequest request = attributes.getRequest();
            contextPath = request.getContextPath();
        }
        return contextPath;
    }

    /**
     * 获取请求中的参数值
     */
    public static String getRequestParameter(String name) {
        String value = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            HttpServletRequest request = attributes.getRequest();
            value = request.getParameter(name);
        }
        return value;
    }

    /**
     * 获取Servlet请求对象
     */
    public static HttpServletRequest getServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            return attributes.getRequest();
        }
        return null;
    }

    /**
     * 获取请求中的属性值
     */
    public static Object getRequestAttribute(String name) {
        Object value = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            HttpServletRequest request = attributes.getRequest();
            value = request.getAttribute(name);
        }
        return value;
    }

}
