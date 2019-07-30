package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class DP_Kadane2nd_BOJ_2167_2차원배열의합 {
//https://www.acmicpc.net/problem/2167
/*
N, M(1 ≤ N, M ≤ 300)
K(1 ≤ K ≤ 10,000)
2 3
1 2 4
8 16 32
3
1 1 2 3
1 2 1 2
1 3 2 3
====================
63
2
36

*/	
	static int a[][], N,M,K;
	static long d[][];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		a = new int[N+1][M+1];
		d = new long[N+1][M+1];
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());	
			for(int j=1;j<=M;j++){
				a[i][j] = Integer.parseInt(st.nextToken());
				d[i][j] = d[i-1][j]+d[i][j-1] - d[i-1][j-1] + a[i][j];
			}
		}

		K = Integer.parseInt(br.readLine());
		for(int k=1;k<=K;k++){
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(d[x2][y2] - d[x1-1][y2] - d[x2][y1-1] + d[x1-1][y1-1]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	
	}

}

/*

public class Main {

    static int[][] arr = new int[301][301];
    static int[][] dp = new int[301][301];

    public static void main(String[] args) throws IOException {

        int N, M; // 배열의 크기
        int K; // query

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {

                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = arr[i][j] + dp[i][j - 1];
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {

            int i, j, x, y;

            st = new StringTokenizer(br.readLine());

            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int row = i; row <= x; row++) {

                sum += dp[row][y] - dp[row][j-1];
            }

            bw.write(sum + "\n");
        } // ~ K loop

        bw.flush();
        bw.close();
        br.close();
    } // ~ main
}
*/