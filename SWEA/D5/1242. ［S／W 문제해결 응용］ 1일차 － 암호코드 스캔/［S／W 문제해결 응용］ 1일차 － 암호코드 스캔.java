import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Solution {
	static String[] binaryLookUp = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001",
			"1010", "1011", "1100", "1101", "1110", "1111" };

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

			List<String> checkedLine = new ArrayList<>();
			List<List<Integer>> checkedCode = new ArrayList<>();
			int result = 0;
			for (int i = 0; i < n; i++) {
				int[] code = new int[8];
				
				String s = br.readLine();
				if (checkedLine.contains(s))
					continue;
				checkedLine.add(s);

				StringBuilder binarySb = new StringBuilder();
				for (char c : s.toCharArray()) {
					binarySb.append(binaryLookUp[Character.digit(c, 16)]);
				}
				String binary = binarySb.toString();

				int idx = binary.length();
				while (idx >= 0) {
					idx = binary.lastIndexOf('1', idx);
					if (idx < 0)
						continue;

					// len 구하기
					int w1 = 0, w2 = 0, w3 = 0, lenIdx = idx;
					while (lenIdx >= 0 && binary.charAt(lenIdx) == '1') {
						w1++;
						lenIdx--;
					}
					while (lenIdx >= 0 && binary.charAt(lenIdx) == '0') {
						w2++;
						lenIdx--;
					}
					while (lenIdx >= 0 && binary.charAt(lenIdx) == '1') {
						w3++;
						lenIdx--;
					}
									
					int len = Math.min(Math.min(w1, w2), w3);
					idx = idx - len * 56;

					for (int j = 0, k = idx + 1; j < 8; j++, k += 7 * len) {

						String part = binary.substring(k, k + 7 * len);
						String shorten = "";
						for (int l = 0; l < 7; l++) {
							shorten += binary.charAt(k + l * len);
						}
						switch (shorten) {
						case "0001101":
							code[j] = 0;
							break;
						case "0011001":
							code[j] = 1;
							break;
						case "0010011":
							code[j] = 2;
							break;
						case "0111101":
							code[j] = 3;
							break;
						case "0100011":
							code[j] = 4;
							break;
						case "0110001":
							code[j] = 5;
							break;
						case "0101111":
							code[j] = 6;
							break;
						case "0111011":
							code[j] = 7;
							break;
						case "0110111":
							code[j] = 8;
							break;
						case "0001011":
							code[j] = 9;
							break;
						default:
							code[j] = -1;
							break;
						}
						;
					}
					List<Integer> wrapCode = Arrays.stream(code).boxed().collect(Collectors.toList());
					if (checkedCode.contains(wrapCode)) continue;
					checkedCode.add(wrapCode);
					int a = code[0] + code[2] + code[4] + code[6];
					int b = code[1] + code[3] + code[5] + code[7];
					int sum = a * 3 + b;
					if (sum % 10 == 0) {
						result += a + b;
					}
				}
			}

			
			sb.append("#").append(tc).append(" ").append(result);

			System.out.println(sb);
			sb.setLength(0);
		}
	}

}