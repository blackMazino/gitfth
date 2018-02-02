package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day7_05_Geo_bitonicTour_DP {

/*
N 개의 점P1,P2,P3,…,PN이 2차원 좌표 평면에 있다. 
Pi의 좌표를(xi,yi)라 할 때,1이상N미만인i에 대해xi<xi+1를 항상 만족한다.

1번 점에서 시작하여, x좌표가 증가하는 방향으로만 이동하면서 다른 점들을 방문하여 
N번 점까지 이동한다. 그리고N번 점에서 x좌표가 감소하는 방향으로 다시1번 점으로 되돌아온다.
이 때,1번 점을 제외한 나머지 점들은 정확히 한 번만 방문하는 경우 이를 Bitonic Tour라고 한다.

아래 그림은 6개의 점이 있을 때, 가능한 Bitonic Tour의 한 가지 경우를 나타낸다.

Optimal Bitonic Tour란, 이동한 거리 합이 제일 작은 Bitonic Tour를 의미한다. N개의 점이 주어졌을 때, Optimal Bitonic Tour에서 이동한 거리를 구하는 프로그램을 작성하시오.

첫 줄에 점의 개수를 나타내는 자연수 N이 주어진다. (2≤N≤1,000)

다음 N개의 줄에 각 점의 좌표가 주어진다.i+1번째 줄에는Pi의 좌표xi와yi가 공백으로 구분되어 주어진다. 
주어지는 좌표는1이상1,000,000이하다.

첫 줄에 Optimal Bitonic Tour의 이동거리를 소숫점 셋째 자리에서 반올림하여 출력한다.

6
1 4
3 1
4 7
6 3
8 6
10 4

22.53
*/
	static int N;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			 
			
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
}
