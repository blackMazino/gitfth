package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day6_05_DP_출근 {
/*
삼성이는 매일 출근을 한다. 도로는 아래 그림과 같이 
가로 방향 도로가(H+1)개,세로 방향 도로가(W+1)개가 바둑판 모양으로 배치되어 있다. 
삼성이네 집은(1,1)이고, 회사는(H+1,W+1)에 있다.
(a,b)는 위쪽에서a번째, 왼쪽에서b번째에 있는 교차로이다.

 (1,1) - - - - (1,W+1)
      | | | | |
       - - - -
      | | | | |
       - - - -     
      | | | | |
(H+1,1)- - - -(H+1,W+1)  

매일 같은 경로로 운전해간다면 졸음운전의 위험이 굉장히 높아진다.
따라서 아래와 같은 재미있는 방법으로 회사까지 가려고 한다.

항상 오른쪽, 또는 아래쪽으로 이동한다.
맨 아래와, 맨 오른쪽을 제외한 각 교차로에 ‘아’ 혹은 ‘오’가 적혀있다.
‘아’라고 적힌곳에 있다면, 아래로 가야한다. 
또 ‘오’라고 적힌곳에 있다면 오른쪽으로 가야한다.
지나친 교차로의 ‘아’와 ‘오’는 다음날 서로 바뀐다. 
‘아’를 지났다면, 다음날 그 교차로는 ‘오’로 바뀌어져 있다.
N번째 날의 출근 경로를 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 H,W,N이 주어진다.(1≤H,W≤1,000,1≤N≤10,000,000)
두 번째 줄부터H개 줄에 걸쳐 각 줄에W개의 정수가 주어진다. 
이 정수는 맨 아래와 오른쪽을 제외한 교차로에 적혀있는 ‘오’, ‘아’에 대한 정보이다. 
0은 ‘아’,1은 ‘오’를 의미한다.

출력
N번째 산책에서 가장 처음 도착하는 맨 아래, 또는 맨 오른쪽의 교차로를 
(i,j)라 할 때,i와j를 공백으로 구분하여 출력한다.

3 4 3
1 0 1 1
0 1 0 0
1 0 1 0

1 5
*/
	static int H,W,N;
	static int [][] a;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());			
			a = new int [H+1][W+1];
			for(int i=1;i<=H;i++){
				st = new StringTokenizer(br.readLine());	
				for(int j=1;j<=W;j++){
					a[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//본인풀이 : 타임아웃
/*			
//			print();
			int i = 1;
			int j = 1;
			for(int k=1;k<=N;k++){
				i=j=1;
				while(i<=H && j<=W){
					if(a[i][j]==1){
						a[i][j] = 0;
						j++;
					}else if(a[i][j]==0){
						a[i][j] = 1;
						i++;
					}
				}
//				print();				
			}
			System.out.println(i+" "+j);
*/			
			
			//강사풀이
			int D[][] = new int [1212][1212];
			/* D[i][j] = N-1일동안 i,j 칸이 지나온 횟수
			 * i) 아(0)가 먼저나온 경우
			 * 아(0) -> D[i][j] += D[i-1][j]/2 + D[i-1][j]%2
			 * 오(1) -> D[i][j] += D[i-1][j]/2  
			 * ii) 오(1)가 먼저나온경우
			 * 오(1) -> D[i][j] += D[i][j-1]/2 + D[i][j-1]%2
			 * 아(0) -> D[i][j] += D[i][j-1]/2
			 * */
			//D배열의 특징에 따라 D배열 작성
			D[1][1] = N-1;
			for(int i=1;i<=H;i++){
				for(int j=1;j<=W;j++){
					if(i==1&&j==1) continue;
					//아(0)가 먼저나온 경우
					if(a[i-1][j]==0) D[i][j] += D[i-1][j]/2 + D[i-1][j]%2;
					else D[i][j] += D[i-1][j]/2;
					//오(1)가 먼저나온경우
					if(a[i][j-1]==1) D[i][j] += D[i][j-1]/2 + D[i][j-1]%2;
					else D[i][j] += D[i][j-1]/2;
				}				
			}
			//홀수인 경우 지나간 자리를 바꿔준다 
			for(int i=1;i<=H;i++){
				for(int j=1;j<=W;j++){
					if(D[i][j]%2 ==1){//홀수이면 서로 바꿔준다
						if(a[i][j]==0) a[i][j] = 1;
						else a[i][j] = 0;						
					}
				}
			}
			int i=1;
			int j=1;
			while(i<=H && j<=W){
				if(a[i][j]==1){
					a[i][j] = 0;//굳이 또 안바꿔줘도 되긴 하다
					j++;
				}else if(a[i][j]==0){
					a[i][j] = 1;//굳이 또 안바꿔줘도 되긴 하다
					i++;
				}
			}
			System.out.println(i+" "+j);
			
//		}
		br.close();
	}

	private static void print() {
		System.out.println("================");
		for(int i=1;i<=H;i++){			
			for(int j=1;j<=W;j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println("");
		}
		
	}
	
}
