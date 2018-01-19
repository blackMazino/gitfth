import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Solution{
    static int T,N;
    static long[][] iD;
    static int iMod=100000123;
     
    public static void main(String args[]) throws Exception{
        long[] ans;
         
        iD=new long[2049][4097];
         
        for(int i=1;i<=2048;i++) iD[i][i]=1;
        for(int i=1;i<=4096;i++) iD[1][i]=i;
         
        for(int i=2;i<=2048;i++) {
            for(int j=i;j<=4096;j++) {
                iD[i][j]=iD[i-1][j-1]+iD[i][j-1];
                iD[i][j]%=iMod;
            }
        }
         
        ans=new long[12];
        ans[0]=1;
        for(int i=1;i<=11;i++) {
            int a=(int)Math.pow(2, i)-1;
            int b=(int)Math.pow(2, i+1)-2;
             
            ans[i]=iD[a][b];
            ans[i]%=iMod;
            ans[i]*=ans[i-1];
            ans[i]%=iMod;
            ans[i]*=ans[i-1];
            ans[i]%=iMod;
        }
         
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         
        T=Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            N=Integer.parseInt(br.readLine());
            System.out.println("#"+tc+" "+ans[N]);
        }
    }
}
