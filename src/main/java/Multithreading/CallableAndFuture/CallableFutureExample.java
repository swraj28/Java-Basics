package Multithreading.CallableAndFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Callable Interface — Short Explanation:
Callable<V> is similar to Runnable but more powerful.

✅ Key Differences from Runnable:
It returns a result via V call().

It can throw checked exceptions.

📌 Use Callable when:

You need to return a result.
You want to handle checked exceptions in your task logic.

 */

public class CallableFutureExample {
    public static void main(String[] args) {
        // Create a thread pool with a single thread
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Create a callable task
        Callable<String> callableTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000); // Simulate some work
                return "Callable task result";
            }
        };

        // Submit the callable task to the executor
        Future<String> future = executor.submit(callableTask);

        // Do other tasks if needed

        // Get the result of the callable task
        try {
            String result = future.get();
            System.out.println("Result from callable task: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // Output:
//        Result from callable task: Callable task result

        // Shut down the executor
        executor.shutdown();
    }
}