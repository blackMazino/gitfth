package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
https://koitp.org/problem/SHORTEST_PATH/read/

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

//ArrayList와 Priority Queue를 이용
public class 그래프_DIJKSTRA_KOITP_01최단경로 {

	static int N,M;
	static long d[];
	static ArrayList<Integer> con[];
	static ArrayList<Integer> conW[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		con = new ArrayList[N+1]; conW = new ArrayList[N+1]; 
		d= new long[N+1];
		for(int i =1;i<=N;i++ ){
			con[i] = new ArrayList<>();
			conW[i] = new ArrayList<>();
			d[i] = Long.MAX_VALUE;
		}
		
		for(int i=1;i<=M;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			con[a].add(b); con[b].add(a);
			conW[a].add(c); conW[b].add(c);
		}
		
		dijkstraByPQ();
		
		System.out.println(d[N] < Long.MAX_VALUE ? d[N] : -1);
	}

	private static void dijkstraByPQ() {
		d[1] = 0;
		PriorityQueue<Edge2> pQ = new PriorityQueue<>();
		pQ.add(new Edge2(d[1],1));
		
		while(!pQ.isEmpty()){
			Edge2 e = pQ.poll();
			int v = e.vertex;
			long distance = e.distance;
			
			if( d[v] < distance) continue;
			
			for(int i=0;i<con[v].size();i++){
				int thisV = con[v].get(i);
				int thisD = conW[v].get(i);
				if(d[thisV] > d[v]+thisD){
					d[thisV] = d[v] + thisD;
					pQ.add(new Edge2(d[thisV], thisV));
				}
			}
		}
	}

}
//class Edge2 implements Comparator<Edge2>{//ClasscastException Occur
class Edge2 implements Comparable<Edge2>{
	public Edge2(long distance, int vertex) {
		this.distance = distance;
		this.vertex = vertex;
	}
	long distance;
	int vertex;
//	@Override
//	public int compare(Edge2 o1, Edge2 o2) {		
//		return (int)(o1.distance-o2.distance);//오름차순
//	}
	@Override
	public int compareTo(Edge2 o) {
		//return (int)(distance - o.distance);
		return Long.compare(distance,o.distance);//오름
	}
}

/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class source {
	private static int N, M;
	private static ArrayList<Edge>[] adj;

	public static void main(String[] args) throws Exception {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			adj = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());

				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				adj[start].add(new Edge(end, cost));
				adj[end].add(new Edge(start, cost));
			}

			System.out.println(solve());
		}
	}

	public static long solve() {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		long[] dist = new long[N + 1];
		Arrays.fill(dist, Long.MAX_VALUE);

		queue.add(new Edge(1, 0));
		dist[1] = 0;

		while (!queue.isEmpty()) {
			Edge cur = queue.poll();

			if (dist[cur.vertex] < cur.cost)
				continue;

			for (Edge next : adj[cur.vertex]) {
				if (dist[next.vertex] > cur.cost + next.cost) {
					dist[next.vertex] = cur.cost + next.cost;
					queue.add(new Edge(next.vertex, dist[next.vertex]));
				}
			}
		}

		return dist[N] < Long.MAX_VALUE ? dist[N] : -1;
	}

	static class Edge implements Comparable<Edge> {
		int vertex;
		long cost;

		public Edge(int vertex, long cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.cost < o.cost) {
				return -1;
			} else if (this.cost == o.cost) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
*/