import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingWorker;

public class FractalExplorer {
	private int sizeDisplay;
	private JImageDisplay imageDisplay;
	private FractalGenerator myFractalGenerator;
	private Rectangle2D.Double range;
	private JComboBox comboBox;
	private JButton button_save;
	private JButton button_reset;
	private int rowsRemaining;
	
	public static void main (String [] args) {
		FractalExplorer myFractalExplorer = new FractalExplorer(600);
		myFractalExplorer.createAndShowGUI();
		myFractalExplorer.drawFractal();
	}
	
	public FractalExplorer (int size) {
		this.sizeDisplay = size;
		this.range = new Rectangle2D.Double(0, 0, 0, 0);
		this.myFractalGenerator = new Mandelbrot();
		myFractalGenerator.getInitialRange(this.range);
	}
	
	public void createAndShowGUI () {
		JFrame frame = new JFrame();
		imageDisplay = new  JImageDisplay (sizeDisplay, sizeDisplay);
    	imageDisplay.addMouseListener(new MouseListener());
    	
    	comboBox = new JComboBox ();
    	comboBox.addItem(new Mandelbrot());
    	comboBox.addItem(new Tricorn());
    	comboBox.addItem(new BurningShip());
    	comboBox.addActionListener(new ActionHandler());
    	
		JPanel panel = new JPanel();
		Label label1 = new Label("Fractal:");
		panel.add(label1);
		panel.add(comboBox);

		JPanel panel2 = new JPanel();
		button_save = new JButton("save");
		panel2.add(button_save);
		button_save.setActionCommand("save");
		button_save.addActionListener(new ActionHandler());
		button_reset = new JButton("reset");
		panel2.add(button_reset);
		button_reset.setActionCommand("reset");
		button_reset.addActionListener(new ActionHandler());
		
		frame.setLayout(new java.awt.BorderLayout());
		frame.add(imageDisplay, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.SOUTH);
		
		frame.pack ();
		frame.setVisible (true);
		frame.setResizable (false);
	}
	
	private void  drawFractal () {
		enableUI(false);
		rowsRemaining = sizeDisplay;
		for(int y = 0; y < sizeDisplay; y++) {
			FractalWorker fw = new FractalWorker(y);
			fw.execute();
		}
	}
	
	public class ActionHandler implements  ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("save")) {
				JFileChooser fileChooser  = new JFileChooser();
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);
				int a = fileChooser.showSaveDialog(imageDisplay);
				if (a == JFileChooser.APPROVE_OPTION) {
					try {
						ImageIO.write(imageDisplay.getPicture(), "png", fileChooser.getSelectedFile());
					}
					catch (NullPointerException | IOException ee) {
						JOptionPane.showMessageDialog(imageDisplay, ee.getMessage(), "«Cannot Save Image", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else if (e.getActionCommand().equals("reset")) {
				myFractalGenerator.getInitialRange(range);
	            drawFractal();
			}
			else {
				myFractalGenerator = (FractalGenerator) comboBox.getSelectedItem();
	            range = new Rectangle2D.Double(0,0,0,0);
	            myFractalGenerator.getInitialRange(range);
	            drawFractal();
			}
		}
	}
	
	public class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (rowsRemaining == 0) {
				double x = FractalGenerator.getCoord (range.x, range.x + range.width, sizeDisplay, e.getX());
				double y = FractalGenerator.getCoord (range.x, range.x + range.width, sizeDisplay, e.getY());
				myFractalGenerator.recenterAndZoomRange(range, x, y, 0.5);
				drawFractal();
			}
		}
	}
	
	private void enableUI(boolean val) {
		button_save.setEnabled(val);
		button_reset.setEnabled(val);
		comboBox.setEditable(val);
	}

	private class FractalWorker extends SwingWorker<Object, Object> {
		public int yCoordFW;
		public int[] RGBmas;
		
		public FractalWorker (int y) {
			this.yCoordFW = y;
		}
		
		@Override
		protected Object doInBackground() throws Exception {
			RGBmas = new int [sizeDisplay];
			double yCoord = FractalGenerator.getCoord (range.y, range.y + range.height, sizeDisplay, yCoordFW);
			for(int x = 0; x < sizeDisplay; x++) {
					double xCoord = FractalGenerator.getCoord (range.x, range.x + range.width, sizeDisplay, x);
					int num = myFractalGenerator.numIterations(xCoord, yCoord);
					if (num == -1) RGBmas[x] = 0;
					else {
						float hue = 0.7f + (float) num / 200f;
						int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
						RGBmas[x] = rgbColor;
					}
			}
			return null;
		}
		
		protected void done () {
			for(int x = 0; x < sizeDisplay; x++) {
				imageDisplay.drawPixel(x, yCoordFW, RGBmas[x]);
			}
			imageDisplay.repaint(0, 0, yCoordFW, sizeDisplay, 1);
			rowsRemaining--;
			if (rowsRemaining == 0) enableUI(true);
		}
		
	}

}
