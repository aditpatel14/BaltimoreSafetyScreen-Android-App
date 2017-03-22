package com.company.group18.baltimoress.javafiles;

/**
 * Created by Adit on 2017-03-22.
 */

public class Location {

    private double xcrd;
    private double ycrd;

    public Location(){

    }
    public Location(double xcrd, double ycrd) {
        this.xcrd = xcrd;
        this.ycrd = ycrd;
    }

    public double getXcrd() {
        return xcrd;
    }

    public void setXcrd(double xcrd) {
        this.xcrd = xcrd;
    }

    public double getYcrd() {
        return ycrd;
    }

    public void setYcrd(double ycrd) {
        this.ycrd = ycrd;
    }

    @Override
    public String toString() {
        return "{xcrd=" + xcrd +", ycrd=" + ycrd +'}';
    }
}
