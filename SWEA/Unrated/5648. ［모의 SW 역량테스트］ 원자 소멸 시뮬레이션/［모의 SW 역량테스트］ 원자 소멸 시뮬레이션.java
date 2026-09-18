import java.util.*;
import java.io.*;

class Solution {
	// 상 하 좌 우
	static final int[] dy = { 1, -1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	static final int OFFSET = 1000;

	static class Atom {
		int dead; // 0: alive, 1: just died, 2: died
		int x, y, nx, ny, d, k;

		public Atom(int x, int y, int d, int k) {
			dead = 0;
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}

		public void nextPosUpdate() {
			ny = y + dy[d];
			nx = x + dx[d];
		}
	}

	static int N, totalEnergy, map[][] = new int[2001][2001], nextAtomCnt[][] = new int[2001][2001];
	static Atom[] atoms;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			totalEnergy = 0;
			for (int i = 0; i < 2001; i++) {
				Arrays.fill(map[i], 0);
				Arrays.fill(nextAtomCnt[i], 0);
			}
			N = Integer.parseInt(br.readLine()); // 1 ≤ N ≤ 1,000
			atoms = new Atom[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = OFFSET + Integer.parseInt(st.nextToken()); // -1,000 ≤ x, y ≤ 1,000
				int y = OFFSET + Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken()); // 1 ≤ K ≤ 100
				atoms[i] = new Atom(x, y, d, k);
				map[y][x] = i;
			}

			/**
			 * 원자의 x, y < -1000 or x, y > 1000 이면 충돌 안 일어남 -> 삭제
			 * 모든 원자 삭제하면 종료
			 * 최대 시간 = 2001초
			 * 
			 * <!!!> .5초 충돌 있음
			 * 		근데 .5초 충돌은 2개의 원소만 가능 여러개 안됨
			 * 
			 * <!!!> 동시 이동
			 * 		-> nextMap 쓰기?
			 * 		최대 4개 충돌 가능 (4방에서 한 점으로)
			 * 
			 * 
			 * <???> 원자 관리
			 * 1000 * 100 map VS 객체로 관리
			 * 1000 * 1000 map? 4 * 1M = 4MB map + nextMap(동시) 
			 * 		-> 총 8MB
			 * 객체로 관리하면 누가 충돌하는지 어떻게 앎?
			 * 	-> HashMap 으로 현재 살아있는 원자 좌표 관리???
			 * 
			 * <???> .5초 충돌
			 * 시뮬을 0.5초씩 2배로 진행 VS .5초 충돌 관리하기
			 */
			int dmp = 0;

			while (!allDie()) {
				for (int i = 1; i <= N; i++) {
					Atom atom = atoms[i];
					if (atom.dead != 0)
						continue;

					atom.nextPosUpdate();

					// out of bound -> die
					if (atom.ny < 0 || atom.ny > 2000 || atom.nx < 0 || atom.nx > 2000) {
						atom.dead = 3;
						continue;
					}

					// .5 collide
					if (map[atom.ny][atom.nx] != 0) {
						int dir2 = atoms[map[atom.ny][atom.nx]].d;
						// atom.d, dir2
						// 0 -> 1
						// 1 -> 0
						// 2 -> 3
						// 3 -> 2
						boolean halfCollide = false;
						switch (atom.d) {
						case 0:
							if (dir2 == 1)
								halfCollide = true;
							break;
						case 1:
							if (dir2 == 0)
								halfCollide = true;
							break;
						case 2:
							if (dir2 == 3)
								halfCollide = true;
							break;
						case 3:
							if (dir2 == 2)
								halfCollide = true;
							break;
						}
						if (halfCollide) {
							atom.dead = 1;
							totalEnergy += atom.k;
						} else {
							nextAtomCnt[atom.ny][atom.nx]++;
                        }
					} else {
						nextAtomCnt[atom.ny][atom.nx]++;
					}
				}

				for (int i = 1; i <= N; i++) {
					Atom atom = atoms[i];
					if (atom.dead == 1) {
						map[atom.y][atom.x] = 0;
						continue;
					} else if (atom.dead >= 2)
						continue;

					map[atom.y][atom.x] = 0;
					atom.y = atom.ny;
					atom.x = atom.nx;
					if (nextAtomCnt[atom.y][atom.x] > 1) {
						atom.dead = 2;
						totalEnergy += atom.k;
					} else {
						map[atom.y][atom.x] = i;
					}
				}

				for (int i = 1; i <= N; i++) {
					Atom atom = atoms[i];
					if (atom.dead == 3)
						continue;
					if (atom.dead == 1) {
						atom.dead = 3;
						continue;
					}
					if (atom.dead == 2) {
						nextAtomCnt[atom.y][atom.x] = 0;
						atom.dead = 3;
						continue;
					}
					nextAtomCnt[atom.y][atom.x] = 0;
				}
//				for (int i = 0; i < 2001; i++) {
//					Arrays.fill(nextAtomCnt[i], 0);
//				}
			}

			sb.append("#").append(tc).append(" ").append(totalEnergy);// .append("\n")
			System.out.println(sb);
			sb.setLength(0);
		}
//		System.out.println(sb);
	}

	static boolean allDie() {
		for (int i = 1; i <= N; i++) {
			if (atoms[i].dead == 0)
				return false;
		}

		return true;
	}
}