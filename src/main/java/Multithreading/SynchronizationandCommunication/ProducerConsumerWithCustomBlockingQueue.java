package Multithreading.SynchronizationandCommunication;

import java.util.ArrayList;
import java.util.List;


/*
        ✅ synchronized(buffer) means:
        Only one thread (either Producer or Consumer) can access the buffer block at a time.

        🔄 What Happens:
        When Producer enters synchronized(buffer), it acquires the lock on buffer.

        While it holds the lock, Consumer cannot enter its synchronized block.

        Once Producer finishes its operation and calls buffer.notifyAll() and exits the block, it releases the lock.

        Then, Consumer (if waiting) can acquire the lock and proceed — and vice versa.

        🧠 Why it's important:
        This ensures mutual exclusion, preventing race conditions on the shared buffer.
 */

public class ProducerConsumerWithCustomBlockingQueue {

    private static final int BUFFER_SIZE = 10;
    private static final List<Integer> buffer = new ArrayList<>();
    private static int value = 0;

    static class Producer implements Runnable {

        @Override
        public void run() {
            try {
                while (value < 10) {
                    synchronized (buffer) {
                        while (buffer.size() == BUFFER_SIZE) {
                            buffer.wait(); // wait if buffer is full
                        }
                        buffer.add(value);
                        System.out.println("Produced: " + value++);
                        buffer.notifyAll(); // notify consumer
                    }
                    Thread.sleep(100); // simulate production time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                while (value < 10) {
                    synchronized (buffer) {
                        while (buffer.isEmpty()) {
                            buffer.wait(); // wait if buffer is empty
                        }
                        int item = buffer.remove(0);
                        System.out.println("Consumed: " + item);
                        buffer.notifyAll(); // notify producer
                    }
                    Thread.sleep(100); // simulate consumption time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }
}
