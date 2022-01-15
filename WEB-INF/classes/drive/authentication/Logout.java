package drive.authentication;

import java.io.*;

import drive.verification.VerificationDriver;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Logout extends HttpServlet {
    
    /** 
     * Process the logout GET request, redirect to login upon success and show error in case of failure
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {
        // get session
        HttpSession session = req.getSession( false );

        // invalid session
        if ( session != null )
            session.invalidate();

        // redirect to login
        res.sendRedirect( "index.jsp" );
    }

}
