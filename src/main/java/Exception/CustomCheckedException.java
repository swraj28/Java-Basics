package Exception;



public class CustomCheckedException extends  Exception{

    public CustomCheckedException(String message){
        super(message);
    }

    public CustomCheckedException(String message, Throwable cause) {
        super(message,cause);
    }

    // Constructor that accepts a cause
    public CustomCheckedException(Throwable cause) {
        super(cause);
    }
}

