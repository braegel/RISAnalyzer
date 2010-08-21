package de.braegel.rispacs;

import de.braegel.rispacs.Patient;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import javax.xml.stream.*;

/**
 * This Servlets shows all RIS patients suspicious to be doublets
 */

 public class RISPatientDoubletServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
	int connections;

    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RISPatientDoubletServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		Parse parameters
		
		String responsestyle = "";
		try 
		{ 
		    responsestyle = request.getParameter("style"); 
		} 

		catch (Exception e) 
		{ 
		    e.printStackTrace(); 
		}
		
		Boolean onlyhisdoublet = false;
		try 
		{ 
		    if (request.getParameter("onlyhisdoublet") != null && request.getParameter("onlyhisdoublet").equalsIgnoreCase("true")){
		    	onlyhisdoublet = true;
		    }
		} 

		catch (Exception e) 
		{ 
		    e.printStackTrace(); 
		}		
		
		
//		Work		
		
		ArrayList<Patient> patients = getrispatients();
		ArrayList<Patient> doublets;	
		if (!onlyhisdoublet){
			doublets = findpatientdoublets(patients);
		}
		else{
			doublets = findpseudopatientdoublets(patients);
		}
		
		
//		Response
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head><title>RISPatientDoubletServlet</title></head>");
	    out.println("<body>");
	    
	    if (responsestyle != null && responsestyle.contentEquals("autoit")){
	    	createrisidonlylistofpatients(doublets,out);
	    }
	    else{
	    	createhtmltableofpatients(doublets,out);
	    }
	    	    
	    out.println("</body></html>");
	    out.close();
	}  	
	
	private void createrisidonlylistofpatients(ArrayList<Patient> patients,
		PrintWriter out) {
		int i;
		out.println("<p>");
		for (i=0;i<patients.size();i=i+2){
			out.println(patients.get(i).getRisid()+","+patients.get(i+1).getRisid()+"<br />");	
		}
		out.println("<p>");
	}

	private ArrayList<Patient> findpseudopatientdoublets(ArrayList<Patient> patients) {
		ArrayList<Patient> doublets = new ArrayList<Patient>();

		Date timestamp1,timestamp2;
    	int duration;	
    	
		timestamp1 = new Date();
		System.out.println("Sorting patients");
		Collections.sort(patients);
    	timestamp2 = new Date();
        duration = (int)(timestamp2.getTime() - timestamp1.getTime())/1000;
        System.out.println("Sorting patients took "+duration+" seconds");
        
		Patient p2 = new Patient();
		for (Patient p : patients)
		{
			if (p.compareTo(p2) == 0 && p.getRisid().compareTo(p2.getRisid())!=0 && p.getGenerator().compareTo(p2.getGenerator()) == 0){
				doublets.add(p);
				doublets.add(p2);
			}
			p2 = p;			
		}

		
		return doublets;
	}
	
	private ArrayList<Patient> findpatientdoublets(ArrayList<Patient> patients) {
		ArrayList<Patient> doublets = new ArrayList<Patient>();

		Date timestamp1,timestamp2;
    	int duration;	
    	
		timestamp1 = new Date();
		System.out.println("Sorting patients");
		Collections.sort(patients);
    	timestamp2 = new Date();
        duration = (int)(timestamp2.getTime() - timestamp1.getTime())/1000;
        System.out.println("Sorting patients took "+duration+" seconds");
        
		Patient p2 = new Patient();
		for (Patient p : patients)
		{
			if (p.compareTo(p2) == 0 && p.getRisid().compareTo(p2.getRisid())!=0){
				doublets.add(p);
				doublets.add(p2);
			}
			p2 = p;			
		}

		
		return doublets;
	}

	private void createhtmltableofpatients(ArrayList<Patient> patients,
			PrintWriter out) {
		
		out.println("<p>"+patients.size()+" patients</p>");
		
		out.println("<table>");
		for (Patient p:patients){
			out.println("<tr>");

			out.println("<td>");
			out.println(p.getLastname());
			out.println("</td>");

			out.println("<td>");
			out.println(p.getFirstname());
			out.println("</td>");

			out.println("<td>");
			out.println(String.format("%tF",p.getDateofbirth()));
			out.println("</td>");
			
			out.println("<td>");
			out.println(p.getRisid());
			out.println("</td>");

			out.println("<td>");
			out.println(p.getGenerator());
			out.println("</td>");
			
			out.println("</tr>");
		}
		out.println("</table>");
		
	}
	
	
	@SuppressWarnings("unchecked")
	public String getPreferences(String pref){
		String pref_value = null ;
		
		InputStream in;
		try {
			in = new FileInputStream(this.getServletContext().getRealPath("/WEB-INF/config.xml"));
	    	XMLInputFactory factory = XMLInputFactory.newInstance(); 
	    	XMLStreamReader parser  = factory.createXMLStreamReader( in );
	        while ( parser.hasNext() ) 
	        { 
//	          System.out.println( "Event: " + parser.getEventType() ); 
	         
	          switch ( parser.getEventType() ) 
	          { 

	            case XMLStreamConstants.END_DOCUMENT: 
//	              System.out.println( "END_DOCUMENT: " ); 
	              parser.close(); 
	              break; 
	        	         
	            case XMLStreamConstants.START_ELEMENT: 
//	              spacer.append( "  " ); 
//	              System.out.println( spacer + "START_ELEMENT: " + parser.getLocalName() );
	              if(parser.getLocalName().equals(pref)){
	            	  pref_value = parser.getElementText();
	            	  }	            	  	
	              break;      	        
	            default: 
	              break; 
	          } 
	          parser.next(); 
	        }

		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pref_value;
	}


	private ArrayList<Patient> getrispatients() {
//		The following is suitable for the Syngo Workflow Installation V35 in Düren, Germany - Administrator is bernd@braegelmann.de
		
//		final String SQLSTATEMENT ="SELECT pat_name, birth_date,ris_pat_id,his_code FROM patient,patient_id WHERE patient.pat_ckey=patient_id.pat_ckey AND pat_name LIKE \'Brägelmann%\'";
		final String SQLSTATEMENT ="SELECT pat_name, birth_date,ris_pat_id,his_code FROM patient,patient_id WHERE patient.pat_ckey=patient_id.pat_ckey";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Patient> patients = new ArrayList<Patient>();
		Date timestamp1,timestamp2;
    	int duration;	
    	
		timestamp1 = new Date();
		System.out.println(SQLSTATEMENT);
		try
		{
		  Class.forName( "com.sybase.jdbc3.jdbc.SybDriver" );
		}
		catch ( ClassNotFoundException e )
		{
		  // OO! Treiber konnte nicht geladen werden.
		  e.printStackTrace();
		}

        try
        {
        	 con = DriverManager.getConnection(
        	        	"jdbc:sybase:Tds:"+getPreferences("dbip")+":"+getPreferences("dbport")+"/"+getPreferences("dbname"), getPreferences("dbuser"), getPreferences("dbpassword"));
        }
        catch(SQLException ex){
    			System.err.println("SQLException: " + ex.getMessage());
    			System.err.println("SQLState: " + ex.getSQLState());
    			System.err.println("VendorError: " + ex.getErrorCode());
    	}

        try{
        	stmt = con.createStatement();        	
        	rs = stmt.executeQuery(SQLSTATEMENT);

        	Patient patient;
				while(rs.next()){
					patient = new Patient();
					patient.setName(rs.getString("pat_name"));
					patient.setDateofbirth(rs.getString("birth_date"));
					patient.setRisid(rs.getString("ris_pat_id"));
					patient.setGenerator(rs.getString("his_code"));
					patients.add(patient);
				}
        }
        catch(SQLException ex){
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}			
        finally{
			if (rs != null) {
				try {
					rs.close();
				}
				catch(SQLException sqlEx) { }
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) { }
				stmt = null;
			}
        }
    	timestamp2 = new Date();
        duration = (int)(timestamp2.getTime() - timestamp1.getTime())/1000;
        System.out.println("Got "+patients.size()+" results in "+duration+" seconds");
		return patients;
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}