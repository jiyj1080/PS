import java.io.*;
import java.util.*;

// 

public class Solution {
	static int n, m, minGap;
	static int[][] map = new int[16][16];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			minGap = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			m = n / 2;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// solve
			dfs(0, 0);

			sb.append("#").append(tc).append(" ").append(minGap).append("\n");
		}
		System.out.println(sb);
	}
	
	static int[] picked1 = new int[8], picked2 = new int[8];
	
	static void dfs(int depth, int start) {
		if (depth == m) {
			minGap = Math.min(minGap, calculate());
			return;
		}
		
		for (int i = start; i < n; i++) {
			picked1[depth] = i;
			dfs(depth + 1, i + 1);
		}
	}
	
	static int calculate() {
		int score1 = 0;
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				score1 += map[picked1[i]][picked1[j]] + map[picked1[j]][picked1[i]];
			}
		}

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			boolean picked = false;
			for (int j = 0; j < m; j++) {
				if (picked1[j] == i)
					picked = true;
			}
			if (!picked)
				picked2[cnt++] = i;
		}
		
		int score2 = 0;
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				score2 += map[picked2[i]][picked2[j]] + map[picked2[j]][picked2[i]];
			}
		}
		
		return Math.abs(score1 - score2);
	}
}
