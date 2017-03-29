

public class Location implements Comparable<Location>{

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
    
	@Override
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
