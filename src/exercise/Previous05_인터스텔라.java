package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Previous05_인터스텔라 {
/*
[입출력 예]
(입력)
3
6 6 0
1 2 3
5 1 2
5 6 5
3 4 3
6 4 6
2 3 3
1 4
6 6 1
1 2 3
5 1 2
5 6 5
3 4 3
6 4 6
2 3 3
4 1
6 6 2
1 2 3
5 1 2
5 6 5
3 4 3
6 4 6
2 3 3
1 4
 
(출력)
#1 9
#2 7
#3 4
 
(sample_input.txt에 대한 출력)
#1 9999900000
#2 387611
#3 1193489
#4 337443
#5 618285
#6 440476
#7 255266
#8 184867
#9 267544
#10 316086

 */
	static int TC,N,M,K, s,e;//2 ≤ N ≤ 100,000, 1 ≤ M ≤ 200,000, 0 ≤ K ≤ 2
	static long d[][];
	static ArrayList<Integer> con[];
	static ArrayList<Integer> conW[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			con = new ArrayList[N+1];
			conW = new ArrayList[N+1];
			for(int i=1;i<=N;i++){
				con[i]=new ArrayList<>();
				conW[i]=new ArrayList<>();
			}
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				con[a].add(b); con[b].add(a);
				conW[a].add(c); conW[b].add(c);
			}
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			d = new long[N+1][K+1];
			for(int n=1;n<=N;n++){
				for(int k=0;k<=K;k++){
					d[n][k] = Long.MAX_VALUE;
				}
			}
			PriorityQueue<Interstela> pq = new PriorityQueue<>();
			
		}

	}

}
class Interstela implements Comparator<Interstela>{
	public Interstela(long x, long y, long z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	long x,y,z;
	@Override
	public int compare(Interstela o1, Interstela o2) {
		// TODO Auto-generated method stub
		return (int)(o1.x-o2.x);
	}

}



