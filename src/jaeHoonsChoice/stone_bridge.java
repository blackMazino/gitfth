package jaeHoonsChoice;

import java.io.*;
 
public class stone_bridge {
     
    static String rolls, angels, devils;
    static char rollc[];
    static char bridge[][];
    static int D[][][];
    static int n,k;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("stone_bridge_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         
        rolls = br.readLine();
        n = rolls.length();
        rollc = new char[n+1];
        for(int i = 1; i <= n; i++) rollc[i] = rolls.charAt(i-1); 
         
        angels = br.readLine();
        devils = br.readLine();
        k = angels.length();
         
        bridge = new char [2][k+1];
        for(int i = 1; i <= k; i++) {
            bridge[0][i] = angels.charAt(i-1);
            bridge[1][i] = devils.charAt(i-1);
        }
         
        D = new int[k+1][n+1][2];  // 다리 문자열 / 두루마리 문자열 / 천사-악마다리
        for(int i = 0; i <= k; i++) {  // 다리의 문자열이 1개이고, 두루마리 문자열이 1개일 때 초기값.
            D[i][0][0] = 1;             // 값을 더하기 위해서 1로 세팅함
            D[i][0][1] = 1;
        }
         
        for(int i = 1; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                if(rollc[j] == bridge[0][i]) {  // 천사의 다리
                    D[i][j][1] = D[i-1][j][1] + D[i-1][j-1][0];  // 찾았으면 다른 다리로 넘김
                } else {
                    D[i][j][1] = D[i-1][j][1];  // 못찾았으면 이전값과 동일
                }
                 
                if(rollc[j] == bridge[1][i]) {  // 악마의 다리
                    D[i][j][0] = D[i-1][j][0] + D[i-1][j-1][1];
                } else {
                    D[i][j][0] = D[i-1][j][0];
                }
            }
        }
        bw.write(D[k][n][0] + D[k][n][1] + "\n");
        bw.flush();
    }
 
}