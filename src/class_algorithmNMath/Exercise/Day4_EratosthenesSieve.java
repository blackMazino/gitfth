package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day4_EratosthenesSieve {
/*	
	문제 2960
	에라토스테네스의 체는 N보다 작거나 같은 모든 소수를 찾는 유명한 알고리즘이다.

	이 알고리즘은 다음과 같다.

	2부터 N까지 모든 정수를 적는다.
	아직 지우지 않은 숫자 중 가장 작은 수를 찾는다. 이것을 P라고 한고, 이 수는 소수이다.
	P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
	아직 모든 숫자를 지우지 않았다면, 다시 2번 단계로 간다.
	N, K가 주어졌을 때, K번째 지우는 수를 구하는 프로그램을 작성하시오.

	입력
	첫째 줄에 N과 K가 주어진다. (2 ≤ K < N ≤ 1000)

	출력
	첫째 줄에 K번째 지워진 수를 출력한다.
*/	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int result = doEratosSieve(N,K);
		System.out.println(result);
	}

	private static int doEratosSieve(int n, int k) {
		int [] arr = new int [n-1];
		arr[0]=2;
		for(int i=1;i<arr.length;i++){
			arr[i] = arr[i-1]+1;
		}
		int idx = 0;	
		for(int i=0;i<arr.length;i++){
			if(arr[i]!=0){
				int thisNum = arr[i];
				arr[i]=0; k--; 	
				if(k==0) {
					idx=i;
					break;					
				}
				for(int j=i+1;j<arr.length;j++){
					if(arr[j]!=0){
						if(arr[j]%thisNum==0){
							arr[j]=0;k--; 										
						}
						if(k==0) {
							idx=j;
							break;					
						}							
					}
				}
			}			
		}
		return idx+2;
	}
/*
	#include "stdio.h"
	#define MAXN 1000
	int N, K;
	int visited[MAXN + 1], cnt = 0;
	int main()
	{
	    scanf("%d %d", &N, &K);
	    int i, j;
	    for (i = 2; i <= N && cnt < K; i++)
	    {
	        if (visited[i] == 1) continue;
	        for (j = i; j <= N; j += i) {
	            cnt += (1 - visited[j]);
	            visited[j] = 1;
	            if (cnt >= K) break;
	        }
	    }
	    printf("%d\n", j <= N ? j : 0);
	    return 0;
	}	
*/
}
