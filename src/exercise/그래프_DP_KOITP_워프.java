package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
우주에 N개의 행성이 있다. 어떤 행성쌍에는 워프장치가 존재하는데
ai행성에서bi행성으로 워프를 타고ci시간만에 이동할 수 있다. 
이때, 워프장치가 설치되어 있는 형태는 ai<bi를 만족한다. 
또한, 행성 간의 이동은 오직 워프장치를 통해서만 할 수 있다. 
1번 행성에 N번 행성까지 이동하려고 할 때, 최소로 드는 시간을 구하여라.

입력
첫 번째 줄에 행성의 개수N
과 워프장치의 개수 M
이 공백으로 분리되어 주어진다.(1≤N≤100,000,1≤M≤500,000)
두 번째 줄부터 각 워프장치의 정보가 
(ai,bi,ci)의 형태로 주어진다. 
(1≤ai,bi≤N,1≤ci≤100,000)
출력첫 번째 줄에1번 행성에서 N번 행성에 도달하는 최소의 시간을 출력한다. 
만약 그러한 경로가 없다면 −1을 출력한다.

3 3
1 2 1
2 3 1
1 3 3

2
*/
public class 그래프_DP_KOITP_워프 {
	
	static int N,M;
	static ArrayList<Integer> con[];
	static ArrayList<Integer> conW[];
	static long D[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		con = new ArrayList[N+1]; conW = new ArrayList[N+1];
		D = new long[N+1];
		for(int i =1;i<=N;i++ ){
			con[i] = new ArrayList<>();
			conW[i] = new ArrayList<>();
			D [i] = Long.MAX_VALUE;
		}
		
		for(int i=1;i<=M;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			con[a].add(b); 
			conW[a].add(c); 
		}

		D[1] = 0;
		for(int i=1;i<=N;i++){
			if(D[i] < Long.MAX_VALUE){
				for (int j=0;j<con[i].size();j++){
					int k = con[i].get(j);
					int w = conW[i].get(j);
					D[k] = Math.min(D[k], D[i]+w);
				}
			}
		}
		
		System.out.println(D[N] < Long.MAX_VALUE ? D[N] : -1);

	}

}
