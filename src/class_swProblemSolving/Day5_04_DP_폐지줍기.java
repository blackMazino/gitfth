package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day5_04_DP_폐지줍기 {
/*
N×N 격자로 이루어진 도시가 있다. 이 도시 군데군데에는 폐지가 버려져 있다.

범수는 가장 왼쪽 위 격자 (1,1)에서 출발하여 가장 오른쪽 아래 격자 (N,N)
까지 이동하며 폐지를 줍는데, 최단 경로를 따라가야만 한다. 
즉, 인접한 오른쪽 칸 또는 아래쪽 칸으로만 이동할 수 있다. 
이 때, 범수가 수집할 수 있는 폐지의 최대값을 출력하시오.

출발점과 도착점에 위치한 폐지 또한 주울 수 있다.

입력
첫 번째 줄에는 도시의 크기 N이 주어진다. (2≤N≤200)
두 번째 줄부터 N개의 줄에 걸쳐 도시의 정보가 주어진다. 
도시의 정보 중 i번째 줄의 j번째 숫자는 격자 (i,j)에 버려진 폐지의 양 Aij을 나타낸다. (0≤Aij≤1000)
출력
범수가 주울 수 있는 최대 폐지 양을 출력한다.

4
1 0 1 7
2 0 2 0
0 2 2 1
1 3 3 2

13
*/
	static int N;
	static int [][] A,D;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int [N+1][N+1];
			D = new int [N+1][N+1];
			for(int i=1;i<=N;i++){
				st = new StringTokenizer(br.readLine());
				for(int j=1;j<=N;j++){
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//D[i][j] : D[i][j]에서의 수집한 폐지의 최대값
			//D[i][j] = max( D[i-1][j], D[i][j-1] ) + A[i][j];			
			D[1][1] = A[1][1];
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(i==1 && j==1){
						continue;
					}
					if(i==1){//1번열(row)은 좌측에서만 온다.
						D[i][j] = A[i][j] + D[i][j-1];
					}else if(j==1){//1번행(col)은 위에서만 온다.
						D[i][j] = A[i][j] + D[i-1][j];
					}else{
						D[i][j] = Math.max(D[i-1][j], D[i][j-1]) + A[i][j];
					}
				}
			}
			System.out.println(D[N][N]);
//		}
		br.close();
	}
}
