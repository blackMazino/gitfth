package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Practice31_최대와최소2 {
/*
크기 N 의 수열이 주어졌을 때 특정 구간에서의 최댓값과 최솟값을 구하는 프로그램을 작성하여라.
최대, 최소를 구해야 하는 질문은 여러 개 주어질 수 있으며, 도중에 수열의 특정 값이 변경될 수 있음에 유의하라. 질문과 값의 변경은 모두 합하여 Q 번 주어진다.
각 질문의 답인 최댓값과 최솟값을 모두 모아 그 최댓값들의 합, 최솟값들의 합을 출력하면 된다.
 
[제한조건]
1. 수열의 크기(길이) N 은 5 이상 100000 이하의 정수이다.
2. 수열의 각 항의 값은 0 이상 10000 이하의 정수이다.
3. Q 는 1 이상 100000 이하의 정수이며 질문은 적어도 1개 이상 주어진다.
 
[입력]
첫 줄의 테스트 케이스의 개수 T 가 주어진다. 이후 여러줄에 걸쳐 T 개의 테스트 테이스가 주어진다. 
각 케이스는 Q + 2 줄로 구성되어 있으며 첫 줄에는 N, Q 의 값이 공백으로 구분되어 차례대로 주어진다. 
다음 줄에는 수열을 이루는 N 개의 값들이 공백으로 구분되어 차례대로 주어진다. 그 다음 Q 줄에 걸쳐 질문 또는 값의 변경 정보가 순서대로 주어진다.
 
각 줄에는 세 개의 정수 q, a, b 의 값이 공백으로 구분되어 주어지며, q 는 0 또는 1 의 값을 가진다.
 
q 가 0 이면 그 시점에서 a항부터 b항까지의 폐구간(1<=a<=b<=N) 에서의 최댓값과 최솟값을 묻는 질문을 뜻하며,
q 가 1 이면 a항의 값이 b로 변경됨(1<=a<=N, 0<=b<=10000)을 뜻한다.
 
[출력]
각 케이스에 대한 답을 케이스 당 한줄에 출력한다.
각 테스트 케이스에 대해 #x (x는 케이스 번호, 1부터 시작)를 출력하고 공백을 하나 둔 후 모든 질문에 대한 최댓값의 합과 최솟값의 합을 공백으로 구분하여 차례로 출력한다.
 
[입출력 예]
(입력)
2
5 5
3 5 7 4 6
0 1 3
1 2 2
0 1 3
1 4 0
0 3 5
10 4
1 2 3 4 5 6 7 8 9 10
1 1 12
1 9 0
0 1 10
0 2 8
 
(출력)
#1 21 5
#2 20 2
 
(sample_input.txt 의 출력)
#1 12 7
#2 29 6
#3 59 8
#4 6343471 75079
#5 10633877 121269
#6 12771901 429767
#7 12501442 406813
#8 15347171 588802
#9 14871498 667353
#10 20305196 44694
 */
	static int TC,N,Q, tn;
	static int[] arr;
	static long min,max;	
	static long[] minTree, maxTree;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Practice31.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			
			//IndexedTree
			for(tn=1;tn<N;tn=tn+2);//tn = N+1
			minTree = new long [2*N + 1];			
			Arrays.fill(minTree, Long.MAX_VALUE);			
			maxTree = new long [2*N + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++){
				int t  = Integer.parseInt(st.nextToken());
				arr[n] = t;
				update(n, t);
			}
//			print(minTree);
//			print(maxTree);
			min = 0;
			max = 0;
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				if(a==1){
					arr[b] = c;
					update(b,c);
				}else{					
					max += searchMax(b,c);
					min += searchMin(b,c);
				}
				
			}
			System.out.println("#"+tc+" "+max+" "+min);		
		}
	}

	
	private static void print(long[] tree) {
		for(long l : tree){
			System.out.print(l+ " ");
		}
		System.out.println();
	}


	private static long searchMax(int s, int e) {
		long result = 0;
		s = s + tn-1;
		e = e + tn-1;
		while(s<=e){
			if(s%2==1){
				result = Math.max(maxTree[s], result);
				s++;
			}
			if(e%2==0){
				result = Math.max(maxTree[e], result);
				e--;
			}
			s/=2;
			e/=2;
		}		
		return result;
	}

	private static long searchMin(int s, int e) {
		long result = Long.MAX_VALUE;
		s = s + tn-1;
		e = e + tn-1;
		while(s<=e){
			if(s%2==1){
				result = Math.min(minTree[s], result);
				s++;
			}
			if(e%2==0){
				result = Math.min(minTree[e], result);
				e--;
			}
			s/=2;
			e/=2;
		}		
		return result;
	}


	private static void update(int w, int g) {
		for(int i=tn+w-1;i>0; i = i/2){
			minTree[i] = Math.min(minTree[i], g);
			maxTree[i] = Math.max(maxTree[i], g);
		}			
	}

}