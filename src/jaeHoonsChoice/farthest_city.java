package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
// Floyd-Warshall 알고리즘 사용한다.
// MIN(road[i][j], road[i][k] + road[k][j])
// 시간복잡도는 O(V^3)
// - 3중 for문 사용
 
public class farthest_city {
     
    static int N;
    static int road[][];
    static int solve;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
    	System.setIn(new FileInputStream("sample/farthest_city.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        N = Integer.parseInt(br.readLine());
         
        road = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                road[i][j] = Integer.parseInt(st.nextToken());
            }
        }
         
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    road[i][j] = Math.min(road[i][j], road[i][k] + road[k][j]);
                }
            }
        }
         
        solve = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                solve = Math.max(solve, road[i][j]);
            }
        }
         
        bw.write(String.valueOf(solve));
        bw.flush();
    }
 
}
