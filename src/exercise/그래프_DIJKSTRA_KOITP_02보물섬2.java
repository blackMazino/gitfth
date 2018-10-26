package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 그래프_DIJKSTRA_KOITP_02보물섬2 {
/*	
https://koitp.org/problem/SDS_PRO_4_3/read/
	
강희는 악마의 바다에 어마어마한 보물이 숨겨져 있는 보물섬이 있다는 정보를 입수했다. 
하지만 악마의 바다에는 해류가 매우 복잡하게 흐르고 있기 때문에 
강희는 좀처럼 보물을 얻고 돌아올 길을 찾기가 힘들어 여러분에게 도움을 요청했다. 
강희가 악마의 바다에서 보물을 찾아올 수 있는 최단 시간을 계산하자.

악마의 바다에는 1번부터 N번까지 섬이 N개가 있으며, 서로 다른 섬들을 연결하는 해류가 M개 존재한다. 
해류는 한 방향으로만 흐르며 어떤 두 쌍을 잇는 해류가 여러개 존재할 수도 있다. 
강희는 현재 1번 섬에 있으며, 보물섬에 들렀다가 다시 1번 섬으로 돌아와 악마의 바다를 탈출하려고 한다.

첫 번째 줄에 줄에는 섬의 개수 N과 섬들을 잇는 해류의 개수 M이 공백으로 분리되어 주어진다.
(2≤N≤3000,1≤M≤20000)
두 번째 줄에는 보물섬의 번호 T가 주어진다.(2≤T≤N)
세 번째 줄부터 M개의 줄에 걸쳐 각 줄마다 해류의 정보를 나타내는 세 자연수Xi,Yi,Zi가 주어진다. 
Xi는 해류의 시작섬 번호, 
Yi는 해류의 도착섬 번호,
Zi는 해류를 타고 섬과 섬을 이동하는데 걸리는 시간을 나타낸다. 
(1≤Xi,Yi≤N,1≤Zi≤1000)

첫 번째 줄에 강희가 1번 섬에서 출발해 보물을 찾아 다시 1번 섬으로 돌아올 수 있는지 여부를 첫 줄에 출력한다. 
돌아올 수 있는 경우 YES, 아닌 경우 NO를 출력한다.
만약, 첫 번째 줄에 YES를 출력한 경우에는 두 번째 줄에 보물을 찾아 악마의 바다를 탈출하는데 드는 최단 시간을 출력한다.

3 4
3
1 2 4
2 3 3
3 2 2
3 1 1

YES
8
*/
	static int N,M,T;
//	static int [][]g;//시작->종료
	static ArrayList<Integer> con[];
	static ArrayList<Integer> conW[];
	static int [] d1,d2;//way to go, way back
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
//        g = new int [N+1][N+1];        
//        for(int i=1;i<=N;i++){
//        	for(int j=1;j<=N;j++){
//        		g[i][j] = (i==j?0:Integer.MAX_VALUE);//출발=종료인 경우 0
//        	}
//        }
        con = new ArrayList[N+1];
        conW = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
        	con[i]= new ArrayList<>();
        	conW[i]= new ArrayList<>();
        }
        
        
        for(int i=1;i<=M;i++){
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
//        	g[a][b] = Math.min(g[a][b], c);
        	con[a].add(b);
        	conW[a].add(c);
        }
        d1 = new int[N+1];
        d2 = new int[N+1];
        dijkstraByPq(1, d1);       
        dijkstraByPq(T, d2);
        if(d1[T]==Integer.MAX_VALUE || d2[1]==Integer.MAX_VALUE){
        	System.out.println("NO");
        }else{
        	System.out.println("YES");
        	System.out.println(d1[T]+d2[1]);
        }
	}
	
	private static void dijkstraByPq(int s, int[] d) {
		// TODO Auto-generated method stub
		Arrays.fill(d, Integer.MAX_VALUE);
		d[s]=0;
		PriorityQueue<Edge3> pQ = new PriorityQueue<>();
		pQ.add(new Edge3(d[s], s));
		
		while(!pQ.isEmpty()){
			Edge3 e = pQ.poll();
			int v = e.vertex;
			long t = e.time;
			if(d[v] >= t){
				for(int i=0;i<con[v].size();i++){
					int thisV = con[v].get(i);
					int thisT = conW[v].get(i);
					if(d[thisV] > d[v]+thisT){
						d[thisV] = d[v]+thisT;
						pQ.add(new Edge3(d[thisV], thisV));
					}
				}
			}
		}
		
	}

//	private static void dijkstra(int s, int [] d){
//		Arrays.fill(d, Integer.MAX_VALUE);
//		d[s] = 0;
//		boolean [] visited = new boolean[N+1];
//		for(int i=1;i<=N;i++){
//			int tmp = 0;
//			for(int j=1;j<=N;j++){
//				if(!visited[j] && d[tmp]>d[j]){
//					tmp = j;
//				}
//			}
//			visited[tmp] = true;
//			for(int j=1;j<=N;j++){
//				if(g[tmp][j]<Integer.MAX_VALUE && !visited[j]){
//					d[j]=Math.min(d[j], d[tmp]+g[tmp][j]);			
//				}
//			}
//		}
//	}

}

class Edge3 implements Comparable<Edge3>{
	public Edge3(long time, int vertex) {

		this.time = time;
		this.vertex = vertex;
	}
	long time;
	int vertex;
	@Override
	public int compareTo(Edge3 o) {
		return Long.compare(time, o.time);
//		return (int) (time-o.time);
	}
}
