package model;

import java.awt.Color;

/**
 * 
 * Définit la notion d'objet coloriable, permettant de traiter indifféremment
 * la coloration des arêtes et des noeuds du graphe. 
 * 
 * @cons
 *     $POST$
 *     getColor() == INITIAL_COLOR
 *
 */

public interface Colored {
	
	/**
	 * La couleur initiale de l'objet 
	 */
	Color INITIAL_COLOR = Color.BLACK;
	
	//REQUETES
	/**
	 * Retourne la couleur actuelle de l'objet
	 */
	Color getColor();
	
	//COMMANDES
	/**
	 *  Définit la couleur de l'objet.
     * @pre
     * 
     *     c != null
     * @post
     *     getColor() == c
	 */
	void setColor(Color c);

}
