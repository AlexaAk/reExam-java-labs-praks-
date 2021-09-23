import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;

public class JImageDisplay extends JComponent {
	private static BufferedImage image;
	
	public JImageDisplay (int width, int length) {
		image = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
		Dimension d = new Dimension(length, width);
		setPreferredSize(d);
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
	}
	
	public void clearImage () {
		for(int i = 0; i < image.getHeight(); i ++)
			for(int j = 0; j < image.getWidth(); j ++)
				drawPixel(i, j, 0);
	}
	
	public void drawPixel (int x, int y, int rgbColor) {
		image.setRGB(x, y, rgbColor);
	}
}
