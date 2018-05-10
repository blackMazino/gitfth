package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class downhill {
    static final int mod = 1234567;
    static int m,n;
    static int map[][], D[][];
    static int visit[][];
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("downhill_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
         
        map = new int[m+2][n+2];
        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new int[m+1][n+1];
         
        bw.write(dfs(1,1)%mod + "\n");
        bw.flush();
    }
    private static int dfs(int x, int y) {
        // TODO Auto-generated method stub
         
        if(x == n && y == m) {
            return 1;
        }
         
        if(visit[y][x] != 0) return visit[y][x];
         
        int ys[] = {1,0,-1,0};
        int xs[] = {0,1,0,-1};
         
        for(int i = 0; i < 4; i++) {
            int xx = x + xs[i];
            int yy = y + ys[i];
            if(map[y][x] > map[yy][xx] && xx > 0 && xx <= n && yy > 0 && yy <= m) {
                visit[y][x] = visit[y][x] % mod + dfs(xx,yy) % mod;
            }
        }
        return visit[y][x] % mod;
    }
 
}