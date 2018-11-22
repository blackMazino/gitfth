package old;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그래프_LCA_KOITP_상인 {
/*
사막에 N(1≤N≤100,000)개의 도시가 있고, 각 도시에는 1부터 N까지의 번호가 매겨져 있다. 
두 도시를 연결하는 N−1개의 길이 있으며, 임의의 두 도시 사이에는 길들을 따라 이동할 수 있는 경로가 하나만 존재한다.
어떤 사람이 하나의 길을 지나는 데는 정확히 하루가 걸리며, 길 이외의 장소에서는 마실 물을 구할 수 없어 이동할 수 없다.
한 상인이 1번 도시부터 N번 도시까지를 순서대로 방문하며 장사를 하려고 한다. 
이 상인이1번 도시부터 N번 도시까지 순서대로 방문하는데 며칠이 걸리는지를 계산하여 출력하시오.

첫 줄에 도시의 수 N(1≤N≤100,000)이 주어진다.
두 번째 줄부터N번째 줄까지는 각각의 줄에 하나의 길의 정보가 주어진다. 
하나의 줄에는 두 개의 서로 다른1 이상N 이하의 정수가 주어지며, 이는 이 두 도시를 연결하는 길이 존재한다는 의미이다.


상인이 1번 도시에서 출발하여 2번 도시,...,N−1번 도시를 순서대로 거쳐 N번 도시에 도착하기까지 며칠이 결리는지를 출력한다.
5
3 1
2 4
3 5
1 4
===========
10

20
1 8
8 9
8 10
1 7
7 14
7 6
6 5
6 4
5 11
5 12
12 2
12 17
4 3
3 16
1 15
15 18
18 13
13 19
13 20
====
66
*/
	static int N;
	static int M = 16;
	static ArrayList<Integer>[] con;
	static int [] depth;
	static int [][] parent;
	static long answer;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream(""));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		con = new ArrayList[N+1];
		depth = new int [N+1];
		parent = new int[M+1][N+1];
//		Arrays.fill(parent, -1);
		for(int i=1;i<=N;i++) con[i] = new ArrayList<>();
		
		for(int i=1; i<N;i++){
			st = new StringTokenizer(br.readLine());			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());			
			con[a].add(b);
			con[b].add(a);			
		}
		dfs(1,1,0);
		//Sparse Table 
		for(int k=0;k<M;k++){
			for(int i=1;i<=N;i++){
				parent[k+1][i] = parent[k][ parent[k][i] ];
			}	
		}
		
		
		answer = 0;
		for(int i=1;i<N;i++){
			answer += getMovingDays(i,i+1);//	
//			System.out.println(i+" : "+answer);
		}
		bw.write(answer+"\n");
		bw.flush();
		bw.close();
		br.close();

	}

	private static int getMovingDays(int s, int e) {
		int result = depth[e]-depth[s];
		if(s>1){
			int ca = lca2(s,e);
			result = (depth[s]-depth[ca]) + (depth[e]-depth[ca]);			
		}
		return result;
	}
	//a기준
	private static int lca2(int a, int b) {
		if(depth[a] < depth[b]) return lca2(b,a);
        int d = depth[a] - depth[b];
//        for(int i = 0; i < M; ++i){
//        	//연산순서 : 쉬프트연산 > 비트연산
//        	// << :  * (2^i)
//        	// >> :  / (2^i)
//        	// &  : 논리곱 각 비트가 둘다 1이어야 1 나머지는 0
//            if((d & 1 << i) > 0){
//                a = parent[i][a];
//            }
//        }
        int k=0;
        while(d>0){
	      	if(d%2==1){
	    			a = parent[k][a];
    		}
    		d /= 2;
        	k++;        
        }
        
        if(a == b) return a;
        for(int i = M; i >= 0; i--){
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }
		return parent[0][a];
	}

	private static void dfs(int cur, int d, int p) {
		depth[cur]=d;
		parent[0][cur]=p;//0은의 2^0을 말할때 0이다
		for(int next : con[cur]){
			if(next != p){
				dfs(next, d+1, cur);
			}
		}
		
	}

}
