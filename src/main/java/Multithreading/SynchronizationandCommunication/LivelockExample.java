package Multithreading.SynchronizationandCommunication;

public class LivelockExample {
    static class Person {
        private final String name;
        private boolean side = true; // true = left, false = right

        Person(String name) {
            this.name = name;
        }

        public boolean isSide() {
            return side;
        }

        public void switchSide() {
            side = !side;
            System.out.println(name + " moves to " + (side ? "left" : "right"));
        }
    }

    public static void main(String[] args) {
        final Person alice = new Person("Alice");
        final Person bob = new Person("Bob");

        new Thread(() -> {
            while (alice.isSide() == bob.isSide()) {
                alice.switchSide();
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
            System.out.println("Alice passes.");
        }).start();

        new Thread(() -> {
            while (alice.isSide() == bob.isSide()) {
                bob.switchSide();
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
            System.out.println("Bob passes.");
        }).start();
    }
}

