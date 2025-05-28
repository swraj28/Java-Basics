package Multithreading.AtomicVolatileSynchronized;


class SharedObj {

    private volatile Boolean flag = false;

    public void setFlag() {
        System.out.println("Setting the flag as true");
        flag=true;
    }

    public void getFlag() {

        while(!flag) {
            // do nothing
        }

        System.out.println("Flag is True");

        // Why this log is not printed as every thread has it's own cache and in the cache of thread2 initial value of flag is set to false
        // that's why it is stuck in the while . To make the changes visible we should use the volatile keyword .
    }

}

public class VolatileExample {

    SharedObj sharedObj = new SharedObj(); // Assume this class exists

    Thread t1 = new Thread(() -> {
        try {
            Thread.sleep(100); // t1.sleep(...) is incorrect; should be Thread.sleep(...)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        sharedObj.setFlag(); // sets some shared value
    });

    Thread t2 = new Thread(() -> {
        sharedObj.getFlag(); // presumably reads some shared value
    });

    public void startThreads() {
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new VolatileExample().startThreads();
    }
}

