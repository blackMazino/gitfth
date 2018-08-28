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

public class 그래프_BFSDFS_KOITP_그래프순회 {

	static int V,E,S;
	static boolean [] visited;
	static ArrayList<Integer> [] con;
	static ArrayList<Integer> dfsOut,bfsOut;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		con = new ArrayList[V+1];
		for(int i=1;i<=V;i++){
			con[i] = new ArrayList<>();//Initialize
		}
		for(int i=1;i<=V;i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			con[s].add(e);
			con[e].add(s);			
		}
		
		for(int i=1;i<=V;i++){
			Collections.sort(con[i]);			
		}
		
		visited = new boolean[V+1];
		Arrays.fill(visited, false);
		dfsOut = new ArrayList<>();
		bfsOut = new ArrayList<>();
		
		dfs(S);
		for(int out : dfsOut){
//			System.out.print(out+" ");
			bw.write(out+" ");
		}
//		System.out.println("");
		bw.write("\n");
		
		Arrays.fill(visited, false);
		bfs(S);
		for(int out : bfsOut){
//			System.out.print(out+" ");
			bw.write(out+" ");
		}
//		System.out.println("");
		bw.write("\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int n) {
		dfsOut.add(n);
		visited[n] = true;
		for(int t : con[n]){
			if(!visited[t]){
				dfs(t);
			}
		}				
	}
	private static void bfs(int n) {
		Queue<Integer> que = new LinkedList<>();
		que.add(n);
		visited[n] = true;
		while(!que.isEmpty()){
			//que.removeFirst()
			int u = que.poll();//Retrieves and removes the head of this queue, or returns null if this queue is empty.
			bfsOut.add(u);
			for(int v : con[u]){
				if(!visited[v]){
					visited[v] = true;
					//que.addLast();
					que.add(v);
				}
			}
		}
		
	}

}
