package model;

public interface DynGraphModel extends GraphModel {

	//COMMANDES
	/**
     * Ajoute un sommet au graphe (num�rot� automatiquement)
     * @pre <pre>
     *    getVertexNb() <= MAX_VERTEX_NB </pre>
     * @post <pre>
     *     getNextVertexNb() == (old getNextVertexNumber()) + 1
     *     getVertexNb() == (old getVertexNumber()) + 1</pre>
     *     le nouveau sommet est ajout� � la liste //ACOMPLETER
     */
    void addVertex();
    
    /**
     * Retire le sommet numero n du graphe et retire 
     * les connexions vers lui
     * @pre <pre>
     * 	   
     *    getVertexNb() <= MAX_VERTEX_NB </pre>
     * @post <pre>
     *     getVertexNb() == (old getVerticesNb()) - 1</pre>
     *     le nouveau sommet est ajout� � la liste //ACOMPLETER
     */
    void removeVertex(Vertex v);
    
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
    void connect(Vertex v1, Vertex v2);
    
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
    void disconnect(Vertex v1, Vertex v2);
}

