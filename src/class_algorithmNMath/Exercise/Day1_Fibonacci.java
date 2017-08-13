package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day1_Fibonacci {
/*
문제 2749
피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.

이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n>=2)가 된다.

n=17일때 까지 피보나치 수를 써보면 다음과 같다.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 n이 주어진다. n은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 n번째 피보나치 수를 1,000,000으로 나눈 나머지를 출력한다.
 * */
	static int mod = 1000000;
	static int[][] defaultMat = {{1,1},{1,0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		st = new StringTokenizer(br.readLine());
		//int N = Integer.parseInt(st.nextToken());		
		//System.out.println(getFiboNoByRF(N));
		//System.out.println(getFiboNoByDP(N));		
		long n = Long.parseLong(st.nextToken());
		//System.out.println(getFiboNoByMat(n));
		System.out.println(getFiboNoByP(n));
	}

	private static long getFiboNoByP(long n) {
		int p = mod/10*15;
		int fibo[] = new int [p];
		fibo[0] = fibo[1] = 1;
		for(int i=2;i<p;i++){
			fibo[i] = fibo[i-1]+fibo[i-2];
			fibo[i] %=mod;
		}
		int z = (int) (n%p);
		
		return fibo[z-1];
	}

	private static long getFiboNoByMat(long n) {
		if(n<2) return 1;
		else {
			int [][] calMat = defaultMat;
//			int k=(int) n;
//			k = mod*15/10;
			while(n>3){
				calMat = Multiply(calMat, defaultMat);
				n--;
			}
			return (calMat[0][0]+calMat[0][1])%mod;
		}
		
	}

	private static int[][] Multiply(int[][] A, int[][] B) {
		int [][] result = new int[2][2];
		result[0][0] = (A[0][0]*B[0][0] + A[0][1]*B[1][0]);
		result[0][1] = (A[0][0]*B[0][1] + A[0][1]*B[1][1]);
		result[1][0] = (A[1][0]*B[0][0] + A[1][1]*B[1][0]);
		result[1][1] = (A[1][0]*B[0][1] + A[1][1]*B[1][1]);		
		return result;
	}

	private static int getFiboNoByDP(int n) {//Dynamic Planning

		int[] D = new int [n+1];
		D[2]= D[1] = 1;
		for(int i=3;i<=n;i++){
			D[i] = D[i-1]+D[i-2];
		}		
		return D[n];
	}

	private static int getFiboNoByRF(int n) {//Recursive Function
		if (n<0) return -1; 
		else if( n<2) return 1;
		else return getFiboNoByRF(n-2) + getFiboNoByRF(n-1);
	}

/*	
	#include "stdio.h"
	#include <queue>
	#define P 1000000
	using namespace std;
	typedef long long ll;
	ll N;
	ll A[4] = { 0,1,1,1 }, A2[4], R[2] = {1,1}, R2[2];
	int main()
	{
	    scanf("%lld", &N);
	    N -= 2;
	    for (ll i = 0, k = 1; k <= N; i++, k <<= 1)
	    {
	        if (N&k)
	        {
	            R2[0] = (A[0] * R[0]) % P + (A[1] * R[1]) % P;
	            R2[1] = (A[2] * R[0]) % P + (A[3] * R[1]) % P;
	            R[0] = (R2[0]) % P;
	            R[1] = (R2[1]) % P;
	        }
	        A2[0] = (A[0] * A[0]) % P + (A[1] * A[2]) % P;
	        A2[1] = (A[0] * A[1]) % P + (A[1] * A[3]) % P;
	        A2[2] = (A[2] * A[0]) % P + (A[3] * A[2]) % P;
	        A2[3] = (A[2] * A[1]) % P + (A[3] * A[3]) % P;
	        A[0] = (A2[0]) % P;
	        A[1] = (A2[1]) % P;
	        A[2] = (A2[2]) % P;
	        A[3] = (A2[3]) % P;
	    }
	    printf("%lld\n", R[1]);
	}
*/
}

