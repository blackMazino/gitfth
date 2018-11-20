package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 자료구조_IDXTREE_SdsPractice31_최대와최소 {
/*

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
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Practice31.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			
			//IndexedTree
			for(tn=1;tn<N;tn=tn+2);//tn = N+1
			minTree = new long [212121];			
			Arrays.fill(minTree, Long.MAX_VALUE);			
			maxTree = new long [212121];
			
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
			if(i==tn+w-1){
				minTree[i] = g;
				maxTree[i] = g;
				continue;
			}
			minTree[i] = Math.min(minTree[2*i], minTree[2*i+1]);
			maxTree[i] = Math.max(maxTree[2*i], maxTree[2*i+1]);
		}			
	}

}