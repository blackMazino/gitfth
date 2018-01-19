import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class Solution{
    static int T,N,M;
    static int[] par;
    static ArrayList<MEM> mems;
     
    private static class MEM{
        public MEM(int a,int b,int c) {
            this.a=a; this.b=b; this.c=c;
        }
        int a,b,c;
    }
     
    public static void main(String args[]) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         
        T=Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            N=Integer.parseInt(br.readLine());
            M=Integer.parseInt(br.readLine());
             
            par=new int[N+1];
            mems=new ArrayList<>();
             
            for(int i=1;i<=M;i++) {
                StringTokenizer st=new StringTokenizer(br.readLine());
                 
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                int c=Integer.parseInt(st.nextToken());
                 
                mems.add(new MEM(a,b,c));
            }
             
            /*
            for(int i=1;i<=M;i++) {
                System.out.println(mems.get(i-1).a+" "+mems.get(i-1).b+" "+mems.get(i-1).c);
            }
            */
 
            Collections.sort(mems, new Comparator<MEM>() {
                public int compare(MEM a,MEM b) {
                    return a.c-b.c;
                }
            });
             
            /*
            System.out.println();
            for(int i=1;i<=M;i++) {
                System.out.println(mems.get(i-1).a+" "+mems.get(i-1).b+" "+mems.get(i-1).c);
            }
            */
             
            for(int i=1;i<=N;i++) par[i]=i;
             
            int ans=0;
            for(int i=1;i<=M;i++) {
                int s=find(mems.get(i-1).a);
                int e=find(mems.get(i-1).b);
                 
                if (s==e) continue;
                par[e]=s;
                ans+=mems.get(i-1).c;
            }
             
            System.out.println("#"+tc+" "+ans);
        }
    }
     
    public static int find(int n) {
        if(par[n]==n) return n;
        else return par[n]=find(par[n]);
    }
}
