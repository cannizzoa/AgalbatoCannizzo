
//SIGNATURES

abstract sig User {}
sig UserList{
	list: set User
}

sig TaxiDriver extends User{
	located: one Location
}

sig Passenger extends User{
}

sig Time{
}

sig Location{
}

sig Reservation {
	booker: one Passenger,
	startingTime: one Time,
	startingLocation: one Location,
	destination: one Location
}
sig ReservationList{
	list: set Reservation
}

sig RequestForService extends Reservation{
	server: one TaxiDriver
}



//FACTS


//two requests for service can't have the same passenger
fact{
	(no a1, a2: RequestForService, p: Passenger | a1 != a2 and a1.booker = p and a2.booker = p)
} 

//destination must be different form starting location
fact{
	(no a1, a2: Location, r: Reservation | a1=a2 and a1=r.startingLocation and a2=r.destination)
}

//two request for service can't have the same taxi driver
fact{
	(no a1, a2: RequestForService, t: TaxiDriver | a1 != a2 and a1.server= t and a1.server=t)
}

//a passenger can't reserve two taxi at the same starting time
fact{
	(no a1, a2: Reservation, t: Time | a1 !=a2 and a1.startingTime=t and a2.startingTime=t and a1.booker=a2.booker)
}
//there is no location without reservation
fact{
	(all l : Location {one r: Reservation |l in r.startingLocation or l in r.destination})
}

//there is no passenger without reservation
fact{
	(all p : Passenger {one r: Reservation |p in r.booker})
}

//there is no time without reservation
fact{
	(all t : Time {one r: Reservation |t in r.startingTime})
}

//two taxi can't be in the same location
fact{
	(no a1, a2 : TaxiDriver, l: Location | a1 != a2 and a1.located =l and a2.located = l)
}

//there is only one ReservationList
fact{
	(#ReservationList = 1)
}

//there is only one UserList
fact{
	(#UserList =1)
}



//ASSERTIONS


//checking the adding of a reservation to the list
assert addReservation{
	all r: Reservation, l1, l2: ReservationList | (r not in l1.list) and addReservation[r, l1, l2] implies (r in l2.list)
}
check addReservation for 5

//checking the removing of a reservation from the list
assert deleteReservation{
	all r: Reservation, l1, l2: ReservationList | (r in l1.list) and deleteReservation[r, l1, l2] implies (r not in l2.list)
}
check deleteReservation for 5



//PREDICATES


//adding a reservation to the list
pred addReservation ( r: Reservation, l1, l2 : ReservationList){
	r not in l1.list implies l2.list = l1.list+r
}
run addReservation for 7

//removing a reservation from the list
pred deleteReservation ( r: Reservation, l1, l2 : ReservationList){
	l2.list = l1.list - r
}
run deleteReservation for 2


pred show(){
}

run show for 10 but exactly 6 User