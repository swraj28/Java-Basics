package Multithreading.BasicThreadOperations;

import java.io.IOException;

public class CheckedExceptionInRunnable {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                throw new IOException("Simulated IO failure");
            } catch (IOException e) {
                System.out.println("Caught inside Runnable: " + e.getMessage());
            }
        };

//        Runnable task = () -> {
//            throw new IOException("This won't compile"); // ❌ Compilation error
//        };


        Thread thread = new Thread(task);
        thread.start();
    }
}
