import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine().trim());
			String stringA = st.nextToken();
			String stringB = st.nextToken();
			long A = Long.parseLong(stringA); // -10 ^ 12 ~ 10 ^ 12
			long B = Long.parseLong(stringB); // -10 ^ 12 ~ 10 ^ 12
			
			
			
			/**
			 * 자릿수 별 몇 더할 지 정하기!
			 * 
			 * 1 ~ 28
			 * 2x 이니까 + 18
			 * x8  이니까 + 7
			 * => 25
			 * 
			 * 1 ~ 67
			 * 6x -> 9 * 5
			 * x7 -> 6
			 * => 51 52 53
			 * 
			 * 1 ~ 283
			 * 2xx -> 9 * 9 * 2
			 * x8x -> 9 * 7
			 * xx3 -> 3
			 * 
			 * 1 ~ 529
			 * 5xx -> 9 * 9 * 4
			 * 
			 * 
			 * 
			 * 0 1 2 3 4 5 6 7 8 
			 * 0 1 2 3 5 6 7 8 9 
			 * 
			 * 9  10 11 12 13 14 15 16 17 
			 * 10 11 12 13 15 16 17 18 19 
			 * 
			 * 18 19 20 21 22 23 24 25 26 
			 * 20 21 22 23 25 26 27 28 29
			 * 
			 * 27 28 29 30 31 32 33 34 35
			 * 30 31 32 33 35 36 37 38 39
			 * 
			 * 36
			 * 50 51 52 53 55 56 57 58 59
			 * 
			 * 45 - 60
			 * 54 - 70
			 * 63 - 80
			 * 72 - 90
			 * 81 - 100
			 * 
			 * 
			 * 0  1  2  3  5  6  7  8  9	1
			 * 10 11 12 13 15 16 17 18 19 	1
			 * 20 21 22 23 25 26 27 28 29	1
			 * 30 31 32 33 35 36 37 38 39	11
			 * 50 51
			 * ...
			 * 131 132 133 135 136 137 138 139 150
			 * 151
			 * ...
			 * 231 ... 250
			 * 251
			 * 
			 * 
			 */
			
			long aDist = getDist(stringA);
			long bDist = getDist(stringB);
			
			long answer = -1;
			if ((A > 0 && B > 0) || (A < 0 && B < 0))
				answer = Math.abs(aDist - bDist);
			else 
				answer = aDist + bDist - 1;

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static long getDist(String s) {
		long result = 0;
		int i = s.charAt(0) == '-' ? 1 : 0;
		for (; i < s.length(); i++) {
			result *= 9;
			int tmp = s.charAt(i) - '0';
			result += tmp > 4 ? tmp - 1 : tmp;
		}
		
		return result;
	}
}
