//package com.api.antonio;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Component
//public class LoggingFilter implements Filter {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, javax.servlet.ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        logger.info("Request URI: {}", httpServletRequest.getRequestURI());
//        logger.info("Request Method: {}", httpServletRequest.getMethod());
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws javax.servlet.ServletException {
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
