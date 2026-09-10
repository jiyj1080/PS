import java.util.*;
import java.io.*;

public class Solution {
	static int n;
	static int[][] map = new int[49][49];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				char[] cs = br.readLine().toCharArray();
				for (int j = 0; j < n; j++) {
					map[i][j] = cs[j] - '0';
				}
			}

			// solve
			int answer = 0;
			int half = n / 2;
			for (int r = 0; r < n; r++) {
				for (int c = Math.abs(r - half); c < n - Math.abs(r - half); c++) {
					answer += map[r][c];
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}