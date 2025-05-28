package Multithreading.SynchronizationandCommunication;

public class StarvationExample {
    public static void main(String[] args) {
        Thread highPriority = new Thread(() -> {
            while (true) {
                System.out.println("High-priority thread running...");
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            }
        });

        Thread lowPriority = new Thread(() -> {
            while (true) {
                System.out.println("Low-priority thread running...");
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            }
        });

        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);

        highPriority.start();
        lowPriority.start();
    }
}

