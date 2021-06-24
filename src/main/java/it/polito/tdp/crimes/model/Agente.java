package it.polito.tdp.crimes.model;

import java.time.LocalTime;

import com.javadocmd.simplelatlng.LatLng;

public class Agente implements Comparable<Agente>{

	enum EventType{
		LIBERO, // arriva un nuovo paziente, entra in triage
		IN_MOVIMENTO, //finito il triage entro in sala d'attesa
		OCCUPATO, //PASSA UN CERTO TEMPO DI ATTESA
	};
	
	private LocalTime time;
	private EventType type;
	private int id;
	private LatLng posizione;
	public Agente(LocalTime time, EventType type, int id, LatLng posizione) {
		super();
		this.time = time;
		this.type = type;
		this.id = id;
		this.posizione=posizione;
	}
	public LatLng getPosizione() {
		return posizione;
	}
	public void setPosizione(LatLng posizione) {
		this.posizione = posizione;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int compareTo(Agente o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.time);
	}
	
	
	
}
