package class_algorithmNMath.DailyHomework;

import java.util.Scanner;

public class Day1_TimeOut {
/*
	 문제 11332
	유빈이는 코딩을 하다가 시간 초과가 났다. 그래서 시간 복잡도를 계산하기로 했다.
	
	채점 시스템은 1초에 100000000(10^8)가지 동작을 할 수 있다.
	
	여러분들은 유빈이를 도와 시간초과가 나는지 확인하는 프로그램을 작성하라.
	
	입력
	입력의 첫 번째 줄에는 테스트 케이스들의 수 C가 주어진다.
	
	그 다음 C개의 줄에는 시간 복잡도를 나타내는 문자열 S, 각 테스트 케이스마다 입력의 최대 범위 N, 테스트 케이스의 수를 나타내는 T랑 제한시간(초 단위) 를 나타내는 L이 주어진다. (1 <= C <= 100, 1 <= N <= 1000000, 1 <= T, L <= 10)
	
	시간 복잡도는 다음과 같은 5개 중 하나로 주어진다.
	
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
