import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	char data;
	int left;
	int right;

	public Node(char data, int left, int right) {
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
				char data = st.nextToken().charAt(0);
				int left = 0, right = 0;
				if (countToken >= 3) 
					left = Integer.parseInt(st.nextToken());
				if (countToken >= 4)
					right = Integer.parseInt(st.nextToken());
				
				tree[idx] = new Node(data, left, right);
			}
			
			sb.append("#").append(tc).append(" ");
			
			inOrder(1);
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	static void inOrder(int idx) {
		Node node = tree[idx];
		if (node.left != 0) inOrder(node.left);
		sb.append(node.data);
		if (node.right != 0) inOrder(node.right);
	}

}
