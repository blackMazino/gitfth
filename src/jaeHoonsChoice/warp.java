package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class warp {
 
    static int N, M;
    static long[] D;
    static ArrayList<Integer>[] con, conv;
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
    	System.setIn(new FileInputStream("sample/warp.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        con = new ArrayList[N+1];
        conv = new ArrayList[N+1];
 
        for (int i = 1; i <= N; i++) {
            con[i] = new ArrayList<>();
            conv[i] = new ArrayList<>();
        }
 
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
 
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
 
            con[a].add(b);
            conv[a].add(c);
        }
 
        D = new long[N + 1];
 
        for (int i = 2; i <= N; i++)
            D[i] = Long.MAX_VALUE;
 
        D[1] = 0;
        for (int i = 1; i < N; i++)
            if (D[i] < Long.MAX_VALUE) {
                for (int j = 0; j < con[i].size(); j++) {
                    int t = con[i].get(j);
                    int v = conv[i].get(j);
 
                    D[t] = Math.min(D[t], D[i] + v);
                }
            }
        System.out.println(D[N] < Long.MAX_VALUE ? D[N] : -1);
    }
}
