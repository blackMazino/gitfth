package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day6_04_DP_이친수 {
/*
0과 1로 이루어진 이진수 중 다음 성질을 만족하는 수를 이친수라고 한다.

이친수는 0으로 시작하지 않는다.
이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
예를 들면, 1, 10, 100 등이 이친수가 된다. 하지만 010이나 110은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.

N이 주어졌을 때,N자리 이친수의 개수를 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 N이 주어진다.(1≤N≤90)출력
첫 번째 줄에 N자리 이친수의 개수를 출력한다.

3

2
*/
	static int N;
	static long [] D;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = new long [N+1];
			D[1] = 1;
			for(int i=2; i<=N;i++){
				D[i] = D[i-2]+D[i-1];
			}
			System.out.println(D[N]);
//		}
		br.close();
	}
}
/*
//이친수 pinary
import java.io.*;

public class source {
  static int N;
  static long[] D;
    
  public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      D = new long[N+1]; D[1] = 1;
      for (int i=2;i<=N;i++) D[i] = D[i-1] + D[i-2];
      System.out.println(D[N]);
  }
}
*/