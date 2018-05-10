package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
 
public class nqueen {
    static int solve;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
    	System.setIn(new FileInputStream("sample/nqueen.txt"));
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
        int n;
        solve = 0;
         
        n = Integer.parseInt(bw.readLine());
         
        int NN[][] = new int[n+1][n+1];
         
         
        cal_nqueen(1,n,NN);
         
        System.out.println(solve);
    }
 
    private static void cal_nqueen(int i, int n, int[][] NN) {
        // TODO Auto-generated method stub
        if(i <= n) { // N이하일 때만 계산
            for(int j = 1; j <= n; j++) {
                if(check(i,j,n,NN) == 1) {  // 모든 조건이 만족할 때
                    NN[i][j] = 1;
                    cal_nqueen(i+1,n,NN);
                    NN[i][j] = 0;
                }
            }
        } else {
            solve++;
        }
    }
 
    private static int check(int i, int j, int n, int[][] NN) {
        // TODO Auto-generated method stub
         
        if(NN[i][j] == 1) return 0;
        //양옆, 위, 대각선 위쪽이 모두 0일때만 가능
        //양옆
        for(int k = 1; k < i; k++) {
            if(NN[k][j] == 1) return 0;
        }
        for(int k = i+1; k <= n; k++) {
            if(NN[k][j] == 1) return 0;
        }
        for(int k = 1; k < j; k++) {
            if(NN[i][k] == 1) return 0;
        }
        for(int k = j+1; k <= n; k++) {
            if(NN[i][k] == 1) return 0;
        }

        //대각선 위
        int l = i;
        int m = j;
        while(l-- > 0 && m-- > 0) {
            if(NN[l][m] == 1) return 0;
        }
        l = i;
        m = j;
        while(l-- > 0  && m++ < n) {
            if(NN[l][m] == 1) return 0;
        }
                 
        return 1;
    }
}