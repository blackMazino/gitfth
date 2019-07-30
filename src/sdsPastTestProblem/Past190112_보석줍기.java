package sdsPastTestProblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Past190112_보석줍기 {
/*
1
2 5
S5211
3.#2E\
폐지두번줍기 : https://koitp.org/problem/TWICE_PICK/read/
2
9 7
*........
.....**#.
..**...#*
..####*#.
.*.#*.*#.
...#**...
*........
5 5
.*.*.
*###.
*.*.*
.###*
.*.*.

7
8
*/
	
	static int TC,N,M,answer;//2<=N,M<200
	static int[][] gem; 
	static int[][] d1, d2;
	static char[][] r1, r2;
	static StringBuffer sb;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			gem = new int[N][M];
			
			for(int n=0;n<N;n++){
				String str = br.readLine();			
				char[] cArr = str.toCharArray();
				for(int m=0;m<cArr.length;m++){
					if('S'== cArr[m] || 'E' == cArr[m] || '.' == cArr[m]){
						gem[n][m] = 0;
					}else if('#' == cArr[m]){
						gem[n][m] = -1;
					}else{
//						gem[n][m] = Integer.parseInt(String.valueOf(cArr[m]));
						gem[n][m] = 1;
					}
				}
			}
			printGem();
			
			d1 = new int[N][M];
			r1 = new char[N][M];//경로를 저장
			for(int n=0;n<N;n++) Arrays.fill(d1[n], -1);
//			String R = "";
			for(int n=0;n<N;n++){
				for(int m=0;m<M;m++){
					int idx = n*M+m;
					if(n==0 && m==0){
						d1[n][m] = gem[n][m];
						r1[n][m] = 'S';
					}else{
						if(gem[n][m]>=0){
							if(m>0 && n==0){
								if(d1[n][m-1] >= 0){
									if(d1[n][m-1]>=0 && d1[n][m] < d1[n][m-1] + gem[n][m]){
										d1[n][m] = d1[n][m-1] + gem[n][m];
										r1[n][m] = 'L';
									}
								}
							}else if(n>0 && m==0){
								if(d1[n-1][m]>=0 && d1[n-1][m] >= 0){
									if(d1[n][m] < d1[n-1][m] + gem[n][m]){
										d1[n][m] = d1[n-1][m] + gem[n][m];
										r1[n][m] = 'U';
									}
								}
							}else {								
								int l = d1[n][m-1];
								int u = d1[n-1][m];
								if(l>=0 || u>=0){
									if(l>u){
										if(d1[n][m] < l+gem[n][m]){
											d1[n][m] = l+gem[n][m];
											r1[n][m] = 'L';
										}
									}else{
										if(d1[n][m] < u+gem[n][m]){
											d1[n][m] = u+gem[n][m];
											r1[n][m] = 'U';
										}
									}
								}
							}
						}
						
					}
				}
			}
			sb = new StringBuffer();
			cleanGem(N-1,M-1, r1);
			printGem();
			System.out.println(sb.toString());
			
			
			d2 = new int[N][M];
			r2 = new char[N][M];//경로를 저장
			sb = new StringBuffer();
			for(int n=0;n<N;n++) Arrays.fill(d2[n], -1);
			for(int n=N-1;n>=0;n--){
				for(int m=M-1;m>=0;m--){
					if(n==N-1&&m==M-1){
						d2[n][m] = 0;
						r2[n][m] = 'S';
					}else{
						if(gem[n][m]>=0){
							if(n==N-1 && m<M-1){
								if(d2[n][m+1]>=0 && d2[n][m] < d2[n][m+1] + gem[n][m]){
									d2[n][m] = d2[n][m+1] + gem[n][m];
									r2[n][m] = 'R';
								}
							}else if(m==M-1 && n<N-1){
								if(d2[n+1][m]>=0 && d2[n][m] < d2[n+1][m] + gem[n][m]){
									d2[n][m] = d2[n+1][m] + gem[n][m];
									r2[n][m] = 'D';
								}
							}else{
								int r = d2[n][m+1];
								int d = d2[n+1][m];
								if(r>=0 || d>=0){
									if(r>d){
										if(d2[n][m]<d2[n][m+1] + gem[n][m]) {
											d2[n][m]=d2[n][m+1] + gem[n][m];
											r2[n][m] = 'R';
										}
									}else{
										if(d2[n][m]<d2[n+1][m] + gem[n][m]){
											d2[n][m]=d2[n+1][m] + gem[n][m];
											r2[n][m] = 'D';
										}
									}									
								}
							}
						}
					}
				}
			}
			cleanGem(0,0, r2);
//			System.out.println(sb.toString());	
			answer = d1[N-1][M-1] + d2[0][0];
			System.out.println("#"+tc+" "+answer);
		}
	}
	private static void printGem() {
		for(int n=0;n<N;n++){
			for(int m=0;m<M;m++){
				System.out.print(gem[n][m]+" ");
			}
			System.out.println();
		}
		
	}
	private static void cleanGem(int n,int m, char r[][]) {
		int idx = n*M+m;
		if(sb.length()==0){
			sb.append(m+","+n);
//			sb.append(idx);
		}else{
			sb.append("\n");
//			sb.append(idx);
			sb.append(m+","+n);
		}
		
		char c = r[n][m];
		gem[n][m] = 0;
		if(c=='S') return;
		if(c=='L') cleanGem(n, m-1, r);
		if(c=='U') cleanGem(n-1, m, r);
		if(c=='R') cleanGem(n, m+1, r);
		if(c=='D') cleanGem(n+1, m, r);		
	}

}
/*
http://koitp.org/problem/SDS_PRO_6_1/read/


import java.io.*;
import java.util.StringTokenizer;

public class D2C6_폐지줍기 {
 public static int N;
 public static int[][] dp,A;
 
 public static void main(String[] args) throws Exception{
//  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedReader br = new BufferedReader(new FileReader("C:\\input.txt"));
  
  N = Integer.parseInt(br.readLine());
  A = new int[N+1][N+1];
  
  StringTokenizer st ;
  for(int i=1; i<=N; i++){
   st = new StringTokenizer(br.readLine());
   for(int j=1; j<=N; j++){
    A[i][j] = Integer.parseInt(st.nextToken());
   }
  }
  
  dp = new int[N+1][N+1];
  for(int i=1;i<=N;i++) for(int j=1;j<=N;j++){
   if(i ==1 && j ==1) dp[i][j] = A[1][1];
   if(i>1) dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + A[i][j]);
   if(j>1) dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + A[i][j]);
  }
  
  System.out.println(dp[N][N]);
  
 }
}
*/