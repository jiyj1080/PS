import java.io.*;
import java.util.*;

class Solution {
	static Queue<Integer> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			q.clear();
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			boolean condition = true;
			while (condition) {
				// cycle
				for (int i = 1; i <= 5; i++) {
					int t = q.poll();
					t -= i;
					if (t <= 0) {
						q.offer(0);
						condition = false;
						break;
					}
					q.offer(t);
				}
				
			}
			
			sb.append("#").append(n);
			for (int i : q) {
				sb.append(" ").append(i);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}