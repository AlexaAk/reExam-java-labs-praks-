import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Task2 {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		//System.out.println(oppositeHouse(scan.nextInt(), scan.nextInt()));
		//System.out.println(nameShuffle(scan.nextLine()));
		//System.out.println(discount(scan.nextDouble(), scan.nextDouble()));
		double[] mas = {10, 4, 1, 4, -10, -50, 32, 21};
		//System.out.println(differenceMaxMin(mas));
		//System.out.println(equal(scan.nextInt(), scan.nextInt(), scan.nextInt()));
		//System.out.println(reverse(scan.nextLine()));
		//System.out.println(programmers(scan.nextInt(), scan.nextInt(), scan.nextInt()));
		//System.out.println(getXO(scan.nextLine()));
		//System.out.println(bomb(scan.nextLine()));
		System.out.println(sameAscii(scan.nextLine(), scan.nextLine()));
	}
	
	public static int oppositeHouse (int num, int len) {
		len*=2;
		return len - (num - 1);
	}
	
	public static String nameShuffle (String s1) {
		String s2 = "", s3 = "";
		boolean fl = false;
		for(int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == ' ') fl = true;
			else if (!fl) s2 += s1.charAt(i);
			else s3 += s1.charAt(i);
		}
		return s3 + ' ' + s2;
	}
	
	public static double discount (double price, double disc) {
		disc = 1 - disc/100;
		return price *= disc;
	}
	
	public static double differenceMaxMin (double[] mas ) {
		double max = mas[0], min = mas[0];
		for (int i = 1; i < mas.length; i ++) {
			if (mas[i] < min) min = mas[i];
			else if (mas[i] > max) max = mas[i];
		}
		return max - min;
	}
	
	public static int equal (int a, int b, int c) {
		if(a == b && a == c) return 3;
		else if (a == b || a == c || b == c) return 2;
		else return 0;
	}
	
	public static String reverse (String s) {
		String s1 = "";
		for (int i = s.length()-1; i > 0; i --) {
			s1 += s.charAt(i);
		}
		return s1;
	}
	
	public static int programmers (int a, int b, int c) {
		int dif;
		if (a > b && a > c) 
			if (b > c) return a - c;
			else return a - b;
		if (b > a && b > c) 
			if (a>c) return b - c;
			else return b - a;
		else if(a > b) return c - b;
			else return c - a;
	}
	
	public static boolean getXO (String s) {
		s = s.toLowerCase();
		int x = 0, o = 0;
		for(int i = 0; i < s.length(); i ++)
			if(s.charAt(i) == 'x') x++;
			else if (s.charAt(i)== 'o') o++;
		return x == o;
	}
	
	public static String bomb (String s) {
		s = s.toLowerCase();
		String s1 = "bomb";
		int i = s.indexOf(s1);
		if (i == -1) return "Relax, there's no bomb.";
		else return "DUCK!";
	}
	
	public static boolean sameAscii (String a, String b) {
		int aa = 0, bb = 0;
		for (int i = 0; i < a.length(); i ++) 
			aa += a.charAt(i);
		for (int i = 0; i < b.length(); i ++) 
			bb += b.charAt(i);
		return aa == bb;
	}

}
