package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그래프_LCA_KOITP_상인2 {
/*
사막에 N(1≤N≤100,000)개의 도시가 있고, 각 도시에는 1부터 N까지의 번호가 매겨져 있다. 
두 도시를 연결하는 N−1개의 길이 있으며, 임의의 두 도시 사이에는 길들을 따라 이동할 수 있는 경로가 하나만 존재한다.
어떤 사람이 하나의 길을 지나는 데는 정확히 하루가 걸리며, 길 이외의 장소에서는 마실 물을 구할 수 없어 이동할 수 없다.
한 상인이 1번 도시부터 N번 도시까지를 순서대로 방문하며 장사를 하려고 한다. 
이 상인이1번 도시부터 N번 도시까지 순서대로 방문하는데 며칠이 걸리는지를 계산하여 출력하시오.

첫 줄에 도시의 수 N(1≤N≤100,000)이 주어진다.
두 번째 줄부터N번째 줄까지는 각각의 줄에 하나의 길의 정보가 주어진다. 
하나의 줄에는 두 개의 서로 다른1 이상N 이하의 정수가 주어지며, 이는 이 두 도시를 연결하는 길이 존재한다는 의미이다.


상인이 1번 도시에서 출발하여 2번 도시,...,N−1번 도시를 순서대로 거쳐 N번 도시에 도착하기까지 며칠이 결리는지를 출력한다.
5
3 1
2 4
3 5
1 4
===========
10
*/
	static int N;
	static int[]depth;
	static int[][]parent;
	static boolean[] visited;
	static ArrayList<Integer>[] con;
	static long answer;
	static int Max = 16;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		con = new ArrayList[N+1];
		depth = new int[N+1];//깊어봐야 N
		visited = new boolean[N+1];
		parent = new int [Max+1][N+1];//[조상노드][현재노드]
		
		for(int i=1;i<=N;i++) con[i] = new ArrayList<Integer>();		
		
		for(int i=1;i<=N-1;i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			con[s].add(e);
			con[e].add(s);			
		}
		
		dfsForLCA(1,1,0);
//		bfsForLCA(1,1,0);
		
		//Sparase table
		for(int k=0;k<Max;k++){
			for(int n=1;n<=N;n++){
				parent[k+1][n] = parent[k][ parent[k][n] ];//n의 2^(k+1)번째 조상 = (n의 k번째 조상의) 2^k번째 조상과 같다
			}
		}		
		
		answer = 0;
		for(int i=1;i<N;i++){
			answer += getMovingDay(i,i+1);
		}
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		
	}

	private static void dfsForLCA(int cur, int d, int p) {
		depth[cur] = d;
		parent[0][cur] = p;//2^0 즉 바로위 조상
		visited[cur] = true;
		for(int next:con[cur]){
//			if(next != p){
			if(!visited[next]){
				visited[next] = true;
				dfsForLCA(next,d+1,cur);
			}
		}	
	}
	
	public static void bfsForLCA(int cur, int d, int p){
		Queue<Integer> que = new LinkedList<>();
		que.add(cur);
		depth[cur] = d;
		parent[0][cur] = p;
		visited[cur] = true;
		while(!que.isEmpty()){
			int u = que.poll();			
			for(int next : con[u]){
//				if(next != p){
				if(!visited[next]){
					que.add(next);
					visited[next] = true;
					depth[next] = depth[u] +1;
					parent[0][next] = u;
				}
				
			}
		}
	}
	
	private static long getMovingDay(int s, int e) {
		long result = 0;
		if(s==1){
			result = depth[e]-depth[s];
		}else{
			int ca = lca(s,e);
			result = (depth[e]-depth[ca]) + (depth[s]-depth[ca]);
		}				
		return result;
	}

	private static int lca(int a, int b) {
		if(depth[b] > depth[a]) return lca(b,a);
		int d = depth[a] - depth[b];
		int k=0;
		while(d>0){
			if(d%2==1) a=parent[k][a];
			d/=2;
			k++;
		}
		if(a==b) return a;
		for(int i=Max;i>=0;i--){
			if(parent[i][a] != parent[i][b]){
				a=parent[i][a];
				b=parent[i][b];	
			}			
		}		
		return parent[0][a];
	}

}
 