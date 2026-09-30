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
			int minMove;
			st = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(st.nextToken());	// -100 ~ 100
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			/**
			 * 대각선으로 쭉 이동 후 가로 or 세로로  이동
			 * 
			 * 나머지 이동
			 * 거리가 홀수일 시 (2N + 1)
			 * 1 + 4N
			 * 
			 * 거리가 짝수일 시 (2N)
			 * 4N
			 */
			
			// 대각선
			minMove = 2 * Math.min(Math.abs(x1 - x2), Math.abs(y1 - y2));
			
			// 나머지 이동
			int moveLeft = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) - Math.min(Math.abs(x1 - x2), Math.abs(y1 - y2));
			if (moveLeft % 2 == 0) {
				minMove += moveLeft * 2;
			} else {
				minMove += moveLeft * 2 - 1;
			}

			System.out.println("#" + tc + " " + minMove);
		}
	}
}