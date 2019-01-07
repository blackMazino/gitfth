package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 사전201708_동전배치 {

/*
입력
2
4
-1 0 0 0
11
4 9 0 0 -1 9 4 0 4 8 4
 
출력
#1 5
#2 15

*/
	static int TC, N, root, answer,val[][];
//	static int cVal[];
//	static boolean visited[];
	static int iK = 6;
	static ArrayList<Integer> con[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("src/exercise/사전201708.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			N= Integer.parseInt(br.readLine());
			con = new ArrayList[N];
			val = new int[N][iK+1];
			
			for(int n=0;n<N;n++){
				con[n] = new ArrayList<>();
				Arrays.fill(val[n],-1 );
			}
			st = new StringTokenizer(br.readLine());
			for(int n=0;n<N;n++){
				int p = Integer.parseInt(st.nextToken());				
				if(p==-1) root = n;
				else con[p].add(n);
			}
			
//			for(int i=0;i<N;i++) System.out.println("con["+i+"]="+con[i]);
			
			answer = Integer.MAX_VALUE;
			for(int i=1;i<=6;i++){				
				answer = Math.min(answer, getMin(root,i));
				
//				visited = new boolean[N];
//				visited[root] = true;	
//				cVal = new int[N];
//				cVal[root] = i;
//				answer = Math.min(answer, dfs(root,i));
			}
			
			
			System.out.println("#"+tc+" "+answer);
		}
	}

//	private static int dfs(int p, int v) {
//		int result = 0;
//		if(visited[p]) return cVal[p];
//		if(con[p].isEmpty()) return v;
//		for(int n:con[p]){
//			int val = Integer.MAX_VALUE;
//			for(int i=1;i<=iK;i++){
//				if(i==v) continue;
//				val = Math.min(val, dfs(n,i));
//			}
//			visited[p] = true;
//			result += val;
//		}
//		return cVal[p]=result + v;
//	}

	private static int getMin(int preVal, int v) {
		int result = 0;
		if(val[preVal][v] != -1) return val[preVal][v];
		if(con[preVal].isEmpty()) return v;
		for(int n:con[preVal]){
			int val = Integer.MAX_VALUE;
			for(int i=1;i<=iK;i++){
				if(i==v) continue;
				val = Math.min(val, getMin(n, i));
			}
			result += val;
		}
		return val[preVal][v] = result + v;
	}

}

