package jaeHoonsChoice;

import java.io.*;
 
class Matrix {
    public static final long MOD = 1000000007; 
    long m[][] = new long[2][2];
     
    public Matrix() {};
     
    public Matrix(long a, long b, long c, long d) {
        m[0][0] = a;
        m[0][1] = b;
        m[1][0] = c;
        m[1][1] = d;
    }
     
    // 행렬 곱셈
    public Matrix multiply(Matrix mm) {
        Matrix ret = new Matrix();
         
        // MOD 연산의 덧셈과 곱셈을 이용하여 리턴 값을 적용해둠
        //(a + b)%mod = (a%mod + b%mod )%mod
        //(a * b)%mod = (a%mod * b%mod )%mod
        ret.m[0][0] = ((mm.m[0][0]*m[0][0]%MOD)%MOD + (mm.m[0][1]*m[1][0]%MOD)%MOD);
        ret.m[0][1] = ((mm.m[0][0]*m[0][1]%MOD)%MOD + (mm.m[0][1]*m[1][1]%MOD)%MOD);
        ret.m[1][0] = ((mm.m[1][0]*m[0][0]%MOD)%MOD + (mm.m[1][1]*m[1][0]%MOD)%MOD);
        ret.m[1][1] = ((mm.m[1][0]*m[0][1]%MOD)%MOD + (mm.m[1][1]*m[1][1]%MOD)%MOD);
        return ret;
    }
}
 
public class stair {
     
    public static final long MOD = 1000000007; 
    static long n;
    static Matrix m, k, v;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("stair_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         
        n = Long.parseLong(br.readLine());
        m = new Matrix(1, 1, 1, 0);  // 곱셈 연산할 행렬
        k = new Matrix(1, 0, 0, 1);  // 실제 출력할 행렬 : 홀수가 나올때마다 곱해진다.(마지막에 1이 있으므로 무조건 홀수는 존재함)
        for(; n > 0; n >>= 1, m = m.multiply(m)) {  // n을 2로 나눈 값을 계속해서 돌려준다.
            if ((n&1) == 1) {
                k = k.multiply(m);
            } 
        }
         
        bw.write(k.m[0][0]%MOD + "\n");
        bw.flush();
    }
 
}