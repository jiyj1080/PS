import java.io.*;
import java.util.*;

/*

 */

public class Solution {
	
	static int D, N;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine().trim());
			D = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			double maxTime = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				int K = Integer.parseInt(st.nextToken());
				int S = Integer.parseInt(st.nextToken());
				double time = (D - K) / (double) S;
				maxTime = Math.max(maxTime, time);
			}

			System.out.println("#" + tc + " " + D / maxTime);
		}
	}
}
