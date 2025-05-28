package CoreJava.JavaFeatures;

// AbstractExample abstract class with an abstract method
abstract class AbstractExample {
    // Abstract method (no implementation)
    abstract void abstractMethod();
}

// Concrete subclass of AbstractExample
class ConcreteExample extends AbstractExample {
    // Implementation of abstract method
    @Override
    void abstractMethod() {
        System.out.println("Inside abstractMethod in ConcreteExample");
    }
}

public class AbstractKeywordExample {

    public static void main(String[] args) {
        ConcreteExample concreteExample = new ConcreteExample();
        concreteExample.abstractMethod(); // Call the method
    }
}
