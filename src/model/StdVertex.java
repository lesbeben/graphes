package model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class StdVertex implements Vertex {
	
	private static final long serialVersionUID = 5973984289037546941L;
	private Color color;
	private int number;
	private List<Vertex> adjacents;
	
	public StdVertex(int n) {
		color = Colored.INITIAL_COLOR;
		number = n;
		adjacents = new LinkedList<Vertex>();
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color c) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		color = c;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public List<Vertex> getAdjacents() {
		return adjacents;
	}

	@Override
	public int getDegree() {
		return adjacents.size();
	}

	@Override
	public boolean isConnectedTo(Vertex v) {
		return adjacents.contains(v);
	}

	@Override
	public void connectTo(Vertex v) {
		if ((v == null) || (isConnectedTo(v))) {
			throw new IllegalArgumentException();
		}
		adjacents.add(v);
	}

	@Override
	public void disconnectFrom(Vertex v) {
		if ((v == null) || (!isConnectedTo(v))) {
			throw new IllegalArgumentException();
		}
		adjacents.remove(v);
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass() != getClass()){
			throw new ClassCastException(); 
		}
		Vertex v = (Vertex) o;
		return getNumber() - v.getNumber();
	}

	@Override
	public boolean isColored() {
		return getColor().equals(Colored.INITIAL_COLOR);
	}

	@Override
	public void uncolor() {
		color = Colored.INITIAL_COLOR;
	}
	
	public String toString() {
		return "(" + getNumber() + "|" + getColor().toString() + ")";
	}

}
