package jaeHoonsChoice;

import java.io.*;

public class lcs {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String lc1, lc2;
        int lc1_length, lc2_length;
 
        //BufferedReader bw = new BufferedReader(new FileReader("lcs_sample.txt"));
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
 
        lc1 = " " + bw.readLine();
        lc2 = " " + bw.readLine();
        lc1_length = lc1.length();
        lc2_length = lc2.length();
 
        int map[][][] = new int[lc1_length + 1][lc2_length + 1][2];
 
        char S1[] = lc1.toCharArray();
        char S2[] = lc2.toCharArray();
 
        char solve[] = new char[1001];
 
        for (int i = 1; i < lc1_length; i++) {
            for (int j = 1; j < lc2_length; j++) {
                if (S1[i] == S2[j]) {
                    map[i][j][0] = map[i - 1][j - 1][0] + 1;
                    map[i][j][1] = 3;
                } else {
                    if (map[i - 1][j][0] >= map[i][j - 1][0]) {
                        map[i][j][0] = map[i - 1][j][0];
                        map[i][j][1] = 1;
                    } else {
                        map[i][j][0] = map[i][j - 1][0];
                        map[i][j][1] = 2;
                    }
                }
            }
        }
 
        int i = lc1_length - 1;
        int j = lc2_length - 1;
        int len = 0;
        while (i > 0 && j > 0) {
            if (map[i][j][1] == 3) {
                solve[len++] = S1[i];
                i--;
                j--;
            } else if (map[i][j][1] == 1) {
                i--;
            } else if (map[i][j][1] == 2) {
                j--;
            }
        }
 
        for (int k = len - 1; k >= 0; k--) {
            System.out.print(solve[k]);
        }
    }
 
}
