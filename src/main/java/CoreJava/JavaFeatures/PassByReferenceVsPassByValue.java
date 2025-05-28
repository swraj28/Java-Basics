package CoreJava.JavaFeatures;


/*
Everything is passed by value — even when passing an object to a method.

1.When you pass an object to a method, you pass a copy of the reference (the "address") that points to the object, not the actual object itself.

2.Inside the method, if you change the object's fields or state, those changes are visible outside because both the original reference and the copy point to the same object.

3.But, if you reassign the object reference inside the method (make it point to a new object),
that reassignment only changes the local copy of the reference, not the original reference outside the method.

 */

class Person {
    String name;
}

public class PassByReferenceVsPassByValue {

    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Alice";

        updatePerson(person);

        System.out.println(person.name);  // Output: "Bob"

        reassignPerson(person);

        System.out.println(person.name);  //Output: "Bob"
    }

    public static void updatePerson(Person p) {
        p.name = "Bob";  // Changing the object's state -> visible outside
    }

    public static void reassignPerson(Person p) {
        p = new Person();  // p now points to a new Person object
        p.name = "Charlie";  // Changes the new object, not the original
    }
}
