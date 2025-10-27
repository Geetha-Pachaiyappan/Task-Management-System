package exceptionHandling;

public class UsernameNotValidException extends Exception{
    public UsernameNotValidException(String message){
        super(message);
    }
}
