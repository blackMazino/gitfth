package class_swProblemSolving;
import java.io.*;
import java.util.*;


public class DayAnother_03Graph_LCA_촌수계산 {
	    static int N, M, A, B;
	    static int[] par, dep;
	    static boolean[] is_root;
	    static ArrayList <Integer>[] con;
	     
	    static void dfs(int n) {
	        for (int t: con[n]){
	            par[t] = n; dep[t] = dep[n]+1;
	            dfs(t);
	        }
	    }
	     
	    static int slow_lca(int a, int b) {
	        boolean[] vis = new boolean[N+1];
	        for (int t=a;t>0;t=par[t]) vis[t] = true;
	        for (int t=b;t>0;t=par[t]){
	            if (vis[t]) return t;
	        }
	        return 0;
	    }
	     
	    public static void main(String[] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        N = Integer.parseInt(br.readLine());
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        A = Integer.parseInt(st.nextToken());
	        B = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(br.readLine());
	        is_root = new boolean[N+1];
	        con = new ArrayList[N+1];
	        for (int i=1;i<=N;i++){
	            is_root[i] = true;
	            con[i] = new ArrayList<>();
	        }
	        for (int i=0;i<M;i++){
	            st = new StringTokenizer(br.readLine());
	            int a = Integer.parseInt(st.nextToken());
	            int b = Integer.parseInt(st.nextToken());
	            con[a].add(b);
	            is_root[b] = false;
	        }
	        par = new int[N+1];
	        dep = new int[N+1];
	        for (int i=1;i<=N;i++) if (is_root[i]){
	            dfs(i); // Make tree!
	        }
	        int c = slow_lca(A, B);
	        System.out.println(c == 0 ? -1 : dep[A]+dep[B]-2*dep[c]);
	    }
	}

