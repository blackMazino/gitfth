package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day4_02_DP_assemblyLineScheduling {
/*
명우네 공장에는 두 개의 생산라인이 있고, 각 라인에는 n개의 공정이 순서대로 있다. 
하나의 제품이 완성이 되려면 두 생산라인 중 한 생산라인을 정해, 
그 생산라인에 미완성 제품이 들어가고 그 생산라인의 n개의 공정을 순서대로 지나, 
생산라인에서 생산을 마무리하여 완성된다. 중간에 생산라인을 바꿀 수도 있다.

첫 번째 생산라인의 i번째 공정에서 소요되는 시간은S1,i이고, 두 번째 생산라인의i번째 공정에서 소요되는 시간은S2,i이다. 
그리고 첫 번째 생산라인에 진입하는 시간은e1, 두 번째 생산라인에 진입하는 시간은e2이고, 
첫 번째 생산라인에서 생산을 마무리 하는 시간은x1, 두 번째 생산라인에서 생산을 마무리 하는 시간은x2이다. 
마지막으로 첫 번째 생산라인의i번째 공정을 마치고 두 번째 생산라인으로 바꾸는데 걸리는 시간은t1,i이고, 
두 번째 생산라인의i번째 공정을 마치고 첫 번째 생산라인으로 바꾸는데 걸리는 시간은t2,i이다. 즉, 명우의 공장은 아래와 같은 그림으로 표현된다.

명우는 자신이 만들어놓은 공장에서 하나의 제품을 만드는데 걸리는 최소 시간을 알고 싶어한다. 
명우를 도와 하나의 제품을 만드는데 걸리는 최소 시간을 구하는 프로그램을 작성하시오.

입력
첫 줄에 라인별 공정의 개수n, 라인별 진입 시간과 마무리 시간e1,e2,x1,x2가 주어진다. (2≤n≤300,000,1≤e1,e2,x1,x2≤200)
두 번째 줄에 S1,1,S1,2,…,S1,n를 나타내는n개의 자연수가 공백으로 구분되어 주어진다. (1≤S1,i≤200)
세 번째 줄에 S2,1,S2,2,…,S2,n를 나타내는n개의 자연수가 공백으로 구분되어 주어진다. (1≤S2,i≤200)
네 번째 줄에 t1,1,t1,2,…,t1,n−1를 나타내는n−1개의 자연수가 공백으로 구분되어 주어진다. (1≤t1,i≤200)
다섯 번째 줄에 t2,1,t2,2,…,t2,n−1를 나타내는n−1개의 자연수가 공백으로 구분되어 주어진다. (1≤t2,i≤200)
출력
첫 줄에 하나의 제품을 만드는데 걸리는 최소 시간을 출력한다.

6 2 4 3 2
7 9 3 4 8 4
8 5 6 4 5 7
2 1 1 3 4 
2 1 2 2 1

38
*/
	static int N,e1,e2,x1,x2, a1,a2,b1,b2;
	static int [][] S, T, D;
	
	
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			e1 = Integer.parseInt(st.nextToken());
			e2 = Integer.parseInt(st.nextToken());
			x1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			
			S = new int[3][N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++){
				S[1][i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++){
				S[2][i] = Integer.parseInt(st.nextToken());
			}		
			
			T = new int[3][N];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<N;i++){
				T[1][i] = Integer.parseInt(st.nextToken());			
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<N;i++){
				T[2][i] = Integer.parseInt(st.nextToken());			
			}

			
			/*
			점화식
			b1 =  S1i 
			    + min(S1(i-1)즉, a1에서 오는값 
			        , S2(i-1)즉, a2에서 오는값+ T2i)
			 * */

			//첫번째값
			a1= e1+S[1][1];
			a2= e2+S[2][1];
			for(int i=2;i<=N;i++){
				b1 = S[1][i] + Math.min(a1, a2+T[2][i-1]); 
				b2 = S[2][i] + Math.min(a2, a1+T[1][i-1]);
				a1 = b1;
				a2 = b2;
			}
			System.out.println(Math.min(b1+x1,b2+x2));
			
			//D배열을 이용해보자
			D = new int[3][N+1];
			//첫번째값
			D[1][1]= e1+S[1][1];
			D[2][1]= e2+S[2][1];
			for(int i=2;i<=N;i++){
				D[1][i] = S[1][i] + Math.min(D[1][i-1], D[2][i-1]+T[2][i-1]); 
				D[2][i] = S[2][i] + Math.min(D[2][i-1], D[1][i-1]+T[1][i-1]); 
			}
			System.out.println(Math.min(D[1][N]+x1,D[2][N]+x2));
			
			
			
//		}
		br.close();
	}
	
}
/*
//Assembly Line Scheduling
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
public class source {
    static int N;
    static int[] E, X;
    static int[][] S, T, D;
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = new int[2]; X = new int[2];
        N = Integer.parseInt(st.nextToken());
        E[0] = Integer.parseInt(st.nextToken());
        E[1] = Integer.parseInt(st.nextToken());
        X[0] = Integer.parseInt(st.nextToken());
        X[1] = Integer.parseInt(st.nextToken());
        S = new int[2][N+1];
        T = new int[2][N];
        for (int i=0;i<2;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++) S[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i=0;i<2;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<N;j++) T[i][j] = Integer.parseInt(st.nextToken());
        }
        D = new int[2][N+1];
        for (int i=0;i<2;i++) D[i][1] = E[i] + S[i][1];
        for (int j=2;j<=N;j++){
            for (int i=0;i<2;i++){
                D[i][j] = Math.min(D[i][j-1], D[1-i][j-1] + T[1-i][j-1]) + S[i][j];
            }
        }
        System.out.println(Math.min(D[0][N] + X[0], D[1][N] + X[1]));
    }
}
*/