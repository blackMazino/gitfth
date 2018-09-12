package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 그래프_LCA_KOITP_촌수계산 {
/*
https://koitp.org/problem/SDS_PRO_4_4/read/
	
우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다. 
이런한 촌수는 다음과 같은 방식으로 계산된다.
기본적으로 부모와 자식 사이를 1촌으로 정의하고, 이로부터 사람들 간의 촌수를 계산한다. 
예를 들면 나와 아버지,아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 
아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.

첫 번째 줄에 전체 사람의 수 N이 주어진다. 사람들의 번호는 각각 1, 2, ..., N으로 표현된다. (1 ≤ N ≤ 100)
두 번째 줄에 촌수를 계산해야 하는 서로 다른 두 사람의 번호 A, B가 주어진다.(1 ≤ A, B ≤ N)
세 번째 줄에 부모 자식들 간의 관계의 개수 M이 주어진다.
네 번째 줄부터 M개의 줄에 걸쳐 부모 자식간의 관계를 나타내는 두 번호 x, y가 공백으로 분리되어 주어진다. 
이 때, x는 y의 부모임을 나타낸다. (1 ≤ x, y ≤ N)

첫 번째 줄에 A, B 간의 촌수를 출력한다. 
어떤 경우에는 두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때가 있는데, 이 때에는 -1을 출력한다.

9
7 3
7
1 2
1 3
2 7
2 8
2 9
4 5
4 6

3
*/
	
	
	static int N,A,B,M;
	static ArrayList<Integer>[] con;
	static int[] parent, depth;
	static boolean [] isRoot;
	static boolean [] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		con = new ArrayList[N+1];
		depth = new int [N+1];
		visited = new boolean [N+1];
		parent = new int [N+1];
		isRoot =  new boolean [N+1];
		//Initialize
		for(int i=1;i<=N;i++){
			con[i] = new ArrayList<Integer>();
			isRoot[i] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=M;i++){
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			con[d].add(s);			
			isRoot[s] = false;
		}
		for(int i=1;i<=N;i++){
			if(isRoot[i]){//연결이 안되어 있을수도 있으므로, isRoot가  true인것 기준으로 tree를 만들자
				dfs(i);
			}
		}
		int c = getLca(A,B);
		System.out.println(c==0?-1:depth[A]+depth[B]-2*depth[c]);
	}
	
	private static int getLca(int a, int b) {
		int result = 0;
		boolean [] visA = new boolean[N+1];
		int tmp = a;
		while(tmp>0){
			visA[tmp] = true;
			tmp = parent[tmp];			
		}
		tmp = b;
		while(tmp>0){
			if(visA[tmp]){
				result = tmp;
				break;
			}
			tmp = parent[tmp];
		}
		return result;
	}

	private static void dfs(int i) {
		visited[i] = true;
		for(int n:con[i]){
			if(!visited[n]){
				visited[n] = true;
				parent[n] = i;
				depth[n] = depth[i]+1;
				dfs(n);
			}
		}
		
	}

}

/*	
	static int N,A,B,M;
	static ArrayList<Integer>[] con;
	static boolean visited[];
	static int depth[];
	static int parent[][];
	static long answer;
	static int Max;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		con = new ArrayList[N+1];
		for(int i=1;i<=N;i++) con[i] = new ArrayList<>();
		depth = new int [N+1];
		visited = new boolean [N+1];
		parent = new int [Max+1][N+1];
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=M;i++){
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			con[d].add(s);
			con[s].add(d);
		}
		
		dfs(1,1,0);
		for(int k=0;k<Max;k++){
			for(int n=1;n<=N;n++){
				parent[k+1][n] = parent[k][parent[k][n]];
			}
		}
		
		answer = 0;
		int lca = getLca(A,B);
		answer = lca==0? -1 : depth[A]+depth[B]-2*depth[lca];
		System.out.println(answer);
	}

	private static int getLca(int a, int b) {
		if(depth[a] < depth[b]) getLca(b,a);
		int d = depth[a] - depth[b];
		int k=0;
		while(d>0){
			if(d%2==1) a=parent[k][a];
			d/=2;k++;
		}
		if(a==b) return a;
		int result = 0;
		boolean isLinked = false;
		for(int i=Max;i>=0;i--){
			if(parent[i][a]!=parent[i][b]){
				isLinked = true;
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		result = isLinked ? parent[0][a] : 0;
		return result;
	}

	private static void dfs(int cur, int d, int p) {
		// TODO Auto-generated method stub
		visited[cur] = true;
		depth[cur] = d;
		parent[0][cur] =p;
		for(int n : con[cur]){
			if(!visited[n]){
				visited[n] = true;
				dfs(n,d+1,cur);
			}
		}
		
	}

}
*/