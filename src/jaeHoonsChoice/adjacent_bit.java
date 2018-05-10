package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
// 길이 N인 수열에 인접한 비트의 개수가 K개 이면서 "마지막 원소가 X인" 수열의 경우의 수
// -> 길이가 N-1인 수열에 인접한 비트의 수가 k개이면서 마지막 원소가 last인 소문제로 변경
// 마지막 원소가 0/1일때를 나누어서 계산한다.
// 0일 때는 바로 앞의 값이 어떤 값이어도 인접한 비트의 개수의 수가 변경되지 않는다.
// 1일 때는 바로 앞의 값에 따라서 변경되므로 j-1로 지정한다.
// 초기값은 길이 1인 수열에 인접한 비트의 개수가 0개일 때 이므로 D[1][0][0] = D[1][0][1] = 1로 지정한다.
 
public class adjacent_bit {
 
    static int T, t, N, K;
    static int D[][][];
    static int solve;
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("adjacent_bit_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
 
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            D = new int[N + 1][K + 1][2];
 
            D[1][0][0] = D[1][0][1] = 1;
 
            for (int i = 2; i <= N; i++) {
                for (int j = 0; j <= K; j++) {
                     
                    D[i][j][0] = D[i - 1][j][0] + D[i - 1][j][1];  // 맨 끝자리 수가 0일 때
                     
                    // 맨 끝자리 수가 1일 때
                    if (j == 0) {  
                        D[i][j][1] = D[i - 1][j][0];  // 첫번째 값은 j-1이 없으므로 
                    } else {
                        D[i][j][1] = D[i - 1][j][0] + D[i - 1][j - 1][1];  // 이전값에 대해서 확인
                    }
                }
            }
            solve = D[N][K][0] + D[N][K][1];
            bw.write(t + " " + solve + "\n");
        }
        bw.flush();
    }
 
}