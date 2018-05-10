package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class rod {
	
	static int N, cost[], D[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("sample/rod.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		// DP 세팅 : 길이가 i인 막대기를 잘랐을 때 cost의 최대값
		D = new int[N+1];
		D[0] = 0;  // 0인 막대기는 cost가 0
		D[1] = cost[1];  // 1인 막대기는 초기값이므로 cost[1] 넣는다.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				// 각 막대기의 값과 나머지 막대기의 최대값을 더하여, 그 최대값을 더한다
				D[i] = Math.max(D[i], cost[j] + D[i-j]);  // 각 막대기의 값이 모두 다르기 때문에 for문 두번 사용
			}
		}
		
		bw.write(D[N] + "\n");  // 마지막 막대기의 최대값이 원하는 정답
		bw.flush();
	}

}
