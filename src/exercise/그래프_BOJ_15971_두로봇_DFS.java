package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 그래프_BOJ_15971_두로봇_DFS {
//dfs로 풀기
	
/*https://www.acmicpc.net/problem/15971
5 1 5
1 2 1
2 3 2
3 4 3
4 5 4
=====
6

9 1 9
1 2 8
2 3 6
2 4 5
2 5 10
9 5 6
6 5 14
6 7 7
8 6 7
=====
14
*/
	static List<List<Cave>> graph;
	static int N, robot1, robot2;
	static long  answer, MaxEVal;
	static boolean visited[], isEnd;
	static LinkedList<Integer> path;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		robot1= Integer.parseInt(st.nextToken());
		robot2= Integer.parseInt(st.nextToken());
		graph =new ArrayList<>();
		for(int i=0;i<=N;i++){
			graph.add(new ArrayList<>());
		}
		for(int n=1;n<N;n++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Cave(e,w));
			graph.get(e).add(new Cave(s,w));
		}
		
		//Dfs
		visited  = new boolean[N+1];
		MaxEVal = 0;
		answer = 0;
		path = new LinkedList<>();
		dfs(robot1, 0);

		
		answer -= MaxEVal;
		System.out.println(answer);
	}
	private static void dfs(int v, long d) {
		if(v == robot2){
			answer = d;
			while(!path.isEmpty()){
				long curr = path.removeLast();
				MaxEVal = Math.max(curr, MaxEVal);
			}			
			isEnd = true;
			return;
		}
		
		if(isEnd) return;
		
		visited[v] = true;
		for(Cave ca : graph.get(v)){
			
			if(isEnd) break;
			
			if(visited[ca.v]) continue;
			
			path.addLast((int)ca.d);
			dfs(ca.v, d+ca.d );
			if(!path.isEmpty()) path.removeLast();
		}
	}

}
class Cave {
	public Cave(int v,long d ) {
		super();
		this.d = d;
		this.v = v;
	}
	long d;
	int v;

}

 