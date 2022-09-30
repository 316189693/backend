package com.htjy.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.Duration;

public class TestWarm {
    private static  final RateLimiter rateLimiter = RateLimiter.create(2, Duration.ofSeconds(3));
    public static void main(String[] args) {
        int a = 0;
        double z = 123/a;
        rateLimiter.acquire(10);
        System.out.println("require 3");
        rateLimiter.acquire(5);
        System.out.println("require 5");
    }
}
