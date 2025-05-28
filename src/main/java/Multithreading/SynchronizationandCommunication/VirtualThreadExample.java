package Multithreading.SynchronizationandCommunication;


/*
What Are Virtual Threads?
Lightweight threads managed by the JVM, not the OS.

Created using the Thread.ofVirtual() factory.

Ideal for concurrent I/O-bound tasks (like HTTP calls, DB queries).

Backed by platform threads but much cheaper in terms of memory and scheduling.

| Feature      | Platform Threads       | Virtual Threads                       |
| ------------ | ---------------------- | ------------------------------------- |
| Managed By   | OS                     | JVM Scheduler                         |
| Memory Usage | High (1MB+ per thread) | Low (\~few KB per thread)             |
| Scalability  | Limited (\~thousands)  | Massive (\~millions possible)         |
| Suitable For | CPU-bound tasks        | I/O-bound, high-concurrency workloads |


✅ Use Platform Threads when:
You're doing CPU-intensive tasks (e.g., number crunching, image processing).

You need low-latency, tight control over threads.

You rely heavily on thread-local variables, or native calls that aren’t virtual-thread friendly.

Your application is multi-threaded but not massively concurrent.

✅ Use Virtual Threads when:
You're doing massive I/O-bound concurrency (e.g., web servers, database access).

You need millions of concurrent tasks, like handling HTTP requests or chat users.

You want a simpler alternative to async code (like CompletableFuture or reactive).

 */

//public class VirtualThreadExample {
//    public static void main(String[] args) throws InterruptedException {
//        Runnable task = () -> {
//            System.out.println("Running in thread: " + Thread.currentThread());
//            try {
//                Thread.sleep(1000); // Simulates blocking I/O
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//
//        Thread vt = Thread.ofVirtual().start(task);  // I am using java 17 and virtual threds has been introduced in java 21
//
//        vt.join(); // Wait for virtual thread to finish
//        System.out.println("Main thread finished.");
//    }
//}

