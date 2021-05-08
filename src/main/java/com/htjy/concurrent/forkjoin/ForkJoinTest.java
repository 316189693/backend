package com.htjy.concurrent.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class ForkJoinTest {
    public static void main(String[] args) {
        long[] array = new long[3000];
        Random random = new Random();
        long expectedSum = 0;
        for(int i =0; i< array.length; i++){
            array[i] = random.nextLong();
            expectedSum += array[i];
        }

        System.out.println("Expected sum: " + expectedSum);
        ForkJoinTask<Long> forkJoinTask = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(forkJoinTask);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

}

class SumTask extends RecursiveTask<Long> {
    static final int THREASHOLD = 500;
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
       this.array = array;
       this.start = start;
       this.end = end;
    }
    @Override
    protected Long compute() {
        if (end - start <= THREASHOLD) {
            long sum = 0;
            for(int i = start; i<end; i++){
                sum += this.array[i];
                try{
                    Thread.sleep(1);
                }catch (InterruptedException exception){

                }
            }
            return sum;
        }
        int middle = (end+start) /2;
        SumTask subTask1 = new SumTask(this.array, start, middle);
        SumTask subTask2 = new SumTask(this.array, middle, end);
        invokeAll(subTask1, subTask2);
        Long subRst1 = subTask1.join();
        Long subRst2 = subTask2.join();
        Long result = subRst1 + subRst2;
        return result;
    }

}