
public class Point3d {
	private double xCoord;
	private double yCoord;
	private double zCoord;

	public Point3d (double x, double y, double z) {
		xCoord = x;
		yCoord = y;
		zCoord = z;
	}
	
	public Point3d () {
		xCoord = 0;
		yCoord = 0;
		zCoord = 0;
	}
	
	public double getX () {
		return xCoord;
	}
	
	public double getY () {
		return yCoord;
	}

	public double getZ () {
		return zCoord;
	}
	
	public void setX ( double val) {
		xCoord = val;
	}
	
	public void setY ( double val) {
		yCoord = val;
	}
	
	public void setZ ( double val) {
		zCoord = val;
	}
	
	public double distanceTo (Point3d point) {
		double dist = Math.sqrt(Math.pow(point.getX() - xCoord, 2) + Math.pow(point.getY() - yCoord, 2) + Math.pow(point.getZ() - zCoord, 2));
		dist = Math.round(dist*100)/100;
		return Math.abs(dist);
	}
	
	public boolean equals (Point3d p) {
		if (p.getX() != xCoord) return false;
		if (p.getY() != yCoord) return false;
		if (p.getZ() != zCoord) return false;
		return true;
	}
}
