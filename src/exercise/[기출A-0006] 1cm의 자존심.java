import java.io.FileInputStream;
import java.util.Scanner;
 
class Solution{
 
    static int T, N, M, Answer;
    static int[][] edge = new int[105][2];
 
  //인접배열
 
  static int[][] mat;
  static int[] visited;
 
public static void main(String[] args) throws Exception {
 
 
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++){
            N = sc.nextInt();
        M = sc.nextInt();
 
            //인접 배열 생성 : 크기(N+1) 주의
            mat = new int[N+1][N+1];
            for(int i=0; i<M; i++){ // 관계 edge
                int v1 = sc.nextInt(); 
                int v2 = sc.nextInt();
                edge[i][0]=v1;
                edge[i][1]=v2;
 
                //인접 배열 입력 (한방향 간선)
                mat[v1][v2]=1;
            }
 
            visited = new int[N+1];
            System.out.print("#"+test_case+" ");
            for(int i=1;i<=N;i++){
                Answer=0;
                visited[i]=1;
                dfs(i, 1);
                System.out.print(Answer+" ");
                visited[i]=0;
            }
            System.out.println();
        }
    }
 
public static void dfs(int pos, int cnt){
 
        Answer = Math.max(Answer, cnt);
 
        for(int i=1;i<=N;i++){
            //방문하지 않았고 간선이 존재할 경우
            if(visited[i]==0 && mat[pos][i]==1){
                visited[i]=1;
                dfs(i, cnt+1);
                visited[i]=0;   //VISITED체크 해제 중요
            }
        }
    }
}
