package validation;

import exceptionHandling.DueDateFormatNotValidateException;
import model.TaskStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskValidation {
    public static LocalDateTime convertAndValidateDueDate(String dueDate) throws DueDateFormatNotValidateException {
        if(dueDate == null || dueDate.isEmpty() ||
                !dueDate.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}")){
            throw new DueDateFormatNotValidateException("Due Date Format Not Valid");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dueDate,dateTimeFormatter);
    }
    public static boolean isStatusValid(String status){
        if(status == null || status.isEmpty()){
            return false;
        }
        try {
            TaskStatus.valueOf(status.toUpperCase());
            return true;
        }catch (IllegalArgumentException exception){
            return false;
        }
    }
}
