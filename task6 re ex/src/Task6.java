import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
	public static void main (String [] args) {
		System.out.println(hiddenAnagram("D e b90it->?$ (c)a r...d,,#~", "bad credit"));
		//2
		String[] ans2 = collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15);
		for(int i = 0; i < ans2.length; i++) {
			System.out.print(ans2[i]);
			System.out.print(" ");
		}
		System.out.println();
		//3
		System.out.println(nicoCipher("andiloveherso", "tesha"));
		//4
		int [] mas = {1, 2, -1, 4, 5, 6, 10, 7};
		int [] ans = twoProduct(mas, 20);
		System.out.print(ans[0]);
		System.out.print(' ');
		System.out.println(ans[1]);
		//5
		int [] masss = isExact(24);
		System.out.print(masss[0]);
		System.out.print(' ');
		System.out.println(masss[1]);
		//6
		System.out.println(fractions("0.1097(3)"));
		//7
		System.out.println(pilish_string("CANIMAKEAGUESSNOW"));
		//8
		System.out.println(generateNonconsecutive(4));
		//9
		System.out.println(isValid("abcdefghhgfedecba"));
		//10
		int[] mas1 = {1, 6, 5, 4, 8, 2, 3, 7};
		System.out.println(sumsUp(mas1));
	}
	
	public static String hiddenAnagram (String s, String anagram) {
		s = s.toLowerCase();
		anagram = anagram.toLowerCase();
		s = s.replace(" ", "");
		anagram = anagram.replace(" ", "");
		int index = 0;
		String s1 = "";
		for(int i = 0; i < s.length(); i++)
			if(s.charAt(i)>= 'a' && s.charAt(i) <= 'z') 
				s1 = s1 + s.charAt(i);
		int [] anagramLetters = new int [26];
		for(int i = 0; i < 26; i++)
			anagramLetters[i] = 0;
		for(int i = 0; i < anagram.length(); i++) 
			anagramLetters[anagram.charAt(i)-'a']++;
			
		int [] newMas = new int [26];
		for(int beg = 0; beg < s1.length() - anagram.length()+1; beg++) {
			for(int i = 0; i < 26; i++)
				newMas[i] = 0;
			for(int i = beg; i < beg + anagram.length(); i++) {
				newMas[s1.charAt(i)-'a']++;
			}
			if (Arrays.equals(anagramLetters, newMas)) return s1.substring(beg, beg + anagram.length());
		}

		return "noutfond";
	}
	
	public static String [] collect (String s, int len_rez) {
		String [] ans;
		if(s.length() < len_rez) {
			ans = null;
		}
		else {
			ans = new String [s.length()/len_rez];
			ans[0] = s.substring(0, len_rez);
			String[] ans_else = collect (s.substring(len_rez), len_rez);
			if(ans_else != null) {
				for(int i = 1; i < ans.length; i++) {
					ans[i] = ans_else[i-1];
				}
			}
			Arrays.sort(ans);
		}
		return ans;
	}
	
	public static String nicoCipher (String s, String key) {
		int [] code = new int [key.length()];
		int [] sorted_code =  new int [key.length()]; //in indexes
		int index = 1, index_key = 0;
		for(char i = 'a'; i <= 'z'; i++) {
			for(int j = 0; j < key.length(); j++) {
				if(key.charAt(j) == i) {
					code[j] = index;
					sorted_code[index-1] = j;
					index++;
				}
			}
		}
		int strings = (int) Math.ceil((double)s.length()/key.length());
		char [][] mas = new char [strings+1][key.length()];
		for(int i = 0; i < strings+1; i++) {
			for(int j = 0; j < key.length(); j++) {
				mas[i][j] = ' ';
			}
		}
		for(int i = 0; i < key.length(); i++)
			mas[0][i] = (char) ((char) code[i] +'0');
		index = 0;
		for(int i = 1; i < strings+1; i++) {
			for(int j = 0; j < key.length(); j++) {
				mas[i][j] = s.charAt(index);
				index++;
				if(index == s.length()) break;
			}
			if(index == s.length()) break;
		}

		char [][] sorted_mas = new char [strings+1][key.length()];
		
		
		for(int j = 0; j < key.length(); j++) {
			int the_real_j = sorted_code[j];
			for(int i = 0; i < strings + 1; i++) {
				sorted_mas[i][j] = mas[i][the_real_j];
			}
		}
		String ans = "";
		for(int i = 1; i < strings + 1; i++)
			for (int j = 0; j < key.length(); j++)
				ans = ans + sorted_mas[i][j];

		return ans;
	}
	
	public static int [] twoProduct (int [] mas, int num) {
		int [] ans = {0,0};
		int [][] divider = new int [5][num+1];
		for(int i = 0; i < 5; i ++)
			for(int j = 0; j < num+1; j++)
				divider[i][j] = -1;
		int index = 0;
		for(int i = 1; i <= num; i ++) {
			if(num % i == 0) {
				divider[0][index] = i;
				divider[1][index] = num/i;
				index++;
			}
		}

		for(int x = 0; x < mas.length; x++) {
			int theJ = -1;
			for(int j = 0; j < num+1; j ++) {
				if(mas[x] == divider[0][j]) {
					divider[2][j] = x;
					theJ = j;
					break;
				}
			}
			if(theJ != -1 && x+1 != mas.length) {
				for(int x1 = x+1; x1 < mas.length; x1++) {
					if(mas[x1] == divider[1][theJ]) {
						divider[3][theJ] = x1;
						break;
					}
				}
			}
		}
		int maxmin = -1;
		for(int j = 0; j < num+1; j ++) {
			if(divider[2][j] != -1 && divider[3][j] != -1) divider[4][j] = divider[2][j] - divider[3][j];
			else divider[4][j] = 0;
			if (divider[4][j] < 0) {
				if(maxmin == -1) maxmin = j;
				else if (divider[4][j] > divider[4][maxmin]) maxmin = j;
			}
		}
		ans[0] = mas[divider[2][maxmin]];
		ans[1] = mas[divider[3][maxmin]];
		
		return ans;
	}
	
	public static int isFractal(int val, int k) 
	{
		if(val==1) 
			return k-1;
		if(val%k!=0)
			return -1;
		return isFractal(val/k,k+1);
	}
	
	public static int [] isExact (int a) {
		int [] ans;
		int val = isFractal(a, 2);
		if (val == -1) {
			ans = new int[0];
		}
		else {
			ans = new int[] {a, val};
		}
		return ans;		
	}
	
	public static String fractions (String period) {
		Pattern pattern1 = Pattern.compile("(\\d?)\\.(\\d+)\\((\\d+)\\)");
		Pattern pattern2 = Pattern.compile("(\\d?)\\.\\((\\d+)\\)");
		Matcher matcher1 = pattern1.matcher(period);
		Matcher matcher2 = pattern2.matcher(period);
		int chisl = 0;
		int znam = 0;
		if(matcher1.find()) {
			String Snum1 = matcher1.group(1);
			String Snum2 = matcher1.group(2);
			String Snum3 = matcher1.group(3);
			int num1 = Integer.parseInt(Snum1);
			int num2 = Integer.parseInt(Snum2);
			int num3 = Integer.parseInt(Snum3);
			//znam
			String Sznam = "9".repeat(Snum3.length()) + "0".repeat(Snum2.length());
			znam =  Integer.parseInt(Sznam);
			//chisl
			String Schisl = Snum2+Snum3;
			chisl = Integer.parseInt(Schisl) - num2;
			if (num1 != 0)
				chisl += num1*znam;
		}			
		else if (matcher2.find()) {
			String Snum1 = matcher2.group(1);
			String Snum3 = matcher2.group(2);
			int num1 = Integer.parseInt(Snum1);
			int num3 = Integer.parseInt(Snum3);
			//znam
			String Sznam = "9".repeat(Snum3.length());
			znam = Integer.parseInt(Sznam);
			//chisl
			chisl = num3;
			if (num1 != 0)
				chisl += num1*znam;
		}
		int max = 0;
		if(chisl > znam) max = chisl;
		else max = znam;
		boolean fl = true;
		while (fl) {
			fl = false;
			for(int i = 2; i < max; i++) {
				//System.out.println(i);
				if (chisl%i == 0 && znam%i == 0) {
					chisl/=i;
					znam/=i;
					fl = true;
					break;
				}
			}
			if (fl == false) break;
		}
		String ans = String.valueOf(chisl) + "/" + String.valueOf(znam);
		return ans;
	}
	
	public static String pilish_string (String s) {
		if (s == "")return "";
		String pi = "314159265358979";
		//Pattern pattern = Pattern.compile(s);
		int[] lengMas = {3, 4, 8, 9, 14, 23, 25, 31, 36, 39, 44, 52, 61, 68, 77};
		int len = s.length();
		int lenInd = 0;
		if(len >= 77) {
			len = 77;
			s = s.substring(0, 78);
			lenInd = 14;
		}
		else for(int i = 1; i < 15; i++) {
			if(len > lengMas[i-1] && len <lengMas[i]) {
				String last = ""+ s.charAt(s.length() - 1);
				s = s + last.repeat(lengMas[i] - len);
				lenInd = i;
				break;
			}
		}
		int[] piMas = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9};
		StringBuilder s1 = new StringBuilder("");
		int qur = 0;
		int next = 0; //index
		while (qur < len) {
			//if (next>lenInd) s1.append(s.substring(qur, lengMas[next]));
			//else 
			s1.append(s.substring(qur, lengMas[next]) + ' ');
			qur = lengMas[next];
			next ++;
		}
		s1.deleteCharAt(s1.length()-1);
		s = s1.toString();
		return s;
	}
	
	public static StringBuilder toBin (int num, int len) {
		StringBuilder s = new StringBuilder("");
		if (num == 0)
			s.append("0");
		while (num != 0) {
			s.append(num%2);
			num/=2;
		}
		if(s.length() != len) {
			s.append("0".repeat(len-s.length()));
		}
		s.reverse();
		//System.out.println(s);
		return s;
	}
	
	public static String generateNonconsecutive (int len) {
		StringBuilder s = new StringBuilder("");
		int num = (int) Math.pow(2, len);
		Pattern pattern = Pattern.compile("\\d*11");
		StringBuilder ans = new StringBuilder("");
		for(int i = 0; i < num; i++) {
			s = toBin(i, len);
			Matcher matcher = pattern.matcher(s);
			if (!matcher.find())
				ans.append(s.toString() + ' ');
		}
		ans.deleteCharAt(ans.length()-1);
		return ans.toString();
	}
	
	public static String isValid (String s) {
		int [] mas = new int [26];
		for(int i = 0; i < 26; i++)
			mas[i] = 0;
		for(int i = 0; i < s.length(); i++)
			mas[s.charAt(i)-'a']++;	
		Arrays.sort(mas);
		boolean check = false;
		int min = 0;
		int max = 0;
		for(int i = 0; i < 26; i++) {
			if (mas[i] != 0 && min == 0) {
				check = true;
				min = mas[i];
				max = mas[25];
				if (max == min) return "YES";
				continue;
			}
			if (check) {
				if (mas[i] != min && mas[i] != max) 
					return "NO";
				if (mas[i] == max && i != 25)
					return "NO";
			}
		}
		return "YES";
	}
	
	public static String sumsUp (int [] mas) {
		StringBuilder ans = new StringBuilder("[");
		List<int[]> list = new ArrayList<int[]>();
		for(int i = 0; i < mas.length - 1; i++) {
			for(int j = i + 1; j < mas.length; j++) {
				if (mas[i] + mas[j] == 8) {
					int[] pair = {Math.min(mas[i], mas[j]), Math.max(mas[i], mas[j])};
					list.add(pair);
				}
			}
		}
		for(int[] element:list) {
			ans.append(Arrays.toString(element) + ' ');
		}
		if (ans.length() != 1)ans.deleteCharAt(ans.length() - 1);
		ans.append("]");
		return ans.toString();
	}
	
}
