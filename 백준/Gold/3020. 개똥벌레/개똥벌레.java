import java.io.*;
import java.util.*;

public class Main {

	static int N, H;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(in.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		/**
		 * bottom, top
		 * bottom: 1 1 4 1
		 * top:    0 2 3 2
		 * 
		 * 누적합
		 * bottom: 7 6 5 1
		 * top:    7 7 5 2
		 * 		   
		 * 		   7 6  5 1 0
		 * 		   0 2  5 7 7
		 * +	   7 8 10 8 7
		 * 
		 */
		int[] cnt = new int[H];
		int[] bottom = new int[H];
		int[] top = new int[H];

		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(in.readLine());

			if (i % 2 == 0) {
				bottom[h - 1]++;
			} else {
				top[h - 1]++;
			}
		}

		for (int i = H - 3; i >= 0; i--) {
			bottom[i] += bottom[i + 1];
			top[i] += top[i + 1];
		}
		
		for (int i = 0; i < H; i++) {
			cnt[i] = bottom[i] + top[H - i - 1];
		}

		int min = N / 2;
		int result = 0;

		for (int i = 0; i < H; i++) {
			if (cnt[i] < min) {
				result = 1;
				min = cnt[i];
			} else if (cnt[i] == min) {
				result++;
			}
		}

		System.out.println(min + " " + result);
	}
}
