package exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
[입출력예제]
입력
5
4 3
12 11 14 17 
3 4
3 1
3 2
4 3
6 13 19 10 
1 4
1 2
4 3
6 5
7 7 10 6 6 3 
5 1
3 6
3 5
6 4
6 2
8 7
12 10 16 2 9 9 16 17 
1 8
3 5
4 2
8 6
6 3
3 7
8 4
7 6
4 7 1 7 19 17 4 
6 2
1 7
1 5
4 1
4 6
7 3

출력
#1 17
#2 32
#3 14
#4 38
#5 37

*/
public class 그래프_BFS_SdsPracticeA30_겨울난방 {
	static int TC,N,M, w[],wN[],answer, childCnt[], parent[];//2<=N<=100,000, 1<=M<=100,000, 1<=W<=1,000,000
	static ArrayList<Integer> con[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Practice30.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			con = new ArrayList[N+1];			
			w = new int[N+1];
			wN = new int[N+1];
			childCnt = new int[N+1];
			parent = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++){
				con[n] = new ArrayList<>();
				int a = Integer.parseInt(st.nextToken());
				w[n] = a;
			}			
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				con[a].add(b); con[b].add(a);
				childCnt[a]++;			
				parent[b] = a;
			}
			
			childCnt[0] = 1;//root 노드가 마지막에 남을 경우 parent[root]==0 childCnt[0] 이 1이어야 bfs 한번 더 돌아 root의 남은 필요시간을 더해줄수 있다
			LinkedList<Integer> que = new LinkedList<>();
			for(int i=1;i<=N;i++){
				if(childCnt[i]==0) que.add(i);
			}
				
			answer = 0;
			while(!que.isEmpty()){
				int q = que.removeFirst();
				int p = parent[q];
				answer += wN[q];
				w[q] = Math.max(w[q]-wN[q],0);
				w[p] = Math.max(w[p]-wN[q],0);
//				if(q==0) break;
				wN[p] = Math.max(wN[p], w[q]);
				childCnt[p]--;
				if(childCnt[p]==0) que.addLast(p);			
			}
			System.out.println("#"+tc+" "+answer);
		}
	}

}
