import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	static class State {
		int r, c, d, m;

		public State(int r, int c, int d, int m) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.m = m;
		}

	}

	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int R, C;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine().trim());
			R = Integer.parseInt(st.nextToken()); // ~ 20
			C = Integer.parseInt(st.nextToken()); // ~ 20

			map = new char[R][];
			boolean is = false;
			for (int r = 0; r < R; r++) {
				map[r] = in.readLine().trim().toCharArray();
				for (int c = 0; c < C; c++) {
					if (map[r][c] == '@')
						is = true;
				}
			}

			if (!is) {
				System.out.println("#" + tc + " NO");
				continue;
			}

			/**
			 * r, c, d, m
			 * 0, 0, 0, 0 시작
			 * 벽 뚫을 시 반대쪽으로 이동
			 * 메모리: 0 ~ 15, 초기값 0
			 * 
			 * ?: 4방향 분기해서 탐색해서 1개라도 정지 가능할 시 YES
			 * 
			 * 같은 자리에 같은 상태로 올 시 루프로 판단하고 종료?
			 * 좌표별로 경험한 상태 메모리 관리 -> d, m 으로 관리하는 boolean map r, c, d, m
			 * 
			 * 
			 */
			boolean[][][][] memory = new boolean[R][C][4][16];

			System.out.println("#" + tc + " " + (canStop(0, 0, 0, 0, memory) ? "YES" : "NO"));
		}
	}

	private static boolean canStop(int r, int c, int d, int m, boolean[][][][] memory) {
		Queue<State> q = new ArrayDeque<>();
		memory[r][c][d][m] = true;
		q.offer(new State(r, c, d, m));

		while (!q.isEmpty()) {
			State cur = q.poll();

			// 현재 칸 처리
			char ch = map[cur.r][cur.c];

			if (ch == '@')
				return true;

			switch (ch) {
			case '<':
				cur.d = 2;
				break;
			case '>':
				cur.d = 0;
				break;
			case '^':
				cur.d = 3;
				break;
			case 'v':
				cur.d = 1;
				break;

			case '_':
				cur.d = cur.m == 0 ? 0 : 2;
				break;
			case '|':
				cur.d = cur.m == 0 ? 1 : 3;
				break;

			case '?':
				// TODO
				for (int nd = 0; nd < 4; nd++) {
					int nr = (cur.r + dr[nd] + R) % R;
					int nc = (cur.c + dc[nd] + C) % C;

					if (map[nr][nc] == '@')
						return true;

					if (memory[nr][nc][nd][cur.m])
						continue;

					memory[nr][nc][nd][cur.m] = true;

					q.offer(new State(nr, nc, nd, cur.m));
				}
				break;

			case '.':
				break;

			case '+':
				cur.m = cur.m == 15 ? 0 : cur.m + 1;
				break;
			case '-':
				cur.m = cur.m == 0 ? 15 : cur.m - 1;
				break;

			// 0 ~ 9
			default:
				cur.m = ch - '0';
				break;
			}

			// 이동
			if (ch != '?') {
				cur.r = (cur.r + dr[cur.d] + R) % R;
				cur.c = (cur.c + dc[cur.d] + C) % C;

				if (memory[cur.r][cur.c][cur.d][cur.m])
					continue;
				
				memory[cur.r][cur.c][cur.d][cur.m] = true;
				q.offer(cur);
			}
		}

		return false;
	}
}
