package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DayAnother_05Graph_임계경로 {
    static int N, M;
    static int[] D, in;
    static ArrayList<Integer>[] con, conv;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        con = new ArrayList[N+1];
        conv = new ArrayList[N+1];
        for (int i=1;i<=N;i++){
            con[i] = new ArrayList<>();
            conv[i] = new ArrayList<>();
        }
        in = new int[N+1];
        for (int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            con[a].add(b); conv[a].add(c);
            in[b]++;
        }
        Queue<Integer> que = new LinkedList<>();
        D = new int[N+1];
        que.add(1);
        while (!que.isEmpty()){
            int q = que.poll();
            for (int i=0;i<con[q].size();i++){
                int t = con[q].get(i), v = conv[q].get(i);
                if (--in[t] == 0) que.add(t);
                D[t] = Math.max(D[t], D[q]+v);
            }
        }
        System.out.println(D[N]);
    }
}
