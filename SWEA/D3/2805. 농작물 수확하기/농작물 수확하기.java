import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[][] map;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			int n = Integer.parseInt(br.readLine());

			int result = 0;
			
			int m = n / 2;

			for (int i = 0; i < n; ++i) {
				String s = br.readLine();
				int dist = Math.abs(m - i);
				for (int j = dist; j < n - dist; ++j) {
					result += s.charAt(j) - '0';
				}
			}
			
			

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);

	}
}
