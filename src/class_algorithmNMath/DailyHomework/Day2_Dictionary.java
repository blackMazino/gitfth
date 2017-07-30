package class_algorithmNMath.DailyHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day2_Dictionary {

/*
문제
동호와 규완이는 212호에서 문자열에 대해 공부하고 있다. 김진영 조교는 동호와 규완이에게 특별 과제를 주었다. 특별  과제는 특별한 문자열로 이루어 진 사전을 만드는 것이다. 사전에 수록되 있는 모든 문자열은 N개의 "a"와 M개의 "z"로 이루어져 있다. 그리고 다른 문자는 없다. 사전에는 알파벳 순서대로 수록되어 있다.

규완이는 사전을 완성했지만, 동호는 사전을 완성하지 못했다. 동호는 자신의 과제를 끝내기 위해서 규완이의 사전을 몰래 참조하기로 했다. 동호는 규완이가 자리를 비운 사이에 몰래 사전을 보려고 하기 때문에, 문자열 하나만 찾을 여유밖에 없다.

N과 M이 주어졌을 때, 규완이의 사전에서 K번째 문자열이 무엇인지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N, M, K가 순서대로 주어진다. N과 M은 100보다 작거나 같은 자연수이고, K는 1,000,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 규완이의 사전에서 K번째 문자열을 출력한다. 만약 규완이의 사전에 수록되어 있는 문자열의 개수가 K보다 작으면 -1을 출력한다
 * */
	static int kMax = 1000000000;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;		
//		st = new StringTokenizer(br.readLine());		
//		int TC = Integer.parseInt(st.nextToken());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());//cnt of a
			int M = Integer.parseInt(st.nextToken());//cnt of z
			int K = Integer.parseInt(st.nextToken());//K번째 문자열
					
			String str = getStrByQueue(N,M,K);			
			System.out.print(str);
//		}	
	}

	private static String getStrByQueue(int N, int M, int K) {
		int L = N+M;
		Queue<Integer> q = new LinkedList<Integer>(); 	
		if(Math.pow(2, L) >= K){//문자열 조합이 K보다같거나 꺼야 찾을수 있다 없다면 -1			
			int C[][] = new int [L+1][L+1];// N,M <100			
			C = makePascalsTri(L);//파스칼의 삼각형 만들기
			
			if(C[L][M] < K){
				return "-1";
			}
			
			//print(C);
			//queue 사용				
			for(int i=1;i<=L;i++){
				if(C[L-i][M] >= K) continue;
				q.offer(i);
				K-=C[L-i][M];
				M--;
			}
			String str = "";
			for(int i=1;i<=L;i++){
				if(!q.isEmpty() && q.peek() ==i){
					//System.out.print("z"); 
					str += "z";
					q.poll();
				}
				else {
					//System.out.print("a");
					str += "a";
				}
			}
			return str;				
		}else{
			return "-1";	
		}
	}

	private static int[][] makePascalsTri(int L) {
		int C[][] = new int [L+1][L+1];	
		C[0][0] = 1;
		for(int i=1;i<=L;i++){
			C[i][0]=1;//첫번째 열은 1
			for(int j=1;j<=i;j++){
				C[i][j] = Math.min(kMax, C[i-1][j]+C[i-1][j-1]);
			}
		}
		return C;
	}

	private static void print(int[][] c) {
		for(int i=1;i<c.length;i++){			
			for(int j=1;j<c.length;j++){
				System.out.print(c[i][j]);
			}	
			System.out.println();
		}	
	}
/*	
	#include "stdio.h"
	#include <queue>
	#define min(x, y) ((x)>(y)?(y):(x))
	using namespace std;
	int N, M, K, L;
	int C[201][201];
	queue<int> zpos;
	int main()
	{
	    scanf("%d %d %d", &N, &M, &K);
	    L = N + M;
	    C[0][0] = 1;
	    for (int i = 1; i <= L; i++)
	    {
	        C[i][0] = 1;
	        for (int j = 1; j <= i; j++) C[i][j] = min(1e9, C[i - 1][j] + C[i - 1][j - 1]);
	    }
	    if (C[L][M] < K)
	    {
	        printf("-1\n");
	        return 0;
	    }

	    for (int i = 1; i <= L; i++)
	    {
	        if (C[L - i][M] >= K) continue;
	        zpos.push(i);
	        K -= C[L - i][M];
	        M--;
	    }
	    for (int i = 1; i <= L; i++)
	    {
	        if (!zpos.empty() && zpos.front() == i) printf("z"), zpos.pop();
	        else printf("a");
	    }
	}
*/
}
