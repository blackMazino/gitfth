import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution{
	static int T,N,M;
	static ArrayList<Integer>[] con,conv;
	static int[] D;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			D=new int[N+1];
			con=new ArrayList[N+1];
			conv=new ArrayList[N+1];
			
			for(int i=1;i<=N;i++) {
				con[i]=new ArrayList<>();
				conv[i]=new ArrayList<>();
			}
			
			for(int i=1;i<=M;i++) {
				st=new StringTokenizer(br.readLine());
				
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				
				con[a].add(b); con[b].add(a);
				conv[a].add(c); conv[b].add(c);
			}
			
			for(int i=1;i<=N;i++) D[i]=Integer.MAX_VALUE;
			
			PriorityQueue<int[]> que=new PriorityQueue<>(10,new Comparator<int[]>() {
				public int compare(int[] a,int[] b) {
					return a[1]-b[1];
				}
			});
			
			D[1]=0;
			
			que.add(new int[]{1,0});
			
			while(!que.isEmpty()) {
				int q=que.peek()[0];
				int d=que.peek()[1];
				
				que.poll();
				
				if(D[q]!=d) continue;

				for(int i=0;i<con[q].size();i++) {
					int t=con[q].get(i);
					int v=conv[q].get(i);
					
					if (D[t]>D[q]+v) {
						D[t]=D[q]+v;
						que.add(new int[]{t,D[t]});
					}
				}
			}
			
			System.out.println("#"+tc+" "+(D[N]<Integer.MAX_VALUE? D[N]:-1));

		}
	}
}

/*

2
3 3
1 2 3
2 3 1
1 3 2
6 9
1 2 2
1 3 4
2 3 1
2 5 2
2 4 4
3 5 3
4 5 3
4 6 2
5 6 2

(Ãâ·Â)
#1 2
#2 6

*/
