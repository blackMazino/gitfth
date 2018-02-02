package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Day8_02_Graph_위상정렬_topologicalSort {

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
	static int V,E;
	static ArrayList<Integer>[] con;
	static LinkedList<Integer> stk;//스택을 사용
	static int [] indegreeArr;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			con = new ArrayList[V+1];
			for(int i=1;i<=V;i++){
				con[i] = new ArrayList<>(); 
			}
			stk = new LinkedList<>();
			indegreeArr = new int [V+1];
			for(int i=1;i<=E;i++){
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				con[s].add(e); 			
				indegreeArr[e]++;//호출이 되어진다면 cnt++
			}
			
			for(int i=1;i<=V;i++){
				if(indegreeArr[i]==0){//0건인건 호출하는 주체(각 tree별 최상위부모)는 스택에 넣자
					stk.addLast(i);
				}				
			}
			while(!stk.isEmpty()){
				int s = stk.removeLast();//스택에서 하나빼서
				System.out.print(s+" ");//보여주고
				for(int t:con[s]){//얘를 없앴으니 얘를 바로위부모로 하는 애들을 찾아서 -1해준다
					indegreeArr[t]--;
					if(indegreeArr[t] == 0){//근데 자식이 0이면? 스택에 담는다
						stk.addLast(t);
					}
				}
			}
			//stk이 비었는데 건수가 모자란다? -> cycle존재. 위상정렬불가
			
			
			//DFS를 사용한 풀이는?
			
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
}

/*
//위상 정렬
import java.io.*;
import java.util.*;

public class source {
  static int N, M;
  static int[] in;
  static ArrayList<Integer>[] con;
    
  public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      con = new ArrayList[N+1];
      for (int i=1;i<=N;i++)
          con[i] = new ArrayList<>();
      in = new int[N+1];
      for (int i=1;i<=M;i++){
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          con[a].add(b);
          in[b]++;
      }
      Queue<Integer> que = new LinkedList<>();
      for (int i=1;i<=N;i++) if (in[i] == 0) que.add(i);
      while (!que.isEmpty()){
          int q = que.poll();
          System.out.print(q + " ");
          for (int t: con[q]){
              if (--in[t] == 0) que.add(t);
          }
      }
      System.out.println();
  }
}
*/