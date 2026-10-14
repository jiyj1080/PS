import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int N, map[][], wormHoles[][][] = new int[5][2][2];

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			for (int i = 0; i < 5; i++) {
				wormHoles[i][0][0] = -1;
			}
			int maxScore = 0;
			N = Integer.parseInt(in.readLine().trim());

			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					int t = map[r][c] = Integer.parseInt(st.nextToken());
					// 웜홀
					if (t > 5) {
						int idx = t - 6;
						if (wormHoles[idx][0][0] == -1) {
							wormHoles[idx][0][0] = r;
							wormHoles[idx][0][1] = c;
						} else {
							wormHoles[idx][1][0] = r;
							wormHoles[idx][1][1] = c;
						}
					}
				}
			}

			/**
			 * 완전 탐색
			 * 
			 * 출발 위치로 돌아오거나, 블랙홀 빠질 시 끝
			 * 점수: 블록 or 벽에 부딫힌 횟수
			 * 
			 * 
			 */

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != 0)
						continue;

					for (int d = 0; d < 4; d++) {
						maxScore = Math.max(maxScore, getScore(r, c, d));
					}
				}
			}

			System.out.println("#" + tc + " " + maxScore);
		}
	}

	static int getScore(int startR, int startC, int startD) {
		int score = 0;
		int r = startR, c = startC, d = startD;
		do {
			int nr = r + dr[d];
			int nc = c + dc[d];
//			if (map[r][c] == 0 || map[r][c] > 5) {
//				nr += dr[d];
//				nc += dc[d];
//			}

			// 벽
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				score++;
				d = (d + 2) % 4;
				if (map[r][c] > 5) {
					int[] nextPos = getNextPos(r, c);
					r = nextPos[0];
					c = nextPos[1];
				} else {
					r = nr;
					c = nc;
				}
			}
			// 블록
			else if (map[nr][nc] > 0 && map[nr][nc] <= 5) {
				score++;
				if (map[nr][nc] == 5) {
					d = (d + 2) % 4;
					if (map[r][c] > 5) {
						int[] nextPos = getNextPos(r, c);
						r = nextPos[0];
						c = nextPos[1];
					} else {
						r = nr;
						c = nc;
					}
				} else {
					int nd = getDir(map[nr][nc], d);
					
					if (nd == (d + 2) % 4) {
						if (map[r][c] > 5) {
							int[] nextPos = getNextPos(r, c);
							r = nextPos[0];
							c = nextPos[1];
						} else {
							r = nr;
							c = nc;
						}
					} else {
						r = nr;
						c = nc;
					}
					
					d = nd;
				}
			}
			// 웜홀
			else if (map[nr][nc] > 5) {
				int[] nextPos = getNextPos(nr, nc);
				r = nextPos[0];
				c = nextPos[1];
			}
			// 빈공간 or 웜홀
			else {
				r = nr;
				c = nc;
			}

		} while ((r < 0 || r >= N || c < 0 || c >= N) || !((r == startR && c == startC) || map[r][c] == -1));

		return score;
	}

	/**	우 하 좌 상
	 * 1: ㅇ 우 상 ㅇ
	 * 2: ㅇ ㅇ 하 우
	 * 3: 하 ㅇ ㅇ 좌
	 * 4: 상 좌 ㅇ ㅇ
	 * 5: ㅇ ㅇ ㅇ ㅇ
	 */
	static int getDir(int block, int d) {
		int nd = -1;
		
		if (block == 1) {
			if (d == 0 || d == 3) {
				nd = (d + 2) % 4;
			} else {
				if (d == 1)
					nd = 0;
				else if (d == 2)
					nd = 3;
			}
		} else if (block == 2) {
			if (d == 0 || d == 1) {
				nd = (d + 2) % 4;
			} else {
				if (d == 2)
					nd = 1;
				else if (d == 3)
					nd = 0;
			}
		} else if (block == 3) {
			if (d == 1 || d == 2) {
				nd = (d + 2) % 4;
			} else {
				if (d == 0)
					nd = 1;
				else if (d == 3)
					nd = 2;
			}
		} else if (block == 4) {
			if (d == 2 || d == 3) {
				nd = (d + 2) % 4;
			} else {
				if (d == 0)
					nd = 3;
				else if (d == 1)
					nd = 2;
			}
		}
		
		return nd;
	}

	static int[] getNextPos(int r, int c) {
		int idx = map[r][c] - 6;
		if (wormHoles[idx][0][0] == r && wormHoles[idx][0][1] == c)
			return wormHoles[idx][1];
		else
			return wormHoles[idx][0];
	}
}
