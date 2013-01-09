package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Modélise un Graphe non-orienté, non-valué
 *
 */
public interface GraphModel extends ObservableModel {
	
	int MAX_VERTEX_NB = 100;
	
	//REQUETES
	/**
	 * Retourne le nombre de sommets du graphe
	 */
	int getVerticesNb();
	
	/**
	 * Retourne le prochain numéro libre pour un noeud
	 */
	int getNextVertexNb();
	
	/**
	 * Retourne le nombre d'arêtes du graphe
	 */
	int getEdgesNb();
	
	/**
	 * Retourne la liste des sommets du graphe
	 */
	Set<Vertex> getVertices();
	
	/**
	 * Retourne la liste des aretes du graphe
	 */
	List<Edge> getEdges();	
	/**
	 * Retourne la table d'adjacence du graphe
	 */
	Map<Vertex, List<Edge>> getAdjacenceMap();
	
	/**
	 * Retourne vrai si v1 est connecté à v2 dans le graphe
	 */
	boolean isConnected(Vertex v1, Vertex v2);
	
	
}
