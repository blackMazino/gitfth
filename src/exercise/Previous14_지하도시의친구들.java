package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Previous14_지하도시의친구들 {
/*
흠.. 잘모르겠다;;
[입출력 예]
입력
2
3 2
2 3 1
5 2
2 1 2 3 3
 
출력
#1 6
#2 8
 
(sample_input.txt에 대한 출력)
#1 19221
#2 30663
#3 42356
#4 29248
#5 35109
#6 19918804
#7 113718600
#8 199499500
#9 200000000
#10 0

 */
	static int TC, N,K, seq;//2 ≤ N ≤ 200,000, 0 ≤ K ≤ 200,000
	static int [] next, vCnt,visited;
	static long answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous14.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			next = new int[N+1];
			vCnt = new int[N+1];
			visited = new int[N+1];
			st = new StringTokenizer(br.readLine());					
			for(int n=1;n<=N;n++ ){
				int e = Integer.parseInt(st.nextToken());
				next[n] = e;
			}
			seq = 1;
			for(int n=1;n<=N;n++){
				if(visited[n]==0){
					solve(n);
				}
			}
			
			answer = 0;
			for(int n=1;n<=N;n++){
				answer += Math.min(vCnt[n], K);
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
	private static void solve(int s) {
		LinkedList<Integer> stack = new LinkedList<>();
		int here = s;
		
		//findCycle
		while(true){
			if(visited[here]!=0){
				if(visited[here]>= visited[s]){
					int cnt = seq - visited[here];
					for(int i=0;i<cnt;i++){
						vCnt[stack.removeLast()] = cnt-1;
					}
				}
				break;
			}
			stack.add(here);
			visited[here] = seq++;
			here = next[here];
		}
		while(!stack.isEmpty()){
			int top = stack.removeLast();
			vCnt[top] = vCnt[next[top]] + 1 ;
		}
	}

}
