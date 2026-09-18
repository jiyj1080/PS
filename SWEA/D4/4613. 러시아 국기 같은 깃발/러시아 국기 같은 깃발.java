import java.util.*;
import java.io.*;

class Solution {
	static int N, M, minColor;
	static char map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			minColor = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 3≤N,M≤50
			M = Integer.parseInt(st.nextToken());
			map = new char[N][];
			for (int r = 0; r < N; r++) {
				map[r] = br.readLine().toCharArray();
			}
			
			/**
			 * 경계 정하기 
			 * 	-> N - 1 C 2
			 * 	0 <= r <= first
			 * 	first < r <= second
			 * 	second < r < N
			 * 정해진 경계로 칠해야 하는 경우에서 몇개를 색칠해야 하는지 센 후, 답 갱신
			 * 
			 */
			
			for (int first = 0; first < N - 2; first++) {
				for (int second = first + 1; second < N - 1; second++) {
					int coloring = 0;
					// white
					for (int r = 0; r <= first; r++) {
						for (int c = 0; c < M; c++) {
							if (map[r][c] != 'W')
								coloring++;
						}
					}
					
					// blue
					for (int r = first + 1; r <= second; r++) {
						for (int c = 0; c < M; c++) {
							if (map[r][c] != 'B')
								coloring++;
						}
					}
					
					// red
					for (int r = second + 1; r < N; r++) {
						for (int c = 0; c < M; c++) {
							if (map[r][c] != 'R')
								coloring++;
						}
					}
					
					minColor = Math.min(minColor, coloring);
				}
			}

			sb.append("#").append(tc).append(" ").append(minColor);// .append("\n")
			System.out.println(sb);
			sb.setLength(0);
		}
//		System.out.println(sb);
	}
}