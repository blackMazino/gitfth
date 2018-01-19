import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution{
    static int T,N,Q,Answer;
    static int[] par;
     
    public static void main(String args[]) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
         
        T=Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            N=Integer.parseInt(br.readLine());
            Q=Integer.parseInt(br.readLine());
             
            par=new int[N+1];
             
            for(int i=1;i<=N;i++) par[i]=i;
 
            Answer=0;
            for(int i=1;i<=Q;i++) {
                StringTokenizer st=new StringTokenizer(br.readLine());
                 
                int f=Integer.parseInt(st.nextToken());
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                 
//              System.out.println("f="+f+",a="+a+",b="+b);
                 
                if (f==0) {
                    union(a,b);
 
//                  for(int j=1;j<=N;j++) System.out.print(par[j]+" ");
//                  System.out.println("");
                     
                } else {
                    if (find(a)==find(b)) Answer++;
//                  System.out.println("Answer="+Answer);
                }
                 
                 
            }
             
            bw.write("#"+tc+" "+Answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
     
    public static void union(int a,int b) {
        int s=find(a);
        int e=find(b);
         
        if (s==e) return;
         
        par[e]=s;
    }
     
    public static int find(int n) {
        if(par[n]==n) return n;
        else return par[n]=find(par[n]);
    }
}
