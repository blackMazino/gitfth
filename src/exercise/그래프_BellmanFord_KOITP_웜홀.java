package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
https://koitp.org/problem/USACO_2016DEC_WORMHOLES/read/
그의 많은 농장들을 탐험하던중, 존은 몇 개의 놀라운 웜홀을 발견했다. 
웜홀은 현재 농장에서 다른 농장으로의 이상한 단방향 통로로써 당신이 들어갔던 시간보다 이전시간으로 시간을 되돌린다. 
존의 농장은 N개의 농장과 M개의 양방향 도로, W개의 웜홀로 구성되어있다. 
그리고 각 농장은 편의상 농장1, 농장2, … ,농장 N이라고 이름붙여주자.

존은 갑자기 현재위치에서 출발해서 여행을 하다 다시 현재위치로 돌아왔을 때 시간이 되돌아 가 있는 경우가 있는지 궁금해졌다.
존을 도와 시간을 되돌리는 여행이 가능한지 구하는 프로그램을 작성하여라.

첫 번째 줄에 테스트케이스의 개수 T가 주어진다. (1 ≤ T ≤ 5)
각 테스트 케이스의 첫 번째 줄에 농장의 수 N, 도로의 수 M, 웜홀의 수 W가 주어진다. 
(1 ≤ N ≤ 500, 1 ≤ M ≤ 2,500, 1 ≤ W ≤ 200)

각 테스트 케이스의 두 번째 줄부터 M개의 줄에 걸쳐 양방향 도로의 정보 s, e, t가 공백으로 분리되어 주어진다. 
s, e는 해당 도로가 잇는 지점의 번호, t는 해당 도로를 통해 이동하는데 걸리는 시간을 의미한다. 
(1 ≤ s, e ≤ N, 1 ≤ t ≤ 10,000)


각 테스트 케이스의 (M + 2) 번째 줄부터 W개의 줄에 걸쳐 웜홀의 정보 s, e, t가 공백으로 분리되어 주어진다. 
s는 해당 웜홀의 시작지점, e는 해당 웜홀의 도착 지점, t는 줄어드는 시간을 의미한다. 
(1 ≤ s, e ≤ N, 1 ≤ t ≤ 10,000)

두 지점을 연결하는 도로가 한 개보다 많을 수도 있다.

2
3 3 1

1 2 2
1 3 4
2 3 1

3 1 3


3 2 1

1 2 3
2 3 4

3 1 8

NO
YES
*/
class Edge{
	int s,e,t;
	public Edge(int s, int e, int t) {
		super();
		this.s = s;
		this.e = e;
		this.t = t;
	}	
}

public class 그래프_BellmanFord_KOITP_웜홀 {

	static int T,N,M,W;
	static ArrayList<Edge> edge;
	static int dist[];
	static boolean isUpdate;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			edge = new ArrayList<>();
			//road
			for(int i=1;i<=M;i++){
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				edge.add(new Edge(s,e,t));
				edge.add(new Edge(e,s,t));				
			}
			//WormHole
			for(int i=1;i<=W;i++){
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				edge.add(new Edge(s,e,-t));
			}
			
			dist = new int[N+1];
			isUpdate = false;//초기화
			for(int i=1;i<=N;i++){
/*				
				isUpdate = false;
				for(Edge e : edge){
					if(dist[e.e]>dist[e.s]+e.t){//웜홀(음수)인경우 성립된다
						dist[e.e]=dist[e.s]+e.t;
						isUpdate = true;
					}
				}
				if(!isUpdate) break; //갱신할게 없다면 또는 모두 갱신했다면
*/				
				
				for(Edge e : edge){
					if(dist[e.e]>dist[e.s]+e.t){//웜홀(음수)인경우 성립된다
						dist[e.e]=dist[e.s]+e.t;
						if(i==N){
							isUpdate = true;
							break;
						}
					}
				}								
			}
			System.out.println(isUpdate? "YES":"NO");
		}
	}

}
