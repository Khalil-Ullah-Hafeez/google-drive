package drive.file;

import java.io.*;

import drive.Beans.UserBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class DownloadFile extends HttpServlet {

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

        if ( !(new File( complete_path )).exists() ) {
            res.setStatus( 404 );
            PrintWriter out = res.getWriter();
            out.print( "Files do not exist" );
            out.close();
            return;
        }

        String path = "/data/" + email + "/" + filename;

        res.setContentType( getServletContext().getMimeType( path ) );
        res.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");

        InputStream instream = null;
        OutputStream outstream = null;
        try {
            instream = getServletContext().getResourceAsStream( path );
            outstream = res.getOutputStream();

            byte[] buffer = new byte[1024];
        
            int numBytesRead;
            while ((numBytesRead = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, numBytesRead);
            }
        } finally {
            if (instream != null)
                instream.close();

            if (outstream != null)
                outstream.close();
        }
    }
}