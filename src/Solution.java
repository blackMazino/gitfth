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

//조상이 키컸으면
/*
public class Solution {
	static int TC,N,Q,K;
	static int H[];
	static int answer[];
	static ArrayList<int[]> qList;
	
	//LCA
	static ArrayList<Integer> con[];
	static int depth[];
	static int parent[][];
	static boolean visited[];
	static int Max = 16;
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
			
			H = new int[N+1];
			
			con = new ArrayList[N+1];
			for(int n=0;n<=N;n++) con[n] = new ArrayList<>();
			for(int n=1;n<=N;n++){
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				H[n] = p;
//				parent[0][n] = p;//for BFS
				con[p].add(n);
				con[n].add(p);//for BFS
			}
			depth = new int[N+1];
			parent = new int[Max+1][N+1];
			visited = new boolean [N+1];
			
			depth[1] = 1;
			parent[0][1] = 0;
			visited[1] = true;
			dfs(1);
			for(int k=0;k<Max;k++){
				for(int n=1;n<=N;n++){
					parent[k+1][n] = parent[k][ parent[k][n] ];
				}
			}
			
			bw.write("#"+tc);
			for(int q=1;1<=Q;q++){
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				int t[] = new int[K];
				for(int k=0;k<K;k++){
					t[k] =  Integer.parseInt(st.nextToken());
				}
				bw.write(" ");
				bw.write(String.valueOf(getTallestA(t)));
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	private static int getTallestA(int[] t) {
		int result = 0;
		int lca = t[0];
		for(int k=1;k<t.length;k++){
			lca = getLca(lca, t[k+1]);
		}
		result = H[lca];
		while(lca>0){
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
			if(d%2==1) a = parent[k][a];
			d/=2;
			k++;
		}
		
		if(a==b) return a;
		for(int i=Max;i>=0;i++){
			if(parent[i][a]!=parent[i][b]){
				a = parent[i][a];
				b = parent[i][b];
			}
		}				
		return parent[0][a];
	}
	private static void dfs(int i) {
		for(int n:con[i]){
			if(!visited[n]){
				visited[n] = true;
				depth[n] = depth[i]+1;
				parent[0][n] = i;
				dfs(n);
			}
		}		
	}
}
*/
//최대최소
/*
public class Solution {
	
	static int TC,N,Q,tn;
	static int[] arr;
	static long min, max;
	static long[] minTree, maxTree;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Practice31.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++){
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
				int t = Integer.parseInt(st.nextToken());
				arr[n] = t;
				update(n,t);				
			}
			
			min = 0;max = 0;
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				int a= Integer.parseInt(st.nextToken());
				int b= Integer.parseInt(st.nextToken());
				int c= Integer.parseInt(st.nextToken());
				if(a==1){
					arr[b] = c;
					update(b,c);
				}else{
					max += searchMax(b,c);
					min += searchMin(b,c);
				}
			}
			System.out.println("#"+tc+" "+max+" "+min);
		}
	}
	private static long searchMin(int s, int e) {
		long result = Long.MAX_VALUE;
		s = s+tn-1; e=e+tn-1;
		while(s<=e){
			if(s%2==1){
				result = Math.min(result, minTree[s]);
				s++;
			}
			if(e%2==0){
				result = Math.min(result, minTree[s]);
				e--;
			}
			s/=2; e/=2;
		}
		return result;
	}
	private static long searchMax(int s, int e) {
		long result = 0;
		s = s+tn-1; e=e+tn-1;
		while(s<=e){
			if(s%2==1){
				result = Math.max(result, maxTree[s]);
				s++;
			}
			if(e%2==0){
				result = Math.max(result, maxTree[s]);
				e--;
			}
			s/=2; e/=2;
		}
		return result;
	}
	private static void update(int w, int g) {
		// TODO Auto-generated method stub
		for(int i=tn+w-1;i>0;i/=2){
			if(i==tn+w-1){
				minTree[i] = g;
				maxTree[i] = g;
				continue;
			}
			minTree[i] = Math.min(minTree[2*i],minTree[2*1 + 1]);
			maxTree[i] = Math.max(maxTree[2*i],maxTree[2*1 + 1]);			
		}
	}
}
*/



//말뚝
/*
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
*/

