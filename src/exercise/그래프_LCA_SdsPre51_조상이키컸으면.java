package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 그래프_LCA_SdsPre51_조상이키컸으면 {

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

sampleinput
#1 9 8 8
#2 2 6 7 9 2
#3 30 30 66 30 66
#4 74 85 82 82 92 92 60 82 82 47 74 89 37 99 82
#5 608 560 560 315 795 359 691 691 608 690 691 320 795 570 630
#6 712 712 712 712 920 920 457 712 987 920 828 920 495 712 826 683 979 826 758 929
#7 961 555 809 718 946 945 643 485 485 485 485 485 946 906 485 718 945 958 718 535
#8 9997 9703 7812 9997 9997 9577 8891 4395 9997 9030 9997 9997 9997 9997 8416 8416 7748 7748 5352 9997 9703 9703 4931 5352 9354 7785 3539 7141 6566 9997 7748 9785 7756 9997 9997 3680 9997 7812 9764 9307 8242 9997 7756 7785 9713 3606 9997 9292 9997 9808
#9 23768 30194 32477 31925 30247 30675 29826 22934 30247 29276 30247 29719 30812 24292 3184 22934 18632 31137 32662 31137 31137 31126 31392 30247 24800 30247 29276 21236 31925 27596 29276 30245 30247 30247 30247 31797 29859 31137 31925 25734 30247 31925 27260 32426 27260 21236 22934 30247 21236 31925

*/
	static int TC, N, Q, K;
	static int[] H;
	static int[] answer;
//	static ArrayList<int[]> qList;
	//LCA
	static ArrayList<Integer> con[];
	static int depth[];
	static int parent[][];
	static boolean visited[];
	static int Max = 16;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous51.txt"));
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
				//parent[0][n] = p;
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
