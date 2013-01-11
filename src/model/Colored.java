package model;

import java.awt.Color;

/**
 * 
 * Définit la notion d'objet coloriable, permettant de traiter de la meme facon
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
	
	/**
	 * Retourne vrai si l'objet n'est pas noir.
	 */
	boolean isColored();
	
	
	//COMMANDES
	/**
	 *  Définit la couleur de l'objet.
     * @pre <pre>
     *     c != null</pre>
     * @post<pre>
     *     getColor() == c</pre>
	 */
	void setColor(Color c);
	
	/**
	 * Reinitialise la couleur de l'objet.
	 * @post<pre>
     *     getColor() == c</pre>
	 */
	void uncolor();
	

}
