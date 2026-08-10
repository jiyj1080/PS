import java.util.*;


class Solution {
    static int[] gap;
    
    public int solution(int distance, int[] rocks, int n) {
        int size = rocks.length;
        Arrays.sort(rocks);
        gap = new int [size + 1];
        
        int start = 0;
        for (int i = 0; i < size; i++) {
            gap[i] = rocks[i] - start;
            start = rocks[i];
        }
        gap[size] = distance - rocks[size - 1];
        
        int left = 1;
        int right = distance;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int removed = 0;
            int prev = 0;
            for (int i = 0; i < size; i++) {
                if (rocks[i] - prev < mid) {
                    removed++;
                } else {
                    prev = rocks[i];
                }
            }
            // last rock and dest
            if (distance - prev < mid) {
                removed++;
            }
                
            if (removed > n) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        return answer;
        
    }
    
// 0 2 11 14 17 21 25
//  2 9  3  3  4  4
//   11 12 6  7  8

// 0 11 14 17 21 25
//  11  3  3  4  4
//   14 6  7  8

// 0 2 4 6 8 10 12 14 16
//  2 2 2 2 2  2  2  2
//   4   4   4     4
}
