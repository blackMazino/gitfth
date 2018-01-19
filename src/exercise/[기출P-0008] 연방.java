import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Solution {
	static int T,N,M,Q;
	static int[][] Edge;
	static int[] p;
	static ArrayList<Integer> cut;
	static ArrayList<int[]> que;
	
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.ini"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //나라의수
			M = Integer.parseInt(st.nextToken()); //간선의수
			
			p = new int[N+1];
			for(int i=1; i<=N; i++)
				p[i] = i;
			
			Edge = new int[M+1][3];
			for(int i=1; i<=M; i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				Edge[i][0] = Integer.parseInt(st1.nextToken());
				Edge[i][1] = Integer.parseInt(st1.nextToken());
				Edge[i][2] = 1;
			}
			
			Q = Integer.parseInt(br.readLine());
			cut = new ArrayList<>();
			que = new ArrayList<>();
			int kind;
			for(int i=1; i<=Q; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				kind = Integer.parseInt(st2.nextToken());
				
				if ( kind == 1) {
					int v = Integer.parseInt(st2.nextToken());
					Edge[v][2] = 0;
					que.add(new int[]{-1,v});
				}
				else if ( kind == 2 ) {
					int v1 = Integer.parseInt(st2.nextToken());
					int v2 = Integer.parseInt(st2.nextToken());
					que.add(new int[]{Math.min(v1, v2), Math.max(v1, v2) });
				}
			}
			
			for(int i=1; i<=M; i++) {
				if ( Edge[i][2] == 1 ) {
					merge(Edge[i][0],Edge[i][1]);
				}
			}
			//print();
			
			StringBuilder sb = new StringBuilder();
			for(int i = que.size()-1; i>=0; i--) {
				if ( que.get(i)[0] == -1 ) {
					int n1 = Edge[que.get(i)[1]][0];
					int n2 = Edge[que.get(i)[1]][1];
					merge(n1,n2);
					//print();
				}
				else {
					int n1 = que.get(i)[0];
					int n2 = que.get(i)[1];
					
					if ( find(n1) == find(n2) ) {
						sb.append(Integer.toString(1));
					}
					else
						sb.append(Integer.toString(0));
					
				}
			}
			sb.reverse();
			System.out.println("#"+tc+" "+sb.toString());
			
		}
		br.close();
	}
	
	public static int find(int z) {
		if(p[z]==z) return p[z];
		p[z] = find(p[z]);
		return p[z];
	}
	
	public static void merge(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py) return;
		
		p[py] = px;
		
	}
	
	public static void print() {
		for(int i=1; i<=N; i++) {
			System.out.print(p[i]+ " ");
		}
		System.out.println();
	}
	

}