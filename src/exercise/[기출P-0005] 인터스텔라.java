import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
class Solution {
    static int T,N,M,K,start,end,a,b,c;
    static ArrayList<Integer>[] con = new ArrayList[100000+1];
    static ArrayList<Integer>[] conv = new ArrayList[100000+1];
     
    static long D[][];// = new long[100000+1][2+1];
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.ini"));
        //System.setIn(new FileInputStream("sample_input.ini"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
         
         
        for(int i=1;i<=100000;i++) {
            con[i] = new ArrayList<Integer>();
            conv[i] = new ArrayList<Integer>();
        }
         
        for(int tc=1;tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //행성갯수
            M = Integer.parseInt(st.nextToken()); //경로수
            K = Integer.parseInt(st.nextToken()); //패킷수
            /*
            con = new ArrayList[N+1];
            conv = new ArrayList[N+1];
             
            for(int i=1;i<=N;i++) {
                con[i] = new ArrayList<Integer>();
                conv[i] = new ArrayList<Integer>();
            }
            */
             
            for(int i=1;i<=100000;i++) {
                con[i].clear();
                conv[i].clear();
            }
             
             
             
            for(int i=1;i<=M;i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                 
                con[a].add(b); con[b].add(a);
                conv[a].add(c); conv[b].add(c);
            }
             
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
             
            D = new long[N+1][K+1];
             
             
            for(int i=1;i<N+1;i++) {
                for(int j=0;j<K+1;j++) {
                    D[i][j] = Long.MAX_VALUE;
                }
            }
             
            PriorityQueue<long[]> Q = new PriorityQueue<>(10, new Comparator<long[]>() {
                public int compare(long[] o1, long[] o2) {
                    return (int)(o1[0]-o2[0]);
                }
            });
             
            for(int j=0;j<=K;j++) {
                D[start][j] = 0;
            }
             
            Q.add(new long[]{0, start, 0});
 
            boolean arrive = false ;
            while( !Q.isEmpty() && !arrive ) {
                long d = Q.peek()[0];
                int q = (int)Q.peek()[1];
                int p_cnt = (int)Q.peek()[2];
                 
                Q.poll();
                 
                if(D[q][p_cnt] != d) continue;
                 
                if(D[end][p_cnt] == d) break;
                 
                for(int i=0;i<con[q].size();i++) {
                    int t = con[q].get(i);
                    int v = conv[q].get(i);
                     
                    if ( D[t][p_cnt] > D[q][p_cnt] + v ) {
                        D[t][p_cnt] = D[q][p_cnt] + v;
                         
                        Q.add(new long[]{D[t][p_cnt], t, p_cnt});
                    }
                     
                    if ( ( p_cnt < K ) &&
                         ( D[t][p_cnt+1] > D[q][p_cnt] + 1 ) ) {
                        D[t][p_cnt+1] = D[q][p_cnt] + 1;
                         
                        Q.add(new long[]{D[t][p_cnt+1], t, p_cnt+1});
                    }
                }
            }
             
            long ans = Long.MAX_VALUE;
            for(int i=0;i<=K;i++) {
                ans = Math.min(ans, D[end][i]);
            }
             
            System.out.println("#"+tc+" "+ans);
        }
    }
}
