package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day2_CntOfCombination {
/*문제 2004
nCm의 끝자리 0의 개수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 정수 n, m(0≤m≤n≤2,000,000,000, n!=0)이 들어온다.

출력
첫째 줄에 0의 개수를 출력한다.

2와  5의 인자수  중 작은수를 찾는다
 * 
 * */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;		
//		st = new StringTokenizer(br.readLine());		
//		int TC = Integer.parseInt(st.nextToken());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());//nCm
			int M = Integer.parseInt(st.nextToken());
			
			//nCm = n! / ((n-m)! * m!)
			//n!의 2(5)의지수를 찾아서 (n-m)!, m!를 찾아서 뺀다
//			int cal2 = findTrailingZeros(N, 2) - findTrailingZeros(N-M, 2) - findTrailingZeros(M, 2); 
//			int cal5 = findTrailingZeros(N, 5) - findTrailingZeros(N-M, 5) - findTrailingZeros(M, 5);
			int cal2 = cntZeros(N, 2) - cntZeros(N-M, 2) - cntZeros(M, 2); 
			int cal5 = cntZeros(N, 5) - cntZeros(N-M, 5) - cntZeros(M, 5);
			System.out.println(cal2<cal5?cal2:cal5);
		
	}

	private static int cntZeros(int n, int i) {
		int cnt = 0;
		for(cnt =0; n>0; n/= i){
			cnt += n/i;
		}
	return cnt;
}

	private static int findTrailingZeros(int n, int a ) {
		int count = 0;
		for(int i=a; n/i>=1; i*=a) count += n/i;
		return count;
	}
/*
	#include "stdio.h"

	int a, b, a1, a2, ret;
	int f(int n, int d) {
	    for (ret = 0;n; n /= d) ret += n / d;
	    return ret;
	}
	int main() {
	    scanf("%d%d", &a, &b);
	    a1 = f(a, 5) - f(a - b, 5) - f(b, 5);
	    a2 = f(a, 2) - f(a - b, 2) - f(b, 2);
	    printf("%d", a1 < a2 ? a1 : a2);
	}	
*/
}
