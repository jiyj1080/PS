import java.io.*;
import java.util.*;

/*

 */

public class Solution {

	static class Edge {
		int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}

	static class State {
		int id, dist;

		public State(int id, int dist) {
			super();
			this.id = id;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		A: for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken()); // ~ 10,000
			int M = Integer.parseInt(st.nextToken()); // ~ 50,000
			st = new StringTokenizer(in.readLine());
			int S = Integer.parseInt(st.nextToken()) - 1;
			int E = Integer.parseInt(st.nextToken()) - 1;
//			if (S == E) {
//				System.out.println("#" + tc + " " + 0);
//				continue A;
//			}

			List<Edge>[] adjList = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				adjList[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				adjList[from].add(new Edge(to, weight));
			}

			int[] minDist = new int[N];
			Arrays.fill(minDist, Integer.MAX_VALUE);
			minDist[S] = 0;

			Queue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
			pq.offer(new State(S, 0));

			while (!pq.isEmpty()) {
				State cur = pq.poll();

//				if (cur.id == E) {
//					System.out.println("#" + tc + " " + cur.dist);
//					continue A;
//				}

				if (cur.dist != minDist[cur.id])
					continue;

				for (Edge next : adjList[cur.id]) {
					if (next.weight + cur.dist < minDist[next.to]) {
						minDist[next.to] = next.weight + cur.dist;
						pq.offer(new State(next.to, minDist[next.to]));
					}
				}
			}

			System.out.println("#" + tc + " " + (minDist[E] == Integer.MAX_VALUE ? -1 : minDist[E]));
		}
	}
}
