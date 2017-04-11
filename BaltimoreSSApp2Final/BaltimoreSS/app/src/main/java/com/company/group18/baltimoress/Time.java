package com.company.group18.baltimoress;

/**
 * Class for time
 */
public class Time {
    private int hour;
    private int minute;

    /**
     * Constructor for class
     * @param hour hour of the time
     * @param minute minute of the time
     */
    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Getter for hour of time
     * @return hour of the time
     */
    public int getHour() {
        return hour;
    }

    /**
     * Setter for the hour of time
     * @param hour the hour of the time
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Getter for minute of time
     * @return minute of the time
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Setter for the minute of time
     * @param minute the hour of the time
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    /**
     * Converts the out to a string format
     */
    public String toString() {
        return "[" +
                "hour=" + hour +
                ", minute=" + minute +
                ']';
    }
}
