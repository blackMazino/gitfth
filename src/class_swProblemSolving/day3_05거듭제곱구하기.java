package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class day3_05거듭제곱구하기 {
/*
문제
정수 a와 m이 주어 졌을때,a의 m 거듭제곱(am)을 1,000,000,007 로 나눈 나머지를 출력하는 문제이다.

입력
두 정수 a와 m이 주어진다. 
1≤a,m≤2^63 − 1

출력
a 의m 제곱 값을 10억 7로 나눈 나머지를 출력한다.

2 4

16

*
*
*/
	static int MOD = (int) 1e9 + 7;//1,000,000,007
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long m = Long.parseLong(st.nextToken());
			
			//System.out.println(Math.pow(a, m)%MOD);//이건 아닐꺼고..
			a = a%MOD;
			System.out.println(f(a,m));
			
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
	
	private static long f(long a, long m) {
		if(m==0) return 1;
		
		long x = f (a, m/2);		
		if(m%2 == 1){//홀수면 a를 한번더 곱해줘야한다
			return (x*x%MOD * a)%MOD; 
		}else{
			return (x*x%MOD)%MOD;
		}		
	}
}
