package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class Day1_rootedTree2 {

	static int TC;
	static int N,R,Q,x,y;
	static int [] nodes;
	static ArrayList<Integer[]> parentList = new ArrayList<Integer[]>();
	
	

	//static ArrayList<int[]> edge[] = new ArrayList[121212];
	static ArrayList<Integer> edge[] = new ArrayList[121212];//ArrayList of Array
	
	static int [] S = new int [121212];
	static int [] E = new int [121212];
	static int cnt = 1;
	static int []name  = new int [121212];
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			nodes = new int [N+1];
			st = new StringTokenizer(br.readLine());
			
			//초기화
			edge = new ArrayList[N+1];
			for(int i=0;i<N;i++){		
				edge[i] = new ArrayList<>();
			}
			
			for(int i=1;i<=N;i++){							
				int x = Integer.parseInt(st.nextToken());				
				if(i==R){
					continue;
				}											
				edge[x].add(i);				
			}
			for(int i=0;i<edge.length;i++){
				for(int j=0;j<edge[i].size();j++){
					System.out.println(edge[i].get(j));
				}				
			}
			
			
			dfs(R);//reNumbering
			
//			for(int a=0;a<S.length;a++){
//				System.out.println(S[a]);
//			}
//			System.out.println("==========");
//			for(int a=0;a<S.length;a++){
//				System.out.println(S[a]);
//			}
			
			for(int k=0;k<Q;k++){
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());						
				
				if(isParent()){
					System.out.println("YES");
				}else{
					System.out.println("NO");
				}
			}
//		}
		br.close();

	}
	static void dfs(int w){		
		int i;
		S[w] = cnt;
		name[w] = cnt++;
		
		for(i=0;i<edge[w].size();i++){
			dfs(edge[w].get(i));
		}
		E[w] = cnt -1;		
	}
	
	private static boolean isParent() {
		boolean result = false;
		if(S[x] <= name[y] && name[y]<=E[x]){
			result = true;
		}		
		return result;
	}

}