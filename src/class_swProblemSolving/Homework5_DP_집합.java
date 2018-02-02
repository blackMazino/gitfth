package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Homework5_DP_집합 {
/*
합A와B에 각각N,M개의 자연수가 들어있다. 이제 다음의 행동을min(N,M)번 시행할 것이다.

집합 A,B
에서 각각 자연수 하나씩 고르고, 고른 수는 각 집합에서 삭제한다
고른 두 수의 차를 그룹C에 넣는다.우리의 목표는 그룹C에 있는 원소의 합을 최소로 하는 것이다.

입력
첫 번째 줄에N,M이 공백으로 분리되어 주어진다. (1≤N,M≤1,000)
두 번째 줄에 집합A의 원소인N개의 자연수가 공백으로 분리되어 주어진다.
세 번째 줄에 집합B의 원소인M개의 자연수가 공백으로 분리되어 주어진다.

모든 집합의 원소는 1 이상1,000,000 이하이다.
출력
첫 번째 줄에 그룹C의 원소합의 최소값을 출력한다.

2 1
10 20
30

10

 * */
	static int N;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			
			
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
}

/*
//집합 
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;  
  
import java.util.Arrays;
  
  
   
import java.text.NumberFormat; 
//import java.math;
  
public class source {     
*/  
//  집합 
/*  
2 1
10 20
30
  
10
  
3 2
10 5 20
30 4
  
11
*/
/*      
    static int n, m;
    static int[] ns, ms;
    static int[][] dp;
      
    public static void main(String[] args) throws Exception {       
          
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
          
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ns = new int[n+1];
        ms = new int[m+1];
        dp = new int[n+1][m+1];
          
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            ns[i] =  Integer.parseInt(st.nextToken());
        }
          
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=m; i++) {
            ms[i] =  Integer.parseInt(st.nextToken());
        }         
           
        Arrays.sort(ns);
        Arrays.sort(ms);
            
        for (int i = 1; i <= n; i++) {
           
            for (int j = 1; j <= m ; j++) {
                dp[i][j] = dp[i-1][j-1] + Math.abs(ns[i] - ms[j]);
                  
                if ( i < j && dp[i][j] > dp[i][j-1])
                    dp[i][j] = dp[i][j-1];
  
                if ( i > j && dp[i][j] > dp[i-1][j])
                    dp[i][j] = dp[i-1][j];
            } 
        }
           
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
           
        br.close();
          
        bw.write(String.valueOf(dp[n][m])); 
          
        bw.flush();
        bw.close();
    }  
}
 */