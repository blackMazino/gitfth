package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day5_01_DP_합분해 {
/*
문제0부터N까지의 정수K개를 더해서 그 합이N이 되는 경우의 수를 구하는 프로그램을 작성하시오.

덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.

입력
첫 번째 줄에 두 정수 N,K가 공백으로 분리되어 주어진다. 
(1≤N,K≤200)
출력
첫 번째 줄에 답을1,000,000,000으로 나눈 나머지를 출력한다.

20 2

21

 * */
	static int N,K;
	static int MOD = (int) 1e9;
	static int [][] D;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			/* D[i][j] = i개의 숫자를 사용해서 J를 만드는 경우의 수
			 * N = 4, K = 3
			 *       최우측을 빼고보면
			 * 0 0 | 4 D[2][0]
			 * 0 1 | 3 D[2][1]
			 * 1 0 | 3
			 * 0 2 | 2 D[2][2]
			 * 1 1 | 2
			 * 2 0 | 2
			 * 0 3 | 1 D[2][3]
			 * 1 2 | 1
			 * 2 1 | 1 
			 * 3 0 | 1
			 * 0 4 | 0 D[2][4]
			 * 1 3 | 0
			 * 2 2 | 0 
			 * 3 1 | 0
			 * 4 0 | 0
			 * 
			 * D[3][4] = D[2][0] + D[2][1] + ... + D[2][4] 
			 *         = sig k:0->4 (D[2][k])
			 * 일반화
			 * D[i][j] = sum(D[i-1][k]) 0<=k<=j
			 * 그런데
			 * 위의 배열에서 
			 * 
			 * D[3][4] = (D[2][0]+...+D[2][3])+D[2][4]
			 *         = D[2][4] + D[3][3] 으로 표기할 수 있다!!(D[i-1][j]이므로 i-1 = 2, i=3)
			 * 
			 * 따라서 D[i][j] = D[i-1][j]+D[i][j-1]
			 * */
			D = new int[K+1][N+1];
			D[0][0] = 1;
			for(int i=1;i<=K;i++){
				D[i][0] = D[i-1][0];
				for(int j=1;j<=N;j++ ){
					D[i][j] = ( D[i-1][j]+D[i][j-1] )%MOD;
				}				
			}
			System.out.println(D[K][N]);
//		}
		br.close();
	}
}

/*
//합분해
 
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;  
  
import java.text.NumberFormat; 
 
public class source {     
 
    static int n, k; //, result;  
    static int[][] dp;    
     
    public static void main(String[] args) throws Exception {
  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken()); // n까지의 정수 
        k = Integer.parseInt(st.nextToken()); // k개..
          
        dp = new int[k+1][n+1];
        dp[0][0] = 1;
         
        for (int i = 1; i < k+1; i++) {  //   2
            
           for (int j = 0; j <=n; j++){
               for (int l = 0; l <= j; l++) {
                  dp[i][j] = (dp[i][j] + dp[i-1][j-l]) % 1000000000; 
               }
           }
        }
      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            
         
        br.close();
         
        bw.write(String.valueOf(dp[k][n])); 
         
        bw.flush();
        bw.close();
    } 
}
*/
