import java.util.*;
import java.io.*;

class Solution {
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };
	/**
	 * 상태 공간 무한 -> X = 1 일 때 K / 2 == 150 만큼 퍼지는게 최대 거리 -> 500 * 500 이면 충분
	 */
	static final int OFFSET = 300, MAP_SIZE = OFFSET * 2;
	static int N, M, K, initial[][], aliveCnt, map[][] = new int[MAP_SIZE][MAP_SIZE],
			cntMap[][] = new int[MAP_SIZE][MAP_SIZE], nextMap[][] = new int[MAP_SIZE][MAP_SIZE];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			aliveCnt = 0;
			for (int i = 0; i < MAP_SIZE; i++) {
				Arrays.fill(map[i], 0);
				Arrays.fill(nextMap[i], 0);
				Arrays.fill(cntMap[i], 0);
			}
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 1 <= N <= 50
			M = Integer.parseInt(st.nextToken()); // 1 <= M <= 50
			K = Integer.parseInt(st.nextToken()); // 1 <= K <= 300
			initial = new int[N][M];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					int t = Integer.parseInt(st.nextToken()); // 1 <= X <= 10
					nextMap[OFFSET + r][OFFSET + c] = map[OFFSET + r][OFFSET + c] = t;
					if (t != 0) cntMap[OFFSET + r][OFFSET + c] = 1;
				}
			}

			/**
			 * !!! map 크기가 무한대
			 * 	-> Map 크기 얼마나 커질 수 있는지 계산하기
			 * 	-> X ArrayDeque 으로 추가해야 할 때마다 추가하기? X
			 * 
			 * X 비활성 -> X 활성 -> 사망
			 * 
			 * 1. 죽은 상태 죽어도 셀 차지 -> map == 0 이면 비어있음
			 * 
			 * 2. 활성 상태 활성화 되면서 상하좌우로 비활성 세포 번식 
			 * 		없으면(map == 0) nextMap 에 X 기록, 이미 있으면 생략 
			 * 		이후에는 X 시간 카운트 후 사망.
			 * 
			 * !!! tie break 
			 * 		동시 번식 시도 시 X 높은게 우선 -> max 값 취하기? 
			 * 		-> 동시 번식이므로 nextMap 만들고 번식 후 상태 따로 관리
			 * 
			 * 3. 비활성 상태 X 시간 카운트 후 활성 + 번식
			 * 
			 * !!! 활성 + 비활성 cntMap 하나로 운영하기 
			 * 		1) 비활성 cnt: 1 -> X cnt == X: 번식 
			 * 		2) 활성 cnt: -1 -> -X cnt == -X: 사망
			 * 
			 * <<<필요>>> cntMap, nextMap
			 * 
			 * <<<순서>>> 
			 * 		활성 세포 번식 (cntMap, map 참조 -> nextMap 갱신) 
			 * 		-> 세포 aging (nextMap 기준으로 cntMap 갱신) 
			 * 		-> swap (nextMap, map)
			 */

			for (int t = 1; t <= K; t++) {
				simulate();
			}

			// count alive cell
			for (int r = 0; r < MAP_SIZE; r++) {
				for (int c = 0; c < MAP_SIZE; c++) {
					if (cntMap[r][c] != 0)
						aliveCnt++;
				}
			}

			sb.append("#").append(tc).append(" ").append(aliveCnt);// .append("\n")
			System.out.println(sb);
			sb.setLength(0);
		}
//		System.out.println(sb);
	}

	static void simulate() {
		// 활성 세포 번식
		for (int r = 0; r < MAP_SIZE; r++) {
			for (int c = 0; c < MAP_SIZE; c++) {
				// 세포 없으면 스킵
				if (map[r][c] == 0)
					continue;

				nextMap[r][c] = map[r][c];

				if (cntMap[r][c] != map[r][c] + 1)
					continue;

				// 활성 상태 -> 번식
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					// 이미 세포 있으면 스킵
					if (map[nr][nc] != 0)
						continue;

					// 번식
					nextMap[nr][nc] = Math.max(nextMap[nr][nc], map[r][c]);
//					cntMap[nr][nc] = 1;
				}
			}
		}

		int dummy = 0;

		// 세포 aging
		for (int r = 0; r < MAP_SIZE; r++) {
			for (int c = 0; c < MAP_SIZE; c++) {
				if (nextMap[r][c] > 0) {
					if (cntMap[r][c] > 0) {
						// 비활성 -> 활성
						if (cntMap[r][c] == nextMap[r][c] + 1) {
							cntMap[r][c] = -1;
						}
						else
							cntMap[r][c]++;
					}
					if (cntMap[r][c] < 0) {	
						// 활성 ->> 사망
						cntMap[r][c]--;
						if (cntMap[r][c] == -nextMap[r][c] - 1) {
							cntMap[r][c] = 0;
						}
					} 
					if (cntMap[r][c] == 0 && map[r][c] != nextMap[r][c])
						cntMap[r][c]++;
				}
			}
		}

		// swap
		int[][] temp = map;
		map = nextMap;
		nextMap = temp;
	}
}