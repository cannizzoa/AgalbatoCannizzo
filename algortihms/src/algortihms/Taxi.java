package algortihms;


/*
- Analisi della copertura dei tass“ e conseguente selezione di quali spostare da quali zone a quali altre per ottimizzarla
- Ogni zona ha un numero che rappresenta il numero minimo di tass“ per avere la copertura minima
- Quelli oltre la minimalitˆ vengono distribuiti secondo proporzione
- Per coprire una zona, il sistema prende il taxi libero a distanza minima dalla zona in esame
- Questo significa che se il tassista era in una coda, ne esce ed entra in un'altra

- Gestione delle code di tass“: entrata e uscita dei taxi nelle code

- Reservation and requests real time: verifica che una richiesta sia fattibile quando arriva il momento di assegnarla a qualcuno
*/

public class Taxi {
	public int id; // identification number of the taxi
	public boolean available; // if the taxi is available
	public int myZone; // identification number of the zone in which the taxi is
	public int zoneToGo; //identification number of the zone in which the taxi has to move
}
