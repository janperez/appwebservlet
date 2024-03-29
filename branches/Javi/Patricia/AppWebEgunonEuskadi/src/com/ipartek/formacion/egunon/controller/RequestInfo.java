package com.ipartek.formacion.egunon.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestInfo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        	
        	out.println("<head>");
        		out.println("<title>Request Information Example</title>");
        		out.print("<link href='css/style.css' rel='stylesheet' type='text/css' >");
        	out.println("</head>");
        	out.println("<body>");
        	out.print("<div>");
        	request.getRequestDispatcher("/cabecera.jsp").include(request, response);
        		out.println("<h3>Request Information Example</h3>");
        		out.println("Method: " + request.getMethod()+"<br>");
        		out.println("Request URI: " + request.getRequestURI()+"<br>");
        		out.println("Protocol: " + request.getProtocol()+"<br>");
        		out.println("PathInfo: " + request.getPathInfo()+"<br>");
        		out.println("Remote Address: " + request.getRemoteAddr()+"<br>");
        	out.print("</div><br>");
        	request.getRequestDispatcher("/footer.jsp").include(request, response);
        	out.println("</body>");
        	
        out.println("</html>");
    }

    /**
     * We are going to perform the same operations for POST requests
     * as for GET methods, so this method just sends the request to
     * the doGet method.
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}


