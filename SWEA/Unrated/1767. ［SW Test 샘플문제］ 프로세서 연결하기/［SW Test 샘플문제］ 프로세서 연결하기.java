import java.util.*;
import java.io.*;

// 메모리: 28,032 KB, 시간: 165 ms

public class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int n, r, coreCnt, wireLen;
	static int[][] map = new int[12][12];
	static int[][] corePos = new int[12][2];
	static boolean[] connected = new boolean[12];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			coreCnt = 0;
			wireLen = Integer.MAX_VALUE;
			Arrays.fill(connected, false);
			n = Integer.parseInt(br.readLine());
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < n; c++) {
					int t = map[r][c] = Integer.parseInt(st.nextToken());
					if (t != 0) {
						corePos[coreCnt][0] = r;
						corePos[coreCnt][1] = c;
						if (r == 0 || c == 0 || r == n - 1 || c == n - 1) {
							connected[coreCnt] = true;
						}
						coreCnt++;
					}
				}
			}

			// n -> 0 try
			for (r = coreCnt; r >= 0; r--) {
				dfs(0, 0, 0);
				if (wireLen != Integer.MAX_VALUE)
					break;
			}

			sb.append("#").append(tc).append(" ").append(wireLen).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth, int start, int wire) {
		if (depth == r) {
			wireLen = Math.min(wireLen, wire);
			return;
		}

		for (int i = start; i < coreCnt; i++) {
			// if core on the edge search directly
			int r = corePos[i][0];
			int c = corePos[i][1];
			
			if (r == 0 || c == 0 || r == n - 1 || c == n - 1) {
				dfs(depth + 1, i + 1, wire);
				continue;
			}
			
			// not on the edge
			for (int d = 0; d < 4; d++) {
				// try to connect
				int currentWire = 0;
				int nr = r + dr[d];
				int nc = c + dc[d];
				while (nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] == 0) {
					map[nr][nc] = 2;
					currentWire++;
					nr += dr[d];
					nc += dc[d];
				}

				// if can connect
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
					dfs(depth + 1, i + 1, wire + currentWire);
				}

				// restore
				nr -= dr[d];
				nc -= dc[d];
				while (map[nr][nc] != 1) {
					map[nr][nc] = 0;
					nr -= dr[d];
					nc -= dc[d];
				}
			}
		}
	}
}
