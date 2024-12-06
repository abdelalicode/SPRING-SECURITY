package com.secure.secureAPP.config;

import com.secure.secureAPP.auth.EbankifyAuthentificationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getHeader("Auth").equals("AHYAA")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return;
        }

        EbankifyAuthentificationToken ebankifyAuthentificationToken = new EbankifyAuthentificationToken();
        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(ebankifyAuthentificationToken);
        SecurityContextHolder.setContext(newContext);

    }
}
