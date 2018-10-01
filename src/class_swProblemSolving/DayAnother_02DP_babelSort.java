package class_swProblemSolving;

import java.io.*;
import java.util.*;
 
public class DayAnother_02DP_babelSort{
    static int N;
    static int[] A, to;
    static Integer[] B;
    static boolean[] vis;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        B = new Integer[N+1];
        for (int i=1;i<=N;i++){
            A[i] = Integer.parseInt(br.readLine());
            B[i] = new Integer(i);
        }
        Arrays.sort(B, 1, N+1, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return A[a] - A[b];
            }
        });
        to = new int[N+1];
        for (int i=1;i<=N;i++) to[B[i]] = i;
        vis = new boolean[N+1];
        int ans = 0;
        for (int i=1;i<=N;i++) if (!vis[i]){
            int mn = A[i], sum = 0, sz = 0;
            for (int t=i;!vis[t];t=to[t]){
                vis[t] = true;
                mn = Math.min(mn, A[t]);
                sum += A[t];
                sz++;
            }
            ans += Math.min(sum + (sz-2)*mn, sum + mn + (sz+1)*A[B[1]]);
        }
        System.out.println(ans);
    }
}
