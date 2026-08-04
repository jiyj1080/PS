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
			
			sb.append(check(1) ? 1 : 0);
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	static boolean check(int idx) {
		Node node = tree[idx];
		if (isOp(node.data)) {
			return check(node.left) && check(node.right);
		} else {
			if (node.left == 0 && node.right == 0)
				return true;
			else 
				return false;
		}
	}
	
	static boolean isOp(String c) {
		if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/"))
			return true;
		return false;
	}

}
