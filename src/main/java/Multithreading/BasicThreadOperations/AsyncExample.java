package Multithreading.BasicThreadOperations;

import java.util.concurrent.CompletableFuture;

public class AsyncExample {
    public static void main(String[] args) {
        System.out.println("Request received: " + Thread.currentThread().getName());

        // Simulate an asynchronous database query
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching data from DB: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data from DB";
        }).thenApply(data -> {
            System.out.println("Processing data: " + Thread.currentThread().getName());
            return "Processed " + data;
        }).thenAccept(result -> {
            System.out.println("Sending response: " + Thread.currentThread().getName());
            System.out.println("Response: " + result);
        });

        System.out.println("Thread is free to handle other requests: " + Thread.currentThread().getName());

        // Keep the main thread alive to see the async output
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
