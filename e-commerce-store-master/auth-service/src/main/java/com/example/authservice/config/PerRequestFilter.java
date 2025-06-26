package com.example.authservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.authservice.config.ContextHolder.CORRELATION_ID;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PerRequestFilter
        extends OncePerRequestFilter {

    private final ContextHolder contextHolder;

    private static Long parseUserId(final HttpServletRequest request) {
        try {
            var userIdString = request.getHeader("userId");
            return Long.parseLong(userIdString);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        var correlationId = request.getHeader(CORRELATION_ID);
        var userId = parseUserId(request);
        var username = request.getHeader("username");

        var context = new ContextData(correlationId, userId, username);

        this.contextHolder.set(context);

        try {
            filterChain.doFilter(request, response);
        } finally {
            this.contextHolder.remove();
        }
    }
}
