package drive.credientials;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import drive.Beans.UserBean;
import drive.database.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteAccount extends HttpServlet {
    /** 
     * Process the delete account GET request
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {
        HttpSession session = req.getSession( false );

        if ( session == null ) {
            res.setStatus( 401 );
            PrintWriter out = res.getWriter();
            out.println( "There is a problem with your session" );
            out.close();
            return;
        }

        String email = ( (UserBean) session.getAttribute( "user" ) ).getEmail();

        String complete_path = getServletContext().getRealPath("/data/" + email);

        File folder = new File( complete_path );

        if ( folder.exists() ) {
            folder.delete();
        }

        if ( !UserDAO.deleteAccount( email ) ) {
            res.setStatus( 409 );
            PrintWriter out = res.getWriter();
            out.println( "Your account could not be deleted" );
            out.close();
            return;
        }

        session.invalidate();
        
    }
}
