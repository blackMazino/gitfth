package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//탐색
public class Day1_01_탐색_nQueen {
/*
N-Queen문제는 유명한 문제이다. 이는 N × N인 체스판 위에 N개의 퀸을 서로 공격할 수 없게 놓는 문제이다.
N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하시오.
첫 번째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 12)
첫 번째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
==================
4
==================
2
 * */
	static int answer;
    static int N;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 한 줄에 정수 하나가 주어지는 경우
		N = Integer.parseInt(br.readLine());
		int []queens = new int[N];
		
		putQueen(queens,0);		
		
		System.out.println(answer);
		br.close();

	}
	//back tracking
	private static void putQueen(int[] q, int len) {
       if (len == N)
            answer++;
        LOOP:
        for (int j = 0; j < N; j++) {
            for (int m = 0; m < len; m++){
                if (q[m] == j || Math.abs(m - len) == Math.abs(q[m] - j)) 
                    continue LOOP;
            }
            q[len] = j;
            putQueen(q, len + 1);
        }
    }		

}
/*  
//nQueen
import java.io.BufferedReader;
import java.io.InputStreamReader;
   
public class source {
    static int N, ans;
    static boolean[] col, d1, d2;
      
    static void dfs(int y) {
        if (y == N){ ans++; return; }
        for (int x=0;x<N;x++){
            if (col[x] || d1[y+x] || d2[y-x+N-1]) continue;
            col[x] = d1[y+x] = d2[y-x+N-1] = true;
            dfs(y+1);
            col[x] = d1[y+x] = d2[y-x+N-1] = false;
        }
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        col = new boolean[N];
        d1 = new boolean[N+N-1]; d2 = new boolean[N+N-1];
        dfs(0);
        System.out.println(ans);
    }
}
*/