package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*https://www.acmicpc.net/problem/1912
10
10 -4 3 1 5 6 -35 12 21 -1
============================
33

*/
public class DP_Kadane1st_BOJ_1912_부분합 {
	
		static int N;
		static int[]a;
		static long[]d;
		public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			a = new int[N+1];
			d = new long[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++){
				int t =Integer.parseInt(st.nextToken()); 
				a[i] = t;			
			}
					
			long answer = Long.MIN_VALUE;
			for(int i=1;i<=N;i++){
				d[i]= Math.max(d[i-1]+a[i], a[i]);
				answer = Math.max(d[i], answer);
			}
			
			System.out.println(answer);
		}
}
