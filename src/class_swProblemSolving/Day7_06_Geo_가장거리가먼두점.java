package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day7_06_Geo_가장거리가먼두점 {
/*
차원 좌표 평면에 N개의 점이 주어진다. 좌표가 같은 점이 주어질 수도 있다.

두 점 사이의 거리는 유클리드 거리로 정의한다. 유클리드 거리에서 두 점 (x1,y1), (x2,y2) 사이의 거리를 
√(x1−x2)^2+(y1−y2)^2로 정의한다.

이 때, 가장 먼 두 점 사이의 거리를 구하는 프로그램을 작성하시오.

입력
첫 줄에 점의 개수를 나타내는 자연수 N이 주어진다. (2≤N≤200,000)
그 다음 N개의 줄에 각 점의 x, y 좌표를 나타내는 두 정수가 공백으로 구분되어 주어진다.
주어지는 좌표의 절대값은 10^7을 넘지 않는다.

출력
첫 줄에 가장 먼 두 점 사이의 거리의 제곱을 출력한다.

5
0 0
0 1
0 2
0 3
0 4

16
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
