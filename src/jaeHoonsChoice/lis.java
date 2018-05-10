package jaeHoonsChoice;

import java.io.*;
import java.util.*;

public class lis {

	static int N, Answer;
	static int[] num = new int[300001];
	static int[] D = new int[300001];

	public static int lower_bound(int size, int target) {

		int mid, start, end;
		start = 0;
		end = size - 1;

		// 이분탐색 : end가 start보다 같거나 작아지면, 그 값이 Lower Bound
		while (end > start) {
			mid = (start + end) / 2;
			// 중간값이 원하는 값보다 크거나 같을 경우, 끝값을 중간값으로 설정하여 다시 탐색한다.
			if (D[mid] >= target)
				end = mid;
			// 중간값이 원하는 값보다 작을 경우, 시작값을 중간값+1로 설정하여 다시 탐색한다.
			else
				start = mid + 1;
		}
		return end;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());

		D[0] = num[0];
		int j = 0;

		for (int i = 1; i < N; i++) {
			// DP배열의 가장 큰 수보다 더 큰수가 들어오면 추가
			if (D[j] < num[i]) {
				D[++j] = num[i];
			} else {
				// DP배열의 가장 큰수보다 작을 경우 배열내에서 작지 않은 첫번째 수와 교체
				int ans = lower_bound(j + 1, num[i]);
				D[ans] = num[i]; // ★ DP 배열에 저장되는 숫자는 LIS와 무관함
			}
		}

		Answer = j + 1;

		System.out.println(Answer);
	}
}
