public class Person {
//instances of this class represent each individual person on the tenting team
	private int numFilled; //number of intervals filled
	private int numLeft; //number of intervals left 
	private int numAvail; //number of available slots beyond current slot (future availabilities)
	private int currentConsec; //number of consecutive slots at current time
//	private int futureConsec; //number of consecutive slots ahead
	private int ID; //unique ID to identify people (1-numPeople)
	
	
	static int maxConsec = 10; //5 hrs
	static int minConsec = 2;	//1 hr
	
	//private int[] futureConsec;
	//private int[] futureAvail; //at each index would be the future availability beyond that index (inclusive)
	
	public Person(int id, int avail, int left) {
		ID = id;
		numAvail = avail;
		numFilled = 0;
		numLeft = left;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getAvail() {
		return numAvail;
	}
	
	public void setAvail(int avail) {
		numAvail = avail;
	}
	
	public int getFilled() {
		return numFilled;
	}
	
	public void setFilled(int filled) {
		numFilled = filled;
	}
	
	public int getLeft() {
		return numLeft;
	}
	
	public void setLeft(int left) {
		numLeft = left;
	}
	
	public int getCurrConsec() {
		return currentConsec;
	}
	
	public void setCurrConsec(int curr) {
		currentConsec = curr;;
	}
}
