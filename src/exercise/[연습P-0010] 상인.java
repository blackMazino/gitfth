import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution{
    static int T,N,pow,sz;
    static long ans;
    static int[][] iSpt;
    static int[] dep;
    static boolean[] bVisit;
    static ArrayList<Integer>[] con;
     
    public static void main(String args[]) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         
        T=Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            N=Integer.parseInt(br.readLine());
             
            dep=new int[N+1];
            bVisit=new boolean[N+1];
            con=new ArrayList[N+1];
            for(int i=1;i<=N;i++) {
                con[i]=new ArrayList<>();
                bVisit[i]=false;
            }
             
            pow=1; sz=0;
            while(pow<=N) {
                pow*=2;
                sz++;
            }
             
            iSpt=new int[N+1][sz+1];
             
            for(int i=1;i<N;i++) {
                StringTokenizer st=new StringTokenizer(br.readLine());
                 
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                 
                con[a].add(b); con[b].add(a);
            }
             
            Queue<Integer> que=new LinkedList<>();
             
            que.add(1);
            dep[1]=0; bVisit[1]=true;
             
            while(!que.isEmpty()) {
                int q=que.poll();
                 
                for(int n:con[q]) {
                    if (bVisit[n]) continue;
                    bVisit[n]=true;
                    dep[n]=dep[q]+1;
                    iSpt[n][0]=q;
                    que.add(n);
                }
            }
             
            for(int i=1;i<=sz;i++) {
                for(int j=1;j<=N;j++) {
                    iSpt[j][i]=iSpt[iSpt[j][i-1]][i-1];
                }
            }
             
            ans=0;
            for(int i=1;i<N;i++) {
                int lca=LCA(i,i+1);
                ans+=dep[i]-dep[lca]+dep[i+1]-dep[lca];
            }
             
            System.out.println("#"+tc+" "+ans);
        }
    }
     
    static int LCA(int a,int b) {
        if (dep[a]<dep[b]) {
            a ^= b; b ^= a; a ^= b;
        }
         
        for(int i=0;i<=sz;i++) {
            if (((1<<i)&(dep[a]-dep[b]))!=0) {
                a=iSpt[a][i];
            }
        }
         
        if (a==b) return a;
         
        for(int i=sz;i-->0;) {
            if (iSpt[a][i]!=iSpt[b][i]) {
                a=iSpt[a][i]; b=iSpt[b][i];
            }
        }
         
        return iSpt[a][0];
    }
}
