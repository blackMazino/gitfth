package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//AD hoc
public class Day6_01_AD_너겟 {

/*
태양이는 N개의 너겟 상자를 가지고 있다. 
i번째 너겟 상자에는Ai개의 너겟이 들어있다.

명우는 태양이에게 정확히x개의 너겟을 달라고 부탁하는데, 
태양이는 가지고 있는 너겟 상자 중 일부를 선택해 명우에게 전달한다.

태양이가 가지고 있는 너겟 상자에 있는 너겟 개수에 따라, 
명우에게 전달할 수 없는 너겟 갯수가 있을 수 있다.
예를 들어, 태양이가 가지고 있는 너겟 상자의 개수는 3개고, 
각 상자에 너겟이 1개, 2개, 5개 있다면, 명우에게 4개의 너겟을 전달할 방법이 없다.

심술궂은 명우는 태양이를 당황시키려고 한다. 
명우를 도와 전달할 수 없는 너겟 갯수 중에 가장 작은 값을 구하는 프로그램을 작성하시오.

입력
첫 줄에 태양이가 가지고 있는 상자의 개수를 나타내는 자연수 N이 주어진다. (1≤N≤100,000)
둘째 줄에 N개의 자연수가 공백으로 구분되어 주어지는데,
i번째로 주어지는 수는i번째 상자에 들어있는 너겟의 수Ai를 의미한다. 
주어지는 수는10^9을 넘지 않는다.

출력
명우에게 전달할 수 없는 너겟 갯수 중 가장 작은 수를 출력한다.

3
1 2 5

4

 * */	
	
	static int N;
	static int[] A;
	static long[] X;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int[N+1];
			st = new StringTokenizer(br.readLine());

			X = new long [212121];
			Arrays.fill(X, -1);		
			for(int i=1; i<=N;i++){
				int in = Integer.parseInt(st.nextToken());
				A[i] = in;
				X[in] = in;
			}
			Arrays.sort(A);		
			
			/*강사풀이			
			long now = 0;
			for(int i=1;i<=N;i++){
				if(A[i] > now +1){
					System.out.println(now+1);
					return;
				}
				now += A[i];
			}
			System.out.println(now+1);
			*/
			//본인풀이 - fail
			long now = 0;
			for(int i=1;i<=N;i++){			
				now += A[i];					
				if(X[(int) now] < 0){
					X[(int) now] = now;
				}
			}
			//Arrays.sort(X);
//			for(int i=1;i<=4;i++){
//				System.out.println(X[i]);
//			}
			for(int i=1;i<=212120;i++){
				if(X[i] < 0){
					System.out.println(i);
					return;
				}
			}
			
			
//		}
		br.close();
	}
}
