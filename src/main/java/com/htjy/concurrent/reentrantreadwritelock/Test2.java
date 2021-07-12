package com.htjy.concurrent.reentrantreadwritelock;

import org.apache.commons.lang3.ThreadUtils;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class Test2 extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    public static void main(String[] args) throws Exception{
        int c = 100;
        int b = c+SHARED_UNIT;
        System.out.println(b);
        System.out.println(Integer.MAX_VALUE);
        for (int i =0 ; i<5; i++){
            System.out.println(i);
            if(i==2) {
                break;
            }
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 1 run");
                LockSupport.park();
                System.out.println("thread 1 continue");
            }
        });

        thread1.start();
        Thread.sleep(1000);
        System.out.println("unpark");
        LockSupport.unpark(thread1);
    }
}
