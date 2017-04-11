package com.company.group18.baltimoress;

/**
 * represents a date (dd/mm/yyyy)
 */
public class Date {
    private int day;
    private int month;
    private int year;

	/**
	 * initializes a new instance of Date
	 *
	 * @param day day of the month as integer
	 * @param month month of the year (01 - 12)
	 * @param year year as integer
	 */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;

    }

	/**
	 * returns string representation of the date
	 *
	 * @return string representation of the date
	 */
	@Override
	public String toString() {
		return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
	}

	/**
	 * accessor for year
	 * @return year of date
	 */
	public int getYear() {
		return year;
	}

	/**
	 * sets new year
	 * @param year year to replace current
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * accessor for month
	 *
	 * @return month of date
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * sets new month
	 *
	 * @param month month to replace current
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * accessor for day
	 *
	 * @return day of date
	 */
	public int getDay() {
		return day;
	}

	/**
	 * sets new day
	 *
	 * @param day day to replace current
	 */
	public void setDay(int day) {
		this.day = day;
	}


}
