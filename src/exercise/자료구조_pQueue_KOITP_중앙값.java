package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//중앙값 순서대로 나열했을때 가운데 숫자. 홀수일때 1개 
public class 자료구조_pQueue_KOITP_중앙값 {

	static int N;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
//		LinkedList<Integer> L = new LinkedList<>();
//		LinkedList<Integer> R = new LinkedList<>();

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>();//큰값이 앞에 오게
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();//작은값이 앞에 오게
		
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
		
//			if(R.isEmpty() || R.getFirst() > v){
//				L.add(-v);
//			}else{
//				R.add(v);
//			}
//			while(L.size() > R.size()+1){
//				R.add(-L.removeFirst());
//			}
//			while(R.size() > L.size()){
//				L.add(-R.removeFirst());
//			}
//			if(i%2==1) System.out.println(-R.getFirst());
            
            
            if (minHeap.isEmpty() || minHeap.peek() > v) maxHeap.add(-v);//v의 절대값이 클수록 작은값(앞쪽에)
            else minHeap.add(v);
              
            while (maxHeap.size() > minHeap.size()+1)
            	minHeap.add(-maxHeap.poll());
            while (minHeap.size() > maxHeap.size())
            	maxHeap.add(-minHeap.poll());
                          
            if (i % 2 == 1){
            	System.out.println(-maxHeap.peek());            	            	
            }
		}
		
		
	}

}
