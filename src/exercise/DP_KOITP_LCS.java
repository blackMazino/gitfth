package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_KOITP_LCS {
/*
https://koitp.org/problem/LCS/read/
LCS(최장 공통 부분수열, Longest Common Subsequence)란, 두 수열이 있을 때 두 수열의 공통 부분수열 중 가장 긴 공통 부분수열의 길이를 의미한다.
예를 들어, 수열이 1,4,2,5와1,2,3,4,5의 LCS는1,2,5이다.
LCS 문제는 수열이 아닌 문자열에서 주로 다루기도하는데 이 때 문자열의 각 문자가 수열의 수에 해당한다고 보면 된다. 
따라서 두 문자열 "ABCBDAB", "BDCABA"의 LCS는 "BCBA", "BDAB", "BCAB" 등이 된다.
두 문자열이 주어졌을 때, 두 문자열의 LCS를 구하는 프로그램을 작성하시오.


ABCBDAB
BDCABA

BCBA
 */
	
	static int  d [][];
	static String solution[][];
	static char[] A,B;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken(); 
		A = str.toCharArray();
		st = new StringTokenizer(br.readLine());
		str = st.nextToken(); 
		B = str.toCharArray();
		d = new int[A.length+1][B.length+1];
		solution = new String [A.length+1][B.length+1];
		
		//LCS Table
		for(int i=1;i<=A.length;i++){
			for(int j=1;j<=B.length;j++){
				if(A[i-1]==B[j-1]){
					d[i][j] = d[i-1][j-1] +1;
					solution[i][j] = "diagonal";
				}else{
					d[i][j] = Math.max(d[i][j-1], d[i-1][j]);
					if(d[i][j] == d[i][j-1]){
						solution[i][j] = "left";
					}else{
						solution[i][j] = "top";
					}
				}
			}
		}
		int i = A.length;
		int j = B.length;
		StringBuffer sb = new StringBuffer();
		while(i>0 && j>0){
			if(solution[i][j]=="diagonal"){
				i--;j--;
				sb.append(A[i]);
			}else if(solution[i][j]=="top"){
				i--;
			}else if(solution[i][j]=="left"){
				j--;
			}
		}
		System.out.println(sb.reverse().toString());
	}

}
