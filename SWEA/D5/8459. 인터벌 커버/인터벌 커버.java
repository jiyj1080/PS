import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	static final int div = 1_000_000_007;

	static int X, N, L[][];

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine().trim());
			X = Integer.parseInt(st.nextToken()); // 100
			N = Integer.parseInt(st.nextToken()); // 100

			L = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				L[i][0] = s;
				L[i][1] = e;
			}

			Arrays.sort(L, (a, b) -> {
				int tmp = Integer.compare(a[0], b[0]);
				if (tmp == 0)
					return Integer.compare(a[1], b[1]);
				return tmp;
			});

			int dp[][] = new int[N + 1][X + 1];
			dp[0][0] = 1;

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j <= X; j++) {
					dp[i][j] += dp[i - 1][j];
					dp[i][j] %= div;
					
					int l[] = L[i - 1];
					int s = l[0], e = l[1];
					if (s <= j + 1) {
						int tmp = Math.max(j, e);
						dp[i][tmp] += dp[i - 1][j];
						dp[i][tmp] %= div;
					}
				}
			}

			/**
			 * 완탐 = 2 ^ N(100)
			 * 
			 * dp 0 ~ i 까지 채운 경우의 수
			 * 
			 * 
			 * 
			 * 1번 테케
			 * 
			 * 1이상 9이하의 모든 정수
			 * 1 2 3 4 5 6 7 8 9
			 * 
			 * 1 ~ 5
			 * 2 ~ 4
			 * 3 ~ 7
			 * 4 ~ 9
			 * 
			 * 1 ~ 5 + 4 ~ 9
			 * 나머지 2개 넣냐 마냐 2 * 2 = 4
			 * 
			 * 3번 테케
			 * 1 2 3
			 * 1 ~ 3
			 * 1 ~ 3
			 * 1 ~ 3
			 * 1 ~ 3
			 * 2 ^ 4 - 1 = 15
			 */

			System.out.println("#" + tc + " " + dp[N][X]);
		}
	}
}
