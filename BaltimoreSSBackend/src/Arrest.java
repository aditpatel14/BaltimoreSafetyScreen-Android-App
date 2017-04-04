/**
 * Created by Adit on 2017-03-22.
 */
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
		String s = String.format("%-35s %-35s %-35s %-35s %-35s %-35s %-35s %-35s %-35s %-35s %-100s %-35s %-35s %-40s %-40s",
				ArrestID,
				age,
				sex,
				race,
				date,
				time,
				arrestLocation,
				incidentOffence,
				incidentLocation,
				charge,
				chargeDescription,
				district,
				post,Neighborhood,location);
		return s;
	}
	
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

	public void setArrestID(int arrestID) {
		ArrestID = arrestID;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public void setArrestLocation(String arrestLocation) {
		this.arrestLocation = arrestLocation;
	}

	public String getIncidentOffence() {
		return incidentOffence;
	}

	public void setIncidentOffence(String incidentOffence) {
		this.incidentOffence = incidentOffence;
	}

	public String getIncidentLocation() {
		return incidentLocation;
	}

	public void setIncidentLocation(String incidentLocation) {
		this.incidentLocation = incidentLocation;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getChargeDescription() {
		return chargeDescription;
	}

	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public String getNeighborhood() {
		return Neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		Neighborhood = neighborhood;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
