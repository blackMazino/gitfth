package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class alliance2 {

	static int N, Q, par[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("sample/alliance.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Q = Integer.parseInt(br.readLine());
		par = new int[N+1];
		for (int i = 1; i <= N; i++) par[i] = i;  // 부모노드 초기화
		
		// Query 확인
		for (int i = 1; i <= Q; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (q == 0) {  // 동맹을 맺었다!!
				if (find(a) != find(b)) {  // a와 b의 대표노드가 같지 않으면(동맹이 아니면)
					union(a, b); // 대표노드를 같게 만든다.
				} 
			} else {  // 동맹인가??
				if (find(a) == find(b)) {
					bw.write("1\n");
				} else {
					bw.write("0\n");
				}
			}
		}
		bw.flush();
	}

	private static void union(int a, int b) {
		// TODO Auto-generated method stub
		if(find(a) == find(b)) return;
		par[find(a)] = find(b);
	}

	private static int find(int x) {
		// TODO Auto-generated method stub
		if (par[x] == x) return x;  // 대표노드이면 바로 return한다.
		par[x] = find(par[x]);  // 경로압축(x의 대표노드를 찾아서 업데이트)
		return par[x];  // 대표노드를 return
	}
}
