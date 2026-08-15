import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static final int n = 9;
	static int[][] map = new int[n][n];

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("#").append(tc).append(" ").append(check() ? 1 : 0).append("\n");
		}

		System.out.println(sb);
	}

	static boolean check() {
		boolean[] count;

		// row
		for (int i = 0; i < n; ++i) {
			count = new boolean[n];
			for (int j = 0; j < n; ++j) {
				int tmp = map[i][j] - 1;
				if (count[tmp])
					return false;
				count[tmp] = true;
			}
		}

		// column
		for (int j = 0; j < n; ++j) {
			count = new boolean[n];
			for (int i = 0; i < n; ++i) {
				int tmp = map[i][j] - 1;
				if (count[tmp])
					return false;
				count[tmp] = true;
			}
		}

		// square
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				count = new boolean[n];
				for (int k = 0; k < 3; ++k) {
					for (int l = 0; l < 3; ++l) {
						int tmp = map[i * 3 + k][j * 3 + l] - 1;
						if (count[tmp])
							return false;
						count[tmp] = true;
					}
				}
			}
		}

		return true;
	}

}
