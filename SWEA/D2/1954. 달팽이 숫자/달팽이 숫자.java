import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			int N = Integer.parseInt(br.readLine());

			int[][] map = new int[N][N];

			int num = 1, count = N * N;
			int r = 0, c = 0, d = 0, nr, nc;

			for (int i=0;i<N*N;++i) {
				map[r][c] = num++;

				nr = r + dr[d];
				nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != 0) {
					d = (d + 1) % 4;
					nr = r + dr[d];
					nc = c + dc[d];
				}
				r = nr;
				c = nc;

			}

			sb.append("#").append(tc).append("\n");
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}
}
