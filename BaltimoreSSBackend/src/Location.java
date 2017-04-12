/**
 * Class that estabilishes location
 */
public class Location implements Comparable<Location>{

    private double xcrd;
    private double ycrd;

    public Location(){}

    /**
     * Constructor for location class
     * @param xcrd the x-coordinate for the desired location
     * @param ycrd the y-coordinate for the desired location
     */
    public Location(double xcrd, double ycrd) {
        this.xcrd = xcrd;
        this.ycrd = ycrd;
    }

    /**
     * Getter for x-coordinate
     * @return x-coordinate of location
     */
    public double getXcrd() {
        return xcrd;
    }

    /**
     * Setter for x-coordination
     * @param xcrd the x-coordinate we are setting for location
     */
    public void setXcrd(double xcrd) {
        this.xcrd = xcrd;
    }

    /**
     * Getter for y-coordinate
     * @return y-coordinate of location
     */
    public double getYcrd() {
        return ycrd;
    }

    /**
     * Setter for y-coordination
     * @param ycrd the y-coordinate we are setting for location
     */
    public void setYcrd(double ycrd) {
        this.ycrd = ycrd;
    }

    /**
     * Checking whether location is in the specified region
     * @param other the location we are checking
     * @param threshold the leeway we are given the location
     * @return true if location is with in the region, else false
     */
    public boolean withinRegion(Location other, double threshold){
    	
    	if (this.xcrd - threshold <= other.getXcrd() && other.getXcrd() <= this.xcrd + threshold){
    		if (this.ycrd - threshold <= other.getYcrd() && other.getYcrd() <= this.ycrd + threshold){
        		return true;
        	} 
    	} 
    	return false;
    }
    
    @Override
    /**
     * Converts output format to desired string output
     */
    public String toString() {
        return "{xcrd=" + xcrd +", ycrd=" + ycrd +'}';
    }
    
	@Override
    /**
     * Compares locations coordinates
     * @param o the location we are comparing to
     * @return 1 if locations coordinates are greater, -1 if less, else 0
     */
	public int compareTo(Location o) {
		if ( this.xcrd < o.xcrd ){
			return -1;
		}
		else if( this.xcrd > o.xcrd){
			return 1;
		}
		else {
			if ( this.ycrd < o.ycrd ){
				return -1;
			}
			if( this.ycrd > o.ycrd){
				return 1;
			}
			return 0;		
		}
		
	}
}
