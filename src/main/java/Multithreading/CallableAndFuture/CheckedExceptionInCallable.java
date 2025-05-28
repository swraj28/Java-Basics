package Multithreading.CallableAndFuture;

import java.io.IOException;
import java.util.concurrent.*;

public class CheckedExceptionInCallable {
    public static void main(String[] args) {
        Callable<String> task = () -> {
            throw new IOException("Simulated IO failure");
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(task);

        try {
            future.get(); // this will throw ExecutionException
        } catch (ExecutionException e) {
            System.out.println("Caught in main: " + e.getCause()); // prints IOException
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
    }
}
