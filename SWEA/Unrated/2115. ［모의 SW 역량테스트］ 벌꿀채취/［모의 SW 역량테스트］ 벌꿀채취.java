import java.util.*;
import java.io.*;

class Solution {

	static int N, M, M2, C, map[][], maxProfit;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			maxProfit = 0;
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // N <= 10
			M = Integer.parseInt(st.nextToken()); // M <= 5, M <= N
			M2 = N - M + 1;
			C = Integer.parseInt(st.nextToken()); // C <= 30

			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken()); // 1 ~ 9
				}
			}

			/**
			 * 1.
			 * 2명 각자 M 개 채취
			 * 가로로 M개, 겹치면 안됨
			 * 
			 * 2.
			 * 용기에 담기
			 * 벌통 1칸 = 용기 1개
			 * 일부 채취 X, 전부 한번에 채취
			 * 최대 채취 양 = C
			 * 
			 * 3.
			 * 용기의 꿀 양 제곱만큼 수익
			 * 
			 * 수익의 합이 최대가 되는 경우 찾기
			 * 
			 * ~~~~~~~~~
			 * 
			 * 조합 문제로 풀기: ? C 2
			 * 2차원 -> 1차원 매핑 후 2개 뽑기
			 * 각 row 마다 N - M + 1 개 뽑음
			 * 총 N * (N - M + 1) 개 = 10 * (10 - 5 + 1) = 60
			 * 
			 * ???: 그냥 2개 뽑기 전에 N * (N - M + 1) 개의 수익 다 기록해놓기
			 * 		-> 이후에 모순 없는 2개 뽑기
			 * 
			 * ???: M 개 중에 C 넘기 전까지 뽑아야 하는데 부분집합 완전 탐색?
			 * 		2 ^ M <= 32
			 * 
			 */

			// 수익 기록하기
			int[][] profits = new int[N][M2];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M2; c++) {
					profits[r][c] = calculateProfit(r, c);
				}
			}

			// 올바른 2개 뽑기 - 조합
			for (int i = 0; i < N * M2; i++) {
				for (int j = i + 1; j < N * M2; j++) {
					if (conflict(i, j))
						continue;
					int profitSum = profits[i / M2][i % M2] + profits[j / M2][j % M2];
					maxProfit = Math.max(maxProfit, profitSum);
				}
			}

			System.out.println("#" + tc + " " + maxProfit);
		}
	}

	static int result;

	static int calculateProfit(int r, int c) {
		result = 0;

		// dfs
		dfs(r, c, 0, 0, 0);

		return result;
	}

	static void dfs(int r, int c, int depth, int harvest, int profit) {
		if (harvest > C)
			return;

		if (depth == M) {
			result = Math.max(result, profit);
			return;
		}
		
		int honey = map[r][c + depth];
		dfs(r, c, depth + 1, harvest + honey, profit + honey * honey);
		dfs(r, c, depth + 1, harvest, profit);
		
	}

	static boolean conflict(int i, int j) {
		int iR = i / M2, iC = i % M2, jR = j / M2, jC = j % M2;

		if (iR == jR && iC + M >= jC)
			return true;

		return false;
	}
}