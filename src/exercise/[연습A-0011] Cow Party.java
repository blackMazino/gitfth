import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Solution{
    static int T,N,M,X,ans;
    static ArrayList<ROAD>[] road1,road2;
    static boolean[] bVisit;
    static int[] par1,par2;
    static PriorityQueue<int[]> minH;
     
    private static class ROAD{
        public ROAD(int a,int b) {
            this.a=a; this.b=b;
        }
        int a,b;
    }
     
    public static void main(String args[]) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         
        T=Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
             
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            X=Integer.parseInt(st.nextToken());
             
            road1=new ArrayList[N+1];
            road2=new ArrayList[N+1];
             
            for(int i=1;i<=N;i++) {
                road1[i]=new ArrayList<>();
                road2[i]=new ArrayList<>();
            }
             
            for(int i=1;i<=M;i++) {
                st=new StringTokenizer(br.readLine());
                 
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                int c=Integer.parseInt(st.nextToken());
                 
                road1[a].add(new ROAD(b,c));
                road2[b].add(new ROAD(a,c));
            }
             
            /*
            for(int i=1;i<=N;i++) {
                System.out.print("road1["+i+"]=");
                for(int j=0;j<road1[i].size();j++) {
                    System.out.print("("+road1[i].get(j).a+","+road1[i].get(j).b+")");
                }
                System.out.println();
            }
            System.out.println();
            for(int i=1;i<=N;i++) {
                System.out.print("road2["+i+"]=");
                for(int j=0;j<road2[i].size();j++) {
                    System.out.print("("+road2[i].get(j).a+","+road2[i].get(j).b+")");
                }
                System.out.println();
            }
            */
             
            par1=new int[N+1]; par2=new int[N+1];
            bVisit=new boolean[N+1];
             
            for(int i=1;i<=N;i++) {
                par1[i]=Integer.MAX_VALUE; par2[i]=Integer.MAX_VALUE;
            }
             
            ans=0;
            minH=new PriorityQueue<>(10, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[1]-b[1];
                }
            });
             
            iCalc(par1,road1,X,0);
            iCalc(par2,road2,X,0);
             
            for(int i=1;i<=N;i++) ans=Math.max(ans, par1[i]+par2[i]);
             
            System.out.println("#"+tc+" "+ans);
             
        }
    }
     
    static void iCalc(int[] par,ArrayList<ROAD>[] Lists, int s, int v) {
        par[s]=0; Arrays.fill(bVisit, false);
        minH.add(new int[]{s,v});
         
        while(!minH.isEmpty()) {
            int q=minH.poll()[0];
            if (bVisit[q]) continue;
            bVisit[q]=true;
             
            for(ROAD e:Lists[q]) {
                int p=e.a; int r=e.b;
                if (par[p]>par[q]+r) {
                    par[p]=par[q]+r;
                    minH.add(new int[]{p,par[p]});
                }
            }
        }
    }
}
