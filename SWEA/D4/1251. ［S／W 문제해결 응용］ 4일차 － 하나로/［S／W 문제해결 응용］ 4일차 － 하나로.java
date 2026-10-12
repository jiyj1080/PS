import java.util.*;
import java.io.*;

class Solution {
	
	static class Vertex implements Comparable<Vertex> {
		int idx;
		double dist;

		public Vertex(int idx, double dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			
			double[][] adjMatrix = new double[N][N];
			double[] X = new double[N];
			double[] Y = new double[N];			
			StringTokenizer stX = new StringTokenizer(in.readLine());
			StringTokenizer stY = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				X[i] = Double.parseDouble(stX.nextToken());
				Y[i] = Double.parseDouble(stY.nextToken());
			}
			double E = Double.parseDouble(in.readLine());
			
			/**
			 * MST-Prim
			 */
			
			for (int from = 0; from < N; from++) {
				for (int to = from + 1; to < N; to++) {
					adjMatrix[from][to] = adjMatrix[to][from] 
							= Math.pow(X[from] - X[to], 2) + Math.pow(Y[from] - Y[to], 2);
//							= (X[from] - X[to]) * (X[from] - X[to]) + (Y[from] - Y[to]) * (Y[from] - Y[to]);
				}
			}
			
			boolean[] visited = new boolean[N];
			double[] minDist = new double[N];
			Arrays.fill(minDist, Double.MAX_VALUE);
			
			Queue<Vertex> pq = new PriorityQueue<>();
			minDist[0] = 0;
			pq.offer(new Vertex(0, 0));
			
			double totalCost = 0.0;
			
			while (!pq.isEmpty()) {
				Vertex cur = pq.poll();
				
				if (visited[cur.idx])
					continue;
				
				totalCost += E * cur.dist;
				visited[cur.idx] = true;
				
				for (int next = 0; next < N; next++) {
					if (next == cur.idx)
						continue;
					
					if (!visited[next] && adjMatrix[cur.idx][next] < minDist[next]) {
						minDist[next] = adjMatrix[cur.idx][next];
						pq.offer(new Vertex(next, minDist[next]));
					}
				}
			}

			System.out.printf("#%d %.0f\n", tc, totalCost);
		}
	}
}