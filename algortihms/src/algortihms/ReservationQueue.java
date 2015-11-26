package algortihms;

public class ReservationQueue {
	public Reservation[] queue; // list of reservations
	public int pointer; // pointer of the queue
	
	
	public void fulfill(Time t){
		
		// it allocates a taxi to the reservation that has to be fulfilled in that time
		
		for (int i=0; i<pointer; i++){
			if (queue[i].startingT == t){
				for (int j=0; j< Controller.map[queue[i].zone].pointer; j++){
					if (Controller.map[queue[i].zone].list[j].available){
						
						queue[i].server = Controller.map[queue[i].zone].list[j];
						Controller.map[queue[i].zone].list[j].toServe = queue[i];
						Controller.map[queue[i].zone].list[j].available = false;
						queue[i] = queue [pointer];
						pointer--;
					}
				}
			}
		} 
	
	}
	
}

