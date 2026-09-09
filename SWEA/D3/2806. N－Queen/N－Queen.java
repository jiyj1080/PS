import java.util.*;
import java.io.*;

public class Solution {
	static final int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static final int[] dc = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int n, count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			count = 0;
			n = Integer.parseInt(br.readLine());

			// solve
			dfs(0);

			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}

	static int[][] attacked = new int[10][10];

	static void dfs(int r) {
		if (r == n) {
			count++;
			return;
		}

		for (int c = 0; c < n; c++) {
			if (attacked[r][c] != 0)
				continue;

			attack(r, c);

			dfs(r + 1);

			recover(r, c);
		}
	}

	static void attack(int r, int c) {
		attacked[r][c]++;
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
				attacked[nr][nc]++;
				nr += dr[d];
				nc += dc[d];
			}
		}
	}
	
	static void recover(int r, int c) {
		attacked[r][c]--;
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
				attacked[nr][nc]--;
				nr += dr[d];
				nc += dc[d];
			}
		}
	}
}