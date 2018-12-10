package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Previous42_배제쌍 {
/*
[입출력 예]
입력
2                                                 ← 2 test case in total
5 5 3                                             ← 1st case
1 2 1
2 3 1
3 4 1
4 1 1
4 5 99
1 2
2 3
3 4
5 6 3                                             ← 2nd case
1 2 1
2 3 1
3 4 1
4 1 1
1 3 1
4 5 99
1 2
2 3
3 4

출력
#1 -1
#2 102

(sample_input.txt 에 대한 출력)
#1 -1
#2 103
#3 1294588252
#4 5754724245
#5 16098091578
#6 14117894237
#7 329
#8 15604984750
#9 2230
#10 2528
#11 -1
#12 -1
#13 -1
#14 7147672570
#15 7272576052


 */
	static int TC, N,M,K;//3 ≤ N ≤ 50, N-1 ≤ M ≤200, 1 ≤ K ≤ 16
	static int par[];
	static ArrayList<Integer> kList[];
	static ArrayList<P42VO> list;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new P42VO(a,b,c));
			}
			kList = new ArrayList[K+1];
			for(int k=1;k<=K;k++){
				kList[k] = new ArrayList<>();
			}
			for(int k=1;k<=K;k++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				kList[a].add(b);kList[b].add(a);
			}
			
			
		}
		
		
		
		
	}

}
class P42VO{
	int a,b,c;

	public P42VO(int a, int b, int c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
}