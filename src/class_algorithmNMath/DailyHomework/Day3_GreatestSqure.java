package class_algorithmNMath.DailyHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Scanner;
import java.util.StringTokenizer;

public class Day3_GreatestSqure {
/*
문제
n×m의 0, 1로 된 배열이 있다. 이 배열에서 1로 된 가장 큰 정사각형의 크기를 구하는 프로그램을 작성하시오.

0	1	0	0
0	1	1	1
1	1	1	0
0	0	1	0
위와 같은 예제에서는 가운데의 2×2 배열이 가장 큰 정사각형이다. 

입력
첫째 줄에 n, m(1 ≤ n, m ≤ 1,000)이 주어진다. 다음 n개의 줄에는 m개의 숫자로 배열이 주어진다.

출력
첫째 줄에 가장 큰 정사각형의 넓이를 출력한다.
 **/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;		
//		st = new StringTokenizer(br.readLine());		
//		int TC = Integer.parseInt(st.nextToken());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int [][] array = new int [N+1][M+1];
			//Scanner sc = new Scanner(System.in);
			for(int i=1;i<=N;i++){
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				for(int j=1; j<=M;j++){
					array[i][j] = Integer.parseInt(String.valueOf(str.charAt(j-1)));				
				}
			}
			int lengthSize = 0;
			lengthSize = getLengthByDP(array,N,M);
			
			System.out.println(lengthSize*lengthSize);
			
	}

	private static int getLengthByDP(int[][] array, int n, int m) {
		int d[][] = new int [n+1][m+1];//i,j가 우측하단에 위치하는 정사각형의 변의 길이
		int lengthSize = 0;
		for(int i=1;i<=n;i++){				
			for(int j=1; j<=m;j++){
				//System.out.print(array[i][j]);
				if(array[i][j]==0) {
					d[i][j] = 0;
				}else{
					int w = Math.min(d[i][j-1], d[i-1][j]);
					//왼쪽의 D 값(i,j-1)과 위쪽의 D값 중 작은 것을 찾고, 그값을 차이 w에 받는다.
					//둘중의 하나가 0이면 길이는 무조건 1			
					d[i][j] = d[i][j-1]==0||d[i-1][j]==0 ? 1 : w+1;
					if(d[i-w][j-w]==0){
						d[i][j] = w;
					}
					lengthSize = Math.max(lengthSize, d[i][j]);
				}				
			}
			//System.out.println();
		}		
		return lengthSize;
	}

}
