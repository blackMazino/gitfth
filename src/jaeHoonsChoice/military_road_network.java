package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Road {
	int a, b, c;

	public Road(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}

public class military_road_network {
	
	static int T, N, M, K;
	static ArrayList<Road> road;
	static long solve = 0;
	static int par[];
		
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("sample/military_road_network.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 도시 번호
			M = Integer.parseInt(st.nextToken());  // 기존 도로 정보 수
			K = Integer.parseInt(st.nextToken());  // 건설 가능 도로 정보 수
			
			solve = 0;
			road = new ArrayList<>();
						
			// 기존 도로 정보
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());  // 도시번호 1
				int b = Integer.parseInt(st.nextToken());  // 도시번호 2
				int c = Integer.parseInt(st.nextToken());  // 취소비용
				
				road.add(new Road(a, b, -c));  // 취소비용을 미리 (-) 값으로 넣어놓는다.
				solve += c;  // 취소 비용을 다시 건설하는 경우 상쇄되므로 미리 solve에 넣어둔다.
			}
			
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());  // 도시번호 1
				int b = Integer.parseInt(st.nextToken());  // 도시번호 2
				int c = Integer.parseInt(st.nextToken());  // 건설비용
				
				road.add(new Road(a, b, c));
			}
			
			// MST 이용하여 확인
			// 크루스칼 알고리즘 이용
			// 비용으로 sorting - 오름차순
			Collections.sort(road, new Comparator<Road>() {

				@Override
				public int compare(Road A, Road B) {
					// TODO Auto-generated method stub
					return A.c - B.c;
				}
			});
			
			// Union-Find
			par = new int[N+1];
			for (int i = 1; i <= N; i++) par[i] = i;  // Union-Find 부모노드 초기화
			
			// Arraylist의 값을 확인하여, 같은 그룹인 경우(Union) 넘어가고,
			// 그렇지 않은 경우에는 추가
			for (int i = 0; i < road.size(); i++) {
				Road r = road.get(i);
				int a = find(r.a);
				int b = find(r.b);
				
				// Union 처리
				if (find(a) != find(b)) {
					Union(a,b);
					solve += r.c;
				}
			}
			
			bw.write("#" + tc + " " + solve + "\n");
			bw.flush();
		}
		br.close();
		bw.close();
	}

	private static void Union(int a, int b) {
		// TODO Auto-generated method stub
		par[a] = b;
	}

	private static int find(int x) {
		// TODO Auto-generated method stub
		if (par[x] == x) return x;
		return par[x] = find(par[x]);
	}

}
