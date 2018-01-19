import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Edge{
	public Edge(int a,int b,int c) {
		this.a=a; this.b=b; this.c=c;
	}
	
	int a,b,c;
}

public class Solution{
	static int T,N,M,K;
	static long Answer;
	static int[] par;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			par=new int[N+1];
			for(int i=1;i<=N;i++) par[i]=i;
			
			ArrayList<Edge> edges=new ArrayList<>();
			
			Answer=0;
			for(int i=1;i<=M;i++) {
				st=new StringTokenizer(br.readLine());
				
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				
				edges.add(new Edge(a,b,-c));
				
				Answer+=c;
			}
			
			for(int i=1;i<=K;i++) {
				st=new StringTokenizer(br.readLine());
				
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				
				edges.add(new Edge(a, b, c));
			}
			
			Collections.sort(edges, new Comparator<Edge>() {
				public int compare(Edge a, Edge b) {
					return a.c-b.c;
				}
			});
			
			for(Edge e:edges) {
				int a=find(e.a);
				int b=find(e.b);
				
				if (a==b) continue;
				
				par[b]=a;
				Answer+=e.c;
			}
			
			bw.write("#"+tc+" "+Answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int find(int n) {
		if (par[n]==n) return n;
		else return par[n]=find(par[n]);
	}
}


/*

1
10 5 5
1 8 23
8 4 8
4 5 23
5 2 15
2 6 9
6 9 26
9 10 2
10 3 24
3 7 17
7 1 19



(Ãâ·Â)
#1 62

*/