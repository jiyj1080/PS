import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int [] num = new int[8];
			boolean check = false;
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				
				if (!s.contains("1") || check) continue;
				
				int idx = s.lastIndexOf('1') - 55;
				
				for (int j=0;j<8;j++, idx += 7) {
					String part = s.substring(idx, idx + 7);
					switch (part) {
					case "0001101":
						num[j] = 0;
						break;
					case "0011001":
						num[j] = 1;
						break;
					case "0010011":
						num[j] = 2;
						break;
					case "0111101":
						num[j] = 3;
						break;
					case "0100011":
						num[j] = 4;
						break;
					case "0110001":
						num[j] = 5;
						break;
					case "0101111":
						num[j] = 6;
						break;
					case "0111011":
						num[j] = 7;
						break;
					case "0110111":
						num[j] = 8;
						break;
					case "0001011":
						num[j] = 9;
						break;
					default:
						num[j] = -1;
						break;
					};
				}
			}
			
			int a = num[0] + num[2] + num[4] + num[6];
			int b = num[1] + num[3] + num[5] + num[7];
			int sum = a * 3 + b;
			int result = 0;
			if (sum % 10 == 0) result = a + b;
			sb.append("#").append(tc).append(" ").append(result);

			System.out.println(sb);
			sb.setLength(0);
		}
	}

}