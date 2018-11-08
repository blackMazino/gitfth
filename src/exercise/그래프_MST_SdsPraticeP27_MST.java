package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 그래프_MST_SdsPraticeP27_MST {

/*
(중상) [연습P-0027] MST
노드가 N개 있는 완전 그래프가 주어져 있다. 
완전 그래프란 모든 가능한 쌍의 노드들 간에 에지가 존재하는 그래프를 말한다. 
그래프의 각 에지는 길이가 있으며, 에지들 간에 길이는 서로 다를 수 있다. 
이 그래프의 최소 신장 트리(MST)를 구했다고 하자. 
어떤 그래프의 MST는 그 그래프의 에지들 중 일부를 골라서 전체가 트리가 되며 에지들의 길이 합이 최소가 되는 경우를 말한다. 
주어진 그래프의 MST는 유일하지 않을 수 있다는 점에 주의하라.
 
주어진 MST의 에지 길이 합을 X라고 하자. 
가능한 어떤 MST에도 속하지 않는 에지 e에 대해서 이 에지 e가 MST에 포함되기 위해서 제거되어야 하는 에지를 생각할 수 있다. 
에지 e가 MST에 포함되기 위하여 제거되어야 하는 에지를 모두 제거하고 다시 MST를 구했다고 하자. 
즉, 이 새로운 MST는 e를 포함할 것이다. 이 새로운 MST의 에지 길이 합을 Y(e)라고 하자. 
가능한 어떤 MST에도 속하지 않는 에지 e 각각에 대해서 Y(e)-X를 모두 계산했다고 했을 때, 
Y(e)-X의 가능한 최소값을 계산하라.
 
[입력]
입력 파일에는 여러 테스트 케이스가 포함될 수 있다. 파일의 첫째 줄에 케이스의 개수 T가 주어지고, 
이후 차례로 T개 테스트 케이스가 주어진다. (1 ≤ T ≤ 50) 
각 케이스의 첫 줄에는 노드의 개수 N이 주어진다. (2 ≤ N ≤ 300) 
다음 N-1개의 줄에 에지들의 길이가 아래의 형식으로 주어진다. 
d[I, j]는 i번 노드와 j번 노드를 잇는 에지의 길이를 의미하여, 에지의 길이는 100,000 이하의 자연수이다.
 
d[1, 2] d[1, 3] … d[1, N]
d[2, 3] d[2, 4] … d[2, N]
…
d[N-1, N]
 
[출력]
각 테스트 케이스의 답을 순서대로 표준출력으로 출력하며, 각 케이스마다 줄의 시작에 “#x”를 출력하여야 한다. 
이때 x는 케이스의 번호이다. 같은 줄에, 가능한 Y(e)-X의 최소값을 출력한다. 
Y(e)-X의 값을 계산할 수 있는 에지가 존재하지 않는 경우 -1을 출력해야 한다.

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
	static int TC, N;
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
				int s = Integer.parseInt(st.nextToken());
				int c = 0;				
				for(int m=n+1;m<=N;m++){
					c = Integer.parseInt(st.nextToken());
					list.add(new Mst(s,m,c));
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
			
			for(Mst mst:list){
				int s=find(mst.s);
				int e=find(mst.e);
				if(find(s)!=find(e)){
					union(s,e);
					X +=mst.c;			
					mst.mst = true;
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