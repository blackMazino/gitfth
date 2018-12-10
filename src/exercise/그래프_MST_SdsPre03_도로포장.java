package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 그래프_MST_SdsPre03_도로포장 {
/*
(입력)
2         ← 총 2개의 테스트 케이스가 있다는 뜻
5          ← 1번 케이스의 시작줄
7
1 2 9
1 5 5
2 5 3
2 3 8
2 4 2
3 4 9
4 5 6
6          ← 2번 케이스의 시작줄
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
	static int TC,N,M;// 2<=N<=350, 1<=M<=N(N-1)/2
	static int min, max,answer;
	//Union-find
	static int par[];
	static ArrayList<R> list;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous03.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			par = new int[N+1];
			list = new ArrayList<>();
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new R(a,b,c));
			}
			Collections.sort(list, new Comparator<R>(){

				@Override
				public int compare(R o1, R o2) {
					// TODO Auto-generated method stub
					return o1.c-o2.c;
				}
				
			});
			
			for(int i=1;i<=N;i++) par[i] = i;
			answer = Integer.MAX_VALUE;
			int cnt = 0;
			for(int m=0;m<M;m++){
				int j,k;
				
				//initialize
				for(int i=1;i<=N;i++) par[i] = i;
				cnt=0;
				
				//max
				for(j=m;j<M;j++){
					int a = list.get(j).s;
					int b = list.get(j).e;
					if(find(a)==find(b)){
						continue;						
					}else{
						union(a,b);
						cnt++;
					}
					if(cnt==N-1){
						max = list.get(j).c;
						break;
					}
				}
				if(cnt!=N-1) break;
				
				//initialize
				for(int i=1;i<=N;i++) par[i] = i;
				cnt=0;
				
				//min
				for(k=j;k>=0;k--){
					int a = list.get(k).s;
					int b = list.get(k).e;
					if(find(a)==find(b)){
						continue;						
					}else{
						union(a,b);
						cnt++;
					}
					if(cnt==N-1){
						min = list.get(k).c;
						break;
					}
				}
				
				answer = Math.min(answer, max-min);
				m=k;
			}
			
			System.out.println("#"+tc+" "+answer);
			
		}
	}
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return;
		par[pb] = pa;
		
	}
	private static int find(int a) {
		if(par[a]==a) return a;
		else return find(par[a]);
	}

}
class R{
	int s, e, c;

	public R(int s, int e, int c) {
		super();
		this.s = s;
		this.e = e;
		this.c = c;
	}
}
