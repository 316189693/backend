package com.htjy.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Test {
    private static  final RateLimiter rateLimiter = RateLimiter.create(10);
    public static void main(String[] args) {

       for(int i = 1; i<100; i++) {
          Thread t = new Thread(()->{
              LockSupport.parkNanos(100000);

               if(rateLimiter.tryAcquire(1, TimeUnit.SECONDS)) {
                   System.out.println(String.format("%s get rate success", Thread.currentThread().getName()));
               } else {
                   System.out.println(String.format("%s get rate timeout", Thread.currentThread().getName()));
               }

           }, "Thread-"+i);
          t.start();
       }
    }
}
