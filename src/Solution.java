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

//public class Solution {
//	static int TC;
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//
//	}
//	
//}


/*조상이 키컸으면
public class Solution {
	static int TC, N,Q,K;
	static int [] H, answer;
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
			for(int i=0;i<=N;i++) con[i] = new ArrayList<>();
			
			for(int n=1;n<=N;n++){
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				con[n].add(p); con[p].add(n);
				H[n] = h;
			}
			depth = new int [N+1];
			parent = new int [Max+1][N+1];
			visited = new boolean [N+1];
			depth[1]=1;
			parent[0][1]=0;
			visited[1]=true;
			dfs(1);
			for(int k=0;k<Max;k++){
				for(int n=1;n<=N;n++){
					parent[k+1][n]=parent[k][parent[k][n]];
				}
			}
			bw.write("#"+tc);
			int t[];
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				t = new int [K];
				for(int k=0;k<K;k++){
					t[k] = Integer.parseInt(st.nextToken());
				}
				bw.write(" ");
				bw.write(String.valueOf(getTallest(t)));
			}
			bw.write("\n");
			bw.flush();
			
		}
		bw.close();
	}
	private static int getTallest(int[] t) {
		int result =0;
		int lca = t[0];
		for(int k=1;k<t.length;k++){
			lca = getLca(lca, t[k]);
		}
		result = H[lca];
		while(true){
			result = Math.max(result , H[parent[0][lca]]);
			lca = parent[0][lca];
			if(lca == 0) break;
		}
		return result;
	}
	private static int getLca(int a, int b) {
		if(depth[a]<depth[b]) return getLca(b,a);
		int d = depth[a]-depth[b];
		int k=0;
		while(d>0){
			if(d%2==1) a= parent[k][a];
			d/=2; k++;
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
		for(int n:con[i]){
			if(!visited[n]){
				visited[n]=true;
				depth[n] = depth[i]+1;
				parent[0][n]=i;
				dfs(n);
			}
		}
		
	}	
}
*/



//최대최소
//public class Solution {
//	static int TC,N,Q,tn;	
//	static long[] minTree, maxTree;
//	static long min,max;
//	public static void main(String[] args) throws Exception {
////		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Practice31.txt"));
//		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
//			st = new StringTokenizer(br.readLine());
//			N = Integer.parseInt(st.nextToken());
//			Q = Integer.parseInt(st.nextToken());
//			for(tn =1;tn<N;tn=tn+2);
//			minTree = new long[212121]; Arrays.fill(minTree, Long.MAX_VALUE);
//			maxTree = new long[212121];
//			
//			st = new StringTokenizer(br.readLine());
//			for(int n=1;n<=N;n++){
//				int t  = Integer.parseInt(st.nextToken());
//				update(n, t);
//			}
//			min = 0;max = 0;
//			for(int q=1;q<=Q;q++){
//				st = new StringTokenizer(br.readLine());
//				int a = Integer.parseInt(st.nextToken());
//				int b = Integer.parseInt(st.nextToken());
//				int c = Integer.parseInt(st.nextToken());
//				if(a==1){
//					update(b,c);
//				}else{
//					max += searchMax(b,c);
//					min += searchMin(b,c);
//				}
//			}
//			
//			System.out.println("#"+tc+" "+max+" "+min);
//		}
//	}
//	private static long searchMax(int s, int e) {
//		long result = 0;
//		s = s+tn-1;
//		e = e+tn-1;
//		while(s<=e){
//			if(s%2==1){
//				result = Math.max(result, maxTree[s]);
//				s++;
//			}
//			if(e%2==0){
//				result = Math.max(result, maxTree[e]);
//				e--;
//			}
//			s/=2;e/=2;
//		}
//		return result;
//	}
//	private static long searchMin(int s, int e) {
//		long result = Long.MAX_VALUE;
//		s = s+tn-1;
//		e = e+tn-1;
//		while(s<=e){
//			if(s%2==1){
//				result = Math.min(result, minTree[s]);
//				s++;
//			}
//			if(e%2==0){
//				result = Math.min(result, minTree[e]);
//				e--;
//			}
//			s/=2;e/=2;
//		}
//		return result;
//	}
//	private static void update(int w, int g) {
//		for(int i=tn+w-1;i>0;i=i/2){
//			if(i==tn+w-1){
//				minTree[i] = g;
//				maxTree[i] = g;
//				continue;
//			}
//			minTree[i] = Math.min(minTree[2*i], minTree[2*i + 1]);
//			maxTree[i] = Math.max(maxTree[2*i], maxTree[2*i + 1]);			
//		}
//		
//	}
//}


//말뚝
public class Solution {
	static int TC,N,Q,answer;
	static P2[] arr;
	static P2[] qArr;
	static LinkedList<Integer> stk;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous181110.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new P2 [N];
			for(int n=0;n<N;n++){
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[n] = new P2(x,y);
			}			
			Q = Integer.parseInt(br.readLine());
			qArr = new P2 [Q];
			
			for(int q=0;q<Q;q++){
				st = new StringTokenizer(br.readLine());
				int x= Integer.parseInt(st.nextToken());
				int y= Integer.parseInt(st.nextToken());
				qArr[q] = new P2(x,y);
			}
			convexHull();
			answer = 0;
			for(int q=0;q<qArr.length;q++){
				P2 t = new P2(1000000001,qArr[q].y+1);
				int crossCnt = 0;
				for(int i=0;i<stk.size();i++){
					int j = i+1;
					if(i==stk.size()-1) j = 0;
					int a = stk.get(i);
					int b = stk.get(j);
					if(isCross(qArr[q],t,arr[a],arr[b])){
						crossCnt++;
					}
				}
				if(crossCnt ==0 || crossCnt%2 ==0){
					answer ++;
				}
			}

			System.out.println("#"+tc+" "+answer);
		}

	}
	private static boolean isCross(P2 a, P2 b, P2 c, P2 d) {
		return (ccw(a,b,c)*ccw(a,b,d) < 0 
			 && ccw(c,d,a)*ccw(c,d,b) < 0 );
	}
	private static void convexHull() {
		for(int i=1;i<arr.length;i++){
			if(arr[0].y >= arr[i].y){
				if(arr[0].x > arr[i].x){
					P2 tmp = arr[0];
					arr[0] = arr[i];
					arr[i] = tmp;
				}
			}
		}
		Arrays.sort(arr, 1,N, new Comparator<P2>(){

			@Override
			public int compare(P2 a, P2 b) {
				int val = ccw(arr[0],a,b);
				if(val > 0) return -1;
				if(val < 0) return 1;
				return (Math.abs(a.x)+a.y) - (Math.abs(b.x)+b.y);
			}
			
		});
		
		stk = new LinkedList<Integer>();
		stk.addLast(0);
		for(int i=1;i<N;i++){
			while(stk.size()>1
			   && ccw(arr[stk.get(stk.size()-2)], arr[stk.getLast()], arr[i]) <=0){
				stk.removeLast();
			}
			stk.addLast(i);
		}
		
	}
	protected static int ccw(P2 a, P2 b, P2 c) {
		long val = ((long)a.x*(long)b.y - (long)b.x*(long)a.y
				  +(long)b.x*(long)c.y - (long)c.x*(long)b.y
				  +(long)c.x*(long)a.y - (long)a.x*(long)c.y);
		if(val>0) return 1;
		if(val<0) return -1;
		return 0;
	}
	
}
class P2{
	int x,y;

	public P2(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
