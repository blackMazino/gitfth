import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution{
	static int T,N,K,K2;
	static long ans;
	static ArrayList<Integer>[] con;
	static int[][] iD;
	static int[] iCri;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			K2=Integer.parseInt(st.nextToken());
			
			K=K2<N?K2:N;
			
			iD=new int[K+1][N+1];
			iCri=new int[N+1];
			con=new ArrayList[N+1];
			for(int i=1;i<=N;i++) con[i]=new ArrayList<>();
			
			for(int i=1;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				
				con[a].add(b); con[b].add(a);
			}
			
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) iCri[i]=Integer.parseInt(st.nextToken());
			
			for(int i=0;i<=K;i++) {
				for(int j=1;j<=N;j++) {
					if (i==0) iD[i][j]=iCri[j];
					else if (i==1) {
						iD[i][j]=iD[i-1][j];
						for(int t:con[j]) iD[i][j]+=iD[i-1][t];
					} else {
						for(int t:con[j]) iD[i][j]+=iD[i-1][t];
						
						iD[i][j]-=iD[i-2][j]*(con[j].size()-1);
					}
				}
			}
			
//			for(int i=1;i<=N;i++) System.out.println("iD["+K+"]["+i+"]=["+iD[K][i]+"]");
			ans=0;
			for(int i=1;i<=N;i++) ans+=iD[K][i];
			System.out.println("#"+tc+" "+ans);
		}
	}
}

/*


[입출력 예]
(입력)
2
8 1
1 4
5 4
7 5
5 8
3 7
6 5
2 6
1 0 0 1 1 0 0 1
5 6
1 2
2 3
3 4
4 5
1 0 1 1 0




(출력)
#1 12
#2 15


*/

/*

package sds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_TEST_P0013_Warning {

	public static void main(String[] args) throws Exception {
		
		int N, K, ANS;
		int[][] DP = new int[101][100001]; // DP[K][N] = N번 장소에서 K칸 이동 가능할 때 만날 수 있는 범죄자 수
		ArrayList<Integer>[] AL = new ArrayList[100001]; // AL[N] = N번 장소와 연결된 장소 리스트
		for(int i = 1; i <= 100000; i++)
			AL[i] = new ArrayList<Integer>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			for(int i = 1; i <= N; i++)
				AL[i].clear();
			
			int temp1, temp2;
			for(int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				temp1 = Integer.parseInt(st.nextToken());
				temp2 = Integer.parseInt(st.nextToken());
				AL[temp1].add(temp2); AL[temp2].add(temp1);
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++)
				DP[0][i] = Integer.parseInt(st.nextToken()); // DP[0][N] = 그 장소에 범죄자가 있는가 없는가
			
			for(int i = 1; i <= N; i++) { // DP[1][N] = N번 장소에 범죄자가 있는가 + N번 장소와 연결된 장소에 범죄자가 있는가
				DP[1][i] = DP[0][i];
				for(int n : AL[i])
					DP[1][i] += DP[0][n];
			}
			
			for(int i = 2; i <= K; i++) { // DP[2][N] ~ DP[K][N] = N번 장소와 직접 연결된 장소들의 누적범죄자에서, 중복 제거
				for(int j = 1; j <= N; j++) {
					DP[i][j] = -(DP[i-2][j] * (AL[j].size() - 1)); // 중복을 미리 감소시키고
					for(int n : AL[j])
						DP[i][j] += DP[i-1][n]; // 연결된 도시들의 누적범죄자들 더한다.
				}
			}
			
			ANS = 0;
			for(int i = 1; i <= N; i++)
				ANS += DP[K][i];
			System.out.println("#" + testCase + " " + ANS);
		}
		br.close();

	}

}

 */

