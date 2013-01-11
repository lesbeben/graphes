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

import model.Edge;
import model.GraphModel;
import model.Vertex;

public class GraphicGraph extends JComponent {

	private static final int RADIUS = 20;
	private static final int PREFERRED_WIDTH = 800;
	private static final int PREFERRED_HEIGHT = 600;
	private static final long serialVersionUID = -4583711502138269427L;
	private Map<Vertex, Point> coords;
	private Map<Point, Vertex> vertices;
	private GraphModel model;
	
	public GraphicGraph(GraphModel m) {
		super();
		if (m == null) {
			throw new IllegalArgumentException();
		}
		model = m;
		createController();
		coords = new HashMap<Vertex, Point>();
		vertices = new HashMap<Point, Vertex>();
		if (m.getVertices().size() > 0) {
			for (Vertex v: m.getVertices()) {
				Point p = randomNewPoint();
				coords.put(v, p);
				vertices.put(p, v);
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
		for (Point p2: vertices.keySet()) {
			if (verticesCollide(p1,p2)) {
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
	
	public void setModel(GraphModel m, Map<Vertex, Point> c, Map<Point, Vertex> v) {
		if (m == null) {
			throw new IllegalArgumentException();
		}
		model = m;
		createController();
		if ((c == null) || (v == null)){
			coords = new HashMap<Vertex, Point>();
			vertices = new HashMap<Point, Vertex>();
		} else {
			coords = c;
			vertices = v;
		}
		repaint();
	}
	
	public boolean verticesCollide(Point p1, Point p2){
	    return (p1.distance(p2) <= (2 * RADIUS));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
	}
	
	public void reset() {
		model.clear();
		coords.clear();
		vertices.clear();
	}
	
	public void randomize(int n) {
		reset();
		model.randomize(n);
		for (Vertex v: model.getVertices()) {
			Point p = randomNewPoint();
			coords.put(v, p);
			vertices.put(p, v);
		}
	}
	
	public Vertex clickedVertex(Point p) {
		for (Vertex v: getModel().getVertices()) {
			if ((Math.abs(p.x - coords.get(v).x + RADIUS) < RADIUS) && (Math.abs(p.y - coords.get(v).y + RADIUS) < RADIUS)) {
				return v;
			}
		}
		return null;
	}
	
	
	public Map<Vertex,Point> getCoords() {
		return coords;
	}
	
	public Map<Point, Vertex> getVertices() {
		return vertices;
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
			Point p1 = coords.get(e.getVertices()[0]);
			Point p2 = coords.get(e.getVertices()[1]);
			int x1 = p1.x + RADIUS;
			int y1 = p1.y + RADIUS;
			int x2 = p2.x + RADIUS;
			int y2 = p2.y + RADIUS;
			g.drawLine(x1, y1, x2, y2);
		}
		for (Vertex v: getModel().getVertices()) {
			g.setColor(Color.BLACK);
			Point p = coords.get(v);
			int x = p.x;
			int y = p.y;
			if ((getWidth() > 0) && (getHeight() > 0)) {
				if (p.x > getWidth()){
					p.setLocation(getWidth() - (2 * RADIUS), p.y);
				}
				if (p.y > getHeight()) {
					p.setLocation(p.x, getHeight() - (2 * RADIUS));
				}
			}
			g.drawOval(x, y, 2 * RADIUS, 2 * RADIUS);
			g.setColor(v.getColor());	
			g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
		}
	}
}
