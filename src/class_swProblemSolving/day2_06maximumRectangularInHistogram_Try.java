package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class day2_06maximumRectangularInHistogram_Try {

/*
히스토그램은 직사각형 여러 개가 아래쪽으로 정렬되어 있는 도형이다. 각 직사각형은 같은 너비를 가지고 있지만, 높이는 서로 다를 수 있다. 
예를 들어, 아래에서 왼쪽 그림은 높이가 순서대로 
2,1,4,5,1,3,3이고, 너비는 모두 1인 직사각형으로 이루어진 히스토그램이다.


너비가 1인 N개의 직사각형으로 이루어진 히스토그램이 주어진다. 
이 때, 위에서 오른쪽 그림처럼 히스토그램 내에서 가장 큰 직사각형의 크기를 구하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트케이스로 이루어져 있다.
각 테스트케이스의 첫 정수는 직사각형의 수N을 의미한다 (1≤N≤100,000).
만약 N=0이면 입력의 끝을 의미한다.
같은 줄에 N개의 정수가 공백을 사이에 두고 주어진다. 
이는 순서대로 직사각형 N개의 높이를 나타낸다. 
주어지는 높이는 1,000,000,000 보다 크지 않다.

출력
각 테스트케이스 마다 한 줄에 히스토그램 내에서 가장 큰 직사각형의 크기를 출력한다.
===============
7 2 1 4 5 1 3 3
4 1000 1000 1000 1000
0
===============
8
4000
*/	
	
	static int N;
	static int[] h;
	static LinkedList<Integer> Lstack ;
	static LinkedList<Integer> Rstack ;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());		
			if(N!=0){
				h = new int[N+1];
				int max = 0;
				st = new StringTokenizer(br.readLine());
				for(int i=1;i<N;i++){
					h[i] = Integer.parseInt(st.nextToken());
				}	
							
				
				System.out.println(max);
			}
			
			
			
//			System.out.println("#"+tc+"");
//		}

		br.close();

	}

}
