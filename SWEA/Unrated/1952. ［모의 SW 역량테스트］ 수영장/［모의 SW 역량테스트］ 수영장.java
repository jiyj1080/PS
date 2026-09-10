import java.io.*;
import java.util.*;

// 메모리: 27,904 KB, 시간: 158 ms

public class Solution {
	static int minCost;
	static int[] cost = new int[4], plan = new int[12];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			minCost = cost[3];

			// solve
			dfs(0, 0);

			sb.append("#").append(tc).append(" ").append(minCost);// .append("\n");
			System.out.println(sb);
			sb.setLength(0);
		}
		System.out.println(sb);
	}
	
	static void dfs(int depth, int curCost) {
		if (depth >= 12) {
			minCost = Math.min(minCost, curCost);
			return;
		}
		
		// daily
		dfs(depth + 1, curCost + plan[depth] * cost[0]);
		
		// 1 month
		dfs(depth + 1, curCost + cost[1]);
		
		// 3 month
		dfs(depth + 3, curCost + cost[2]);
		
	}
}
