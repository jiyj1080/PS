import java.util.*;
import java.io.*;

class Solution {

	static int N, scores[];

	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine().trim()); // ~ 100
			
			scores = new int[N];
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
			}
			
			/**
			 * 어짜피 점수 1 ~ 100 인데 대충 짜도 안터질듯
			 * 가능 합이 0 ~ 10000정도인데
			 * 100 * 10000 = 백만
			 * 
			 */
			
			Set<Integer> able = new HashSet<>();
			able.add(0);
			
			for (int i = 0; i < N; i++) {
				List<Integer> list = new ArrayList<>();
				for (int integer : able) {
					list.add(integer + scores[i]);
				}
				
				for (Integer integer : list) {
					able.add(integer);
				}
			}

			System.out.println("#" + tc + " " + able.size());
		}
	}
}