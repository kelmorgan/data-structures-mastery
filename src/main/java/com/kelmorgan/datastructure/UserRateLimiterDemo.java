package com.kelmorgan.datastructure;

public class UserRateLimiterDemo {
    
    public static void main(String[] args) throws InterruptedException {
        // Create limiter: each user gets 5 tokens, refills 2 per second
        SimpleUserRateLimiter limiter = new SimpleUserRateLimiter(5, 2);
        
        System.out.println("=== User-based Token Bucket Rate Limiter ===\n");
        System.out.println("Settings: 5 tokens per user, refill 2 tokens/second\n");
        
        // Simulate multiple users
        String[] users = {"Alice", "Bob", "Charlie"};
        
        // First round: All users make requests
        System.out.println("Round 1: All users making requests");
        for (int i = 1; i <= 7; i++) {
            System.out.println("\nRequest #" + i + ":");
            for (String user : users) {
                boolean allowed = limiter.isAllowed(user);
                int remaining = limiter.getRemainingTokens(user);
                System.out.printf("  %-7s: %s (Tokens left: %d)%n", 
                    user, allowed ? "✅ ALLOWED" : "❌ DENIED", remaining);
            }
        }
        
        // Wait for refill
        System.out.println("\n⏰ Waiting 3 seconds for refill...");
        Thread.sleep(3000);
        
        // Check tokens after refill
        System.out.println("\nAfter refill:");
        for (String user : users) {
            System.out.printf("  %s has %d tokens%n", 
                user, limiter.getRemainingTokens(user));
        }
        
        // Try requests again
        System.out.println("\nRound 2: Making more requests");
        for (int i = 1; i <= 3; i++) {
            for (String user : users) {
                boolean allowed = limiter.isAllowed(user);
                if (allowed) {
                    System.out.printf("  %s request %d: ✅%n", user, i);
                }
            }
        }
        
        // Simulate a new user
        System.out.println("\n🆕 New user Dave joins:");
        String newUser = "Dave";
        for (int i = 1; i <= 6; i++) {
            boolean allowed = limiter.isAllowed(newUser);
            System.out.printf("  %s request %d: %s (Remaining: %d)%n",
                newUser, i, allowed ? "✅" : "❌", 
                limiter.getRemainingTokens(newUser));
        }
        
        // Reset a user
        System.out.println("\n🔄 Resetting Bob's limit:");
        limiter.resetUser("Bob");
        System.out.println("  Bob now has " + limiter.getRemainingTokens("Bob") + " tokens");
    }
}