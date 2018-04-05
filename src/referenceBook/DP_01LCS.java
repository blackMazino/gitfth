package referenceBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DP_01LCS {
	/*
	 * LCS (Longest Common Subsequence) ● 정의 두 수열에서 순서를 그대로 유지하면서 가장 긴 공통 부분 수열을
	 * 찾을 때 사용하는 알고리즘 ● 설명 두 수열의 비교할 값이 같을 경우 현재 이전까지 비교한 길이의 A, B의 LCS에서 1 증가
	 * A[i]==B[j] 이면 LCS[i][[j] = LCS[i-1][j-1]+1 두 수열의 비교할 값이 다를 경우 각 수열의 이전까지
	 * 비교한 길이의 LCS와 동일하다. 최장 수열이므로 둘 중 큰 값을 LCS로 저장한다. A[i]!=B[j] 이면 LCS[i][j] =
	 * Max(LCS[i-1][j], LCS[i][j-1])
	 * 
	 * [입력] ACAYKP CAPCAK
	 * 
	 * [출력] 4
	 * 
	 */

	static char[] A = new char[1001];
	static char[] B = new char[1001];
	static int[][] D = new int[1001][1001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len_A = str.length();

		for (int i = 1; i <= len_A; i++)
			A[i] = str.charAt(i - 1);
		
		str = br.readLine();
		int len_B = str.length();

		for (int i = 1; i <= len_B; i++)
			B[i] = str.charAt(i - 1);

		for (int i = 1; i <= len_A; i++) {
			for (int j = 1; j <= len_B; j++) {
				// 비교하려는 값이 일치하는 경우 (1번 경우)
				if (A[i] == B[j]) {
					D[i][j] = D[i - 1][j - 1] + 1;
				// 비교하려는 값이 일치하지 않는 경우 (2번 경우)
				} else {
					D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
				}
			}
		}

		System.out.println(D[len_A][len_B]);

	}

}
