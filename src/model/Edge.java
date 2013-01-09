package model;


/**
 * 
 * Modélise une arête du graphe en fonction de deux sommets
 *
 */

public interface Edge extends Colored {

	//REQUETES
	/**
	 * Retourne vrai si l'arête relie v à un autre sommet
	 */
	boolean connectedTo(Vertex v);
	
	/**
	 * Retourne vrai si l'arête relie v1 et v2, faux sinon
	 */
	boolean connects(Vertex v1, Vertex v2);
	
	/** 
	 * Retourne les deux sommets relies par cette arete
	 */
	Vertex[] getVertices();
	
}
