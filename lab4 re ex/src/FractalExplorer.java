import java.awt.geom.Rectangle2D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FractalExplorer {
	private int sizeDisplay;
	private JImageDisplay imageDisplay;
	private FractalGenerator myFractalGenerator;
	private Rectangle2D.Double range;
	
	public static void main (String [] args) {
		FractalExplorer myFractalExplorer = new FractalExplorer(800);
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
		JButton button = new JButton();
		
		button.addActionListener(new ActionHandler());
    	imageDisplay.addMouseListener(new MouseListener());
		
		frame.setLayout(new java.awt.BorderLayout());
		frame.add(imageDisplay, BorderLayout.CENTER);
		frame.add(button,  BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack ();
		frame.setVisible (true);
		frame.setResizable (false);
	}
	
	private void  drawFractal () {
		for(int x = 0; x < sizeDisplay; x++) {
			for(int y = 0; y < sizeDisplay; y++) {
				double xCoord = FractalGenerator.getCoord (range.x, range.x + range.width, sizeDisplay, x);
				double yCoord = FractalGenerator.getCoord (range.y, range.y + range.height, sizeDisplay, y);
				int num = this.myFractalGenerator.numIterations(xCoord, yCoord);
				if (num == -1) imageDisplay.drawPixel(x, y, 0);
				else {
					float hue = 0.7f + (float) num / 200f;
					int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
					imageDisplay.drawPixel(x, y, rgbColor);
				}
			}
			imageDisplay.repaint();
		}
	}
	
	public class ActionHandler implements  ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myFractalGenerator.getInitialRange(range);
            drawFractal();
		}
	}
	
	public class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			double x = FractalGenerator.getCoord (range.x, range.x + range.width, sizeDisplay, e.getX());
			double y = FractalGenerator.getCoord (range.x, range.x + range.width, sizeDisplay, e.getY());
			myFractalGenerator.recenterAndZoomRange(range, x, y, 0.5);
			drawFractal();
		}
	}
}
