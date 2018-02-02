package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day4_03_DP_LCS {

/*
LCS(최장 공통 부분수열, Longest Common Subsequence)란, 
두 수열이 있을 때 두 수열의 공통 부분수열 중 가장 긴 공통 부분수열의 길이를 의미한다.

예를 들어, 수열이 1,4,2,5와 1,2,3,4,5의 LCS는1,2,5이다.
LCS 문제는 수열이 아닌 문자열에서 주로 다루기도하는데 
이 때 문자열의 각 문자가 수열의 수에 해당한다고 보면 된다. 
따라서 두 문자열 "ABCBDAB", "BDCABA"의 LCS는 "BCBA", "BDAB", "BCAB" 등이 된다.

두 문자열이 주어졌을 때, 두 문자열의 LCS를 구하는 프로그램을 작성하시오.

입력
첫 줄에 첫 번째 문자열이 주어지고, 둘째 줄에 두 번째 문자열이 주어진다. 주어지는 문자열은 영어 대문자로만 구성되어 있으며 길이가 1,000을 넘지 않는다.

출력
첫 줄에 주어진 두 문자열의 LCS를 출력한다. 만약 답이 여러 가지인 경우 아무거나 하나 출력한다.

ABCBDAB
BDCABA

BCBA
*/
	static String str1, str2;
	static int N,M;
	static int [][] D, direction;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			str1 = st.nextToken();
			st = new StringTokenizer(br.readLine());
			str2 = st.nextToken();
			N = str1.length();
			M = str2.length();
					
			D = new int [N+1][M+1];
			
			direction = new int [N+1][M+1];
			//1 = 위, 2 = 대각선(같을경우), 0=default, left 
			
			//fill the tables
			for(int i=1; i<=N; i++){
				for(int j=1;j<=M; j++){
					//default
					D[i][j] = D[i][j-1];
					//위에서 내려온경우
					if(D[i][j] < D[i-1][j]){
						D[i][j] = D[i-1][j];
						direction[i][j] = 1;
					}
					//대각선, 같을경우 +1해준다
					if(str1.charAt(i-1) == str2.charAt(j-1)){
						if(D[i][j] < D[i-1][j-1]+1){
							D[i][j] = D[i-1][j-1]+1;
							direction[i][j] = 2;
						}						
					}					
				}
			}
			StringBuffer sb;
			sb = new StringBuffer();
			//뒤에서부터 내려온다
			int i=N;
			int j=M;
			while( i>0 && j>0 ){
				if(direction[i][j] == 2){//대각선인 경우 담는다
					i--;
					j--;
					sb.append(str1.charAt(i));
				}else if(direction[i][j] == 1){//위에서 온경우 
					i--;
				}else{//default 왼쪽에서 온경우
					j--;
				}				
			}
			String reverse = sb.toString();
			sb = new StringBuffer();
			for(int z = reverse.length()-1; z>=0;z--){
				sb.append(reverse.charAt(z));
			}
			System.out.println(sb.toString());
//		}
		br.close();
	}
}


/*
//LCS
import java.io.BufferedReader;
import java.io.InputStreamReader;
  
public class source {
    static int N, M;
    static String A, B;
    static int[][] D, F;
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();
        N = A.length(); M = B.length();
        D = new int[N+1][M+1]; F = new int[N+1][M+1];
        for (int i=1;i<=N;i++) for (int j=1;j<=M;j++){
            D[i][j] = D[i][j-1];
            if (D[i][j] < D[i-1][j]){
                D[i][j] = D[i-1][j];
                F[i][j] = 1; // from up
            }
            if (A.charAt(i-1) == B.charAt(j-1) && D[i][j] < D[i-1][j-1]+1){
                D[i][j] = D[i-1][j-1]+1;
                F[i][j] = 2; // from up-left
            }
        }
        String ans = "";
        for (int i=N,j=M;i>0&&j>0;){
            if (F[i][j] == 0){
                j--;
            }else if (F[i][j] == 1){
                i--;
            }else if (F[i][j] == 2){
                i--; j--;
                ans += A.charAt(i);
            }
        }
        System.out.println(new StringBuilder(ans).reverse());
    }
}
*/