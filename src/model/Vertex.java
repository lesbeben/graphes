package model;

import java.util.List;


/**
 * 
 * Modélise un sommet du graphe
 *
 */

public interface Vertex extends Colored, Comparable<Vertex> {
	
	
	//REQUETES
	/**
	 * Retourne le numéro du sommet
	 */
	int getNumber();
	
	/**
	 * Retourne les sommet adjacents
	 */
	List<Vertex> getAdjacents();
	
	/**
	 * Retourne le nombre de sommets adjacents
	 */
	int getAdjacentsNb();
	
	/**
	 * Retourne vrai si le noeud courant est connecte au noeud v
	 */
	boolean isConnectedTo(Vertex v);
	
	//COMMANDES
	/**
	 * Connecte le noeud courant au noeud v
	 * @pre <pre>
     *    v != null
     *    !isConnectedTo(v) </pre>
     * @post <pre>
     * 	  isConnectedTo(v)
	 */
	void connectTo(Vertex v);
	
	/**
	 * Connecte le noeud courant au noeud v
	 * @pre <pre>
     *    v != null
     *    isConnectedTo(v) </pre>
     * @post <pre>
     * 	  !isConnectedTo(v)
	 */
	void disconnectFrom(Vertex v);
	
}
