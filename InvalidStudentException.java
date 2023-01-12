
/**
 * Thrown whenever there's a problem in ClassList.
 * 
 * If this extends "RuntimeException", then this is an "unchecked" exception
 * and the caller doesn't have to catch it.
 * 
 * If this extends "Exception", then this is a "checked" exception and the
 * caller must catch or throw the exception.
 */
public class InvalidStudentException extends RuntimeException
{
    public InvalidStudentException(String msg)
    {
        super(msg);
    }
}
