package referenceBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DS_01PriorityQueue {

	/*
	 * Heap 구조를 가지는 자료구조. 큐에서 삭제연산 시 가장 우선순위가 높은 노드를 제거. logN
	 * 사용가능 연산자 : offer, peak, poll, isEmpty, size
	 * */
	
	static class Temp {int x,y;}
	static PriorityQueue<Temp> q = new PriorityQueue<Temp>(10, new Comparator<Temp>(){//intiCapacity : 기본공간 여기선 10, Comparator 정렬의 그것과 동일. 선언하지 않은 경우 min heap

		@Override
		public int compare(Temp o1, Temp o2) {
			//x항목이 같다면 y항목 이 큰 순서대로 진행
			//x항목이 다르다면 x항목이 큰 순서대로 진행
			if(o1.x != o2.x) return o1.x-o2.x;
			return o2.y-o1.y;
		}
		
	});
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());		
		for(int tc=1; tc<=TC;tc++){
			int z = Integer.parseInt(st.nextToken());
		}
		
		Temp t[] = new Temp [5];
		for(int i=0; i<5;i++){
			t[i] = new Temp();
			t[i].x = (int)(Math.random() * 5);
			t[i].y = (int)(Math.random() * 5);
			System.out.println("offer data : "+t[i].x + " " + t[i].y  );
			q.offer(t[i]);
		}
		
		while(!q.isEmpty()){
			Temp temp = q.poll(); 
			System.out.println("poll data : "+temp.x + " " + temp.y  );
		}
	}

}
