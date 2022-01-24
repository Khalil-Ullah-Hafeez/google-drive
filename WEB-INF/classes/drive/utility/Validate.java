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
    public static String isValidEmail(String email)
    {
        if ( email == null || email.isEmpty() )
            return "email can't be empty";

        if ( !EmailValidator.getInstance().isValid(email) )
            return "email is not valid";

        return "";
    }

    /** 
     * Check whether a password is valid or not
     * @param password
     * @return boolean
     */
    public static String isValidPassword(String password)
    {
        if ( password == null || password.isEmpty() )
            return "password can't be empty";

        if ( password.length() < 8 )
            return "password can't have less than 8 characters";

        return "";
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
        if ( data == null || data.isEmpty() )
            return false;
        else
            return true;
    }

}