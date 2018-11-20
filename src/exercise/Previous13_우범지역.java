package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Previous13_우범지역 {
/*
[입출력 예]
(입력)
2
8 1
1 4
5 4
7 5
5 8
3 7
6 5
2 6
1 0 0 1 1 0 0 1
5 6
1 2
2 3
3 4
4 5
1 0 1 1 0
 
(출력)
#1 12
#2 15
*/
	static int T,N,K;// 1<=N<=100,000, 0<=K<=100
	static ArrayList<Integer>[] con;
	static int [] crime;
	static int [][]d;
	static long answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());	
		
		for(int tc=1;tc<=T;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
		}
		crime = new int[N+1];
		con = new ArrayList[N+1];
		
		for(int i=1;i<=N;i++)con[i] = new ArrayList<>();
		
		for(int i=1;i<N;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			con[a].add(b); con[b].add(a);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++){
			crime[i] = Integer.parseInt(st.nextToken());
		}
		d = new int [K+1][N+1];//K:거리 0은 본인 , N : node 수
		answer = 0;
		for(int k=0;k<=K;k++){
			for(int n=1;n<=N;n++){
				if(k==0){
					d[k][n] = crime[n];
					continue;
				}
				d[k][n] = d[k-1][n];
				for(int t:con[n]){
//					d[][]
				}
			}
		}
	}

}
