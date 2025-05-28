package Multithreading.ThreadPoolAndExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                1, 1, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        Future<?> futureObj = threadPool.submit(() -> { //runnable
            try {
                Thread.sleep(7000);
                System.out.println("this task is done by the thread");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<Integer> list = new ArrayList<>();

        Future<List<Integer>> futureObj2 = threadPool.submit(() -> {  // Runnable with task T
            try {
                Thread.sleep(1000);
                list.add(100);
                // no return here!
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, list);


        Future<Integer> futureObj1 = threadPool.submit(() -> {   // Callable
            try {
                Thread.sleep(7000);
                return 45;
            } catch(Exception e) {
                e.printStackTrace();
                return -1; // or any default value you prefer
            }
        });


        System.out.println("is done {}" + futureObj.isDone());

        // Wait for task to complete (optional but helps in debugging)
        try {
            futureObj.get(2,TimeUnit.MILLISECONDS);
        }catch(TimeoutException e){
            System.out.println("Timeout Exception Occured " + e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("is done {}" + futureObj.isDone());

        System.out.println("The system call reached here");

        threadPool.shutdown(); // always shut down the pool
    }
}
