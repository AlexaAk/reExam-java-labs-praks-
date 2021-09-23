import java.util.Arrays;

public class Task4 {

	public static void main (String [] args) {
		
		//1
		int [] boom = {7, 55, 60, 96, 86};
		System.out.println(sevenBoom(boom));
		//2
		int [] con = {5, 1, 4, 3, 2, 8};
		//System.out.println(cons(con));
		//3
		System.out.println(unmix("hTsii  s aimex dpus rtni.g"));
		//4
		System.out.println(noYelling("Oh my goodness!"));
		//5
		System.out.println(xPronounce("OMG x box unboxing video x D"));
		//6
		int [] sor = {14, 13, 7, 1, 4, 12, 3, 7, 7, 12, 11, 5, 7};
		System.out.println(largestGap(sor));
		//7
		System.out.println(task7(1));
		//8
		System.out.println(commonLastVowel("Hello World!"));
		//9
		System.out.println(memeSum(26, 39));
		//10
		System.out.println(unrepeated("aaabbb"));
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
	
	public static String xPronounce (String s) {
		s = s.replaceAll("\sx{1}\s", "\secks\s");
		s = s.replaceAll("\sx{1}", "\sz");
		s = s.replaceAll("x{1}", "cks");
		return s;
	}
	
	public static int largestGap (int [] mas) {
		Arrays.sort(mas);
		int max = 0;
		for (int i = 0; i < mas.length - 1; i ++)
			if (mas[i+1] - mas[i] > max) max = mas[i+1] - mas[i];
		return max;
	}
	
	public static int task7 (int num) {
		int len = 0;
		int cur = num;
		while (cur != 0) {
			len ++;
			cur /= 10;
		}
		int [] mas = new int [len];
		cur = num;
		int i = 0;
		while (cur != 0) {
			mas[i] = cur % 10;
			i ++;
			cur /= 10;
		}
		Arrays.sort(mas);
		cur = 0;
		for(i = 0; i < len; i ++) {
			cur *= 10;
			cur += mas[i];
		}
		return num - cur;		
	}
	
	public static char commonLastVowel(String s) {
		s = s.toLowerCase();
		char[] ms = s.toCharArray();
		char[] vou = {'a', 'e', 'i', 'o', 'u', 'y'};
		int[] count = {0,0,0,0,0,0};
		for (int i = 0; i < ms.length; i ++) {
			for(int j = 0; j < 6; j++) 
				if(ms[i] == vou[j]) count[j]++;
		}
		int max = 0;
		for(int j = 1; j < 6; j++)
			if (count[j] > count[max]) max = j;
		return vou[max];
	}
	
	public static int memeSum (int a, int b) {
		int max, min;
		if (a > b) {
			max = a;
			min = b;
		}
		else {
			max = b;
			min = a;
		}
		int ans = 0;
		int x = 0, y = 0;
		int pow = 0;
		boolean fl = false;
		
		while (max > 0) {
			boolean fl2 = false;
			x = max % 10;
			y = min % 10;
			//System.out.println(x);
			//System.out.println(y);
			y += x;
			if (y >= 10) fl2 = true;
			if (fl) y *= Math.pow(10, pow);
			ans += y;
			//System.out.println(y);
			//System.out.println(ans);
			pow ++;
			if (fl2) pow++;
			fl = true;
			max /= 10;
			min /= 10;
		}
		return ans;
	}
	
	public static String unrepeated (String s) {
		String result = "";
		s = new StringBuilder(s).reverse().toString();
		s = s.replaceAll("(.)(?=.*\\1)", "");
		result = new StringBuilder(s).reverse().toString();
		return result;
	}
	
	
}
