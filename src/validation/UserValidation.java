package validation;

import exceptionHandling.EmailNotValidException;
import exceptionHandling.UsernameNotValidException;

public class UserValidation {

    public static void isUsernameValid(String name) throws UsernameNotValidException {
        if( name == null || name.isEmpty() ||
                !name.matches("[a-zA-Z\\s]+") )
            throw new UsernameNotValidException("Invalid UserName, Username contains only Characters not digits!");
    }
    public static void isEmailValid(String email) throws EmailNotValidException {
        if (email == null || email.isEmpty() ||
                !email.matches("[A-Za-z0-9_.]+@[a-z]+\\.[a-z]{2,3}"))
            throw new EmailNotValidException("Invalid email, Check Your Email");
    }

}
