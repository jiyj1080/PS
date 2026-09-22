import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = N / 4;
			int K = Integer.parseInt(st.nextToken());
			char[] arr = br.readLine().toCharArray();

			ArrayDeque<Character> dq = new ArrayDeque<>();

			for (char c : arr) {
				dq.offerLast(c);
			}

			TreeSet<Integer> set = new TreeSet<>();

			for (int turn = 0; turn < M; turn++) {
				// add to set
				for (Iterator iterator = dq.iterator(); iterator.hasNext();) {
					for (int i = 0; i < 4; i++) {
						int num = 0;
						for (int j = 0; j < M; j++) {
							num *= 16;
							char c = (char) iterator.next();
							if (c <= '9') {
								num += c - '0';
							} else {
								num += c - 'A' + 10;
							}
						}
						set.add(num);
					}
				}

				// turn 1
				dq.offerLast(dq.pollFirst());
			}

			System.out.println("#" + tc + " " + set.toArray()[set.size() - K]);

		}

	}

}
