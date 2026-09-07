import java.io.*;
import java.util.*;

public class Solution {
	static int v, e;
	static boolean[][] edge;// = new int[1010][1010];

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
			List<Integer> answer = new LinkedList<>();
			
			// topological sort - bfs
			
			// in degree
			int[] inDegree = new int [v + 1];
			for (int from = 1; from <= v; from++) {
				for (int to = 1; to <= v; to++) {
					if (edge[from][to])
						inDegree[to]++;
				}
			}
			
			// bfs
			Queue<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[v + 1];
			
			// push 0 in degree node
			for (int node = 1; node <= v; node++) {
				if (inDegree[node] == 0) {
					q.offer(node);
					visited[node] = true;
				}
			}
			
			// 
			while (!q.isEmpty()) {
				int cur = q.poll();
				answer.add(cur);
				
				for (int to = 1; to <= v; to++) {
					if (!edge[cur][to] || visited[to])
						continue;
					
					if (--inDegree[to] == 0) {
						q.offer(to);
						visited[to] = true;
					}
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
}