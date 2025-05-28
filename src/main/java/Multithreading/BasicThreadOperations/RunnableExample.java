package Multithreading.BasicThreadOperations;

// RunnableExample.java

// Define a class that implements the Runnable interface
class MyRunnable implements Runnable {

    @Override
    public void run() {

        for(int i=0;i<5;i++){
            System.out.println("Thread is running using Runnable having id  " + Thread.currentThread().getId());

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                // Handle the exception
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}

// Main class to test MyRunnable
public class RunnableExample {
    public static void main(String[] args) {

        System.out.println("Going Inside Main Methode {} " + Thread.currentThread().getName());

        // Create an instance of MyRunnable
        MyRunnable runnable = new MyRunnable();

        // Create a Thread object and pass the Runnable instance
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        // Start the thread
        t1.start(); // Output: Thread is running using Runnable
        t2.start();

        // Using Lambda Expression

        Runnable objRunnable = () ->{
            System.out.println("Runnable Interface Using Lambda Expression");
//            throw new RuntimeException("Error In Runnable Interface");
//            throw new IOException("Error In Runnable Interface");
        };

        Thread t3 = new Thread(objRunnable, "Runnable interface using Lambda Expression");
        t3.start();


        //-------------------------


        Runnable task = () -> {
            throw new RuntimeException("Oops");
        };

        try {
            new Thread(task).start(); // runs in a new thread
        } catch (RuntimeException e) {
            System.out.println("------Here----------");
            // ❌ This won't catch the exception from the thread
        }


        // Instead we should be doing -> We can handle unchecked exception inside the methode


        Runnable task1 = () -> {
            try {
                throw new RuntimeException("Oops");
            } catch (RuntimeException e) {
                System.out.println("Caught inside thread: " + e.getMessage());
            }
        };


        Thread t5 = new Thread(task1);

        t5.start();

        // ------------------------ For Checked Exception

        Runnable r = () -> {
            // IOException is a checked exception — not allowed directly
            // throw new IOException("Oops"); // ❌ Compilation error (Checked Exception)
            System.out.println("Runnable running");
        };

        Thread t4 = new Thread(r);
        t4.start();
    }
}

