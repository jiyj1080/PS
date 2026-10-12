import java.util.*;

class Solution {
    
    class Edge {
        int to, weight;
        
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    class Vertex {
        int id, dist;
        
        Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        List<Edge>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int[] cost : costs) {
            int from = cost[0], to = cost[1], weight = cost[2];
            adjList[from].add(new Edge(to, weight));
            adjList[to].add(new Edge(from, weight));
        }
        
        Queue<Vertex> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
        
        boolean[] visited = new boolean[n];
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;
        pq.offer(new Vertex(0, 0));
        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            
            if (visited[cur.id])
                continue;
            
            visited[cur.id] = true;
            answer += cur.dist;
            
            for(Edge edge : adjList[cur.id]) {
                if (!visited[edge.to] && edge.weight < minDist[edge.to]) {
                    minDist[edge.to] = edge.weight;
                    pq.offer(new Vertex(edge.to, edge.weight));
                }
            }
        }
        
        return answer;
    }
}