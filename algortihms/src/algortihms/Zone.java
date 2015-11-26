package algortihms;

public class Zone {
	public Taxi[] list; // list of taxis
	public int maxList; // maximum number of taxis in the zone
	public int coefficient; // coefficient of density of the zone (to proportion the distribution)
	
	public int contaTaxi(){
		
		// it counts the number of available taxis in the zone and returns that number
		
		int a = 0;
		for(int i=0; i<maxList; i++){
			if (list[i].available){
				a++;
			}
		}
		return a;
	}
}
