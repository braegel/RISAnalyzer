package de.braegel.rispacs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class Databaseconnector {
	
	private static ArrayList<Examinationsetting> getExaminationsettingtree(Preferences preferences){
		final String SQLSTATEMENT =	"SELECT * FROM "+preferences.getExaminationstreetable()+", "+preferences.getExaminationstreetable()+"_value WHERE "+preferences.getExaminationstreetable()+".element_number = "+preferences.getExaminationstreetable()+"_value.element_number";

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Examinationsetting examinationsetting = null;
		ArrayList<Examinationsetting> examinationsettings= new ArrayList<Examinationsetting>();

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
//        	rs = stmt.executeQuery("SELECT * FROM D_Baum3_V1, D_Baum3_V1_value WHERE D_Baum3_V1.element_number = D_Baum3_V1_value.element_number");

				while(rs.next()){
					examinationsetting = new Examinationsetting();
					examinationsetting.setElement_number(rs.getInt("element_number"));
					examinationsetting.setPrev_element(rs.getInt("prev_element"));
					examinationsetting.setElement_text(rs.getString("element_text"));
					examinationsetting.setElement_code(rs.getString("element_code"));
					examinationsetting.setValue_element(rs.getInt("value_element"));
					examinationsetting.setElement_order(rs.getInt("element_order"));
					examinationsetting.setElement_status(rs.getInt("element_status"));
					examinationsetting.setValue_element_1(rs.getString("value_element_1"));
					examinationsetting.setValue_element_2(rs.getString("value_element_2"));
					examinationsetting.setValue_element_3(rs.getString("value_element_3"));
					examinationsetting.setValue_element_4(rs.getString("value_element_4"));
					examinationsetting.setValue_element_5(rs.getString("value_element_5"));
					examinationsetting.setValue_element_6(rs.getString("value_element_6"));
					examinationsetting.setValue_element_7(rs.getString("value_element_7"));
					examinationsetting.setValue_element_8(rs.getString("value_element_8"));
//					System.out.println(examinationsetting);
					examinationsettings.add(examinationsetting);
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
        System.out.println("Done in "+duration+" seconds");

		return (examinationsettings);
	}

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

	public static ArrayList<Examination> getExaminations(Date date,
			ArrayList<Examination> examinations, Preferences preferences) {
	
		SimpleDateFormat df = new SimpleDateFormat( "yyyy.MM.dd" );
		final String SQLSTATEMENT = "SELECT * FROM examination,examination2 WHERE examination.fld_uid = examination2.fld_uid  AND examination.plan_date= \'"+df.format(date)+"\'";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Examinationsetting> examinationsettings = new ArrayList<Examinationsetting>();
		Date timestamp1,timestamp2;
    	int duration;	

		examinationsettings=getExaminationsettingtree(preferences);
    	
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
      	        	"jdbc:sybase:Tds:"+preferences.getDbip()+":"+preferences.getDbport()+"/"+preferences.getDbname(), preferences.getDbuser(), preferences.getDbpassword());
        }
        catch(SQLException ex){
    			System.err.println("SQLException: " + ex.getMessage());
    			System.err.println("SQLState: " + ex.getSQLState());
    			System.err.println("VendorError: " + ex.getErrorCode());
    	}

        try{
        	stmt = con.createStatement();
//        	rs = stmt.executeQuery("SELECT examination.plan_date, examination2.geraet, examination2.anf_text, examination2.station, examination2.rlvf, examination.study_comment, examination2.dringlichkeit FROM examination,examination2 WHERE examination.fld_uid = examination2.fld_uid  AND (examination.plan_date = \'20\'+convert(CHAR(10),getdate(),2) OR examination.plan_date = \'20\'+convert(CHAR(10),dateadd(dd,1,getdate()),2))");
//        	rs = stmt.executeQuery("SELECT * FROM examination,examination2 WHERE examination.fld_uid = examination2.fld_uid  AND (examination.plan_date = \'20\'+convert(CHAR(10),getdate(),2) OR examination.plan_date = \'20\'+convert(CHAR(10),dateadd(dd,1,getdate()),2))");
        	
        	rs = stmt.executeQuery(SQLSTATEMENT);

        	Examination exam;
				while(rs.next()){
					exam = new Examination();
					exam.setPlan_date(rs.getString("plan_date"));
					exam.setGeraet(rs.getString("geraet"));
					exam.setAnf_text(rs.getString("anf_text"));
					exam.setAnf_code(rs.getString("anf_code"));
					exam.setStation(rs.getString("station"));
					exam.setRlvf(rs.getString("rlvf"));
					exam.setStudy_comment(rs.getString("study_comment"));
					exam.setDringlichkeit(rs.getString("dringlichkeit"));
					exam.setRis_ward(rs.getString("ris_ward"));
					exam.setRep_rta(rs.getString("rep_rta"));
//					System.out.println(">"+rs.getString("ris_ward")+"<");
//					System.out.println(exam);
					exam.setDuration(examinationsettings);

					examinations.add(exam);
				}
								
				Collections.sort(examinations);
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
        System.out.println("Done in "+duration+" seconds");

		return examinations;
	}

}
