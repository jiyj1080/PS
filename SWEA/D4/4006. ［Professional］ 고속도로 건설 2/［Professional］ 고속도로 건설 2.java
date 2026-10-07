import java.util.*;
import java.io.*;

/*

 */

class Solution {

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int V, E, parents[], cnt, totalCost;
	static Edge[] edgeList;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			V = Integer.parseInt(in.readLine());
			E = Integer.parseInt(in.readLine());

			parents = new int[V];
			edgeList = new Edge[E];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}

			makeSet();

			Arrays.sort(edgeList);

			cnt = totalCost = 0;

			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					totalCost += edge.weight;
					if (++cnt == V - 1)
						break;
				}
			}

			System.out.println("#" + tc + " " + totalCost);
		}
	}

	static void makeSet() {
		Arrays.fill(parents, -1);
	}

	static int find(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		if (parents[aRoot] <= parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}

		return true;
	}
}