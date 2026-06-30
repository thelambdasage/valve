public class RateLimiter {

    // implement the fixed window counter
    // set these final
    private final int maxRequests;
    private final long windowSizeMs;

    private int requestCount;
    private long windowStart;

    public RateLimiter(int maxRequests, long windowSizeMs) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
        this.requestCount = 0;
        this.windowStart = System.currentTimeMillis();
    }

    public boolean allowRequest() {
        long now = System.currentTimeMillis();

        // 1: check window expiration
        if (now - windowStart >= windowSizeMs) {
            windowStart = now;
            requestCount = 0;
        }

        // 2: check under limit
        if (requestCount < maxRequests) {
            requestCount++;
            return true;
        }

        return false; // over limit - blocked
    }
}