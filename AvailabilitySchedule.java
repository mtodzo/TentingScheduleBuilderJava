import java.util.Scanner;
public class AvailabilitySchedule {
	/*
	 * this class will contain all the user inputed stuff
	 * will create matrix totalAvailability which contains availability for all members
	 */
	int[][] totalAvailability;
	int numPeople;
	int numIntervals;
	int peoplePerInterval;
	Scanner scan;
	
	
	public AvailabilitySchedule() {
		scan = new Scanner(System.in);
		System.out.println("Number of people: ");
		numPeople = scan.nextInt();
		System.out.println("Number of Intervals: ");
		numIntervals = scan.nextInt();
		System.out.println("People per Interval: ");
		peoplePerInterval = scan.nextInt();
		
		totalAvailability = new int[numPeople][numIntervals];

		for (int x=0; x<numPeople; x++) {
			String [] availArray = new String[numIntervals];
			int counter = 0;
			System.out.println("Enter availability 1=avail, 0 = not. Spaces in between.");
			while(counter < numIntervals) {
				availArray[counter] = scan.next();
				counter ++;
			}
			//String avail = scan.nextLine();
			//String [] availArray = avail.split(" ");
			for (int i=0; i<availArray.length; i++) {
				totalAvailability[x][i] = Integer.parseInt(availArray[i]);
			}
			
		}
	}
	
	public int[][] getAvailSched(){
		return totalAvailability;
	}
	
	/*returns number of future open slots
	@param
		*row/column represent the coordinates of current interval on availability matrix
		*total availability would be getFutureAvailability(0,0)
	*/
	public int getFutureAvailability(int row, int column) {
		//TODO
		return 0;
		
	}

	
}
