package class_swProblemSolving;

import java.io.*;
import java.util.*;
 
public class DayAnother_01Graph_cowParty{
    static int N, M, X;
    static int[] D, E;
    static int[][] w1, w2;
     
    static void dijkstra(int s, int[][] w, int[] dist) {
        for (int i=0;i<=N;i++) dist[i] = Integer.MAX_VALUE;
        boolean[] vis = new boolean[N+1];
        dist[s] = 0;
        for (int i=1;i<=N;i++){
            int t = 0;
            for (int j=1;j<=N;j++) if (!vis[j] && dist[t] > dist[j]) t = j;
            vis[t] = true;
            for (int j=1;j<=N;j++) if (w[t][j] < Integer.MAX_VALUE){
                dist[j] = Math.min(dist[j], dist[t] + w[t][j]);
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
         
        w1 = new int[N+1][N+1];
        w2 = new int[N+1][N+1];
        for (int i=1;i<=N;i++) for (int j=1;j<=N;j++){
            w1[i][j] = w2[i][j] = i == j ? 0 : Integer.MAX_VALUE;
        }
        for (int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            w1[a][b] = Math.min(w1[a][b], c);
            w2[b][a] = Math.min(w2[b][a], c);
        }
 
        D = new int[N+1];
        E = new int[N+1];
        dijkstra(X, w1, D);
        dijkstra(X, w2, E);
        int ans = 0;
        for (int i=1;i<=N;i++)
            ans = Math.max(ans, D[i] + E[i]);
        System.out.println(ans);
    }
}
