package class_algorithmNMath.DailyHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Scanner;
import java.util.StringTokenizer;

public class Day3_GreatestSqure {
/*
����
n��m�� 0, 1�� �� �迭�� �ִ�. �� �迭���� 1�� �� ���� ū ���簢���� ũ�⸦ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

0	1	0	0
0	1	1	1
1	1	1	0
0	0	1	0
���� ���� ���������� ����� 2��2 �迭�� ���� ū ���簢���̴�. 

�Է�
ù° �ٿ� n, m(1 �� n, m �� 1,000)�� �־�����. ���� n���� �ٿ��� m���� ���ڷ� �迭�� �־�����.

���
ù° �ٿ� ���� ū ���簢���� ���̸� ����Ѵ�.
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
		int d[][] = new int [n+1][m+1];//i,j�� �����ϴܿ� ��ġ�ϴ� ���簢���� ���� ����
		int lengthSize = 0;
		for(int i=1;i<=n;i++){				
			for(int j=1; j<=m;j++){
				//System.out.print(array[i][j]);
				if(array[i][j]==0) {
					d[i][j] = 0;
				}else{
					int w = Math.min(d[i][j-1], d[i-1][j]);
					//������ D ��(i,j-1)�� ������ D�� �� ���� ���� ã��, �װ��� ���� w�� �޴´�.
					//������ �ϳ��� 0�̸� ���̴� ������ 1			
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
