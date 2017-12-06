import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;

public class Schedule {
	
	private int numPeople;
	private int numIntervals; //to be calculated for how long tenting is (dates inputed)
	private int peoplePerInterval;
	private int idealNumIntervals;
	
	private int[] availArray; //i-th index for i-th person - contains # of avail intervals
	
	
	ArrayList<Person> people;
	PriorityQueue<Person> rankings; //will keep all people in order so that bottom ranked is assigned intervals first
	Comparator<Person> comp = new PersonComparator();
	
	AvailabilitySchedule availSched;
	
	private int[][] totalAvailability;
	private int[][] schedFinal;
	
	//WILL NEED TO ADD NUM/NIGHT AND NUM/DAY; ideal day intervals / ideal nights
	
	public Schedule(AvailabilitySchedule avail) {
		numPeople = avail.numPeople;
		numIntervals = avail.numIntervals;
		peoplePerInterval = avail.peoplePerInterval;
		idealNumIntervals = numIntervals*peoplePerInterval/numPeople;
		availSched = avail;
		
		rankings = new PriorityQueue<Person>(numPeople, comp);
		people = new ArrayList<Person>();

		
		initiate();
		
	}
	
	
	//aggregates individual schedules into totalAvailability matrix
	public void initiate() {
		schedFinal = new int [numPeople][numIntervals];
		totalAvailability =  availSched.getAvailSched();
		availArray = calcAvailability(totalAvailability);
		
		for (int x=0; x<numPeople; x++) {
			Person p = new Person(x, availArray[x], idealNumIntervals);
			people.add(p);
			rankings.add(p);
		}
	}
	
	//calcs availability for each person and stores in i-th index of array
	public int[] calcAvailability(int[][] matrix) {
		int [] ret = new int[numPeople];
		int avails;
		int counter = 0;
		for (int x=0; x<numPeople; x++) {
			avails = 0;
			for (int i=0; i<matrix[0].length; i++) {
				if (matrix[x][i] == 1) avails += 1;
			}
			ret[counter] = avails;
			counter += 1;
		}
		return ret;
	}
	
	//creates final schedule in int[][] sched
	public void fillIntervals() {
		//TODO
		for (int x=0; x<numIntervals; x++) {
			int counter = 0;
			for (int i=0; i<numPeople; i++) {
				Person p = rankings.poll();
				if (counter<peoplePerInterval) {
					if (totalAvailability[p.getID()][x] == 1) {
						schedFinal[p.getID()][x] = 1;
						p.setCurrConsec(p.getCurrConsec() + 1);
						p.setFilled(p.getFilled() + 1);
						p.setLeft(p.getLeft() - 1);
						counter += 1;
					}
					else {
						p.setCurrConsec(0);
					}
				}
				else {
					p.setCurrConsec(0);
				}
			}
			if (counter < peoplePerInterval) {
				System.out.println("NEED " + (peoplePerInterval-counter) + " AT INTERVAL NUMBER " + (x+1));
			}
			
			for (int k=0; k<people.size(); k++) {
				rankings.add(people.get(k));
			}
		}
		
		
		//assign until reaching minConsec and then pull from PriorityQueue
		//when assigning intervals, update currentConsec for person by setCurrentConsec
	}
	
//	//checks if the person can fill minConsec intervals
//	public boolean canFillMinConsec(Person p) {
//		return false;
//	}
	
	//calc the number of consecutive intervals beyond the current index (INCLUSIVE)
	public int calcConsec(Person p, int current) {
		int consecs = 0;
		int id = p.getID();
		for (int x=current; x<totalAvailability[0].length; x++) {
			if (totalAvailability [id][x] == 1) consecs += 1;
		}
		return consecs;
	}
	
	//return number of intervals assigned to Person p
	public int calcNumIntervals(Person p) {
		int intervals = 0;
		int id = p.getID();
		for (int x = 0; x<schedFinal[0].length; x++) {
			if (schedFinal[id][x] == 1) intervals += 1;
		}
		return intervals;
	}
	
	public void print() {
		for (int x=0; x<schedFinal.length; x++) {
			for (int j=0; j<schedFinal[0].length; j++) {
				System.out.print(schedFinal[x][j] + " ");
			}
			System.out.println("");
		}
	}
	
	
}
