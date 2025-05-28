package Multithreading.BasicThreadOperations;

// ThreadExample.java

class MyThread1 extends Thread {

    @Override
    public void run() {

        for(int i=0;i<5;i++) {
            System.out.println("Thread is running with id " + Thread.currentThread().getId());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // Handle the exception
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}

// Main class to test MyThread
public class ThreadExample {
    public static void main(String[] args) {

        // Create another instance of MyThread1
        MyThread1 t2 = new MyThread1();
        MyThread1 t3 = new MyThread1();

        t2.start(); // Output: Thread is running with id 1
        t3.start(); // Output: Thread is running with id 2

        // Using Lambda Expression
        Thread t4 = new Thread(() -> {
            System.out.println("Thread is running");
        });

        t4.start();
    }
}
