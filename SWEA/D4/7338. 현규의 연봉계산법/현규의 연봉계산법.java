import java.io.*;
import java.util.*;

/*

 */

public class Solution {
	
	static long Y;
	static int M;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine().trim());
			Y = Long.parseLong(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			
			Y -= 2016;
			Y = Y * 12 + M;
			M = (int) (Y % 13);
			Y /= 13;
			Y += 2016;
			
			if (M == 0) {
				M = 13;
				Y--;
			}

			System.out.println("#" + tc + " " + Y + " " + M);
		}
	}
}
