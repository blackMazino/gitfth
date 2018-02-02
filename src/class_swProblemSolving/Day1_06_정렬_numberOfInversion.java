package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//강사풀이
public class Day1_06_정렬_numberOfInversion {

/*
Inversion이란, 어떤 수열 A에서 i < j일 때 A_i > A_j 인 경우를 의미한다. 
예를 들어, 수열 A의 원소가 각각 순서대로 3, 1, 2인 경우, (i=1, j=2)인 Inversion과 (i=1, j=3)인 Inversion 두 개가 존재한다.
1부터 N까지 수들로 이루어진 순열이 주어질 때, Inversion의 개수를 출력하라.
첫 번째 줄에 순열의 길이 N이 주어진다. (2 ≤ N ≤ 100,000)
두 번째 줄에 순열의 각 원소가 공백으로 분리되어 주어진다.
첫 번째 줄에 주어진 순열에 존재하는 Inversion의 개수를 출력하라
===============
5
5 3 2 4 1
===============
8
 * */	
	
	static int TC;
	static int n;
	static int a[] = new int [121212];
	static int b[] = new int [121212];
	static int w_j[] = new int [121212];
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=n;i++){
				a[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println(f(1,n));
//		}

		br.close();

	}
	private static long f(int s, int e) {
		long res = 0;		
		if(s>=e){ 
			return 0;
		}
		int m = (s+e)/2;
		res += f(s,m);
		res += f(m+1,e);
		int i,j;
		int p1 = s; int p2=m+1;
		w_j[s-1] = m;
		for(i=s;i<=m;i++){
			w_j[i] = w_j[i-1];
			while(w_j[i]<e && a[i]>a[ w_j[i]+1 ]){
				w_j[i]++;				
			}			
		}
		for(i=s;i<=m;i++){
			res += w_j[i] - (m+1) +1;
		}
		//MergeSort
		for(i=s;i<=e;i++){
			if(p1==m+1){
				b[i] = a[p2++];
			}else if(p2 == e+1){
				b[i] = a[p1++];
			}else if(a[p1] < a[p2]){
				b[i] = a[p1++];
			}else{
				b[i] = a[p2++];
			}				
		}
		for(i=s;i<=e;i++){
			a[i] = b[i];
		}
		return res;
	}
}