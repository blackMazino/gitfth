package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//사선DP
public class Day4_04_DP_matrixChainMultiplication {
/*
문제
M×N 크기의 행렬 A와 N×K 크기의 행렬 B의 행렬 곱셈 AB를 생각해보자. 두 행렬의 곱셈 과정의 연산 횟수는 MNK 이다. 
행렬 곱셈에는 교환 법칙 AB=BA는 성립하지 않지만, 결합 법칙 (AB)C=A(BC)는 성립한다. 
결합 법칙에 의해 어떤 순서로 곱셈을 하는지에 따라 전체 연산 횟수가 차이난다.

예를 들어, 세 행렬 A1,A2,A3가 있다고 하자. 
A1은 10×100 크기, A2는 100×5 크기, A3은 5×50 크기의 행렬이다. 
(A1A2)A3 순서로 곱셈을 할 경우, 전체 10×100×5+10×5×50=7,500번의 연산이 필요하고, 
A1(A2A3) 순서로 곱셈을 할 경우, 전체 100×5×50+10×100×50=75,000번의 연산이 필요하다.

n개의 행렬 A1,A2,A3,…,An이 있다. 행렬 Ai의 크기는 ai×ai+1이다. 
이 때, A1A2A3…An을 계산하는데 필요한 연산의 최소 횟수를 구하는 프로그램을 작성하라.

입력
첫 줄에 행렬의 개수 n이 주어진다. (2≤n≤500)

둘째 줄에 행렬의 크기를 나타내는 자연수 n+1개가 공백으로 구분되어 주어진다. 
i번째로 주어지는 수는 a_i이다. (a_i≤100)

출력
첫 줄에 A1A2A3…An를 구하기 위해 필요한 연산의 최소 횟수를 출력한다.

3
10 100 5 50

7500
*/
	static int N;
	static int []A;
	static int [][]D;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			A = new int[N+2];
			D = new int[N+2][N+2];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N+1;i++){
				A[i] = Integer.parseInt(st.nextToken());
				//우하향 대각선 0으로 초기화
				D[i][i] = 0;
			}
			/* P(n) = sig k=1-> n-1 P(k)*P(n-k)
			 * 
			 * D[s][e] = min (D[s][k] + D[k+1][e] + P(s-1)*P(k)*P(e))
			 * 
			 * 점화식 D[s][e] = min(D[s][k] + D[K+1][e] + A[s] x A[K+1] x A[e+1]) (s<=K<e)
			 * i= 구간의 크기, j = 시작위치
			 * s = j, e = j+i-1
			 * -> ans = D[1][N];
			 * */
			//사선DP를 그린다
			for(int i=2;i<=N;i++){
				for(int j=1;j+i-1<=N;j++){
					int min = -1;
					for(int k = j; k<j+i-1;k++){
						if(min == -1
						|| min > D[j][k] + D[k+1][j+i-1] + A[j] * A[k+1] * A[j+i-1+1]){
							min =  D[j][k] + D[k+1][j+i-1] + A[j] * A[k+1] * A[j+i-1+1];													
						}
					}
					D[j][j+i-1] = min;
				}
			}
			
			System.out.println(D[1][N]);
//		}
		br.close();
	}
}

/*
//Matrix Chain Multiplication
import java.io.*;
import java.util.*;
  
public class source {
    static int N;
    static int[] A;
    static int[][] cache;
    static boolean[][] calculated;
      
    static int dy(int s, int e) {
        if (calculated[s][e]) return cache[s][e];
        calculated[s][e] = true;
        if (s == e) return cache[s][e] = 0;
        if (e-s == 1) return cache[s][e] = A[s] * A[s+1] * A[s+2];
        int ret = (int)2e9;
        for (int k=s;k<e;k++){
            int v = dy(s, k) + dy(k+1, e) + A[s] * A[k+1] * A[e+1];
            ret = Math.min(ret, v);
        }
        return cache[s][e] = ret;
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N+2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N+1;i++) A[i] = Integer.parseInt(st.nextToken());
          
        cache = new int[N+1][N+1];
        calculated = new boolean[N+1][N+1];
        System.out.println(dy(1, N));
    }
}
*/