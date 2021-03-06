package model;

import java.io.Serializable;


/**
 * 
 * Mod�lise une ar�te du graphe en fonction de deux sommets
 *
 */

public interface Edge extends Colored, Serializable {

	//REQUETES
	/**
	 * Retourne vrai si l'ar�te relie v � un autre sommet
	 */
	boolean connectedTo(Vertex v);
	
	/**
	 * Retourne vrai si l'ar�te relie v1 et v2, faux sinon
	 */
	boolean connects(Vertex v1, Vertex v2);
	
	/** 
	 * Retourne les deux sommets relies par cette arete
	 */
	Vertex[] getVertices();
	
	/**
	 * Retourne vrai si les deux ar�tes sont adjacentes
	 */
	boolean adjacentTo(Edge e);
	
}
