package model;

import java.io.Serializable;
import java.util.List;



/**
 * 
 * Modélise un sommet du graphe
 *
 */

public interface Vertex extends Colored, Comparable<Object>, Serializable {
	
	
	//REQUETES
	/**
	 * Retourne le numéro du sommet
	 */
	int getNumber();
	
	
	/**
	 * Retourne le nombre de sommets adjacents
	 */
	int getDegree();
	
	/**
	 * Retourne les sommets adjacents
	 */
	List<Vertex> getAdjacents();
	
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
