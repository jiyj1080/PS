import java.util.*;
import java.io.*;

/*

 */

class Solution {

	static int N, X, map[][], answer;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // 6 ~ 20
			X = Integer.parseInt(st.nextToken()); // 2 ~ 4

			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken()); // 1 ~ 6
				}
			}

			for (int r = 0; r < N; r++) {
				if (able(map[r]))
					answer++;
			}

			for (int c = 0; c < N; c++) {
				int[] road = new int[N];
				for (int r = 0; r < N; r++) {
					road[r] = map[r][c];
				}
				if (able(road))
					answer++;
			}

			System.out.println("#" + tc + " " + answer);
		}
	}

	static boolean able(int[] road) {
		boolean[] used = new boolean[N];
		for (int i = 0; i < N - 1; i++) {
			int a = road[i], b = road[i + 1];

			if (a == b)
				continue;

			if (Math.abs(a - b) > 1)
				return false;

			// 내려가는
			if (a > b) {
				if (i + X >= N)
					return false;
				
				for (int j = i + 1; j <= i + X; j++) {
					if (road[j] != b || used[j])
						return false;
					used[j] = true;
				}
			}
			// 올라가는
			else {
				if (i + 1 - X < 0)
					return false;
				
				for (int j = i; j >= i + 1 - X; j--) {
					if (road[j] != a || used[j])
						return false;
					used[j] = true;
				}
			}
		}

		return true;
	}
}