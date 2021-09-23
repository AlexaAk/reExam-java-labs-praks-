import java.util.Scanner;
public class Task7 {

	public static void main (String [] args) {
		//Scanner scan = new Scanner(System.in);
		//1
		int [] boom = {7, 55, 60, 96, 86};
		System.out.println(sevenBoom(boom));
		//2
		int [] con = {5, 1, 4, 3, 2, 8};
		System.out.println(cons(con));
		//3
		System.out.println(unmix("hTsii  s aimex dpus rtni.g"));
		//4
		System.out.println(noYelling(""));
	}
	
	private static boolean has7 (int a) {
		while (a > 0) {
			if (a%10 == 7) return true;
			a /= 10;
		}
		return false;
	}
	
	public static String sevenBoom (int [] boom) {
		for (int i = 0; i < boom.length; i++)
			if (has7(boom[i])) return "Boom";
		return "there is no 7 in the array";
	}
	
	public static boolean cons (int [] mas) {
		int min = 0;
		int max = 0;
		for (int i = 0; i < mas.length; i ++) {
			if(mas[i] < mas[min]) min = i;
			else if(mas[i] > mas[max]) max = i;
		}
		int cur = min;
		int next = 0;
		while (cur <= max) {
			for (int i = 0; i < mas.length; i ++) {
				if (i!= cur && mas[i] == mas[cur]) return false;
				else if (mas[i] == mas[cur] + 1) next = i;
			}
			if (cur == next) return false;
			cur = next;	
		}		
		return true;
	}
	
	public static String unmix (String s) {
		String s1 = "";
		int max = 0;
		if (s.length() % 2 == 0) max = s.length() - 1;
		else max = s.length() - 2;
		for (int i = 0; i < max; i += 2) {
			s1 = s1 + s.charAt(i+1) + s.charAt(i);
		}
		if (max == s.length() - 2) s1 += s.charAt(s.length() - 1);
		return s1;
	}
	
	public static String noYelling (String s) {
		if (s.charAt(s.length()-1) != '?' && s.charAt(s.length()-1) != '!') return s;
		char c = s.charAt(s.length()-1);
		int end = s.length()-1; //end = s.length()-1
		for (int i = s.length()-2; i >= 0; i--)
			if (s.charAt(i) != c) {
				end = i+1; 
				break;
			}
		String s1 = "";
		for (int i = 0; i <= end; i++)
			s1 = s1 + s.charAt(i);
		return s1;
	}
	
}
