package net.phoenix.Webserver.utils;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ApiFilter implements Filter {
    private static final int REQUESTS_PER_INTERVAL = 100;
    private static final int BUCKET_CAPACITY = 100;
    private static final long REFILL_INTERVAL = 10000;
    private final Map<String, TokenBucket> tokenBuckets = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            String clientIp = getClientIp(httpRequest);

            TokenBucket tokenBucket = tokenBuckets.computeIfAbsent(clientIp, k -> new TokenBucket(BUCKET_CAPACITY));

            if (!tokenBucket.tryConsume()) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(429);
                httpResponse.getWriter().write("Rate limit exceeded");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private String getClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isEmpty()) {
            return forwardedFor.split(",")[0].trim();
        } else {
            return request.getRemoteAddr();
        }
    }

    private static class TokenBucket {
        private final int capacity;
        private final AtomicLong tokens;
        private long lastRefillTime;

        public TokenBucket(int capacity) {
            this.capacity = capacity;
            this.tokens = new AtomicLong(capacity);
            this.lastRefillTime = System.currentTimeMillis();
        }

        public boolean tryConsume() {
            refillTokens();
            long currentTokens = tokens.get();
            if (currentTokens > 0) {
                return tokens.decrementAndGet() >= 0;
            }
            return false;
        }

        private void refillTokens() {
            long currentTime = System.currentTimeMillis();
            long timeElapsed = currentTime - lastRefillTime;

            if (timeElapsed >= REFILL_INTERVAL) {
                long newTokens = Math.min(capacity, REQUESTS_PER_INTERVAL);
                tokens.set(newTokens);
                lastRefillTime = currentTime;
            }
        }
    }
}