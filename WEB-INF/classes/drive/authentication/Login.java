package drive.authentication;

import java.io.*;
import java.util.ArrayList;

import drive.verification.VerificationDriver;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Login extends HttpServlet {
    
    
    /** 
     * Process the login POST request, redirect to homepage upon success and show error in case of failure
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doPost( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException 
    {
        // setting this header to *, so we can communicate with server from any domain.
        res.setHeader("Access-Control-Allow-Origin", "*");

        // instantiate print writer
        PrintWriter out = res.getWriter();

        // get data from request body
        LoginUser user = new LoginUser( req.getParameter("email"), req.getParameter("password") );

        // validate data
        // if data is not valid, then set status to 401 and send an error array to client
        ArrayList<String> validate_list = user.validate();
        if ( validate_list.size() != 0 ) {
            res.setStatus( 400 );
            out.println( validate_list );
            out.close();
            return;
        }

        // verify data from database
        String result = user.checkFromDB();
        if ( result.length() != 0 ) {
            res.setStatus( 401 );
            out.println( result );
            out.close();
            return;
        }

        // (new VerificationDriver("khalil.formal@gmail.com")).sendVerificationMail();

        // create session
        HttpSession session = req.getSession( true );
        session.setAttribute( "email" ,  user.getEmail() );

        // redirect to home
        res.sendRedirect( "home.jsp" );

        // close PrintWriter
        out.close();
    }

}