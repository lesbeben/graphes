package model;

public interface DynGraphModel extends GraphModel {

	//COMMANDES
	/**
     * Ajoute un sommet au graphe (numéroté automatiquement)
     * @pre <pre>
     *    getVertexNb() <= MAX_VERTEX_NB </pre>
     * @post <pre>
     *     getNextVertexNb() == (old getNextVertexNumber()) + 1
     *     getVertexNb() == (old getVertexNumber()) + 1</pre>
     *     le nouveau sommet est ajouté à la liste //ACOMPLETER
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
     *     le nouveau sommet est ajouté à la liste //ACOMPLETER
     */
    void removeVertex(Vertex v);
    
    /**
     * Connecte deux sommets à l'aide d'une arête
     * @pre <pre>
     *    v1 != null
     *    v2 != null 
     *    !isConnected(v1, v2)
     *    </pre>
     * @post <pre>
     * 	  isConnected(v1, v2)
     *     </pre>
     *     la nouvelle arête est ajoutée à la table //ACOMPLETER
     */
    void connect(Vertex v1, Vertex v2);
    
    /**
     * Déconnecte deux sommets
     * @pre <pre>
     *    v1 != null
     *    v2 != null 
     *    isConnected(v1, v2)
     *    </pre>
     * @post <pre>
     * 	  !isConnected(v1, v2)
     *     </pre>
     *     l'arête est supprimée la ou il faut (Map(v1), map(v2) //ACOMPLETER
     */
    void disconnect(Vertex v1, Vertex v2);
}

