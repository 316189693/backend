package com.htjy.concurrent.phaser;

import java.util.concurrent.Phaser;

public class CeremonyTest {
    private static Phaser phaser = new MarriagePhaser();
    public static void main(String[] args) {
        System.out.println("starting ....");
        phaser.bulkRegister(12);
        for(int i = 0; i < 10; i++) {
            new Thread(new Person("per"+i)).start();
        }
        new Thread(new Person("bride")).start();
        new Thread(new Person("groom")).start();

    }


   static class Person implements Runnable{
        private String name;
        public Person(String name){
            this.name = name;
        }
       public void arrived() {
           System.out.printf("%s arrived\n", this.name);
           phaser.arriveAndAwaitAdvance();
       }
        public void eat() {
            System.out.printf("%s eat\n", this.name);
            phaser.arriveAndAwaitAdvance();
        }
        public void leave() {

            if ("bride".equals(this.name) || "groom".equals(this.name)) {
                phaser.arriveAndAwaitAdvance();
            } else {
                System.out.printf("%s leave\n", this.name);
                phaser.arriveAndDeregister();

            }

        }
        public void gotobed() {
            if ("bride".equals(this.name) || "groom".equals(this.name)) {
                System.out.printf("%s got bed\n", this.name);
                phaser.arriveAndAwaitAdvance();
            }
        }

        public void countMony(){
            if ("bride".equals(this.name) || "groom".equals(this.name)) {
                System.out.printf("%s counting\n", this.name);
                phaser.arriveAndAwaitAdvance();
            }

        }
        @Override
        public void run() {

                arrived();
                eat();
                leave();
                gotobed();
                countMony();


        }
    }
}



class MarriagePhaser extends Phaser{
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                System.out.println("everybody arrived");
                System.out.println("ceremony starting ....dinner");
                return false;  // this must be false so that whole waiting next barrier
            case 1:
                System.out.println("finsh eat");
                System.out.println("ceremony show... ");
                return false; // this must be false so that whole waiting next barrier
            case 2:
                System.out.println("every body leave");
                return false; // final return true
            case 3:
                System.out.println("go to bed");
                return true; // this must be false so that whole waiting next barrier


            default:
                System.out.println("finish");
                return true;
        }

    }
}