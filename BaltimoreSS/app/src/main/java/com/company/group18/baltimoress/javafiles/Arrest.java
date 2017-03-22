package com.company.group18.baltimoress.javafiles;

/**
 * Created by Adit on 2017-03-22.
 */

public class Arrest {
    private int ArrestID;
    private int age;
    private Character sex;
    private String race;
    private Date date;
    private Time time;
    private String arrestLocation;
    private String incidentOffence;
    private String incidentLocation;
    private String charge;
    private String chargeDescription;
    private String district;
    private String post;
    private String Neighborhood;
    private Location location;

    public Arrest(int arrestID, int age, Character sex, String race, Date date, Time time, String arrestLocation, String incidentOffence, String incidentLocation, String charge, String chargeDescription, String district, String post, String neighborhood, Location location) {
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


}
