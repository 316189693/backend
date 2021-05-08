package com.htjy.concurrent.rate;

import com.google.common.util.concurrent.RateLimiter;

public class RateTest {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.acquire(6));
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
    }
}
