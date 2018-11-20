package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 그래프_LCA_SdsPre51_LCA_조상이키컸으면2 {

/*

[입출력 예]
(입력)
1                                              ← 1 test case in total
10 3                                              ← 1st case
0 2
3 9
1 7
1 3
4 8
4 10
8 8
1 6
8 4
2 9
3 6 4 5
2 9 8
1 6
 
(출력)
#1 3 6 10
*/
	static int TC, N, Q, K;
	static int[] H;
	static int[] answer;
	static ArrayList<int[]> qList;
	//LCA
	static ArrayList<Integer> con[];
	static int depth[];
	static int parent[][];
	static boolean visited[];
	static int Max = 16;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previoust51_Input.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			H = new int[N+1];
			
			depth = new int[N+1];
			parent = new int [Max+1][N+1];
			visited = new boolean [N+1];
			con = new ArrayList[N+1];
			for(int n=0;n<=N;n++) con[n] = new ArrayList<>();
			
			for(int n=1;n<=N;n++){
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				con[n].add(p);
				con[p].add(n);
				H[n] = h;
			}
			
			depth[1] = 1;
			parent[0][1] = 0;
			visited[1] = true;
			dfs(1);
			//sparse Table
			for(int k=0;k<=Max-1;k++){
				for(int n=1;n<=N;n++){
					parent[k+1][n] = parent[k][parent[k][n]];
				}
			}
			
			bw.write("#"+tc);
			
			int [] t;
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				t = new int[K];
				for(int k=0;k<=K-1;k++){
					t[k] = Integer.parseInt(st.nextToken());
				}
				bw.write(" ");
				bw.write(String.valueOf(getTallestVal(t)));	
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();		
	}
	
	private static int getTallestVal(int[] t) {
		int result = 0;
		//Lca
		int lca = t[0];
		for(int k=0;k<t.length-1;k++){
//			System.out.println(k+" : "+lca+","+t[k+1]);
			lca = getLca(lca, t[k+1]);
		}
		result = H[lca];
		while(true){
			result = Math.max(result, H[parent[0][lca]]);
			lca = parent[0][lca];
			if(lca == 0) break;
		}
		
		return result;
	}
	
	private static int getLca(int a, int b) {
		if(depth[a]<depth[b]) return getLca(b,a);
		int d = depth[a] - depth[b];
		int k=0;
		while(d>0){
			if(d%2==1) a = parent[k][a];
			d/=2;
			k++;
		}
		
		if(a==b) return a;
		for(int i=Max;i>=0;i--){
			if(parent[i][a]!=parent[i][b]){
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		return parent[0][a];
	}
	
	private static void dfs(int i) {
		for(int n:con[i]){
			if(!visited[n]){
				visited[n]=true;
				depth[n] = depth[i] +1;
				parent[0][n] = i;
				dfs(n);						
			}
		}
		
	}

}
