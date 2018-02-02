package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//못품
public class Day6_02_AD_세금 {

/*
납세의 의무는 국민의 기본적인 의무이다. 세금은 수입에 비례하여 정해진 규칙에 따라 계산되기 때문에, 규칙에 따라서 정확한 수입을 계산하는 것이 중요하다. 여러분은 새로 가게를 열고, 총 N 일 동안 영업을 하였다. 납세의 의무를 성실하게 수행하기 위하여 매 영업일마다 손익(이익 또는 손해)을 기록하여 세무서에 신고하였다. 세금을 매기는데 기준이 되는 수입은 다음 규칙에 의해서 계산된다.

"1일부터 N일 사이의 어떤 연속된 기간에 대하여 이 기간 동안 손익의 총합을 구한다. 단, 하루 이상의 기간만 고려한다. 다음, 전체 가능한 모든 기간에 대하여 구한 손익의 총합들 중 K번째로 큰 값이 기준이 된다. 즉, 총합들을 내림차순으로 정렬했을 때 K번째 값이다."

예를 들어, 총 3일간 영업을 하였다면 1일, 1일~2일, 1일~3일, 2일, 2일~3일, 3일 총 6가지 기 간에 대하여 각 기간마다 손익의 총합을 구하고, 이 중 K번째 큰 값이 세금의 기준이 된다.

입력
첫 번째 줄에는 N과 K가 사이에 하나의 공백을 두고 주어진다. 단, 1≤N≤1,000,000, 1≤K≤min(50, N(N+1)/2) 이다.

다음 줄에는 매 영업일의 손익을 나타내는 N개의 정수가 사이에 하나 의 공백을 두고 주어진다. 만약 해당 영업일에 이익을 보았다면 양의 값, 손해를 보았다면 음의 값, 이익 손해 모두 없다면 0이다. 손익의 범위는 -1,000,000,000 부터 1,000,000,000 사이이다.

출력
조건을 만족하는 손익의 총합이 K번째로 큰 값을 나타내는 하나의 정수를 출력한다.

4 3
20 -10 10 5

20
*/
	static int N,K;
	static long []a, D, ans, temp;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			a = new long [N+1];			
			int kMax = Math.min( 50,  (N*(N+1)/2) );
			D = new long [kMax];
			ans = new long [kMax]; 
			temp = new long [kMax]; 
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++){
				a[i] = Integer.parseInt(st.nextToken());
			}
			/* D[i][j] = i일일때 J번째까지의 최대값
			 *         = D[i-1][j] = A[i]
			 * 단, k번째 이후 값은 무시해도 된다. 그러므로 a[k-1]가 답(i가 0부터임)         
			 * 
			 * 
			 */
			int i,j;
			for(i=0; i<N;i++){
				for(j=0;j<Math.min(K, i);j++){
					D[j] += a[i];
				}
				j = Math.min(K,i);
				while(j>0 && D[j-1] < a[i]){
					D[j] = D[j-1];
					j--;
				}
				D[j] = a[i];
				
				//MergeSort
				int p1 = 0; 
				int p2 = 0;
				for(j=0; j< Math.min((i+1)*(i+2)/2, K);j++){
					if(p1== Math.min(K, i+1)) 				temp[j] = ans[p2++];
					else if(p2 == Math.min(K, ((i+1)*i/2))) temp[j] = D[p1++];
					else if(D[p1]>ans[p2]) 					temp[j] = D[p1++];
					else 									temp[j] = ans[p2++];
				}
				
				for(j=0;j<Math.min((i+1)*(i+2)/2,  K);j++){
					ans[j] = temp[j];					
				}
			}
			System.out.println(ans[K-1]);
//		}
		br.close();
	}
}

/*
//세금
import java.io.*;
import java.util.*;
  
public class source {
    static int N, K;
    static long[] A, B, C;
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new long[K+1]; B = new long[K+1]; C = new long[K+1];
        for (int i=1;i<=K;i++) A[i] = B[i] = (long)-1e18;
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            int v = Integer.parseInt(st.nextToken());
            for (int j=1;j<=K;j++) A[j] += v;
            for (int j=1;j<=K;j++) if (A[j] < v){
                for (int k=K-1;k>=j;k--) A[k+1] = A[k];
                A[j] = v;
                break;
            }
            for (int j=1,l=1,r=1;j<=K;j++){
                if (A[l] > B[r]) C[j] = A[l++];
                else C[j] = B[r++];
            }
            for (int j=1;j<=K;j++) B[j] = C[j];
        }
        System.out.println(B[K]);
    }
}
*/
