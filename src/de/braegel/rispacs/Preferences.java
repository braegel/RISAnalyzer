package de.braegel.rispacs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Preferences {
	private String dbuser;
	private String dbpassword;
	private String dbip;
	private String dbport;
	private String dbname;
	private String imagegeneratortable;
	private String examinationstreetable;
	
	public String getExaminationstreetable() {
		return examinationstreetable;
	}

	public void setExaminationstreetable(String examinationstreetable) {
		this.examinationstreetable = examinationstreetable;
	}

	public String getImagegeneratortable() {
		return imagegeneratortable;
	}

	public void setImagegeneratortable(String imagegeneratortable) {
		this.imagegeneratortable = imagegeneratortable;
	}

	public Preferences(String realPath) {
		InputStream in;
		try {
			in = new FileInputStream(realPath);
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
		              if(parser.getLocalName().equals("dbuser")){
		            	  this.setDbuser(parser.getElementText());
		            	  }
		              if(parser.getLocalName().equals("dbpassword")){
		            	  this.setDbpassword(parser.getElementText());
		            	  }
		              if(parser.getLocalName().equals("dbip")){
		            	  this.setDbip(parser.getElementText());
		            	  }
		              if(parser.getLocalName().equals("dbport")){
		            	  this.setDbport(parser.getElementText());
		            	  }
		              if(parser.getLocalName().equals("dbname")){
		            	  this.setDbname(parser.getElementText());
		            	  } 
		              if(parser.getLocalName().equals("Imagegeneratortable")){
		            	  this.setImagegeneratortable(parser.getElementText());
		            	  }
		              if(parser.getLocalName().equals("Examinationstreetable")){
		            	  this.setExaminationstreetable(parser.getElementText());
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
	}
	
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	public String getDbpassword() {
		return dbpassword;
	}
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	public String getDbip() {
		return dbip;
	}
	public void setDbip(String dbip) {
		this.dbip = dbip;
	}
	public String getDbport() {
		return dbport;
	}
	public void setDbport(String dbport) {
		this.dbport = dbport;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	
}
