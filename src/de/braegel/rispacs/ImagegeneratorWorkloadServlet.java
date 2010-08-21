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
 public class ImagegeneratorWorkloadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ImagegeneratorWorkloadServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Imagegenerator> imagegenerators;
		ArrayList<Examination> examinations = new ArrayList<Examination>();
		
//		Parse parameters
		String imagegeneratorfilter = "";
		try { 
			imagegeneratorfilter = request.getParameter("imagegeneratorfilter"); 
		} 

		catch (Exception e) { 
		    e.printStackTrace(); 
		}
		
//		work		

		Preferences preferences = new Preferences(this.getServletContext().getRealPath("/WEB-INF/config.xml"));

		examinations = Databaseconnector.getExaminations(new Date(), examinations, preferences);
		
		
		
//		Response
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
	    out.println("<style type=\"text/css\" media=\"screen\">");
	    out.println("@import url(\"../style.css\");");
	    out.println("</style>");
	    out.println("<title>ImagegeneratorWorkloadServlet</title>");
	    out.println("</head>");
	    out.println("<body>");
	    	    
	    
	    if (imagegeneratorfilter == null || imagegeneratorfilter.length() == 0){
		    imagegenerators  = Databaseconnector.getImagegenerators(preferences);
	    	out.println("<h2>Overview - Open examinations</h2>");
	    	out.println("<table>");
	    	out.println("<tr><th>Imagegenerator</th><th>minutes</th><th>count</th></tr>");
	    	for (Imagegenerator imagegenerator : imagegenerators){
	    		if (Examination.getCountopenexaminations(imagegenerator.getName(),examinations)>0){
	    			out.println("<tr>");
	    			out.println("<td><a href=\"/RISAnalyzer/ImagegeneratorWorkloadServlet?imagegeneratorfilter="+imagegenerator.getName()+"\">"+imagegenerator.getName()+"</a></td>");
	    			out.println("<td>"+Examination.getSumestimatedduration(imagegenerator.getName(),examinations)+"</td>");
	    			out.println("<td>"+Examination.getCountopenexaminations(imagegenerator.getName(),examinations)+"</td>");
	    			out.println("</tr>");
	    		}
	    	}
		    out.println("</table>");
		}
	    else{
	    	out.println("<h2>Details of Imagegenerator "+imagegeneratorfilter+"</h2>");
	    	out.println("<table>");
	    	out.println("<tr><th>minutes</th><th>Examination</th><th>Station</th></tr>");
	    	for (Examination examination : examinations){
	    		if (examination.getGeraet().equalsIgnoreCase(imagegeneratorfilter)){
					out.println("<TR>");
					out.println("<TD>"+examination.getDuration()+"</TD>");
					out.println("<TD>"+examination.getAnf_text()+"</TD>");
					out.println("<TD>"+examination.getStation()+"</TD>");
					out.println("<TD>"+examination.getRlvf()+"</TD>");
					out.println("</TR>");
	    		}
	    	}
	    	out.println("</table>");
	    	out.println("<p><a href=\"/RISAnalyzer/ImagegeneratorWorkloadServlet\">Back to overview</a></p>");
	    }
	    

	    out.println("</body></html>");
	    out.close();
	    }  	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}