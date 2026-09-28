import java.util.*;
import java.io.*;

class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			
			char[] two = in.readLine().toCharArray();
			char[] three = in.readLine().toCharArray();

			A: for (int i = 0; i < two.length; i++) {
				two[i] = two[i] == '0' ? '1' : '0';
				for (int j = 0; j < three.length; j++) {
					char original = three[j];
					for (int k = 0; k < 3; k++) {
						if (original == (three[j] = (char) ('0' + k)))
							continue;
						int d1 = twoToDecimal(two);
						int d2 = threeToDecimal(three);
						if (d1 == d2) {
							answer = d1;
							break A;
						}
					}
					three[j] = original;
				}
				two[i] = two[i] == '0' ? '1' : '0';
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static int threeToDecimal(char[] three) {
		int result = 0;
		for (int i = 0; i < three.length; i++) {
			result *= 3;
			if (three[i] == '1')
				result++;
			else if (three[i] == '2')
				result += 2;
		}
		return result;
	}

	private static int twoToDecimal(char[] two) {
		int result = 0;
		for (int i = 0; i < two.length; i++) {
			result *= 2;
			if (two[i] == '1')
				result++;
		}
		return result;
	}
}