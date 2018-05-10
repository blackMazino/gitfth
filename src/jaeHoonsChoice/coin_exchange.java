package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class coin_exchange {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        int n, w;
 
        //BufferedReader bw = new BufferedReader(new FileReader("coin_exchange_sample.txt"));
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        n = Integer.parseInt(bw.readLine());
 
        int coin[] = new int[n + 1];
 
        st = new StringTokenizer(bw.readLine());
        for (int i = 1; i <= n; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }
 
        w = Integer.parseInt(bw.readLine());
        int D[] = new int[w + 1];
 
        D[0] = 0;
        for (int i = 1; i <= w; i++) {
            D[i] = Integer.MAX_VALUE;
        }
 
        for (int i = 1; i <= n; i++) {
            for (int j = coin[i]; j <= w; j++) {
                if (D[j - coin[i]] != Integer.MAX_VALUE) {  // 값이 성립된다는 조건 하에서 계산
                    D[j] = Math.min(D[j], D[j - coin[i]] + 1);
                }
            }
        }
 
        System.out.println(D[w]);
        bw.close();
    }
 
}