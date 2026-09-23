import java.util.*;
import java.io.*;

public class Solution {

	static int N, X, M, hamsters[], candidate[], l[], r[], s[], maxHamsters;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			maxHamsters = -1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 6
			X = Integer.parseInt(st.nextToken()); // 1 ≤ X, M ≤ 10
			M = Integer.parseInt(st.nextToken());

			hamsters = new int[N + 1];
			candidate = new int[N + 1];
//			Arrays.fill(hamsters, X);

			l = new int[M];
			r = new int[M];
			s = new int[M];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				l[i] = Integer.parseInt(st.nextToken());
				r[i] = Integer.parseInt(st.nextToken());
				s[i] = Integer.parseInt(st.nextToken());
			}

			/**
			 * 완전탐색
			 * 
			 * (X + 1) ^ N = 11 ^ 6 = 1,771,561 1,771,561 * M = 17,715,610
			 * 
			 */

			dfs(1, 0);

			sb.append('#').append(tc);
			if (maxHamsters == -1) {
				sb.append(' ').append(-1);
			} else {
				for (int i = 1; i <= N; i++) {
					sb.append(' ').append(hamsters[i]);
				}
			}
//			sb.append("\n");
			System.out.println(sb);
			sb.setLength(0);
		}
//		System.out.println(sb);
	}

	static void dfs(int depth, int count) {
		if (depth == N + 1) {
			// if conflict skip
			for (int j = 0; j < M; j++) {
				int sum = 0;
				for (int k = l[j]; k <= r[j]; k++) {
					sum += candidate[k];
				}

				if (sum != s[j])
					return;
			}
			if (count > maxHamsters) {
				maxHamsters = count;
				for (int i = 1; i <= N; i++) {
					hamsters[i] = candidate[i];
				}
			}
			return;
		}

		for (int i = 0; i <= X; i++) {
			candidate[depth] = i;

			dfs(depth + 1, count + i);
		}
	}
}
