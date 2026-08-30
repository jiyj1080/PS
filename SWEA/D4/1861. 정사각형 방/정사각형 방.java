import java.io.*;
import java.util.*;

public class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int n;
	static int[][] map = new int[1000][1000];
	static boolean[] canMove;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			canMove = new boolean[n * n + 1];

			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// solve
			
			// canMove
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					int num = map[r][c];
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] == num + 1) {
			                canMove[num] = true;
			                break;
			            }
					}
				}
			}
			
			// iterate from behind
			int maxCount = 1, startNum = 1, currentCount = 1;
			for (int i = n * n - 1; i >= 1; i--) {
				if (canMove[i]) {
			        currentCount++;
			    } else {
			        currentCount = 1;
			    }

			    if (currentCount >= maxCount) {
			        maxCount = currentCount;
			        startNum = i;
			    }
			}

			sb.append("#").append(tc).append(" ").append(startNum).append(" ").append(maxCount);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}