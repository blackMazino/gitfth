package class_algorithmNMath.DailyHomework;

import java.util.Scanner;

public class Day1_TimeOut {
/*
	 ���� 11332
	�����̴� �ڵ��� �ϴٰ� �ð� �ʰ��� ����. �׷��� �ð� ���⵵�� ����ϱ�� �ߴ�.
	
	ä�� �ý����� 1�ʿ� 100000000(10^8)���� ������ �� �� �ִ�.
	
	�����е��� �����̸� ���� �ð��ʰ��� ������ Ȯ���ϴ� ���α׷��� �ۼ��϶�.
	
	�Է�
	�Է��� ù ��° �ٿ��� �׽�Ʈ ���̽����� �� C�� �־�����.
	
	�� ���� C���� �ٿ��� �ð� ���⵵�� ��Ÿ���� ���ڿ� S, �� �׽�Ʈ ���̽����� �Է��� �ִ� ���� N, �׽�Ʈ ���̽��� ���� ��Ÿ���� T�� ���ѽð�(�� ����) �� ��Ÿ���� L�� �־�����. (1 <= C <= 100, 1 <= N <= 1000000, 1 <= T, L <= 10)
	
	�ð� ���⵵�� ������ ���� 5�� �� �ϳ��� �־�����.
	
	O (N)
	O (N^2)
	O (N^3)
	O (2^N)
	O (N!) 
 * */

	static int lmtNum = 100000000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();//test Case
		//System.out.println(C);
		for(int i=1;i<=C;i++){
			String S = sc.next();
			int N = sc.nextInt();
			int T = sc.nextInt();
			int L = sc.nextInt();
			
			if(isPass(S,N,T,L)){
				System.out.println("May Pass.");	
			}else{
				System.out.println("TLE!");
			}	
		}
		sc.close();
	}
	private static boolean isPass(String S,int N, int T,int L) {
		boolean result = false;
		if("O(N)".equals(S)){
			if(Math.log10(N*T)<=Math.log10(lmtNum*L)){
				result = true;
			}
		}else if("O(N^2)".equals(S)){
			if(2*Math.log10(N) + Math.log10(T) <= Math.log10(lmtNum*L)){
				result = true;
			}
			
		}else if("O(N^3)".equals(S)){
			if(3*Math.log10(N) + Math.log10(T)  <= Math.log10(lmtNum*L)){
				result = true;
			}
			
		}else if("O(2^N)".equals(S)){
			if(N*Math.log10(2) + Math.log10(T)  <= Math.log10(lmtNum*L)){
				result = true;
			}
		}else{//N!
			int a = fact(N);
			if(getSumLog10(N) + Math.log10(T)  <=Math.log10(lmtNum*L)){
				result = true;
			}
		}
		
		return result;
	}
	private static double getSumLog10(int n) {
		// TODO Auto-generated method stub
		double result =0;
		for(int i=n;i>0;i--){
			result = result+Math.log10(i);
		}
		return result;
	}
	private static int fact(int n) {
		if(n<=1) return 1;
		else return n* fact(n-1);
	}

}
