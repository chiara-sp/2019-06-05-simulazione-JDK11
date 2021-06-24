package it.polito.tdp.crimes.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.crimes.db.EventsDao;


public class Simulatore {
	
	EventsDao dao= new EventsDao();

	//coda degli eventi
		private PriorityQueue<Agente> queue;
		
		//modello del mondo 
		private int agenti;
		private LatLng partenza;
		LocalDate data;
		List<Event> eventiDelGiorno;
		private Graph<Integer, DefaultWeightedEdge> grafo;
		
		
		//output
		int nMalGestite; 
	
		public void init(int anno, int mese, int giorno, int numAgenti, SimpleWeightedGraph<Integer, DefaultWeightedEdge>grafo ) {
			
			queue= new PriorityQueue<>();
			agenti= numAgenti;
			eventiDelGiorno= new LinkedList<>(dao.listAllEvents(anno, mese, giorno));
			int distrettoMigliore= dao.getDistrettoMigliore(anno);
			double latMedia= dao.latMedia(distrettoMigliore, anno);
			double lonMedia= dao.lngMedia(distrettoMigliore, anno);
			partenza= new LatLng(latMedia,lonMedia);
			
			this.grafo=grafo;
			this.nMalGestite=0;
		}
}
