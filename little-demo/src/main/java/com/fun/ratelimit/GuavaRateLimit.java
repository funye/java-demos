package com.fun.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaRateLimit {

    public static void main(String[] args) {

        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());

    }
}
