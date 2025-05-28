package Multithreading.ThreadPoolAndExecutors;

import java.util.concurrent.*;

public class CompletableFutureUsingThreadPoolExecutor {

    public static void main(String[] args) {

        ThreadPoolExecutor threadpool = new ThreadPoolExecutor(
                1, 1, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );


        CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task complete";
        },threadpool);

        // Wait for the result and print it
        try {
            String result = asyncTask1.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("-----Reached Here ---------");

        threadpool.shutdown();
    }
}
