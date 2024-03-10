package com.laurentiuspilca.ssia.handlers;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e)  {
        try {
            httpServletResponse.sendRedirect("/error");
            httpServletResponse.setHeader("failed", LocalDateTime.now().toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
