package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_KOITP_폐지두번줍기 {

	 public static int TC,N,M;
	 public static int[][] dp,A;
	   
	 public static void main(String[] args) throws Exception{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st ;
		 TC = Integer.parseInt(br.readLine());

		 for(int tc=1;tc<=TC;tc++){
			 st = new StringTokenizer(br.readLine());
			 M = Integer.parseInt(st.nextToken());
			 N = Integer.parseInt(st.nextToken());
			 A = new int[M+1][N+1];
			  for(int i=1; i<=M; i++){
				  String line = br.readLine();
				  for(int j=1; j<=N; j++){
					  A[i][j] = line.charAt(j);
				  }
			  }
		 }
	  
	  
	    
	  

	    
	  dp = new int[N+1][N+1];
	  for(int i=1;i<=N;i++) {
		  for(int j=1;j<=N;j++){
			  if(i ==1 && j ==1) dp[i][j] = A[1][1];
			  if(i>1) dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + A[i][j]);
			  if(j>1) dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + A[i][j]);
		  }	   
	  }

	    
	  System.out.println(dp[N][N]);
	    
	 }

}


//public class source {
//
//public static void main(String[] args) throws NumberFormatException, IOException {
//
//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
//	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out) );
//
//	int T = Integer.parseInt(br.readLine());
//
//	int M = Integer.parseInt(br.readLine());
//	int N = Integer.parseInt(br.readLine());
//	int[][] roads = new int[M+1][N+1];
//
//	
//	for( int t = 0 ; t < T; t++ ) {
//		for(int m = 1; m < M+1; m++ ) {
//			String line= br.readLine();
//			for(int n = 1; n < N+1; n++) {
//				roads[m][n] = line.charAt(n);
//			}
//			/**
//			 *  D(i,j,k) 
//			 *  	i는 가로 or 세로를 이동하여 움직인 총 turn수
//			 *  	j는 내려가면서 폐지를 줍는 사람의 x 좌표
//			 *     k는 올라가면서 폐지를 줍는 사람의 x 좌표 ( 내려가고 올라가고 하는 두번의 동작을 time series로 선행 후행 따지지 말고 그냥 상태를 
//			 *     하기 위해서 그냥 같이 내려가면서 줍는걸로 역발상!!		
//			 */
//			int D[][][] = new int[200+1][100+1][100+1];
//			
//			// '*'는 갈 수 있으며 폐지가 있는 곳을 나타낸다.
//			//   '.'는 갈 수 있지만 폐지가 없는 곳을 나타낸다.
//			//   '#'는 갈 수 없는 곳을 나타낸다.
//			
//			for(int i = 1; i < M+N; i++) {
//				for(int j = 1 ; j < M; j++) {
//					for(int k = 1; k < N; k++) {
//						if( k > i  || j > k) continue;
//
//						if( i == 0 && j == 0 && k == 0 ) D[i][j][k] = 0;
//						if( i == 1 && roads[0][0] == '*' ) D[i][j][k] = 1;
//
//						
//						// 지금 현재 i번째 대각선의 j번째 x좌표의 그 포인트에서 가장 많이 폐지를 주울 수 있는 가지수!
//						
//						
//						D[i][j][k] =  Math.max( 
//
//								/*
//								 *  
//								 */
//								Math.max(  D[i-1][j-1][k]  , D[i-1][j][k]) ,  
//								
//								Math.max(  D[i-1][j][k-1] , D[i-1][j][k]) 
//						);
//						
//						
//					}
//				}
//			}
//			
//		}
//	}
//}
//}
