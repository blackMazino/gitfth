package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayAnother_04Graph_보물섬 {
    static int N, M, T;
    static int[][] w;
     
    static void dijkstra(int s, int[] dist) {
        for (int i=0;i<=N;i++) dist[i] = Integer.MAX_VALUE;
        dist[s] = 0;
        boolean[] vis = new boolean[N+1];
        for (int i=1;i<=N;i++){
            int t = 0;
            for (int j=1;j<=N;j++) {
            	if (!vis[j] && dist[t] > dist[j]) {
            		t = j;
            	}
            }
            // t가 확정
            vis[t] = true;
            for (int j=1;j<=N;j++) {
            	if (w[t][j] < Integer.MAX_VALUE && !vis[j]){
            		if (dist[j] > dist[t] + w[t][j]){
                        dist[j] = dist[t] + w[t][j];
                    }
            	}
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(br.readLine());
        w = new int[N+1][N+1];
        for (int i=1;i<=N;i++) for (int j=1;j<=N;j++){
            if (i == j) w[i][j] = 0;
            else w[i][j] = Integer.MAX_VALUE;
        }
        for (int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            w[a][b] = Math.min(w[a][b], c);
        }
        int[] dist1 = new int[N+1];
        dijkstra(1, dist1);
        // dist1[T]
        int[] distT = new int[N+1];
        dijkstra(T, distT);
        // distT[1]
        if (dist1[T] == Integer.MAX_VALUE || distT[1] == Integer.MAX_VALUE){
            System.out.println("NO");
        }else{
            System.out.println("YES");
            System.out.println(dist1[T] + distT[1]);
        }
    }
}

