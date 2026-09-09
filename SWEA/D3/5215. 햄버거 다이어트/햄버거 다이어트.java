import java.util.*;
import java.io.*;

// 

public class Solution {
	static int n, l, maxScore;
	static int[] t = new int[20], k = new int[20];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			maxScore = 0;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				t[i] = Integer.parseInt(st.nextToken());
				k[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int depth, int score, int kcal) {
		if (kcal > l)
			return;
		
		if (depth == n) {
			maxScore = Math.max(maxScore, score);
			return;
		}
		
		// used
		dfs(depth + 1, score + t[depth], kcal + k[depth]);
		// not used
		dfs(depth + 1, score, kcal);
	}
}
