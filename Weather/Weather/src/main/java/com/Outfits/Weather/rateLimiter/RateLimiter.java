package com.Outfits.Weather.rateLimiter;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.http.HttpServletRequest;

public class RateLimiter {
    private final ConcurrentHashMap<String, UserRequest> requestCounts = new ConcurrentHashMap<>();
    private final int MAX_REQUESTS = 550; 
    private final long TIME_WINDOW = TimeUnit.MINUTES.toMillis(1);

    public boolean isAllowed(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        long now = System.currentTimeMillis();
        requestCounts.putIfAbsent(ip, new UserRequest(now, 0));
        UserRequest userRequest = requestCounts.get(ip);
        if (now - userRequest.timestamp > TIME_WINDOW) {
            userRequest.timestamp = now;
            userRequest.count = 0;
        }
        userRequest.count++;
        return userRequest.count <= MAX_REQUESTS;
    }

    private static class UserRequest {
        long timestamp;
        int count;

        UserRequest(long timestamp, int count) {
            this.timestamp = timestamp;
            this.count = count;
        }
    }
}
