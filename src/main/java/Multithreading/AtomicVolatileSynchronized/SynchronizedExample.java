package Multithreading.AtomicVolatileSynchronized;

class SafeCounter {
    private int count = 0;

    public synchronized void increment(String threadName) {
        System.out.println("In Synchronized Block {}" + threadName);
        count++; // Now thread-safe
    }

//    public void increment(String threadName) {
//        System.out.println("Out of Synchronized Block {}" + threadName);
//        synchronized (this) {
//            count++;
//        }
//    }

//    public int getCount(){
//        return  count;
//    }

    public synchronized int getCount() {
        return count;
    }
}

public class SynchronizedExample {
    public static void main(String[] args) throws InterruptedException {
        SafeCounter counter = new SafeCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment(Thread.currentThread().getName());
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment(Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.getCount()); // Expected: 2000
    }
}

