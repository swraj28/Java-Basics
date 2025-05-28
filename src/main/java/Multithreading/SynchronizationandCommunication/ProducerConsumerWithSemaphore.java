package Multithreading.SynchronizationandCommunication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerWithSemaphore {
    private static final int CAPACITY = 5;
    private static Queue<Integer> buffer = new LinkedList<>();

    // Semaphores
    private static Semaphore empty = new Semaphore(CAPACITY);
    private static Semaphore full = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1); // Binary semaphore (mutex)

    // Producer thread
    static class Producer extends Thread {
        public void run() {
            int value = 0;
            try {
                while (true) {
                    empty.acquire();       // Wait for empty slot
                    mutex.acquire();       // Lock buffer

                    buffer.add(value);
                    System.out.println("Produced: " + value);
                    value++;

                    mutex.release();       // Unlock buffer
                    full.release();        // Signal that item is available

                    Thread.sleep(1000);    // Simulate time to produce
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Consumer thread
    static class Consumer extends Thread {
        public void run() {
            try {
                while (true) {
                    full.acquire();        // Wait for item
                    mutex.acquire();       // Lock buffer

                    int item = buffer.poll();
                    System.out.println("Consumed: " + item);

                    mutex.release();       // Unlock buffer
                    empty.release();       // Signal that space is available

                    Thread.sleep(1500);    // Simulate time to consume
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }
}
