package Multithreading.AtomicVolatileSynchronized;

import java.util.concurrent.atomic.AtomicInteger;

class SharedCounter {

//    private int count;

    private AtomicInteger count;

    SharedCounter(){
//        this.count=0;
        this.count = new AtomicInteger(0);
    }

    public void increment() {
//        this.count++;
        count.incrementAndGet();
    }

    public int getCount() {
//        return this.count;
        return  count.get();
    }
}

public class Atomic {
    public static void main(String[] args) throws InterruptedException {

        SharedCounter sharedCounter = new SharedCounter();

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 Started");
            for (int i = 0; i < 1000; i++) {
                sharedCounter.increment();
            }
            System.out.println("Thread 1 completed");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 Started");
            for (int i =0; i < 1000; i++) {
                sharedCounter.increment();
            }
            System.out.println("Thread 2 completed");
        });

        t1.start();
        t2.start();

        // Wait for both threads to complete
        t1.join();
        t2.join();

        System.out.println("Total Count = " + sharedCounter.getCount());
    }
}

