package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Homework6_DP_정사각형2 {
/*
문제
1로만 이루어진 정사각형을 1-정사각형이라고 한다.

0과 1로 이루어진 N*N 사각형이 주어질 때, 크기 2*2 이상의 1-정사각형을 찾아 개수를 출력하자.

입력
첫 번째 줄에 정사각형의 크기 N이 주어진다. (2 ≤ N ≤ 3,000)

두 번째 줄부터 N개의 줄에 걸쳐 각 줄에 N개의 숫자가 공백으로 분리되어 주어진다. 각 숫자는 0또는 1이다.

출력
첫 번째 줄에 크기 2*2 이상의 1-정사각형의 개수를 출력한다.

3
0 1 0
1 1 1
1 1 1

2

6
1 0 1 1 1 1
0 0 1 1 1 1
1 1 1 1 1 1
0 0 1 1 1 1
1 0 1 1 0 1
1 1 1 0 0 1

15
*/
	static int N;
	static int[][] sq, mark;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			sq = new int [N][N];
			
			for(int i=0; i<N;i++){
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N;j++){
					sq[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			long cnt = 0;
//			print();
			//forloop를 돌면서 현재 위치가 정사각형의 좌측상단일 경우를 카운트
			//2*2 이상부터
			//time out
//			int k=2;			
//			while(k<=N){				
////				System.out.println(k);
//				int t = k-1;//이동할 칸의 수
//				for(int i=0;i<N-t;i++){
//					for(int j=0;j<N-t;j++){
//						if(sq[i][j] == 1 && sq[i+t][j] == 1 && sq[i][j+t] == 1 && sq[i+t][j+t] == 1){
////							System.out.println(i+","+j+","+t);
//							if(isAllSq(i,j,t)){
//								cnt++;
//							}
//						}
//					}
//				}
//				k++;				
//			}
			//현재위치의 사각형이 가장큰 길이가 몇인지 알면 사각형이 구해진다
			//length n square = 1*1 + 2*2 + 3*3 + ... + k*k when k = n-1;
			//그렇다면 현재 위치를 좌측 상단으로 두고 에서 만들수 있는 가장큰 사각형의 길이만 구하면 된다.
			ArrayList<Integer> L = new ArrayList<Integer>();
			mark = new int [N][N];
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					
				}
			}
			
			for(int l : L){
			 cnt +=	getCnt(l);	
			}

			//강사풀이
			/*
			 * D[i][j] = (i, j)를 오른쪽 아래 꼭지점으로 하는 정사각형의 최대 크기
			 *         = min(D[i-1][j] ,D[i][j-1], D[i-1][j-1])+1
			 * Ans = Sum(max(D[i][j]-1, 0))
			 */
			 
			System.out.println(cnt);
//		}
		br.close();
	}

	private static int getCnt(int k) {
		int cnt = 0;
		for(int i=1;i<=k-1;i++ ){
			cnt += i*i;
		}
		return cnt;
	}

	private static void print() {
		System.out.println("================");
		for(int i=0;i<N;i++){			
			for(int j=0;j<N;j++){
				System.out.print(sq[i][j]+" ");
			}
			System.out.println("");
		}
		
	}

	private static boolean isAllSq(int pi, int pj, int t) {
		boolean result = true;
		for(int a=pi;a<=pi+t;a++){
			if(!result){
				break;
			}
			for(int b=pj;b<=pj+t;b++){
				if(sq[a][b]==0){
					result = false;
					break;
				}
			}

		}
		return result;
	}
}
