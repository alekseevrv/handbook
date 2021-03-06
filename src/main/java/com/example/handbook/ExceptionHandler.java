package com.example.handbook;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            Exception e
    ) {
        return new ModelAndView(
                "exception",
                "exceptionMsg",
                e.getMessage()
        );
    }
}
