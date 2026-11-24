import java.io.*;
import java.util.*;

/*

 */

public class Main {

	static int N, M, map[][], minDist, chickens[][] = new int[13][2], chickenCnt;
	static List<List<int[]>> houseDist;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(in.readLine().trim());
		N = Integer.parseInt(st.nextToken()); // 50
		M = Integer.parseInt(st.nextToken()); // 13
		// 집: 1이상 100 이하
		// 치킨집: M 이상 13이하

		map = new int[N][N];
		chickenCnt = 0;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine().trim());
			for (int c = 0; c < N; c++) {
				int t = map[r][c] = Integer.parseInt(st.nextToken());
				if (t == 2) {
					map[r][c] += chickenCnt;
					chickens[chickenCnt][0] = r;
					chickens[chickenCnt][1] = c;
					chickenCnt++;
				}
			}
		}

		/**
		 * 13 C 6 = 완탐
		 * ~ 10,000
		 * 집 100개
		 * bfs 2500? 미리 해서 가까운 순으로 저장하기
		 * boolean 으로 선택해서 마지막에 거리 합
		 * 
		 */

		houseDist = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1) {
					List<int[]> dist = new ArrayList<>();

					for (int i = 0; i < chickenCnt; i++) {
						int[] pos = chickens[i];

						dist.add(new int[] { i, Math.abs(r - pos[0]) + Math.abs(c - pos[1]) });
					}

					dist.sort((a, b) -> Integer.compare(a[1], b[1]));

					houseDist.add(dist);
				}
			}
		}

		minDist = Integer.MAX_VALUE;

		dfs(0, 0, new boolean[chickenCnt]);

		System.out.println(minDist);
	}

	static void dfs(int depth, int start, boolean[] able) {
		if (depth == M) {
			minDist = Math.min(minDist, calculate(able));
			return;
		}

		for (int i = start; i < chickenCnt; i++) {
			able[i] = true;
			dfs(depth + 1, i + 1, able);
			able[i] = false;
		}
	}

	static int calculate(boolean[] able) {
		int result = 0;
		
		for (List<int[]> house : houseDist) {
			for (int[] dist : house) {
				if (able[dist[0]]) {
					result += dist[1];
					break;
				}
			}
		}

		return result;
	}
}
