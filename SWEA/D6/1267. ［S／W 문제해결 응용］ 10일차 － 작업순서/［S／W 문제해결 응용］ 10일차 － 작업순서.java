import java.io.*;
import java.util.*;

public class Solution {
	static int v, e;
	static boolean[][] edge;// = new int[1010][1010];

	public static void main(String[] args) throws Exception {
	//	System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			edge = new boolean[v + 1][v + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < e; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edge[from][to] = true;
			}
			
			// solve
			// floyd-warshall
			for (int k = 1; k <= v; k++) {
				for (int i = 1; i <= v; i++) {
					if (!edge[i][k])
						continue;
					for (int j = 1; j <= v; j++) {
						if (edge[k][j])
							edge[i][j] = true;
					}
				}
			}
			
			// make hash set for each node storing front nodes
			List<HashSet<Integer>> fronts = new ArrayList<>();
			//empty for index 0
			fronts.add(new HashSet<>());
			for (int i = 1; i <= v; i++) {
				HashSet<Integer> front = new HashSet<>();
				for (int j = 1; j <= v; j++) {
					if (edge[j][i])
						front.add(j);
				}
				fronts.add(front);
			}
			
			List<Integer> answer = new LinkedList<>();
			for (int i = 1; i <= v; i++) {
				int idx = 0;
				HashSet<Integer> front = fronts.get(i);
				for (int j = 0; j < answer.size(); j++) {
					if (front.contains(answer.get(j)))
						idx = j + 1;
				}
				answer.add(idx, i);
			}
			
			sb.append("#").append(tc);
			for (int i : answer) {
				sb.append(" ").append(i);
			}
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}