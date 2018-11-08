package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Previoust51_LCA_조상이키컸으면 {

/*
Single Parent Species(SPS)라는 가상의 인류는 남녀 성별이 없어서, 자식을 낳을 때 부부가 결혼할 필요가 없다. 
이 인류의 가족 트리는 결혼 관계가 없으므로 우리 인류의 가족 트리 보다 훨씬 간단한 모양이 된다. 즉, 보통의 루트 있는 트리와 같은 모양이다.
 
어떤 가족 트리가 주어져 있다고 하자. 트리에는 N명의 구성원이 등장한다. 
구성원들은 1번부터 N번까지 번호가 붙어 있으며 가족 트리의 최고 조상은 항상 1번 구성원이다. 
트리에 등장하는 모든 구성원의 키가 얼마인지 (이미 죽었다면 얼마였는지) 모두 기록이 되어 있다고 한다. 
구성원 중 K 명이 모여서 그들의 공통 조상들 중 가장 키가 컸던 구성원을 찾고 싶다. 공통 조상이라는 것은 모든 K명의 조상이 될 수 있는 구성원을 말한다. 
한가지 주의할 점은 이 문제에서는 자기 자신도 자신의 조상으로 간주한다는 것이다.
 
이들을 도와 공통 조상들 중 가장 키가 컸던 구성원의 키를 출력하는 프로그램을 작성하라.
 
[입력]
입력 파일의 제일 첫째 줄에는 파일에 포함된 케이스의 수 T가 주어진다. 
케이스의 첫 줄에 트리의 구성원 수 N(1 ≤ N ≤10,000)과 조상의 키를 알아보고 싶은 구성원 모임의 개수 Q(1 ≤ Q ≤10,000)가 주어진다.
다음 N개 줄에, 1번 구성원부터 순서대로, 해당 구성원의 부모가 되는 구성원의 번호와 해당 구성원의 키가 주어진다. 
1번 구성원은 부모가 없으므로 부모의 번호가 0으로 주어진다. 키의 값은 1 이상 1,000,000이하의 정수이다. 
다음 Q개의 줄에 각각 구성원들의 모임이 주어진다. 
첫 수는 모임에 속한 구성원의 개수 K(1 ≤ K ≤ 100)이며, 두번째부터 K+1번째 수는 구성원의 번호이다. 한 줄에 주어지는 구성원의 번호에는 중복이 없다.
 
[출력]
각 테스트 케이스의 답을 순서대로 표준출력으로 출력하며, 각 케이스마다 줄의 시작에 “#x”를 출력하여야 한다. 
이때 x는 케이스의 번호이다. 같은 줄에 Q개의 자연수를 출력하여야 한다. 입력에 주어진 순서대로 주어진 모임을 위한 답을 출력해야 한다.
 
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
	static int[] height;
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
			height = new int[N+1];
			con = new ArrayList[N+1];
			depth = new int[N+1];
			parent = new int [Max+1][N+1];
			visited = new boolean [N+1];
			for(int n=0;n<=N;n++) con[n] = new ArrayList<Integer>();
			for(int n=1;n<=N;n++){
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int h  = Integer.parseInt(st.nextToken());				
				con[n].add(p);
				con[p].add(n);				
				parent[0][n] = p;
				height[n] = h;
			}
			depth[1]=1;
			parent[0][1] = 0;
			visited[1] = true;
			dfs(1);
			for(int k=0;k<=Max-1;k++){
				for(int n=1;n<=N;n++){
					parent[k+1][n] = parent[k][parent[k][n]]; 
				}				
			}			
			qList = new ArrayList<int[]>();
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				int [] t = new int[K];
				for(int k=0;k<K;k++){
					t[k] = Integer.parseInt(st.nextToken());
				}
				qList.add(t);
			}
			bw.write("#"+tc);			
			for(int q=0;q<Q;q++){
				bw.write(" ");
				bw.write(String.valueOf(getMaxHeight(q)));
			}
			bw.write("\n");
			bw.flush();
//			System.out.println("#"+tc+str);
		}
	}
	private static int getMaxHeight(int q) {
		int lca = 0;
//		if(qList.get(q).length==1){
//			lca = qList.get(q)[0];
//		}else{
			lca = getLca(q);
//		}		
		return getMaxValue(lca);
	}
	
	private static int getLca(int q) {
		int result = qList.get(q)[0];
		int[] t = qList.get(q);
		for(int i=0;i<t.length-1;i++){
			result = lca(result,t[i+1]);
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
	private static int getMaxValue(int lca) {
		int result = height[lca];
		for(int i=0;i<=Max;i++){
			if(parent[i][lca]==0){
				break;
			}
			result = Math.max(result, height[parent[i][lca]]);
		}
		return result;
	}
	
	private static void dfs(int i) {
		for(int n : con[i]){
			if(!visited[n]){
				visited[n] = true;
				depth[n] = depth[i]+1;
				parent[0][n] = i;
				dfs(n);
			}
		}
		
	}

}
