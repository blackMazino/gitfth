package old;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Practice31_최대와최소 {
/*

[입출력 예]
(입력)
2
5 5
3 5 7 4 6
0 1 3
1 2 2
0 1 3
1 4 0
0 3 5
10 4
1 2 3 4 5 6 7 8 9 10
1 1 12
1 9 0
0 1 10
0 2 8
 
(출력)
#1 21 5
#2 20 2
 
(sample_input.txt 의 출력)
#1 12 7
#2 29 6
#3 59 8
#4 6343471 75079
#5 10633877 121269
#6 12771901 429767
#7 12501442 406813
#8 15347171 588802
#9 14871498 667353
#10 20305196 44694

 */
	static int TC,N,Q;
	static int[] arr;
	static long min,max;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Practice31.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			min = 0; max=0;
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++){
				arr[n] = Integer.parseInt(st.nextToken());
			}
			
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				if(a==1){
					arr[b] = c;
				}else{
					PriorityQueue<Integer> minQ = new PriorityQueue<>();
					PriorityQueue<Integer> maxQ = new PriorityQueue<>();
					for(int i=b;i<=c;i++){
						minQ.add(arr[i]);
						maxQ.add(-arr[i]);
					}
					min += minQ.peek();
					max += -maxQ.peek();
				}
			}
			System.out.println("#"+tc+" "+max+" "+min);
			
		}
	}

}
