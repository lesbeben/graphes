package color;

import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import model.Edge;
import model.GraphModel;
import model.Vertex;

public class Dsatur implements ColorationAlgorithm {

	private TreeSet<Vertex> sortedVertices;
	private int colorNb;
	private LinkedHashSet<Color> colors;
	private GraphModel m;
	
	public Dsatur(GraphModel model) {
		if (model == null){
			throw new IllegalArgumentException();
		}
		m = model;
		sortedVertices = new TreeSet<Vertex>(this);
		sortedVertices.addAll(m.getVertices());
		colorNb = sortedVertices.last().getDegree() + 1;
		colors = new LinkedHashSet<Color>();
		while (colors.size() < colorNb) {
			//Random random = new Random();
			//float hue = random.nextFloat();
			//float saturation = 0.9f;
			//float luminance = 1.0f; 
			//Color color = Color.getHSBColor(hue, saturation, luminance);
			//colors.add(color);
			int r =(int) (Math.random() * 254);
			r -= (r % 32);
			r += 1;
			int g =(int) (Math.random() * 254);
			g -= g % 32;
			g += 1;
			int b =(int) (Math.random() * 254);
			b -= b % 32;
			b += 1;
			colors.add(new Color(r, g, b));
		}
	}
	
	/**
	 * Retourne le DSAT du sommet
	 * @pre <pre>
	 * 	   v != null</pre>
	 */
	public int getDSAT(Vertex v) {
		int DSAT = 0;
		for (Vertex adj: v.getAdjacents()) {
			if (adj.isColored()) {
				DSAT += 1;
			} 
		}
		return (DSAT > 0) ? DSAT : v.getDegree();
	}
	
	@Override
	public void color() {
		//1. Ordonner les sommets par ordre décroissant de degré.
	    //2. Colorer un des sommets de degré maximum avec la couleur 1.
	    //3. Choisir un sommet non coloré avec DSAT maximum. Si un sommet voisin a la même couleur (cad conflit), choisir un sommet parmi ceux de degré maximum.
	    //4. Colorer ce sommet par la plus petite couleur possible
	    //5. Si tous les sommets sont colorés alors stop. Sinon aller en 3.
		Vertex v = sortedVertices.pollLast();
		v.setColor((Color) colors.toArray()[0]);
		while (sortedVertices.size() > 0){
			v = sortedVertices.pollLast();
			for (Color c: colors){
				boolean used = false;
				for (Vertex adj: v.getAdjacents()) {
					if (adj.getColor() == c) {
						used = true;
						break;
					}
				}
				if (used == false) {
					v.setColor(c);
					
					break;
				}
			}
		}		
		m.refresh();
	}

	@Override
	public void uncolor() {
		if (m == null) {
			throw new IllegalArgumentException();
		}
		for (Vertex v: m.getVertices()) {
			v.uncolor();
		}
		for(Edge e: m.getEdges()) {
			e.uncolor();
		}
		m.refresh();
	}

	@Override
	public int compare(Vertex v1, Vertex v2) {
		if ((v1 == null)|| (v2 == null)) {
			throw new IllegalArgumentException();
		}
		if (getDSAT(v1) == getDSAT(v2)) {
			return v1.compareTo(v2);
		} else {
			return getDSAT(v1) - getDSAT(v2);
		}
	}
}
