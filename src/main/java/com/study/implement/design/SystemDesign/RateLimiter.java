package com.study.implement.design.SystemDesign;

public class RateLimiter {

    public static class TokenBucketRateLimiter{

        private final long capacity;
        private final long refillTokensPerSecond;
        private double availableTokens;
        private long lastRefillTimestamp;


        public TokenBucketRateLimiter(long capacity, long refillTokensPerSecond) {
            this.capacity = capacity;
            this.refillTokensPerSecond = refillTokensPerSecond;
            this.availableTokens = availableTokens;
        }

        public synchronized boolean allowRequest(){
            refill();

            if(availableTokens >= 1){
                availableTokens -= 1;
                return true;
            }
            return false;
        }

        public void refill(){
            long now  = System.nanoTime();
            double tokensToAdd = ((now - lastRefillTimestamp) / 1e9) * refillTokensPerSecond;
            if(tokensToAdd > 0){
                availableTokens = Math.min(capacity,availableTokens + tokensToAdd);
                lastRefillTimestamp = now;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter tokenBucketRateLimiter =  new TokenBucketRateLimiter(5,2);
        for(int i=0;i<10;i++){
            boolean allowed = tokenBucketRateLimiter.allowRequest();
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(300); // simulate incoming requests every 300ms
        }
    }
}
