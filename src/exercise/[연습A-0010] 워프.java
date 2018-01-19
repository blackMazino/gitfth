import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution{
    static int T,N,M;
    static long Answer;
    static long[] D;
    static ArrayList<Mem>[] mems;
     
    private static class Mem{
        public Mem(int a,int b) {
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
             
            D=new long[N+1];
            mems=new ArrayList[N+1];
             
            for(int i=1;i<=N;i++) {
                mems[i]=new ArrayList<>();
                D[i]=Long.MAX_VALUE;
            }
             
            for(int i=1;i<=M;i++) {
                st=new StringTokenizer(br.readLine());
                 
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                int c=Integer.parseInt(st.nextToken());
                 
                mems[b].add(new Mem(a,c));
            }
             
            /*
            for(int i=1;i<=N;i++) {
                for(int j=0;j<mems[i].size();j++) {
                    System.out.println(i+" : "+mems[i].get(j).a+" "+mems[i].get(j).b);
                }
            }
            */
             
            D[1]=0;
             
            for(int i=2;i<=N;i++) {
                for(int j=0;j<mems[i].size();j++) {
                    Mem m=mems[i].get(j);
                     
//                  System.out.println("D["+m.a+"]="+D[m.a]);
                    if (D[m.a]==Long.MAX_VALUE) continue;
                    D[i]=Math.min(D[i], D[m.a]+m.b);
//                  System.out.println("--D["+i+"]="+D[i]);
                }
            }
             
            Answer=D[N];
            if (Answer==Long.MAX_VALUE) Answer=-1;
             
            System.out.println("#"+tc+" "+Answer);
        }
    }
}
