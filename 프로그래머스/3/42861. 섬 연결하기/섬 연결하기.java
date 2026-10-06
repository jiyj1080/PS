import java.util.*;

class Solution {
    static class Edge implements Comparable<Edge> {
        final int from, to, dist;
        
        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
    
    private static Edge[] edges;
    private static int[] parent;
    private static int[] size;
    
    public int solution(int n, int[][] costs) {
        init(n, costs);
        
        return kruskal(n);
    }
    
    private static void init(int n, int[][] costs) {
        int m = costs.length;
        
        edges = new Edge[m];
        
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(costs[i][0], costs[i][1], costs[i][2]);
        }
        
        parent = new int[n];
        size = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    private static int kruskal(int n) {
        Arrays.sort(edges);
        
        int cost = 0;
        int edgeCount = 0;
        
        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                cost += edge.dist;
                edgeCount++;
                
                if (edgeCount == n - 1) {
                    break;
                }
            }
        }
        
        return cost;
    }
    
    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }
    
    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA == rootB) {
            return false;
        }
        
        if (size[rootA] < size[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }
        
        size[rootA] += size[rootB];
        parent[rootB] = rootA;
        
        return true;
    }
}