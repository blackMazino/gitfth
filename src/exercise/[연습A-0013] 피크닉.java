import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution{
    static int T,K,N,M,ans;
    static int[] iD,iStart;
    static ArrayList<Integer>[] con;
    static boolean[] bVisit;
     
    public static void main(String args[]) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         
        T=Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
             
            K=Integer.parseInt(st.nextToken());
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
             
            con=new ArrayList[N+1];
            iStart=new int[K+1];
            iD=new int[N+1];
            bVisit=new boolean[N+1];
             
            for(int i=1;i<=N;i++) {
                con[i]=new ArrayList<>();
                iD[i]=0;
            }
             
            for(int i=1;i<=K;i++) {
                iStart[i]=Integer.parseInt(br.readLine());
            }
             
            for(int i=1;i<=M;i++) {
                st=new StringTokenizer(br.readLine());
                 
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                 
                con[a].add(b);
            }
             
            for(int i=1;i<=K;i++) {
                Arrays.fill(bVisit, false);
                bfs(iStart[i]);
            }
            ans=0;
            for(int i=1;i<=N;i++) if (iD[i]==K) ans++;
             
            System.out.println("#"+tc+" "+ans);
        }
    }
     
    static void bfs(int iS) {
        Queue<Integer> que=new LinkedList<>();
         
        que.add(iS); bVisit[iS]=true; iD[iS]++;
         
        while(!que.isEmpty()) {
            int q=que.poll();
             
            for(int n:con[q]) {
                if (bVisit[n]) continue;
                bVisit[n]=true; iD[n]++; que.add(n);
            }
        }
    }
}
