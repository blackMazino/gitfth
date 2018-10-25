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
	//BFS, DFS picnic

	static int K,N,M;
	static int[] startNo;
	static ArrayList<Integer>[] path;
	static ArrayList<CowPath> list;
	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );        	
		 StringTokenizer st = new StringTokenizer( br.readLine() ) ;
		     
		 K = Integer.parseInt(st.nextToken());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 startNo = new int[K+1];
		 for(int i=1;i<=K;i++){
			 startNo[i] = Integer.parseInt(br.readLine());
		 }
		 path = new ArrayList[N+1];
		 list = new ArrayList<>();
		 boolean [] visit = new boolean[N+1];
		 list.add(new CowPath(0, visit));
		 for(int i=1;i<=N;i++){
        	path[i] = new ArrayList<>();
        	boolean [] tmp = new boolean[N+1];
        	list.add(new CowPath(0,tmp));
		 }
         for(int i=1;i<=M;i++) {
        	st = new StringTokenizer(br.readLine()) ;
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	path[s].add(e);
         }	
         int answer = 0;
         for(int i=1;i<=K;i++){
         	//bfs(i);
         	dfs(i,startNo[i]);
         }
         for(int i=1;i<=N;i++){
         	if(list.get(i).cowCnt==K){
         		answer++;
         	}
         }
         System.out.println(answer);
                  
	}
	private static void dfs(int k, int n) {
		if(!list.get(n).cowVisit[k]){
			list.get(n).cowVisit[k] = true;
			list.get(n).cowCnt++;
		}
		for(int x:path[n]){
			if(!list.get(x).cowVisit[k]){
				list.get(x).cowVisit[k] = true;
				list.get(x).cowCnt++;
				dfs(k,x);
			}
			
		}
	}
	
//	public static void main(String[] args) {
//	
//}
	
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