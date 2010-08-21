package de.braegel.rispacs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Databaseconnector {		
	
	public static ArrayList<Imagegenerator> getImagegenerators(Preferences preferences){
		ArrayList<Imagegenerator> imagegenerators = new ArrayList<Imagegenerator>();
		
		final String SQLSTATEMENT ="SELECT * FROM "+preferences.getImagegeneratortable();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
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
//        	        	"jdbc:sybase:Tds:"+getPreferences("dbip")+":"+getPreferences("dbport")+"/"+getPreferences("dbname"), getPreferences("dbuser"), getPreferences("dbpassword"));
	        	"jdbc:sybase:Tds:"+preferences.getDbip()+":"+preferences.getDbport()+"/"+preferences.getDbname(), preferences.getDbuser(), preferences.getDbpassword());
        }
        catch(SQLException ex){
    			System.err.println("SQLException: " + ex.getMessage());
    			System.err.println("SQLState: " + ex.getSQLState());
    			System.err.println("VendorError: " + ex.getErrorCode());
    	}

        try{
        	stmt = con.createStatement();        	
        	rs = stmt.executeQuery(SQLSTATEMENT);

        	Imagegenerator imagegenerator;
        	StringTokenizer tokenizer;



				while(rs.next()){
					if (!rs.getString("long_descr").startsWith("#")){
						imagegenerator = new Imagegenerator();
						
						tokenizer = new StringTokenizer(rs.getString("long_descr"),"\\");

					imagegenerator.setName(tokenizer.nextToken());
//					imagegenerator.setModality(tokenizer.nextToken());
//					imagegenerator.setDa(tokenizer.nextToken());
//					imagegenerator.setRequested_location(tokenizer.nextToken());
//					imagegenerator.setNy(tokenizer.nextToken());
//					imagegenerator.setGroup(tokenizer.nextToken());
//					imagegenerator.setLkst1(tokenizer.nextToken());
//					imagegenerator.setPriority(tokenizer.nextToken());
//					imagegenerator.setSearchoption(tokenizer.nextToken());
//					imagegenerator.setAet(tokenizer.nextToken());
//					imagegenerator.setMultiplier(tokenizer.nextToken());
//					imagegenerator.setRondo(tokenizer.nextToken());
//					imagegenerator.setCmovetarget(tokenizer.nextToken());
//					imagegenerator.setAetpacs(tokenizer.nextToken());
//					imagegenerator.setLocation(tokenizer.nextToken());
//					imagegenerator.setDefaultward(tokenizer.nextToken());

//						System.out.println(imagegenerator.getName());
						imagegenerators.add(imagegenerator);
					}
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
        System.out.println("Got "+imagegenerators.size()+" results in "+duration+" seconds");

		return imagegenerators;
	}
}
