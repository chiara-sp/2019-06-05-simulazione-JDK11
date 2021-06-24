package it.polito.tdp.crimes.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private EventsDao dao;
	private SimpleWeightedGraph<Integer,DefaultWeightedEdge> grafo;
	
	public Model() {
		dao= new EventsDao();
	}
	
	public List<Integer> getYears(){
		return dao.getYears();
	}
	public void creaGrafo(int anno) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, dao.getVertici());
		
		for(Integer v1: grafo.vertexSet()) {
			for(Integer v2: grafo.vertexSet()) {
				if(v1!=v2) {
					double latV1 = dao.latMedia(v1, anno);
					double lonV1 = dao.lngMedia(v1, anno);
					
					double latV2= dao.latMedia(v2, anno);
					double lonV2 = dao.lngMedia(v2, anno);
					
					LatLng l1= new LatLng(latV1, lonV1);
					LatLng l2= new LatLng(latV2, lonV2);
					
					double distance = LatLngTool.distance(l1, l2, LengthUnit.KILOMETER);
					
					Graphs.addEdge(grafo, v1, v2, distance);
				}
			}
		}
		
	}

	public int numVertici() {
		// TODO Auto-generated method stub
		return this.grafo.vertexSet().size();
	}
	public int numArchi() {
		return grafo.edgeSet().size();
	}
	
	public List<Vicino> getVicini(int distretto){
		List<Integer> vicini= Graphs.neighborListOf(grafo,distretto);
		List<Vicino> result= new LinkedList<>();
		
		for(Integer i: vicini) {
			result.add(new Vicino(i, grafo.getEdgeWeight(grafo.getEdge(i, distretto))));
		}
		Collections.sort(result);
		
		return result;
	}
	public Set<Integer> getDistretti(){
		return grafo.vertexSet();
	}
	public List<Integer> getMesi(int anno){
		return dao.getMonths(anno);
	}
	public List<Integer> getGiorni(int anno, int mese){
		return dao.getDays(anno, mese);
	}
}
