package preTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1709_BoardGame2 {
	 static int T,N,K,M,remain, ans,iSize,iC;	
	 static int[] Board,cardUseHis;
	 static int[][] D =new int[(int)Math.pow(10+1, 6)][6+1]; // Max로 지정, 입력 변수에 맞게 생성했더니 Timeout 남
	 static int[] Position =new int[(int)Math.pow(10+1, 6)];  	// Max로 지정, 입력 변수에 맞게 생성했더니 Timeout 남

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  System.setIn(new FileInputStream("src/preTest/Q1709.txt"));//D:/workspace/swTest_Pro/src/preTest/Q1709.txt
		  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		  
		  T=Integer.parseInt(br.readLine());
		  
		  for(int tc=1;tc<=T;tc++) {
			   StringTokenizer st=new StringTokenizer(br.readLine());
			   
			   N=Integer.parseInt(st.nextToken());//보드의 크기
			   K=Integer.parseInt(st.nextToken());//카드의 종류
			   M=Integer.parseInt(st.nextToken());//각 카드 사용가능횟수
			   
			   Board=new int[N+1+K];//시작을 통과한 경우를 생각해야 하므로 보드크기N+1에 카드종류 K를 한번 더한다 			   
			   
			   st=new StringTokenizer(br.readLine());
			   for(int i=1;i<=N;i++){ 
				   Board[i]=Integer.parseInt(st.nextToken());
			   }
			   
			   System.out.println("#"+tc+" "+getAnswer());
		  }
	}

	private static int getAnswer() {
		int result = Integer.MAX_VALUE;
		
		for(int i=1;i<=K;i++){
			Board[i+N] = Board[i];//보드크기를 초과하여(한바퀴 지나가고 나서)의 보드에 대한 값 입력
			D[i][0] = M+1;
			D[0][i] = 1;
		}
		
		/*
		for(int i=0;i<=(int)Math.pow(M+1, K+1);i++){
			for(int j=0;j<K+1;j++){
				System.out.print(D[i][j]+" ");
			}
			System.out.println();
		}
		*/
		
		cardUseHis = new int[K*M+1]; //카드사용이력
		remain = (int)Math.pow(M+1, K);//남은 카드
		
		//초기화
		Arrays.fill(Position, 0);
		Position[0]=1;
		
		for(int i=0; i<=remain;i++){
			if(Position[i]==0||Position[i]>N){
				for(int j=1;j<=K;j++){
					D[i][j] = 0;
				}
			}
			
			int tmp = i;
			for(int j=K;j>0;j--){
				cardUseHis[j] = tmp / (int)Math.pow(M+1, j-1);
				tmp = tmp%(int)Math.pow(M+1, j-1);
			}			
			result = doGame(result, i);
		}
		
		
		return result-1;
	}

	private static int doGame(int result, int i) {
		
		
		for(int j=1; j<=K;j++){
			if(D[i][j] != 0){
				for(int k=1;k<=K;k++){
					if(j!=k && cardUseHis[k] < M){			
						int n = (int)Math.pow(M+1, k-1) + i;
						Position[n] = Position[i]+k;
						
						if(D[n][k]==0 || D[n][k] > D[i][j]+Board[Position[n]]){
							D[n][k] = D[i][j]+Board[Position[n]];
						}
						
						result = (Position[n]>N)? Math.min(result, D[n][k]):result;
					}
				}
			}
			D[i][j] = 0;
		}
		return result;
	}

}
