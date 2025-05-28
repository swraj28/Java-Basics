package CoreJava.Oops;

public class ClassAndObjectsExample extends Address {
    // Fields of the class
    int id;
    String name;

    // Constructor to initialize the object
    public ClassAndObjectsExample(int id, String name, String address) {
        super(address);
        this.id = id;
        this.name = name;
    }

    public void getId() {
        System.out.println("ID: " + id);
    }

    public void getName() {
        System.out.println("Name: " + name);
    }

    public void setId() {
        this.id = id;
    }

    public  void setName() {
        this.name = name;
    }

    // Method to display the details
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name);
        displayAddress();
    }

    public static void main(String[] args) {
        // Creating an object of the class
        ClassAndObjectsExample obj = new ClassAndObjectsExample(1, "John Doe", "Bihta Jinpura Road"); // ID: 1, Name: John Doe

        // Calling the method to display the details
        obj.display();
        obj.getName();
    }
}
