package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Practice27_MST {

/*

[입출력 예]
(입력)
3
3
1 1
1
3
1 1
2
4
1 3 8
1 2
2
 
(출력)
#1 -1
#2 1
#3 2
 
(sample_input에 대한 출력)
#1 6859
#2 1758
#3 67352
#4 23418
#5 48317
#6 68310
#7 96414
#8 -1
#9 37725
#10 65621

 */
	static int TC, N;//2 ≤ N ≤ 300, edge : N-1
	static int [] par;
	static long X;
	static ArrayList<Mst> list;
	static boolean isUnique;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			N = Integer.parseInt(br.readLine());
			for(int n=1;n<N;n++){
				st = new StringTokenizer(br.readLine());
//				int s = Integer.parseInt(st.nextToken());
				int c = 0;				
				for(int m=n+1;m<=N;m++){
					c = Integer.parseInt(st.nextToken());
					list.add(new Mst(n,m,c));
				}
			}
			Collections.sort(list, new Comparator<Mst>(){

				@Override
				public int compare(Mst o1, Mst o2) {
					// TODO Auto-generated method stub
					return o1.c-o2.c;//desc
				}
				
			});
			par = new int[N+1];
			for(int i=1;i<=N;i++) par[i]=i;
			
			int mstCnt = N-1;
			for(Mst mst:list){
				int s=find(mst.s);
				int e=find(mst.e);
				if(find(s)!=find(e)){
					union(s,e);
					X +=mst.c;			
					mst.mst = true;
					mstCnt--;
				}
			}
			
			
		}
	}

	private static void union(int s, int e) {
		par[s] = e;		
	}

	private static int find(int s) {
		if(par[s]==s) return s;
		return par[s] = find(par[s]);
	}

}
class Mst{
	public Mst(int s, int e, int c) {
		super();
		this.s = s;
		this.e = e;
		this.c = c;
		mst = false;
	}
	int s;
	int e;
	int c;
	boolean mst;
}