import java.util.Scanner;
public class Lab2 {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("1st point");
		Point3d point1 = new Point3d(scan.nextDouble(), scan.nextDouble(), scan.nextDouble());

		System.out.println("2nd point");
		Point3d point2 = new Point3d(scan.nextDouble(), scan.nextDouble(), scan.nextDouble());

		System.out.println("3rd point");
		Point3d point3 = new Point3d(scan.nextDouble(), scan.nextDouble(), scan.nextDouble());
		if (point1.equals(point2) || point1.equals(point3) || point2.equals(point3))
			System.out.println("equal points!");
		else System.out.println(computeArea(point1, point2, point3)); 
	}
	
	public static double computeArea (Point3d A, Point3d B, Point3d C) {
		double AB = A.distanceTo(B);
		double AC = A.distanceTo(C);
		double BC = B.distanceTo(C);
		double p = (AB + AC + BC) /2;
		double s = p*(p - AB)*(p - AC)*(p-BC);
		return Math.sqrt(s);
	}
}
