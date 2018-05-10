package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class primepath {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        int t, no1, no2;
        int no = 0;
        int pr[];
 
//      BufferedReader bw = new BufferedReader(new FileReader("prime_number_sample.txt"));
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        // 소수 구하기
        int pr_set[] = new int[10000];
        for (int i = 1001; i <= 9999; i++) {
            no = 0;
            for (int j = 2; j * j <= 9999; j++) {
                if (i % j == 0) {
                    no = 1;
                    continue;
                }
            }
            if (no == 0) {
                pr_set[i] = i;
            }
        }
 
        t = Integer.parseInt(bw.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(bw.readLine());
            no1 = Integer.parseInt(st.nextToken());
            no2 = Integer.parseInt(st.nextToken());
             
            pr = new int[10001];
            for (int i = 1; i <= 10000; i++) pr[i] = Integer.MAX_VALUE;
             
            Queue<Integer> que = new LinkedList<Integer>();
            que.add(no1);
            pr[no1] = 0;
             
            while (!que.isEmpty()) {
                int q = que.poll();
                 
                for (int j = 1; j < 10000; j *= 10) {
                    for (int k = 0; k < 10; k++) {
                        int v = q / j / 10 * j * 10 + q % j + j * k;
                        if (pr_set[v] == 0 || pr[v] < Integer.MAX_VALUE) continue;
                        pr[v] = pr[q] + 1;
                        que.add(v);
                    }
                }
            }
            System.out.println(pr[no2]);
        }
         
         
    }
 
}
