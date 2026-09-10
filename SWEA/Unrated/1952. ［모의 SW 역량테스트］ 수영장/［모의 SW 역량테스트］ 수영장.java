import java.io.*;
import java.util.*;

// 

public class Solution {
	static int minCost, nonZero;
	static int[] cost = new int[4], plan = new int[12];
	static boolean[] exist = new boolean[12];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			minCost = 0;
			nonZero = 0;
			Arrays.fill(exist, false);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				int t = plan[i] = Integer.parseInt(st.nextToken());
				minCost += t;
				if (t != 0) {
					exist[i] = true;
					nonZero++;
				}
			}
			minCost *= cost[0];

			// solve
			dfs(0, 0, minCost);

			// yearly
			minCost = Math.min(minCost, cost[3]);

			sb.append("#").append(tc).append(" ").append(minCost);// .append("\n");
			System.out.println(sb);
			sb.setLength(0);
		}
		System.out.println(sb);
	}

	static void dfs(int cnt, int start, int curCost) {
		if (cnt == nonZero) {
			minCost = Math.min(minCost, curCost);
			return;
		}

		for (int i = start; i < 12; i++) {
			if (plan[i] == 0)
				continue;
			// daily
			dfs(cnt + 1, i + 1, curCost);

			// 1 month
			int subtractCost = plan[i] * cost[0];
			dfs(cnt + 1, i + 1, curCost + cost[1] - subtractCost);

			// 3 month
			int tmp = 1;
			if (i + 1 < 12 && plan[i + 1] != 0) {
				subtractCost += plan[i + 1] * cost[0];
				tmp++;
			}
			if (i + 2 < 12 && plan[i + 2] != 0) {
				subtractCost += plan[i + 2] * cost[0];
				tmp++;
			}
			dfs(cnt + tmp, i + 3, curCost + cost[2] - subtractCost);

		}
	}
}
