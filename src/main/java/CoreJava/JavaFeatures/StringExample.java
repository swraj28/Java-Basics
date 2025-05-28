package CoreJava.JavaFeatures;

public class StringExample {
    public static <string> void main(String[] args) {
        // Creating strings
        String str1 = "Hello";
        String str2 = "World";

        // Concatenating strings
        String str3 = str1 + " " + str2;
        System.out.println("Concatenated string: " + str3); // Output: Concatenated string: Hello World

        // String length
        System.out.println("Length of str3: " + str3.length()); // Output: Length of str3: 11

        // Character at a specific index
        System.out.println("Character at index 1 in str3: " + str3.charAt(1)); // output: Character at index 1 in str3: e

        // Substring
        System.out.println("Substring of str3 from index 6 to 11: " + str3.substring(6, 11)); // Output: Substring of str3 from index 6 to 11: World

        String str4 = "Java";
        String str5 = new String("Java").intern();

        System.out.println("str4 == str5: " + (str4 == str5));

        String str6 = new String("Swraj");
        String str7 = new String( "Swraj");

        System.out.println("str6 == str7: " + (str6 == str7)); // Output: str6 == str7: false

        String str8 = "Swraj";

        System.out.println("str6 == str8: " + (str6 == str8));
    }
}
