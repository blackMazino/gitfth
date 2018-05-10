package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class sliding_windows {
	
	static int N, K, sw[];
	static Deque<Integer> dq_max, dq_min;
	static long sum;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("sample/sliding_windows.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		sw = new int[N+1];  // 배열 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) sw[i] = Integer.parseInt(st.nextToken());
		
		//deque로 입력받으면서 계산하기
		dq_max = new ArrayDeque<>();  // 최대값용 deque
		dq_min = new ArrayDeque<>();  // 최소값용 deque
		
		for (int i = 1; i <= N; i++) {
			// 최대값
			while (!dq_max.isEmpty() && sw[dq_max.getLast()] <= sw[i]) dq_max.pollLast();
			dq_max.addLast(i);
			
			// 최소값
			while (!dq_min.isEmpty() && sw[dq_min.getLast()] >= sw[i]) dq_min.pollLast();
			dq_min.addLast(i);
			
			sum += sw[i];
			if (i >= K) {
				sum -= sw[i-K];  // K이상일 때 출력할 합산값 찾기
				while (dq_max.getFirst() <= i-K) dq_max.pollFirst();  // 출력하기 전에 구간에 속해있는 값만 있도록 빼주기
				while (dq_min.getFirst() <= i-K) dq_min.pollFirst();
				
				bw.write(sw[dq_min.getFirst()] + " " + sw[dq_max.getFirst()] + " " + sum + "\n");
			}
		}

		// 결과 출력
		bw.flush();
	}

}
