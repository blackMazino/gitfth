package preTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1705_CarpBurnMachine {

	public static void main(String[] args) throws IOException {
		int mod = 100000123;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for(int i=1; i<=TC;i++){
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[]schedule = new int [K+1];
			for(int j=1;j<=K;j++){
				schedule[j] = Integer.parseInt(st.nextToken());
			}		
			int [][] machine = new int [N+1][5];
			for(int j=1; j<=N;j++){
				st = new StringTokenizer(br.readLine());
				for(int k=1;k<=4;k++){					
					machine[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			//print(N,M,K,machine,schedule);
			
			
			int score = 0;
			int [] d = new int [M+1];
			for(int j=1;j<=K;j++){//시간이 지날수록
				d[schedule[j]]++;
				for(int k=1;k<=N;k++){
					int fst = machine[k][1];
					int snd = machine[k][2];
					int trd = machine[k][3];
					int energy = machine[k][4];
					int tmp = 0;
					tmp = d[fst]+d[snd]+d[trd];
					//System.out.print(k+"machine "+tmp);
					if(tmp>=energy) score += j%mod;
				}
				//System.out.println(j+"day "+score);
			}
			System.out.println("#"+i+" "+score);
		}

	}

	private static void print(int n, int m, int k, int[][] machine,int[] schedule) {
		System.out.print(n+" "+m+" "+k);
		System.out.println();
		System.out.print("schedule : ");
		for(int i=1;i<=k;i++){
			System.out.print(schedule[i]+" ");
		}
		System.out.println();	
		for(int i=1;i<=n;i++){
			System.out.print(i+"machine : ");
			for(int j=1;j<=4;j++){
				System.out.print(machine[i][j]+" ");
			}
			System.out.println();	
		}

	}
}
