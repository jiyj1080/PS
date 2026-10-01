import java.util.*;
import java.io.*;

/*

 */

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int maxLen = 0;
			int N = Integer.parseInt(in.readLine());	// 1 ~ 1000
			int[] heights = new int[N + 1];
			int[] lisLengths = new int[N +1];
			heights[0] = lisLengths[0] = 0;
			st = new StringTokenizer(in.readLine());
			for (int i = 1; i <= N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());	// 1 ~ 1000
			}
			
			/**
			 * <완전 탐색>
			 * 앞에서부터 탐색하면서 모순 생길 시
			 * 1. 본인 집 부수기
			 * 2. 본인 뒤에서 같거나 작은 집들 다 부수기 
			 * N * 2 ^ N 
			 * 
			 * <<<최장 증가 부분 수열
			 * (LIS: Longest Increasing Subsequence)>>>
			 * 
			 * 
			 */
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < i; j++) {
					if (heights[i] > heights[j]) {
						lisLengths[i] = Math.max(lisLengths[i], lisLengths[j] + 1);
					}
				}
				maxLen = Math.max(maxLen, lisLengths[i]);
			}
			
			System.out.println("#" + tc + " " + (N - maxLen));
		}
	}
}