import java.util.*;

class Solution {
    private static int[] parent;
    private static int[] size;
    
    public int solution(int n, int[][] costs) {
        init(n);
        
        return kruskal(n, costs);
    }
    
    private static void init(int n) {
        parent = new int[n];
        size = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    private static int kruskal(int n, int[][] costs) {
        // Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));
        
        int totalCost = 0;
        int edgeCount = 0;
        
        for (int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            int dist = cost[2];
            
            if (union(from, to)) {
                totalCost += dist;
                
                if (++edgeCount == n - 1) {
                    break;
                }
            }
        }
        
        return totalCost;
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