package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day4_EratosthenesSieve {
/*	
	���� 2960
	�����佺�׳׽��� ü�� N���� �۰ų� ���� ��� �Ҽ��� ã�� ������ �˰����̴�.

	�� �˰����� ������ ����.

	2���� N���� ��� ������ ���´�.
	���� ������ ���� ���� �� ���� ���� ���� ã�´�. �̰��� P��� �Ѱ�, �� ���� �Ҽ��̴�.
	P�� �����, ���� ������ ���� P�� ����� ũ�� ������� �����.
	���� ��� ���ڸ� ������ �ʾҴٸ�, �ٽ� 2�� �ܰ�� ����.
	N, K�� �־����� ��, K��° ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

	�Է�
	ù° �ٿ� N�� K�� �־�����. (2 �� K < N �� 1000)

	���
	ù° �ٿ� K��° ������ ���� ����Ѵ�.
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
