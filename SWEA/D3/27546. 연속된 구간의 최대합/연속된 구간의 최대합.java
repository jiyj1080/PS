import java.io.*;
import java.util.*;

// 

public class Solution {
	static int[] arr = new int[1_000_000];

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer;
			st = new StringTokenizer(br.readLine());
			final int n = Integer.parseInt(st.nextToken());
			final int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int i = 0; i < k; i++) {
				int num = Integer.parseInt(st.nextToken());
				sum += num;
				arr[i] = num;
			}
			answer = sum;
			
			for (int i = k; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				sum = sum - arr[i - k] + num;
				arr[i] = num;
				answer = Math.max(answer, sum);
			}
			
			

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
}
