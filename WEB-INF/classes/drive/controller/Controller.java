package drive.controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

@MultipartConfig
public class Controller extends HttpServlet {

    private void work( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {
        String servlet = req.getParameter( "action" );
        req.getRequestDispatcher(servlet).forward(req, res);
    }

    public void doPost( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {
        work( req, res );
    }

    public void doGet( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {
        work( req, res );
    }
}
