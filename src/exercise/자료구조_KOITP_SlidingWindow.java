package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 자료구조_KOITP_SlidingWindow {
	/*
	길이가 N인 정수 배열이 있다. 이 배열의 부분배열 중 크기가 K인 부분배열은 총 N-K+1개 있다. 
	이 부분배열에 대해 부분배열에 속한 최대값, 최소값, 합을 구하는 프로그램을 작성하자.
	예를 들어, 배열 [1, 3, -1, -3, 5, 3, 6, 7]이 있고 K=3이라 하자. 
	이 배열에 크기가 3인 6개의 부분배열들이 있고, 왼쪽에 있는 부분배열부터 순서대로 나열하면 다음과 같다:
	 [1, 3, -1], [3, -1, -3], [-1, -3, 5], [-3, 5, 3], [5, 3, 6], [3, 6, 7]. 
	 각 부분배열의 최소값은 순서대로 -1, -3, -3, -3, 3, 3이고, 
	 최대값은 순서대로 3, 3, 5, 5, 6, 7이며, 
	 부분배열의 합은 순서대로 3, -1, 1, 5, 14, 16이다.
	 
	첫 줄에 배열의 크기 N과 부분배열의 크기 K가 주어진다. (1 ≤ K ≤ N ≤ 1,000,000)
	둘째 줄에 배열의 내용을 나타내는 N개의 정수들이 공백으로 구분되어 주어진다. 주어지는 수는 절대값이 1,000,000,000 보다 크지 않다.
	출력은 총 N-K+1개의 줄로 구성되며 각 줄에 부분배열의 최소값, 최대값, 합이 공백으로 구분되어 출력한다. 출력은 왼쪽에 위치한 부분배열에 대한 값들부터 차례대로 출력한다.
	===============
	8 3
	1 3 -1 -3 5 3 6 7
	===============
	-1 3 3
	-3 3 -1
	-3 5 1
	-3 5 5
	3 6 14
	3 7 16
	 * */
	static int N,K;
	static int arr[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
	}

}
