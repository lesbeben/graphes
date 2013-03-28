package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class StdDynGraphModel extends Observable implements DynGraphModel {

	private static final long serialVersionUID = -3170882226726024740L;
	private SortedSet<Integer> next;
	private Set<Vertex> vertices;
	private List<Edge> edges;
	private Map<Vertex, List<Edge>> adjacence;
	
	public StdDynGraphModel() {
		next = new TreeSet<Integer>();
		next.add(1);
		vertices = new LinkedHashSet<Vertex>();
		edges = new ArrayList<Edge>();
		adjacence = new HashMap<Vertex, List<Edge>>();
		setChanged();
	}
	
	@Override
	public int getVerticesNb() {
		return getVertices().size();
	}

	@Override
	public int getNextVertexNb() {
		if (next.size() > 0) {
			int n = next.first();
			next.remove(n);
			return n;
		} else {
			return getVerticesNb() + 1;
		}
	}

	@Override
	public int getEdgesNb() {
		return getEdges().size();
	}

	@Override
	public Set<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public List<Edge> getEdges() {
		return edges;
	}

	@Override
	public Map<Vertex, List<Edge>> getAdjacenceMap() {
		return adjacence;
	}


	@Override
	public boolean isConnected(Vertex v1, Vertex v2) {
		return v1.isConnectedTo(v2);
	}

	@Override
	public Vertex addVertex() {
		if (getVerticesNb() >= MAX_VERTEX_NB) {
			throw new IllegalStateException();
		}
		Vertex v = new StdVertex(getNextVertexNb());
		vertices.add(v);
		setChanged();
		return v;
	}

	@Override
	public void removeVertex(Vertex v) {
		vertices.remove(v);
		next.add(v.getNumber());
		List<Edge> le = new ArrayList<Edge>();
		for (Edge e: edges) {
			if (e.connectedTo(v)) {
				le.add(e);
			}
		}
		for (Edge e: le) {
			edges.remove(e);
		}
		adjacence.remove(v);
		setChanged();
	}

	@Override
	public void connect(Vertex v1, Vertex v2) {
		if ((v1 == null) || (v2 == null)) {
			throw new IllegalArgumentException();
		}
		if ((isConnected(v1,v2))) {
			throw new IllegalStateException();
		}
		v1.connectTo(v2);
		v2.connectTo(v1);
		Edge e = new StdEdge(v1,v2);
		if (adjacence.containsKey(v1)) {
			adjacence.get(v1).add(e);
		} else {
			List<Edge> le = new ArrayList<Edge>();
			le.add(e);
			adjacence.put(v1, le);
		}
		if (adjacence.containsKey(v2)) {
			adjacence.get(v2).add(e);
		} else {
			List<Edge> le = new ArrayList<Edge>();
			le.add(e);
			adjacence.put(v2, le);
		}
		edges.add(e);
		setChanged();
	}

	@Override
	public void disconnect(Vertex v1, Vertex v2) {
		if ((v1 == null) || (v2 == null)) {
			throw new IllegalArgumentException();
		}
		if ((!isConnected(v1,v2))) {
			throw new IllegalStateException();
		}
		v1.disconnectFrom(v2);
		v2.disconnectFrom(v1);
		for (Edge e: adjacence.get(v1)) {
			if (e.connectedTo(v2)) {
				adjacence.get(v1).remove(e);
				edges.remove(e);
				break;
			}
		}
		if (adjacence.get(v1).size() == 0) {
			adjacence.remove(v1);
		}
		for (Edge e: adjacence.get(v2)) {
			if (e.connectedTo(v1)) {
				adjacence.get(v2).remove(e);
				break;
			}
		}
		if (adjacence.get(v2).size() == 0) {
			adjacence.remove(v2);
		}
		setChanged();
	}
	
	public String toString() {
		String s = new String();
		s += "Sommets : ";
		for (Vertex v: getVertices()) {
			s += v.toString();
		}
		s += "Arêtes : ";
		for (Edge e: getEdges()) {
			s += e.toString();
		}
		s += "\n";
		return s;
	}
	
	@Override
	public void randomize(int n) {//pas de random pour l'instant, à terminer
		for (int i = 0; i < n; i++) {
			addVertex();
		}
		Vertex[] vt = new Vertex[10];
		int i = 0;
		for (Vertex v: getVertices()) {
			vt[i]=v;
			i += 1;
		}
		connect(vt[0], vt[1]);
		connect(vt[1], vt[3]);
		connect(vt[3], vt[5]);
		connect(vt[5], vt[6]);
		connect(vt[6], vt[0]);
		connect(vt[2], vt[4]);
		connect(vt[4], vt[8]);
	}
	
	@Override
	public void clear() {
		next = new TreeSet<Integer>();
		next.add(1);
		vertices = new LinkedHashSet<Vertex>();
		edges = new ArrayList<Edge>();
		adjacence = new HashMap<Vertex, List<Edge>>();
		setChanged();
	}
	
	@Override
	public void refresh() {
		setChanged();
	}
	
	public List<Colored> getAdjacents(Colored c) {
		if (c instanceof Edge) {
			return getAdjacents((Edge) c);
		} else if (c instanceof Vertex) {
			return getAdjacents((Vertex) c);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public List<Colored> getAdjacents(Edge e){
		List<Colored> lc = new LinkedList<Colored>();
		for (Edge adj: getAdjacenceMap().get(e.getVertices()[0])) {
			if (!lc.contains(adj)) {
				lc.add(adj);
			}
		}
		for (Edge adj: getAdjacenceMap().get(e.getVertices()[1])) {
			if (!lc.contains(adj)) {
				lc.add(adj);
			}
		}
		return lc;
	}
	
	public List<Colored> getAdjacents(Vertex v){
		List<Colored> lc = new LinkedList<Colored>();
		lc.addAll(v.getAdjacents());
		return lc;
	}
	
	@Override
	public int getDegree(Colored c) {
		return getAdjacents(c).size();
	}
}