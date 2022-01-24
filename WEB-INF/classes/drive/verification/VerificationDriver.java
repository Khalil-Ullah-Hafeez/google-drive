package drive.verification;

import java.io.*;
import java.util.Random;

import drive.Beans.UserBean;
import drive.database.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class VerificationDriver extends HttpServlet {

    /** 
     * Process the GET request
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {
        String code = req.getParameter( "code" );

        if ( code != null ) {
            verifyIncomingCode(req, res);
            return;
        }

        HttpSession session = req.getSession( false );

        if ( session == null ) {
            res.setStatus( 401 );
            PrintWriter out = res.getWriter();
            out.println( "There is a problem with your session" );
            out.close();
            return;
        }

        String email = ( (UserBean) session.getAttribute( "user" ) ).getEmail();

        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);

        session.setAttribute( "email-verification-number", n );

        Verification.sendMail( email, n );
    }

    public void verifyIncomingCode( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {
        HttpSession session = req.getSession( false );

        if ( session == null ) {
            res.sendRedirect( "error.jsp?message=Your email could not be verified because there was an issue with your session." );
        }

        UserBean user = (UserBean) session.getAttribute( "user" );
        String email = user.getEmail();
        int session_code = (int) session.getAttribute( "email-verification-number" );
        int request_code = Integer.parseInt( req.getParameter( "code" ) );

        try {
            if ( session_code == request_code ) {
                UserDAO.verifyEmail( email );

                user.setEmail_verified( true );

                res.sendRedirect( "success.jsp?message=Your email is verified successfully." );
            } else {
                res.sendRedirect( "error.jsp?message=Your email could not be verified." );
            }
        } catch ( Exception  err ) {
            res.sendRedirect( "error.jsp?message=Your email could not be verified." );
        }

    }
}
