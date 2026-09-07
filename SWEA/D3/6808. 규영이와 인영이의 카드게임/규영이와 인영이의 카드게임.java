import java.util.*;
import java.io.*;

public class Solution {
	static int win, lose;
	static int[] kyuyoung = new int[9], inyoung = new int[9];

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			win = lose = 0;
			st = new StringTokenizer(br.readLine());
			boolean[] used = new boolean[19];
			for (int i = 0; i < 9; i++) {
				int t = Integer.parseInt(st.nextToken());
				kyuyoung[i] = t;
				used[t] = true;
			}
			int cnt = 0;
			for (int n = 1; n <= 18; n++) {
				if (!used[n])
					inyoung[cnt++] = n;
			}

			// solve
			dfs(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean[] used = new boolean[9];

	static void dfs(int cnt, int kyuScore, int inScore) {
		if (cnt == 9) {
			if (kyuScore > inScore) win++;
			else if (kyuScore < inScore) lose++;
		}

		for (int i = 0; i < 9; i++) {
			if (used[i])
				continue;
			
			used[i] = true;

			int score = kyuyoung[cnt] + inyoung[i];
			if (kyuyoung[cnt] > inyoung[i]) dfs(cnt + 1, kyuScore + score, inScore);
			else dfs(cnt + 1, kyuScore, inScore + score);

			used[i] = false;
		}
	}
}
