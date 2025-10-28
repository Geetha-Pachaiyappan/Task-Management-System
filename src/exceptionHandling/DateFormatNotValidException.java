package exceptionHandling;

public class DateFormatNotValidException extends Exception{
    public DateFormatNotValidException(String message){
        super(message);
    }
}
