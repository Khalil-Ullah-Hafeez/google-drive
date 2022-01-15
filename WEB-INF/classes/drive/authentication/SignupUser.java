package drive.authentication;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.ArrayList;
import com.google.gson.Gson;

import drive.utility.Validate;
import drive.database.DataSource;
import drive.error.FieldError;

public class SignupUser {
    private String email;
    private String password;
    private String confirm_password;
    private String first_name;
    private String last_name;
    private String gender;


    public SignupUser(
        String email, 
        String password,
        String confirm_password,
        String first_name,
        String last_name,
        String gender
    )
    {   
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
    }

    /** 
     * Validates the data members
     * @return boolean
     */
    public ArrayList<String> validate()
    {
        ArrayList<String> data = new ArrayList<String>();
        Gson gson = new Gson();

        if ( !Validate.isValidEmail( this.email ) )
            data.add( gson.toJson( new FieldError("email", "Email is not valid") ) );

        if ( !Validate.isValidPassword( this.password ) )
            data.add( gson.toJson( new FieldError("password", "Password is not valid") ) );

        // if ( !Validate.isValidPassword( this.confirm_password ) )
        //     data.add( gson.toJson( new FieldError("confirm_password", "Confirm password is not valid") ) );

        if ( !Validate.matchPassword( this.password, this.confirm_password ) )
            data.add( gson.toJson( new FieldError("confirm_password", "Confirm password is not valid") ) );

        if ( !Validate.isValidString( this.first_name ) )
            data.add( gson.toJson( new FieldError("first_name", "First name is not valid") ) );

        if ( !Validate.isValidString( this.last_name ) )
            data.add( gson.toJson( new FieldError("last_name", "Last name is not valid") ) );

        if ( !Validate.isValidString( this.gender ) )
            data.add( gson.toJson( new FieldError("gender", "Gender is not valid") ) );

        return data;
    }

    public String insertIntoDB()
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "INSERT INTO users (email, password, first_name, last_name, gender) VALUES (?,?,?,?,?)" );
            ps.setString( 1, this.email );
            ps.setString( 2, this.password );
            ps.setString( 3, this.first_name );
            ps.setString( 4, this.last_name );
            ps.setString( 5, this.gender );

            int affected_rows = ps.executeUpdate();
     
            if ( ps != null )
            ps.close();

            if ( con != null )
            con.close();
            
            return "";
        }
        catch (SQLIntegrityConstraintViolationException err) {
            return "There is another user already registered with this email";
        }
        catch ( SQLException err )
        {
            return "There was an error while communicating with the database";
        }
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
