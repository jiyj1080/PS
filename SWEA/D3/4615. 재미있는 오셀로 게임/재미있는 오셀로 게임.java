import java.util.*;
import java.io.*;

class Solution {
	static final int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static final int[] dc = { 1, 1, 0, -1, -1, -1, 0, 1 };

	static int N, M, blackCnt, whiteCnt, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			blackCnt = whiteCnt = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// initialize map
			map = new int[N][N];
			map[N / 2 - 1][N / 2 - 1] = map[N / 2][N / 2] = 2;
			map[N / 2][N / 2 - 1] = map[N / 2 - 1][N / 2] = 1;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				int color = Integer.parseInt(st.nextToken());
				map[r][c] = color;

				/**
				 * 8방 탐색하면서 2: 1 1 1 2 or 1: 2 2 2 2 1 일 시 다 뒤집기
				 */

				for (int d = 0; d < 8; d++) {
					if (filppable(r, c, d, color))
						flip(r, c, d, color);
				}
			}

			// counting
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 1)
						blackCnt++;
					else if (map[r][c] == 2)
						whiteCnt++;
				}
			}

			sb.append("#").append(tc).append(" ").append(blackCnt).append(" ").append(whiteCnt);// .append("\n")
			System.out.println(sb);
			sb.setLength(0);
		}
//		System.out.println(sb);
	}

	/**
	 * 
	 * 
	 * 1. nr, nc 계산 2.
	 */
	static boolean filppable(int r, int c, int d, int color) {
		int reverseColor = color == 1 ? 2 : 1;
		do {
			r += dr[d];
			c += dc[d];

			if (r < 0 || r >= N || c < 0 || c >= N)
				return false;
			if (map[r][c] == 0)
				return false;

		} while (map[r][c] == reverseColor);

		if (map[r][c] == color)
			return true;
		else
			return false;
	}

	static void flip(int r, int c, int d, int color) {
		do {
			map[r][c] = color;

			r += dr[d];
			c += dc[d];

		} while (map[r][c] != color);
	}
}