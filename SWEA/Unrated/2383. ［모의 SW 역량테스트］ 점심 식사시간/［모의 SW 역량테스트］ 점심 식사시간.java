import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	static int N, map[][], minTime, stairs[][] = new int[2][3], movingTime[][];
	static List<int[]> people;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			minTime = Integer.MAX_VALUE;
			people = new ArrayList<>();
			N = Integer.parseInt(in.readLine()); // 4 ~ 10

			map = new int[N][N];
			int stairCnt = 0;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					int t = map[r][c] = Integer.parseInt(st.nextToken());
					/**사람의 수 1 ~ 10 
					 * 계단 입구 2개, 길이 2 ~ 10
					 */
					if (t == 1) {
						people.add(new int[] { r, c });
					} else if (t > 1) {
						stairs[stairCnt][0] = r;
						stairs[stairCnt][1] = c;
						stairs[stairCnt][2] = t;
						stairCnt++;
					}
				}
			}
			stairChoice = new int[people.size()];
			movingTime = new int[people.size()][2];
			// 이동 시간 미리 계산하기
			for (int i = 0; i < people.size(); i++) {
				int[] person = people.get(i);
				for (int j = 0; j < 2; j++) {
					movingTime[i][j] = Math.abs(person[0] - stairs[j][0]) + Math.abs(person[1] - stairs[j][1]);
				}
			}

			// 2 ^ 10 = 1024의 경우의 수 완전 탐색

			dfs(0);

			System.out.println("#" + tc + " " + minTime);
		}
	}

	static int[] stairChoice;

	static void dfs(int idx) {
		if (idx == people.size()) {
			minTime = Math.min(minTime, simulate());
			return;
		}

		stairChoice[idx] = 0;
		dfs(idx + 1);
		stairChoice[idx] = 1;
		dfs(idx + 1);

	}

	/**
	 * 이동시간 정해짐 -> 사람 위치 추적 필요 없음
	 * 도착시간부터 들어갈 수 있는지 계속 확인, 들어갈 시 타이머 켜기
	 * 계단별 인원 수 관리해야 함. 최대 3명
	 * 
	 * 1. 계단 내려가기
	 * 2. 입장 가능성 따지기 순서
	 * 
	 */
	static int simulate() {
		int time = 0;
		int[] peopleInStairs = new int[2];
		int[] peopleStatus = new int[people.size()];

		boolean isEnd;
		while (++time < minTime) {
			isEnd = true;
			for (int person = 0; person < people.size(); person++) {
				// 계단 내려가기
				if (peopleStatus[person] > 0) {
					if (peopleStatus[person]++ == stairs[stairChoice[person]][2]) {
						peopleStatus[person] = -1;
						peopleInStairs[stairChoice[person]]--;
					}
				}
			}
			for (int person = 0; person < people.size(); person++) {
				// 입장 가능성
				if (peopleStatus[person] == 0) {
					if (time > movingTime[person][stairChoice[person]] && peopleInStairs[stairChoice[person]] < 3) {
						peopleStatus[person] = 1;
						peopleInStairs[stairChoice[person]]++;
					}
				}

				if (peopleStatus[person] < 0)
					continue;

				isEnd = false;
			}

			if (isEnd)
				return time;
		}

		return minTime;
	}
}
