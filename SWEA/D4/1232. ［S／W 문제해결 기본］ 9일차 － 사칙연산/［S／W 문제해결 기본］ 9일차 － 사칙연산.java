import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Node {
    String data;
    int left;
    int right;
 
    public Node(String data, int left, int right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
 
public class Solution {
     
    static Node[] tree;
    static StringBuilder sb;
 
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
 
        int T = 10;
 
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
 
            tree = new Node[n + 1];
 
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                 
                int countToken = st.countTokens();
                 
                int idx = Integer.parseInt(st.nextToken());
                String data = st.nextToken();
                int left = 0, right = 0;
                if (countToken >= 3) 
                    left = Integer.parseInt(st.nextToken());
                if (countToken >= 4)
                    right = Integer.parseInt(st.nextToken());
                 
                tree[idx] = new Node(data, left, right);
            }
             
            sb.append("#").append(tc).append(" ");
             
            sb.append((int)check(1));
             
            System.out.println(sb);
            sb.setLength(0);
        }
    }
     
    static double check(int idx) {
        Node node = tree[idx];
        switch (node.data) {
        case "+":
        	return check(node.left) + check(node.right);
        case "-":
        	return check(node.left) - check(node.right);
        case "*":
        	return check(node.left) * check(node.right);
        case "/":
        	return check(node.left) / check(node.right);
        default:
        	return Double.parseDouble(node.data);
        }
    }
 
}