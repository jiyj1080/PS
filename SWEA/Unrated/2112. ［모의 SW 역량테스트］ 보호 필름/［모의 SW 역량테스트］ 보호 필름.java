import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	static int D, W, K, map[][];
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine().trim());
			D = Integer.parseInt(st.nextToken()); // ~ 13
			W = Integer.parseInt(st.nextToken()); // ~ 20
			K = Integer.parseInt(st.nextToken()); // ~ D
			
			map = new int[D][W];
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(in.readLine().trim());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}				
			}
			
			/**
			 * 0 개 ~ K-1개 시도하고 실패하면 K
			 * 
			 * k (0 ~ K-1) 각각의 경우
			 * D C k 만들고 시도인데? -> 2 ^ D
			 * 각 경우의 k 개의 층에 A, B 두 개 다 시도 가능 -> 2 ^ k
			 * 
			 */
			int k;
			picked = new int[K];
			for (k = 0; k < K; k++) {
				if (chooseLayer(0, 0, k))
					break;
			}

			System.out.println("#" + tc + " " + k);
		}
	}
	
	static int[] picked;
	
	static boolean chooseLayer(int index, int start, int k) {
		if (index == k) {
			return chooseDrug(0, k);
		}
		
		boolean result = false;
		
		for (int i = start; i < D; i++) {
			picked[index] = i;
			result |= chooseLayer(index + 1, i + 1, k);
		}
		
		return result;
	}
	
	static boolean chooseDrug(int depth, int k) {
		if (depth == k) {
			return checking();
		}
		
		boolean result = false;
		
		int[] newLayer = new int[W];
		int[] original = map[picked[depth]];
		// A
		map[picked[depth]] = newLayer;
		result |= chooseDrug(depth + 1, k);
		// B
		Arrays.fill(newLayer, 1);
		result |= chooseDrug(depth + 1, k);
		
		map[picked[depth]] = original;
		return result;
	}
	
	static boolean checking() {
		A: for (int c = 0; c < W; c++) {
			for (int r = 0; r <= D - K; r++) {
				boolean passed = true;
				for (int i = 0; i < K; i++) {
					if (map[r][c] != map[r + i][c])
						passed = false;
				}
				
				if (passed) {
					continue A;
				}
			}
			
			return false;
		}
		
		return true;
	}
}
