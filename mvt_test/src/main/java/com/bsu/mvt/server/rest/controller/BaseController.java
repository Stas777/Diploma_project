package com.bsu.mvt.server.rest.controller;

import com.bsu.mvt.server.core.exception.*;
import org.slf4j.*;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(MVTException.class)
    public
    @ResponseBody
    ResponseEntity<ResponseErrorBean> handleException(MVTException ex, HttpServletRequest request) {
        logger.debug("BaseController.handleException", ex);
        if (MVTErrorConst.SESSION_EXPIRED.equals(ex.getMvtCode()) || MVTErrorConst.SESSION_MISSING.equals(ex.getMvtCode()) || MVTErrorConst.LOGIN_INVALID_OR_USER_NOT_FOUND.equals(ex.getMvtCode())) {
            return new ResponseEntity<>(new ResponseErrorBean(HttpStatus.UNAUTHORIZED.value(), ex.getMvtCode(), ex.getMessage() != null ? ex.getMessage() : ex.getMvtMessage(), ex.getValidationErrorBeans()), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(new ResponseErrorBean(HttpStatus.BAD_REQUEST.value(), ex.getMvtCode(), ex.getMessage() != null ? ex.getMessage() : ex.getMvtMessage(), ex.getValidationErrorBeans()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(TypeMismatchException.class)
    public
    @ResponseBody
    ResponseEntity<ResponseErrorBean> handleTypeMismatchException(TypeMismatchException ex, HttpServletRequest request) {
        logger.debug("BaseController.handleTypeMismatchException", ex);
        return new ResponseEntity<>(new ResponseErrorBean(HttpStatus.BAD_REQUEST.value(), MVTErrorConst.ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public
    @ResponseBody
    ResponseEntity<ResponseErrorBean> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        logger.debug("BaseController.handleDataIntegrityViolationException", ex);
        return new ResponseEntity<>(new ResponseErrorBean(HttpStatus.BAD_REQUEST.value(), MVTErrorConst.DB_INTEGRITY_VIOLATION, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public
    @ResponseBody
    ResponseEntity<ResponseErrorBean> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        logger.debug("BaseController.handleHttpMessageNotReadableException", ex);
        return new ResponseEntity<>(new ResponseErrorBean(HttpStatus.BAD_REQUEST.value(), MVTErrorConst.JSON_INVALID, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
