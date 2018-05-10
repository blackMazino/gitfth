package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day8_01_Graph_그래프순회_dfs_bfs {
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
	static int [] visited; //0 : 미방문, 1:방문 boolean으로 정의해도된다	

	//아래 ArrayList선언문이 외우기 어려우면 오른쪽형태를 봐라 :  static int [] con;
	static ArrayList<Integer>[] con;//각 정점vertex(node)에서의 간선edge(link)정보
	static ArrayList<Integer> dfsOut, bfsOut;
	
	
	static boolean [] visit; //0 : 미방문, 1:방문 boolean으로 정의해도된다
	static ArrayList<Integer>[] conn;//각 정점vertex(node)에서의 간선edge(link)정보
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			
			conn = new ArrayList[V+1];
			for(int i=1;i<=V;i++){
				conn[i]= new ArrayList<>();
			}
			for(int i=1;i<=E;i++){
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				conn[s].add(e);
				conn[e].add(s);
			}
			for(int i=1;i<=V;i++){
				Collections.sort(conn[i]);
			}
			visit = new boolean [V+1];
			Arrays.fill(visit, false);
					
			//dfs
			dfsOut = new ArrayList<>();
			dfs2(S);
			for(int i:dfsOut){
				System.out.print(i+" ");
			}
			System.out.println();
			
			
			//bfs
			Arrays.fill(visit, false);//initialize
			bfsOut = new ArrayList<>();
			bfs2(S);
   	        for(int out : bfsOut){
   	        	System.out.print(out+" ");
   	        }
   	        System.out.println();

			/*
			con = new ArrayList[V+1];
			for(int i=1;i<=V;i++){
		          con[i] = new ArrayList<>();
			}
			for(int i=1;i<=E;i++){
		          st = new StringTokenizer(br.readLine());
		          int a = Integer.parseInt(st.nextToken());
		          int b = Integer.parseInt(st.nextToken());
		        //con[시작점].add(끝점);
		          con[a].add(b); 
		          con[b].add(a);
			}
			//각 정점의 인접리스트를 정렬 : 오름차순
   	        for (int i=1;i<=V;i++){
		        Collections.sort(con[i]);
		    }
   	        visited = new int [V+1]; 
   	        dfsOut = new ArrayList<>();   	        
   	        dfs(S);   	        
   	        for(int out : dfsOut){
   	        	System.out.print(out+" ");
   	        }
   	        System.out.println();
   	        
   	        visited = new int [V+1];//초기화
   	        bfsOut = new ArrayList<>();
   	        bfs(S);   	       
   	        for(int out : bfsOut){
   	        	System.out.print(out+" ");
   	        }
   	        System.out.println();
//			System.out.println("#"+tc+"");
//		}
		*/
		br.close();
	}
	
	private static void dfs(int n) {
	      dfsOut.add(n); 
	      visited[n] = 1;//방문
	      for (int t: con[n]){
	    	  if (visited[t] == 0){
		          dfs(t);
		      }
	      }	    	  
	}
	
	private static void bfs(int n) {
	      Queue<Integer> que = new LinkedList<>();
	      que.add(n); 
	      visited[n] = 1;
	      while(!que.isEmpty()){
	    	  int u = que.poll();//Retrieves and removes the head of this queue
	    	  bfsOut.add(u);
	    	  for(int v : con[u]){
	    		  if(visited[v] == 0){
	    			  visited[v] = 1;
	    			  que.add(v);
	    		  }	    		  
	    	  }
	      }	
	}	
	
	public static void dfs2(int n){
		dfsOut.add(n);
		visit[n] = true;
		for(int t: conn[n]){
			if(!visit[t]){
				dfs2(t);
			}
		}		
	}
	
	private static void bfs2(int n) {
	      Queue<Integer> que = new LinkedList<>();
	      que.add(n); 
	      visit[n] = true;;
	      while(!que.isEmpty()){
	    	  int u = que.poll();//Retrieves and removes the head of this queue
	    	  bfsOut.add(u);
	    	  for(int v : conn[u]){
	    		  if(!visit[v]){
	    			  visit[v] = true;
	    			  que.add(v);
	    		  }	    		  
	    	  }
	      }	
	}
}


/*
//그래프 순회
import java.io.*;
import java.util.*;
 
public class source {
  static int N, M, S;
  static ArrayList<Integer>[] con;
  static ArrayList<Integer> dfso, bfso;
  static boolean[] vis;
     
  static void dfs(int n) {
      dfso.add(n); vis[n] = true;
      for (int t: con[n]) if (!vis[t]){
          dfs(t);
      }
  }
 
  static void bfs(int n) {
      Queue<Integer> que = new LinkedList<>();
      que.add(n); vis[n] = true;
      while (!que.isEmpty()){
          int q = que.poll();
          bfso.add(q);
          for (int t: con[q]) if (!vis[t]){
              vis[t] = true;
              que.add(t);
          }
      }
  }
     
  public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      S = Integer.parseInt(st.nextToken());
      con = new ArrayList[N+1];
      for (int i=1;i<=N;i++)
          con[i] = new ArrayList<>();
      for (int i=1;i<=M;i++){
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          con[a].add(b); con[b].add(a);
      }
      for (int i=1;i<=N;i++)
          Collections.sort(con[i]);
      vis = new boolean[N+1];
      dfso = new ArrayList<>();
      dfs(S);
      vis = new boolean[N+1];
      bfso = new ArrayList<>();
      bfs(S);
      for (int t: dfso) System.out.print(t + " ");
      System.out.println();
      for (int t: bfso) System.out.print(t + " ");
      System.out.println();
  }
}
*/