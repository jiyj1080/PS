import java.io.*;
import java.util.*;

public class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int n, start, count;
	static int[][] map = new int[1000][1000];
	static boolean[][] visited = new boolean[1000][1000];

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());

			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					visited[r][c] = false;
				}
			}

			// solve
			int answer_start = Integer.MAX_VALUE, answer_count = 0;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (visited[r][c])
						continue;
					visited[r][c] = true;

					start = map[r][c];
					count = 1;
					dfs(r, c);

					if (count > answer_count) {
						answer_count = count;
						answer_start = start;
					} else if (count == answer_count) {
						answer_start = Math.min(answer_start, start);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(answer_start).append(" ").append(answer_count);
			System.out.println(sb);
			sb.setLength(0);
		}
	}

	static void dfs(int r, int c) {
		int num = map[r][c];

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc])
				continue;

			// to lower
			if (map[nr][nc] == num - 1) {
				visited[nr][nc] = true;
				start = num - 1;
				count++;
				dfs(nr, nc);
			}
			// to upper
			else if (map[nr][nc] == num + 1) {
				visited[nr][nc] = true;
				count++;
				dfs(nr, nc);
			}
		}
	}
}