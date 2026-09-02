import java.io.*;
import java.util.*;

// 

public class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int[][] map = new int[50][50];
	static boolean[][] visited = new boolean[50][50];
	static int[] q = new int[50 * 50];

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			final int R = Integer.parseInt(st.nextToken());
			final int C = Integer.parseInt(st.nextToken());
			int rr = Integer.parseInt(st.nextToken());
			int cc = Integer.parseInt(st.nextToken());
			for (int r = 0; r < R; r++) {
				String s = br.readLine();
				for (int c = 0; c < C; c++) {
					map[r][c] = s.charAt(c) == '.' ? 0 : 1;
					visited[r][c] = false;
				}
			}

			int head = 0, tail = 0;
			q[tail++] = rr * 100 + cc; // RRCC
			visited[rr][cc] = true;
			while (head != tail) {
				int pos = q[head++];
				int r = pos / 100;
				int c = pos % 100;
				answer++;

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc])
						continue;
					if (map[nr][nc] == 1)
						continue;
					
					q[tail++] = nr * 100 + nc;
					visited[nr][nc] = true;
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
