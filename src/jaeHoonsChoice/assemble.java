package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
// K의 맨 마지막 값이 0, 1, 2...K 일 때
// 0인 경우, K-1개까지의 합이 N이어야 하고,
// 1인 경우, K-1개까지의 합이 N-1,
// 2인 경우, N-2이어야 한다.
// 즉, 맨 뒤의 값을 L이라고 했을 때,
// N-1까지의 값의 합과 L을 더하면 최대 경우의 수가 나온다.
// 초기값은 K가 1인 경우에는 경우의 수가 1개밖에 없으므로 1로 세팅한다.
// D[K][N] = D[K-1][N-1] + L
 
public class assemble {
 
    static int N, K;
    static long div = 1000000000;
    static long solve;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("assemble_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        long D[][] = new long[K+1][N+1]; 
         
        // 초기값 세팅 : K가 1인 경우에는 경우의 수가 1개밖에 없음
        for(int i = 0; i <= N; i++) {
            D[1][i] = 1;
        }
         
        for(int i = 2; i <= K; i++) {
            for(int j = 0; j <= N; j++) {
                for(int k = 0; k <= j; k++) {
                    D[i][j] = D[i][j] + (D[i-1][j-k] % div);  // 맨 뒤의 값이 k인 경우 바로 앞까지의 합은 k를 뺀 값이 된다.
                }
            }
        }
        solve = D[K][N] % div;
         
        bw.write(String.valueOf(solve));
        bw.write("\n");
        bw.flush();
    }
}