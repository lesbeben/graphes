import gui.GraphicGraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import color.ColorationAlgorithm;
import color.Dsatur;
import color.ImplementedAlgorithms;
import model.DynGraphModel;
import model.GraphModel;
import model.StdDynGraphModel;
import model.Vertex;


public class GraphColor {

	private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
	private JFrame frame = null;
	private JPanel contentPane = null;
	private JButton newButton = null;
	private JButton randomButton = null;
	private JButton saveButton = null;
	private JButton loadButton = null;
	private JToggleButton moveButton = null;
	private JToggleButton vertexButton = null;
	private JToggleButton edgeButton = null;
	private JComboBox<String> algoButton = null;
	private JButton colorButton = null;
	private JButton uncolorButton = null;
	private DynGraphModel model;
	private ColorationAlgorithm algo;
	private GraphicGraph graphic;
	private enum tools {
		MOVE,VERTEX,EDGE;
	}
	private tools mode = tools.VERTEX;
	private ImplementedAlgorithms setAlgo = null;
	
	public GraphColor(){
		createModel();
		createView();
		placeComponents();
		createController();
	}
		
	public void display() {
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    model.notifyObservers();
	}
	
	private void createModel() {
		model = new StdDynGraphModel();
		model.randomize(10);
    }
	
	private void createView() {
        frame = new JFrame("Graph Color");
        newButton = new JButton("Nouveau");
		randomButton = new JButton("Aleatoire");
		saveButton = new JButton("Sauvegarder");
		loadButton = new JButton("Charger");
		moveButton = new JToggleButton("Deplacer");
		vertexButton = new JToggleButton("Sommet");
		edgeButton = new JToggleButton("Arete");
		String[] algos = { "DSATUR", "Algo 2", "Algo 3", "Algo 4", "Algo 5" };
		algoButton= new JComboBox<String>(algos);
		algoButton.setSelectedIndex(0);
		colorButton = new JButton("Colorier");
		uncolorButton = new JButton("Decolorier");
        contentPane = new JPanel(new BorderLayout());
        graphic = new GraphicGraph(model);
		contentPane.setBackground(Color.WHITE);
    	frame.setPreferredSize(
                new Dimension(FRAME_WIDTH, FRAME_HEIGHT)
        );
    }
	
	private void placeComponents() {
		JToolBar toolBar = new JToolBar(); {
			toolBar.setFloatable(false);
			toolBar.setOrientation(JToolBar.HORIZONTAL);
			toolBar.addSeparator();
			toolBar.add(newButton);
			toolBar.add(randomButton);
			toolBar.add(saveButton);
			toolBar.add(loadButton);
			toolBar.addSeparator();
			toolBar.addSeparator();
			toolBar.add(moveButton);
			toolBar.add(vertexButton);
			toolBar.add(edgeButton);
			toolBar.addSeparator();
			toolBar.addSeparator();
			toolBar.add(algoButton);
			toolBar.add(colorButton);
			toolBar.add(uncolorButton);
			toolBar.addSeparator();
		}
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(graphic,BorderLayout.CENTER);
		frame.add(contentPane);
	}
	
	private void createController() {
		model.addObserver(new Observer() {
            public void update(Observable o, Object arg) {
               if (model.getVerticesNb() >= GraphModel.MAX_VERTEX_NB) {
            	   vertexButton.setEnabled(false);
            	   mode = tools.MOVE;
            	   moveButton.setSelected(true);  
               } else {
            	   vertexButton.setEnabled(true);
            	   if (model.getVerticesNb() == 0) {
            		   mode = tools.VERTEX;
            		   vertexButton.setSelected(true);
            		   moveButton.setEnabled(false);
            		   edgeButton.setEnabled(false);
            		   saveButton.setEnabled(false);
            		   colorButton.setEnabled(false);
            		   uncolorButton.setEnabled(false);
            	   } else {
            		   moveButton.setEnabled(true);
            		   edgeButton.setEnabled(true);
            		   saveButton.setEnabled(true);
            		   colorButton.setEnabled(true);
            		   uncolorButton.setEnabled(true);
            	   }
               }
            }
        });
		ButtonGroup bg = new ButtonGroup();
		bg.add(moveButton);
        bg.add(vertexButton);
        bg.add(edgeButton);
        vertexButton.setSelected(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		graphic.reset();
        		model.notifyObservers();
        	}
        });
        randomButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		graphic.randomize(10); 
        		model.notifyObservers();
        	}
        });
        saveButton.addActionListener( new ActionListener(){
        	public void actionPerformed(ActionEvent e)  {
        			try {
						saveState();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
        	}
        });
        loadButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser fc = new JFileChooser();
        		fc.setFileFilter(new GraphFileFilter());
        		int returnVal = fc.showOpenDialog(null);
        		if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    try {
						load(f);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                    model.notifyObservers();
        		}
        	}
        });
        moveButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mode = tools.MOVE; 
        		model.notifyObservers();
        	}
        });
        vertexButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mode = tools.VERTEX; 
        		model.notifyObservers();
        	}
        });
        edgeButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mode = tools.EDGE; 
        		model.notifyObservers();
        	}
        });
        algoButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//A FINIR 
        		model.notifyObservers();
        	}
        });
        colorButton.addActionListener( new ActionListener() { //A NETTOYER
        	public void actionPerformed(ActionEvent e) {
        		algo = new Dsatur(model);
        		algo.color();
        		model.notifyObservers();
        	}
        });
        uncolorButton.addActionListener( new ActionListener() { //A NETTOYER
        	public void actionPerformed(ActionEvent e) {
        		algo = new Dsatur(model);
        		algo.uncolor();
        		model.notifyObservers();
        	}
        });
        
	}
	
	public void saveState() throws IOException, FileNotFoundException {
		Date now = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy.hhmmssa'.gra'");
		FileOutputStream fos = new FileOutputStream(sdf.format(now));
		ObjectOutputStream oos =new ObjectOutputStream(fos);
		oos.writeObject(model);
		oos.writeObject(graphic.getCoords());
		oos.writeObject(graphic.getVertices());
		oos.close();
	}
	
	public void load(File f) throws IOException, FileNotFoundException, ClassNotFoundException{
		if (f == null){
			throw new IllegalArgumentException();
		}
		FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        model = (DynGraphModel) ois.readObject();
		Map<Vertex, Point> c = (Map<Vertex, Point>) ois.readObject();
		Map<Point, Vertex> v = (Map<Point, Vertex>) ois.readObject();
        graphic.setModel(model, c, v);
        ois.close();
	}
	
	public class GraphFileFilter extends FileFilter
	{
	     public boolean accept(File f)
	    {
	        if(f.isDirectory())
	        {
	            return true;
	        }
	        return f.getName().endsWith(".gra");
	    }
	 
	    public String getDescription()
	    {
	        return "Fichiers Graphes (*.gra)";
	    }
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GraphColor().display();
            }
		});
	}
}
