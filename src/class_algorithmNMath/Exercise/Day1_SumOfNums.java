package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Day1_SumOfNums {
/*
���� 2003
N���� ���� �� ���� A[1], A[2], ��, A[N] �� �ִ�. �� ������ i��° ������ j��° �������� �� A[i]+A[i+1]+��+A[j-1]+A[j]�� M�� �Ǵ� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�Է�
ù° �ٿ� N(1��N��10,000), M(1��M��300,000,000)�� �־�����. ���� �ٿ��� A[1], A[2], ��, A[N]�� �������� �и��Ǿ� �־�����. ������ A[x]�� 30,000�� ���� �ʴ� �ڿ����̴�.
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
		st = new StringTokenizer(br.readLine());//line ������ �д´�
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
