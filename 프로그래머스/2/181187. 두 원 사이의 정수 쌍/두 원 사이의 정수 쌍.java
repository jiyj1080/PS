import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for (long y = -r2; y <= r2; y++) {            
            long lower = (long) Math.ceil(Math.sqrt(Math.max((long) r1 * r1 - y * y, 0)));
            long upper = (long) Math.floor(Math.sqrt((long) r2 * r2 - y * y));
            
            
            long result = upper * 2 + 1;
            if (lower > 0) {
                result -= (lower - 1) * 2 + 1;
            }
            
            //System.out.println(lower + " " + upper + " " + result);
            answer += result;
        }
        
        return answer;
    }
}

/* 
2,000,000 * 2,000,000
가로로 한줄씩 더하기
Y = -r2 ~ r2
r1 ^ 2 <= X ^ 2 + Y ^ 2 <= r2 ^ 2
*/