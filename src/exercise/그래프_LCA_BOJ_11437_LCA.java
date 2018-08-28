//그래프-LCA
package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그래프_LCA_BOJ_11437_LCA {
/*
N(2 ≤ N ≤ 50,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 
루트는 1번이다.
두 노드의 쌍 M(1 ≤ M ≤ 10,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다

입력
첫째 줄에 노드의 개수 N이 주어지고, 다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다. 
그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고, 다음 M개 줄에는 정점 쌍이 주어진다

M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.

15
1 2
1 3
2 4
3 7
6 2
3 8
4 9
2 5
5 11
7 13
10 4
11 15
12 5
14 7
6
6 11
10 9
2 6
7 6
8 13
8 15


2
4
2
1
3
1

 * */
	static int N,M;
	static ArrayList<Integer>[] con;
	static int [] depth;
	static int [] parent;
	static boolean [] visited;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		con = new ArrayList[N+1];
		for(int i=1;i<=N;i++){
			con[i] = new ArrayList<Integer>();			
		}
		depth = new int [N+1];
		parent = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(parent, -1);
		for(int i=1;i<N;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//연결, 누가 부모인진 알수없다
			con[a].add(b);
			con[b].add(a);
		}
		dfs(1,1,0);//루트는 1번
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for(int i=1;i<=M;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int aDepth = depth[a];
			int bDepth = depth[b];
			
			//FIND LCA! balance depth of a and b
			while(aDepth > bDepth){
				a = parent[a];
				aDepth--;
			}
			while(bDepth > aDepth){
				b = parent[b];
				bDepth--;
			}
			while(a!=b){
				a = parent[a];
				b = parent[b];
			}
			System.out.println(a);
		}
		br.close();
	}

	private static void dfs(int cur, int d, int p) {
		depth[cur] = d;
		parent[cur] = p;
		for(int next :con[cur]){
			if(next != p){
				dfs(next,d+1,cur);
			}
		}
		
	}
	
	private static void dfs2(int cur, int d, int p){
		depth[cur] = d;
		parent[cur] = p;
		for(int t : con[cur]){
			if(!visited[t]){
				dfs2(t,d+1,cur);
			}
		}
	}
	
}
