package com.kelmorgan.datastructure;

import java.util.*;
import java.util.concurrent.*;

public class SimpleUserRateLimiter {
    
    // Class to hold user's rate limit info
    private static class UserLimit {
        int tokens;
        long lastRefillTime;
        
        UserLimit(int initialTokens, long currentTime) {
            this.tokens = initialTokens;
            this.lastRefillTime = currentTime;
        }
    }
    
    private final int capacity;           // Max tokens per user
    private final int refillRate;          // Tokens per second
    private final Map<String, UserLimit> userMap = new ConcurrentHashMap<>();
    
    public SimpleUserRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }
    
    /**
     * Check if request from user is allowed
     */
    public boolean isAllowed(String userId) {
        long now = System.currentTimeMillis();
        
        // Get or create user's rate limit info
        UserLimit userLimit = userMap.computeIfAbsent(userId, 
            k -> new UserLimit(capacity, now));
        
        synchronized (userLimit) {  // Lock only this user, not all users
            // Refill tokens based on time passed
            long timePassed = now - userLimit.lastRefillTime;
            int tokensToAdd = (int) (timePassed * refillRate / 1000);
            
            if (tokensToAdd > 0) {
                userLimit.tokens = Math.min(capacity, userLimit.tokens + tokensToAdd);
                userLimit.lastRefillTime = now;
            }
            
            // Try to consume
            if (userLimit.tokens > 0) {
                userLimit.tokens--;
                return true;
            }
            
            return false;
        }
    }
    
    /**
     * Get remaining tokens for a user
     */
    public int getRemainingTokens(String userId) {
        UserLimit userLimit = userMap.get(userId);
        if (userLimit == null) {
            return capacity;
        }
        
        synchronized (userLimit) {
            // Refill first to get current count
            long now = System.currentTimeMillis();
            long timePassed = now - userLimit.lastRefillTime;
            int tokensToAdd = (int) (timePassed * refillRate / 1000);
            
            if (tokensToAdd > 0) {
                userLimit.tokens = Math.min(capacity, userLimit.tokens + tokensToAdd);
                userLimit.lastRefillTime = now;
            }
            
            return userLimit.tokens;
        }
    }
    
    /**
     * Reset limit for a user
     */
    public void resetUser(String userId) {
        userMap.remove(userId);
    }
}