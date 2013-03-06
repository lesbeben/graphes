package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import model.DynGraphModel;
import model.Edge;
import model.GraphModel;
import model.Vertex;

public class GraphicGraph extends JComponent {

	
	private static final int PREFERRED_WIDTH = 800;
	private static final int PREFERRED_HEIGHT = 600;
	private static final long serialVersionUID = -4583711502138269427L;
	private Map<Vertex, GraphicVertex> vertex;
	private DynGraphModel model;
	
	public GraphicGraph(DynGraphModel m) {
		super();
		if (m == null) {
			throw new IllegalArgumentException();
		}
		model = m;
		createController();
		vertex = new HashMap<Vertex, GraphicVertex>();
		if (m.getVertices().size() > 0) {
			for (Vertex v: m.getVertices()) {
				Point p = randomNewPoint();
				GraphicVertex gv = new GraphicVertex(v, p);
				vertex.put(v, gv);
			}
		}
	}
	
	
	
	private Point randomNewPoint() {
		Point p1;
		if ((getWidth() == 0) && (getHeight() == 0)) {
			 p1 = new Point ((int) ((Math.random() * (PREFERRED_WIDTH - 40)) + 20), (int)((Math.random() * (PREFERRED_HEIGHT - 40)) + 20));
		} else {
			p1 = new Point ((int) ((Math.random() * (getWidth() - 40)) + 20), (int)((Math.random() * (getHeight() - 40)) + 20));
		}
		Boolean used = false;
		for (GraphicVertex gv: vertex.values()) {
			if (verticesCollide(p1, gv.getPoint())) {
				used = true;
				break;
			}
		}
		if (used == true) {
			return randomNewPoint();
		} else {
			return p1;
		}
		
	}
	
	public GraphModel getModel() {
		return model;
	}
	
	public int getRadius() {
		return GraphicVertex.RADIUS;
	}
	
	public void setModel(DynGraphModel m, Map<Vertex, GraphicVertex> v) {
		if (m == null) {
			throw new IllegalArgumentException();
		}
		model = m;
		createController();
		if ( v == null){
			vertex = new HashMap<Vertex, GraphicVertex>();
		} else {
			vertex = v;
		}
		repaint();
	}
	
	public boolean verticesCollide(Point p1, Point p2){
	    return (p1.distance(p2) <= (2 * GraphicVertex.RADIUS));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
	}
	
	public void reset() {
		model.clear();
		vertex.clear();
	}
	
	public void randomize(int n) {
		reset();
		model.randomize(n);
		for (Vertex v: model.getVertices()) {
			Point p = randomNewPoint();
			GraphicVertex gv = new GraphicVertex(v, p);
			vertex.put(v, gv);
		}
	}
	
	public GraphicVertex clickedVertex(Point p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		p.x -= GraphicVertex.RADIUS;
		p.y -= GraphicVertex.RADIUS;
		for (Vertex v: getModel().getVertices()) {
			if ((Math.abs(p.x - vertex.get(v).getPoint().x) < GraphicVertex.RADIUS) && (Math.abs(p.y - vertex.get(v).getPoint().y) < GraphicVertex.RADIUS)) {
				return vertex.get(v);
			}
		}
		return null;
	}
	
	
	public Map<Vertex, GraphicVertex> getCoords() {
		return vertex;
	}
	
	
	public void addVertex(Point p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		Vertex v = model.addVertex();
		p.x -= GraphicVertex.RADIUS;
		p.y -= GraphicVertex.RADIUS;
		GraphicVertex gv = new GraphicVertex(v, p);
		vertex.put(v, gv);
	}
	
	public void removeVertex(GraphicVertex gv) { //LEGEREMENT MODIFIE
		if (gv == null) {
			throw new IllegalArgumentException();
		}
		/*for (Vertex adj: gv.getVertex().getAdjacents()) {
			model.disconnect(gv.getVertex(), adj);
		}*/
		model.removeVertex(gv.getVertex());
		vertex.remove(gv.getVertex());
	}
	
	public void moveVertex(Vertex v, Point p) {
		if ((p == null) || (v == null)) {
			throw new IllegalArgumentException();
		}
		p.x -= GraphicVertex.RADIUS;
		p.y -= GraphicVertex.RADIUS;
		vertex.remove(v);
		GraphicVertex gv = new GraphicVertex(v, p);
		vertex.put(v, gv);
		repaint();
	}
	
	public void createController() {	
		getModel().addObserver(new Observer() {
            public void update(Observable o, Object arg) {
            	repaint();
            }
       });
    }
	
	protected void paintComponent(Graphics g) {
		for (Edge e: getModel().getEdges()) {
			g.setColor(e.getColor());
			Point p1 = vertex.get(e.getVertices()[0]).getPoint();
			Point p2 = vertex.get(e.getVertices()[1]).getPoint();
			int x1 = p1.x + GraphicVertex.RADIUS;
			int y1 = p1.y + GraphicVertex.RADIUS;
			int x2 = p2.x + GraphicVertex.RADIUS;
			int y2 = p2.y + GraphicVertex.RADIUS;
			g.drawLine(x1, y1, x2, y2);
		}
		for (Vertex v: getModel().getVertices()) {
			g.setColor(Color.BLACK);
			Point p = vertex.get(v).getPoint();
			int x = p.x;
			int y = p.y;
			if ((getWidth() > 0) && (getHeight() > 0)) {
				if (p.x > getWidth()){
					p.setLocation(getWidth() - (2 * GraphicVertex.RADIUS), p.y);
				}
				if (p.y > getHeight()) {
					p.setLocation(p.x, getHeight() - (2 * GraphicVertex.RADIUS));
				}
			}
			g.drawOval(x, y, 2 * GraphicVertex.RADIUS, 2 * GraphicVertex.RADIUS);
			g.setColor(v.getColor());	
			g.fillOval(x, y, 2 * GraphicVertex.RADIUS, 2 * GraphicVertex.RADIUS);
		}
	}
}
