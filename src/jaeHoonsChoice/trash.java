package jaeHoonsChoice;
import java.io.*;
import java.util.*;
 
// 위 값에 현재 값을 더한 값과, 왼쪽 값에 현재 값을 더한 값을 비교하여 D배열에 넣는다.
// 도시의 크기만큼 다 돌리고, 맨 끝 부분의 값을 찾는다.
// MAX(D[i-1][j] + map[i][j], D[i][j-1] + map[i][j])
 
public class trash {
 
    static int N;
    static int D[][], map[][];
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
    	System.setIn(new FileInputStream("sample/trash.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        D = new int[N+1][N+1];
         
        // 입력 초기화
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
         
        // D 배열 초기값 지정
        D[1][1] = map[1][1];
         
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                D[i][j] = Math.max(D[i-1][j] + map[i][j], D[i][j-1] + map[i][j]);
            }
        }
         
        bw.write(D[N][N] + "\n");
        bw.flush();
    }
 
}