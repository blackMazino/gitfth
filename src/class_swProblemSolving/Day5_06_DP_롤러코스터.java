package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//못품 다시풀기
public class Day5_06_DP_롤러코스터 {
/*
소들은 롤러코스터를 짓고있다! 소들은 자신들이 가지고 있는 돈을 활용해서 최대한 재밌는 롤러코스터를 만들고 싶어한다.

롤러코스터는 직선형으로, 길이가 L이다. 소들이 롤러코스터를 지을 때, 롤러코스터는 N개의 교체 가능한 부품들중 일부로 구성되어야 한다.
각 부품 i는 고정된 길이 Wi를 가지고 있다. 그리고 다양한 지형의 굴곡 때문에, i번째 부품은 오직 Xi의 위치를 시작점으로만 놓을 수 있다.
소들은 다양한 롤러코스터를 0부터 L까지 빈틈없이 채우고 싶어한다. 만약 중간에 빈칸이 있으면 롤러코스터를 구성할 수 없다. 또한, 각 부품끼리 겹쳐서 롤러코스터를 건설하는 경우도 없다.

각 i번째 부품은 "재미도" Fi과 짓는데 드는 비용 Ci가 있다. 총 비용은 롤러코스터를 구성하는 부품을의 비용의 합으로 계산된다. 마찬가지로 총 재미도의 합은 롤러코스터를 구성하는 부품들의 재미도의 합으로 계산된다.

소들의 총 예산은 B이다. 소들을 도와 예산내에서 가장 큰 재미도를 가진 롤러코스터를 지을 수 있도록 도와주자!

입력
첫 번재 줄에 세개의 정수 L,N,B가 공백으로 분리되어 주어진다. (1≤L≤1,000, 1≤N≤10,000, 1≤B≤1,000)
두 번째 줄부터 N개의 줄에 걸쳐 각 부품들의 Xi,Wi,Fi,Ci가 공백으로 분리되어 주어진다. 
(0≤Xi≤L−Wi, 1≤Wi≤L, 1≤Fi≤1,000,000, 1≤Ci≤1,000)
출력
첫 번째 줄에 소들이 예산안에 각 부품들을 가지고 지을 수 있는 롤러코스터의 최대 재미도의 합을 출력한다. 만약, 롤러코스터를 짓는 방법이 없다면 -1을 출력한다.

3번, 5번, 6번 부품들을 이용하면 롤러코스터는 비용 7을 이용해서 재미도 17의 롤러코스터를 만들 수 있다. 반면, 1번, 2번 부품들을 이용하면 재미도는 25이지만 비용이 12가 되어 예산(10)을 초과하게 된다.

5 6 10
0 2 20 6
2 3 5 6
0 1 2 1
1 1 1 3
1 2 5 4
3 2 10 2

17

*/
	static int N;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			
			
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
	
}


/*
import java.io.*;
import java.util.*;
  
public class source {
    static int L, N, B;
    static int[] W, C, F;
    static ArrayList<Integer>[] list;
    static int[][] D;
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        W = new int[N+1]; C = new int[N+1]; F = new int[N+1];
        list = new ArrayList[L];
        for (int i=0;i<L;i++) list[i] = new ArrayList<>();
        for (int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
            F[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            list[x].add(i);
        }
        D = new int[L+1][B+1];
        for (int i=0;i<=L;i++) for (int j=0;j<=B;j++) D[i][j] = Integer.MIN_VALUE;
        D[0][0] = 0;
        for (int i=0;i<L;i++) for (int j=0;j<=B;j++) if (D[i][j] > Integer.MIN_VALUE){
            for (int k: list[i]){
                if (j + C[k] <= B &&
                    D[i + W[k]][j + C[k]] < D[i][j] + F[k])
                    D[i + W[k]][j + C[k]] = D[i][j] + F[k];
            }
        }
        int ans = -1;
        for (int i=0;i<=B;i++) ans = Math.max(ans, D[L][i]);
        System.out.println(ans);
    }
}
 */