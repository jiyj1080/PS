import java.util.*;
import java.io.*;

class Solution {

	static int N, M, adj[][];

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;

			N = Integer.parseInt(in.readLine()); // ~ 500
			M = Integer.parseInt(in.readLine()); // ~ 125,000

			adj = new int[N][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				adj[from][to] = 1;
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (adj[i][k] == 1 && adj[k][j] == 1)
							adj[i][j] = 1;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				int cnt = 0;
				for (int j = 0; j < N; j++) {
					cnt += adj[i][j] + adj[j][i];
				}
				if (cnt == N - 1)
					answer++;
			}

			System.out.println("#" + tc + " " + answer);
		}
	}
}