package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class day1_01nQueen2 {
/*
N-Queen문제는 유명한 문제이다. 이는 N × N인 체스판 위에 N개의 퀸을 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하시오.
첫 번째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 12)

 * */
	static int ans;
    static int N;
    static boolean check1[] = new boolean [100];
    static boolean check2[] = new boolean [100];
    static boolean check3[] = new boolean [100];
    static int x[] = new int [100];
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 한 줄에 정수 하나가 주어지는 경우
		N = Integer.parseInt(br.readLine());
		//int []queens = new int[N];
		
		dfs(0);		
		
		System.out.println(ans);
		br.close();

	}
	static void dfs(int w){
		int i;
		if(w==N){
			ans++;
			return;
		}
		for(i=0;i<N;i++){
			if(check1[i]||check2[w+i]||check3[w-i+N]){
				continue;
			}
			check1[i] = check2[w+i] = check3 [w-i+N] = true;
			x[w] = i;
			dfs(w+1);
			check1[i] = check2[w+i] = check3 [w-i+N] = false;
		}
		
	}
}
