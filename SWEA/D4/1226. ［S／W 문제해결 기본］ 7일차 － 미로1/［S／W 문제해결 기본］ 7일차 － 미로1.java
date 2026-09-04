import java.io.*;

// 

public class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };
	static final int SIZE = 16;

	static int[][] map = new int[SIZE][SIZE];
	static int fromR = 1, fromC = 1;
	static int toR = 13, toC = 13;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int testcase = Integer.parseInt(br.readLine());
			visitToken++;
			visited[fromR][fromC] = visitToken;

			for (int r = 0; r < SIZE; r++) {
				String s = br.readLine();
				for (int c = 0; c < SIZE; c++) {
					map[r][c] = s.charAt(c) - '0';
				}
			}
			
			canGo(fromR, fromC);

			sb.append("#").append(testcase).append(" ").append(visited[toR][toC] == visitToken ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}

	static int[][] visited = new int[SIZE][SIZE];
	static int visitToken = 0;

	static void canGo(int r, int c) {
		if (visited[toR][toC] == visitToken)
			return;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (map[nr][nc] == 1 || visited[nr][nc] == visitToken)
				continue;

			visited[nr][nc] = visitToken;
			canGo(nr, nc);
		}
	}
}
