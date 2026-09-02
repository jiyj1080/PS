import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			String[] ans = new String[n];
			for (int i = 0; i < n / 2 + n % 2; i++) {
				ans[i * 2] = st.nextToken();
			}
			for (int i = 0; i < n / 2; i++) {
				ans[i * 2 + 1] = st.nextToken();
			}


			sb.append("#").append(tc);
			for (String s : ans) {
				sb.append(" ").append(s);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
