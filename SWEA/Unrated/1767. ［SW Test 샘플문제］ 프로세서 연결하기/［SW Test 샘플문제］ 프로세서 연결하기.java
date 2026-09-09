import java.util.*;
import java.io.*;

public class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };
	
	static int n, coreCnt, connectedCnt, maxConnectedCnt, wireLen;
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
			connectedCnt = 0;
			wireLen = 0;
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
							connectedCnt++;
							connected[coreCnt] = true;
						}
						coreCnt++;
					}
				}
			}
			maxConnectedCnt = connectedCnt;
			
			dfs(0, connectedCnt, 0);

			sb.append("#").append(tc).append(" ").append(wireLen).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int depth, int connectCnt, int wire) {
		// pruning
		if (connectCnt + (coreCnt - depth) < maxConnectedCnt) 
			return;
		
		if (depth == coreCnt) {
			if (connectCnt > maxConnectedCnt) {
				maxConnectedCnt = connectCnt;
				wireLen = wire;
			}
			else if (connectCnt == maxConnectedCnt) {
				wireLen = Math.min(wireLen, wire);
			}
			return;
		}
		
		// if already connected (on the edge of the map)
		if (connected[depth]) {
			dfs(depth + 1, connectCnt, wire);
			return;
		}
		
		// connect for 4 directions
		connected[depth] = true;
		for (int d = 0; d < 4; d++) {
			// try to connect
			int currentWire = 0;
			int r = corePos[depth][0];
			int c = corePos[depth][1];
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
				dfs(depth + 1, connectCnt + 1, wire + currentWire);
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
		connected[depth] = false;
		
		// skip connecting
		dfs(depth + 1, connectCnt, wire);
	}
}
