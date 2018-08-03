package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그래프_BFSDFS_그래프순회2 {
/*
문제
그래프에서 탐색을 하는 방법에는 여러 가지가 존재한다. 깊이 우선 탐색(DFS; Depth First Search)과 너비 우선 탐색(BFS; Breadth First Search)가 대표적인 탐색 방법이다.
깊이 우선 탐색과 너비 우선 탐색을 하는 프로그램을 작성하시오.
이 문제에서 너비 우선 탐색이란 큐를 사용하여 한 번에 하나의 정점만 탐색을 하는 형태만을 생각한다. 또한 주어지는 그래프는 시작점을 포함하는 하나의 연결그래프(connected graph)이다.

입력
첫 번째 줄에 그래프의 정점의 개수 V, 간선의 개수 E, 그리고 시작 정점의 번호 S가 공백으로 분리되어 주어진다. (1 ≤ S ≤ V ≤ 20,000, 1 ≤ E ≤ 100,000)

두 번째 줄부터 E개의 줄에 걸쳐 각 간선의 정보인 x, y가 공백으로 분리되어 주어진다. 이는 x와 y를 잇는 간선이 존재한다는 것을 의미한다. (1 ≤ x, y ≤ V, x ≠ y)

출력
첫 번째 줄에 정점 S에서 시작한 깊이 우선 탐색의 결과 중 오름차순으로 가장 빠른 것을 출력한다.

두 번째 줄에 정점 S에서 시작한 너비 우선 탐색의 결과 중 오름차순으로 가장 빠른 것을 출력한다.

5 6 2
1 2
1 3
2 4
3 4
3 5
4 5

2 1 3 4 5
2 1 4 3 5

5 4 1
1 2
1 3
2 5
3 4

1 2 5 3 4
1 2 3 5 4

*/
	
	
	static int V,E,S;
	static boolean visited[];
	static ArrayList<Integer>[] con;
	static ArrayList<Integer> dfsOut, bfsOut;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		visited = new boolean [V+1];//default = false
		con = new ArrayList[V+1];
		for(int i=1;i<=V;i++) con[i] = new ArrayList<>(); 
		
		for(int i=1;i<=E;i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			con[s].add(e);
			con[e].add(s);
		}
		for(int i=1;i<=V;i++) Collections.sort(con[i]);
		
		dfsOut = new ArrayList<>();
		dfs(S);
		bw.write("dfs result"+"\n");
		for(int dfs:dfsOut) bw.write(dfs+" ");
		bw.write("\n");
		
		Arrays.fill(visited, false);//initialize for bfs
				
		bfsOut = new ArrayList<>();
		bfs(S);
		bw.write("bfs result"+"\n");
		for(int bfs:bfsOut) bw.write(bfs+" ");
		bw.write("\n");
		bw.flush();
		bw.close();
	}

	private static void bfs(int n) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(n); visited[n] = true;
		while(!que.isEmpty()){
			int u = que.poll();
			bfsOut.add(u);
			for(int t : con[u]){
				if(!visited[t]){
					visited[t] = true; 
					que.add(t);
				}
			}
		}
		
		
	}

	private static void dfs(int n) {
		dfsOut.add(n); 
		visited[n]=true;
		for(int t:con[n]){
			if(!visited[t]) dfs(t); 
		}		
	}

}
