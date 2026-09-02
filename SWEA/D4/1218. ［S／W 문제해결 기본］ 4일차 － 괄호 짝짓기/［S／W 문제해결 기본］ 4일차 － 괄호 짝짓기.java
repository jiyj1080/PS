import java.io.*;
import java.util.*;

public class Solution {
	static Deque<Character> stack = new ArrayDeque();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			int length = Integer.parseInt(br.readLine());
			String s = br.readLine();

			sb.append("#").append(tc).append(" ").append(check(s, length) ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}

	static boolean check(String s, int length) {
		stack.clear();
		for (int i = 0; i < length; i++) {
			char c = s.charAt(i);

			if (c == '(' || c == '[' || c == '{' || c == '<') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				switch (c) {
				case ')':
					if (top != '(')
						return false;
					break;
				case ']':
					if (top != '[')
						return false;
					break;
				case '}':
					if (top != '{')
						return false;
					break;
				case '>':
					if (top != '<')
						return false;
					break;
				}
			}
		}

		return stack.isEmpty();
	}

}
