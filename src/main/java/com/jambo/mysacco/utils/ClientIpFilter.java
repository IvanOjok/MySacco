package com.jambo.mysacco.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ClientIpFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String ip = extractClientIp(request);
            RequestContext.setClientIp(ip);
            filterChain.doFilter(request, response);
        } finally {
            RequestContext.clear(); // prevent memory leaks
        }
    }

    private String extractClientIp(HttpServletRequest request) {
        String header = request.getHeader("X-Forwarded-For");

        if (header != null && !header.isBlank()) {
            return header.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }
}

