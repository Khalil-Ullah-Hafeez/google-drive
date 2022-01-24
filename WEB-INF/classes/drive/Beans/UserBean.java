package drive.Beans;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

import drive.utility.Validate;

public class UserBean implements Serializable {
    private String email;
    private String password;
    private String confirm_password;
    private String first_name;
    private String last_name;
    private String gender;
    private boolean email_verified;

    public UserBean() {
        this.email = "";
        this.password = "";
        this.confirm_password = "";
        this.first_name = "";
        this.last_name = "";
        this.gender = "";
    }

    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserBean(String email, String password, String confirm_password, String first_name, String last_name, String gender, boolean email_verified) {
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email_verified = email_verified;
    }

    public UserBean(String email, String password, String confirm_password, String first_name, String last_name, String gender) {
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
    }
    
    public ArrayList<String> validateLogin()
    {
        ArrayList<String> data = new ArrayList<String>();
        Gson gson = new Gson();

        String email_validation = Validate.isValidEmail( this.email );
        if ( email_validation.length() != 0 )
            data.add( gson.toJson( new FieldErrorBean("email", email_validation) ) );

        String password_validation = Validate.isValidPassword( this.password );
        if ( password_validation.length() != 0 )
            data.add( gson.toJson( new FieldErrorBean("password", password_validation) ) );

        return data;
    }

    public ArrayList<String> validate()
    {
        ArrayList<String> data = new ArrayList<String>();
        Gson gson = new Gson();

        String email_validation = Validate.isValidEmail( this.email );
        if ( email_validation.length() != 0 )
            data.add( gson.toJson( new FieldErrorBean("email", email_validation) ) );

        String password_validation = Validate.isValidPassword( this.password );
        if ( password_validation.length() != 0 )
            data.add( gson.toJson( new FieldErrorBean("password", password_validation) ) );

        String confirm_password_validation = Validate.isValidPassword( this.confirm_password );
        if ( confirm_password_validation.length() != 0 )
            data.add( gson.toJson( new FieldErrorBean("confirm_password", confirm_password_validation) ) );

        if ( !Validate.matchPassword( this.password, this.confirm_password ) )
            data.add( gson.toJson( new FieldErrorBean("confirm_password", "Confirm password does not match password") ) );

        if ( !Validate.isValidString( this.first_name ) )
            data.add( gson.toJson( new FieldErrorBean("first_name", "First name is not valid") ) );

        if ( !Validate.isValidString( this.last_name ) )
            data.add( gson.toJson( new FieldErrorBean("last_name", "Last name is not valid") ) );

        if ( !Validate.isValidString( this.gender ) )
            data.add( gson.toJson( new FieldErrorBean("gender", "Gender is not valid") ) );

        return data;
    }


    public boolean isEmail_verified() {
        return this.email_verified;
    }

    public boolean getEmail_verified() {
        return this.email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return this.confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
