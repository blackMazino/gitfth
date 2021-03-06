import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class source {
	//BFS, DFS picnic

//	static int K,N,M;
//	static int[] startNo;
//	static ArrayList<Integer>[] path;
//	static ArrayList<CowPath> list;
//	public static void main(String[] args) throws Exception {
//		 BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );        	
//		 StringTokenizer st = new StringTokenizer( br.readLine() ) ;
//		     
//		 K = Integer.parseInt(st.nextToken());
//		 N = Integer.parseInt(st.nextToken());
//		 M = Integer.parseInt(st.nextToken());
//		 startNo = new int[K+1];
//		 for(int i=1;i<=K;i++){
//			 startNo[i] = Integer.parseInt(br.readLine());
//		 }
//		 path = new ArrayList[N+1];
//		 list = new ArrayList<>();
//		 boolean [] visit = new boolean[N+1];
//		 list.add(new CowPath(0, visit));
//		 for(int i=1;i<=N;i++){
//        	path[i] = new ArrayList<>();
//        	boolean [] tmp = new boolean[N+1];
//        	list.add(new CowPath(0,tmp));
//		 }
//         for(int i=1;i<=M;i++) {
//        	st = new StringTokenizer(br.readLine()) ;
//        	int s = Integer.parseInt(st.nextToken());
//        	int e = Integer.parseInt(st.nextToken());
//        	path[s].add(e);
//         }	
//         int answer = 0;
//         for(int i=1;i<=K;i++){
//         	//bfs(i);
//         	dfs(i,startNo[i]);
//         }
//         for(int i=1;i<=N;i++){
//         	if(list.get(i).cowCnt==K){
//         		answer++;
//         	}
//         }
//         System.out.println(answer);
//                  
//	}
//	private static void dfs(int k, int n) {
//		if(!list.get(n).cowVisit[k]){
//			list.get(n).cowVisit[k] = true;
//			list.get(n).cowCnt++;
//		}
//		for(int x:path[n]){
//			if(!list.get(x).cowVisit[k]){
//				list.get(x).cowVisit[k] = true;
//				list.get(x).cowCnt++;
//				dfs(k,x);
//			}
//			
//		}
//	}

//	static int N,M,T;
//	static ArrayList<Integer> con[];
//	static ArrayList<Integer> conW[];
//	static int [] d1,d2;
//	//dijkstra treasure Island
//	public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        T = Integer.parseInt(br.readLine());
//        //Initialize
//        d1 = new int[N+1];
//        d2 = new int[N+1];        
//        con = new ArrayList[N+1];
//        conW = new ArrayList[N+1];
//        for(int i=1;i<=N;i++){//as Node counts
//        	con[i] = new ArrayList<>();
//        	conW[i] = new ArrayList<>();
//        }
//        
//        for(int i=1;i<=M;i++){
//        	st = new StringTokenizer(br.readLine());
//        	int s = Integer.parseInt(st.nextToken());
//        	int e = Integer.parseInt(st.nextToken());
//        	int w = Integer.parseInt(st.nextToken());
//        	con[s].add(e);
//        	conW[s].add(w);
//        }
//        
//        dijkstrayByPq(1,d1);
//        dijkstrayByPq(T,d2);
//        if(d1[T]==Integer.MAX_VALUE || d2[1]==Integer.MAX_VALUE){
//        	System.out.println("NO");
//        }else{
//        	System.out.println("YES");
//        	System.out.println(d1[T]+d2[1]);
//        }
//	}	
//	private static void dijkstrayByPq(int s, int[] d) {
//		Arrays.fill(d, Integer.MAX_VALUE);
//		d[s]=0;
//		PriorityQueue<Edge> pQ = new PriorityQueue<>();
//		pQ.add(new Edge(s,d[s]));
//		while(!pQ.isEmpty()){
//			Edge e = pQ.poll();
//			int v = e.vertex;
//			long t = e.time;
//			if(d[v]>=t){
//				for(int i=0;i<con[v].size();i++){
//					int end = con[v].get(i);
//					int w = conW[v].get(i);
//					if(d[end]>d[v] + w){
//						d[end] = d[v] + w;
//						pQ.add(new Edge(end,d[end]));
//					}
//				}
//			}
//			
//		}
//	}
	
	//merchant - LCA
	static int N;
	static int[]depth;
	static int[][]parent;
	static boolean[] visited;
	static ArrayList<Integer>[] con;
	static long answer;
	static int Max = 16;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		con = new ArrayList[N+1];
		depth = new int[N+1];
		visited = new boolean [N+1];
		parent = new int [Max+1][N+1];
		
		for(int i=1;i<=N;i++){
			con[i] = new ArrayList<>();
		}
		
		for(int i=1;i<=N-1;i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			con[s].add(e);
			con[e].add(s);
		}
		
		depth[1] = 1;
		
		
		
		parent[0][1] = 0;
		visited[1] = true;
		dfs(1);
		
		//make sparse table
		for(int k=0;k<=Max-1;k++){
			for(int n=1;n<=N;n++){
				parent[k+1][n] = parent[k][parent[k][n]];
			}
		}
	
		answer = 0;
		for(int i=1;i<=N-1;i++){
			answer += getMovingDay(i,i+1);
		}
	}
	private static long getMovingDay(int s, int e) {
		if(s==1){
			return depth[e] - depth[s];
		}else{
			int lca = lca(s,e);
			return ( depth[e]-depth[lca] + depth[s]-depth[lca] ) ;	
		}		
	}
	private static int lca(int a, int b) {
		if(depth[b]>depth[a]) return lca(b,a);
		
		int d = depth[a]-depth[b];
		int k=0;
		while(d>0){
			if(d%2==1) a = parent[k][a];
			k++;
			d/=2;
		}
		
		if(a==b) return a;
		
		for(int i=Max;i>=0;i--){
			if(parent[i][a] != parent[i][b]){
				a = parent[i][a];
				b = parent[i][b];
			}
		}		
		return parent[0][a];
	}
	private static void dfs(int i) {
		for(int n : con[i]){
			if(!visited[n]){
				visited[n] = true;
				depth[n] = depth[i] + 1;
				parent[0][n] = i;
				dfs(n);
			}
		}
		
	}
	
//	public static void main(String[] args) {
//	
//}
	
}
class Edge implements Comparable<Edge>{
	public Edge(int vertex, long time) {
		super();
		this.vertex = vertex;
		this.time = time;
	}
	int vertex;
	long time;
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return Long.compare(time, o.time);//desc
	}
}

class CowPath{
	public CowPath(int cowCnt, boolean[] cowVisit) {
		super();
		this.cowCnt = cowCnt;
		this.cowVisit = cowVisit;
	}
	int cowCnt;
	boolean[] cowVisit;
}