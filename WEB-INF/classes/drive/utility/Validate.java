package drive.utility;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * a utility class to validate user input
 */
public class Validate
{
    /** 
     * Check whether an email is valid or not
     * @param email
     * @return boolean
     */
    public static boolean isValidEmail(String email)
    {
        return EmailValidator.getInstance().isValid(email);
    }

    
    /** 
     * Check whether a password is valid or not
     * @param password
     * @return boolean
     */
    public static boolean isValidPassword(String password)
    {
        boolean result = true;

        if ( password.length() < 1 )
            result=false;

        return result;

    }

    
    /** 
     * Check whether two passwords match
     * @param password
     * @param confirm_password
     * @return boolean
     */
    public static boolean matchPassword(String password, String confirm_password)
    {
        if ( password.equals( confirm_password ) )
            return true;
        else
            return false;
    }

    
    /** 
     * Check whether a generic string is valid for the application
     * @param data
     * @return boolean
     */
    public static boolean isValidString(String data)
    {
        if ( !data.equals( "" ) )
            return true;
        else
            return false;
    }

}