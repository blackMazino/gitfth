package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class power {
     
    static long a, m;
    static long solve;
    static long mod = 1000000007;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("power_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        a = Long.parseLong(st.nextToken()) % mod;
        m = Long.parseLong(st.nextToken());
         
        solve = pow(a,m) % mod;
        bw.write(solve + "\n");
        bw.flush();
    }
 
    private static long pow(long x, long y) {
        // TODO Auto-generated method stub
        if(y == 0) return 1;
 
        long temp = y / 2;
         
        long p = pow(x,temp);
         
        if(y % 2 == 1) {  // Ȧ���� ��
            return p * p % mod * x % mod;
        } else {
            return p * p % mod;
        }
    }
}
