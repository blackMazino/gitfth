package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//못품 다시풀기
public class Day2_04_DS_중앙값_FAIL {
/*
정수가 N개 주어진다. 홀수번째 수가 주어질 때마다, 지금까지 주어진 수의 중앙값을 구하는 프로그램을 작성하여라.
예를 들어 1, 4, 5, 3, 6가 주어진다면, 
첫번째 수인 1을 입력받을 때 중앙값이 1이고, 
세 번째 수인 5를 입력받을 때까지의 중앙값이 4이고, 
다섯번째 수인 6을 입력받을 때까지의 중앙값이 4이므로 
1, 4, 4를 순서대로 출력하는 것이다.
첫 번째 줄에 주어지는 정수의 개수 N이 주어진다. (1 ≤ N ≤ 99,999, N은 홀수)
두 번째 줄부터 N개의 줄에 걸쳐 각 줄에 하나씩 정수가 주어진다. (1 ≤ 주어지는 정수 ≤ 1,000,000,000)
홀수번째 수를 입력받을 때마다 그 때까지 입력받은 정수들의 중앙값을 한 줄에 하나씩 출력한다
===============
7
1 
9 
5 
3 
2 
8
8
===============
1
5
3
5
*/
		
	static int TC;
	static int N,i,j,M;
	
	static class Node{
		int e;
		long v;
		public Node(int e,long v){
			this.e = e;
			this.v = v;
		}
	}
	static PriorityQueue<Node> queL, queR;
	static PriorityQueue<Integer> L,R;
	

	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
		//초기화
		queL = new PriorityQueue(30000001, new Comparator<Node>(){

			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return (int)(o1.v-o2.v);
			}				
		});
		queR = new PriorityQueue(30000001, new Comparator<Node>(){

			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return (int)(o1.v-o2.v);
			}				
		});
		
		L = new PriorityQueue();
		R = new PriorityQueue();
		
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			System.out.println(M);
			for(i=0;i<N-1;i+=2){
				for(j=0;j<2;j++){
					int x;
					st = new StringTokenizer(br.readLine());
					x = Integer.parseInt(st.nextToken());
					if(x<M) L.add(x);				
					else    R.add(-x);
				}
				while(L.size()>R.size()){
					R.add(-M);
					M = L.peek();
					L.poll();				
				}
				while(L.size()<R.size()){
					L.add(M);
					M = -R.peek();
					R.poll();
				}
				System.out.println(M);
			}

			
//			System.out.println("#"+tc+"");
//		}

		br.close();

	}


}

/*
//중앙값
import java.io.*;
import java.util.*;
  
public class source {
    static int N;
    static int[] A;
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> L = new PriorityQueue<Integer>();
        PriorityQueue<Integer> R = new PriorityQueue<Integer>();
        for (int i=1;i<=N;i++){
            int v = Integer.parseInt(br.readLine());
              
            if (R.isEmpty() || R.peek() > v) L.add(-v);
            else R.add(v);
              
            while (L.size() > R.size()+1)
                R.add(-L.poll());
            while (R.size() > L.size())
                L.add(-R.poll());
              
            if (i % 2 == 1) System.out.println(-L.peek());
        }
    }
}
*/