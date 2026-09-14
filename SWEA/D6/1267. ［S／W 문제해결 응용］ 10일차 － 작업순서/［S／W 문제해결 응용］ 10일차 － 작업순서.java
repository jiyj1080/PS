import java.util.*;
import java.io.*;

public class Solution {
	static int v, e;
	static List<Integer>[] edge = new ArrayList[1001];
	static boolean[] visited = new boolean[1001];
	static List<Integer> answer = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int i = 1; i <= 1000; i++) {
			edge[i] = new ArrayList<>();
		}

		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			answer.clear();
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			Arrays.fill(visited, 1, v + 1, false);
			for (int i = 1; i <= v; i++) {
				edge[i].clear();
			}
			e = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < e; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edge[from].add(to);
			}

			// solve
			for (int vertex = 1; vertex <= v; vertex++) {
				if (!visited[vertex]) {
					visited[vertex] = true;
					dfs(vertex);
				}
			}

			sb.append("#").append(tc);
			for (int v : answer) {
				sb.append(" ").append(v);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int v) {
		for (int to : edge[v]) {
			if (!visited[to]) {
				visited[to] = true;
				dfs(to);
			}
		}

		answer.add(0, v);
	}
}
