package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day5_03_DP_최대구간의합 {
/*
문제
크기N인 정수 배열이 들어왔을 때 연속 부분 수열 중 합이 최대인 것의 합을 구하시오.

입력
첫 번째 줄에 크기 
N이 주어진다. (1≤N≤1,000,000)두 번째 줄에 크기 N인 정수 배열이 주어진다.
 주어지는 수의 절대값은 2,000,000 보다 크지 않다.

출력
연속 부분 수열의 합 중 최대값을 출력하시오. (단, 출력값이 int 범위를 벗어날 수 있다.)

10
-2 3 4 -5 30 -15 2 4 5 10

38

*/
	
	static int N;
	static int []A;
	static long []D;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++){
				A[i] = Integer.parseInt(st.nextToken());
			}
			/* D[i] = i를 마지막으로 하는 모든 부분 수열들의 합중 최대값
			 * i-1까지의 최대값을 사용할 경우        D[i-1] + A[i]
			 * i-1까지의 최대값을 사용하지 않을 경우 A[i]
			 * D[i] = max(D[i-1]+A[i], A[i]) = max(D[i-1], 0) + A[i]
			 * 
			 * */
			D = new long [N+1];
			long max = Long.MIN_VALUE;
			for(int i=1;i<=N;i++){
				D[i] = Math.max(D[i-1], 0) + A[i];
				max = Math.max(max, D[i]);
			}
			System.out.println(max);
//		}
		br.close();
	}
}
