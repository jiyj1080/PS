import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	static int N, M, K, A, B, a[], b[], t[];

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // ~ 9
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); // ~ 1,000
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			a = new int[N];
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			b = new int[M];
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			t = new int[K + 1];
			st = new StringTokenizer(in.readLine());
			for (int i = 1; i <= K; i++) {
				t[i] = Integer.parseInt(st.nextToken());
			}

			/**
			 * 고객: 접수 -> 정비
			 * 
			 * 시뮬: 
			 * 1. 도착 -> 접수 대기, 접수 완료 -> 정비 대기, 정비 완료 -> 끝
			 * 2. 접수대기 -> 접수, 정비 대기 -> 정비
			 * 	접수, 정비 시작하면서 기록하기
			 * 
			 */

			Queue<Integer> receptionQ = new ArrayDeque<>();
			Queue<Integer> repairQ = new ArrayDeque<>();
			int[] receptionIdx = new int[N];
			int[] receptionTime = new int[N];
			int[] repairIdx = new int[M];
			int[] repairTime = new int[M];
			Arrays.fill(receptionIdx, -1);
			Arrays.fill(receptionTime, -1);
			Arrays.fill(repairIdx, -1);
			Arrays.fill(repairTime, -1);
			List<Integer>[] receptionRecords = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				receptionRecords[i] = new ArrayList<>();
			}
			List<Integer>[] repairRecords = new ArrayList[M];
			for (int i = 0; i < M; i++) {
				repairRecords[i] = new ArrayList<>();
			}

			int nextIdx = 1;
			boolean done[] = new boolean[K + 1];
			for (int time = 0; !isDone(done); time++) {
				// 1-1. 도착 -> 접수 대기
				while (nextIdx <= K && t[nextIdx] == time) {
					receptionQ.add(nextIdx++);
				}
				
				// 1-2. 접수 완료 -> 정비 대기
				for (int i = 0; i < N; i++) {
					if (receptionTime[i] >= 0)
						receptionTime[i]++;
					
					if (receptionTime[i] == a[i]) {
						repairQ.add(receptionIdx[i]);
						receptionTime[i] = -1;
						receptionIdx[i] = -1;
					}
				}
				
				// 1-3. 정비 완료 -> 끝
				for (int i = 0; i < M; i++) {
					if (repairTime[i] >= 0)
						repairTime[i]++;
					
					if (repairTime[i] == b[i]) {
						done[repairIdx[i]] = true;
						repairTime[i] = -1;
						repairIdx[i] = -1;
					}
				}
				
				// 2-1. 접수대기 -> 접수
				for (int i = 0; i < N; i++) {
					if (receptionTime[i] == -1 && !receptionQ.isEmpty()) {
						int idx = receptionQ.poll();
						receptionTime[i] = 0;
						receptionIdx[i] = idx;
						receptionRecords[i].add(idx);
					}
				}
				
				// 2-2. 정비 대기 -> 정비
				for (int i = 0; i < M; i++) {
					if (repairTime[i] == -1 && !repairQ.isEmpty()) {
						int idx = repairQ.poll();
						repairTime[i] = 0;
						repairIdx[i] = idx;
						repairRecords[i].add(idx);
					}
				}
			}
			
			// 정답 계산
			int answer = 0;
			for (int idx1 : receptionRecords[A - 1]) {
				for (int idx2 : repairRecords[B - 1]) {
					if (idx1 == idx2)
						answer += idx1;
				}
			}

			System.out.println("#" + tc + " " + (answer == 0 ? -1 : answer));
		}
	}
	
	static boolean isDone(boolean[] done) {
		for (int i = 1; i <= K; i++) {
			if (!done[i])
				return false;
		}
		
		return true;
	}
}
