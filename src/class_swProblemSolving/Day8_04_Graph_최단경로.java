package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//DAG X(cycle이 있을 수 있다), 음수가 있을수 있음 -> Bellman-Ford
//DAG X(cycle이 있을 수 있다), 음수가 없음 -> Dijkstra
//DAG X(cycle이 있을 수 있다), 음수가 없음, 모든가중치가 동일 -> BFS
//
public class Day8_04_Graph_최단경로 {

/*
N 개의 도시가 있다. 각 도시는 1번부터N번까지 번호가 매겨져있다. 
그리고 서로 다른 두 도시를 잇는 M개의 도로가 있다. 도로마다 길이가 다를 수 있으며, 
어떠한 두 도시는 여러 개의 도로로 이어져 있을 수 있다.

태양이의 집은 1번 도시에 있고, 명우의 집은 N번 도시에 있다. 
태양이는 자신의 집에서 명우의 집으로 놀러가려고 한다. 
단, 조금이라도 빨리 명우를 보고 싶어하는 마음에 최대한 빠르게 이동하려고 한다.

태양이를 도와 1번 도시에서 N번 도시로 가는 최단 거리를 구하는 프로그램을 작성하시오.

입력
입력의 첫 줄에 도시의 수를 나타내는 자연수 N과 도로의 개수를 나타내는 정수M
이 공백으로 구분되어 주어진다. (1≤N≤100,000, 0≤M≤300,000)

그 다음 M개의 줄에 각 도로를 나타내는 세 개의 자연수 a,b,c가 공백으로 구분되어 주어진다.
이는a번 도시와b번 도시를 연결하는 길이c인 도로를 의미한다. 
(1≤a,b≤N,a≠b,1≤c≤106)
출력
1번 도시에서N번 도시로 주어진 도로를 통해 이동할 때 최단 거리를 출력한다. 
만약 이동할 수 없으면 −1을 출력한다.

6 9
1 2 2
1 3 4
2 3 1
2 4 4
2 5 2
3 5 3
4 5 1
4 6 2
5 6 4

7

3 2
1 2 1
1 2 2

-1
*/	
	static int V,E;
	static ArrayList<Integer>[] con;
	static ArrayList<Integer>[] conW;
	static long [] D;
	
	static PriorityQueue<E> q;
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
			conW = new ArrayList[V+1];
			for(int i=1;i<=V;i++){
				con[i] = new ArrayList<>();
				conW[i] = new ArrayList<>();
			}
//			for(int i=1;i<=E;i++){
//				st = new StringTokenizer(br.readLine());
//				int s = Integer.parseInt(st.nextToken()); 
//				int e = Integer.parseInt(st.nextToken());
//				int w = Integer.parseInt(st.nextToken());
//				con[s].add(e);
//				conW[s].add(w);
//			}
//			System.out.println(Integer.MIN_VALUE);
//			D = new long[V+1];
//			for(int i=1;i<=V;V++){
//				if(i==1) D[i] = 0;
//				else D[i] = Long.MAX_VALUE;
//			}
//			
//			for(int i=1;i<V;i++){
//				if(D[i]<Long.MAX_VALUE){
//					for(int j=0;j<con[i].size();j++){
//						int vi = con[i].get(j);
//						int wi = conW[i].get(j);
//						D[vi] = Math.max(D[vi],D[i]+wi);						
//					}					
//				}				
//			}
//			
//			if(D[V] == Long.MAX_VALUE) System.out.println(-1);
//			else System.out.println(D[V]);
			
			//Dijkstra 풀이
			
			//1.V^2 array
			
			//2.Heap 사용-PriorityQueue 사용??
			q = new PriorityQueue<E>();
			D = new long[V+1];
			Arrays.fill(D, Long.MAX_VALUE);
			D[1] = 0;
			for(int i=1;i<=E;i++){
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken()); 
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				con[s].add(w);
				conW[s].add(w);
			}
			q.offer(new E(1,0));
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
}

class E implements Comparable<E>{
	int s;
	int e;
//	int distance;
	
	public E(int s,int e
//			,int distance
			){
		this.s = s;
		this.e = e;
//		this.distance = distance;		
	}
	@Override
	public int compareTo(E o) {		
		return e-o.e;
	}
	
}

/*

//
흠 테스트케이스 1개틀리다고 나옴
아래는 원본
import java.io.BufferedReader;	
import java.io.InputStreamReader;	
import java.util.ArrayList;	
import java.util.Comparator;	
import java.util.PriorityQueue;	
import java.util.StringTokenizer;	
	
public class source{	
	static int T,N,M;	
	static ArrayList<Integer>[] con,conv;	
	static long[] D;	
		
	public static void main(String args[]) throws Exception {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));	
			
		T=Integer.parseInt(br.readLine());	
			
		for(int tc=1;tc<=T;tc++) {	
			StringTokenizer st=new StringTokenizer(br.readLine());	
				
			N=Integer.parseInt(st.nextToken());	
			M=Integer.parseInt(st.nextToken());	
				
			D=new int[N+1];	
			con=new ArrayList[N+1];	
			conv=new ArrayList[N+1];	
				
			for(int i=1;i<=N;i++) {	
				con[i]=new ArrayList<>();	
				conv[i]=new ArrayList<>();	
			}	
				
			for(int i=1;i<=M;i++) {	
				st=new StringTokenizer(br.readLine());	
					
				int a=Integer.parseInt(st.nextToken());	
				int b=Integer.parseInt(st.nextToken());	
				int c=Integer.parseInt(st.nextToken());	
					
				con[a].add(b); con[b].add(a);	
				conv[a].add(c); conv[b].add(c);	
			}	
				
			for(int i=1;i<=N;i++) D[i]=long.MAX_VALUE;	
				
			PriorityQueue<int[]> que=new PriorityQueue<>(10,new Comparator<int[]>() {	
				public int compare(int[] a,int[] b) {	
					return a[1]-b[1];	
				}	
			});	
				
			D[1]=0;	
				
			que.add(new int[]{1,0});	
				
			while(!que.isEmpty()) {	
				int q=que.peek()[0];	
				int d=que.peek()[1];	
					
				que.poll();	
					
				if(D[q]!=d) continue;	
	
				for(int i=0;i<con[q].size();i++) {	
					int t=con[q].get(i);	
					int v=conv[q].get(i);	
						
					if (D[t]>D[q]+v) {	
						D[t]=D[q]+v;	
						que.add(new int[]{t,D[t]});	
					}	
				}	
			}	
				
			System.out.println("#"+tc+" "+(D[N]<Integer.MAX_VALUE? D[N]:-1));	
	
		}	
	}	
}
*/