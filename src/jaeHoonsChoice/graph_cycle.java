package jaeHoonsChoice;

import java.io.*;
import java.util.*;

public class graph_cycle {

	static int V, E, S;
	static ArrayList<Integer> list[], dfs_list, bfs_list;
	static boolean visit[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("sample/graph_cycle_sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) list[i] = new ArrayList<>();  // 리스트 초기화
		
		// 간선의 개수만큼 인접 리스트에 넣기
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);  // 방향이 없으므로 양쪽 다 넣는다.
			list[b].add(a);
		}
		
		// 인접 리스트 정렬(각 리스트에 있는 내역 순서대로 정렬한다.)
		// 문제에서 오름차순으로 가장 빠른것을 출력하라고 되어 있어서 정렬한다.
		for (int i = 1; i <= V; i++) Collections.sort(list[i]);
		
		// dfs
		visit = new boolean[V+1];
		dfs_list = new ArrayList<>();
		dfs(S);  // 깊이 우선 탐색 확인
		
		// bfs
		visit = new boolean[V+1];
		bfs_list = new ArrayList<>();
		bfs(S);  // 넓이 우선 탐색 확인
		
		for (int i: dfs_list) bw.write(i + " ");
		bw.write("\n");
		for (int i: bfs_list) bw.write(i + " ");
		bw.flush();
	}

	private static void bfs(int S) {
		// TODO Auto-generated method stub
		Queue<Integer> que = new LinkedList<>();  // 큐에서 확인
		que.add(S);  // 처음 시작점 지정
		visit[S] = true;  // 방문 체크
		
		while (!que.isEmpty()) {  // 큐에 계속 쌓아가면서 확인(끝날때까지)
			int q = que.poll();  // 큐에서 하나를 빼서
			bfs_list.add(q);  // 출력할 리스트에 넣는다.
			
			for (int t: list[q]) {  // 해당 내역에서 시작하여 리스트를 확인
				if(!visit[t]) {  // 방문되지 않았을 경우
					visit[t] = true;  // 방문체크하고 큐에 넣는다.(끝까지 간 뒤에 다음으로 넘어감)
					que.add(t);
				}
			}
		}
	}

	private static void dfs(int S) {
		// TODO Auto-generated method stub
		dfs_list.add(S);  // 출력할 리스트에 첫번째 번호 add
		visit[S] = true;  // 방문 체크
		
		for(int t: list[S]) {  // 리스트 내의 다음 내역을 확인한다.
			if (!visit[t]) {  // 방문체크되지 않았을 경우 재귀호출
				dfs(t);
			}
		}
	}

}
