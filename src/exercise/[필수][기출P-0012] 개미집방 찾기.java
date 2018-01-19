import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
      
public class Solution {
    static int T,N;
    static int Ans, pow, sz, rt;
    static ArrayList<Integer>[] edges;
    static ArrayList<Integer>[] inparr;
    static int[] parents;
    static int[] depth;
    static int[][] sparsetable;
    static ArrayDeque<Integer> que = new ArrayDeque<>();
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
              
        T = Integer.parseInt(br.readLine());
 
        for(int tc = 1; tc <=T; tc++){
            N = Integer.parseInt(br.readLine());
             
            edges = new ArrayList[N+1];
            for(int i=1; i<=N; i++){
                edges[i] = new ArrayList<>();
            }
            inparr = new ArrayList[N+1];
            parents = new int[N+1];
            depth = new int[N+1];
             
            pow = 1; sz = 0;
            while(pow <= N){
                pow *= 2; sz++;
            }
//          System.out.println("sz="+sz+",N="+N);
            sparsetable = new int[N+1][sz];
             
            for(int i=1; i<=N; i++){
                StringTokenizer stk = new StringTokenizer(br.readLine());
                int pts = Integer.parseInt(stk.nextToken());
                int dir = Integer.parseInt(stk.nextToken());
                int num = Integer.parseInt(stk.nextToken());
                 
                parents[i] = pts;
                sparsetable[i][0] = pts;
                 
                if(pts == 0) rt = i;
                else edges[pts].add(i);
                 
                inparr[i] = new ArrayList<>(); 
                for(int j=1; j<=num; j++){
                    inparr[i].add(Integer.parseInt(stk.nextToken())); 
                }
            }
             
            Ans = 0;
            que.clear();
             
            bfs(rt,0);
            make_sptable();
            depth[0] = Integer.MAX_VALUE;
                  
            for(int i=1; i<=N; i++){
                int nowrt = 0;
                 
                for(int j:inparr[i]){
                    int k = lca(i,j);
                    if(k == rt){
                        nowrt = k; break;
                    }
                    if(j == k && depth[nowrt] > depth[k]) nowrt = k;
                }
                 
                Ans += (depth[i] - depth[nowrt])*2;
            }
          
            bw.write("#"+tc+" "+String.valueOf(Ans)+"\n");
             
        }
 
        bw.flush();
    }
      
    private static int lca(int x1, int x2) {
        if(depth[x1] < depth[x2]) return 999999;
              
        for(int i = sz-1; i>=0; i--){
            if(((depth[x1] - depth[x2]) & (1 << i)) > 0){
                x1 = sparsetable[x1][i];
            }
        }
              
        if(x1 == x2) return x1;
        else return 99999999;
    }
      
    private static void make_sptable() {
        for(int k = 1; k<sz; k++){
            for(int i=1; i<=N; i++){
                sparsetable[i][k] = sparsetable[sparsetable[i][k-1]][k-1];
            }   
        }
    }
    private static void bfs(int n, int dep) {
          
        depth[n] = dep;
        que.add(n);
         
        while(!que.isEmpty()){
            int nnow = que.poll();
             
            for(int to:edges[nnow]){
                que.add(to);
                depth[to] = depth[nnow] + 1;
            }
        }
    }
}
