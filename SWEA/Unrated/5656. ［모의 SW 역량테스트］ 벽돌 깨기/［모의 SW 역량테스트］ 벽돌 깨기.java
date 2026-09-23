import java.util.*;
import java.io.*;

public class Solution {

	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int N, W, H, minLeft, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			minLeft = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 4
			W = Integer.parseInt(st.nextToken()); // 2 ≤ W ≤ 12
			H = Integer.parseInt(st.nextToken()); // 2 ≤ H ≤ 15

			map = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					int t = map[r][c] = Integer.parseInt(st.nextToken());
					if (t != 0)
						minLeft++;
				}
			}

			/**
			 * <<<중복 순열 완전 탐색>>>
			 * 
			 * W ^ N 개의 경우의 수 = 12 ^ 4 = 20,736
			 * 
			 * 벽돌은 !!!동시!!!에 부서짐 벽돌 부술 때에는 boolean isBroken 만들어서 재귀로 다 부순 다음, not broken 만
			 * 남기기
			 * 
			 * 재귀마다 H * W 배열 만들어야 함 15 * 12 = 180 180 * 4 = 720B 720B * N (=4) = 2880B -> 재귀
			 * 타도 메모리 문제 X
			 * 
			 */

			dfs(0, minLeft, map);

			System.out.println("#" + tc + " " + minLeft);
		}
	}

	static boolean[][] isBroken;

	static void dfs(int depth, int left, int[][] currentMap) {
		if (depth == N) {
			minLeft = Math.min(minLeft, left);
			return;
		}

		for (int c = 0; c < W; c++) {
			isBroken = new boolean[H][W];

			// c-th column 에 구슬 떨어트려서 벽돌 부수기
			int r = 0;
			while (r < H && currentMap[r][c] == 0)
				r++;

			if (r == H) {
				dfs(depth + 1, left, currentMap);
				continue;
			}

			// r, c 에 구슬이 떨어진다. -> isBroken 갱신
			breaking(r, c, currentMap);

			// isBroken 맞춰서 부수기
			int breakCnt = 0;
			int[][] nextMap = new int[H][W];
			for (int cc = 0; cc < W; cc++) {
				int idx = H - 1;
				for (int rr = H - 1; rr >= 0; rr--) {
					if (isBroken[rr][cc]) {
						breakCnt++;
						continue;
					}

					nextMap[idx--][cc] = currentMap[rr][cc];
				}
			}

			// 탐색
			dfs(depth + 1, left - breakCnt, nextMap);
		}
	}

	static void breaking(int r, int c, int[][] currentMap) {
		isBroken[r][c] = true;

		for (int i = 1; i < currentMap[r][c]; i++) {
			for (int d = 0; d < 4; d++) {
				int nr = r + i * dr[d];
				int nc = c + i * dc[d];

				if (!isIn(nr, nc))
					continue;

				if (currentMap[nr][nc] == 0)
					continue;

				if (isBroken[nr][nc])
					continue;

				breaking(nr, nc, currentMap);
			}
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
