import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
 
public class Solution {
    static int T,N,K=0;
    static StringTokenizer st;
    static ArrayList<Integer>[] node;
    static long chk[][];
    static int max_cnt = 0;
    static long Answer = 0;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            chk = new long[K+1][N+1];
             
            for(int i=0;i<=K;i++){
                for(int j=0;j<=N;j++){
                    chk[i][j] = 0;
                }
            }
            node = new ArrayList[N+1];
            for(int j=1;j<=N;j++){
                node[j] = new ArrayList<Integer>();
            }
            int from, to =0;
            for(int j=1;j<N;j++){
                st = new StringTokenizer(br.readLine());
                from = Integer.parseInt(st.nextToken());
                to   = Integer.parseInt(st.nextToken());
                node[from].add(to);
                node[to].add(from);
            }
            st = new StringTokenizer(br.readLine());
            int cnt=0;
            for(int i=1;i<=N;i++){
                cnt =  Integer.parseInt(st.nextToken());
                chk[0][i] = cnt;
                if (cnt > 0){
                    max_cnt += cnt;
                }
                 
                 
            }
            for(int k=1;k<=K;k++){
                for(int i=1;i<=N;i++){
                    if( k == 1){
                        chk[k][i] = chk[k-1][i] + calc(i,k);
                    } else {
                        chk[k][i] = calc(i,k) - (chk[k-2][i]*(node[i].size() - 1));
                    }
                }
            }
            Answer = 0;
            for(int i=1;i<=N;i++){
                Answer += chk[K][i];
            }
            System.out.println("#"+t+" "+Answer);
        }
         
         
    }
    private static long calc(int nd, int k2) {
        // TODO Auto-generated method stub
        long ans = 0;
        for(int i=0;i<node[nd].size();i++){
            ans += chk[k2-1][node[nd].get(i)];
        }
        return ans;
    }
}
