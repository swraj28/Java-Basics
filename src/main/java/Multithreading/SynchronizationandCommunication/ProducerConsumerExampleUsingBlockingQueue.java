package Multithreading.SynchronizationandCommunication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerExampleUsingBlockingQueue {
    private static final int BUFFER_SIZE = 10;
    private static BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }

    static class Producer implements Runnable {
        public void run() {
            try {
                int item = 1;
                while (true) {
                    // Produce item
                    buffer.put(item++);

                    System.out.println("Produced item: " + (item - 1));
                    Thread.sleep(1000); // Simulate production time
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupting the producer thread {}" + e);
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            try {
                while (true) {
                    // Consume item
                    int item = buffer.take();

                    System.out.println("Consumed item: " + item);
                    Thread.sleep(2000); // Simulate processing time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

