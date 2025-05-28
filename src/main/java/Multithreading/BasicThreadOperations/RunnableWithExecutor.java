package Multithreading.BasicThreadOperations;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableWithExecutor {
    public static void main(String[] args) {
        Runnable task = () -> {
            throw new RuntimeException("Exception inside Runnable");  // RunTimeException is an unchecked exception if we use checked exception IOException it leads to compilation error
        };


        Executor executor = Executors.newSingleThreadExecutor();

        try{
            executor.execute(task); // No way to catch exception directly here
        }catch(RuntimeException e){
            System.out.println("Exception Occured {} "+e);
        }

//        executor.execute(task); // No way to catch exception directly here

        System.out.println("Main thread continues...");

        // Executor doesn't expose shutdown(), so if you're using Executors.newSingleThreadExecutor(), you should cast it back to ExecutorService to shut it

        ((ExecutorService) executor).shutdown();
    }
}
