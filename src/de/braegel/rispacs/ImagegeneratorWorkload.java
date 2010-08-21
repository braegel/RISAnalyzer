package de.braegel.rispacs;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: ModalityWorkload
 *
 */
 public class ImagegeneratorWorkload extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ImagegeneratorWorkload() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Response
		
		Preferences preferences = new Preferences(this.getServletContext().getRealPath("/WEB-INF/config.xml"));
		
		ArrayList<Imagegenerator> imagegenerators;
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head><title>RISPatientDoubletServlet</title></head>");
	    out.println("<body>");
	    	    
	    imagegenerators  = Databaseconnector.getImagegenerators(preferences);
	    
	    out.println("<table>");
	    
	    for (Imagegenerator imagegenerator : imagegenerators){
	    	out.println("<tr>");
	    	out.println("<td>"+imagegenerator.getName()+"</td>");
	    	out.println("</tr>");	    	
	    }
	    
	    out.println("</table>");
	    out.println("</body></html>");
	    out.close();
	    }  	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}