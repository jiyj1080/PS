import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[][] map;
	static int N, K;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = 0;
			
			// row
			for (int i = 0; i < N; ++i) {
				int white = 0;
				for (int j = 0; j < N; ++j) {
					if (map[i][j] == 0) {
						if (white == K) result++; 
						white = 0;
					}
					else white++;
				}
				if (white == K) result++;
			}

			// column
			for (int j = 0; j < N; ++j) {
				int white = 0;
				for (int i = 0; i < N; ++i) {
					if (map[i][j] == 0) {
						if (white == K) result++; 
						white = 0;
					}
					else white++;
				}
				if (white == K) result++;
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}
}
