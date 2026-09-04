import java.io.*;
import java.util.*;

//

public class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };
	static final int SIZE = 16;	
	
	static int[][] map = new int[SIZE][SIZE];

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			int testcase = Integer.parseInt(br.readLine());
			
			for (int r = 0; r < SIZE; r++) {
				String s = br.readLine();
				for (int c = 0; c < SIZE; c++) {
					map[r][c] = s.charAt(c) - '0';
				}
			}
			
			sb.append("#").append(testcase).append(" ").append(canGo() ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}

	static int[] q = new int[SIZE * SIZE];
	static int[][] visited = new int [SIZE][SIZE];
	static int visitToken = 0;
	static int fromR = 1, fromC = 1;
	static int toR = 13, toC = 13;
	
	static boolean canGo() {
		visitToken++;
		int head = 0, tail = 0;
		q[tail++] = fromR * 100 + fromC;	// RRCC
		visited[fromR][fromC] = visitToken;
		
		while (head != tail) {
			int curPos = q[head++];
			int cr = curPos / 100, cc = curPos % 100;
			if (cr == toR && cc == toC)
				return true;
			
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if (map[nr][nc] == 1 || visited[nr][nc] == visitToken)
					continue;
				
				q[tail++] = nr * 100 + nc;
				visited[nr][nc] = visitToken;
			}
		}
		
		return false;
	}
}
