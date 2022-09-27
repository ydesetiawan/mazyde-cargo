package com.mazyde.cargo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException(
        MaxUploadSizeExceededException exc,
        HttpServletRequest request,
        HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("page_500.html");
        modelAndView.getModel().put("message", "Ukuran file terlalu besar!");
        return modelAndView;
    }

}
