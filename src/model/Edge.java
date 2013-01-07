package model;


/**
 * 
 * Mod�lise une ar�te du graphe en fonction de deux sommets
 *
 */

public interface Edge extends Colored {

	//REQUETES
	/**
	 * Retourne vrai si l'ar�te relie v � un autre sommet
	 */
	boolean connects(Vertex v);
	
	/**
	 * Retourne vrai si l'ar�te relie v1 et v2, faux sinon
	 */
	boolean connects(Vertex v1, Vertex v2);
	
}
