package swE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWWO3kT6F2oDFAV4&categoryId=AWWO3kT6F2oDFAV4&categoryType=CODE
public class SWE_D5_5521_상원이의생일파티 {
	
	static int TC,N,M;// 2<=N<=500, 1<=M<=10000
	static ArrayList<Integer> con[];
	static ArrayList<Integer> list;
	static boolean visited[];
	static int depth[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			con = new ArrayList[N+1];
			for(int n=0;n<=N;n++) con[n] = new ArrayList<>();
			
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				con[a].add(b);
				con[b].add(a);
			}
			

			if(con[1].size()==0){
				System.out.println("#"+tc+" "+0);
			}else{
				visited = new boolean [N+1];
				visited[1] = true;
				depth = new int [N+1];
				depth[1] = 0;
				list = new ArrayList<>();
				list.add(1);
				dfs(1);
//				for(int n:list){
//					System.out.print(n+" ");
//				}
				System.out.println("#"+tc+" "+(list.size()-1));
			}
			
			
		}
	}
	private static void dfs(int i) {
		for(int n:con[i]){
			if(!visited[n] || depth[i]+1 < depth[n]){			
				visited[n] = true;
				depth[n] = depth[i] + 1;
//				System.out.println(n+" "+depth[n]);
				if(depth[n]>2){
					continue;
				}else{
					if(!list.contains(n)){
						list.add(n);
					}				
					dfs(n);
				}
			}			
		
		}
		
	}
}
