import java.util.*;
import java.io.*;

public class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };
	static HashMap<Character, Integer> dir = new HashMap<>();

	static int h, w, n;
	static char[][] map = new char[20][20];

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		dir.put('R', 0);
		dir.put('D', 1);
		dir.put('L', 2);
		dir.put('U', 3);
		dir.put('>', 0);
		dir.put('v', 1);
		dir.put('<', 2);
		dir.put('^', 3);

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int tankR = -1, tankC = -1, tankD = -1;
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];

			for (int r = 0; r < h; r++) {
				String s = br.readLine();
				for (int c = 0; c < w; c++) {
					char ch = s.charAt(c);
					map[r][c] = ch;
					if (ch == '<' || ch == '>' || ch == '^' || ch == 'v') {
						tankR = r;
						tankC = c;
						tankD = dir.get(ch);
					}
				}
			}
			n = Integer.parseInt(br.readLine());
			String input = br.readLine();

			// solve
			for (int i = 0; i < n; i++) {
				char in = input.charAt(i);
				switch (in) {
				case 'S':
					int shootR = tankR + dr[tankD];
					int shootC = tankC + dc[tankD];

					while (shootR >= 0 && shootR < h & shootC >= 0 && shootC < w 
							&& (map[shootR][shootC] == '.' || map[shootR][shootC] == '-')) {
						shootR += dr[tankD];
						shootC += dc[tankD];
					}

					if (shootR < 0 || shootR >= h || shootC < 0 || shootC >= w)
						break;
					if (map[shootR][shootC] == '*')
						map[shootR][shootC] = '.';

						break;
					// U, D, L, R
				default:
					int d = dir.get(in);
					int nr = tankR + dr[d];
					int nc = tankC + dc[d];
					char nextChar = d == 0 ? '>' : d == 1 ? 'v' : d == 2 ? '<' : '^';
					
					tankD = d;
					map[tankR][tankC] = nextChar;
					if (nr < 0 || nr >= h || nc < 0 || nc >= w)
						break;
					if (map[nr][nc] != '.')
						break;

					map[tankR][tankC] = '.';
					map[nr][nc] = nextChar;
					tankR = nr;
					tankC = nc;

					break;
				}
			}

			sb.append("#").append(tc).append(" ");
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					sb.append(map[r][c]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

}
