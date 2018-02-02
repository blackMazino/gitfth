package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Homework4_DP_동전교환 {
/*
우리나라 동전 단위는 1원, 5원, 10원, 50원, 100원, 500원의 6단계로 이루어진다. 잔돈 256원을 내주려면, 100원 2개, 50원 1개, 5원 1개, 1원 1개로 해서 모두 5개의 동전이 필요하다.

만약 동전 단위가 1원, 4원, 6원의 3단계로 이루어진 나라에서 잔돈 8원을 내주려면 
6원 1개, 1원 2개도 가능하고, 4원 2개로도 가능하다. 
앞의 경우에는 동전이 3개, 뒤의 경우에는 동전이 2개 필요하다.

동전의 개수를 최소로 하면서 동전을 내주는 것이 목적이라면 뒤의 방법을 택해야한다.
동전들의 단위가 주어지고, 원하는 잔돈이 주어질 때, 그 잔돈에 맞추기 위해 필요한 최소의 동전 수를 구하시오. 
갖고 있는 동전의 수는 무한하다.

첫 번째 줄에 동전의 단계 수 N이 주어진다. (1 ≤ N ≤ 10)

두 번째 줄에 각 동전들의 단위가 공백으로 분리되어 주어진다. 각 동전들의 단위는 1 이상 64,000 이하이다.

세 번째 줄에 잔돈 W가 주어진다. (1 ≤ W ≤ 64,000)

3
1 4 6
8

2
*/
	
	static int N,W;
	static int[] coins,D ;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			coins = new int[N+1];
			for(int i=1;i<=N;i++){
				coins[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken()); 
//			int cnt = Integer.MAX_VALUE; //+1하면 Integer.MIN_VALUE가 됨
			// W = a1*coins[1] + a2*coins[2] + .... ak*coins[k]+ an*coins[N]
			// min (a1+a2+...+an)			
			
			/* D[i] = i원을 바꾸는데 필요한 최소동전의 갯수
			 * 막대자르기 처럼 뒤에서 coin을 붙였을때
			 * coins[1] 1개 나머지는 i - coins[1] -> D[i- coins[1]] +1
			 * coins[2] 1개 나머지는 i - coins[2] -> D[i- coins[2]] +1
			 * ...
			 * coins[N] 1개 나머지는 i - coins[N] -> D[i- coins[N]] +1
			 * 
			 * i-> W, j->N
			 * D[i] = min( D[i-coins[j]] + 1)
			 */
			for(int i=1;i<=W;i++){
				D[i] = (int) 1e9;//10억
				for(int j=1;j<=N;j++){
					if(i-coins[j] >= 0){
						D[i] = Math.min(D[i], D[i-coins[j]]+1);
					}
				}				
			}			
			System.out.println(D[W]);
//		}
		br.close();
	}
}
/*
//동전 교환
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
   
public class source {
    static int N, W;
    static int[] A, D;
       
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) A[i] = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(br.readLine());
        D = new int[W+1];
        for (int i=1;i<=W;i++){
            D[i] = (int)1e9;
            for (int j=1;j<=N;j++) if (i-A[j] >= 0){
                D[i] = Math.min(D[i], D[i-A[j]]+1);
            }
        }
        System.out.println(D[W]);
    }
}
*/
