import java.io.*;
import java.util.*;

public class Solution {
	static int totalChange, result, numLen;
	static int[] numpad;
	static List<HashSet<Integer>> visited;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			String num = new String(st.nextToken());
			numLen = num.length();
			result = 0;
			totalChange = Integer.parseInt(st.nextToken());

			visited = new ArrayList<>();
			for (int i = 0; i < totalChange; i++) {
				visited.add(new HashSet<>());
			}
			numpad = new int[numLen];
			for (int i = 0; i < numLen; i++) {
				numpad[i] = num.charAt(i) - '0';
			}

			dfs(0, numpad);

			sb.append("#").append(tc).append(" ").append(result);
			System.out.println(sb);
			sb.setLength(0);
		}
	}

	static void dfs(int change, int[] numpad) {
		// end condition
		if (change == totalChange) {
			result = Math.max(result, numpadToInt(numpad));
			return;
		}

		for (int i = 0; i < numLen; i++) {
			for (int j = i + 1; j < numLen; j++) {
				swap(numpad, i, j);
				
				if (!visited.get(change).contains(numpadToInt(numpad))) {
					visited.get(change).add(numpadToInt(numpad));
					dfs(change + 1, numpad);
				}
				
				swap(numpad, i, j);
				
			}
		}
	}
	
	static void swap(int[] numpad, int i, int j) {
		int tmp = numpad[i];
		numpad[i] = numpad[j];
		numpad[j] = tmp;
	}

	static int numpadToInt(int[] numpad) {
		int result = 0;
		for (int n : numpad) {
			result = result * 10 + n;
		}
		return result;
	}

}