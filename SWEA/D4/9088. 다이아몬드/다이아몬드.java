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
			int N = Integer.parseInt(st.nextToken());	// ~ 1,000
			int K = Integer.parseInt(st.nextToken());	// ~ 10,000

			int[] diamonds = new int[N];
			for (int i = 0; i < N; i++) {
				diamonds[i] = Integer.parseInt(in.readLine().trim());	// 1 ~ 10,000
			}
			
			Arrays.sort(diamonds);
			
			int answer = 1;
			for (int i = 0; i < N; i++) {
				int end = i;
				while (end < N && diamonds[end] - diamonds[i] <= K)
					end++;
				
				answer = Math.max(answer, end - i);
			}

			System.out.println("#" + tc + " " + answer);
		}
	}
}
