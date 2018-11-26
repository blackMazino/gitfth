package exercise;
 
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
//조상이 키컸으면
public class Solution {
	static int TC, N, Q,K;
	static int H[];
	
	static int max = 16;
	static ArrayList<Integer>[] con;
	static int depth[];
	static int parent[][];
	static boolean visited[];
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous51.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			con = new ArrayList[N+1];
			H = new int[N+1];
			for(int i=0;i<=N;i++){
				con[i] = new ArrayList<>();
			}
			for(int n=1;n<=N;n++){
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				H[n] = h;
				con[n].add(p); con[p].add(n);
			}
			depth = new int[N+1]; 
			depth[1] = 1;
			parent = new int[max+1][N+1];
			parent[0][1] = 0;
			visited = new boolean[N+1];
			visited[1]= true;
			dfs(1);
			//spars table
			for(int k=0;k<max;k++){
				for(int n=1;n<=N;n++){
					parent[k+1][n] = parent[k][parent[k][n]];
				}
			}
			
			bw.write("#"+tc);
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				int[] tmp = new int[K];
				for(int k=0;k<K;k++){
					tmp[k] = Integer.parseInt(st.nextToken());
				}
				bw.write(" "+String.valueOf(getTallest(tmp)));
			}
			bw.write("\n");
			
		}
		bw.flush();
	}
	private static int getTallest(int[] tmp) {
		int result = 0;
		int lca = tmp[0];
		for(int i=0;i<tmp.length-1;i++){
			lca = getLca(lca,tmp[i+1]); 
		}
		result = H[lca];
		while(lca>0){
			result = Math.max(result, H[ parent[0][lca] ]);
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
			d/=2;
			k++;
		}
		if(a==b) return a;
		for(int i=max;i>=0;i--){
			if(parent[i][a] != parent[i][b]){
				a = parent[i][a];
				b = parent[i][b];
			}
		}		
		return parent[0][a];
	}
	private static void dfs(int i) {
		for(int n:con[i]){
			if(!visited[n]){
				depth[n] = depth[i]+1;
				parent[0][n] = i;
				visited[n] = true;
				dfs(n);
			}
		}
		
	}
}
*/
/*
//최대최소
public class Solution {
	static int TC, N,Q;
	static int tn;
	static int [] tree;
	static long [] maxTree, minTree;
	static int min, max;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Practice31.txt"));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			tree = new int[N+1];
			for(tn = 1;tn<N;tn=tn+2);
			maxTree = new long [212121];	
			minTree = new long [212121];
			Arrays.fill(minTree, Long.MAX_VALUE);
			
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++){				
				int t = Integer.parseInt(st.nextToken());
				tree[n] = t;
				update(n,t);				
			}						
			min = 0;
			max = 0;
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				if(a==1){
					tree[b] = c;
					update(b,c);
				}else{
					min += searchMin(b,c);
					max += searchMax(b,c);
				}
			}
			
			System.out.println("#"+tc+" "+max+" "+min);
		}
				
	}
	private static long searchMax(int s, int e) {
		long result = Long.MIN_VALUE;
		s = s+tn-1;
		e = e+tn-1;
		while(s<=e){
			if(s%2==1){
				result = Math.max(result, maxTree[s]);
				s++;
			}
			if(e%2==0){
				result = Math.max(result, maxTree[e]);
				e--;
			}
			s/=2;
			e/=2;
		}	
		return result;
	}
	private static long searchMin(int s, int e) {
		long result = Long.MAX_VALUE;
		s = s+tn-1;
		e = e+tn-1;
		while(s<=e){
			if(s%2==1){
				result = Math.min(result, minTree[s]);
				s++;
			}
			if(e%2==0){
				result = Math.min(result, minTree[e]);
				e--;
			}
			s/=2;
			e/=2;
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



//말뚝
public class Solution {

	static int TC,N,Q,answer;
	static M2D [] arr;
	static M2D [] qArr;
	static LinkedList<Integer> stack;
    public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous181110.txt"));
    	StringTokenizer st;
    	TC = Integer.parseInt(br.readLine());
    	for(int tc=1;tc<=TC;tc++){
    		N = Integer.parseInt(br.readLine());
    		arr = new M2D[N];
    		for(int n=0;n<N;n++){
    			st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			arr[n] = new M2D(x,y);
    		}
    		Q = Integer.parseInt(br.readLine());
    		qArr = new M2D[Q];
    		for(int q=0;q<Q;q++){
    			st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			qArr[q] = new M2D(x,y);
    		}
    		
    		convexHull();
    		
    		answer =0;
    		
    		for(int q=0;q<Q;q++){
    			M2D t = new M2D(1000000001, qArr[q].y+1);
    			int crossCnt = 0;
    			for(int i=0;i<stack.size();i++){    			
    				int j = i+1;
    				if(i==stack.size()-1) j = 0;
    				int a = stack.get(i);
    				int b = stack.get(j);
    				if(isCross(qArr[q], t, arr[a], arr[b])){
    					crossCnt++;
    				}
    			}
				if(crossCnt ==0 || crossCnt%2 == 0){
					answer ++;
				}
    		}
    		System.out.println("#"+tc+" "+answer);
    	}
    }
    
	private static boolean isCross(M2D a, M2D b, M2D c, M2D d) {		
		return (ccw(a,b,c)*ccw(a,b,d)<0
			 && ccw(c,d,a)*ccw(c,d,b)<0);
	}

	private static void convexHull() {
		for(int i=1;i<arr.length;i++){
			if(arr[0].y>=arr[i].y){
				if(arr[0].x > arr[i].x){
					M2D tmp = arr[0];
					arr[0] = arr[i];
					arr[i] = tmp;
				}
			}
		}
		
		Arrays.sort(arr, 1, N, new Comparator<M2D>(){

			@Override
			public int compare(M2D a, M2D b) {
				int v = ccw(arr[0], a,b);
				if(v>0) return -1;
				if(v<0) return 1;				
				return Math.abs(a.x)*a.y - Math.abs(b.x)*b.y;
			}
			
		});
		stack = new LinkedList<>();
		stack.add(0);
		for(int n=1;n<N;n++){
			while(stack.size()>1 
			    &&ccw(arr[stack.get(stack.size()-2)], arr[stack.get(stack.size()-1)], arr[n]) <=0 ){
				stack.removeLast();
			}
			stack.addLast(n);
		}
		
		
	}
	public static int ccw(M2D a,M2D b, M2D c){
		long val =  ((long)a.x*b.y - (long)b.x*a.y
				    +(long)b.x*c.y - (long)c.x*b.y
				    +(long)c.x*a.y - (long)a.x*c.y);
		if(val > 0){
			return 1;
		}else if(val <0){
			return -1;
		}else{
			return 0;
		}
	}
 
}
class M2D{
	int x,y;

	public M2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

