import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	static class Charger {
		int x, y, c, p;

		public Charger(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

	}

	static final int[] dy = { 0, -1, 0, 1, 0 };
	static final int[] dx = { 0, 0, 1, 0, -1 };

	static int M, A, aPositions[][], bPositions[][];
	static Charger[] chargers;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine().trim());
			M = Integer.parseInt(st.nextToken()); // 20 ~ 100
			A = Integer.parseInt(st.nextToken()); // 1 ~ 8

			aPositions = new int[M + 1][2];
			bPositions = new int[M + 1][2];
			int y = 1, x = 1;
			aPositions[0][0] = y;
			aPositions[0][1] = y;
			st = new StringTokenizer(in.readLine().trim());
			for (int i = 1; i <= M; i++) {
				int d = Integer.parseInt(st.nextToken());
				y += dy[d];
				x += dx[d];
				aPositions[i][0] = y;
				aPositions[i][1] = x;
			}
			y = 10;
			x = 10;
			bPositions[0][0] = y;
			bPositions[0][1] = x;
			st = new StringTokenizer(in.readLine().trim());
			for (int i = 1; i <= M; i++) {
				int d = Integer.parseInt(st.nextToken());
				y += dy[d];
				x += dx[d];
				bPositions[i][0] = y;
				bPositions[i][1] = x;
			}

			chargers = new Charger[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(in.readLine().trim());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken()); // 1 ~ 4
				int P = Integer.parseInt(st.nextToken()); // 10 ~ 500
				chargers[i] = new Charger(X, Y, C, P);
			}

			/**
			 * M * (2 * A + A * A)
			 * 100 * (2 * 8 + 8 * 8) = 8,000
			 * 
			 */
			
			int maxCharging = 0;
			
			for (int t = 0; t <= M; t++) {
				// 1. A 가능 충전기 찾기
				int[] aPos = aPositions[t];
				List<Integer> a = new ArrayList<>();
				for (int i = 0; i < A; i++) {
					Charger charger = chargers[i];
					if (Math.abs(aPos[0] - charger.y) + Math.abs(aPos[1] - charger.x) <= charger.c)
						a.add(i);
				}
				
				// 2. B 가능 충전기 찾기
				int[] bPos = bPositions[t];
				List<Integer> b = new ArrayList<>();
				for (int i = 0; i < A; i++) {
					Charger charger = chargers[i];
					if (Math.abs(bPos[0] - charger.y) + Math.abs(bPos[1] - charger.x) <= charger.c)
						b.add(i);
				}
				
				// 3. 완탐으로 최대 충전량 찾기
				maxCharging += maxChargingAtT(a, b);
			}

			System.out.println("#" + tc + " " + maxCharging);
		}
	}

	private static int maxChargingAtT(List<Integer> a, List<Integer> b) {
		int result = 0;
		
		if (a.size() == 0 || b.size() == 0) {
			List<Integer> tmp = a.size() == 0 ? b : a;
			for (Integer i : tmp) {
				result = Math.max(result, chargers[i].p);
			}
			return result;
		}
		
		for (Integer i : a) {
			for (Integer j : b) {
				int charge = i == j ? chargers[i].p : chargers[i].p + chargers[j].p;
				
				result = Math.max(result, charge);
			}
		}
		
		return result;
	}
}
