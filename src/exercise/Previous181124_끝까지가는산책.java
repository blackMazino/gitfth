package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Previous181124_끝까지가는산책 {

/*
2
5 5
1 2 3
1 3 2
2 4 1
3 4 1
4 5 1
6 6
1 2 2
1 3 2
2 4 1
3 4 1
4 5 1
2 6 4

#1 2
#2 3
*/	
	static int TC,N,M;
	static int mod = 1000000007;
	static ArrayList<String> endList;
	static int [] endArr;
	static ArrayList<Integer>[] con;
	static ArrayList<Integer>[] conW;
	static long d[];
	static int traceN[][];
	static int traceE[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous05.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			con = new ArrayList[N+1];
			conW = new ArrayList[N+1];		
			endArr = new int [N+1];
			endList = new ArrayList<>();
			
			for(int i=1;i<=N;i++){
				con[i]=new ArrayList<>();
				conW[i]=new ArrayList<>();
				endList.add(String.valueOf(i));
			}
			
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				con[a].add(b); con[b].add(a);
				endArr[a]++; endArr[b]++;
				conW[a].add(c); conW[b].add(c);
				if(endArr[a]>1 && endList.contains(String.valueOf(a))){					
					endList.remove(String.valueOf(a));
				}
				if(endArr[b]>1 && endList.contains(String.valueOf(b))){
					endList.remove(String.valueOf(b));
				}
			}
			
			d  = new long[N+1];
			traceN = new int[N+1][N+1];//순서대로 지나온 Node를 저장
//			traceE = new int[M+1];//순서대로 지나온 Edge를 저장
			for(String e0 : endList){
				int e = Integer.parseInt(e0);
				dijkstra(e);
				ArrayList<Integer> print = new ArrayList<>();
				for(int i=e;i!=1;i=traceN[i][e]){
					print.add(i);
				}
				print.add(1);
				Collections.reverse(print);
				System.out.println("Endpoint E : "+e);
				for(int n : print){
					System.out.print(n+ " ");	
				}				
			}
			
		}	
	}
	private static void dijkstra(int e) {
		Arrays.fill(d, Long.MAX_VALUE);
		d[1]=1;
		PriorityQueue<Walk> pQ = new PriorityQueue<>();
		pQ.add(new Walk(d[1],1));
		traceN[1][e] = 1;
		while(!pQ.isEmpty()){
			Walk w = pQ.poll();
			long dis = w.d;
			int v = w.v;	
//			if(v == e && d[v]<Long.MAX_VALUE) break;
			for(int i=0;i<con[v].size();i++){
				int nV = con[v].get(i);
				int nD = conW[v].get(i);
				if(d[nV] > d[v]+nD){
					d[nV] = d[v]+nD;					
					pQ.add(new Walk(d[nV], nV));
					traceN[nV][e] = v;
				}
			}
		}
	}

}
class Walk implements Comparable<Walk>{
	public Walk(long d, int v) {
		super();
		this.d = d;
		this.v = v;
	}
	long d;
	int v;
	@Override
	public int compareTo(Walk o) {
		// TODO Auto-generated method stub
		return Long.compare(d, o.d);
	}
}
class EndPoint{
	public EndPoint(int v, int cnt) {
		super();
		this.v = v;
		this.cnt = cnt;
	}

	int v,cnt;
}