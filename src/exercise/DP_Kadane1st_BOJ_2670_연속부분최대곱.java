package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/*https://www.acmicpc.net/problem/2670
8
1.1
0.7
1.3
0.9
1.4
0.8
0.7
1.4
===
1.638
*/
public class DP_Kadane1st_BOJ_2670_연속부분최대곱 {
	
	static int N;
	static double[]a;
	static double[]d;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		a = new double[N+1];
		d = new double[N+1];		
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());
			double t = Double.parseDouble(st.nextToken()); 
			a[i] = t;			
		}
				
		double answer = 0;
		d[0]=1;
//		for(int i=1;i<=N;i++){
//			d[i]= Math.max(d[i-1]*a[i], a[i]);
//			answer = Math.max(d[i], answer);
//		}
		
//		for(int i=1;i<=N;++i){
//			double now = 1;
//			for(int j=i;j<=N;++j){
//				now*=a[j];
//				answer = Math.max(now, answer);
//			}
//		}
		
		double tmp = 1;
		for(int i=1;i<=N;i++){			
			tmp = tmp==0 ? a[i]: tmp*a[i]; 
			answer = Math.max(answer, tmp);
			if(tmp<1) tmp=0;
		}
		
		
//		System.out.println(answer);
//		answer = Math.round((answer*1000))/1000.0;
//		System.out.println(answer);
		System.out.println(String.format("%,.3f",answer));
		
//		System.out.println("========");
//		double pi = 3.14159265358979;
//		System.out.println(Math.round(pi)); //결과 : 3
//		System.out.println(Math.round(pi*100) / 100.0); //결과 : 3.14
//		System.out.println(Math.round(pi*1000) / 1000.0); //결과 : 3.142
//		System.out.println("========");
//		double money = 4424.243423;
//		System.out.println(String.format("%.2f", pi)); //결과 : 3.14
//		System.out.println(String.format("%.3f", pi)); //결과 : 3.142
//		System.out.println(String.format("%,.3f", money)); //결과 : 4,424.243
//		System.out.println("========");
//		double money2 = 5000.000;
//		System.out.println(Math.round(money2*1000)/1000); //결과 5000
//		System.out.println(String.format("%.3f", money2)); //결과 : 5000.000
		 
	}

}
