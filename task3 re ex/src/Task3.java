import java.util.Scanner;

public class Task3 {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		String [][] mas = {
		                    {"Nice", "942208"},
		                    {"Abu Dhabi", "1482816"},
		                    {"Naples", "2186853"},
		                    {"Vatican City", "572"}
		};
		millionsRounding(mas);
		for (int i = 0; i < 4; i ++) {
			//System.out.println(mas[i][0]+ ' ' + mas[i][1]);
		}
		
		//double[] mas2 = otherSides(scan.nextInt());
		//System.out.println(mas2[1]);
		//System.out.println(mas2[0]);
		
		//System.out.println(rps(scan.nextLine(), scan.nextLine()));
		
		int[] mas2 = {5, 9, 45, 6, 2, 7, 34, 8, 6, 90, 5, 243};
		//System.out.println(warOfNumbers(mas2));
		//System.out.println(reverseCase(scan.nextLine()));
		//System.out.println(inatorInator(scan.nextLine()));
		//System.out.println(doesBrickFit(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
		//System.out.println(totalDistance(scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.nextBoolean()));
		int[] mas3 = {2, 3, 2, 3};
		//System.out.println(mean(mas3));
		System.out.println(parityAnalysis(scan.nextInt()));
	}
	
	public static String [][] millionsRounding (String mas [][]) {
		for (int i = 0; i < mas.length; i++) {
			int num = Integer.parseInt(mas[i][1]);
			num = (int)Math.round((double)num/1000000) *1000000;
			mas[i][1] = String.valueOf(num);
		}
		return mas;
	}
	
	public static double [] otherSides (int a) {
		double[] mas = {0, 0};
		mas[1] = Math.round(a*2*100)/100;
		mas[0] = Math.round(a*Math.sqrt(3)*100)/100;
		return mas;
	}
	
	public static String rps (String s1, String s2) {
		if (s1 == "rock") 
			if (s2 == "paper") return "Player 2 wins";
			else if (s2 == "rock") return "TIE";
			else return "Player 1 wins";
		else if (s1 == "paper")
			if (s2 == "rock") return "Player 1 wins";
			else if (s2 == "paper") return "TIE";
			else return "Player 2 wins";
		else {
			if (s2 == "paper") return "Player 1 wins";
			else if (s2 == "scissors") return "TIE";
			else return "Player 2 wins";
		}
	}
	
	public static int warOfNumbers (int [] mas) {
		int sumNech = 0, sumCh = 0;
		for (int i = 0; i < mas.length; i ++) {
			if(mas[i]%2==0) sumCh+=mas[i];
			else sumNech+=mas[i];
		}
		return Math.abs(sumNech-sumCh);
	}
	
	public static String reverseCase(String s) {
		String s1 = "";
		int diff = 'a' - 'A';
		for(int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') s1+= ' ';
			else {
				char c = s.charAt(i);
				if (c >= 'a') c -= diff;
				else c += diff;
				s1+= c;
			}

		}
		return s1;
	}

	public static String inatorInator (String s) {
		int len = s.length() *1000;
		String s1 = "inator";
		char c = s.charAt(s.length()-1);
		if (c == 'a' || c == 'e' || c =='i' || c == 'o' || c == 'u' || c == 'y') return s + '-' + s1 + String.valueOf(len);
		else  return s + s1 + String.valueOf(len);
	}
	
	public static boolean doesBrickFit (int a, int b, int c, int w, int h) {
		if ((a <= w && b <= h || a <= h && b <= w) || (a <= w && c <= h || a <= h && c <= w) || (c <= w && b <= h || c <= h && b <= w)) return true;
		return false;
	}
	
	public static  double totalDistance (double litrs, double litrPerKil, double pas, boolean cond) {
		pas *= 0.05;
		litrPerKil *= 1 + pas;
		if(cond) litrPerKil *= 1.10;
		return litrs*100 / litrPerKil;
	}
	
	public static double mean (int [] mas) {
		double sum = 0;
		for (int i = 0; i < mas.length; i ++) {
			sum+=mas[i];
		}
		return sum/mas.length;
	}
	
	public static boolean parityAnalysis (int a) {
		int sum = 0, a1 = a;
		while (a1 != 0) {
			sum += a1%10;
			a1/=10;
		}
		return (a%2)==(sum%2);
	}
}
