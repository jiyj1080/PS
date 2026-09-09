import java.util.*;
import java.io.*;

// 

public class Solution {
	static int n, shortest;
	static int[][] pos = new int[12][2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			shortest = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n + 2; i++) {
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}

			// solve
			dfs(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(shortest).append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean[] visited = new boolean[12];

	static void dfs(int depth, int before, int distance) {
		if (distance > shortest) {
			return;
		}
		
		if (depth == n) {
			shortest = Math.min(shortest, distance + absSum(pos[1][0] - pos[before][0], pos[1][1] - pos[before][1]));
			return;
		}

		for (int i = 2; i < n + 2; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			dfs(depth + 1, i, distance + absSum(pos[i][0] - pos[before][0], pos[i][1] - pos[before][1]));
			visited[i] = false;
		}
	}
	
	static int absSum(int a, int b) {
		return Math.abs(a) + Math.abs(b);
	}
}
