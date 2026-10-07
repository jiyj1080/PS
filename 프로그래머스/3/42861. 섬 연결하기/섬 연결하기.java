import java.util.*;

class Solution {
    static class Edge {
        final int to, dist;
        
        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    
    static class State implements Comparable<State> {
        final int to, dist;
        
        State(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(State other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
    
    private static int n;
    private static List<List<Edge>> graph;
    
    public int solution(int n, int[][] costs) {
        Solution.n = n;
        
        init(costs);
        
        return prim();
    }
    
    private static void init(int[][] costs) {
        graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int dist = costs[i][2];
            
            graph.get(from).add(new Edge(to, dist));
            graph.get(to).add(new Edge(from, dist));
        }
    }
    
    private static int prim() {
        int result = 0;
        
        PriorityQueue<State> pq = new PriorityQueue<>();
        
        boolean[] visited = new boolean[n]; 
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        
        minDist[0] = 0;
        pq.offer(new State(0, 0));
        
        while (!pq.isEmpty()) {
            State current = pq.poll();
            
            if (visited[current.to]) {
                continue;
            }
            
            visited[current.to] = true;
            
            result += current.dist;
            
            for (Edge edge : graph.get(current.to)) {
                int next = edge.to;
                int nextDist = edge.dist;
                
                if (visited[next] || nextDist >= minDist[next]) {
                    continue;
                }
                
                minDist[next] = nextDist;
                pq.offer(new State(next, nextDist));
            }        
        }
        
        return result;
    }
}