import java.util.*;
import java.io.*;

class Solution {
	
	static class Edge {
		int from, to;
		double weight;
		
		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int N;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			double totalCost = 0.0;
			N = Integer.parseInt(in.readLine());
			
			List<Edge> edges = new ArrayList<>();
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
			 * MST-Kruskal
			 */
			
			for (int from = 0; from < N; from++) {
				for (int to = from + 1; to < N; to++) {
					edges.add(new Edge(from, to, Math.pow(X[from] - X[to], 2) + Math.pow(Y[from] - Y[to], 2)));
				}
			}
			
			edges.sort((a, b) -> Double.compare(a.weight, b.weight));
			
			makeSet();
			
			int count = 0;
			for (Edge edge : edges) {
				if(!union(edge.from, edge.to))
					continue;
				
				if (++count == N)
					break;
				
				totalCost += E * edge.weight;
			}
			
			System.out.println("#" + tc + " " + Math.round(totalCost));
		}
	}
	
	static int[] parents;
	
	static void makeSet() {
		parents = new int[N];
		Arrays.fill(parents, -1);
	}
	
	static int find(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int findA = find(a);
		int findB = find(b);
		
		if (findA == findB)
			return false;
		
		if (parents[findA] <= parents[findB]) {
			parents[findA] += parents[findB];
			parents[findB] = findA;
		} else {
			parents[findB] += parents[findA];
			parents[findA] = findB;
		}
		
		return true;
	}
}