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




public class Solution {
	static int TC;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			int j = 0;
			int k = 0;
			for(int i=0;i<2000;i++){
				if(i==0 || i==1999){
					System.out.println(0+" "+0);
					continue;
				}
				if(i<500){
					j += 1000000;
					System.out.println(j+" "+0);
					continue;
					
				}								
				if(500<=i && i<999){
					k +=1000000;
					 System.out.println(j+ " "+k);
					 continue;
				}
				if(1000<=i && i<1499){
					j -= 1000000;
					System.out.println(j+ " "+k);
					continue;
					
				}
				if(i>=1500 ){
					k -= 1000000;
					System.out.println(j+ " "+k);
					continue;					
				}
			}
			
//			System.out.println("#"+tc+" "+i+" "+d+" "+l);
		}
	}	
}

/*
//조상이키컸으면
public class Solution {
	static int TC, N,Q,K;
	static int H[], answer[];
	
	//LCA
	static ArrayList<Integer> con[];
	static int [] depth;
	static int [][] parent;
	static boolean [] visited;
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
			
			depth = new int[N+1];
			parent = new int [Max+1][N+1];
			visited = new boolean [N+1];
			con = new ArrayList[N+1];
			for(int i=0;i<=N;i++) con[i] = new ArrayList<>();
			
			for(int n=1;n<=N;n++){
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				con[p].add(n);
				H[n]=h;
			}
			
			depth[1] = 1;
			parent[0][1] = 0;
			visited[1] = true;
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
		int k = 0;
		while(d>0){
			if(d%2==1){
				a = parent[k][a];
			}
			d/=2;
			k++;
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
		for(int t : con[i]){
			if(!visited[t]){
				visited[t] = true;
				parent[0][t] = i;
				depth[t] = depth[i] + 1;
				dfs(t);
			}
		}
		
	}	
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
			for(tn = 1; tn<N; tn+=2);
			minTree = new long[212121];
			maxTree = new long[212121];
			Arrays.fill(minTree, Long.MAX_VALUE);
			
			st =new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++){
				int v = Integer.parseInt(st.nextToken());
				arr[n] = v;
				update (n,v);
			}
			min = 0; max = 0;
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
		long result =Long.MAX_VALUE;
		s = tn+s-1;
		e = tn+e-1;
		while(s<=e){
			if(s%2==1){
				result = Math.min(result, minTree[s]);
				s++;		
			}
			if(e%2==0){
				result = Math.min(result, minTree[e]);
				e--;
			}
			s/=2; e/=2;		
		}
		return result;
	}

	private static long searchMax(int s, int e) {
		long result =0;
		s = tn+s-1;
		e = tn+e-1;
		while(s<=e){
			if(s%2==1){
				result = Math.max(result, maxTree[s]);
				s++;		
			}
			if(e%2==0){
				result = Math.max(result, maxTree[e]);
				e--;
			}
			s/=2; e/=2;		
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
				int gb = Integer.parseInt(st.nextToken());
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
			
			for(Union u: list){
				if(u.isLinked) union(u.x, u.y);
			}
			
			bw.write("#"+tc+" ");
			StringBuffer sb = new StringBuffer();
			for(int q=qList.size()-1; q>=0;q--){
				int a = qList.get(q)[0];
				int b = qList.get(q)[1];
				if(a==-1){
					union(list.get(b).x, list.get(b).y);
					list.get(b).isLinked=true;
				}else{
					if(find(a)==find(b)) sb.append("1");
					else sb.append("0");
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
		if(pa==pb) return;
		par[pb] = pa;
		
	}
	private static int find(int a) {
		if(par[a]==a) return a;
		return par[a]= find(par[a]);
	}	
}
class Union{
	public Union(int x, int y, boolean isLinked) {
		super();
		this.x = x;
		this.y = y;
		this.isLinked = isLinked;
	}
	int x,y;
	boolean isLinked;
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
			answer = 0;
			for(P q : qArr){
				P t1 = new P(1000000001, q.y+1);
				int crossCnt = 0;
				for(int i=0; i<stk.size();i++){
					int j = (i==stk.size()-1?0:i+1);
					int a = stk.get(i);
					int b = stk.get(j);
					if(isCross(q, t1,arr[a],arr[b])){
						crossCnt++;
					}
				}
				if(crossCnt ==0 || crossCnt%2==0){
					answer++;
				}
			}
			
			System.out.println("#"+tc+" "+answer);
		}
	}
	private static boolean isCross(P a, P b, P c, P d) {
		return (ccw(a,b,c)*ccw(a,b,d)<0 && ccw(c,d,a)*ccw(c,d,b)<0);
	}
	private static int ccw(P a, P b, P c) {
		long val = (long)a.x*b.y - (long)b.x*a.y
				  +(long)b.x*c.y - (long)c.x*b.y
				  +(long)c.x*a.y - (long)a.x*c.y;
		if(val>0) return 1;
		if(val<0) return -1;
		return 0;
	}
	private static void convexHull() {
		//preSort
		for(int i=1;i<arr.length;i++){
			if(arr[i].x < arr[0].x){
				if(arr[i].y <= arr[0].y){
					P tmp = arr[0];
					arr[0] = arr[i];
					arr[i] = tmp;
				}
			}
		}
		
		Arrays.sort(arr, 1, N, new Comparator<P>(){

			@Override
			public int compare(P a, P b) {
				int v = ccw(arr[0],a,b);
				if(v>0) return -1;
				if(v<0) return 1;			
				return Math.abs(a.x)+a.y -(Math.abs(b.x)+b.y);
			}
			
		});
		
		stk = new LinkedList<>();
		stk.add(0);
		for(int i=1;i<N;i++){
			while(stk.size()>1 && ccw(arr[stk.size()-1] , arr[stk.getLast()] , arr[i])<=0){
				stk.removeLast();
			}
			stk.addLast(i);
		}		
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