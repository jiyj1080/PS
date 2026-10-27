import java.util.*;
import java.io.*;

class Solution {

	static int N, p[][];
	static double maxProb;

	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine().trim()); // ~ 16
			
			p = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					p[i][j] = Integer.parseInt(st.nextToken());					
				}
			}
			
			/**
			 * N = 16
			 * 완탐 16! 인데 30초로 안되나?
			 * pruning 하면 ㄱㅊ을듯? 아닌가
			 * 안되면 dp
			 * 
			 */
			
			maxProb = 0;
			used = new boolean[N];
			dfs(0, 1.0);

			System.out.printf("#%d %.6f\n", tc, maxProb * 100);
		}
	}
	
	static boolean[] used;

	private static void dfs(int index, double prob) {
		if (index == N) {
			maxProb = Math.max(maxProb, prob);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (used[i])
				continue;
			
			double nextProb = prob * (p[index][i] * 0.01);
			if (nextProb <= maxProb)
				continue;
			
			used[i] = true;
			dfs(index + 1, nextProb);
			used[i] = false;
		}
	}
}