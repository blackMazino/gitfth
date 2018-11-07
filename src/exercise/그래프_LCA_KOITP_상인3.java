package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 그래프_LCA_KOITP_상인3{
static int N;
static int[]depth;
static int[][]parent;
static boolean[] visited;
static ArrayList<Integer>[] con;
static long answer;
static int Max = 16;//2^17 - 13만


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
		depth[1] = 1;
		parent[0][1] = 0;
		visited[1] = true;
		dfs(1);
		
		//Sparase table
		for(int k=0;k<=Max-1;k++){
			for(int n=1;n<=N;n++){
				parent[k+1][n] = parent[k][ parent[k][n] ];//n의 2^(k+1)번째 조상 = (n의 k번째 조상의) 2^k번째 조상과 같다
			}
		}		
		
		answer = 0;
		for(int i=1;i<=N-1;i++){
			answer += getMovingDay(i,i+1);
		}
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		
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
/*
//촌수계산방법으로 LCA
//촌수계산으로도 가능한데 sparse table이 없으므로 오래걸린다

	static int N;
	static ArrayList<Integer>[] con;
	static int[] parent, depth;
	static boolean [] isRoot;
	static boolean [] visited;
	static long answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		con = new ArrayList[N+1];
		for(int i=1;i<=N;i++) con[i] = new ArrayList<Integer>();	
		
		depth = new int[N+1];//깊어봐야 N
		parent = new int[N+1];
		visited = new boolean[N+1];
		for(int i=1;i<=N-1;i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			con[s].add(e);
			con[e].add(s);			
		}
		
		
		dfsForLCA(1,1,0);
		
		answer =0;
		for(int i=1;i<N;i++){
			answer += getMovingDay(i,i+1);
		}
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		
		
		
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
	
	//촌수계산방법으로 LCA를 구현
	private static int lca(int a, int b) {
		int result =0;
		if(depth[b]>depth[a]) return lca(b,a);
		boolean [] visitA = new boolean[N+1];
		int tmp = a;
		while(tmp>0){
			visitA[tmp] = true;
			tmp = parent[tmp];
		}
		tmp = b;
		while(tmp>0){
			if(visitA[tmp]){
				result = tmp;
				break;
			}
			tmp = parent[tmp];
		}
		return result;
	}

	private static void dfsForLCA(int cur, int d, int p) {
		visited[cur]=true;
		depth[cur]=d;
		parent[cur]=p;
		for(int n:con[cur]){
			if(!visited[n]){
				visited[n] = false;
				dfsForLCA(n,depth[cur]+1,cur);
			}
		}
		
	}

}
*/