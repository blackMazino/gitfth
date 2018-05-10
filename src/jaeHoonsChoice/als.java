package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class als {  // Assembly Line Scheduling

	static int n, e1, e2, x1, x2;
	static int S[][], T[][], D[][];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("sample/als.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		e1 = Integer.parseInt(st.nextToken());
		e2 = Integer.parseInt(st.nextToken());
		x1 = Integer.parseInt(st.nextToken());
		x2 = Integer.parseInt(st.nextToken());
		
		S = new int[3][n+1];  // 1,2를 쓰기 위하여 3개
		T = new int[3][n];
		
		// S1
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) S[1][i] = Integer.parseInt(st.nextToken());
		
		// S2
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) S[2][i] = Integer.parseInt(st.nextToken());
		
		// T1
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n; i++) T[1][i] = Integer.parseInt(st.nextToken());
		
		// T2
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n; i++) T[2][i] = Integer.parseInt(st.nextToken());
				
		// 초기값 지정
		D = new int[3][n+1];
		D[1][1] = e1 + S[1][1];  // 시작점
		D[2][1] = e2 + S[2][1];
		
		// DP
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= 2; j++) {
				// 바로 옆에서 온 최소값과, T를 통해서 온 최소값 비교하여 작은 값을 저장한다.
				// 3에서 j를 빼는 이유는 S1에서 시작, S2에서 시작하는 부분을 단순화하기 위한 코딩스킬
				D[j][i] = Math.min(D[j][i-1] + S[j][i], D[3-j][i-1] + T[3-j][i-1] + S[j][i]);
			}
		}
		
		bw.write(Math.min(D[1][n] + x1, D[2][n] + x2) + "\n");  // S1에서 끝나는지, S2에서 끝나는지 확인(min값)
		bw.flush();
	}

}
