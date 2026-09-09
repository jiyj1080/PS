import java.util.*;
import java.io.*;

// 메모리:  KB, 시간:  ms

public class Solution {
	static int n, m, maxWeight;
	static int[] weight = new int[1000];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			maxWeight = -1;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}

			// solve
			dfs(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(maxWeight).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth, int start, int currentWeight) {
		if (currentWeight > m) {
			return;
		}
		
		if (depth == 2) {
			maxWeight = Math.max(maxWeight, currentWeight);
			return;
		}

		for (int i = start; i < n; i++) {
			dfs(depth + 1, i + 1, currentWeight + weight[i]);
		}
	}
}
