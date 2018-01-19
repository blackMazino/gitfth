import java.io.FileInputStream;
import java.util.Scanner;
 
class Solution{
 
    static int T, N, M, Answer;
    static int[][] edge = new int[105][2];
 
  //�����迭
 
  static int[][] mat;
  static int[] visited;
 
public static void main(String[] args) throws Exception {
 
 
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++){
            N = sc.nextInt();
        M = sc.nextInt();
 
            //���� �迭 ���� : ũ��(N+1) ����
            mat = new int[N+1][N+1];
            for(int i=0; i<M; i++){ // ���� edge
                int v1 = sc.nextInt(); 
                int v2 = sc.nextInt();
                edge[i][0]=v1;
                edge[i][1]=v2;
 
                //���� �迭 �Է� (�ѹ��� ����)
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
            //�湮���� �ʾҰ� ������ ������ ���
            if(visited[i]==0 && mat[pos][i]==1){
                visited[i]=1;
                dfs(i, cnt+1);
                visited[i]=0;   //VISITEDüũ ���� �߿�
            }
        }
    }
}
