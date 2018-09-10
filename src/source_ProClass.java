import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class source_ProClass {

}

////막대기 자르기
//
//
//class sourceDay1_1 {
//	static int N;
//	static int i;
//	static int cost[];
//	static int ans[];
//	
//	static int ANSWER;
//	
//    	  public static void main( String[] args ) throws Exception{
//              	 	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );
//              	 	
//              	 	
//              	 	N = Integer.parseInt( br.readLine() );
//              	 	cost = new int [N + 1];
//              	 	ans = new int [N + 1];
//              	 	ANSWER = 0;
//              	 	i = 0;
//              	 	StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//              	 	while( st.hasMoreTokens() ) {
//              		  	 i++;
//              		  	 cost[ i ] =  Integer.parseInt ( st.nextToken() );   	
//              	 	}
//              		
//              	 	
//              	 	for(int i=1; i<=N ;i++) {
//              		  	  int maxValue = -1;
//              		  	  for( int j=1 ; j <= i ; j++ ) {
//              		            	  if( maxValue  <  cost[j] + ans[i-j] )
//              		                       	  maxValue = cost[j] + ans[i-j];
//              		  	  }
//              		  	  ans[i] = maxValue;
//              	 	}
//              		  	   
//              		System.out.printf( "%d\n", ans[N] ); 
//    	  }
//}
//
//
//
//
////Assembly Line Scheduling
//
//public class sourceDay1_2 {
//
//    	public static void main( String[] args ) throws Exception {
//              	
//              	BufferedReader br = new BufferedReader( new InputStreamReader( System.in));
//              	
//              	StringTokenizer st = new  StringTokenizer ( br.readLine() );
//              	int N = Integer.parseInt( st.nextToken() );
//              	int e1 = Integer.parseInt( st.nextToken() );
//              	int e2 = Integer.parseInt( st.nextToken() );
//              	int x1 = Integer.parseInt( st.nextToken() );
//              	int x2 = Integer.parseInt( st.nextToken() );
//              	
//              	int S[][] = new int[2][N+1];
//              	int T[][] = new int[2][N+1];
//              	int A[][] = new int[2][N+1];
//              	
//              	StringTokenizer st0 = new  StringTokenizer ( br.readLine() );
//              	StringTokenizer st1 = new  StringTokenizer ( br.readLine() );
//              	for( int i=1; i<=N ; i++) {
//              	   S[0][i] =Integer.parseInt( st0.nextToken() );
//              	   S[1][i] =Integer.parseInt( st1.nextToken() );
//              	}
//              	st0 = new  StringTokenizer ( br.readLine() );
//              	st1 = new  StringTokenizer ( br.readLine() );
//              	for( int i=2; i<=N ; i++) {
//              	   T[0][i] =Integer.parseInt( st0.nextToken() );
//              	   T[1][i] =Integer.parseInt( st1.nextToken() );
//              	}
//              	
//              	A[0][0] =  e1;
//              	A[1][0] =  e2;
//
//              	A[0][1] =  S[0][1] + A[0][0];
//              	A[1][1] =  S[1][1] + A[1][0];
//              	
//              	for( int i=2; i<=N ; i++) {
//                         	A[0][i]  = Math.min( A[0][i-1] + S[0][i] ,  A[1][i-1] + T[1][i] + S[0][i]);
//                         	A[1][i]  = Math.min( A[1][i-1] + S[1][i] ,  A[0][i-1] + T[0][i] + S[1][i]);	
//              	}                	
//              	
//              	int ANSWER = Math.min( A[0][N] + x1 ,  A[1][N] + x2 );
//              	System.out.printf("%d\n",ANSWER);
//    	}
//}
////LCS
//public class sourceDay1_3 {
//    	public static void main( String[] args ) throws Exception {           	
//              	BufferedReader br = new BufferedReader( new InputStreamReader( System.in));                	
//              	StringBuilder ANSWER = new StringBuilder("");
//              	String S1 =  br.readLine() ;
//              	String S2 =  br.readLine() ;
//              	if(S1.length() > S2.length()) {
//                         	String tmp = S1;
//                         	S1 = S2;
//                         	S2 = tmp;
//              	}                	
//              	int len[][] = new  int[S1.length() + 1][S2.length() + 1];
//              	int select[][] = new  int[S1.length() + 1 ][S2.length() + 1];
//              	for( int i=1; i <= S1.length() ; i++) {
//                         	for( int j=1; j <= S2.length() ; j++) {                                      	
//                                   	 if( S1.substring(i-1,i).equals( S2.substring(j-1,j) ) ) {
//                                              	 len[i][j]     = len[i-1][j-1] + 1;
//                                              	 select[i][j] = 3;                                         	 
//                                   	 } else {
//                                              	 if(  len[i][j-1]  >  len[i-1][j] ) {
//                                                         	 len[i][j]     = len[i][j-1];
//                                                         	 select[i][j] = 2;
//                                              	 } else {
//                                                         	 len[i][j]     = len[i-1][j];
//                                                         	 select[i][j] = 1;
//                                              	 }
//                                   	 }     	
//                         	}
//              	}                	
//              	int i = S1.length();
//              	int j = S2.length();
//              	
//              	while( i >0 && j > 0 ) {
//                         	 if( select[i][j] == 3  ) {
//                                   	 //System.out.printf("%s\n",S1.substring(i-1,i));
//                                   	 
//                                   	  ANSWER.append(  S1.substring(i-1,i)  ) ;
//                                   	  i--;
//                                   	  j--;
//                         	 }  else if( select[i][j] == 2  )  j--;
//                         	 else if( select[i][j] == 1  )  i--;
//              	}                           	
//              	System.out.printf("%s\n",ANSWER.reverse());
//    	}
//}
//
////Matrix Chain Multiplication
//
//public class sourceDay1_4 {
//	static int N;
//	static int m[];
//	static int ans[][];
//	
//	static int ANSWER;
//	
//    	  public static void main( String[] args ) throws Exception{
//              	 	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );
//              	 	
//              	 	
//              	 	N = Integer.parseInt( br.readLine() );
//              	 	m = new int [N + 1];
//              	 	ans = new int [N + 1][N + 1];
//              	 	ANSWER = 0;
//              	 	StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//              	 	for( int i = 0; i<  m.length ; i++ ) {
//              		  	 m[ i ] =  Integer.parseInt ( st.nextToken() );      	
//              	 	} 
//              	 	
//    	       	     for(int j=1;j<=N ;j++) {
//              		  	  for( int i=1 ; i <= N-j ; i++ ) {
//              		            	  int min = Integer.MAX_VALUE;
//              		            	  int jj = i+j;
//              		            	  
//              		            	  for( int x = i ; x < jj ; x++) {
//              		                       	  min = Math.min(  min , ans[i][x] + ans[x+1][jj] + m[i-1]*m[x]*m[jj] );
//              		            	  }
//              		            	  ans[i][jj] =  min;
//              		  	  }
//              	 	}
//    	/*                          	for( int i=1; i <= N ; i++) {
//                                              	for( int j=1; j <= N ; j++) {
//                                                         	System.out.printf("%d ", ans[i][j] );
//                                              	}
//                                              	System.out.printf("\n");
//                                   	}                	
//                                   	*/		   
//              		System.out.printf( "%d\n", ans[1][N] ); 
//    	  }
//}
//
//
//
//
////동전교환
//
//public class sourceDay1_5 {
//	static int N;
//	static int coin[];
//	static int money;
//	static int restM[];
//	
//    	  public static void main( String[] args ) throws Exception{
//              	 	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );         	 	
//              	 	
//              	 	N = Integer.parseInt( br.readLine() );
//              	 	coin = new int [N + 1];
//
//              	 	StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//              	 	for( int i = 1; i<  coin.length ; i++ ) {
//              		  	 coin[ i ] =  Integer.parseInt ( st.nextToken() );   	                 	     }
//              	 	
//              	 	money = Integer.parseInt( br.readLine() );
//              	 	restM = new int [ money + 1];                	 	
//              	  
//
//              	 	Arrays.fill( restM , money);
//              	 /*   	 for( int i=1; i <= money ; i++) {
//                                              	System.out.printf("%d ", restM[i] );
//                         	 }               	 	
//              	      	 System.out.printf( "\n" );
//              	      	 */
//              	 	for( int j=1 ; j <= money  ; j++ ) {
//              		  	 
//              		  	 for(int i=1;i<=N ;i++) {          	
//              		            	  if( j < coin[i] ) continue;
//              		            	  if( j == coin[i]  ) restM[j] = 1;
//              		            	  else
//              		            	     restM[j] = Math.min(   restM[ j - coin[i] ] + 1 , restM[j] );
//              		  	  }
//              	 	}
//              	 	
//    	                 	/*
//    	                 	 * for( int i=1; i <= money ; i++) {
//                                                         	System.out.printf("%d ", restM[i] );
//                                   	}      	   
//    	                 	System.out.printf( "\n" );
//    	                 	*/
//              		System.out.printf( "%d\n", restM[money] ); 
//    	  }
//}
////이친수
//public class sourceDay2_1 {
//	static int N;
//	static long dp[];
//	
//    	  public static void main( String[] args ) throws Exception{
//              	 	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );         	 	
//              	 	
//              	 	N = Integer.parseInt( br.readLine() );
//              	 	dp = new long [N + 1];
//              	 	dp[1] = 1;
//              	 	for( int i=2; i<=N; i++) {
//              		  	 dp[i] = dp[i-1] + dp[i-2];
//              	 	}          	 	
//              		System.out.printf( "%d\n", dp[N] ); 
//    	  }
//}
//
////합분해
//public class sourceDay2_2 {
//	static int N, K;
//	static int dp[][];
//	
//    	  public static void main( String[] args ) throws Exception{
//              	 	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );         	 	
//              	 	
//              	 	StringTokenizer st = new StringTokenizer( br.readLine() );
//              	 	N = Integer.parseInt( st.nextToken() ) ;
//              	 	K = Integer.parseInt( st.nextToken() ) ;
//              	 	
//              	 	dp = new int [K + 1] [N + 1];
//              	 	
//              	 	for( int i=1; i<=N; i++) {
//              		  	 dp[1][i] = 1;
//              	 	}
//              	 	
//              	 	for( int i=2; i<=K; i++) {
//              		  	 dp[i][1] = i;
//              		  	 for( int j=2; j<=N; j++) {
//              	     	      	 dp[i][j] = (  dp[i-1][j] + dp[i][j-1]   ) % 1000000000;
//              	     	}
//              	 	}
//              	 	
//              		System.out.printf( "%d\n", dp[K][N]); 
//    	  }
//}
//
////최대구간합
//package kkh;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class sourceDay2_3 {
//	static int N;
//	static int NUM[];
//	static long dp[];
//	static long max;
//	
//    	  public static void main( String[] args ) throws Exception{
//              	 	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );
//              	 	
//              	 	N = Integer.parseInt( br.readLine()) ;
//              	 	NUM = new int  [N + 1];
//              	 	
//              	 	StringTokenizer st = new StringTokenizer( br.readLine() );
//              	 	for( int i=1; i<=N;i++) {
//              		  	 NUM[i]= Integer.parseInt( st.nextToken() ) ;
//              	 	}
//              	 	
//              	 	dp = new long [N + 1];
//              	 	
//              	 	dp[1] = NUM[1];
//              	 	max =  dp[1] ;
//              	 	for( int i=2; i<=N; i++) {
//              		  	 dp[i] = Math.max( dp[i-1], 0) + NUM[i];
//              		  	 
//              		  	 max = Math.max( max, dp[i] );
//              	 	}
//              	 	
//              		System.out.printf( "%d\n", max  ); 
//    	  }
//}
//
//
//
//
//
//
//
//
//
//
//
////인접한 비트의 개수 
//
//package kkh;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class sourceDay2_4 {
//    	static int T, NO, N, K;
//    	static int NUM[];
//    	static long dp[][][];
//    	static long answer;
//
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//              	T = Integer.parseInt(br.readLine());
//
//              	for (int t = 0; t < T; t++) {
//                         	StringTokenizer st = new StringTokenizer(br.readLine());
//                         	NO = Integer.parseInt(st.nextToken());
//                         	N = Integer.parseInt(st.nextToken());
//                         	K = Integer.parseInt(st.nextToken());
//
//                         	dp = new long[101][101][2];
//
//                         	dp[1][0][0] = 1;
//                         	dp[1][0][1] = 1; 
//              		for (int i = 2; i < 101; i++) {
//              	  	for (int j = 0; j < i; j++) {
//              		  	  dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1];
//              		  	  dp[i][j][1] = dp[i-1][j][0] + ((j>0) ? dp[i-1][j-1][1] : 0);
//              	  	}
//              		}
//
//
//                         	System.out.printf("%d %d\n", NO , dp[N][K][0] + dp[N][K][1] );
//              	}
//    	}
//}
//
//
//
//
//
//
////롤러코스터
//public class sourceDay2_5 {
//    	static int L, N, B, X , W[], F[], C[];
//    	static int D[][], ans ;
//    	static ArrayList<Integer>node[];
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//              	StringTokenizer st = new StringTokenizer(br.readLine());
//              	L = Integer.parseInt(st.nextToken());
//              	N = Integer.parseInt(st.nextToken());
//              	B = Integer.parseInt(st.nextToken());  	
//              	node = new ArrayList [L] ;
//              	for (int i = 0; i < L; i++) node[i] = new  ArrayList();        	
// 	        W = new int [N+1] ;
// 	        F = new int [N+1] ;
// 	        C = new int [N+1] ;
//              	for (int i = 1; i <= N; i++) {
//                         	st = new StringTokenizer(br.readLine());
//                         	X  = Integer.parseInt(st.nextToken());
//                         	W[i]  = Integer.parseInt(st.nextToken());
//                         	F[i]   = Integer.parseInt(st.nextToken());
//                         	C [i]  = Integer.parseInt(st.nextToken());                      	 
//                         	node[ X ].add( i );
//              	}
//              	D = new int[L+1][B+1];
//              	for (int i = 0; i <= L; i++) {
//                         	for (int j = 0; j <= B; j++) {
//                                   	D[i][j] = -1;
//                         	}
//              	}                	
//              	D[0][0] = 0;              	
//              	for (int i = 0; i < L; i++) {
//                         	for (int j = 0; j <= B; j++) {
//                                   	if ( D[i][j] == -1 ) continue ;                                    	
//                                   	for (int k : node[i]) {
//                                              	if( j + C[k] > B ) continue;                                                 	
//                                              	D[i + W[k]][ j + C[k] ] = Math.max( D[i + W[k]][ j + C[k] ] , D[i][j] + F[k] ) ;
//                                   	}
//                         	}
//              	}                           	
//              	ans = -1;      	
//              	for( int i=0; i<= B ; i++) {
//                         	ans = Math.max( ans , D[L][i]);
//              	}
//              	System.out.printf("%d \n", ans);
//    	}
//}
////폐지 줍기 
//
//public class sourceDay2_6 {
//    	static int  N ;
//    	static int A[][];
//    	static int D[][];
//
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//              	N = Integer.parseInt(br.readLine());
//              	A = new int[N+1][N+1] ;
//              	D = new int[N+1][N+1] ;
//
//              	StringTokenizer st;
//              	for (int i = 1; i <= N; i++) {
//                         	st = new StringTokenizer(br.readLine());
//                         	
//                         	for( int j=1; j <= N ; j++)
//                                   	A[i][j] = Integer.parseInt(st.nextToken()); 
//              	}
//
//              	D[1][1] = A[1][1];
//              	for (int i = 1; i <= N; i++) {
//                         	for (int j = 1; j <= N; j++) {
//                                   	D[i][j] = Math.max(D[i-1][j]  , D[i][j-1])  + A[i][j];
//                         	}
//              	}
//
//              	System.out.printf("%d \n", D[N][N]);
//    	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////돌다리 건너기
//
//package kkh;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class sourceDay2_7 {
//    	
//    	static char[] rol = new char[30];
//    	static char[][] dol = new char[2][120];    	
//    	static int[][] DT = new int[2][30];
//    	static int rc;
//    	 
//    	public static int f(int k) {
//              	for( int i=0; i < dol[1].length ; i++)
//              	{
//                         	for( int t=k-1; t>=0 ; t--)
//                         	{
//                                   	if( dol[0][i]==rol[t]) DT[1][t+1]+=DT[0][t];
//                                   	if( dol[1][i]==rol[t]) DT[0][t+1]+=DT[1][t];
//                         	}
//              	}
//              	return DT[0][k] + DT[1][k];
//    	}
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//              	rol  	= br.readLine().toCharArray();
//              	dol[0] = br.readLine().toCharArray();
//              	dol[1] = br.readLine().toCharArray();
//
//              	rc = rol.length;
//              	 
//              	DT[0][0] = 1;
//              	DT[1][0] = 1;
//              	 
//              	System.out.printf("%d \n",  f(rc));
//    	}
//}
//
//
//
//
//
////돌다리 건너기
//
//package kkh;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class sourceDay2_72 {
//    	
//    	static String rol;
//    	static String[] dol = new String[2];
//    	static int[][][] DT = new int[30][2][120]; 
//    	 
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//              	rol  	= br.readLine();
//              	dol[0] = br.readLine();
//              	dol[1] = br.readLine();
//
//              	 
//              	DT[0][0][0] = 1;
//              	DT[0][1][0] = 1;
//              	
//              	for( int k = 1; k <= rol.length() ; k ++ ) {
//                         	for( int i = 0; i <= 1 ; i++ ) {
//                         		for( int j = 1; j <= dol[0].length() ; j++ ) {
//                                   	   // if( dol[i].charAt(j-1)==rol.charAt(k-1))
//                                   		  	DT[k][i][j]  = DT[k-1][1-i][j-1] + DT[k][i][j-1]; 
//                         		} 
//                         	}
//              	}
//              	for( int k = 0; k <= rol.length() ; k ++ ) {
//                         	for( int j = 0; j <= dol[0].length() ; j++ ) {
//                                   	System.out.printf("%d ",  DT[ k][0][j]  );
//                         	}
//                         	System.out.printf("\n");
//                         	for( int j = 0; j <= dol[0].length() ; j++ ) {
//                                   	System.out.printf("%d ",  DT[ k][1][j]  );
//                         	}
//                         	System.out.printf("\n");                     	
//              	}
//              	System.out.printf("%d \n",  DT[ rol.length()][0][dol[0].length()] );
//    	}
//}
//
////N-Queen
//
//package kkh;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class sourceDay3_1 {
//    	
//    	static int N, ans;
//    	static boolean[] col, d1, d2;
//
//	
//    	  public static void main( String[] args ) throws Exception{
//              	 	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );
//              	 	
//              	 	N = Integer.parseInt( br.readLine() );
//              	 	col = new boolean[N];
//              	 	d1 = new boolean[N+N-1]; d2 = new boolean[N+N-1];
//
//              	 	dfs(0);
//
//              	 	System.out.println(ans);
//
//    	  }
//    	 
//    	  static void dfs(int y) {
//              		if (y == N){ ans++; return; }
//              		for (int x=0;x<N;x++){
//              	    	if (col[x] || d1[y+x] || d2[y-x+N-1]) continue;
//              	    	col[x] = d1[y+x] = d2[y-x+N-1] = true;
//              	    	dfs(y+1);
//              	    	col[x] = d1[y+x] = d2[y-x+N-1] = false;
//              		}
//              	}
//}
//
//
//
//
//
//
//
//
////소수경로
//public class sourceDay3_2 {
//    	static int T, A, B;
//    	static int D[];
//    	static boolean is_prime[];
//
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//              	is_prime = new boolean[10000];
//              	for (int i=1001;i<10000;i+=2){
//              		is_prime[i] = true;
//              		for (int j=2;j*j<=i;j++) if (i % j == 0){
//              	    	is_prime[i] = false;
//              	    	break;
//              		}
//              	}
//              	
//              	T = Integer.parseInt(br.readLine());
//
//              	for (int t = 0; t < T; t++) {
//                         	
//                         	StringTokenizer st = new StringTokenizer(br.readLine());
//                         	A = Integer.parseInt(st.nextToken());
//                         	B = Integer.parseInt(st.nextToken()); 
//
//                         	D = new int[10000];
//                         	for (int i=0;i<10000;i++) D[i] = Integer.MAX_VALUE;
//                         	Queue<Integer> que = new LinkedList<Integer>();
//                         	que.add(A); D[A] = 0;
//                         	while (!que.isEmpty()){
//                         		int q = que.poll();
//                         		for (int b=1;b<10000;b*=10){
//                         	    	for (int d=0;d<10;d++){
//                         	        	int v = q / b / 10 * b * 10 + q % b + b * d;
//                         	        	if (!is_prime[v] || D[v] < Integer.MAX_VALUE) continue;
//                         	        	D[v] = D[q]+1;
//                         	        	que.add(v);
//                         	    	}
//                         		}
//                         	}
//                         	System.out.println(D[B]);
//              	}
//    	}
//}
//
//
////재밌는 게임
//public class sourceDay3_3 {
//    	static int  N,M ;
//    	static int Data[][];
//    	static int Group[][];
//    	static ArrayList Graph[];
//    	static int answer;                 	
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//              	answer = 0;
//              	StringTokenizer st = new StringTokenizer(br.readLine());
//              	N = Integer.parseInt(st.nextToken());
//              	M = Integer.parseInt(st.nextToken());
//              	Data = new int [N+1][M+1];
//              	Group = new int [N+1][M+1];
//              	for (int i = 1; i <= N; i++) {
//                         	st = new StringTokenizer(br.readLine());
//                         	
//                         	for (int j = 1; j <= M; j++) {
//                         		Data[i][j] = Integer.parseInt(st.nextToken());
//                         	}
//              	}      	
//              	Queue<Integer> que = new LinkedList<Integer>();        	
//              	int group = 0;          	
//              	for (int i = 1; i <= N; i++) {
//                         	for (int j = 1; j <= M; j++) {
//                                   	if( Group[i][j] > 0 ) continue;                                   	
//                                   	group ++ ;
//                                   	que.add(i*1000+j);                                      	
//                                   	while (!que.isEmpty()){
//                                              	int q = que.poll();
//                                              	int row = q /1000;
//                                              	int col   = q % 1000;                                            	
//                                              	if( Group[row][col] > 0 ) continue;                                                  	
//                                              	Group[row][col] = group;                                        	
//                                              	if(  row > 1 && Data[row][col]  == Data[row-1][col] ) {
//                                                         	que.add( (row-1)*1000+col); }
//                                              	if(  col  > 1 && Data[row][col]  == Data[row][col-1] ) {
//                                                         	que.add( (row)*1000+col-1); }
//                                              	if(  row < N  && Data[row][col]  == Data[row+1][col] ) {
//                                                         	que.add( (row+1)*1000+col); }
//                                              	if(  col < M && Data[row][col]  == Data[row][col+1] ) {
//                                                         	que.add( (row)*1000+col+1); }
//                                   	}
//                         	}
//              	}
//              	
//
//              	Graph = new ArrayList [group+1];
//              	for (int i = 1; i <   Graph.length ; i++) 
//                         	Graph[i] = new ArrayList();
//
//              	for (int i = 1; i <=  N; i++) {
//                         	for (int j = 1; j <=  M; j++) {                                   	
//                                   	if( j < M  && Group[i][j]  !=  Group[i][j+1] ) Graph[ Group[i][j] ].add(  Group[i][j+1] );
//                                   	if( j > 1   && Group[i][j]  !=  Group[i][j-1]  ) Graph[ Group[i][j] ].add(  Group[i][j-1] );
//                                   	if( i < N   && Group[i][j]  !=  Group[i+1][j] ) Graph[ Group[i][j] ].add(  Group[i+1][j]  );
//                                   	if( i > 1   && Group[i][j]  !=  Group[i-1][j]  ) Graph[ Group[i][j] ].add(  Group[i-1][j]  );
//                         	}
//              	}
//              	// 이제 최단경로를 찾아보자.
//              	answer = Integer.MAX_VALUE;
//              	for (int i = 1; i <=  group; i++) {
//                         	int pathCnt = 0 ;
//                         	int path[]   = new int[group + 1];  // 경로수 저장하자.
//                         	Arrays.fill( path, -1);                         	
//                         	que = new LinkedList<Integer>(); 
//                         	que.add ( i );
//                         	path[ i ] = 0; // 자기자신은 거리가 0;                         	
//                         	while ( !que.isEmpty() ) {
//                                   	int q = que.poll();                                      	
//                                   	for( int j=0; j < Graph[q].size() ; j++ ) {
//                                              	int v = (Integer)Graph[q].get(j);                                	       	
//                                              	if(   path[ v ] != -1 ) continue;                                            	
//                                              	que.add( v );
//                                              	path[ v ]  = path[ q ] + 1;                                                 	
//                                              	pathCnt = Math.min( pathCnt, path[ v ]);
//                                   	}
//                         	}
//                         	 for( int k=1;k <= group; k++ ) if( k != i ) pathCnt  = Math.max( pathCnt, path[ k ]);        	 
//                         	 answer = Math.min( answer, pathCnt );
//              	}
//              	
//              	System.out.printf("%d\n", answer ) ;
//    	}
//}
//
//
//
//
//
//
////가장 많은 수
//public class sourceDay3_4 {
//    	static int  N ;
//    	static int Data[] ; 
//    	static int Cnt[] ; 
//    	static int MaxCnt;
//    	static int Number;
//    	
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//              	N = Integer.parseInt(br.readLine()); 
//              	Data = new int[N];
//              	Cnt = new int[N];
//              	 
//              	for (int i = 0; i < N; i++) {
//                         	Data[i] =Integer.parseInt( br.readLine() ); 
//              	}
//              	
//    	   Arrays.sort(  Data );
//    	  
//    	   Cnt[0] = 1;
//    	   MaxCnt = 1;
//    	   Number = Data[0] ;
//    	   for (int i = 1; i < N; i++) {
//              	   if( Data[i] == Data[i-1]) Cnt[i] = Cnt[i-1] + 1;
//              	   else Cnt[i] = 1;
//              	  
//              	   if( Cnt[i] > MaxCnt ) {
//                         	   Number = Data[i] ;
//                         	   MaxCnt = Cnt[i] ;
//              	   }
//    	   }
//    	  
//              	System.out.printf("%d\n", Number ) ;
//    	}
//}
//
//
//
//
//
//
//
//
//
//
////지은이가 지은 집
//public class sourceDay3_5 {
//    	static long X;
//    	static int N;
//    	static long A[];
//
//    	static boolean find = false;
//    	public static void main(String[] args) throws Exception {
//
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//              	X = Integer.parseInt(br.readLine());
//              	X = X * 10000000;
//
//              	N = Integer.parseInt(br.readLine());
//
//              	A = new long[N + 1];
//
//              	for (int i = 1; i <= N; i++) {
//                         	A[i] = Long.parseLong(br.readLine());
//              	}
//
//              	Arrays.sort(A, 1, N + 1);
//
//              	find = false;
//
//              	for (int l = 1, r = N; l < r ;) {
//     	
//                         	long sum = A[l] + A[r];
//                         	 
//                         	if (sum > X) r--;
//                         	if (sum < X) l++;
//
//                         	if (l >= r)      	break;
//                         	
//                         	if (sum == X) {
//                                   	find = true;
//                                   	System.out.printf("yes %d %d\n", A[l], A[r]);
//                                   	break;
//                         	}
//              	}
//
//              	if (!find)
//                         	System.out.printf("danger\n");
//
//    	}
//}
////Number of Inversion
//import javax.rmi.CORBA.Util;
//
//public class sourceDay3_6 {
//	static int N;
//	static int num[]; 
//	static long answer;
//	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );
//        	
//      	N = Integer.parseInt( br.readLine() );
//      	num = new int [N ];
//
//          StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//          for( int i = 0; i<  num.length ; i++ ) {
//              num[ i ] =  Integer.parseInt ( st.nextToken() );	
//      	}
//      	
//          //System.out.printf( "before =%s\n", Arrays.toString(num));
//          answer = mergeSort( num );
//          //System.out.printf( "after=%s\n", Arrays.toString(num));
//      	
//         System.out.printf( "%d\n", answer ); 
//	}
// 	
//	public static long mergeSort( int arr[] ) {
//   	if( arr == null || arr.length == 1 ) return 0 ;
//     	
//   	int center = arr.length / 2;
//   	int leftArr[]  = Arrays.copyOfRange( arr , 0, center ) ;
//   	int rightArr[] = Arrays.copyOfRange( arr , center ,   arr.length ) ;
//
//      	
//   	// 나누기
//   	long leftCnt = mergeSort( leftArr );
//   	long rightCnt = mergeSort( rightArr );
//     	
//   	long mCnt = merge( leftArr, rightArr,  arr );  
//       //System.out.printf( "leftArr=%s\n", Arrays.toString(leftArr));
//       //System.out.printf( "rightArr=%s\n", Arrays.toString(rightArr));
//       //System.out.printf( "merge=%s\n", Arrays.toString(arr));
//   	return leftCnt + rightCnt + mCnt;
//     	
//	}
//
// 	
//	public static long merge( int  leftArr[] , int rightArr[] , int arr[] ) {
//   	long mCnt = 0;
//   	int leftIdx = 0, rightIdx = 0;
//   	int type = 0;
//   	for( int i=0; i < arr.length; i++) {
//           if(  leftIdx >= leftArr.length ) {
//                if( rightIdx < rightArr.length )  {
//                     arr[i] = rightArr[ rightIdx ];
//                     rightIdx ++;
//                     type =1;
//                } else type = 2;
//       	} else 	if(  rightIdx >= rightArr.length ) {
//                if( leftIdx < leftArr.length ) {
//                  	arr[i] = leftArr[ leftIdx ];
//                  	leftIdx ++;
//                  	type = 3;
//                }  else type =3;
//	       } else  if(  leftArr[ leftIdx ] < rightArr[rightIdx] ) {
//                arr[i] = leftArr[ leftIdx ];
//                leftIdx ++;
//                type = 4;
//           }  else if(  leftArr[ leftIdx ] > rightArr[rightIdx] ) {
//     	       arr[i] = rightArr[ rightIdx ];
//                rightIdx ++;
//                mCnt = mCnt + leftArr.length - leftIdx   ;
//                type = 5;
//                //System.out.printf( "mCnt=%d , leftArr.length=%d, leftIdx=%d \n", mCnt,leftArr.length,leftIdx);
//       	} else  { // 같으면
//                arr[i] = leftArr[ leftIdx ];
//               // i++;
//               // arr[i] = rightArr[ rightIdx ];
//                leftIdx ++;  	
//               // rightIdx ++;
//                type = 6;
//       	}
//        	
//       	// System.out.printf( "type=%d:mCnt=%d,arr[%d]=%d , rightIdx=%d, leftIdx=%d \n"
//             //           	,type, mCnt, i, arr[i],rightIdx,leftIdx);
//   	}
//    	
//   	return mCnt;
//	}
//}
//
//
//
//
////동맹의 동맹은 동맹
//public class sourceDay4_1 {
//	static int N;
//	static int par[];
//	static int Q;
//	static int t, a , b; 
//	static long answer;
//	static StringTokenizer st;
//	
//	static int find(int  n) {
//	  	if(  par[n] == n ) return n;	 	
//	  	return par[n] = find( par[n]  );
//	}	
//	static void union(int  a, int b) {
//	  	int p = find(a);
//	  	int q = find(b);	     	
//	  	if( p == q ) return;		
//	  	par[q] = p;
//	}
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );
//          BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out ) );
//        	
//      	N = Integer.parseInt( br.readLine() );
//      	par = new int [N + 1];
//      	
//          for( int i = 1; i<=  N  ; i++ ) par[i] = i;         	
//      	Q = Integer.parseInt( br.readLine() ); 	
//          for( int i = 0; i<  Q  ; i++ ) {
//     	   	 st = new StringTokenizer( br.readLine() ) ;
//     	   	 
//     	   	 t =  Integer.parseInt ( st.nextToken() );  
//     	   	 a =  Integer.parseInt ( st.nextToken() );	
//     	   	 b=  Integer.parseInt ( st.nextToken() );  
//            	 
//     	   	 if(  t == 0 ) {
//     	             	 union( a, b);
//     	   	 } else {
//     	             	 if( find(a) == find(b) ) bw.write("1");
//     	             	 else bw.write("0");
//     	             	 bw.write("\n");
//     	   	 }
//          }           	
//          bw.flush();
//	}    	
//
//}
////탑
//
//public class sourceDay4_2 {
//	static int N;
//	static int H;
//	
//	static StringTokenizer st;
//	static Stack<ReadData> stack;
//	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//          BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out ) );
//      	
//      	N = Integer.parseInt( br.readLine() );  
//
//      	st = new StringTokenizer( br.readLine() ) ;
//          stack = new Stack();
//      	
//          for( int i = 1; i<=  N  ; i++ ) { 
//     	   	 H  =  Integer.parseInt ( st.nextToken() );
//     	   	    
//     	   	 while( !stack.isEmpty() &&  stack.peek().h < H ) {
//     	             	// System.out.printf("????==>%d,%d\n" , stack.peek().index, stack.peek().h );
//     	             	 stack.pop();
//     	   	 }
//     	   	 
//     	   	 String peekData = stack.isEmpty()?"0": stack.peek().index + "" ;
//     	   	 
//     	   	 if(  i > 1 ) bw.write(" ");
//     	   	 bw.write( peekData  ); 
//     	   	  
//     	   	 stack.add( new ReadData( i, H ) ) ;
//     	   	 
//          } 
//          
//          bw.flush();          	
//	}
//	
//	static class ReadData {
//	  	   int index , h;
//	  	   
//	  	   ReadData ( int index, int h) {
//	            	   this.index = index;
//	            	   this.h = h;
//	  	   }
//	}
//}
////Sliding Windows
//public class sourceDay4_3 {
//	static int N;
//	static int K;
//	static int Data[];
//	
//	static StringTokenizer st;
//	static Deque<ReadData> max;
//	static Deque<ReadData> min;
//	static long minValue;
//	static long maxValue;
//	static long sum;   	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//          BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out ) );           	
//      	st = new StringTokenizer( br.readLine() ) ;
//      	N = Integer.parseInt( st.nextToken() ); 
//      	K = Integer.parseInt( st.nextToken() ); 
//          Data = new int[N+1];          	
//      	st = new StringTokenizer( br.readLine() ) ;
//      	max = new LinkedList<ReadData>();
//      	min = new LinkedList<ReadData>();
//      	sum = 0;          	
//      	int i = 1;
//          for( ; i<=  K  ; i++ ) { 
//              Data[i]  =  Integer.parseInt ( st.nextToken() );                	
//              addMax( i, Data[i] );
//              addMin( i, Data[i] );
//              sum = sum + Data[i];
//          }	
//          minValue  = min.peekFirst().data;
//          maxValue	= max.peekFirst().data;               	
//          String printData = minValue + " " +  maxValue + " " + sum + "\n" ;
//          bw.write( printData  );            	
//     	for( ; i<=  N  ; i++ ) { 
//         	 Data[i]  =  Integer.parseInt ( st.nextToken() ); 
//              addMax( i, Data[i] );
//              addMin( i, Data[i] );                    
//              sum = sum + Data[i] - Data[i-K] ;
//              minValue  = min.peekFirst().data;
//              maxValue	= max.peekFirst().data;                   	
//              printData = minValue + " " +  maxValue + " " + sum + "\n" ;               	
//              bw.write( printData  ); 
//          }            	
//      	bw.flush();           	
//	}
//	
//	public static void addMax( int index, int data) {
//    	
//  	// 작은 애들은 뒤에서 뺀다.        	
//
//  	while( !max.isEmpty() &&  max.peekLast().data < data ) {
//         max.removeLast();
//	    }     
//   	
//      max.addLast ( new ReadData( index, data ));
//   	
//  	// index가 index - K 보다 앞에서 뺀다.
//
//  	while( !max.isEmpty() &&  max.peekFirst().index <= index - K ) {
//         max.removeFirst();
//  	}  	
//
//	}
//
//	public static void addMin( int index, int data) {
//  	// 작은 애들은 뒤에서 뺀다.
//  	while( !min.isEmpty() &&  min.peekLast().data > data ) {
//         min.removeLast();
//  	}  	
//   	
//     min.addLast( new ReadData( index, data ));
//   	
//  	// index가 index - K 보다 앞에서 뺀다.
//  	while( !min.isEmpty() &&  min.peekFirst().index <= index - K ) {
//         min.removeFirst();
//  	}    	
//	}
//	
//	static class ReadData {
//    	int index , data;
//     	
//        ReadData ( int index, int data) {
//            this.index = index;
//            this.data = data;
//    	}
//	}
//}
//
//
//
//
//
//
////구간 합
//public class sourceDay4_5 {
//    	static int N;
//    	static int newN;
//    	static int Level;
//    	static long Num[];
//    	static int Q;
//    	static int q, x, y;
//    	static StringTokenizer st;
//
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//              	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//              	N = Integer.parseInt(br.readLine());
//              	Level = 1;
//              	
//              	for( newN = 2 ; newN < N ;  newN = newN  <<1 ) {
//                         	Level ++;
//              	}
//              	
//              	Num = new long[newN * 2];
//
//              	// 초기값 셋팅하기
//              	for (int i = 0; i < N; i++) {
//                         	Num[ newN + i] =(  i + 1 );
//              	}
//              	 
//              	// Sum 을 구해볼까...
//              	for( int i = newN  - 1 ; i >= 1; i--) {
//                         	Num[i] = Num[i+i] +  Num[i + i + 1];
//              	}
//                         	
//              	//for (int i = 1; i < Num.length ; i++) {
//              	//     	System.out.printf("%d ", Num[i]);
//              	//}
//              	//System.out.printf("\n");
//
//              	Q = Integer.parseInt(br.readLine());
//              	
//              	for (int i = 0; i < Q; i++) {
//                         	st = new StringTokenizer(br.readLine());
//
//                         	q = Integer.parseInt(st.nextToken());
//                         	x = Integer.parseInt(st.nextToken());
//                         	y = Integer.parseInt(st.nextToken());
//
//    	                 	if (q == 0) {
//                                   	chage(x, y);
//                         	} else {
//                                   	long sum = sum(x, y);
//                                   	bw.write( sum + "\n");
//                         	}
//              	}
//              	bw.flush();
//    	}
//
//    	public static void chage(int a, int b) {
//              	int x= a + newN - 1;
//              	
//              	Num[ x ] = b;
//    	 
//              	for( int n = x/2 ; n >= 1; n = n /2 ) {
//    	                 	Num[ n ] = Num[ n + n ] + Num[ n + n + 1 ] ;
//              	}
//              	
//    	}
//
//    	public static long sum(int a, int b) {
//              	long sumValue = 0;
//              	 
//              	int x= a + newN - 1;
//              	int y = b + newN - 1;
//              	for(  ; x <= y ;  ) {
//                         	//System.out.printf("before x=%d, y=%d, sum=%d \n"
//                         	//     	       	                 	, x,y,sumValue );
//                         	 
//                         	if( x % 2 == 1)  {
//                                   	sumValue += Num[ x++ ];
//                         	}
//                         	if( y % 2 == 0) {
//                                   	sumValue += Num[ y--  ];
//                         	}
//                         	
//                         	x = x / 2;
//                         	y = y / 2;
//                         	
//                         	//System.out.printf("after x=%d, y=%d, sum=%d \n"
// 	    //        , x,y,sumValue );                         	
//              	}
//
//              	return sumValue;
//    	}
//}
////중앙값
//public class sourceDay4_4 {
//	static int N;
//	static int Data; 
//	
//	static StringTokenizer st;
//	static PriorityQueue<Integer> left;
//	static PriorityQueue<Integer> right;
//	static int middel; 
//	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//          BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out ) );
//       	
//      	N = Integer.parseInt( br.readLine() ); 	
//          left = new PriorityQueue( Collections.reverseOrder());
//          right = new PriorityQueue();
//       	
//          middel =  Integer.parseInt( br.readLine() );	// 초기값
//   	         String printData = middel  + "\n" ;
//   	         bw.write( printData  ); 
//   	       
//          for( int i = 2; i<= N  ; i++ ) { 
//     	   	 Data   =  Integer.parseInt( br.readLine() );  
//     	   	 
//     	   	 if(  middel <= Data   ) {
//     	             	 right.add( Data );
//     	   	 }
//     	   	 if(  middel > Data   ) {
//     	             	 left.add( Data );
//     	   	 }             	 
//     	   	 if( right.size() >= left.size() + 1 ) {
//     	             	  left.add( middel );
//     	             	  middel = right.poll();
//     	   	 } else if( right.size() + 1 < left.size()   ) {
//     	             	 right.add( middel );
//    	               	      middel = left.poll();        	               	 
//     	   	 }
//     	   	 if ( i % 2 == 1) {
//     	   	        printData = middel  + "\n" ;
//     	   	        bw.write( printData  ); 
//     	   	 }
//      	} 	
//          bw.flush();
//	} 
//}
//
////히스토그램에서 가장 큰 직사각형
//public class sourceDay4_6 {
//	static int N;
//	static int data[];
//	static int left[];
//	static int right[];
//	static long answer;
//	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );         	    
//          for( ;  ;  ) {
//              StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//              
//              N = Integer.parseInt ( st.nextToken() );              	
//              if( N == 0) break;                
//              data = new int[N+1];                
//              for( int i=1; i<=N; i++) {
//                 data[i] = Integer.parseInt ( st.nextToken() );
//              }              	
//              Stack<Integer>  stack = new  Stack ();
//              left = new int[N+1];
//              right = new int[N+1];                
//              for( int i=1; i<=N ; i++ ) {
//         	   while( !stack.isEmpty() && data[ stack.peek() ] >= data[i]) stack.pop();
//         	   
//         	   left[i] = stack.isEmpty() ? 1 : stack.peek() + 1;
//         	   
//         	   stack.add( i ) ;
//              }
//              stack = new  Stack ();
//              for( int i=N; i>=1 ; i-- ) {
//         	   while( !stack.isEmpty() && data[ stack.peek() ] >= data[i]) stack.pop();
//         	   
//         	   right[i] = stack.isEmpty() ? N : stack.peek() - 1;
//         	   
//         	   stack.add( i ) ;
//              }             	
//              answer = 0;
//         	
//              for( int i=1; i<=N ; i++ ) {
//         	   answer = Math.max( answer , (long)data[i] * (  right[i] - left[i] + 1 )) ;
//              }
//              System.out.printf( "%d\n", answer );
//          }  
//	} 
//
//}
////구간의 대표값
//public class sourceDay4_7  {
//	static int N, newN, Level;
//	static long Num[],  Max[] , Min[]; 
//	static int Q, q, x, y;
//	static StringTokenizer st;
//	public static void main(String[] args) throws Exception {
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
// 	N = Integer.parseInt(br.readLine());         
// 	for( newN = 2 ; newN < N ;  newN = newN  <<1 ) {   }        
// 	Num = new long[newN * 2];
// 	Max = new long[newN * 2];
// 	Min = new long[newN * 2];
//  	
// 	// 초기값 셋팅하기
// 	st = new StringTokenizer(br.readLine());
// 	for (int i = 0; i < N; i++) {
//     	Num[ newN + i] = Max[ newN + i] =
//                 Min[ newN + i] = Integer.parseInt(st.nextToken());
// 	}      	
// 	// Sum / min /max 을 구해볼까...
// 	for( int i = newN  - 1 ; i >= 1; i--) {
//         Num[i] = Num[i+i] +  Num[i + i + 1];         	
//         Min[i] = Math.min( Min[i+i]  , Min[i+i+1]);
//         Max[i] = Math.max( Max[i+i]  , Max[i+i+1]);
// 	}
//
// 	Q = Integer.parseInt(br.readLine());
//  	
// 	for (int i = 0; i < Q; i++) {
//     	st = new StringTokenizer(br.readLine());
//
//     	x = Integer.parseInt(st.nextToken());
//     	y = Integer.parseInt(st.nextToken());
//
//     	long minV = minFind(x, y);
//     	long maxV = maxFind(x, y);
//     	long sum = sumCalc(x, y);
//         bw.write( minV + " ");
//         bw.write( maxV + " ");
//         bw.write( sum + "\n");          	
// 	}
//
//     bw.flush();
//	}
//
//	public static long sumCalc(int a, int b) {
// 	long sumValue = 0;       	
// 	int x= a + newN - 1;
// 	int y = b + newN - 1;
//     for(  ; x <= y ;  ) {
//     	if( x % 2 == 1)  { sumValue += Num[ x++ ]; }
//     	if( y % 2 == 0)  { sumValue += Num[ y--  ]; }          	
//     	x = x / 2;
//     	y = y / 2; 
// 	}
// 	return sumValue;
//	} 	
//	public static long minFind(int a, int b) {
// 	long minValue = Integer.MAX_VALUE;       	
// 	int x= a + newN - 1;
// 	int y = b + newN - 1;
//     for(  ; x <= y ;  ) {
//     	if( x % 2 == 1) { minValue = Math.min( minValue,  Min[ x++ ] ) ; }
//     	if( y % 2 == 0) { minValue = Math.min( minValue,  Min[ y--  ]   }          	
//         x = x / 2;
//     	y = y / 2;
// 	}
// 	return minValue;
//	}  	
//	public static long maxFind(int a, int b) {
// 	long maxValue = 0;     	
// 	int x = a + newN - 1;
// 	int y = b + newN - 1;     	
// 	for( ; x <= y ; ) {         	
//     	if( x % 2 == 1 ) maxValue = Math.max( maxValue, Max[x++]);
//     	if( y % 2 == 0 ) maxValue = Math.max( maxValue, Max[y--]);
//      	
//     	x = x / 2;
//     	y = y / 2;
// 	}
//     return maxValue;
//	}
//}
//
//
//
//
//
//
//
//
//
////그래프 순회
//public class sourceDay5_1 {
//	static int V;
//	static int E;
//	static int S;
//	static ArrayList<Integer> node[];
//	static int visit[];
//	static StringBuffer sb;
//	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//      	
//          StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//    	  V= Integer.parseInt ( st.nextToken() );
//      	E= Integer.parseInt ( st.nextToken() );
//      	S= Integer.parseInt ( st.nextToken() );
//      	
//          node = new ArrayList [ V + 1 ];
//          for( int i=1; i<=V; i++) node[ i ] = new ArrayList();
//      	
//          for( int i=1; i<=E; i++) {
//     	   	 st = new StringTokenizer( br.readLine() ) ;         	   	 
//     	   	 int stV = Integer.parseInt ( st.nextToken() );
//     	   	 int endV = Integer.parseInt ( st.nextToken() );        	    
//     	   	 node[ stV ].add( endV );
//     	   	 node[ endV ].add( stV );
//      	} 	          	
//          for( int i=1; i<=V; i++) Collections.sort( node[i]);
//      	
//      	sb = new StringBuffer();
//      	visit = new int[ V + 1 ];
//          dfs( S ); 
//          System.out.printf( "%s\n", sb.toString() ); 
//      	
//      	sb = new StringBuffer();
//          visit = new int[ V + 1 ];
//          bfs( ); 
//          System.out.printf( "%s\n", sb.toString() );           	
//	}  
//
//
//
//
//
//
//
//	public static void dfs(int find) {
//	  	  
//	  	  if( visit[  find  ]  == 1 ) return;
//	  	   
//   	     // 초기값
//   	     sb.append( (find == S ? "" : " " ) + find );
//   	     visit[ find ] = 1; 
//    		  	 
//    		for( int v : node[find] ) {
//    		  	dfs( v );
//    		} 	  
//	}
//	
//	public static void bfs( ) {
//	  	     int find = S;
//	  	     Queue<Integer> q = new LinkedList();
//	  	    
//	  	     // 초기값
//	  	     
//	  	     sb.append( find );
//	  	     visit[ find ] = 1;
//	  	     q.add( find ) ;
//	  	     
//	  	     while( !q.isEmpty() ) {
//	  	      	 find = q.poll();
//	  	      	 
//	  	      	 for( int v : node[find] ) {
//	  	                	 
//	  	                	 if( visit[ v ]  == 1 ) continue;
//	  	                	 
//	  	              sb.append( " " + v );
//	  	               	 visit[ v ] = 1;
//	  	               	 q.add( v ) ;
//	  	      	 }
//	  	     }
//	  	     
//	  	     
//	  	     
//	  	     
//	}
//	
//
//}
//
//
//
//
////위상 정렬
//public class sourceDay5_2 {
//	static int V;
//	static int E;
//	static ArrayList<Integer> node[];  	
//	static int in[];
//	static StringBuffer sb;	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//      	
//          StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//      	V= Integer.parseInt ( st.nextToken() );
//      	E= Integer.parseInt ( st.nextToken() );
//      	
//  	    node = new ArrayList [ V + 1 ];
//      	in = new int [ V + 1 ];
//          for( int i=1; i<=V; i++) node[ i ] = new ArrayList();
//      	
//          for( int i=1; i<=E; i++) {
//     	   	 st = new StringTokenizer( br.readLine() ) ;
//     	   	 
//     	   	 int stV = Integer.parseInt ( st.nextToken() );
//     	   	 int endV = Integer.parseInt ( st.nextToken() );        	    
//     	   	 node[ stV ].add( endV );
//     	   	 in[endV] ++;
//      	} 	           	
//      	sb = new StringBuffer();
//       	
//          Queue<Integer> que = new LinkedList<>();
//      	for (int i=1;i<=V;i++) if (in[i] == 0)  	que.add(i); 
//      	
//          boolean first = true;         	
//          while (!que.isEmpty()){
//      	  	int q = que.poll();
//      	  	
//      	  	sb.append( ( first ?"":" " ) + q );
//         	
//             first = false;
//      	  	for (int t: node[q]){
//      	            	if (--in[t] == 0) que.add(t);
//   	     	}
//      	}
//      	
//          System.out.printf( "%s\n", sb.toString() ); 	
//	}  
//
//}
////워프
//public class sourceDay5_3 {
//	static int N, M;
//	static ArrayList<Node> node[]; 
//	static long cost[];   	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );            	
//          StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//    	  N = Integer.parseInt ( st.nextToken() );
//      	M = Integer.parseInt ( st.nextToken() );           	
//          node = new ArrayList[ N + 1 ];
//          for( int i=1; i<=N; i++) node[ i ] = new ArrayList<Node>();          	
//     	 for( int i=1; i<=M; i++) {
//              st = new StringTokenizer( br.readLine() ) ;               	
//              int stV = Integer.parseInt ( st.nextToken() );
//              int endV = Integer.parseInt ( st.nextToken() );
//              int c = Integer.parseInt ( st.nextToken() );              	
//              node[ stV ].add( new Node( endV, c ) ); 
//      	}
//          cost = new long[ N + 1 ]; 
//          Arrays.fill( cost , -1);
//          cost[1] = 0;          	
//      	for( int i=1; i<N; i++) {
//              if ( cost[i] == -1 ) continue;
//              for( Node v : node[i] ) {
//                  if( cost[v.y] == -1 ) cost[v.y]  = cost[i] +  v.cost; 
//                  else cost[v.y]  = Math.min(  cost[v.y]  , cost[i] + v.cost  ) ;
//              }
//      	}
//          System.out.printf( "%d\n", cost[N] );
//	}
//	public static class Node implements  Comparable <Node> {
//       int  y, cost;
//    	
//   	Node (  int y, int cost ) {
//           this.y = y;
//           this.cost = cost;
//   	}
//    	
//       public  int compareTo( Node n) {
//           return  Integer.compare( this.y ,  n.y )  ;
//   	}
//	}
//}
//
//
//
////최단경로 , 다익스트라
//public class sourceDay5_4 {
//	static int N, M;
//	static ArrayList<Node> node[]; 
//	static PriorityQueue<Node> pq;
//	static long cost[]; 
//	static int visit[]; 	
//	public static void main( String[] args ) throws Exception{
//	        BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );            	
//          StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//      	N = Integer.parseInt ( st.nextToken() );
//      	M = Integer.parseInt ( st.nextToken() );           	
//          node = new ArrayList[ N + 1 ];
//          for( int i=1; i<=N; i++) node[ i ] = new ArrayList<Node>();          	
//          for( int i=1; i<=M; i++) {
//              st = new StringTokenizer( br.readLine() ) ;               	
//              int stV = Integer.parseInt ( st.nextToken() );
//              int endV = Integer.parseInt ( st.nextToken() );
//              int c = Integer.parseInt ( st.nextToken() );              	
//              node[ stV ].add( new Node( endV, c ) ); 
//              node[ endV ].add( new Node( stV, c ) ); 
//          }           	
//      	pq = new java.util.PriorityQueue<Node>( new Comparator<Node>(){
//                public int compare( Node a, Node b) {
//                  return Long.compare( a.cost, b.cost);
//                }
//              }
//          visit = new int[ N + 1 ];
//          cost = new long[ N + 1 ];                
//      	Arrays.fill( cost , -1);
//          cost[1] = 0;
//       	
//          pq.add( new Node( 1 , 0 ) );           	
//          while( !pq.isEmpty() )  {                    
//              Node nd = pq.poll();                 
//	           if( visit[ nd.y] == 1 ) continue;              	
//              visit[ nd.y] = 1;
//              for( Node v : node[ nd.y] ) { 
//                  if( visit[ v.y] == 1 ) continue;
//                  if( cost[v.y] == -1 ) cost[v.y]  = cost[nd.y] +  v.cost; 
//                  else cost[v.y]  = Math.min(  cost[v.y]  , cost[nd.y] + v.cost  ) ;
//                  
//                  pq.add( new Node( v.y , cost[v.y] ) );
//	            }
//      	} 
//          System.out.printf( "%d\n", cost[N] );   
//	}  
//	
//	public static class Node implements  Comparable <Node> {
//       int  y;
//   	long cost;
//    	
//   	Node (  int y, long cost ) {
//           this.y = y;
//           this.cost = cost;
//   	}
//    	
//       public  int compareTo( Node n) {
//           return  Integer.compare( this.y ,  n.y )  ;
//   	}
//    	
//   	public String toString() {
//    	   return "[node:"+y+"="+cost+"]";
//   	}
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////고속도로 건설  
//public class sourceDay6_1 {
//	static int N, M;
//	static ArrayList<Node> node;	
//	static int tree[];
//	
//	public static int find( int x) {
//	  	  if(  tree[x]  == x ) return x;
//	  	  
//	  	  return tree[x] = find( tree[x] );
//	} 
//	public static void union( int x, int y) {
//	  	  int xx = find(x);
//	  	  int yy = find(y);
//	  	  
//	  	  if( xx == yy ) return;
//	  	  
//	  	  tree[xx] = yy;
//	}  	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//       	
//      	N = Integer.parseInt ( br.readLine() );
//  	    tree = new int[ N + 1 ];
//      	
//          for( int i=1; i<=N; i++) {
//     	   	 tree[i] = i;
//      	}
//      	
//      	M = Integer.parseInt (br.readLine()  );
//       	
//          node = new ArrayList<Node>();
//       	
//          for( int i=1; i<=M; i++) {
//     	   	 StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
//              
//              int stV = Integer.parseInt ( st.nextToken() );
//              int endV = Integer.parseInt ( st.nextToken() );
//              int c = Integer.parseInt ( st.nextToken() );
//              
//              node.add( new Node( stV, endV, c ) );  
//          }	
//      	
//      	// Sorts 합니다.
//      	
//          Collections.sort( node );
//
//          long Answer = 0;
//          for( Node v : node ) {
//     	   	 if( find( v.x) == find( v.y )) continue; // 사이클이 생긴다.
//     	   	  
//     	   	 union( v.x , v.y);  
//     	   	 
//     	   	 Answer = Answer + v.cost;
//      	}
//       	
//          System.out.printf( "%d\n",Answer ); 
//               
//	}  
//	
//	public static class Node implements  Comparable <Node> {
//       int  x, y;
//   	long cost;
//    	
//   	Node ( int x,  int y, long cost ) {
// 	   this.x = x;
//           this.y = y;
//           this.cost = cost;
//   	}
//    	
//       public  int compareTo( Node n) {
//           return  Long.compare( this.cost ,  n.cost )  ;
//   	}
//    	
//   	public String toString() {
//           return "[node:"+x+","+y+"="+cost+"]";
//   	}
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////유령선
//public class sourceDay6_2 {
//	static int W, H;
//	static char Board[][];
//	static int Move[][]; 
//	static int next[][] = { {-1,0}, {0,1}, {1,0}, {0,-1} } ;
//	static int startX = 0,startY = 0;
//	static int endX = 0,endY = 0;
//	
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//       	
//          StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
//      	W = Integer.parseInt ( st.nextToken()  );
//      	H = Integer.parseInt ( st.nextToken()  );
//      	
//          Board = new char[ H  ][W ];
//          Move = new int[ H  ][W ]; 
//          for( int i =0; i< H; i++)
//             Arrays.fill( Move[i], -1);
//       	
//          for( int i=0; i< H; i++) {
//     	   	 String temp = br.readLine();
//     	   	 Board[i] = temp.toCharArray();
//     	   	 
//     	   	 for( int j=0; j< W ; j++) {
//     	             	 if( Board[i][j] == 'S' ) {
//     	                        	 startX = i;
//     	                        	 startY = j;
//    	              	 }
//     	             	 if( Board[i][j] == 'E' ) {
//     	                        	 endX = i;
//     	                        	 endY = j;
//     	             	 }  
//     	   	 }     	
//          } 
//     	
//          find();
//       	
//          System.out.printf( "%d\n",Move[endX][endY] ); 
//               
//	}  
// 	
//
//
//
//
//	public static void find () {
//	  	  
//   	     int find = 0;
//   	     Queue<Integer> q = new LinkedList();
//   	    
//   	     q.add( startX );
//   	     q.add( startY );
//   	     Move[startX][startY] = 0; 
//   	     while( !q.isEmpty() ) {
//   	        int x = q.poll();
//   	      	int y = q.poll();
//   	      	 
//	  	      for( int i=0;i < 4; i++) {
//	  	      	 int nextX = x + next[i][0];
//	  	      	 int nextY = y + next[i][1];
//	  	        
//	  	      	 //System.out.printf( "Move[ %d][ %d] \n",nextX,  nextY); 
//	  	      	if(  nextX < 0 || nextX >= H ) continue; 	
//	  	        if(  nextY < 0 || nextY >= W ) continue;  	
//	  	        if( Board[nextX][nextY] == 'X' ) continue; 
//	  	        if( Move[nextX][nextY] != -1  ) continue;
//	  	        
//	  	       // System.out.printf( "==>Move[ %d][ %d] = %d \n",nextX,  nextY, Move[nextX][nextY]); 
//	  	        
//	  	        Move[nextX][nextY] = Move[x][y] + 1;
//	  	       // Move[nextX][nextY] = Math.min( Move[x][y] + 1 , Move[nextX][nextY] ) ;
//	  	        
//	  	        //System.out.printf( "==>Move[ %d][ %d] = %d \n",nextX,  nextY, Move[nextX][nextY]); 
//	  	        q.add( nextX );
//	  	        q.add( nextY ); 
//   	     }
//   	     }
//   	      
//	}
//	
//	
//}
//
//
//
//
//
//
//
//
//
//
////피크닉 
//public class sourceDay6_3 {
//	static int K, N , M;
//	static int Cow[]; 
//	static ArrayList<Integer> Path[];  
//	static int Meet[][];
//	static  int Answer = 0;
//	public static void main( String[] args ) throws Exception{
//          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//       	
//          StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
//  	    K = Integer.parseInt ( st.nextToken()  );
//      	N = Integer.parseInt ( st.nextToken()  );
//      	M = Integer.parseInt ( st.nextToken()  );
//      	
//          Meet = new int[N+1][K+1];
//      	Cow = new int[K+1];
//      	Path = new ArrayList[N+1];
//      	
//          for( int i = 1; i <= N ; i++ ) Path[i] = new ArrayList();
//          for( int i = 1; i <= K ; i++ ) Cow[i] = Integer.parseInt ( br.readLine() );
//       	
//          for( int i=0; i< M; i++) {
//     	   	 st = new StringTokenizer( br.readLine() ) ; 
//              int a = Integer.parseInt ( st.nextToken()  );
//              int b = Integer.parseInt ( st.nextToken()  );                 	 
//     	   	 
//              Path[a].add( b );
//	       } 
//      	
//      	
//          for( int i = 1; i <= K ; i++ ) {
//              find( i  );
//       	}
//      	
//          for( int i = 1; i <= N ; i++ ) {
//     	   	 if(  Meet[i][ 0] == K ) Answer ++;
//      	}
//       	
//          System.out.printf( "%d\n",Answer); 
//               
//	}  
// 	
//
//
//
//
//	public static void find ( int cow) {
//	  	   
//   	     Queue<Integer> q = new LinkedList();
//   	    
//   	     q.add( Cow[cow]  );  
//   	    
//   	 	while( !q.isEmpty() ) {
//   	        int x = q.poll();
//
//   	        if( Meet[x][ cow ] == 0  ) {
//   	      	        Meet[x][ cow ] = 1;
//   	      	        Meet[x][ 0] ++;
//   	        }
//   	       
//	  	     for( int i  : Path[x]) {
//	  	      	    
//	  	        if( Meet[i][cow] == 1  ) continue;
//	  	        
//	  	        Meet[i][cow]  = 1;
//	  	        Meet[i][0] ++;
//	  	        
//	  	       // System.out.printf( "aa===[%d][%d], %d\n ", i, 0, Meet[i][0]);	
//	  	        
//	  	        
//	  	        q.add( i );
//	  	     }
//	  	      
//   	     }
//   	    
//   	   /*  System.out.printf( "Cow No =%d\n", cow ); 
//   	for( int i=1; i<= N; i++) { 
// 	  for( int j=0; j<= K ; j++) { 
// 	        	 System.out.printf( "%d , ", Meet[i][j]);   	
// 	  }
// 	  System.out.printf( "\n" ); 
//  	}
//  	*/
//   	    
//	}
//	
//	
//}
//
//
//
//
//
////가장 먼 두 도시
//public class sourceDay6_4 {
//    	static int N;
//    	static int w[][];
//
//    	static int Answer = 0;
//
//    	public static void main(String[] args) throws Exception {
//              	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//              	N = Integer.parseInt(br.readLine());
//
//              	w = new int[N + 1][N + 1];
//
//              	for (int i = 1; i <= N; i++) {
//                         	StringTokenizer st = new StringTokenizer(br.readLine());
//
//                         	for (int j = 1; j <= N; j++) {
//                                   	w[i][j] = Integer.parseInt(st.nextToken());
//                         	}
//              	}
//
//              	for (int k = 1; k <= N; k++)
//                         	for (int i = 1; i <= N; i++)
//                                   	for (int j = 1; j <= N; j++)
//                                              	w[i][j] = Math.min(w[i][j], w[i][k] + w[k][j]);
//              	
//              	int ans = 0;
//              	for (int i = 1; i <= N; i++)
//                         	for (int j = 1; j <= N; j++)
//                                   	ans = Math.max(ans, w[i][j]);
//
//              	System.out.printf("%d\n", ans);
//
//    	}
//
//}
//
//
//
//
//
//
//
//
//
//
////풍선  
//
//public class sourceDay7_1 {
//static int N ;
//static int[] borrow;  
//
//static  int Answer = 0;
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//     	
//        	
//        	N = Integer.parseInt (  br.readLine() );
//    	
//	        borrow = new int [1000001];  
//        	
//            StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
//            // 풍선
//            for( int i=0; i< N; i++) {
//     	   	  int baloon = Integer.parseInt ( st.nextToken()  ); 
//     	   	  
//     	   	  if ( borrow[ baloon ] != 0 ) borrow[ baloon ]--;
//     	   	  borrow[ baloon - 1 ] ++ ;
//            }	
//        	
//            //for( int i=0; i <N; i++)  System.out.printf( "%d, ",borrow[i] ); 	
//            //System.out.printf( "\n"); 	
//        	
//            int Answer = 0;
//        	
//            for( int i=0; i< borrow.length; i++) Answer = Answer +  borrow[i] ;
//         	
//            System.out.printf( "%d\n",Answer);	
//} 
//} 
//
//
//
//
//
//
//
//
//
//
//
//
//
////cow party
//public class sourceDay6_5 {
//static int N, M , X;
//static ArrayList<Node> wayOut[];
//static ArrayList<Node> wayIn[];
//static long go[] ;
//static long back[] ;
//static  int Answer = 0;
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//    	
//       StringTokenizer st = new StringTokenizer( br.readLine() ) ;  
//   	N = Integer.parseInt ( st.nextToken()  );
//   	M = Integer.parseInt ( st.nextToken()  );
//   	X = Integer.parseInt ( st.nextToken()  );
//   	
//   	wayOut = new ArrayList[N + 1];
//   	wayIn = new ArrayList[N + 1];
//	    go = new long[N + 1];
//   	back = new long[N + 1];
//   	
//   	for( int i=0; i< M; i++) {
//  	      	 st = new StringTokenizer( br.readLine() ) ; 
//           int a = Integer.parseInt ( st.nextToken()  );
//           int b = Integer.parseInt ( st.nextToken()  );
//           int c = Integer.parseInt ( st.nextToken()  );
//  	      	 
//           if( wayOut[a] == null) wayOut[a] = new ArrayList();
//           wayOut[a].add( new Node( b, c) );
//           if( wayIn[b] == null) wayIn[b] = new ArrayList();
//           wayIn[b].add( new Node( a, c) );
//   	} 
//   	
//       dijkstra(wayOut, X, back);
//       dijkstra(wayIn, X, go);
//   	
//   	long max = 0;
//   	long cur = 0;
//   	
//   	for( int i = 1; i <= N ; i++)
// 	   max = Math.max(  max, go[i] + back[i] );
//    	
//       System.out.printf( "%d\n",max); 
//         	
//}  
//
//	
//public static void dijkstra (ArrayList<Node> way[],  int s, long [] dist) {
//   	     for( int i=1; i<= N; i++) dist[i] = Long.MAX_VALUE;
//   	                	 
//   	    PriorityQueue<Node> pq = new java.util.PriorityQueue<Node>();
//   	   
//    	 	dist[s] = 0;
//    	 	pq.add( new Node ( s, 0 ));
//    	 	Node n = null;
//    	 	
//    	 	while( !pq.isEmpty() ) {
//    	    	n  = pq.poll();
//     	if( way[ n.y ] == null ) continue;
//   	        for( Node node  : way[n.y]) {
//   	        //System.out.printf( "%s, %s\n" , n.toString(), node.toString());
//   	       
//   	        if( dist[ node.y] < node.cost + dist[n.y]  ) continue;
//   	              	        
//   	        	dist[node.y] = node.cost + dist[n.y];
//   	        	pq.add( new Node( node.y, dist[node.y] )); 
//   	        }
//   	    }
//    	  	// for( int i=1; i<=N; i++)  System.out.printf( "%d ", dist[i] );
//    	   	//System.out.printf( "\n" );        	     
//}
//
//public static class Node implements  Comparable <Node> {
//	int  y;
//	long cost;
// 	
//	Node (  int y, long cost ) {
//        this.y = y;
//        this.cost = cost;
//	}
//	 
//    public  int compareTo( Node n) {
//        return  Long.compare(  this.cost  , n.cost )  ;
//	}
// 	
//	public String toString() {
//        return "[node:"+y+"="+cost+"]";
//	}
//}
//} 
//
//
//
//
////웜홀 
//public class sourceDay6_6 {
//static int T;
//static int N, M , W;
//
//static ArrayList<Node> node;
//static ArrayList<Node>[] path;
//static long dist[];
//static long visit[];
//
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//    	
//   	T = Integer.parseInt ( br.readLine() ) ;
//   	
//   	for( int t=0; t < T ; t++ ) {
//            StringTokenizer st = new StringTokenizer( br.readLine() ) ;  
//        	N = Integer.parseInt ( st.nextToken()  );
//        	M = Integer.parseInt ( st.nextToken()  );
//        	W = Integer.parseInt ( st.nextToken()  );
//   	
//            node = new ArrayList();
//            path  = new ArrayList [ N + 1] ;
//  	      
//            // 도로
//            for( int i=0; i< M; i++) {
//     	   	 st = new StringTokenizer( br.readLine() ) ; 
//               int a = Integer.parseInt ( st.nextToken()  );
//               int b = Integer.parseInt ( st.nextToken()  );
//               int c = Integer.parseInt ( st.nextToken()  );
//
//                node.add( new Node( a, b, c) );       
//                node.add( new Node( b, a, c) );
//                
//            } 
//            // 원홀
//            for( int i=0; i< W; i++) {
//     	   	  st = new StringTokenizer( br.readLine() ) ; 
//               int a = Integer.parseInt ( st.nextToken()  );
//               int b = Integer.parseInt ( st.nextToken()  );
//               int c = Integer.parseInt ( st.nextToken()  );
//
//                node.add( new Node( a, b, -c) );             	
//            } 
//        	
//            boolean change = true; 
//            dist = new long [N + 1];
//            visit = new long [N + 1];  
//     	   	    
//            for( int i = 1; i <= N && change == true ; i++ ) { 
//                     	change = false;         	
//                     	for( Node n  : node ) {
//                     	    
//	  	                  if( dist[ n.y] <=  n.cost + dist[n.x]  ) continue;
//	  	               	 
//	  	                  dist[n.y] =   n.cost + dist[n.x]   ;
//	  	                 
//	  	                     change = true;  
//                     	}
//	  	      }   
//        	
//            System.out.printf( "%s\n",change ? "YES":"NO"); 
//   	}
//	
//}
//
//public static class Node implements  Comparable <Node> {
//	int  x;
//	int  y;
//	long cost;
// 	
//	Node (  int x, int y, long cost ) {
//	      this.x = x;
//        this.y = y;
//        this.cost = cost;
//	}
// 	
//    public  int compareTo( Node n) {
//        return  Long.compare(  this.cost  , n.cost )  ;
//	}
// 	
//	public String toString() {
//        return "[node:"+y+"="+cost+"]";
//	}
//}
//} 
//
//
//
//
//
//
//
//
////상인   
//public class sourceDay6_7  {
//	static int N,  int[] dep;
//	static int[][] par;
//	static ArrayList<Integer>[] con; 	
//	static int lca(int a, int b) {
// 	if (dep[a] < dep[b]) return lca(b, a);
// 	for (int i=0;i<17;i++) if (((dep[b]-dep[a])&(1<<i)) > 0) a = par[a][i];
// 	if (a == b) return a;
// 	for (int i=17;i-->0;) if (par[a][i] != par[b][i]){
//     	a = par[a][i];
//     	b = par[b][i];
//	  }
// 	return par[a][0];
//	} 	
//	public static void main(String[] args) throws Exception {
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 	N = Integer.parseInt(br.readLine());
// 	con = new ArrayList[N+1];
// 	for (int i=1;i<=N;i++) con[i] = new ArrayList<>();
// 	for (int i=1;i<N;i++){
//         StringTokenizer st = new StringTokenizer(br.readLine());
//     	int a = Integer.parseInt(st.nextToken());
//  	   int b = Integer.parseInt(st.nextToken());
//         con[a].add(b);
//         con[b].add(a);
// 	}
// 	dep = new int[N+1]; par = new int[N+1][17];
// 	Queue <Integer> que = new LinkedList<>();
//     que.add(1);
// 	while (!que.isEmpty()){
//     	int q = que.poll();
//     	for (int t: con[q]) if (par[q][0] != t){
//             par[t][0] = q; dep[t] = dep[q]+1;
//             que.add(t);
//     	}
// 	}
// 	for (int i=1;i<17;i++) for (int j=1;j<=N;j++){
//	     par[j][i] = par[par[j][i-1]][i-1];
// 	}
// 	long ans = 0;
// 	for (int i=1;i<N;i++){
//     	int j = i+1, k = lca(i, j);
//     	ans += dep[i] + dep[j] - 2*dep[k];
// 	}
//     System.out.println(ans);
//	}
////군사도로망
//public class sourceDay6_8 {
//static int N, M , K;
//static ArrayList<Edge>  edges;
//static int par[]; 
//	static int find(int n) {
//              	if (par[n] == n) return n;
//              	return par[n] = find(par[n]);
//    	}
//    	static boolean union(int a, int b) {
//              	a = find(a); b = find(b);
//              	if (a == b) return false;
//              	par[b] = a;
//              	return true;
//    	}
//public static void main( String[] args ) throws Exception{
//            BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//     	
//        	StringTokenizer st = new StringTokenizer( br.readLine() ) ;  
//        	N = Integer.parseInt ( st.nextToken()  );
//        	M = Integer.parseInt ( st.nextToken()  );
//        	K = Integer.parseInt ( st.nextToken()  );
//
//            edges = new ArrayList<>();
//            long ans = 0;
//            for (int i=1;i<=M;i++){
//       		 st = new StringTokenizer( br.readLine() ) ; 
//              int a = Integer.parseInt ( st.nextToken()  );
//              int b = Integer.parseInt ( st.nextToken()  );
//              int c = Integer.parseInt ( st.nextToken()  );
//             
//        	   edges.add(new Edge(a, b, -c));
//        	   ans += c;
//        	}
//            for (int i=1;i<=K;i++){
//           	 st = new StringTokenizer( br.readLine() ) ; 
//              int a = Integer.parseInt ( st.nextToken()  );
//              int b = Integer.parseInt ( st.nextToken()  );
//              int c = Integer.parseInt ( st.nextToken()  );
//	          
//        	   edges.add(new Edge(a, b, c));
//        	}
//            Collections.sort(edges, new Comparator<Edge>() {
//     	             	public int compare(Edge a, Edge b) {
//     	                        	return a.c - b.c;
//     	             	}
//     	   	});
//        	
//     	   	par = new int[N+1];
//     	   	for (int i=1;i<=N;i++) par[i] = i;
//
//     	   	for (int i=0;i<edges.size();i++){
//     	             	Edge e = edges.get(i);
//     	             	if (i == 0 || edges.get(i-1).c != e.c){
//     	                        	for (int j=i;j<edges.size();j++){
//     	                                   	Edge t = edges.get(j);
//     	                                   	if (e.c != t.c) break;
//     	                                   	if (find(t.a) != find(t.b)) t.mark = true;
//     	                        	}
//     	             	}
//     	             	if (union(e.a, e.b)){
//     	                        	ans += e.c;
//     	                        	e.in_mst = true;
//     	             	}
//     	   	}
//     	   	
//     	   	boolean unique = true;
//     	   	for (Edge e: edges) if (e.mark && !e.in_mst) unique = false;
//     	
//     	   	System.out.println(ans + " " + (unique ? "unique" : "not unique"));
//
//	
//}
//
//static class Edge {
//              	public Edge(int a, int b, int c) {
//                         	this.a = a;
//                         	this.b = b;
//                         	this.c = c;
//                         	mark = in_mst = false;
//              	}
//              	int a, b, c;
//              	boolean mark, in_mst;
//    	}
//
//} 
//
//
//
//
//
//
//
//
////보석   
//public class sourceDay7_2  {
//static int N, K , int[] C , Answer = 0 ;
//static ArrayList <Data> data; 
//static PriorityQueue <Integer> pq;    
//public static void main( String[] args ) throws Exception{
//   	    BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );              
//           StringTokenizer st = new StringTokenizer( br.readLine() ) ;         	
//        	N = Integer.parseInt (   st.nextToken()  );
//        	K = Integer.parseInt (   st.nextToken()  );             	
//            data = new ArrayList();               
//            for( int i=1; i<= N; i++) {
//               st = new StringTokenizer( br.readLine() ) ; 
//               int m = Integer.parseInt ( st.nextToken()  ); 
//               int v = Integer.parseInt ( st.nextToken()  );                 	
//               data.add(  new Data( m, v) );
//            }            	
//        	C = new int [K+1];	
//            for( int i=1; i<= K; i++) {
//	            C[i] = Integer.parseInt (br.readLine() );
//            }            	
//            Collections.sort( data );
//            Arrays.sort( C, 1, K+1 ); 
//            pq = new PriorityQueue( Collections.reverseOrder() );            	
//            long Answer = 0; int j=0;
//            for( int i = 1; i <=K ; i++) {                	
//                for( ; j <N && data.get(j).m <= C[i]  ; j++) {
//                    pq.add( data.get(j).v );
//                }           	     
//                if( j > N ) break;
//                if( pq.isEmpty() ) continue;                	
//                Answer += pq.poll();
//            }                                  
//            System.out.printf( "%d\n",Answer);    
//}  	
//static class Data implements java.lang.Comparable< Data>{
//	int m ;
//	int v ;    	
//    public  Data ( int m, int v) {
//        this.m = m;
//        this.v = v;
//	}    	
//	public int compareTo( Data d) {
//        return Integer.compare(  this.m, d.m  ) ;
//	}
//}
//}
////술약속   
//public class sourceDay7_3 {
//static int N, K ;
//static ArrayList <Data> data;     
//static  int Answer = 0;
//public static void main( String[] args ) throws Exception{
//	    BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );             
//           StringTokenizer st = new StringTokenizer( br.readLine() ) ;        	
//        	N = Integer.parseInt (   st.nextToken()  );             	 
//            data = new ArrayList();              
//            for( int i=1; i<= N; i++) {
//     	   	  st = new StringTokenizer( br.readLine() ) ; 
//     	   	  int m = Integer.parseInt ( st.nextToken()  ); 
//     	   	  int v = Integer.parseInt ( st.nextToken()  );          	     
//     	   	  data.add(  new Data( m, v) );
//            }            	
//            Collections.sort( data );	
//             long Answer = 0;  
//             long amt = 0;
//         	
//             amt = data.get(0).m * 2;            	
//             for( int i=1; i< N; i++) { 
//         	  Answer = Answer + amt * data.get(i).v ;            	  	
//         	  amt = amt + data.get(i).m * 2;
//             }             	
//            System.out.printf( "%d\n",Answer);	
//} 
//
//static class Data implements java.lang.Comparable< Data>{
//    	   int m ; // 시간
//    	   int v ; // 병수
//    	  
//    	   public  Data ( int m, int v) {
//              	   this.m = m;
//              	   this.v = v;
//    	   }
//    	  
//    	   public int compareTo( Data d) {
//              	   return Long.compare( (long) this.m * d.v , (long) d.m * this.v  ) ;
//    	   }
//}
//} 
//
//
//
//
//
////출근
//public class sourceDay7_4 {
//static int H, W, N ;
//static int Road [][] ;  
//static int Visit [][] ;  
//static int Last [][] ; 
//static  int Answer = 0;
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );          	
//           StringTokenizer st = new StringTokenizer( br.readLine() ) ;        	
//        	H = Integer.parseInt (   st.nextToken()  ); 
//        	W = Integer.parseInt (   st.nextToken()  ); 
//        	N = Integer.parseInt (   st.nextToken()  );              	
//      	  Road = new int  [H+1][W+1];
//            Visit = new int [H+1][W+1];              
//            for( int i=1; i<= H; i++) {
//     	   	  st = new StringTokenizer( br.readLine() ) ;          		  
//     	   	  for( int j=1; j<= W; j++)  {
//	                     	  Road[i][j] = Integer.parseInt ( st.nextToken()  );  
//     	   	  }   	 
//            }            	
//            Visit[1][1] = N-1;
//            for( int i=1; i<= H; i++) {
//     	   	   for( int j=1; j<= W; j++)  {                     	   
// 	        	   if( i + 1 <= H ) Visit[i + 1][j]  += Visit[i][j] / 2;
// 	        	   if( j + 1 <= W ) Visit[i ][j + 1] += Visit[i][j] / 2;    	                      	   
//     	             	   if( Visit[i][j] % 2 == 1) {
//     	                        	   if(Road[i][j] == 0 &&  i + 1 <= H ) Visit[i + 1][j]++;
//     	                        	   else if( Road[i][j] == 1 && j + 1 <= W ) Visit[i ][j + 1]++;
//     	             	   }
//                }
//        	}
//            Last = new int [H+1][W+1];
//            for( int i=1; i<= H; i++) {
//     	   	   for( int j=1; j<= W; j++)  {
//     	             	   if( Visit[i][j] % 2 == 0 ) Last[i][j] = Road[i][j];
//     	             	   else Last[i][j] =  Road[i][j] == 0?1:0;
//     	   	   }
//        	}    
//        	int endI = 1 , endJ = 1;
//            while( endI <= H &&  endJ <= W  ) {
//     	   	   if( Last[endI][endJ] == 1 endJ ++;
//     	   	   else endI ++;
//            }           	
//            System.out.printf("%d %d\n",endI, endJ );  
//}
//}  
////최장 증가 부분 수열(LIS)
//public class sourceDay7_5 {
//static int N ;
//static int Data[] ;	
//static int Set[] ;	
//static  int Answer = 0;
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );           	
//        	N = Integer.parseInt (   br.readLine()  );              	
//            Data = new int  [N+1];
//            Set	= new int  [N+1];
//            StringTokenizer st = new StringTokenizer( br.readLine() ) ;             	
//            for( int i=1; i<= N; i++) {
//     	   	  Data[i]  = Integer.parseInt ( st.nextToken()  );            	    	  
//     	   	  int index = search( Data[i]   ) ;
//     	   	  Set[ index ] =  Data[i]  ;
//     	   	  Answer = Math.max( Answer , index );
//            } 
//            System.out.printf("%d\n",Answer );  
//}
//
//public static int search( int value  ) {
//	if( Answer == 0) return 1;
// 	
//	int low = 1;
//	int high = Answer;
//	while(  low < high  ) {
//	  	   int mid = (low + high)  / 2 + 1;     	 
//	  	   if( Set[ mid ]  == value   ) return mid ; 	  	   
//	  	   if( Set[ mid ]  > value   ) high = mid - 1;
//	  	   else low = mid + 1;	       	
//	}
//	if( low > high ) return low ;
//	
//	if( Set[ low ] >= value   ) return low ;
//    	   return  low + 1;
//}
//}  
//
//
//
//
//
//
//
//
//
////색칠공부
//
//public class sourceDay7_6 {
//	static long MOD = 1000000007;
//	static long[] dp;
//	
//	public static void main(String[] args)     throws IOException
//	{
//  	
//  	InputStreamReader fr = new InputStreamReader(System.in);
//  	BufferedReader br = new BufferedReader(fr);
//	  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//  	String s;
//  	StringTokenizer st;
//     	
//     	st = new StringTokenizer(br.readLine() );
//     	int N = Integer.parseInt(st.nextToken());
//     	int K = Integer.parseInt(st.nextToken());
//     	dp = new long[N+1];
//     	dp[0] = 1;
//     	for (int i = 1; i <= N; i++) {
//        	dp[i] = dp[i-1] * (K-1) % MOD;
//     	}
//     	
//     	st = new StringTokenizer(br.readLine());
//     	int[] arr = new int[N+1];
//     	for (int i = 1; i <= N; i++) {
//        	arr[i] = Integer.parseInt(st.nextToken());
//     	}
//     	
//     	//알고리즘 시작
//     	int[] visitCnt = new int[N+1];
//         int[] visitGrp = new int[N+1];
//     	int grp = 0;
//     	long answer = 1;
//     	
//     	for (int i = 1; i <= N; i++) {
//        	if(visitGrp[i] < 1){
//               grp++;
//               visitGrp[i] = grp;
// 	          visitCnt[i] = 1;
//              
//               int curVisitCnt = 1;
//               int nextNode = i;
//               while (true) {
//                  nextNode = arr[nextNode];
//                 
//                  if(visitGrp[nextNode] < 1){
//                     visitGrp[nextNode] = grp;
//                     visitCnt[nextNode] = ++curVisitCnt;                         
//                  }else{
//                     if(visitGrp[nextNode] == grp){
//   	                 //1. 같은 그룹일 경우
//                    	answer = (answer * K) %MOD;
//                    	answer = (answer * cycleAnswer(curVisitCnt-visitCnt[nextNode])) %MOD;
//                    	answer = (answer * dp[visitCnt[nextNode]-1]) %MOD;
//                     }else{
//                    	//2. 다른 그룹일 경우
//                    	answer = (answer * dp[curVisitCnt]) %MOD;
//                     }
//                     break;
//                  }
//               }
// 	       }
//     	}
//     	System.out.println(answer);
//  	
//  	bw.flush();
//  	bw.close();
//  	br.close();
//	}
//
//	private static long cycleAnswer(int cnt) {
//  	
//  	if(cnt < 1) return 1;
//  	long pm = 1;
//  	long result = 0;
//  	
//  	for (int i = cnt; i >0; i--) {
//     	result += pm*dp[i];
//     	if(result < 0) result += MOD;
//     	result = result % MOD;
//     	pm *= -1;
//  	}
//  	
//  	return result;
//	}
//}
//
//
//
//
//
//
////중앙 문자열
//public class sourceDay7_7 {
//    	private static char[][] str = new char[3][];
//    	private static int[] radius = new int[3];
//    	private static ArrayList<Integer> AB;
//    	private static ArrayList<Integer> AC;
//    	private static ArrayList<Integer> BC;
//    	private static ArrayList<Integer> ALL;
//    	private static char[] Answer;
//    	public static void main(String[] args) throws Exception {
//                         	BufferedReader in = new BufferedReader(new InputStreamReader(System.in)) ;                 	       	
//                         	for (int i = 0; i < 3; i++)
//    	                            	str[i] = in.readLine().toCharArray();
//                         	Answer = new char[str[0].length];
//                         	AB = new ArrayList<Integer>();
//                         	AC = new ArrayList<Integer>();
//                         	BC = new ArrayList<Integer>();
//                         	ALL = new ArrayList<Integer>();
//                         	setRadius();
//                         	setAllDiff();
//                         	PriorityQueue<Radius> q = getRadiusQueue();
//                         	int maxIndex = q.poll().index;
//                         	int midIndex = q.poll().index;
//                         	int diff = (radius[maxIndex] - radius[midIndex]) / 2;
//                         	radius[maxIndex] -= diff;
//                         	setAnswer(maxIndex, diff);
//                         	System.out.println(radius[maxIndex]);
//    	}      	
//    	private static PriorityQueue<Radius> getRadiusQueue() {
//              	PriorityQueue<Radius> q = new PriorityQueue<>();
//              	for (int i = 0; i < 3; i++)
//                         	q.add(new Radius(i, radius[i]));
//              	return q;
//    	}
//    	private static void setAnswer(int index, int diff) {
//              	ArrayList<Integer> selectIndex;           	
//              	if (index == 0) selectIndex = BC;
//              	else if (index == 1) selectIndex = AC;
//              	else selectIndex = AB;
//              	for (int i = 0; i < Math.min(diff, selectIndex.size()); i++) {
//                         	Answer[selectIndex.get(i)] = str[index][selectIndex.get(i)];
//              	}
//    	}
//
//
//
//
//   	private static void setAllDiff() {
//              	for (int i = 0; i < ALL.size(); i++) {
//                         	int maxIndex = getRadiusQueue().poll().index;
//                         	Answer[ALL.get(i)] = str[maxIndex][ALL.get(i)];
//                         	if (maxIndex != 0) radius[0]++;
//                         	if (maxIndex != 1) radius[1]++;
//                         	if (maxIndex != 2) radius[2]++;
//              	}
//    	}
//    	private static void setRadius() {
//              	for (int i = 0, size = str[0].length; i < size; i++) {
//                         	char a = str[0][i];
//                         	char b = str[1][i];
//                         	char c = str[2][i];
//                         	if (a == b && a == c) {Answer[i] = a; }
//                         	} else if (a == b) {
//                                   	Answer[i] = a;
//                                   	AB.add(i);
//                                   	radius[2]++;
//                         	} else if (a == c) {
//                                   	Answer[i] = a;
//                                   	AC.add(i);
//                                   	radius[1]++;
//                         	} else if (b == c) {
//                                   	Answer[i] = b;
//                                   	BC.add(i);
//                                   	radius[0]++;
//                         	} else {  ALL.add(i);   }
//              	}
//    	}
//    	static class Radius implements Comparable<Radius> {
//              	int index;
//              	int radius;
//
//              	public Radius(int index, int radius) {
//                         	this.index = index;
//                         	this.radius = radius;
//              	}
//
//              	@Override
//              	public int compareTo(Radius o) {
//                         	return o.radius - this.radius;
//              	}
//    	}
//}
//
//
////나누기
//public class sourceDay8_1 {
//static int N ;
//static int Data[][] ;
//static int Cnt[] ;
//static  int Answer = 0;
//static int[] divRow = { 1,1,2,2 };
//static int[] divCol   = { 1,2,1,2 };
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );           	
//   	N = Integer.parseInt (   br.readLine()  );              	
//  	Data = new int  [N+1][N+1];
//  	Cnt = new int[2];   	
//   	for( int i=1; i<= N; i++) {
//     	   	   StringTokenizer st = new StringTokenizer( br.readLine() ) ;          	 	   
//     	   	   for( int j=1; j <= N; j++) {
//     	   	         Data[i][j]  = Integer.parseInt ( st.nextToken()  );	
//     	   	   }
//  	}             	
//	 dfs ( 1, 1, N ) ;
//     System.out.printf("%d\n",Cnt[0] );
//     System.out.printf("%d\n",Cnt[1] );
//}  
//public static void dfs( int sy, int sx, int size  ) {
//	// 모두 같은지 센다
//    	   int cnt = 0;
//    	   for( int i=sy; i < sy+ size; i++ ) {
//              	   for( int j=sx; j < sx + size ; j++) {
//                         	   cnt = cnt + Data[i][j] ;
//              	   }
//    	   }
//    	   if( cnt == size * size ) {
//              	   Cnt[1]++;
//              	   return;
//    	   }
//    	   if( cnt == 0 ) {
//              	   Cnt[0]++;
//              	   return;                	  
//    	   }
//    	  
//	int       	half = size / 2;
//	dfs(sy, sx, half);
//   	  dfs(sy, sx + half, half);
//   	  dfs(sy + half, sx, half);
//   	  dfs(sy + half, sx + half, half); 
//}
//}  
////거듭제곱 구하기
//public class sourceDay8_2 {
//static long A, M ; 
//static long Data[]  ;  
//static long MOD = 1000000007;
//
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//      	
//       StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
//   	
//  	A = Long.parseLong( st.nextToken()  );
//  	M = Long.parseLong ( st.nextToken()  );
//  	
//  	A = A % MOD  ;
//   	
// 	// for( int i=1; i<= Index; i++)    System.out.printf("%d,\n",Data[i] );  
// 	long Answer = power( A, M );
//     System.out.printf("%d\n",Answer  );
//}
//
//public static long power(long a, long m) {
//    		if (m == 0) return 1;
//    		long v = power(a, m/2);
//    		
//    		if (m % 2 == 0)
//    	 	 	return  v*v%MOD   ;
//    		else
//              		return v*v%MOD*a%MOD;
//}
//}  
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////가장 가까운 두 점
//class Point {
//	public Point(int x, int y) {
// 	this.x = x;   this.y = y;
//	} 
//	int x, y;
//} 
//public class sourceDay8_3 {
//	static int N;
//	static Point[] A, merge_tmp; 
//	static int dist(Point a, Point b) {
// 	return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
//	} 
//	static int dfs(int s, int e) {
// 	if (e-s < 9){
//     	int ret = Integer.MAX_VALUE;
//     	for (int i=s;i<e;i++)  for (int j=i+1;j<=e;j++){
//             ret = Math.min(ret, dist(A[i], A[j]));
//     	}
//         Arrays.sort(A, s, e+1, new Comparator<Point>() {
//             public int compare(Point a, Point b) {
//                 return a.y - b.y;
//             }
//     	});
//         return ret;
// 	}
// 	int m = s+e >> 1, div_x = A[m].x;
// 	int d = Math.min(dfs(s, m), dfs(m+1, e));
// 	/* Merging starts */
// 	for (int l=s,r=m+1,i=s;i<=e;i++){
//     	if (r > e || l <= m && A[l].y < A[r].y) merge_tmp[i] = A[l++];
//     	else merge_tmp[i] = A[r++];
// 	}
// 	for (int i=s;i<=e;i++) A[i] = merge_tmp[i];
// 	/* Merging ended */
//	 ArrayList <Point> arr = new ArrayList<>();
// 	for (int i=s;i<=e;i++){
//     	if ((A[i].x-div_x)*(A[i].x-div_x) <= d) arr.add(A[i]);
// 	}
// 	int ret = d;
// 	for (int i=0;i<arr.size();i++){
//     	for (int j=i+1;j<arr.size()&&j-i<13;j++){
//             ret = Math.min(ret, dist(arr.get(i), arr.get(j)));
//     	}
// 	}
// 	return ret;
//	}
//	public static void main(String[] args) throws Exception {
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 	N = Integer.parseInt(br.readLine());
// 	A = new Point[N+1]; merge_tmp = new Point[N+1];
// 	for (int i=1;i<=N;i++){
//         StringTokenizer st = new StringTokenizer(br.readLine());
//     	int x = Integer.parseInt(st.nextToken());
//     	int y = Integer.parseInt(st.nextToken());
//     	A[i] = new Point(x, y);
// 	}
//     Arrays.sort(A, 1, N+1, new Comparator<Point>() {
//         public int compare(Point a, Point b) {
//             return a.x - b.x;
//     	}
// 	});
//     System.out.println(dfs(1, N));
//	}
//}   
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////계단 오르기  
//class Matrix {
//	static final int MOD = (int)1e9 + 7; 
//	public Matrix() {
//	}
//
//	public Matrix(int a, int b, int c, int d) {
// 	m[0][0] = a;
// 	m[0][1] = b;
// 	m[1][0] = c;
// 	m[1][1] = d;
//	}
//
//	int[][] m = new int[2][2];
//
//	public Matrix multiply(Matrix ot) {
// 	Matrix ret = new Matrix();
//	  for (int i=0;i<2;i++) for (int j=0;j<2;j++){
//     	for (int k=0;k<2;k++)
//             ret.m[i][j] = (ret.m[i][j]+(int)(((long)m[i][k]*ot.m[k][j])%MOD))%MOD;
// 	}
// 	return ret;
//	}
//}
//
//public class sourceDay8_4 {
//	static final int MOD = (int)1e9 + 7;
//	static int N;
//	static Matrix A, V;
//
//	public static void main(String[] args) throws Exception {
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 	N = Integer.parseInt(br.readLine());
// 	A = new Matrix(1, 1, 1, 0);
// 	V = new Matrix(1, 0, 0, 1);
// 	for (;N>0;N>>=1,A=A.multiply(A))
//     	if ((N&1) == 1)
//             V = V.multiply(A);
//     System.out.println((V.m[1][0]+V.m[1][1])%MOD);
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////집합 판별
//public class sourceDay8_5  {
//static int T, N ;
//static  char[] S;
//static  boolean[][][] check;
//static  boolean[][][] visit;
//
//static   boolean is_set( int s, int e ) { 
//	if( visit[0][s][e]  ) return check[0][s][e];
//	
//	boolean result = false;
//  visit[0][s][e]  = true;
//	
//	if( S[s] == '{' && S[e] == '}' && is_elementlist(s+1, e-1) ) result = true; 
//	
//	return check[0][s][e] = result;
//}
//static   boolean is_elementlist( int s, int e ) { 
//	if( visit[1][s][e]  ) return check[1][s][e];
//	
//	boolean result = false;
//  visit[1][s][e]  = true;
//	
//	if( s >  e || is_list( s, e) ) result = true; 
//	
//	return check[1][s][e] = result;
//}
//
//static   boolean is_list( int s, int e ) { 
//	if( visit[2][s][e]  ) return check[2][s][e];
//	
//	boolean result = false;
//  visit[2][s][e]  = true;
//	
//	if( is_element( s, e)) result = true;
//	else {
//   	for( int i=s+1; i<e; i++) {
//           if( S[i] != ',')  continue;
//           if( is_element( s, i-1)  && is_list(i+1, e )) {
//               result = true;
//               break;
//       	}
//   	}
//	}    	
//	return check[2][s][e] = result;  	
//}
//
//static   boolean is_element( int s, int e ) { 
//	if( visit[3][s][e]  ) return check[3][s][e];
//	
//	boolean result = false;
//  visit[3][s][e]  = true;
//	
//	if( is_atom(  s, e) || is_set(s, e )) result = true; 
//	
//	return check[3][s][e] = result;
//}
//
//static   boolean is_atom( int s, int e ) { 
//	if( visit[4][s][e]  ) return check[4][s][e];
//	
//	boolean result = false;
//  visit[4][s][e]  = true;
//	
//	if( s == e  ) result = true;  
//	
//	return check[4][s][e] = result;
//}
//
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//       	
//   	T = Integer.parseInt (   br.readLine()  ); 
//    	
//   	for( int t = 1 ; t <= T ; t++) {
//    	
//        	S = br.readLine().toCharArray() ;
//            check = new boolean[5][S.length][S.length];
//            visit   = new boolean[5][S.length][S.length];
//             
//    	
//            System.out.printf("Word #%d: %s\n",t, is_set(0, S.length-1)?"Set":"No Set" );
//   	}
//}
//	
//}
//
//
//
//
//
//
//
//
////최대공약수가 1이 되는 것
//public class sourceDay8_6 {
//static  final int MOD = (int)1e7 + 3 ;
//static  int N, M;
//static int []A;
//static int[][] D;
//
//static int gcd( int a, int b) {
//    	return b == 0 ? a : gcd( b, a%b ); 
//}
//
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//      	
//   	N = Integer.parseInt (   br.readLine()  ); 
//   	A = new int[N+1];
//    	
//   	for( int i = 1 ; i <= N ; i++) {
//        	A[i] = Integer.parseInt( br.readLine());
//        	
//        	M = Math.max( M,  A[i]);
//   	}
//     	
//   	D = new int [N+1][M+1];
//       D[0][0] = 1;
//   	
//   	for( int i=0; i<N;i++) {
// 	   for( int j=0; j<=M;j++) {
// 	        	   if( D[i][j] == 0) continue;
// 	        	   
// 	        	   int g= gcd(j,A[i+1]);
// 	        	   // i + 1번째 수를 선택하는 경우
// 	        	   D[i+1][g] = ( D[i+1][g] + D[i][j]) % MOD;
// 	        	  // i + 1번째 수를 선택하지 않는 경우
// 	        	   D[i+1][j]  = ( D[i+1][j] + D[i][j]) % MOD;
// 	   }
//  	}
//      System.out.printf("%d\n", D[N][1] );
//    	
//}
//
//}  
//
//
//
//
//
////내리막 길
//public class sourceDay8_7 { 
//static  int N, M;
//static int [][] A, D; 
//static int[] yy = {-1,0,1,0};
//static int[] xx = {0, 1, 0, -1};
//static boolean[][] cal;
//static final int MOD = 1234567;
//
//static int dy( int i, int j) {
//    	 if( cal[i][j])  return D[i][j];
//    	 cal[i][j] = true;
//    	 
//    	 if( i==1   && j== 1 ) return D[i][j] = 1;
//    	 int ret = 0;
//    	 
//    	 for( int dir=0; dir < 4; dir++ ) {
//              	 int y = i+yy[dir], x = j+xx[dir];
//              	 
//              	 if ( y < 1|| y > N || x < 1 || x > M || A[y][x] <= A[i][j] ) continue;                                   	
//              	 ret = ( ret + dy(y,x) ) % MOD;
//    	 }    	 
//    	 return D[i][j] = ret;    	
//}
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//      	
//       StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//   	
//   	N = Integer.parseInt (   st.nextToken() ); 
//   	M = Integer.parseInt (   st.nextToken() ); 
//   	
//   	A = new int[N+1][M+1];
//   	D = new int[N+1][M+1];
//   	cal = new boolean[N+1][M+1];
//   	
//   	for( int i = 1 ; i <= N ; i++) {
// 	   st = new StringTokenizer( br.readLine() ) ;               	  
// 	   for( int j = 1 ; j <= M ; j++) {
// 	        	  A[i][j] = Integer.parseInt (   st.nextToken() ); 
// 	   }
//   	}       	
//      System.out.printf("%d\n", dy( N, M) );       	
//}	
//}  
//
//
////단순 다각형의 넓이 
//
//package kkh;
//import java.io.*;
//import java.util.*;
//
//public class sourceDay9_1 {
//    	 static int N ;
//    	 static int [] X, Y ; 
//    	 
//    	 
//    	   public static void main( String[] args ) throws Exception{
//    	      	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//    	         	
//    	      	N = Integer.parseInt (   br.readLine()  ); 
//    	            	
//    	      	X = new int  [N+1];
//    	      	Y = new int  [N+1];
//    	      	for( int i=1; i<= N; i++) {
//    	        	   	   StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
//    	        	   	   
//    	        	   	    X[i] = Integer.parseInt ( st.nextToken()  );	
//    	        	   	    Y[i] = Integer.parseInt ( st.nextToken()  );	
//    	     	} 
//    	           	
//    	      	long ans = 0;
//    	      	for (int i=1;i<=N;i++){
//    	      	     	int j = i%N + 1;
//    	      	     	ans += (long)X[i]*Y[j] - (long)X[j]*Y[i];
//    	      	}
//    	      	ans = Math.abs(ans);
//    	          System.out.printf("%d.%d\n", ans/2, ans%2 == 0 ? 0 : 5 );
//
//
//    	   }
//    		
//    	 }  
//
//
//
//
//
//
//
//
//
//
////점의 위치
//public class sourceDay9_2 {
//static int N ;
//static int [] X, Y ; 
//static int x1,y1, x2, y2;
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//       StringTokenizer st  ;        	
//   	N = Integer.parseInt (   br.readLine()  );              	
//   	X = new int  [N+1];
//   	Y = new int  [N+1];
//   	for( int i=1; i<= N; i++) {
// 	     st = new StringTokenizer( br.readLine() ) ;          	     
//     	   	    X[i] = Integer.parseInt ( st.nextToken()  );	
//     	   	    Y[i] = Integer.parseInt ( st.nextToken()  );	
// 	   } 
//   	    st = new StringTokenizer( br.readLine() ) ;  
//    		x1 = Integer.parseInt ( st.nextToken()  );	
//    		y1= Integer.parseInt ( st.nextToken()  );	
//   	    st = new StringTokenizer( br.readLine() ) ;  
//    		x2 = Integer.parseInt ( st.nextToken()  );	
//    		y2= Integer.parseInt ( st.nextToken()  );      	     
//    		System.out.println((check(x1, y1) ? "in" : "out"));
//    		System.out.println((check(x2, y2) ? "in" : "out"));
//}  
//    	static boolean check(int x1, int y1) {
//              	int x2 = (int)1e9 + 1, y2 = y1 + 1;
//              	boolean ret = false;
//              	for (int i=1;i<=N;i++){
//                         	int j = i%N + 1;
//                         	if (is_cross(x1, y1, x2, y2, X[i], Y[i], X[j], Y[j]))
//                                   	ret ^= true;
//              	}
//              	return ret;
//    	}      	
//static int ccw(int ax, int ay, int bx, int by, int cx, int cy) {
//              	long k = (long)(bx-ax)*(cy-ay) - (long)(cx-ax)*(by-ay);
//              	if (k > 0) return 1;
//              	if (k < 0) return -1;
//              	return 0;
//    	}	
//    	static boolean is_cross(int ax, int ay, int bx, int by, int cx, int cy, int dx, int dy) {
//              	return ccw(ax, ay, bx, by, cx, cy) * ccw(ax, ay, bx, by, dx, dy) < 0 &&
//              	   	ccw(cx, cy, dx, dy, ax, ay) * ccw(cx, cy, dx, dy, bx, by) < 0;
//    	} 	
//}  
//
////단순 다각형의 넓이
//public class sourceDay9_3 {
//static int N ;
//static Point [] A ;  
//
//public static void main( String[] args ) throws Exception{
//       BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );           	
//   	N = Integer.parseInt (   br.readLine()  );              	
//	      A = new Point  [N];
//   	for( int i=0; i< N; i++) {
//     	   	   StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
//     	   	   int x = Integer.parseInt ( st.nextToken()  );
//                int y = Integer.parseInt ( st.nextToken()  );         
//     	   	    A[i] = new Point (x, y );
//  	}         	
//   	for( int i=1; i<N ; i++) {
// 	   if( A[0].y > A[i].y || A[0].y == A[i].y && A[0].x > A[i].x ) {
// 	        	  Point tmp = A[0];
// 	        	  A[0] = A[i];
// 	        	  A[i] = tmp;
// 	   }
//   	}      	
//   	for( int i=N-1 ; i >=0 ; i--) {
// 	   A[i].x -= A[0].x;
// 	   A[i].y -= A[0].y;
//   	}      	
//       Arrays.sort( A, 1, N, new Comparator<Point>() {
// 	   public int compare( Point a, Point b) {
// 	        	  int v= ccw( new Point(0,0), a, b);
// 	        	  if( v > 0) return -1;
// 	        	  if( v < 0) return 1;
// 	        	  return (Math.abs( a.x) + a.y) - (Math.abs(b.x)+b.y);
// 	   }
//       });            	  
//       ArrayList <Integer> stk = new ArrayList<>();
//       stk.add(0);      	
//   	for( int i=1; i<N;i++) {
// 	   while( stk.size() > 1 &&
// 	                  	  ccw(  A[stk.get( stk.size()-2)]
// 	                                       	  ,A[stk.get( stk.size()-1)]
//	                                        	  ,A[i]) <= 0 ) {
// 	        	  stk.remove( stk.size() - 1);
// 	   }
// 	   stk.add(i);
//   	}      	
//       System.out.printf("%d\n", stk.size() );
//}
//
//static int ccw(Point a , Point b, Point c) {
//              	long k = (long)(b.x-a.x)*(c.y-a.y) - (long)(c.x-a.x)*(b.y-a.y);
//              	if (k > 0) return 1;
//              	if (k < 0) return -1;
//              	return 0;
//    	}
//
//static class Point {
//    	   int x ;
//    	   int y;
//    	   Point( int x, int y) {
//              	   this.x = x;
//              	   this.y = y;
//    	   }
//}  
//}  
//
////가장 거리가 먼 두 점
//public class sourceDay9_4 {
//    	 static int N ;
//    	 static int [] X, Y ;      	 
//    	   public static void main( String[] args ) throws Exception{
//    	      	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
//    	         	
//    	      	N = Integer.parseInt (   br.readLine()  ); 
//    	            	
//    	      	X = new int  [N+1];
//    	      	Y = new int  [N+1];
//    	      	for( int i=1; i<= N; i++) {
//    	        	   	   StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
//    	               	   
//    	        	   	    X[i] = Integer.parseInt ( st.nextToken()  );	
//    	        	   	    Y[i] = Integer.parseInt ( st.nextToken()  );	
//    	     	}
//    	      	long ans = 0;
//    	      	for (int i=1;i<=N;i++){
//    	      	     	int j = i%N + 1;
//    	               	ans += (long)X[i]*Y[j] - (long)X[j]*Y[i];
//    	      	}
//    	      	ans = Math.abs(ans);
//    	          System.out.printf("%d.%d\n", ans/2, ans%2 == 0 ? 0 : 5 );
//    	   }  		
//    	 }  
//
//
//
////묶음 행사
//public class sourceDay9_5 {
//    	 static int N ;
//    	 static ArrayList<Data> data;   	      	 
//    	   public static void main( String[] args ) throws Exception{
//    	      	BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 	       	  
//    	      	N = Integer.parseInt (   br.readLine()  ); 
//    	      	data = new ArrayList<Data>();	          
//    	      	for( int i=1; i<= N; i++) {
//    	        	   	   StringTokenizer st = new StringTokenizer( br.readLine() ) ;       	               	   
//    	        	   	    long w = Integer.parseInt ( st.nextToken()  );	
//    	        	   	    long h = Integer.parseInt ( st.nextToken()  );	      	               	    
//    	        	   	    data.add( new Data( w, h));
//    	     	}     	         
//    	      	Collections.sort( data );           
//    	      	Stack<Data> stack = new Stack();      	   	   
//    	      	for( Data  d : data ) {
//    	    	     while( !stack.isEmpty() && stack.peek().h <= d.h ) {
//    	    	       	stack.pop();
//    	    	     } 	
//    	    	     stack.push( d);     	
//    	      	}    	      	
//    	      	long[] dp = new long [ stack.size() + 1 ];
//    	      	Arrays.fill( dp , Integer.MAX_VALUE);
//    	      	dp[0] = 0;
//    	      	for( int i =1 ; i <=  stack.size(); i++) { 
//    	    	   for( int j =0 ; j < i; j++) {
//    	    	        	  long calc = dp[j] + stack.get(j).h * stack.get(i-1).w;      	    	        	 
//    	              dp[i] = Math.min(  dp[i] , calc) ;
//    	    	   }  
//    	      	}
//    	          System.out.printf("%d\n",  dp[ stack.size()  ]);
//    	   }  	  
//    	   public static class Data implements  Comparable<Data> {
//              	   long  w;
//              	   long  h;              	  
//              	   Data( long w, long h) {
//                         		this.w = w;
//                         		this.h = h;
//              	   }             	  
//              	   public int compareTo( Data d) {
//                         		if( this.w != d.w  ) return Long.compare( this.w  , d.w);                   		
//                         		return Long.compare( this.h  , d.h);
//              	   }
//    	   }
//    		
//    	 }  
//
