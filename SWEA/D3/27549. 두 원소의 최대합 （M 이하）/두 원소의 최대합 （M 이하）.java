import java.io.*;
import java.util.*;

// 메모리: 50,932 KB, 시간: 154 ms

public class Solution {
	static int[] arr = new int[100_000];

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer = -1;
			st = new StringTokenizer(br.readLine());
			final int n = Integer.parseInt(st.nextToken());
			final int m = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr, 0, n);
			
			int l = 0, r = n - 1;
			while (l < r) {
				int sum = arr[l] + arr[r];
				if (sum == m) {
					answer = sum;
					break;
				} else if (sum < m) {
					l++;
					answer = Math.max(answer, sum);
				} else {
					r--;
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
}
