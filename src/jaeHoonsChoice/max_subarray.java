package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
// 첫번째 값부터 Partition을 나눠서 확인한다.
// 초기화는 각 값으로 해놓고,
// index 1에서 끝나는 구간합과 index 2값을 비교하여 큰 값을 D 배열에 저장한다.
// 즉 D[n-1]값과 D[n-1] + list[i] 값을 비교하여 큰 값이 구간합 중 가장 큰 값임
// - 계산해보면 바로 나온다.
// Partition에 대한 이해가 있으면 쉬운 문제
 
public class max_subarray {
 
    static int N;
    static int list[];
    static long D[];
    static long solve = Long.MIN_VALUE;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("max_section_sum_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        N = Integer.parseInt(br.readLine());
        list = new int[N+1];
        st = new StringTokenizer(br.readLine());
        D = new long[N+1];
        for(int i = 1; i <= N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
            D[i] = list[i];
        }
         
        for(int i = 1; i <= N; i++) {
            D[i] = Math.max(D[i-1] + list[i], list[i]);
        }
         
        for(int i = 1; i <= N; i++) {
            solve = Math.max(solve, D[i]);
        }
         
        bw.write(String.valueOf(solve));
        bw.flush();
    }
}