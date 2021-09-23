import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task5 {
	public static void main (String [] args) {
		System.out.println(sameLetterPattern("FFFF", "ABCD"));
		//2
		System.out.println(spiderVsFly("B2", "H4"));
		//3
		System.out.println(digitsCount(1289396387328L));
		//4
		String [] mas = {"dote", "dotes", "toes", "set", "dot", "dots", "sted"};
		System.out.println(totalPoints(mas, "tossed"));
		//5
		int [] mas1 = {5, 4, 2, 1};
		System.out.println(longestRun(mas1));
		//6
		int [] mas2 = {95,83,90,87,88,93};
		System.out.println(takeDownAverage(mas2));
		//7
		System.out.println(rearrange("the4 t3o man5 Happ1iest of6 no7 birt2hday steel8!"));
		//8
		System.out.println(maxPossible(8732, 91255));
		//9
		System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
		//10
		System.out.println(isNew(1203));
	}
	
	public static boolean sameLetterPattern (String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		int [][] mas1 = new int [s1.length()] [s1.length()];
		boolean [] checkedS1 = new boolean [s1.length()];
		boolean [] checkedS2 = new boolean [s1.length()];
		
		for(int i = 0; i < s1.length(); i ++) {
			checkedS1[i] = false;
			checkedS2[i] = false;
			for(int j = 0; j < s1.length(); j++)
				mas1[i][j] = -1;
		}
		// if mas1[i][all] = -1 then s1[i] letter is unique
		for(int i = 0; i < s1.length() - 1; i++) {
			if(checkedS1[i]) continue;
			checkedS1[i] = true;
			int index = 0;
			for(int j = i+1; j < s1.length(); j++)
				if(s1.charAt(i) == s1.charAt(j)) {
					mas1[i][index] = j;
					index++;
					checkedS1[j] = true;
				}
		}
		
		for(int i = 0; i < s1.length(); i++) {
			if(checkedS2[i]) continue;
			checkedS1[i] = true;
			for(int j = 0; j < s1.length(); j++) {
				if(mas1[i][j] == -1) break;
				if(s2.charAt(i) != s2.charAt(mas1[i][j])) return false;
				else checkedS2[mas1[i][j]] = true;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> dAlg(double[][] weights, int start, int end) 
	{
        double[] dist = new double[weights.length]; 
        Arrays.fill(dist, Double.POSITIVE_INFINITY); 
        dist[start] = 0; 
        boolean[] used = new boolean[weights.length]; 
        Arrays.fill(used, false); 
        double min_dist = 0;
        int min_vertex = start;
        int[] p = new int[weights.length + 1];
        p[weights.length] = -1;
        p[start] = weights.length;
        while (min_dist < Double.POSITIVE_INFINITY)
        {
            int i = min_vertex;
            used[i] = true;
            for (int j = 0; j < dist.length; ++j)
                if (dist[i] + weights[i][j] < dist[j]) 
                {
                    dist[j] = dist[i] + weights[i][j];
                    p[j] = i;
                }


            min_dist = Double.POSITIVE_INFINITY;
            for (int j = 0; j < dist.length; ++j)
                if (!used[j] && dist[j] < min_dist)
                {
                    min_dist = dist[j];
                    min_vertex = j;
                }
        }
        ArrayList<Integer> path = new ArrayList<>();
        while (end != -1)
        {
            path.add(end);
            end = p[end];
        }
        return path;
    }
	
	public static String spiderVsFly(String a, String v) {

        int startI = Character.digit(a.charAt(1), 10);
        int startJ = a.charAt(0) - 65; 
        int endI = Character.digit(v.charAt(1), 10);
        int endJ = v.charAt(0) - 65;
        double[][] web = new double[40][40];
        for (int i = 0; i < 40; i++) 
        {
            for (int j = 0; j < 40; j++)
            {
                web[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        double k = Math.PI/4;
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (j < 7) 
                {
                    web[i * 8 + j][i * 8 + j + 1] = i * k;
                    web[i * 8 + j + 1][i * 8 + j] = i * k;
                }
                else
                {
                    web[i * 8][i * 8 + j] = i * k;
                    web[i * 8 + j][i * 8] = i * k;
                }
                if (i < 4) 
                {
                    web[i * 8 + j][(i + 1) * 8 + j] = 1;
                    web[(i + 1) * 8 + j][i * 8 + j] = 1;
                }
            }
        }
        ArrayList<Integer> res = dAlg(web, startI * 8 + startJ, endI * 8 + endJ);
        StringBuilder sb = new StringBuilder();
        for (int i = res.size() - 2; i >= 0; i--)
        {
            sb.append((char) (res.get(i) % 8 + 65)).append(res.get(i) / 8).append('-');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString().replaceAll("[A-H]0.+[A-H]0", "A0");
    }
	
	public static int recursion (long num, int len) {
		if(num == 0) return len;
		num /= 10;
		len ++;
		return recursion(num, len);
	}
	public static int digitsCount(long l){
		return recursion(l, 0);
	}
	
	
	public static int totalPoints (String [] mas, String s) {
		int [][] alph = new int [1+mas.length][26];
		for(int i = 0; i < s.length(); i++) {
			alph[0][i] = 0;
		}
		for(int i = 0; i < s.length(); i++) {
			alph[0][s.charAt(i)-'a']++;
		}
		int total = 0;
		for(int x = 0; x < mas.length; x++) {
			if(mas[x].length() > 6) continue;
			for(int i = 0; i < 26; i ++) 
				alph[1][i] = 0;
			for(int i = 0; i < mas[x].length(); i++)
				alph[1][mas[x].charAt(i)-'a']++;
			boolean fl = true;
			for(int i = 0; i < 26; i ++)
				if(alph[1][i] > alph[0][i]) fl = false;
			if (fl)
				switch (mas[x].length()) {
					case 3: {
						total += 1;
						break;
					}
					case 4: {
						total += 2;
						break;
					}
					case 5: {
						total += 3;
						break;
					}
					default: {
						total += 54;
						break;
					}
				}
		}
		return total;
	}
	
	public static int longestRun (int [] mas) {
		int max = 1;
		int qur = 0;
		int index = 0;
		int ibeg = 0;
		int iend = 0;
		boolean incr = false;
		while (index < mas.length -2) {
			ibeg = index;
			iend = index + 1;
			if (mas[iend] == mas[ibeg] + 1) incr = true;
			else if (mas[iend] == mas[ibeg]-1) incr = false;
			else {
				index++;
				continue;
			}
			while (index < mas.length - 2) {
				index++;
				if(incr) {
					if(mas[index + 1] == mas[index] +1)
						iend++;
					else break;
				}
				else {//decr
					if(mas[index + 1] == mas[index] -1)
						iend++;
					else break;
				}
			}
			qur = iend - ibeg + 1;
			if(max < qur) max = qur;
		}
		return max;
	}
	
	public static int takeDownAverage (int [] mas) {
		double qurSum = 0;
		for(int i = 0; i < mas.length; i ++)
			qurSum+= mas[i];
		double qurMiddle = qurSum/mas.length;
		double newMiddle = qurMiddle - 5;
		double newSum = newMiddle * (mas.length + 1);
		int ans = (int) (newSum - qurSum);
		return ans;
	}
	
	public static String rearrange (String s) {
		if (s == " ") return "";
		String [] mas = s.split(" ");
		String [] mas2 = new String [mas.length];
		for(int i = 0; i < mas.length; i++) {
			for(int j = 0; j < mas[i].length(); j++) {
				if(mas[i].charAt(j) >= '0' && mas[i].charAt(j) <= '9') {
					String num = "" + mas[i].charAt(j);
					mas2[Integer.parseInt(num) -1] = mas[i].replace(num, "");
				}
			}
		}
		
		return String.join(" ", mas2);
	}
	
	public static int maxPossible (int a, int b) {
		int alen = String.valueOf(a).length();
		int blen = String.valueOf(b).length();
		int [] mas = new int [alen];
		int [] mbs = new int [10];
		int aq = a;
		for(int i = alen-1; i >= 0; i--) {
			mas[i] = aq % 10;
			aq /= 10;
		}
		aq = b;
		for(int i = 0; i < blen; i ++) {
			mbs[aq % 10]++;
			aq /= 10;
		}
		
		for(int i = 0; i < alen; i++) {
			for(int j = 9; j > mas[i]; j --) {
				if(mbs[j] > 0) {
					mas[i] = j;
					mbs[j] --;
				}
			}
		}
		int ans = 0;
		for(int i = 0; i < alen; i++) {
			ans = ans*10 + mas[i];
		}
		return ans;
	}
	
	public static String timeDifference (String town1, String fulldate, String town2) {
		fulldate = fulldate.replaceAll(",", "");
		fulldate = fulldate.replaceAll(":", " ");
		String[] mas = fulldate.split(" ");
		String  month = mas[0];
		int date = Integer.parseInt(mas[1]);
		int year = Integer.parseInt(mas[2]);
		int hours = Integer.parseInt(mas[3]);
		int minutes = Integer.parseInt(mas[4]);
		String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		String[] towns = {"Los Angeles","New York","Caracas","Buenos Aires","London","Rome","Moscow","Tehran","New Delhi","Beijing","Canberra"};
		int[] townsTime = {-480,-300,-270,-180,0,60,180,210,330,480,600};
		int indA = 0, indB = 0, indM = 0;
		for(int i = 0; i < 11; i++) {
			if (towns[i] == town1) indA = i;
			if (towns[i] == town2) indB = i;
		}
		for(int i = 0; i < 11; i++) {
			if (months[i] == month) indM = i;
		}
		int diff = Math.max(townsTime[indA], townsTime[indB]) - Math.min(townsTime[indA], townsTime[indB]);
		Calendar calendar = new GregorianCalendar(year, indM, date);
		calendar.set(Calendar.HOUR, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.add(Calendar.MINUTE, diff);
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-M-d HH:mm");
		String ans = format1.format(calendar.getTime());
		return ans;
	}
	
	public static boolean isNew (int a) {
		String sa = String.valueOf(a);
		int[] mas = new int [10];
		for(int i = 0; i < 10; i++)
			mas[i] = 0;
		for(int i = 0; i < sa.length(); i++) {
			mas[sa.charAt(i) - '0']++;
		}
		boolean returnTo0 = false;
		if (mas[0] > 0) returnTo0 = true;
		int qurLen = 0;
		int ans = 0;
		while (qurLen != sa.length()) {
			int beg = 0;
			if(returnTo0) beg = 1;
			for(int i = beg; i < 10; i++) {
				if(mas[i] != 0) {
					ans = ans*10 + i;
					mas[i]--;
					qurLen ++;
					break;
				}
			}
			returnTo0 = false;
		}
		return a == ans;
	}
}
