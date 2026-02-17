package com.kelmorgan.datastructure;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiting {

        private final int maxTokens;           // Maximum bucket capacity
        private final int refillRate;           // Tokens added per second
        private final AtomicInteger currentTokens; // Current tokens in bucket
        private final AtomicLong lastRefillTimestamp; // Last time we refilled

        /**
         * Constructor
         * @param maxTokens Maximum number of tokens bucket can hold
         * @param refillRate Number of tokens added per second
         */
        public RateLimiting(int maxTokens, int refillRate) {
            this.maxTokens = maxTokens;
            this.refillRate = refillRate;
            this.currentTokens = new AtomicInteger(maxTokens); // Start full
            this.lastRefillTimestamp = new AtomicLong(System.nanoTime());
        }

        /**
         * Try to consume a token
         * @return true if token was consumed, false if rate limited
         */
        public boolean tryConsume() {
            refill(); // Add any new tokens based on time passed

            // Try to decrement tokens if available
            while (true) {
                int available = currentTokens.get();
                if (available <= 0) {
                    return false; // No tokens available
                }

                if (currentTokens.compareAndSet(available, available - 1)) {
                    return true; // Successfully consumed token
                }
                // If CAS failed, another thread updated tokens, retry
            }
        }

        /**
         * Refill tokens based on time elapsed since last refill
         */
        private void refill() {
            long now = System.nanoTime();
            long lastRefill = lastRefillTimestamp.get();

            // Calculate time passed in seconds
            long timePassed = now - lastRefill;
            int tokensToAdd = (int) (timePassed * refillRate / 1_000_000_000L);

            if (tokensToAdd > 0) {
                // Update last refill time if we're adding tokens
                if (lastRefillTimestamp.compareAndSet(lastRefill, now)) {
                    while (true) {
                        int current = currentTokens.get();
                        int newTokenCount = Math.min(maxTokens, current + tokensToAdd);

                        if (currentTokens.compareAndSet(current, newTokenCount)) {
                            break; // Successfully added tokens
                        }
                        // If CAS failed, another thread updated, retry
                    }
                }
            }
        }

        /**
         * Get current number of tokens available
         */
        public int getAvailableTokens() {
            refill(); // Update tokens before returning
            return currentTokens.get();
        }

        /**
         * Force set tokens (useful for testing)
         */
        public void setTokens(int tokens) {
            currentTokens.set(Math.min(maxTokens, Math.max(0, tokens)));
        }
}
