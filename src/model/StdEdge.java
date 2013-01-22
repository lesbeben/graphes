package model;

import java.awt.Color;

public class StdEdge implements Edge {

	private static final long serialVersionUID = 1462031434686754705L;
	private Color color;
	private Vertex v1;
	private Vertex v2;
	
	public StdEdge(Vertex v1, Vertex v2) {
		color = Colored.INITIAL_COLOR;
		this.v1 = v1;
		this.v2 = v2;
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
	public boolean connectedTo(Vertex v) {
		return (v1.equals(v) || v2.equals(v));
	}

	@Override
	public boolean connects(Vertex v1, Vertex v2) {
		return (connectedTo(v1) && connectedTo(v2) && (!v1.equals(v2)));
	}
	
	@Override
	public Vertex[] getVertices() {
		Vertex[] vertices = new Vertex[2];
		vertices[0] = v1;
		vertices[1] = v2;
		return vertices;
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
		return "[" + v1.getNumber() + "|" + getColor().toString() + "|" + v2.getNumber() + "]";
	}
	
	public boolean equals(Edge e) {
		return e.connects(this.getVertices()[0], this.getVertices()[1]);
	}

	@Override
	public boolean adjacentTo(Edge e) {
		return ((e.connectedTo(getVertices()[0])) || (e.connectedTo(getVertices()[1])));
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass() != getClass()){
			throw new ClassCastException(); 
		}
		Edge e = (Edge) o;
		if (getVertices()[1].compareTo(e.getVertices()[0]) > 0) {
			if (e.getVertices()[1].compareTo(e.getVertices()[0]) > 0) {
				if (e.getVertices()[0].equals(getVertices()[0])) {
					return getVertices()[1].getNumber() - e.getVertices()[1].getNumber();
				} else {
					return getVertices()[0].getNumber() - e.getVertices()[0].getNumber();
				}
			} else {
				if (e.getVertices()[1].equals(getVertices()[0])) {
					return getVertices()[1].getNumber() - e.getVertices()[0].getNumber();
				} else {
					return getVertices()[0].getNumber() - e.getVertices()[1].getNumber();
				}
			}
		} else {
			if (e.getVertices()[1].compareTo(e.getVertices()[0]) > 0) {
				if (e.getVertices()[0].equals(getVertices()[1])) {
					return getVertices()[0].getNumber() - e.getVertices()[1].getNumber();
				} else {
					return getVertices()[1].getNumber() - e.getVertices()[0].getNumber();
				}
			} else {
				if (e.getVertices()[1].equals(getVertices()[1])) {
					return getVertices()[0].getNumber() - e.getVertices()[0].getNumber();
				} else {
					return getVertices()[1].getNumber() - e.getVertices()[1].getNumber();
				}
			}
		}
	}

}
