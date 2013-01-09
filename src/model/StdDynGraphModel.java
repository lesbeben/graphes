package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class StdDynGraphModel extends Observable implements DynGraphModel {

	private SortedSet<Integer> next;
	private Set<Vertex> vertices;
	private List<Edge> edges;
	private Map<Vertex, List<Edge>> adjacence;
	
	public StdDynGraphModel() {
		next = new TreeSet<Integer>();
		next.add(1);
		vertices = new LinkedHashSet<Vertex>();
		edges = new ArrayList<Edge>();
		
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
	public void addVertex() {
		Vertex v = new StdVertex(getNextVertexNb());
		vertices.add(v);
	}

	@Override
	public void removeVertex(Vertex v) {
		vertices.remove(v);
		next.add(v.getNumber());
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
	}

}
