import java.io.*;
import java.util.*;

public class Solution {
	static int v, e;
	static boolean[][] edge;// = new int[1010][1010];
	static List<Integer> answer;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		
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
			answer = new LinkedList<>();
			
			// topological sort - dfs

			visited = new boolean[v + 1];
			
			for (int node = 1; node <= v; node++) {
				if (!visited[node]) {
					visited[node] = true;
					dfs(node);
				}
			}
			
			sb.append("#").append(tc);
			for (int i : answer) {
				sb.append(" ").append(i);
			}
			sb.append("\n");
//			System.out.println(sb);
//			sb.setLength(0);
		}
		System.out.println(sb);
	}
	
	static void dfs(int node) {
		
		for (int to = 1; to <= v; to++) {
			if (!edge[node][to] || visited[to])
				continue;
			
			visited[to] = true;
			dfs(to);
		}
		
		answer.add(0, node);
	}
}