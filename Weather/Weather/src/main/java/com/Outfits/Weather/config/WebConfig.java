package com.Outfits.Weather.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Outfits.Weather.interceptor.RateLimitInterceptor;
import com.Outfits.Weather.rateLimiter.RateLimiter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private RateLimitInterceptor rateLimitInterceptor;

    @Autowired
    public void setRateLimitInterceptor(RateLimitInterceptor rateLimitInterceptor) {
        this.rateLimitInterceptor = rateLimitInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor);
    }

    @Bean
    public RateLimiter rateLimiter() {
        return new RateLimiter();
    }
}
