package drive.file;

import java.io.*;
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
        // PrintWriter out = res.getWriter();

        HttpSession session = req.getSession( false );

        if ( session == null ) {
            // out.println( "There is a problem with your session" );
            // out.close();
            return;
        }

        String email = (String) session.getAttribute( "email" );
        String filename = req.getParameter( "filename" );
        

        // String path = getServletContext().getRealPath("/data/" + email + "/" + filename);
        // String path = getServletContext().getRealPath("/data/" + email + "/testemail.txt");


        

        // if ( !(new File( path )).exists() ) {
        //     out.print( "Files do not exist" );
        //     out.close();
        //     return;
        // }
        String path = "/data/" + email + "/" + filename;

        res.setContentType( getServletContext().getMimeType( path ) );
        res.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");

        InputStream instream = null;
        OutputStream outstream = null;
        try {
            instream = getServletContext().getResourceAsStream( path );
            System.out.print( instream );
            outstream = res.getOutputStream();

            byte[] buffer = new byte[1024];
        
            int numBytesRead;
            while ((numBytesRead = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, numBytesRead);
            }
        } finally {
            if (instream != null)
                outstream.close();

            if (outstream != null)
                outstream.close();

            // out.close();
        }

        // FileInputStream fileInputStream = new FileInputStream( path );

        // int i;
        // while ( (i = fileInputStream.read()) != -1 ) {
        //     out.write(i);
        // }

        // fileInputStream.close();        

        // out.close();
    }
}