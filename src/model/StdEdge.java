package model;

import java.awt.Color;

public class StdEdge implements Edge {

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
	
	public Vertex[] getVertices() {
		Vertex[] vertices = new Vertex[2];
		vertices[0] = v1;
		vertices[1] = v2;
		return vertices;
	}

		
}
