package CoreJava.Oops;

interface A {
    default void show() {
        System.out.println("A's show");
    }
}

interface B extends A {
    default void show() {
        System.out.println("B's show");
    }
}

interface C extends A {
    default void show() {
        System.out.println("C's show");
    }
}

// Class D implements both B and C → diamond structure
class D implements B, C {
    // Must override show() to resolve conflict
    public void show() {
        // Explicitly choosing which one to call or provide own logic
        B.super.show();  // or C.super.show();
        System.out.println("D's own show");
    }
}


public class DiamondProblem {

    public static void main(String[] args) {
        D obj = new D();
        obj.show();
    }
}
