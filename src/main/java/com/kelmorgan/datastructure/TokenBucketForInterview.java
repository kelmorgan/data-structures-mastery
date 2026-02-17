package com.kelmorgan.datastructure;

public class TokenBucketForInterview {
    
    /**
     * INTERVIEW EXPLANATION:
     * 
     * "Token Bucket works like a bucket that fills with tokens at a constant rate.
     * Each request needs to take a token from the bucket. If the bucket is empty,
     * the request is denied."
     * 
     * Properties:
     * - capacity: How many tokens can be stored (max burst size)
     * - refillRate: How fast tokens are added (steady-state rate)
     */
    
    private int tokens;              // Current tokens in bucket
    private int capacity;            // Max bucket size
    private int refillRate;           // Tokens per second
    private long lastRefillTime;      // Last refill timestamp
    
    public TokenBucketForInterview(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;       // Start with full bucket
        this.lastRefillTime = System.currentTimeMillis();
    }
    
    /**
     * STEP 1: Try to allow a request
     */
    public synchronized boolean allow() {
        // Step 1: Add any new tokens based on time passed
        refill();
        
        // Step 2: Check if we have tokens
        if (tokens > 0) {
            tokens--;  // Consume one token
            return true; // Request allowed
        }
        
        // No tokens available - rate limited
        return false;
    }
    
    /**
     * STEP 2: Calculate how many tokens to add
     */
    private void refill() {
        long now = System.currentTimeMillis();
        
        // How many seconds since last refill?
        long timeSinceLastRefill = now - lastRefillTime;
        
        // Convert to seconds and calculate tokens to add
        // Example: if 2 seconds passed and rate is 5 tokens/sec → add 10 tokens
        long tokensToAdd = (timeSinceLastRefill * refillRate) / 1000;
        
        if (tokensToAdd > 0) {
            // Add tokens but don't exceed capacity
            tokens = (int) Math.min(capacity, tokens + tokensToAdd);
            lastRefillTime = now;
        }
    }
}