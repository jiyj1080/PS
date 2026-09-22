import java.util.*;
import java.io.*;

public class Solution {
	
	static final int[] dr = { 0, 1, 0 ,-1 };
	static final int[] dc = { 1, 0, -1, 0 };
	
	static int N, K, maxLen, map[][], topHeight;
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			maxLen = 0;
			topHeight = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 3 ≤ N ≤ 8
			K = Integer.parseInt(st.nextToken());	// 1 ≤ K ≤ 5
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int t = map[r][c] = Integer.parseInt(st.nextToken());
					topHeight = Math.max(topHeight, t);
				}
			}
			
			/** 
			 * 가장 높은 봉우리는 최대 5개
			 * 
			 * N * N <= 64
			 * 각 칸을 깎은 경우 + 안 깎은 경우 64 + 1개 완전 탐색하기
			 * 
			 * 길 찾기는? 가능한 경우 모두 dfs? current height 추적하기
			 * 
			 */
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != topHeight)
						continue;
					
					visited[r][c] = true;
					dfs(r, c, 1, false);
					visited[r][c] = false;
				}
			}

			System.out.println("#" + tc + " " + maxLen);

		}

	}
	
	static void dfs(int r, int c, int len, boolean cutted) {
		maxLen = Math.max(maxLen, len);
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
				continue;
			
			if (map[nr][nc] < map[r][c]) {
				visited[nr][nc] = true;
				
				dfs(nr, nc, len + 1, cutted);
				
				visited[nr][nc] = false;
			}
			
			else if (!cutted && map[nr][nc] - K < map[r][c]) {
				visited[nr][nc] = true;
				int original = map[nr][nc];
				map[nr][nc] = map[r][c] - 1;
				
				dfs(nr, nc, len + 1, true);
				
				visited[nr][nc] = false;
				map[nr][nc] = original;
			}
		}
	}

}
