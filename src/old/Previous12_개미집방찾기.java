package old;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Previous12_개미집방찾기 {

/*
 * (입력)
3
8
5 0 4 8 5 3 7
4 0 2 1 4
8 1 4 1 4 7 8
1 0 4 7 3 4 6
0 0 3 3 5 8
4 1 2 7 4
8 0 2 5 8
5 1 1 5
5
3 0 3 3 5 2
1 1 2 4 1
0 1 4 1 2 3 4
1 0 1 3
3 1 4 1 3 4 5
3
0 0 1 1
1 0 1 1
1 1 1 1
(출력)
#1 16
#2 10
#3 4

(sample_input.txt에 대한 출력)
#1 328
#2 304
#3 856
#4 900
#5 7360
#6 6940
#7 16340
#8 110224
#9 253970
#10 132502390

 */
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
//	          System.out.println("sz="+sz+",N="+N);
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
