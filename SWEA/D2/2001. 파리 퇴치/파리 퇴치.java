import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] sum = new int[N + 1][N + 1];

			for (int i = 1; i <= N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; ++j) {
					int tmp = Integer.parseInt(st.nextToken());
					sum[i][j] = tmp + sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1];
				}
			}

			int max = 0;

			for (int i = M; i <= N; ++i) {
				for (int j = M; j <= N; ++j) {
					int r1 = i - M + 1;
					int c1 = j - M + 1;
					int tmp = sum[i][j] - sum[r1 - 1][j] - sum[i][c1 - 1] + sum[r1 - 1][c1 - 1];
					max = Math.max(max, tmp);
				}
			}

			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}

		System.out.println(sb);
	}

}
