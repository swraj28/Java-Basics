package Multithreading.BasicThreadOperations;

import java.util.concurrent.*;

/*
 Executor (executor.execute(task))
        Just runs the task asynchronously.
        Does not return anything, so:

        You can't catch any unchecked or checked exception outside.

        Even if the task throws a RuntimeException, it's lost unless caught inside the task.

🔸 ExecutorService (executorService.submit(task))
        Returns a Future.
        Even if the task throws an unchecked exception, it gets captured inside the Future.


        | Feature                      | `Executor`     | `ExecutorService` + `submit()` |
| ---------------------------- | -------------- | ------------------------------ |
| Returns result?              | ❌ No           | ✅ Yes, via `Future`            |
| Exception caught outside?    | ❌ No           | ✅ Yes, via `future.get()`      |
| Use for catching exceptions? | ❌ Not suitable | ✅ Preferred approach           |

 */

public class RunnableWithExecutorService {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            if (true) {
                throw new RuntimeException("Something went wrong in Runnable"); // RuntimeException is an unchecked exception .
            }
             System.out.println("Runnable Done");
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> result = executor.submit(task);

        try {
            result.get(); // This will throw ExecutionException if Runnable throws
        } catch (ExecutionException e) {
            System.out.println("Caught: " + e.getCause()); // prints the actual exception
        }

        executor.shutdown();
    }
}
