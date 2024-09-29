package com.Outfits.Weather.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import com.Outfits.Weather.exception.RateLimitException;
import com.Outfits.Weather.rateLimiter.RateLimiter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class RateLimitInterceptor implements HandlerInterceptor {

	private RateLimiter rateLimiter = new RateLimiter();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!rateLimiter.isAllowed(request)) {
            throw new RateLimitException("Rate limit exceeded. Try again later.");
        }
        return true;
    }
}

