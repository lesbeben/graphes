package model;

import java.util.List;
import java.util.Map;


/**
 * 
 * Mod�lise un Graphe non-orient�, non-valu�
 *
 */
public interface GraphModel extends ObservableModel {
	
	int MAX_VERTEX_NB = 100;
	//REQUETES
	/**
	 * Retourne le nombre de sommets du graphe
	 */
	int getVertexNumber();
	
	/**
	 * Retourne le prochain num�ro libre pour un noeud
	 */
	int getNextVertexNumber();
	
	/**
	 * Retourne le nombre d'ar�tes du graphe
	 */
	int getEdgeNumber();
	
	/**
	 * Retourne la liste des sommets du graphe
	 */
	List<Vertex> getVerticesList();
	
	/**
	 * Retourne la table d'adjacence du graphe
	 */
	Map<Vertex,Edge[]> getAdjacenceMap();
	
	/**
	 * Retourne vrai si v1 est connect� � v2 dans le graphe
	 */
	boolean isConnected(Vertex v1, Vertex v2);
	
	
	//COMMANDES
	/**
     * Ajoute un sommet au graphe (num�rot� automatiquement)
     * @pre <pre>
     *    getNextVertexNumber() <= MAX_VERTEX_NB </pre>
     * @post <pre>
     *     getNextVertexNumber() == (old getNextVertexNumber()) + 1
     *     getVertexNumber() == (old getVertexNumber()) + 1</pre>
     *     le nouveau sommet est ajout� � la liste //ACOMPLETER
     */
    void addVertex();
    
    /**
     * Connecte deux sommets � l'aide d'une ar�te
     * @pre <pre>
     *    v1 != null
     *    v2 != null 
     *    !isConnected(v1, v2)
     *    </pre>
     * @post <pre>
     * 	  isConnected(v1, v2)
     *     </pre>
     *     la nouvelle ar�te est ajout�e � la table //ACOMPLETER
     */
    void link(Vertex v1, Vertex v2);
    
    /**
     * D�connecte deux sommets
     * @pre <pre>
     *    v1 != null
     *    v2 != null 
     *    isConnected(v1, v2)
     *    </pre>
     * @post <pre>
     * 	  !isConnected(v1, v2)
     *     </pre>
     *     l'ar�te est supprim�e la ou il faut (Map(v1), map(v2) //ACOMPLETER
     */
    void unlink(Vertex v1, Vertex v2);
}
