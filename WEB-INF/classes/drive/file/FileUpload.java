package drive.file;

import java.io.*;
import java.util.Collection;

// import org.apache.commons.fileupload.disk.DiskFileItemFactory;
// import org.apache.commons.fileupload.servlet.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FileUpload extends HttpServlet {
    
    
    /** 
     * Process the login POST request, redirect to homepage upon success and show error in case of failure
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doPost( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        HttpSession session = req.getSession( false );

        if ( session == null ) {
            out.println( "There is a problem with your session" );
            out.close();
            return;
        }

        String email = (String) session.getAttribute( "email" );

        

        String path = getServletContext().getRealPath("/data/" + email + "/");

        if ( !(new File( path )).exists() )
            (new File( path )).mkdir();
        
        try {
            final Collection<Part> parts = req.getParts();
            
            for (final Part part : parts) {
                part.write(path + part.getSubmittedFileName() );
            }

            out.print("The file has been uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Upload failed.");
        } finally {
            out.close();
        }

    }
}