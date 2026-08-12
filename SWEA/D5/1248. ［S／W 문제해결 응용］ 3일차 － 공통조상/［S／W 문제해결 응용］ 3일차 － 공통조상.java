import java.io.*;
import java.util.*;

class Node {
	int parent = 0;
	int left = 0;
	int right = 0;
}

public class Solution {
	static int v, e, a, b, commonParent;
	static Node[] tree;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			tree = new Node[v + 1];
			for (int i = 0; i <= v; i++) {
				tree[i] = new Node();
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= e; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				if (tree[parent].left == 0) tree[parent].left = child;
				else tree[parent].right = child;
				tree[child].parent = parent;
			}
			
			HashSet<Integer> aRoot = new HashSet<>();
			
			// a의 부모들 모두 집합에 넣기
			int current = a;
			aRoot.add(current);				
			while (current != 1) {
				current = tree[current].parent;
				aRoot.add(current);				
			}
			
			// b의 부모 확인하면서 aRoot 집합에 존재하면 공통조상으로 판명
			commonParent = b;
			while (!aRoot.contains(commonParent)) {
				commonParent = tree[commonParent].parent;
			}
			
			

			sb.append("#").append(tc).append(" ").append(commonParent).append(" ").append(subTreeSize(commonParent));
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	static int subTreeSize(int i) {
		int result = 1;
		if (tree[i].left != 0) result += subTreeSize(tree[i].left);
		if (tree[i].right != 0) result += subTreeSize(tree[i].right);
		
		return result;
	}
}