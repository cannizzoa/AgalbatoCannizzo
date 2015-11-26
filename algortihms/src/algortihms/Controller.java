package algortihms;

public class Controller {
	public static Zone[] map; // list of zones
	public static int zones; // number of zones
	public Taxi[] toMove; // list of Taxi to be moved from a zone to an other
	public int toMovePointer; // pointer of toMove list
	
	public int contaDisponibili(){
		
		//it counts every available taxi in the city and returns that number
		
		int a = 0;
		for(int i=0; i<zones; i++){
			a=a+map[i].contaTaxi();
		}
		return a;
	}
	
	

	public int totCoeff() {
		
		// it calculate the sum of the coefficients of every zone and returns that number
		
		int a = 0;
		for (int i=0; i<zones; i++){
			a = a + map[i].coefficient;
		}
		return a;
	}
	
	
	public int allocTaxi(int zone) {
		
		// it calculate how many taxis should be available in the zone given as parameter
		
		int a = 0;
		a = (contaDisponibili() / totCoeff()) * map[zone].coefficient;
		return a;
	}
	
	public int taxiToMove(int zone) { 
		
		// it adds taxis that have to be moved from the zone given as parameter to the list "toMove" and return number of taxis added
		
		int a = 0;
		if (allocTaxi(zone) < map[zone].contaTaxi()){
			a = map[zone].contaTaxi() - allocTaxi(zone);
		}
	
		for (int i=0; i<a;){
			if (map[zone].list[i].available){
				toMove[toMovePointer] = map[zone].list[i];
				i++;
				toMovePointer++;
			}
		}
		return a;
	}
	
	public int reqZones(int zone) {
		
		// it allocates taxis to the new zone that requires them and return number of taxis allocated
		
		int a = 0;
		if (allocTaxi(zone) > map[zone].contaTaxi()){
			a = allocTaxi(zone) - map[zone].contaTaxi();
		}
		
		for (int i=0; i<a;){
			toMove[toMovePointer].zoneToGo = zone;
			toMovePointer--;
		}
		
		return a;
	}
	
	
	
	public void cover(){
		
		// it moves taxis to ensure the established coverage
		
		for(int i=0; i<zones; i++){
			taxiToMove(i);
		}
		for(int i=0; i<zones; i++){
			reqZones(i);
		}
	}
	
	
	
	
	public static void move(Taxi t, int z1, int z2){
		
		// it moves a taxi from a queue to an other
		
		for (int i=0; i<map[z1].pointer; i++){
			if (t == map[z1].list[i]){
				
				map[z2].list[map[z2].pointer] = map[z1].list[i];
				map[z2].pointer++;
				map[z1].list[i] = map[z1].list[map[z1].pointer];
				map[z1].pointer--;			
			}
		}
	}
	
	
}
