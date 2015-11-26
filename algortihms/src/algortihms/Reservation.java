package algortihms;

public class Reservation {
	public int zone; // id number of the zone of the reservation
	public Time startingT; // time to start to fulfill reservation (for real time request is -now- )
	public Taxi server; // taxi allocated to the reservation
}

