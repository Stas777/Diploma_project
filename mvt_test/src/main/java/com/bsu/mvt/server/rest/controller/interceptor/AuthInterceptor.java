package com.bsu.mvt.server.rest.controller.interceptor;

import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.AuthService;
import com.bsu.mvt.server.rest.model.constant.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.*;

@Component("authInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Resource(name = "authService")
    private AuthService authService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String xAuthHeader = request.getHeader(SecurityConstants.HEADER_NAME_AUTH);

        if (StringUtils.isBlank(xAuthHeader)) {
            throw new MVTException("UnauthenticatedException: wrong headers", MVTErrorConst.SESSION_MISSING);
        }

        if (!authService.checkAuth(xAuthHeader)) {
            throw new MVTException("UnauthenticatedException: session expired", MVTErrorConst.SESSION_EXPIRED);
        } else {
            return true;
        }
    }
}
