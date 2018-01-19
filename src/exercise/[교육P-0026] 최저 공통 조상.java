import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution{
    static int T,N,Q,ans;
    static int[][] par;
    static int[] dep;
    static ArrayList<Integer>[] con;
     
    public static void main(String args[]) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
         
        T=Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
             
            N=Integer.parseInt(st.nextToken());
            Q=Integer.parseInt(st.nextToken());
             
            par=new int[N+1][17];
             
            con=new ArrayList[N+1];
             
            for(int i=1;i<=N;i++) con[i]=new ArrayList<>();
             
            st=new StringTokenizer(br.readLine());
            for(int i=2;i<=N;i++) {
                par[i][0]=Integer.parseInt(st.nextToken());
                con[par[i][0]].add(i);
            }
             
            /*
            for(int i=1;i<=N;i++) System.out.println(par[i][0]);
            System.out.println();
             
            for(int i=1;i<=N;i++) System.out.println("con["+i+"]="+con[i]);
            System.out.println();
            */
             
            // Sparse Table
            for(int i=1;i<17;i++) {
                for(int j=1;j<=N;j++) {
                    par[j][i]=par[par[j][i-1]][i-1];
                }
            }
 
            /*
            for(int i=0;i<17;i++) {
                for(int j=1;j<=N;j++) {
                    System.out.println("par["+j+"]["+i+"]="+par[j][i]);
                }
            }
            */
             
            dep=new int[N+1];
             
            Queue<Integer> que=new LinkedList<>();
             
            que.add(1);
             
            while(!que.isEmpty()) {
                int q=que.poll();
                 
                for(int t:con[q]) {
                    dep[t]=dep[q]+1;
                     
                    que.add(t);
                }
            }
             
            bw.write("#"+tc);
            for(int i=1;i<=Q;i++) {
                st=new StringTokenizer(br.readLine());
                 
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                bw.write(" "+lca(a,b));
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
     
    static int lca(int a,int b) {
        if (dep[a]<dep[b]) {
            a ^= b; b ^=a; a ^=b;
        }
         
        for(int i=0;i<17;i++) {
            if (((1<<i)&(dep[a]-dep[b]))!=0) {
                a=par[a][i];
            }
        }
         
        if (a==b) return a;
         
        for(int i=17;i-->0;) {
            if (par[a][i]!=par[b][i]) {
                a=par[a][i];
                b=par[b][i];
            }
        }
         
        return par[a][0];
    }
}
