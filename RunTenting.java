
public class RunTenting {

	public static void main(String[] args) {
		AvailabilitySchedule miles =  new AvailabilitySchedule();
		Schedule test = new Schedule(miles);
		test.fillIntervals();
		test.print();

	}

}
