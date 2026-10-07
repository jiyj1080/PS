import java.util.*;
import java.io.*;

/*

 */

class Solution {

	static class Node {
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}

	static int V, E, minEdge[], totalCost;
	static boolean visited[];
	static List<Node>[] adjList;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			totalCost = 0;
			V = Integer.parseInt(in.readLine());
			E = Integer.parseInt(in.readLine());

			adjList = new ArrayList[V];
			for (int i = 0; i < V; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				adjList[from].add(new Node(to, weight));
				adjList[to].add(new Node(from, weight));
			}

			minEdge = new int[V];
			visited = new boolean[V];

			Arrays.fill(minEdge, Integer.MAX_VALUE);
			minEdge[0] = 0;

			PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
			pq.add(new Node(0, 0));

			for (int c = 0; c < V; c++) {
				// 1. poll
				Node cur = pq.poll();
				while (visited[cur.to] || minEdge[cur.to] != cur.weight) {
					cur = pq.poll();
				}
				
				visited[cur.to] = true;
				totalCost += cur.weight;

				// 2. update
				for (Node adj : adjList[cur.to]) {
					if (!visited[adj.to] && adj.weight < minEdge[adj.to]) {
						minEdge[adj.to] = adj.weight;
						pq.add(new Node(adj.to, adj.weight));
					}
				}
			}

			System.out.println("#" + tc + " " + totalCost);
		}
	}
}