package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
public class DP_KOITP_최대구간합 {
	static int N;
	static int[]a;
	static long[]d;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		a = new int[N+1];
		d = new long[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++){
			int t =Integer.parseInt(st.nextToken()); 
			a[i] = t;			
		}
				
		long answer = Long.MIN_VALUE;
		for(int i=1;i<=N;i++){
			d[i]= Math.max(d[i-1]+a[i], a[i]);
			answer = Math.max(d[i], answer);
		}
		
		System.out.println(answer);
	}

}
