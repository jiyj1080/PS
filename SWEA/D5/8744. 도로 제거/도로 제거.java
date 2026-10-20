import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine().trim());
			int N = Integer.parseInt(st.nextToken());	// ~ 10,000
			int M = Integer.parseInt(st.nextToken());	// ~ 10,000

			int edges[] = new int[N];
			Arrays.fill(edges, N - 1);
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine().trim());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				edges[a]--;
				edges[b]--;
			}
			
			int min = N - 1;
			for (int i = 0; i < N; i++) {
				min = Math.min(min, edges[i]);
			}

			System.out.println("#" + tc + " " + (min - 1));
		}
	}
}
