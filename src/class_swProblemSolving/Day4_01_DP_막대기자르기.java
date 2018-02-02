package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP
public class Day4_01_DP_막대기자르기 {
/*
길이가 N인 막대기가 있다. 막대기를 길이가 자연수가 되도록 여러 조각으로 자를 수 있다. 각 길이에 대해 값어치가 있을 때, 값어치 합이 최대가 되도록 막대기를 자르자.

예를 들어, 길이가 4인 막대기가 있고 각 길이 별 값어치가 아래와 같다고 하자.
|  length  | 1 | 2 | 3 | 4 |
|----------|---|---|---|---|
|   cost   | 1 | 5 | 8 | 9 |
이 때, 길이 2인 막대기가 두 개가 되도록 전체 막대기를 자를 경우 전체 값어치가 10이 되어 최대가 된다.

입력
첫 줄에 막대기의 길이 N이 주어진다. (1≤N≤3,000)
둘째 줄에 N 개의 자연수가 공백으로 구분되어 주어지는데, 
i번째로 주어지는 수는 길이가 i인 막대기의 값어치를 의미한다.
 이 때 주어지는 수는 1,000를 넘지 않는다


출력
첫 줄에 값어치 합이 최대가 되도록 막대기를 자를 때, 값어치 합을 출력한다.

4
1 5 8 9

10
*/
	
	static int N;
	static int []cost,D;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cost = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++){
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			/*
			 * 점화식
			 * D[i] = max(현재까지의 값 , 직전+현재길의 cost)
			 * */
			
//			D = new int[N+1];
//			for(int i=1;i<=N;i++){
//				for(int j=1;j<=i;j++){
//					D[i] = Math.max(D[i], D[i-j]+cost[j]);
//				}
//			}
//			System.out.println(D[N]);
			
			
			int revenue = 0;
			int []r = new int [N+1];
			r[0] = 0;
			for(int i=1;i<=N;i++){
				for(int j=1;j<=i;j++){
					if(revenue < cost[j] + r[i-j]){
						revenue = cost[j] + r[i-j];
					}					
				}
				r[i] = revenue;	
			}
			System.out.println(revenue);
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
	
}
/*
//막대기자르기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
   
public class source {
    static int N;
    static int[] A, D;
       
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i] = Integer.parseInt(st.nextToken());
        D = new int[N+1];
        for (int i=1;i<=N;i++){
            for (int j=1;j<=i;j++) D[i] = Math.max(D[i], D[i-j]+A[j]);
        }
        System.out.println(D[N]);
    }
}
*/
