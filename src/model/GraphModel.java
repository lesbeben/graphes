package model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Modélise un Graphe non-orienté, non-valué
 *
 */
public interface GraphModel extends ObservableModel, Serializable {
	
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
	
	/**
	 * Comm à faire
	 * @param c
	 * @return
	 */
	public List<Colored> getAdjacents(Colored c);
	
	
	/**
	 * Renvoie le degré du graphe.
	 * a completer
	 * @param c
	 * @return
	 */
	public int getDegree(Colored c);
	
	//COMMANDES
	/**
	 * Cree un graphe avec n sommets et les connecte aleatoirement.
	 * 
	 * @pre <pre>
	 * 	   0 <= n <= MAX_VERTEX_NB </pre>
	 * 
	 * @post <pre>
	 * 	   getVerticesNb == n </pre> //ACOMPLETER
	 */
	void randomize(int n);
	
	/**
	 * Enleve tous les sommets et les arcs du graphe
	 */
	void clear();
	
	/**
	 * Methode pour tricher //ACOMPLETER
	 */
	void refresh();
	
	
}
