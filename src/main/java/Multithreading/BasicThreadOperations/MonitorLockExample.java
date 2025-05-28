package Multithreading.BasicThreadOperations;


/*
🔒 What is a Monitor Lock in Java?

In Java, monitor lock (or intrinsic lock) is a mechanism that ensures only one thread can access a synchronized block or method of an object at a time.
Every object in Java has an associated monitor (lock).
When a thread enters a synchronized block/method, it acquires the lock on that object.
Other threads trying to access the same synchronized section must wait until the lock is released.

Monitor locks are present at the object level, not the method level.

✅ Monitor lock is on the object, but only used when code is marked synchronized.

❌ If a method like printCount() is not synchronized, any thread (like main) can call it without acquiring the lock, even if other threads are holding it.

*/

class SharedCounter_1 {
    private int count = 0;

    public void increment() {
        System.out.println(Thread.currentThread().getName() + " is trying to acquire the lock...");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " has acquired the lock.");
            count++;
            // Simulate work
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + " is releasing the lock.");
        }
    }

    public void printCount() {
        System.out.println("The current thread is " + Thread.currentThread().getName() + " and the total count is " + this.count);
    }

    public int getCount() {
        return count;
    }
}

public class MonitorLockExample {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter_1 counter = new SharedCounter_1();

        Runnable task = () -> {
            for (int i = 0; i < 2; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        Thread t3 = new Thread(() ->{
            counter.printCount();
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Final count: " + counter.getCount());
    }
}


