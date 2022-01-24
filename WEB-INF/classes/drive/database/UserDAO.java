package drive.database;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;

import drive.Beans.UserBean;

public class UserDAO {
   
    public static String loginCheck( UserBean user ) {
        Connection con = null;
        PreparedStatement ps = null;

        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "SELECT * FROM users WHERE email LIKE ? AND password LIKE ?;" );
            ps.setString( 1, user.getEmail() );
            ps.setString( 2, user.getPassword() );

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

    public static String insertUser( UserBean user ) {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "INSERT INTO users (email, password, first_name, last_name, gender) VALUES (?,?,?,?,?)" );
            ps.setString( 1, user.getEmail() );
            ps.setString( 2, user.getPassword() );
            ps.setString( 3, user.getFirst_name() );
            ps.setString( 4, user.getLast_name() );
            ps.setString( 5, user.getGender() );

            int affected_rows = ps.executeUpdate();

            if ( affected_rows != 1 ) {
                return "Your account could not be registered";
            }
     
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

    public static String updateUser( UserBean user ) {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ?, gender = ? WHERE email LIKE ?;" );
            ps.setString( 1, user.getEmail() );
            ps.setString( 2, user.getPassword() );
            ps.setString( 3, user.getFirst_name() );
            ps.setString( 4, user.getLast_name() );
            ps.setString( 5, user.getGender() );
            ps.setString( 6, user.getEmail() );

            int affected_rows = ps.executeUpdate();

            if ( affected_rows != 1 ) {
                return "Your data could not be updated";
            }
     
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
            err.printStackTrace();
            return "There was an error while communicating with the database";
        }
    }

    public static String updateEmail( String email  ) {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "UPDATE users SET email = ? WHERE email LIKE ?" );
            ps.setString( 1, email );
            ps.setString( 2, email );

            int affected_rows = ps.executeUpdate();

            if ( affected_rows != 1 ) {
                return "Your email could not be changed";
            }
     
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

    public static UserBean getUser( String email ) {
        UserBean user = null;
        Connection con = null;
        PreparedStatement ps = null;

        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "SELECT * FROM users WHERE email LIKE ?;" );
            ps.setString( 1, email );

            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                user = new UserBean( 
                    rs.getString( "email" ), 
                    rs.getString( "password" ), 
                    "", 
                    rs.getString( "first_name" ), 
                    rs.getString( "last_name" ), 
                    rs.getString( "gender" ),
                    rs.getBoolean( "email_verified" )
                );
            }
                

        }
        catch ( SQLException err )
        {
            err.printStackTrace();
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
                err.printStackTrace();
            }
        }

        return user;
    }

    public static boolean deleteAccount( String email ) {
        Connection con = null;
        PreparedStatement ps = null;

        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "DELETE FROM users WHERE email LIKE ?;" );
            ps.setString( 1, email );

            int i = ps.executeUpdate();

            if ( i != 1 ) {
                return false;
            }
            
        }
        catch ( SQLException err )
        {
            err.printStackTrace();
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
                err.printStackTrace();
            }
        }

        return true;
    }

    public static boolean verifyEmail( String email ) {
        Connection con = null;
        PreparedStatement ps = null;

        try
        {
            BasicDataSource ds = DataSource.getDataSource();
            con = ds.getConnection();

            ps = con.prepareStatement( "UPDATE users SET email_verified = 1 WHERE email LIKE ?;" );
            ps.setString( 1, email );
            int i = ps.executeUpdate();

            if ( i != 1 ) {
                return false;
            }
            
        }
        catch ( SQLException err )
        {
            err.printStackTrace();
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
                err.printStackTrace();
            }
        }

        return true;
    }

}
