package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class day1_01nQueen {
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
