package com.example.learnspring.infrastructure.services;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class GhtkJwtTokenFilter extends OncePerRequestFilter {

    public static final String HEADER_KEY = "Authorization";
    public static final String BEARER_PREFIX = "Bearer";

    private final JwtTokenUtil jwtTokenUtil;

    public GhtkJwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HEADER_KEY);

        if (header == null) {
            logger.warn("Không tồn tại Bearer Token");
            return;
        }

        if (!header.startsWith(BEARER_PREFIX)) {
            logger.warn("Không phải Bearer Token");
            return;
        }

        String authToken = header.replace(BEARER_PREFIX, "");
        logger.info(jwtTokenUtil.validateToken(authToken));

        if (!jwtTokenUtil.validateToken(authToken)) {
            logger.warn("JWT Token không hợp lệ");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
