package jaeHoonsChoice;

import java.io.*;
import java.util.*;
  
public class funny_game {
     
    static int n, m;
    static ArrayList<Integer> list[];
    static int grid[][];
    static int G[][];
    static int K;
     
    static int yy[] = {-1, 0, 1, 0}, xx[] = {0, 1, 0, -1};  // 왼, 오른, 위, 아래를 보기 위한 변수 
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
         
//      BufferedReader br = new BufferedReader(new FileReader("funny_game_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
         
        // 초기값 세팅
        grid = new int[n+1][m+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
         
        // Color 세팅
        G = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(G[i][j] == 0) {
                    G[i][j] = ++K;  // 그룹의 숫자 지정
                    color(i, j);  // 그룹을 같은 숫자로 만들어주기 위함
                }
            }
        }
         
        // BFS 돌려가며 각 그룹에서 가장 적은 상태의 길이를 찾는다.
        // 제일 짧은 길이가 동전을 뒤집었을 때 가장 적게 뒤집는 수가 되므로 정답이 됨
        list = new ArrayList[K+1];  // 그룹의 수만큼 BFS를 수행하기 위함
        for(int i = 1; i <= K; i++) list[i] = new ArrayList<>();  //ArrayList 초기화
         
        // 그룹의 번호를 ArrayList에 넣는다.
        // 중복되는 번호가 있기는 하지만, 그것을 무시할 수 있을 정도로 빠르므로 일단 넣는다.
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                for(int xy = 0; xy < 4; xy++) {
                    int y = i + yy[xy];
                    int x = j + xx[xy];
                     
                    if(y < 1 || y > n || x < 1 || x > m || G[y][x] != G[y][x]) continue;
                    list[G[i][j]].add(G[y][x]);
                }
            }
        }
         
        int solve = Integer.MAX_VALUE;
        for(int i = 1; i <= K; i++) {
            solve = Math.min(solve, bfs(i));  // 그룹 1부터 bfs를 돌려서 작은 값을 출력한다.
        }
        bw.write(String.valueOf(solve));
        bw.flush();
    }
  
    private static int bfs(int res) {
        // TODO Auto-generated method stub
        int D[] = new int[K+1];
        for (int i = 1; i < K+1; i++) {
            D[i] = Integer.MAX_VALUE;
        }
         
        Queue<Integer> que = new LinkedList<>();
        // 초기값
        que.add(res);
        D[res] = 0;
         
        while (!que.isEmpty()) {
            int q = que.poll();  // 큐에서 빼서
            for(int t : list[q]) {
                if(D[t] < Integer.MAX_VALUE) continue;  // 다른 그룹을 만나면 거리를 1 늘려준다.
                D[t] = D[q] + 1;
                que.add(t);
            }
        }
         
        int ret = 0;
        for(int i = 1; i <= K; i++) {
            ret = Math.max(ret, D[i]);
        }
         
        return ret;
    }
 
    // 4방향으로 계속 찾아가면서 재귀호출하여 연결되어 있는 4방향의 같은 그룹을 체크한다.
    private static void color(int y, int x) {
        // TODO Auto-generated method stub
        for (int i = 0; i < 4; i++) {
            int ny = y + yy[i];
            int nx = x + xx[i];
             
            if(ny < 1 || ny > n || nx < 1 || nx > m || grid[ny][nx] != grid[y][x] || G[ny][nx] != 0) continue;
            G[ny][nx] = G[y][x];
            color(ny, nx);
        }
    }
     
     
  
}