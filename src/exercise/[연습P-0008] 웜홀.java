import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution{
    static int T,N,M,W;
    static ArrayList<ROAD> road;
    static int[] iD;
    static String ans;
     
    private static class ROAD{
        int s,e,d;
        public ROAD(int s, int e, int d) {
            this.s=s; this.e=e; this.d=d;
        }
    }
     
    public static void main(String args[]) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         
        T=Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
             
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            W=Integer.parseInt(st.nextToken());
             
            road=new ArrayList<ROAD>();
            iD=new int[N+1+1];
             
            for(int i=1;i<=M;i++) {
                st=new StringTokenizer(br.readLine());
                 
                int s=Integer.parseInt(st.nextToken());
                int e=Integer.parseInt(st.nextToken());
                int d=Integer.parseInt(st.nextToken());
                 
                road.add(new ROAD(s,e,d)); road.add(new ROAD(e,s,d));
            }
             
            //for(int i=1;i<=N;i++) System.out.println(road.get(i).s+" "+road.get(i).e+" "+road.get(i).d);
             
            for(int i=1;i<=W;i++) {
                st=new StringTokenizer(br.readLine());
                 
                int s=Integer.parseInt(st.nextToken());
                int e=Integer.parseInt(st.nextToken());
                int d=Integer.parseInt(st.nextToken());
                 
                road.add(new ROAD(s,e,-d));
            }
             
            for(int i=1;i<=N;i++) road.add(new ROAD(N+1,i,0));
             
            for(int i=1;i<=N+1;i++) iD[i]=Integer.MAX_VALUE;
             
            bmf(N+1);
             
            ans="NO";
             
            boolean bFind=false;
             
            for(int i=0;i<road.size();i++) {
                ROAD q=road.get(i);
                 
                if (iD[q.s]!=Integer.MAX_VALUE&&(iD[q.e]>iD[q.s]+q.d)) {
                    bFind=true;
                }
            }
             
            ans=bFind?"YES":"NO";
             
            System.out.println("#"+tc+" "+ans);
             
        }
    }
     
    static void bmf(int s) {
        iD[s]=0;
        for(int i=1;i<=N;i++) {
            for(int j=0;j<road.size();j++) {
                ROAD q=road.get(j);
                 
                if (iD[q.s]!=Integer.MAX_VALUE) iD[q.e]=Math.min(iD[q.e], iD[q.s]+q.d);
            }
        }
    }
}
