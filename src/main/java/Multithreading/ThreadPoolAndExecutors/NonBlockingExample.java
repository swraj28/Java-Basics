package Multithreading.ThreadPoolAndExecutors;

import java.util.concurrent.*;


/*
| Feature                       | `Future`                        | `CompletableFuture` (non-blocking)            |
| ----------------------------- | ------------------------------- | --------------------------------------------- |
| Submits async task            | ✅ Yes                           | ✅ Yes                                         |
| Wait for result               | ❌ **Blocking** (`get()` blocks) | ✅ **Non-blocking chaining with `.thenXXX()`** |
| Chain tasks                   | ❌ No                            | ✅ Yes                                         |
| Reuse same thread efficiently | 🚫 Hard to manage               | ✅ Easily done via chaining or async execution |

--------------------------------------------------------------

CompletableFuture allows the main/user thread to continue immediately (non-blocking).

The actual task runs on a thread from the pool.

If there's only one thread in the pool, and it's busy, it can't start a second task until it finishes the first one.

That thread is not freed until the task completes (i.e., result is produced or exception is thrown).

 */

public class NonBlockingExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2); // only 2 threads

        System.out.println("Main starts: " + Thread.currentThread().getName());

        // Simulate a long-running task
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("Future1 starts: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000); // Simulates long-running task
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Future1 ends: " + Thread.currentThread().getName());
        }, executor);

        // This task is submitted immediately after, to see if it gets the same thread
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("Future2 starts: " + Thread.currentThread().getName());
            System.out.println("Future2 ends: " + Thread.currentThread().getName());
        }, executor);

        // Since the thread-pool have only 2 threads , this task will be picked only after any of the thread gets freed from the exection
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("Future3 starts: " + Thread.currentThread().getName());
            System.out.println("Future3 ends: " + Thread.currentThread().getName());
        },executor);

        // Wait for both to finish
        CompletableFuture.allOf(future1, future2, future3).join();

        executor.shutdown();
        System.out.println("Main ends: " + Thread.currentThread().getName());
    }
}

