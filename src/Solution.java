import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



/*
public class Solution {
	static int TC;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			System.out.println("#"+tc+" ");
		}
	}	
}
*/

//조상이 키컸으면
public class Solution {
	static int TC, N,Q,K;
	static int [] H;
	static int [] answer;
	
	//LCA
	static ArrayList<Integer> con[];
	static int [] depth;
	static int [][] parent;
	static boolean visited[];
	static int Max = 16;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Past51.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			H = new int[N+1];
			
			depth = new int [N+1];
			parent = new int [Max+1][N+1];
			visited = new boolean[N+1];
			con = new ArrayList[N+1];
			for(int n=0;n<=N;n++) con[n] = new ArrayList<>();
			
			for(int n=1;n<=N;n++){				
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				con[p].add(n);
				H[n] = h;				
			}
			
			depth[1] = 1;
			visited[1]= true;
			parent[0][1] = 0;
			dfs(1);
			
			//sparse Table
			for(int k=0;k<Max;k++){
				for(int n=1;n<=N;n++){
					parent[k+1][n] = parent[k][parent[k][n]];
				}
			}
			
			bw.write("#"+tc);
			int [] t;
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				t = new int[K];
				for(int k=0;k<K;k++){
					t[k] = Integer.parseInt(st.nextToken());
				}
				bw.write(" "+String.valueOf(getTallestVal(t)));
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		
	}
	private static int getTallestVal(int[] t) {
		int result = 0;
		int lca = t[0];
		for(int k=1;k<t.length;k++){
			lca = getLca(lca, t[k]);
		}
		result = H[lca];
		while(true){
			if(lca == 0) break;
			result = Math.max(result, H[parent[0][lca]]);
			lca = parent[0][lca];
		}
		return result;
	}
	private static int getLca(int a, int b) {
		if(depth[a] < depth[b]) return getLca(b,a);
		int d = depth[a] - depth[b];
		int k=0;
		while(d>0){
			if(d%2==1){
				a = parent[k][a];
			}
			d/=2; k++;
		}
		
		if(a==b) return a;
		for(int i=Max;i>=0;i--){
			if(parent[i][a]!=parent[i][b]){
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		return parent[0][a];
	}
	private static void dfs(int i) {
		for(int n: con[i]){
			if(!visited[n]){
				visited[n] = true;
				depth[n] = depth[i] +1;
				parent[0][n] = i;
				dfs(n);
			}
		}
		
	}	
}


//인터스텔라
/*
public class Solution {
	static int TC,N,M,K, s,e;//2 ≤ N ≤ 100,000, 1 ≤ M ≤ 200,000, 0 ≤ K ≤ 2
	static long d[][], answer;
	static ArrayList<Integer> con[];
	static ArrayList<Integer> conW[];
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Past05.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			con = new ArrayList[N+1];
			conW = new ArrayList[N+1];
			for(int i=1;i<=N;i++){
				con[i]=new ArrayList<>();
				conW[i]=new ArrayList<>();
			}
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				con[a].add(b); con[b].add(a);
				conW[a].add(c); conW[b].add(c);
			}
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			d = new long[N+1][K+1];
			for(int n=1;n<=N;n++){
				for(int k=0;k<=K;k++){
					d[n][k] = Long.MAX_VALUE;
					if(n==s) d[n][k] = 0;
				}
			}					
			
			PriorityQueue<Interstela> pQ = new PriorityQueue<>();
			pQ.add(new Interstela(0,s,0));
			boolean isArrive= false;
			while(!pQ.isEmpty() && !isArrive){
				Interstela is = pQ.poll();
				long dis = is.d;
				int v = is.v;
				int w = is.w;
				
//				if(d[v][w] < dis) continue;
				
				if(d[e][w] == dis) break;
				
				for(int i=0;i<con[v].size();i++ ){
					int thisV = con[v].get(i);
					int thisD = conW[v].get(i);
					if(d[thisV][w]>d[v][w]+thisD){
						d[thisV][w]=d[v][w]+thisD;
						pQ.add(new Interstela(d[thisV][w],thisV,w));
					}
					//in case of Using Warp
					if(w<K){
						if(d[thisV][w+1]>d[v][w]+1){
							d[thisV][w+1]=d[v][w]+1;
							pQ.add(new Interstela(d[thisV][w+1], thisV, w+1));
						}
					}
				}
			}
			answer = Long.MAX_VALUE;
			for(int k=0;k<=K;k++){
				answer = Math.min(answer, d[e][k]);
			}
			System.out.println("#"+tc+" "+answer);
			
		}

	}

}
//class Interstela implements Comparator<Interstela>{
class Interstela implements Comparable<Interstela>{
	public Interstela(long d, int v, int w) {
		super();
		this.d = d;
		this.v = v;
		this.w = w;
	}
	long d;
	int v,w;//distance, vertex, warp
	@Override
	public int compareTo(Interstela o) {
		return Long.compare(d, o.d);
	}

}
*/
/*
//연방
public class Solution {
	static int TC, N,M,Q;
	static int[] par;
	static ArrayList<Union> list;
	static ArrayList<int[]> qList;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Past08.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			par = new int[N+1];
			for(int n=1;n<=N;n++) par[n] = n;
								
			list = new ArrayList<>();
			list.add(new Union(0,0,true));
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.add(new Union(a,b,true));
			}
			
			qList = new ArrayList<>();
			Q = Integer.parseInt(br.readLine());
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				int gb= Integer.parseInt(st.nextToken());
				if(gb == 1){
					int k = Integer.parseInt(st.nextToken());
					list.get(k).isLinked = false;
					qList.add(new int[]{-1,k});
				}else{
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					qList.add(new int[]{a,b});
				}				
			}
			
			for(Union u:list){
				if(u.isLinked) union(u.a, u.b);
			}
			
			bw.write("#"+tc+" ");
			StringBuffer sb = new StringBuffer();
			for(int q=qList.size()-1; q>=0; q--){
				int a = qList.get(q)[0];
				int b = qList.get(q)[1];
				if(a==-1){
					union(list.get(b).a, list.get(b).b);
					list.get(b).isLinked = true;
				}else{
					sb.append((find(a)==find(b)?"1":"0"));
				}
			}
			sb.reverse();
			bw.write(sb.toString());
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return;
		par[pb] = pa;
		
	}
	private static int find(int a) {
		if(par[a]==a) return a;
		return par[a] = find(par[a]);
	}	
}

class Union{
	public Union(int a, int b, boolean isLinked) {
		super();
		this.a = a;
		this.b = b;
		this.isLinked = isLinked;
	}
	int a,b;
	boolean isLinked;
}
*/

/*
//최대최소
public class Solution {
	static int TC, N,Q, tn;//5<=N<=100000, 1<=Q<=100000
	static int[] arr;
	static long min, max;
	static long[] minTree, maxTree;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Practice31.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			for(tn=1;tn<N;tn+=2);
			minTree = new long[212121];
			maxTree = new long[212121];
			Arrays.fill(minTree, Long.MAX_VALUE);
			
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++){
				int v = Integer.parseInt(st.nextToken());
				arr[n] = v;
				update(n,v);
			}
			
			min=0;max=0;
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				if(a==1){
					arr[b] = c;
					update(b,c);
				}else{
					max+=searchMax(b,c);
					min+=searchMin(b,c);
				}
			}
			
			System.out.println("#"+tc+" "+max+" "+min);
		}
	}
	private static long searchMin(int s, int e) {
		long result = Long.MAX_VALUE;
		s = tn+s-1;
		e = tn+e-1;
		while(s<=e){
			if(s%2==1){
				result = Math.min(minTree[s], result);
				s++;
			}
			if(e%2==0){
				result = Math.min(minTree[e], result);
				e--;
			}
			s/=2;e/=2;
		}
		return result;
	}
	private static long searchMax(int s, int e) {
		long result = 0;
		s = tn+s-1;
		e = tn+e-1;
		while(s<=e){
			if(s%2==1){
				result = Math.max(maxTree[s], result);
				s++;
			}
			if(e%2==0){
				result = Math.max(maxTree[e], result);
				e--;
			}
			s/=2;e/=2;
		}
		return result;
	}
	private static void update(int w, int g) {
		for(int i=tn+w-1;i>0;i/=2){
			if(i==tn+w-1){
				minTree[i] = g;
				maxTree[i] = g;
				continue;
			}
			minTree[i] = Math.min(minTree[2*i], minTree[2*i+1]);
			maxTree[i] = Math.max(maxTree[2*i], maxTree[2*i+1]);
		}
		
	}	
}
*/

/*
//말뚝
public class Solution {
	static int TC,N,Q,answer;
	static P[] arr, qArr;
	static LinkedList<Integer> stk;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Past181110.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			N = Integer.parseInt(br.readLine());
			arr = new P[N];
			for(int n=0;n<N;n++){
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[n] = new P(x,y);
			}
			
			Q = Integer.parseInt(br.readLine());
			qArr = new P[Q];
			for(int q=0;q<Q;q++){
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				qArr[q] = new P(x,y);
			}
			
			convexHull();
			answer =0;
			for(P q : qArr){
				P t1 = new P(1000000001, q.y+1);
				int crossCnt = 0;
				for(int i=0;i<stk.size();i++){
					int j = (i==stk.size()-1? 0:i+1);
					int a = stk.get(i);
					int b = stk.get(j);
					if(isCross(q,t1, arr[a], arr[b])){
						crossCnt ++;
					}					
				}
				if(crossCnt == 0|| crossCnt%2==0){
					answer ++;
				}
				
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
	private static boolean isCross(P a, P b, P c, P d) {
		return (ccw(a,b,c)*ccw(a,b,d) <0 && ccw(c,d,a)*ccw(c,d,b) <0);
	}
	private static void convexHull() {
		//preSort
		for(int i=1;i<arr.length;i++){
			if(arr[0].x > arr[i].x){
				if(arr[0].y >= arr[i].y){
					P tmp = arr[i];
					arr[i] = arr[0];
					arr[0] = tmp;
				}				
			}
		}
		
		Arrays.sort(arr, 1,N, new Comparator<P>(){

			@Override
			public int compare(P a, P b) {
				int v = ccw(arr[0], a,b);
				if(v>0) return -1;
				if(v<0) return 1;
				return Math.abs(a.x)+a.y - (Math.abs(b.x)+b.y);
			}
			
		});
					
		stk = new LinkedList<>();
		stk.addLast(0);
		for(int i=1;i<N;i++){
			while(stk.size()>1 && ccw(arr[stk.size()-1], arr[stk.getLast()], arr[i]) <=0){
				stk.removeLast();
			}
			stk.addLast(i);
		}	
	}
	protected static int ccw(P a, P b, P c) {
		long val = (long)a.x*b.y - (long)b.x*a.y 
				  +(long)b.x*c.y - (long)c.x*b.y
				  +(long)c.x*a.y - (long)a.x*c.y;
		if(val>0) return 1;
		if(val<0) return -1;
		return 0;
	}	
}
class P{
	public P(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	int x,y;
}
*/
