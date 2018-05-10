package jaeHoonsChoice;

import java.io.*;
import java.util.*;

public class lca_test {

	static int N, M, par[][], dep[];
	static ArrayList<Integer> city[];
	static Queue<Integer> que;
	static long solve;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//BufferedReader br = new BufferedReader(new FileReader("sample/merchant_sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		// rooted tree 만들기
		city = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			city[i] = new ArrayList<>();  // 각 ArrayList 초기화
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 방향이 없으므로 양쪽으로 연결시키기
			city[a].add(b);
			city[b].add(a);
		}
		
		dep = new int[N+1];  // 각 도시의 depth
		par = new int[N+1][17];  // 각 도시의 부모값 : i의 2^j번째 부모
		que = new LinkedList<>();
		que.add(1);
		while (!que.isEmpty()) {
			int q = que.poll();
			for (int t: city[q]) {  // city ArrayList를 돌면서 트리를 구성하고, Depth를 구한다.
				// 바로 위 부모 지정
				if(par[q][0] != t) {  // 부모가 자신이 아니면
					par[t][0] = q;  // 부모는 q
					dep[t] = dep[q] + 1;  // q보다 한단계 아래로 지정
					que.add(t);
				}
			}
		}
		
		// 각 city의 부모값 지정
		for (int i = 1; i < 17; i++) {  // 2의 17제곱보다 아래로 확인
			for (int j = 1; j <= N; j++) {  // 각 city의 개수만큼 확인
				par[j][i] = par[par[j][i-1]][i-1];  // j의 i번째 조상을 지정
			}
		}
		
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
            
            bw.write(lca(s, e) + "\n");
		}
		
		bw.flush();
	}

	private static int lca(int a, int b) {
		// TODO Auto-generated method stub
		if (dep[a] < dep[b]) return lca(b, a);  // a가 더 깊은 기준으로 확인한다.
		
		for (int i = 0; i < 17; i++) {
			if (((dep[b] - dep[a]) & (1<<i)) > 0) {  // 깊이의 차이만큼 2의 제곱으로 이동한다
				a = par[a][i];  // a를 올려준다
			}
		}
		
		if (a == b) {  // 깊이가 같으면
			return a;  // 해당 깊이를 return
		}
		
		for (int i = 16; i >= 0; i--) {  // 아래에서부터 올라가면서 부모를 return한다. 배열이 17개이므로 16부터 시작
			if (par[a][i] != par[b][i]) {
				a = par[a][i];
				b = par[b][i];
			}
		}
		
		return par[a][0];  // a의 부모를 return
	}
}
