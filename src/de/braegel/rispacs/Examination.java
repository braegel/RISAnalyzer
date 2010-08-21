package de.braegel.rispacs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Examination implements Comparable<Examination>{
	private Date plan_date;
	private String geraet;			// LOCALIZATION ???
	private String station;
	private String anf_text; 		// LOCALIZATION ???
	private String anf_code; 		// LOCALIZATION ???


	private String rlvf; // seems to be a collector field for clinical informations like question, lab, crea, tsh
	private String study_comment; // additional study informations, SMH uses it for reqDoctors name.
	private String dringlichkeit; 	// LOCALIZATION ???
	private String ris_ward;
	private String rep_rta;
	private int duration;
	
	
	
	public String getRep_rta() {
		return rep_rta;
	}
	public void setRep_rta(String rep_rta) {
		this.rep_rta = rep_rta;
	}
	public String getAnf_code() {
		return anf_code;
	}
	public void setAnf_code(String anfCode) {
		anf_code = anfCode;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setDuration(ArrayList<Examinationsetting> examinationsetting){
		this.duration = 30;
		
		for (Examinationsetting e : examinationsetting){
			if (this.anf_code.equals(e.getElement_code())){
				this.setDuration(e.getDuration());
				break;
			}
		}
	
	}
	
	public String getRis_ward() {
		return ris_ward;
	}
	public void setRis_ward(String risWard) {
		ris_ward = risWard;
	}
	public Date getPlan_date() {
		return plan_date;
	}
	public void setPlan_date(String planDateString) {
		DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		try {
			plan_date = (Date) formatter.parse(planDateString);
		} catch (ParseException e) {
//			e.printStackTrace();
			System.err.println("Error parsing date '"+planDateString+"' "+this);
		}
	}
	public String getGeraet() {
		return geraet;
	}
	public void setGeraet(String geraet) {
		this.geraet = geraet;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getAnf_text() {
		return anf_text;
	}
	public void setAnf_text(String anfText) {
		anf_text = anfText;
	}
	public String getRlvf() {
		return rlvf;
	}
	public void setRlvf(String rlvf) {
		this.rlvf = rlvf;
	}
	public String getStudy_comment() {
		return study_comment;
	}
	public void setStudy_comment(String studyComment) {
		study_comment = studyComment;
	}
	public String getDringlichkeit() {
		return dringlichkeit;
	}
	public void setDringlichkeit(String dringlichkeit) {
		this.dringlichkeit = dringlichkeit;
	}

	@Override
	public int compareTo(Examination e2){
		
		if (e2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		return this.getAnf_text().compareTo(e2.getAnf_text());
		
	}
	
	public int duration(ArrayList<Examinationsetting> examinationsettings){
		return 30;
	}
	
	@Override
	public String toString() 
	{ 
		if (this.rep_rta == null){
			return String.format( "OPEN: %tF %10.10s %2d min %s (%s)", getPlan_date(), getGeraet(), getDuration(), getAnf_text(), getStation());
		}
		else{
			return String.format( "DONE: %tF %10.10s %-6.6s %s (%s)", getPlan_date(), getGeraet(), getRep_rta(), getAnf_text(), getStation());
		}
		
	}
	
	// Returns the estimated sum of examinations still to do in minutes
	public static int getSumestimatedduration(String imagegenerator, ArrayList<Examination> examinations) {
		int sumestimatedduration = 0;
		for (Examination e : examinations)
		{
			if (e.getGeraet().equals(imagegenerator)){
				if (e.getRep_rta() == null){
					sumestimatedduration+=e.getDuration();
				}				
			}
		}
		return sumestimatedduration;
	}
	public static int getCountopenexaminations(String imagegenerator,
			ArrayList<Examination> examinations) {
		int counter =0;
		for (Examination e : examinations)
		{
			if (e.getGeraet().equals(imagegenerator)){
				if (e.getRep_rta() == null){
					counter++;
				}				
			}
		}		
		return counter;
	} 
}
