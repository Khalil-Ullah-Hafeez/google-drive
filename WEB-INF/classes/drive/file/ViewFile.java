package drive.file;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.google.gson.*;

public class ViewFile extends HttpServlet {
    /** 
     * Process the viewFile GET request
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {   
        // get the writer
        PrintWriter out = res.getWriter();

        // instantiate the Gson object
        Gson gson = new Gson();

        // check if session exists, if it does exist then return error
        HttpSession session = req.getSession( false );
        if ( session == null ) {
            res.setStatus( 401 );
            out.println( "There is a problem with your session" );
            out.close();
            return;
        }

        String email = (String) session.getAttribute( "email" );
        String path = getServletContext().getRealPath("/data/" + email + "/");

        // check if there are any files, if not then return error
        if ( !(new File( path )).exists() ) {
            res.setStatus( 401 );
            out.print( "Files do not exist" );
            out.close();
            return;
        }


        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        out.print('[');

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if ( i != 0 ) out.print(',');
                out.print( gson.toJson( new SingleFile( listOfFiles[i].getName() ) ) );
            } else if (listOfFiles[i].isDirectory()) {
                out.println("Directory " + listOfFiles[i].getName());
            }
        }

        out.print(']');

        out.close();
    }
}
