package com.company.group18.baltimoress;

public class Arrest implements Comparable<Arrest>{
    private int ArrestID;
    private int age;
    private String sex;
    private String race;
    private Date date;
    
    private Time time;
    private String arrestLocation;
    private String incidentOffence;
    private String incidentLocation;
    private String charge;
    
    private String chargeDescription;
    private String district;
    private int post;
    private String Neighborhood;
    private Location location;
	
    public Arrest(int arrestID, int age, String sex, String race, Date date,
			Time time, String arrestLocation, String incidentOffence,
			String incidentLocation, String charge, String chargeDescription,
			String district, int post, String neighborhood, Location location) {
		super();
		ArrestID = arrestID;
		this.age = age;
		this.sex = sex;
		this.race = race;
		this.date = date;
		this.time = time;
		this.arrestLocation = arrestLocation;
		this.incidentOffence = incidentOffence;
		this.incidentLocation = incidentLocation;
		this.charge = charge;
		this.chargeDescription = chargeDescription;
		this.district = district;
		this.post = post;
		Neighborhood = neighborhood;
		this.location = location;
	}

	@Override
	public String toString() {
		return "ArrestID=" + ArrestID +
				", age=" + age +
				", sex='" + sex + '\'' +
				"\n, race='" + race + '\'' +
				", date=" + date +
				", time=" + time +
				", arrestLocation='" + arrestLocation + '\'' +
				", incidentOffence='" + incidentOffence + '\'' +
				", incidentLocation='" + incidentLocation + '\'' +
				", charge='" + charge + '\'' +
				", chargeDescription='" + chargeDescription + '\'' +
				", district='" + district + '\'' +
				", post=" + post +
				", Neighborhood='" + Neighborhood + '\'' +
				", location=" + location ;
	}
	//	@Override
//	public String toString() {
//		String s = String.format("%-10s \n%-3s \n%-10s \n%-35s \n%-35s %-35s %-35s %-35s %-35s %-35s %-100s %-35s %-35s %-40s %-40s",
//				ArrestID,
//				age,
//				sex,
//				race,
//				date,
//				time,
//				arrestLocation,
//				incidentOffence,
//				incidentLocation,
//				charge,
//				chargeDescription,
//				district,
//				post,Neighborhood,location);
//		return s;
//	}

	@Override
	public int compareTo(Arrest o) {
		if(this.location.compareTo(o.location) == 1){
			return 1;
		}
		else if(this.location.compareTo(o.location) == -1){
			return -1;
		}		
		return 0;
	}

	public int getArrestID() {
		return ArrestID;
	}

	public int getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public String getRace() {
		return race;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getArrestLocation() {
		return arrestLocation;
	}

	public String getIncidentOffence() {
		return incidentOffence;
	}

	public String getIncidentLocation() {
		return incidentLocation;
	}

	public String getCharge() {
		return charge;
	}

	public String getChargeDescription() {
		return chargeDescription;
	}

	public String getDistrict() {
		return district;
	}

	public int getPost() {
		return post;
	}

	public String getNeighborhood() {
		return Neighborhood;
	}

	public Location getLocation() {
		return location;
	}

}
