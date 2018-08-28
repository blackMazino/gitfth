import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class source {
	
	//Sliding window
//	static int N,K;
//	static int a[];
//	static long sum;
//	static LinkedList<Integer> dQMax, dQMin; 
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		N = Integer.parseInt(st.nextToken());
//		K = Integer.parseInt(st.nextToken());
//		
//		
//		st = new StringTokenizer(br.readLine());
//		for(int i=1;i<=N;i++){
//			a[i] = Integer.parseInt(st.nextToken());			
//		}
//		dQMax = new LinkedList<Integer>();
//		dQMin = new LinkedList<Integer>();
//		
//		for(int i=1;i<=N;i++){
//			while(!dQMax.isEmpty() && a[dQMax.getLast()] <= a[i]) dQMax.removeLast();
//			dQMax.addLast(i);//save idx
//			
//			while(!dQMin.isEmpty() && a[dQMin.getLast()] >= a[i]) dQMin.removeLast();
//			dQMin.addLast(i);
//						
//			sum += a[i];
//			
//			if(i>=K){
//				sum -= a[i-K];
//				while(dQMax.getFirst() <= i-K) dQMax.removeFirst();//1개남는다 가장 큰값
//				while(dQMin.getFirst() <= i-K) dQMin.removeFirst();
//				
//				System.out.println(a[dQMax.getFirst()] +" "+ a[dQMin.getFirst()] + sum);
//			}
//		}
//	}
	
	//LCA
//	static int N;
//	static int[] depth;
//	static boolean [] visited;
//	static int [][] parent;
//	static ArrayList<Integer>[]con;
//	static long answer;
//	static int Max = 16;//N<100000
//	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		N = Integer.parseInt(st.nextToken());
//		
//		depth = new int [N+1];
//		visited = new boolean [N+1];
//		parent = new int[Max+1][N+1];//2^17 = 13만, 2^16 = 6만5천
//		con = new ArrayList [N+1];
//		for(int i=1;i<=N;i++) con[i] = new ArrayList<Integer>();
//		
//		for(int i=1;i<=N-1;i++){
//			st = new StringTokenizer(br.readLine());
//			int s = Integer.parseInt(st.nextToken());
//			int e = Integer.parseInt(st.nextToken());
//			con[s].add(e);
//			con[e].add(s);			
//		}
//	
//		dfsForLCA(1,1,0);
//		
//		//sparase table
//		for(int k=0;k<Max;k++){//2^0(=1)번째 조상부터~ Max-1까지, Max까지 조상을 셋팅함
//			for(int n=1;n<=N;n++){
//				parent [k+1][n] = parent[k][parent[k][n]];
//			}
//		}
//		
//		answer = 0;
//		for(int i=1;i<N;i++){
//			answer += getMovingDay(i,i+1);
//		}
//		System.out.println(answer);
//	}
//
//	private static long getMovingDay(int i, int j) {
//		long result = 0;
//		if(i==1){
//			result = depth[j]-depth[i];
//		}else{
//			int lca = lca(i,j);
//			result = (depth[i] - depth[lca]) + (depth[j] - depth[lca]) ;
//		}		
//		return result;
//	}
//
//	private static int lca(int a, int b) {
//		if(depth[b]>depth[a]) return lca(b,a);
//		
//		int d = depth[a]-depth[b];
//		int k=0;
//		while(d>0){
//			if(d%2==1) a = parent[k][a];
//			d/=2; k++;
//		}
//		
//		if(a==b) return a;
//		
//		for(int i=Max;i>0;i--){
//			if(parent[i][a]!=parent[i][b])
//				a=parent[i][a];
//				b=parent[i][b];
//		}
//		return parent[0][a];
//	}
//
//	private static void dfsForLCA(int cur, int d, int p) {
//		depth[cur] = d;
//		parent[0][cur] = p;
//		visited[cur] = true;
//		for(int t:con[cur]){
//			if(!visited[t]){
//				visited[t] = true;
//				dfsForLCA(t,d+1,cur);
//			}
//		}		
//	}

	//MST
	static int N,M;
	static int[] par;//u-f
	static ArrayList<City> list;
	static long answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<City>();
		answer = 0;
		int a,b,c;
		for(int i=1;i<=M;i++){
			st =new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list.add(new City(a,b,c));
		}
		Collections.sort(list, new Comparator<City>(){

			@Override
			public int compare(City o1, City o2) {
				// TODO Auto-generated method stub
				return o1.c-o2.c;
			}
			
		});
		
		par = new int[N+1];
		for(int i=1;i<=N;i++) par[i]=i;
		
		for(City city : list){
			int s = find(city.s);
			int e = find(city.e);
			if(find(s)!=find(e)){
				union(s,e);
				answer = answer + city.c;
			}
		}
		System.out.println(answer);
	}
	
	private static void union(int s, int e) {
		par[s] = e;		
	}
	
	private static int find(int s) {
		if(par[s]==s) return s;
		return par[s] = find(par[s]);
	}

}
class City{
	int s,e,c;
	public City(int s, int e, int c) {
		super();
		this.s = s;
		this.e = e;
		this.c = c;
	}
}
