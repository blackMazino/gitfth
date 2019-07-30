package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class DP_BOJ_지그재그서기 {
/*
https://www.acmicpc.net/problem/1146
N명의 학생들이 있다. 이 학생들을 다음과 같은 방식으로 줄을 세우려고 한다.

맨 앞줄에는 아무나 설 수 있다.
둘째 줄에도 아무나 설 수 있다.
셋째 줄에는 둘째 줄에 서 있는 사람이 첫째 줄에 서 있는 사람보다 클 경우, 둘째 줄에 서 있는 사람보다 작은 사람만이 설 수 있으며, 둘째 줄에 서 있는 사람이 첫째 줄에 서 있는 사람보다 작을 경우, 둘째 줄에 서 있는 사람보다 큰 사람만이 설 수 있다.
넷째 줄부터는 둘째 줄과 셋째 줄을 비교하는 식으로 해서 N번째의 줄을 서는 사람은 N-2번째 줄과 N-1번째 줄에 서는 사람을 비교해서 세운다.
학생들이 1이 가장 작은 사람, N이 가장 큰 사람이며, 같은 키를 가진 사람이 없다고 할 때, 5명을 세운다면 1 - 3 - 2 - 5 - 4, 3 - 2 - 5 - 1 - 4 등의 방법으로 세울 수 있다.

문제는 N명의 학생을 이런 식으로 줄을 세울 때 총 몇 가지의 경우의 수가 생기는지 찾아내는 것이다.

첫째 줄에 학생 수 N(1 ≤ N ≤ 100)이 입력된다.

5
32
	
*/
	static int TC;
	static int mod = 1000000;
	static int answer;
	static int [][][]d;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous51.txt"));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
//			
//		}
		int N = Integer.parseInt(br.readLine());
		d = new int[100+2][100+2][2];
		answer = 0;
		if(N==1){
			answer = 1;
		}else{
			for(int i=0;i<=100;i++){
				for(int j=0;j<=100;j++){
					d[i][j][1] = d[i][j][0] = -1;
				}
			}
			d[0][0][0] = d[0][0][1] = 1;
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(i==j) continue;
					
					int l = j-1-(i<j?1:0);
					int r = N-j-(i>j?1:0);
					answer += backtracking(l,r,(i>j?1:0));
					answer = answer%mod;
				}
			}
		}
		System.out.println(answer);

	}
	private static int backtracking(int l, int r, int dir) {
	    if( d[l][r][dir] != -1 ) {
	        return d[l][r][dir];
	    }
	    int sum = 0;
	    if( dir == 0 ) {
	        for(int i=1;i<=l;i++) {
	            sum += backtracking(l-i, r+(i-1), 1);
	            sum = sum % mod;
	        }
	    } else {
	        for(int i=1;i<=r;i++) {
	            sum += backtracking(l+(i-1), r-i, 0);
	            sum = sum % mod;
	        }
	    }
	    return (d[l][r][dir]=sum);

	}

}
