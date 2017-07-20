package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Day1_SumOfNums {
/*
문제 2003
N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다. 이 수열의 i번째 수부터 j번째 수까지의 합 A[i]+A[i+1]+…+A[j-1]+A[j]가 M이 되는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1≤N≤10,000), M(1≤M≤300,000,000)이 주어진다. 다음 줄에는 A[1], A[2], …, A[N]이 공백으로 분리되어 주어진다. 각각의 A[x]는 30,000을 넘지 않는 자연수이다.
 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt();
//		int M = sc.nextInt();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt = 0;
		int [] arr = new int[N];
		int sum = 0;
		int p1 = 0;
		st = new StringTokenizer(br.readLine());//line 단위로 읽는다
		for(int i=0; i<N;i++){
//			arr[i] = sc.nextInt();
			arr[i] = Integer.parseInt(st.nextToken());
			sum = sum+arr[i];
			if(sum == M){
				cnt++;
			}
			if(sum>M){
				for(int j=p1;j<=i;j++){
					sum = sum - arr[j];
					if(sum == M){
						cnt++;
						p1 = j+1;
						break;
					}
					if(sum < M){
						p1 =j+1;
						break;
					}
				}
			}		
		}		
		System.out.println(cnt);		
	}
}
