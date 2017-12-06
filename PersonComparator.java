import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{
	
	
	

		/*
		 * rank by:
		 * 		1. Number of Consecutive >= maxConsec
		 * 		2. Number of Intervals = ideal number of intervals
		 * 		3. Available intervals < ideal number of intervals
		 * 		also can add ranking by future intervals available
		 * 		4. if there are consecutive ones give it to same person
		 * 		5. if in the future there is a string of consecIntervals > numLeft dont give them intervals until then
		 *
		 */
		//return negative if p1<p2
		//return positive if p1>p2
		//order from lowest rank to highest
		public int compare(Person p1, Person p2) {
			if (p1.getAvail() <= p1.getLeft()) return -1;
			if (p2.getAvail() <= p2.getLeft()) return 1;
			if (p1.getCurrConsec() >= Person.maxConsec) return 1;
			if (p2.getCurrConsec() >= Person.maxConsec) return 1;
			if (p1.getCurrConsec() != 0 && p1.getCurrConsec() < Person.minConsec) return -1;
			if (p2.getCurrConsec() != 0 && p2.getCurrConsec() < Person.minConsec) return 1;
			return p1.getFilled() - p2.getFilled();
	}
}
