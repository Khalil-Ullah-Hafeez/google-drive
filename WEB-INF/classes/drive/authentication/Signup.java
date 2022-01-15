package drive.authentication;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.ArrayList;

public class Signup extends HttpServlet {
    
    /** 
     * Process the signup POST request, redirect to login upon success and show error in case of failure
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

        PrintWriter out = res.getWriter();

        // get data from request body
        SignupUser user = new SignupUser( 
            req.getParameter("email"), 
            req.getParameter("password"),
            req.getParameter("confirm_password"),
            req.getParameter("first_name"),
            req.getParameter("last_name"),
            req.getParameter("gender")
        );

        // validate data
        ArrayList<String> validate_list = user.validate();
        if ( validate_list.size() != 0 ) {
            res.setStatus( 400 );
            out.println( validate_list );
            out.close();
            return;
        }


        // insert data in the database
        String result = user.insertIntoDB();
        if ( result.length() != 0 ) {
            res.setStatus( 401 );
            out.println( result );
            out.close();
            return;
        }

        
        // redirect to login page
        // res.sendRedirect( "index.jsp" );
        
        out.close();
    }
}
