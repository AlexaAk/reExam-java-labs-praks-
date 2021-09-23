import java.awt.geom.Rectangle2D.Double;

public class Tricorn extends FractalGenerator {
	public static final int MAX_ITERATIONS = 2000;

	@Override
	public void getInitialRange(Double range) {
		// TODO Auto-generated method stub
		range.x = -2;
		range.y = -2;
		range.height = 4;
		range.width = 4;
	}

	@Override
	public int numIterations(double x, double y) {
		// TODO Auto-generated method stub
		double i = y;
		double x1 = x;
		int num = 0;
		while (num < MAX_ITERATIONS) {
			num++;
			double a = x1*x1 - i*i + x;
			double b = (-2)*x1*i + y;
			x1 = a;
			i = b;
			if (x1*x1 + i*i > 4)
				break;
		}
		if (num == MAX_ITERATIONS) return -1;
		return num;
	}
	
	@Override 
	public String toString () {
		return "Tricorn";
	}

}
