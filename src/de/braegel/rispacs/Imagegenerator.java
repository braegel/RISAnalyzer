package de.braegel.rispacs;

public class Imagegenerator {
//	#<GerŠtename>\<ModalitŠt>\[D|A]\<requested_location>\[n|y]\<GerŠtegruppe,..>\<Lkst1,..>\[<PrioritŠt,..>]\[<Suchoption,...>]\<AET>\<Faktor>\Rondo[n|y]\<AET C-MOVE Ziel>\<AET PACS>\<Location>\<Defaultmandant>
	private String name;
	private String modality;
	private String da;
	private String requested_location;
	private String ny;
	private String group;
	private String lkst1;
	private String priority;
	private String searchoption;
	private String aet;
	private String multiplier;
	private String rondo;
	private String cmovetarget;
	private String aetpacs;
	private String location;
	private String defaultward;
	
//	public Imagegenerator(String[] split) {
//		System.out.println(split.length);
//		System.out.println(split[0]);
//		
////		this.setName(split[0]);
////		this.setModality(split[1]);
////		this.setDa(split[2]);
////		this.setRequested_location(split[3]);
////		this.setNy(split[4]);
////		this.setGroup(split[5]);
////		this.setLkst1(split[6]);
////		this.setPriority(split[7]);
////		this.setSearchoption(split[8]);
////		this.setAet(split[9]);
////		this.setMultiplier(split[10]);
////		this.setRondo(split[11]);
////		this.setCmovetarget(split[12]);
////		this.setAetpacs(split[13]);
////		this.setLocation(split[14]);
////		this.setDefaultward(split[15]);
//	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public String getDa() {
		return da;
	}
	public void setDa(String da) {
		this.da = da;
	}
	public String getRequested_location() {
		return requested_location;
	}
	public void setRequested_location(String requested_location) {
		this.requested_location = requested_location;
	}
	public String getNy() {
		return ny;
	}
	public void setNy(String ny) {
		this.ny = ny;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getLkst1() {
		return lkst1;
	}
	public void setLkst1(String lkst1) {
		this.lkst1 = lkst1;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSearchoption() {
		return searchoption;
	}
	public void setSearchoption(String searchoption) {
		this.searchoption = searchoption;
	}
	public String getAet() {
		return aet;
	}
	public void setAet(String aet) {
		this.aet = aet;
	}
	public String getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}
	public String getRondo() {
		return rondo;
	}
	public void setRondo(String rondo) {
		this.rondo = rondo;
	}
	public String getCmovetarget() {
		return cmovetarget;
	}
	public void setCmovetarget(String cmovetarget) {
		this.cmovetarget = cmovetarget;
	}
	public String getAetpacs() {
		return aetpacs;
	}
	public void setAetpacs(String aetpacs) {
		this.aetpacs = aetpacs;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDefaultward() {
		return defaultward;
	}
	public void setDefaultward(String defaultward) {
		this.defaultward = defaultward;
	}

}
