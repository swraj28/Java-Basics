package Exception;

public class Main {

    public static void main(String[] args) {
        CustomCheckedException customCheckedException = new CustomCheckedException("Error");
        System.out.println(customCheckedException.getMessage());

        CustomCheckedException customCheckedException1 = new CustomCheckedException("Error", new Throwable("Learning"));

        System.out.println(customCheckedException1.getCause());


        CustomUncheckedException customUncheckedException = new CustomUncheckedException("Error");
        System.out.println(customUncheckedException.getMessage());
    }
}
