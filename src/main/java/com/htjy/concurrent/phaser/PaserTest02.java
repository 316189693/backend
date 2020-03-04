package com.htjy.concurrent.phaser;

import javax.annotation.security.RunAs;
import java.util.concurrent.Phaser;

/**
 * Created By willz
 * Date : 2020/3/4
 * Time: 11:20
 */
public class PaserTest02 implements Runnable{
    private Phaser phaser;
    PaserTest02(Phaser phaser){
        this.phaser = phaser;
    }
    public static void main(String[] args) {
        System.out.println("Phaser test begin: ");
        Phaser phaser = new Phaser();
        phaser.register();
        for(int i = 0; i < 5; i++)
        {
            phaser.register();
            Thread t = new Thread(new PaserTest02(phaser), String.valueOf(i));
            t.start();
        }
        phaser.arriveAndDeregister();
        System.out.println("Phaser test end: ");
    }
    @Override
    public void run() {

        System.out.println("Thread-"+ Thread.currentThread().getName() +"-start:");
        this.phaser.arriveAndAwaitAdvance();
        System.out.println("Thread-"+ Thread.currentThread().getName() +"-end:");
        System.out.println("Thread-"+ Thread.currentThread().getName() +"-start-1:");
        this.phaser.arriveAndAwaitAdvance();
        System.out.println("Thread-"+ Thread.currentThread().getName() +"-end-0:"+this.phaser.getPhase());
    }
}
