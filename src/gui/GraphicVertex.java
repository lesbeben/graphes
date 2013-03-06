package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

import model.Vertex;

public class GraphicVertex extends JComponent {

	private static final long serialVersionUID = -267928569202882731L;
	public static final int RADIUS = 20;
	private Vertex vertex;
	private Point point;
	
	public GraphicVertex (Vertex v, Point p) {
		if ((v == null) || (p == null)) {
			throw new IllegalArgumentException();
		}
		vertex = v;
		point = p;
	}
	
	public Vertex getVertex() {
		return vertex;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public void setPoint(Point p) {
		if (p == null){
			throw new IllegalArgumentException();
		}
		point = p;
	}
	
	public void PaintComponents(Graphics g) {
		g.setColor(Color.BLACK);
		int x = getPoint().x;
		int y = getPoint().y;
		if ((getWidth() > 0) && (getHeight() > 0)) {
			if (getPoint().x > getWidth()){
				getPoint().setLocation(getWidth() - (2 * RADIUS), getPoint().y);
			}
			if (getPoint().y > getHeight()) {
				getPoint().setLocation(getPoint().x, getHeight() - (2 * RADIUS));
			}
		}
		g.drawOval(x, y, 2 * RADIUS, 2 * RADIUS);
		g.setColor(getVertex().getColor());	
		g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
	}
}
