package drive.authentication;

import drive.utility.Validate;
import drive.database.DataSource;
import drive.error.FieldError;

import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import com.google.gson.Gson;

public class LoginUser
{
    private String email;
    private String password;

    public LoginUser(String email, String password)
    {   
        this.email = email;
        this.password = password;
    }

    
    /** 
     * returns the data members in a String format
     * @return String
     */
    public String toString() {
        return "Email is " + this.email + ". Password is " + this.password;
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


        return data;
    }


    public String checkFromDB()
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "SELECT * FROM users WHERE email LIKE ? AND password LIKE ?;" );
            ps.setString( 1, this.email );
            ps.setString( 2, this.password );

            ResultSet rs = ps.executeQuery();

            if ( !rs.next() )
                return "Credientials are incorrect";

        }
        catch ( SQLException err )
        {
            return "There was an error while communicating with the database";
        } 
        finally 
        {
            try
            {
                if ( ps != null )
                    ps.close();

                if ( con != null )
                    con.close();
            } catch ( SQLException err )
            {
                return "There was an error while communicating with the database";
            }
        }

        return "";
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
    
}