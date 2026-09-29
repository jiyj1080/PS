import java.util.*;
import java.io.*;

/*

 */

class Solution {

	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int N, M, SR, SC, DR, DC;
	static char[][] map;
	static List<int[]> devils = new ArrayList<>();
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			devils.clear();

			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // N <= 50
			M = Integer.parseInt(st.nextToken()); // M <= 50

			map = new char[N][];
			visited = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				map[r] = in.readLine().toCharArray();
				for (int c = 0; c < M; c++) {
					char ch = map[r][c];
					if (ch == 'S') {
						SR = r;
						SC = c;
						map[r][c] = '.';
					} else if (ch == 'D') {
						DR = r;
						DC = c;
					} else if (ch == '*') {
						devils.add(new int[] { r, c });
					}
				}
			}

			/**
			 * 악마: 상하좌우 확장, 여러 개 있을 수 있음
			 * 매 시간 확장해야 함 -> 그냥 리스트로 관리
			 * 수연: 최소 시간 -> bfs?
			 * 
			 */

			int result = bfs();

			System.out.println("#" + tc + " " + (result == -1 ? "GAME OVER" : result));
		}
	}

	static int bfs() {
		Queue<int[]> sQ = new ArrayDeque<>();
		Queue<int[]>[] devilsQ = new ArrayDeque[devils.size()];
		for (int i = 0; i < devils.size(); i++) {
			devilsQ[i] = new ArrayDeque<>();
			devilsQ[i].offer(new int[] { devils.get(i)[0], devils.get(i)[1] });
		}

		sQ.add(new int[] { SR, SC });
		visited[SR][SC] = true;

		int time = 0;

		while (!sQ.isEmpty()) {
			time++;
			// 악마 확장
			for (int devil = 0; devil < devils.size(); devil++) {
				Queue<int[]> devilQ = devilsQ[devil];
				int size = devilQ.size();

				while (size-- > 0) {
					int[] cur = devilQ.poll();

					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];

						if (!isIn(nr, nc) || map[nr][nc] != '.')
							continue;

						map[nr][nc] = '*';
						devilQ.offer(new int[] { nr, nc });
					}
				}
			}

			// 수연 이동
			int size = sQ.size();

			while (size-- > 0) {
				int[] cur = sQ.poll();

				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					
					if (nr == DR && nc == DC)
						return time;

					if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] != '.')
						continue;

					visited[nr][nc] = true;
					sQ.offer(new int[] { nr, nc });
				}
			}
		}

		return -1;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}