package it.polito.tdp.crimes.model;

public class Vicino implements Comparable<Vicino>{

	private int id_vicino;
	private Double peso;
	public Vicino(int id_vicino, double peso) {
		super();
		this.id_vicino = id_vicino;
		this.peso = peso;
	}
	public int getId_vicino() {
		return id_vicino;
	}
	public void setId_vicino(int id_vicino) {
		this.id_vicino = id_vicino;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Vicino [id_vicino=" + id_vicino + ", peso=" + peso + "]";
	}
	@Override
	public int compareTo(Vicino o) {
		// TODO Auto-generated method stub
		return this.peso.compareTo(o.peso);
	}
	
	
}
