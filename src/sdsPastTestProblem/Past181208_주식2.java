package sdsPastTestProblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Past181208_주식2 {

/*
1
7
6 1 3 2 4 5 2

6
1 3 4 5
1 3 5
1 2 4 5
1 2 5
1 4 5
1 5
1 2
8가지

그룹의 좌측에는 가장 좌측의 수보다 작은 수가 있으면 안됨
그룹의 우측에는 가장 우측의 수보다 큰 수가 있으면 안됨
그룹은 좌-> 우 오름차순
전체 부분집합의 총 갯수를 mod(10억7)로 나눈 나머지
*/	
	
	static int TC,N, answer;//-200,000<=N<=200,000
	static int mod = 1000000007;
	static int arr[];//each value <+-100,0000
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++){
				arr[n] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#"+tc+" "+answer);
		}
	}
}
