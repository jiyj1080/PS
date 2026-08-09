import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Solution {
	static int n;
	static int[] pos;
	static int[] mass;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			pos = new int[n];
			mass = new int[n];
			double[] answer = new double[n - 1];

			for (int i = 0; i < n; i++) {
				pos[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < n; i++) {
				mass[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < n - 1; i++) {
				double left = (double) pos[i];
				double right = (double) pos[i + 1];
				double x = 0, diff;

				// L - R < 1e-12, x = ans
				for (int iter = 0; iter < 200; iter++) {
					// x = L + R / 2
					x = (left + right) / 2.0;
					diff = diffLR(x);
					// L < R R = x,
					if (diff < 0) {
						right = x;
					}
					// L > R L = x
					else {
						left = x;
					}
				}
				answer[i] = x;
			}

			sb.append("#").append(tc);
			for (double a : answer) {
				sb.append(String.format(" %.10f", a));
			}

			System.out.println(sb);
			sb.setLength(0);
		}
	}

	static double diffLR(double x) {
		double leftSum = 0, rightSum = 0;
		for (int i = 0; i < n; i++) {
			double p = (double) pos[i];
			double f = mass[i] / ((p - x) * (p - x));

			if (p < x) {
				leftSum += f;
			} else {
				rightSum += f;
			}
		}

		return leftSum - rightSum;
	}
}