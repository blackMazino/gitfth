package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그래프_DAG_위상정렬 {

/*
DAG(Directed Acyclic Graph)는 방항셩이 있는 간선으로 이루어진 그래프 중, 사이클이 없는 그래프를 의미한다. DAG에서는 위상 정렬을 항상 할 수 있다.

DAG가 주어질 때, 위상 정렬을 하는 프로그램을 작성하시오.

입력
첫 번째 줄에 그래프의 정점의 개수 V, 간선의 개수E가 공백으로 분리되어 주어진다.
(1≤V≤50,000,1≤E≤100,000)
두 번째 줄부터 E개의 줄에 걸쳐 각 간선의 정보인x,y가 공백으로 분리되어 주어진다. 
이는x에서 출발하여y에 도착하는 유항 간선이 존재한다는 것을 의미한다.
(1≤x,y≤V,x≠y)주어지는 그래프는 항상 DAG이고,1번으로 들어오는 간선은 없다.

출력
첫 번째 줄에 위상 정렬의 결과를 출력한다. 답이 여러 가지인 경우, 그 중 아무 것이나 출력한다.

4 4
1 2
1 3
2 4
3 4

1 2 3 4 
or
1 3 2 4
*/	
	static BufferedWriter bw;
	static int V,E;
	static ArrayList<Integer>[] con;
	static int [] indegreeArr;//DEFAULT 0
	
	static LinkedList<Integer>stack;		
	static Queue<Integer>queue;
	static boolean [] visited;
	static ArrayList<Integer>dfsOut;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());	
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		indegreeArr = new int [V+1];
		con = new ArrayList[V+1];		
		for(int i=1;i<=V;i++){
			con[i] = new ArrayList<>(); 
		}
		for(int i=1;i<=E;i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			con[s].add(e); 			
			indegreeArr[e]++;//호출이 되어진다면 cnt++
		}
		
		//1) stack을 이용한 풀이
//		topologicalSortBySTACK();

		//2) Queue를 이용한 풀이
//		topologicalSortByQUEUE();
		
		
		//3) DFS를 이용한 풀이
		topologicalSortByDFS();
		

	}	

	private static void topologicalSortBySTACK() throws Exception {
		stack = new LinkedList<>();
		
		//호출건수가 0인건 우선 stack 담는다
		for(int i=1;i<=V;i++){
			if(indegreeArr[i]==0){
				stack.addLast(i);
			}
		}
		
		//stack이 담겨진 정보를 토대로, con의 있는 정보를 하나하나 꺼낸다
		while(!stack.isEmpty()){
			int s= stack.removeLast();
			bw.write(s+" ");
			for(int e:con[s]){
				indegreeArr[e]--;
				if(indegreeArr[e]==0) stack.addLast(e);
			}
		}
		bw.flush();
		bw.close();	
	}	
	
	private static void topologicalSortByQUEUE() throws Exception {
		queue = new LinkedList<>();
		
		//호출건수가 0인건 우선 queue 담는다
		for(int i=1;i<=V;i++){
			if(indegreeArr[i]==0){
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()){
			int s = queue.poll();
			bw.write(s+" ");
			for(int e:con[s]){
				indegreeArr[e]--;
				if(indegreeArr[e] ==0) queue.offer(e);
			}
		}
		bw.flush();
		bw.close();			
		
	}
	
/*
dfs(p) :
    p를 방문했다고 기록
    p->q인 모든 q에 대해: q를 방문한 적이 없다면 dfs(q);
    p를 위상 정렬에 추가

topological_sort():
    방문한 적이 없는 모든 p에 대해: dfs(p);
    만들어진 위상정렬을 뒤집는다
*/
	private static void topologicalSortByDFS() throws Exception{
		visited = new boolean[V+1];
		dfsOut = new ArrayList<>();
		dfs(1);
		Collections.reverse(dfsOut);
		for(int i:dfsOut){
			bw.write(i+" ");
		}
		bw.flush();
		bw.close();
	}

	private static void dfs(int i) {		
		visited[i] = true;
		for(int next : con[i]){
			if(!visited[next]){
				dfs(next);
			}
		}
		dfsOut.add(i);
	}
}
