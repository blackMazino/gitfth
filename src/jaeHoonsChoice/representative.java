package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class representative {
 
    static int n, m;
    static int s, e;
    static long A[], sum_temp[];
    static long MAX, MIN, SUM; // 최소값, 최대값, 합계
    static int tree_depth, B, tree_cnt;
    static long min_tree[], max_tree[];
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("representative_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
 
        n = Integer.parseInt(br.readLine());
 
        // 2의 n제곱으로 tree를 구성해야 함
        tree_depth = 0;
        for (long j = 1;; j *= 2) {
            tree_depth++;
            if(j > n) break;
        }
        B = (1 << (tree_depth-1)) - 1;  // B + 1부터 입력값 시작
         
        tree_cnt = 1<<tree_depth;
         
        min_tree = new long[tree_cnt];
        max_tree = new long[tree_cnt];
         
        // 초기값 구성
        // min_tree -> max_value
        // max_tree -> min_value
        for(int i = 1; i < tree_cnt; i++) {
            min_tree[i] = Long.MAX_VALUE;
            max_tree[i] = Long.MIN_VALUE;
        }
         
        A = new long[n + 1];
        sum_temp = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Long.parseLong(st.nextToken());
            sum_temp[i] += (A[i] + sum_temp[i - 1]); // 누적합 구하기
 
            // min tree / max tree 만들기
            // input 내역 입력하기
            min_tree[i + B] = A[i];
            max_tree[i + B] = A[i];
        }
         
        // tree 구성하기
        for(int i = tree_cnt-1; i >= 2; i -= 2) {  // 끝부터 시작하여 2개씩 전으로 비교한다.
            min_tree[i/2] = Math.min(min_tree[i], min_tree[i-1]);  // 바로 전 값과 비교해서 작은 값 위로 올리기
            max_tree[i/2] = Math.max(max_tree[i], max_tree[i-1]);  // 큰값 올리기
        }
 
        // 질의 확인
        m = Integer.parseInt(br.readLine());
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 시작점
            e = Integer.parseInt(st.nextToken()); // 종료점
             
            MIN = get_min(s,e);
            MAX = get_max(s,e);
            SUM = sum_temp[e] - sum_temp[s - 1];
             
            bw.write(MIN + " " + MAX + " " + SUM + "\n");
        }
        bw.flush();
    }
 
    private static long get_max(int s, int e) {
        // TODO Auto-generated method stub
        long ret = Long.MIN_VALUE;
        int l = s + B;
        int r = e + B;
 
        while (l <= r) {
            if (l % 2 == 1) { // 왼쪽 값이 트리의 오른쪽에 있을 때
                ret = Math.max(ret, max_tree[l]);
            }
            if (r % 2 == 0) { // 오른쪽 값이 트리의 왼쪽에 있을 때
                ret = Math.max(ret, max_tree[r]);
            }
            l = ++l / 2;
            r = --r / 2;
        }
         
        return ret;
    }
 
    private static long get_min(int s, int e) {
        // TODO Auto-generated method stub
        long ret = Long.MAX_VALUE;
        int l = s + B;
        int r = e + B;
 
        while (l <= r) {
            if (l % 2 == 1) { // 왼쪽 값이 트리의 오른쪽에 있을 때
                ret = Math.min(ret, min_tree[l]);
            }
            if (r % 2 == 0) { // 오른쪽 값이 트리의 왼쪽에 있을 때
                ret = Math.min(ret, min_tree[r]);
            }
            l = ++l / 2;
            r = --r / 2;
        }
         
        return ret;
    }
 
}