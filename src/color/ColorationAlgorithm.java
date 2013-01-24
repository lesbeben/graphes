package color;

import java.util.Comparator;

import model.Colored;


/**
 * 
 * Définit la notion d'algorithme de coloration.
 *
 */

public interface ColorationAlgorithm extends Comparator<Colored> {
	
	
	
	//COMMANDES
	/**
	 * Colore les noeuds et les aretes du graphe fourni en parametre
	 * en utilisant le moins de couleurs possibles.
     * @post<pre>
     *     getColor() != Colored.INITIAL_COLOR</pre> //ACOMPLETER (for all)
	 * 
	 */
	
	void color();
	
	
	/**
	 * Decolore les noeuds et les aretes du graphe fourni en parametre.
     * @post<pre>
     *     getColor() == Colored.INITIAL_COLOR</pre> //ACOMPLETER (for all)
	 */
	
	void uncolor();
	
	public static enum Colors {
		RED,BLUE,YELLOW,PURPLE,ORANGE,GREEN,PINK
	}
	
	
	
}
