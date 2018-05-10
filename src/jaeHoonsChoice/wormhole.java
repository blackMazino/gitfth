package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
// BellmanFord 알고리즘 사용한다.
// 음수 사이클이 있는 경우에는 Yes, 아니면 No
// - 가중치의 합이 음수인 경우
// 시작점을 잘 잡으면 모든 누적합이 음수가 된다.
// => 누적합이 무조건 음수가 되는 값이 한개는 존재한다.
 
class Edges {
    public Edges(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    int a, b, c;
}
 
public class wormhole {
     
    static int T, N, M, W;
    static ArrayList<Edges> edge;
    static int dist[];
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
    	System.setIn(new FileInputStream("sample/wormhole.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        T = Integer.parseInt(br.readLine());
         
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
             
            edge = new ArrayList<>();
            for(int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                 
                edge.add(new Edges(a, b, c));
                edge.add(new Edges(b, a, c));
            }
             
            for(int i = 1; i <= W; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                 
                edge.add(new Edges(a, b, -c));
            }
             
            dist = new int[N+1];
            boolean stopped = false;
            for(int i = 1; i <= N; i++) {
                boolean sw = false;
                for(Edges e: edge) {
                    if(dist[e.b] > dist[e.a] + e.c) {
                        dist[e.b] = dist[e.a] + e.c;
                        sw = true;
                    }
                }
                if(!sw) {
                    stopped = true;
                    break;
                }
            }
            bw.write(stopped ? "NO\n" : "YES\n");
        }
        bw.flush();
    }
}
