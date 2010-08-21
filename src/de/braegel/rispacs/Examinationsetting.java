package de.braegel.rispacs;

import java.util.*;

public class Examinationsetting {
	private int element_number;	
	private int prev_element;	
	private String element_text;	
	private String element_code;	
	private int value_element;	
	private int element_order;	
	private int element_status;	
	private String value_element_1; // e.g. Duration of examination
	private String value_element_2;
	private String value_element_3;
	private String value_element_4; // longdescription
	private String value_element_5;
	private String value_element_6;
	private String value_element_7;
	private String value_element_8;
	private String modality;
	private String group;
	private String waitingzone;
	private int duration; // duration in minutes
	
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getWaitingzone() {
		return waitingzone;
	}
	public void setWaitingzone(String waitingzone) {
		this.waitingzone = waitingzone;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getElement_number() {
		return element_number;
	}
	public void setElement_number(int elementNumber) {
		element_number = elementNumber;
	}
	public int getPrev_element() {
		return prev_element;
	}
	public void setPrev_element(int prevElement) {
		prev_element = prevElement;
	}
	public String getElement_text() {
		return element_text;
	}
	public void setElement_text(String elementText) {
		element_text = elementText;
	}
	public String getElement_code() {
		return element_code;
	}
	public void setElement_code(String elementCode) {
		element_code = elementCode;
	}
	public int getValue_element() {
		return value_element;
	}
	public void setValue_element(int valueElement) {
		value_element = valueElement;
	}
	public int getElement_order() {
		return element_order;
	}
	public void setElement_order(int elementOrder) {
		element_order = elementOrder;
	}
	public int getElement_status() {
		return element_status;
	}
	public void setElement_status(int elementStatus) {
		element_status = elementStatus;
	}
	public String getValue_element_1() {
		return value_element_1;
	}
	public void setValue_element_1(String valueElement_1) {
		StringTokenizer st;
		StringTokenizer datehhmm = null;
//		int hours = 0;
//		int minutes = 0;
		
		value_element_1 = valueElement_1;
		st = new StringTokenizer( value_element_1, "\\");
		if (st.hasMoreTokens()){modality=st.nextToken();} 
		else {System.err.println("Error parsing modality in \'"+value_element_1+"\': "+this);}
		
		if (st.hasMoreTokens()){group=st.nextToken();}
		else {System.err.println("Error parsing group in \'"+value_element_1+"\': "+this);}
		
		if (st.hasMoreTokens()){datehhmm = new StringTokenizer(st.nextToken(), ":");
		
			if (datehhmm.hasMoreTokens()){
			try{duration = Integer.parseInt(datehhmm.nextToken()) *60;}
			catch (NumberFormatException e){System.err.println("Error parsing \'"+datehhmm+"' "+this);}}
			else {System.err.println("Error parsing hours in "+datehhmm);}
			
			if (datehhmm.hasMoreTokens()){
			try{duration += Integer.parseInt(datehhmm.nextToken());}
			catch (NumberFormatException e){System.err.println("Error parsing \'"+datehhmm+"' "+this);}}
			else {System.err.println("Error parsing minutes in "+datehhmm);}
		
		}
		else {System.err.println("Error parsing datehhmm in \'"+value_element_1+"\': "+this);}
		
		if (st.hasMoreTokens()){waitingzone=st.nextToken();}
		else {System.err.println("Error parsing waitingzone in \'"+value_element_1+"\': "+this);}
		
	
		
	}
	public String getValue_element_2() {
		return value_element_2;
	}
	public void setValue_element_2(String valueElement_2) {
		value_element_2 = valueElement_2;
	}
	public String getValue_element_3() {
		return value_element_3;
	}
	public void setValue_element_3(String valueElement_3) {
		value_element_3 = valueElement_3;
	}
	public String getValue_element_4() {
		return value_element_4;
	}
	public void setValue_element_4(String valueElement_4) {
		value_element_4 = valueElement_4;
	}
	public String getValue_element_5() {
		return value_element_5;
	}
	public void setValue_element_5(String valueElement_5) {
		value_element_5 = valueElement_5;
	}
	public String getValue_element_6() {
		return value_element_6;
	}
	public void setValue_element_6(String valueElement_6) {
		value_element_6 = valueElement_6;
	}
	public String getValue_element_7() {
		return value_element_7;
	}
	public void setValue_element_7(String valueElement_7) {
		value_element_7 = valueElement_7;
	}
	public String getValue_element_8() {
		return value_element_8;
	}
	public void setValue_element_8(String valueElement_8) {
		value_element_8 = valueElement_8;
	}
	@Override
	
	public String toString() 
	{ 
		return String.format( "%s\t%s\t%d h\t%s\t%s",getElement_code(),getValue_element_4(),duration,getValue_element_5(),getValue_element_7()); 
	} 
}
