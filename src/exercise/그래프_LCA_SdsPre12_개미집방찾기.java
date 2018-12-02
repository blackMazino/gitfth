package exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 그래프_LCA_SdsPre12_개미집방찾기 {

/*
 * (입력)
3
8
5 0 4 8 5 3 7
4 0 2 1 4
8 1 4 1 4 7 8
1 0 4 7 3 4 6
0 0 3 3 5 8
4 1 2 7 4
8 0 2 5 8
5 1 1 5
5
3 0 3 3 5 2
1 1 2 4 1
0 1 4 1 2 3 4
1 0 1 3
3 1 4 1 3 4 5
3
0 0 1 1
1 0 1 1
1 1 1 1
(출력)
#1 16
#2 10
#3 4

(sample_input.txt에 대한 출력)
#1 328
#2 304
#3 856
#4 900
#5 7360
#6 6940
#7 16340
#8 110224
#9 253970
#10 132502390

 */
	static int TC,N, R;
	static long answer;
	static ArrayList<Integer>[] con;
	static ArrayList<Integer>[] route;	
	static int depth[], parent[][];	
	static boolean visited[];
	static int Max = 16;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous12.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			N = Integer.parseInt(br.readLine());
			con = new ArrayList[N+1];
			route = new ArrayList[N+1];
			for(int i=1;i<=N;i++) {
				con[i] = new ArrayList<>();
				route[i] = new ArrayList<>();
			}
			
			depth = new int [N+1];
			parent = new int[Max+1][N+1];
			visited = new boolean [N+1];
			
			for(int n=1;n<=N;n++){
				st = new StringTokenizer(br.readLine());
				int p   = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				parent[0][n] = p;
				if(p==0){
					R=n;
				}else{
					con[p].add(n);
//					con[n].add(p);
				}
				
				for(int i=1;i<=cnt;i++){
					route[n].add(Integer.parseInt(st.nextToken()));
				}
			}	
			
			//LCA PreProcess
			depth[R] = 0;
			parent[0][R] = 0;
			visited[R] = true;
//			dfs(R); stackoverFlow 발생
			bfs(R);
			
			for(int k=0;k<Max;k++){
				for(int n=1;n<=N;n++){
					parent[k+1][n] = parent[k][parent[k][n]];
				}
			}
			
			answer = 0;
			depth[0] = Integer.MAX_VALUE;
			for(int n=1;n<=N;n++){
				int nowrt = 0;
				for(int r:route[n]){
					int k = lca(n,r);
					if(k == R){
						nowrt = k;
						break;
					}
					if(r==k && depth[nowrt] > depth[k]) nowrt = k;
					
				}
				answer += 2*(depth[n]-depth[nowrt]);
			}
			System.out.println("#"+tc+" "+answer);
			
		}
	}
	private static void bfs(int s) {
		LinkedList<Integer> que = new LinkedList<>();
		que.add(s);
		while(!que.isEmpty()){
			int q = que.poll();
			for(int n : con[q] ){
				que.add(n);
				depth[n] = depth[q] +1;
			}
		}
	}
	private static void dfs(int i) {
		for(int n : con[i]){
			if(!visited[n]){
				visited[n] = true;
				depth[n] = depth[i] +1;
				parent[0][n] = i;
				dfs(n);
			}
		}
		
	}
	public static int lca(int a,int b){
//		if(depth[a] < depth[b]) return lca(b,a);
		//문제에 의해 루트~목적지 노드중 하나임. 즉 출발한 방의 depth는 목적지 depth보다 클수 없다
		if(depth[a] < depth[b]) return Integer.MAX_VALUE;
		int d = depth[a] - depth[b];
		int k =0;
		while(d>0){
			if(d%2==1) a = parent[k][a];							
			d/=2; k++;
		}
		if(a==b) return a;
		return Integer.MAX_VALUE;
		//문제의 의해서 루트~목적지 의 노드중 하나임.
		//   root
		//  /    \
		// 1      3
		// 1이 목적지 인 경우 3은불가능 
//		for(int i=Max;i>=0;i--){
//			if(parent[i][a]!=parent[i][b]){
//				a = parent[i][a];
//				b = parent[i][b];
//			}
//		}
//		return parent[0][a];
	}

}
