package drive.file;

import java.io.*;

import drive.Beans.UserBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class DeleteFile extends HttpServlet {
    /** 
     * Process the Download GET request
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
        String filename = req.getParameter( "filename" );
        

        String complete_path = getServletContext().getRealPath("/data/" + email + "/" + filename);
        File file = new File( complete_path );
        
        if ( !file.exists() ) {
            res.setStatus( 404 );
            PrintWriter out = res.getWriter();
            out.print( "Files do not exist" );
            out.close();
            return;
        }

        file.delete();

    }
}
