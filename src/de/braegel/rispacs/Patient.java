package de.braegel.rispacs;

import java.text.*;
import java.util.Date;

public class Patient implements Comparable<Patient>{
	String lastname;
	String firstname;
	Date dateofbirth;
	String risid;
	String generator; // e.g. which his has generated this patient?
	
	public String getLastname() {
		return lastname;
	}
	public void setName(String name) {
		String splitted[] = name.split("(, |,)");
		this.lastname = splitted[0];
		this.firstname = splitted[1];
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String birth) {
		
		DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		try {
			this.dateofbirth = (Date) formatter.parse(birth);
		} catch (ParseException e) {
//			e.printStackTrace();
			System.err.println("Error parsing date '"+birth+"' "+this);
		}

	}
	public String getRisid() {
		return risid;
	}
	public void setRisid(String risid) {
		this.risid = risid.trim();
	}
	public String getGenerator() {
		return generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	
	@Override
	public int compareTo(Patient p2){
		
		if (p2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		if (p2.getDateofbirth() != null && this.getDateofbirth().compareTo(p2.getDateofbirth())!=0){
			return this.getDateofbirth().compareTo(p2.getDateofbirth());
		} else{
			String name1 = this.getLastname()+this.getFirstname();
			String name2 = p2.getLastname()+p2.getFirstname();
			return name1.compareTo(name2);
		}
	}


	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			throw new IllegalArgumentException();
		}

		if (obj instanceof Patient)
		{
			return this.getDateofbirth().getTime() == ((Patient) obj).getDateofbirth().getTime();
		}

		return false;
	}

}
