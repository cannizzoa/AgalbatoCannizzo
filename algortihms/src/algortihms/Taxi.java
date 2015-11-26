package algortihms;

public class Taxi {
	public int id; // identification number of the taxi
	public boolean available; // if the taxi is available
	public int myZone; // identification number of the zone in which the taxi is
	public int zoneToGo; //identification number of the zone in which the taxi has to move
	public Reservation toServe; // the reservation that he has to be fulfilled
	
	
	public void move() {
		Controller.move(this, myZone, zoneToGo);
		this.myZone=zoneToGo;
	}
}
