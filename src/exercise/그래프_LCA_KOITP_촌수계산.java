package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//상인 방법 LCA
public class 그래프_LCA_KOITP_촌수계산 {
	static int N,A,B,M;
	static int Max = 16;
	static ArrayList<Integer>[] con;
	static int[] depth;
	static int[][] parent;//sparse table
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
		parent = new int [Max+1][N+1];
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
				visited[i] = true;
				dfs(i);
			}
		}
		for(int k=0;k<Max;k++){
			for(int n=1;n<=N;n++){
				parent[k+1][n] = parent[k][ parent[k][n] ];//n의 2^(k+1)번째 조상 = (n의 k번째 조상의) 2^k번째 조상과 같다
			}
		}		
		int c = getLca(A,B);
		System.out.println(c==0?-1:depth[A]+depth[B]-2*depth[c]);
	}
	
	private static int getLca(int a, int b) {
		if(depth[a] < depth[b]) return getLca(b,a);
		int d = depth[a] - depth[b];
		int k=0;
		while(d>0){
			if(d%2==1) a=parent[k][a];
			d/=2;k++;
		}
		if(a==b) return a;
		for(int i=Max;i>=0;i--){
			if(parent[i][a]!=parent[i][b]){			
				a = parent[i][a];
				b = parent[i][b];
			}
		}
//		return parent[0][a] == parent[0][b] ? parent[0][a]:0;
		return parent[0][a];//a,b가 같은 tree가 아니라면 어차피 0이 return
	}
	
	private static void dfs(int i) {	
		for(int n:con[i]){
			if(!visited[n]){
				visited[n] = true;
				parent[0][n] = i;
				depth[n] = depth[i]+1;
				dfs(n);
			}
		}
		
	}
}
