import java.util.*;
import java.io.*;

class Solution {
	
	static int N, operator[] = new int[4], operands[], min, max;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			N = Integer.parseInt(in.readLine());	// ~ 12
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			
			operands = new int[N];
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				operands[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(1, operands[0]);
			
			System.out.println("#" + tc + " " + (max - min));
		}
	}

	private static void dfs(int index, int num) {
		if (index == N) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (operator[i] == 0)
				continue;
			
			operator[i]--;
			switch (i) {
			case 0:
				dfs(index + 1, num + operands[index]);
				break;
			case 1:
				dfs(index + 1, num - operands[index]);
				break;
			case 2:
				dfs(index + 1, num * operands[index]);
				break;
			case 3:
				dfs(index + 1, num / operands[index]);
				break;
			}
			operator[i]++;
		}
	}
}