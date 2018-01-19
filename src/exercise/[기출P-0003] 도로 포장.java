import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M;
	static StringTokenizer st;
	static ArrayList<Edge> Edges;
	static int[] par;
	static int NodeCount, MaxValue, MinValue;
	static int ans;
	
	private static class Edge {
		int s, e, v;
		
		public Edge(int s, int e, int v, int r) {
			this.s = s;
			this.e = e;
			this.v = v;
		}
	}
	
	static int find (int x) {
		if(par[x]==x) return x;
		return par[x] = find(par[x]);
	}
	
	static void union (int a, int b) {
		a = find(a); b = find(b);
		
		if(a==b) return;
		par[b] = a;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			Edges = new ArrayList<>();
			
			for (int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				Edges.add(new Edge(s, e, v, M));
			}
		
			Collections.sort(Edges, new Comparator<Edge>() {
				public int compare(Edge a, Edge b) {
					return a.v - b.v;
				}
			});

			par = new int[N+1];
			for (int p=1; p<=N; p++) par[p] = p;
			
			ans = Integer.MAX_VALUE;
			int i, j, k;
			for (i=0; i<=M-1; i++) {
				for (int p=1; p<=N; p++) par[p] = p;
				NodeCount = 0;
				for (j=i; j<=M-1; j++) {
					int a = Edges.get(j).s;
					int b = Edges.get(j).e;

					if(find(a)==find(b)) continue;
					else {
						union(a, b);
						NodeCount++;
					}
					
					if (NodeCount==N-1) {
						MaxValue = Edges.get(j).v;
						break;
					}
				}

				if (NodeCount!=N-1) break;
				
				for (int p=1; p<=N; p++) par[p] = p;
				NodeCount = 0;
				for (k=j; k>=0; k--) {
					int a = Edges.get(k).s;
					int b = Edges.get(k).e;

					if(find(a)==find(b)) continue;
					else {
						union(a, b);
						NodeCount++;
					}
					
					if (NodeCount==N-1) {
						MinValue = Edges.get(k).v;
						break;
					}
				}
				
				ans = Math.min(ans, MaxValue-MinValue);
				i = k;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}

/*
[입출력 예]
(입력)
2
5
7
1 2 9
1 5 5
2 5 3
2 3 8
2 4 2
3 4 9
4 5 6
6
9
1 2 8
1 4 9
2 4 1
2 5 5
2 3 6
2 6 2
3 6 7
4 5 3
5 6 4

(출력)
#1 3
#2 4
*/