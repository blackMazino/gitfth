package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class 그래프_MST_KOITP_군사도로망 {
//https://koitp.org/problem/MILITARY_ROAD_NETWORK/read/
/*
5 3 3
1 2 1
1 3 4
2 3 2
3 5 2
3 4 7
4 5 4

7 unique

3 3 0
1 2 3
1 3 3
2 3 3	

3 not unique
 */
	
	static int N,M,K, par[];
	static ArrayList<Road> list;
	static long answer;
	static boolean isUnique;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
				
		list = new ArrayList<Road>();
		answer = 0;
		isUnique = true;
		int a, b, c;
		//기존도로
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());			
			list.add(new Road(a, b, -c));  // 취소비용을 미리 (-) 값으로 넣어놓는다.
			answer = answer + c;  // 취소 비용을 다시 건설하는 경우 상쇄되므로 미리 solve에 넣어둔다.
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());			
			list.add(new Road(a, b, c));  
			
		}
		Collections.sort(list, new Comparator<Road>(){

			@Override
			public int compare(Road o1, Road o2) {
				// TODO Auto-generated method stub
				return o1.c - o2.c;  // 오름차순 정렬
			}
						
		});

		//Kruscal
		// u-f 를 위한 초기화
		par = new int[N+1];
		for (int i = 1; i <= N; i++) par[i] = i;		
		
		//MST by Union Find			
		for(int i=0;i<list.size();i++){
			//find
			int s = find(list.get(i).s);
			int e = find(list.get(i).e);
			if(i==0){
				for(int j=i;j<list.size();j++){
					if(list.get(i).c != list.get(j).c){
						break;						
					}
					if(find(list.get(j).s) != find(list.get(j).e) ){
						list.get(j).mark = true;
					}
				}
			}else if(list.get(i-1).c != list.get(i).c){
				for(int j=i;j<list.size();j++){
					if(list.get(i).c != list.get(j).c){
						break;						
					}
					if(find(list.get(j).s) != find(list.get(j).e) ){
						list.get(j).mark = true;
					}
				}
			}
			
			//Union
			if(union(s,e)){
				answer = answer + list.get(i).c;
				list.get(i).mst = true;
			}
		}
		for(Road r : list){
			if(r.mark && !r.mst){ 
				isUnique = false;								
			}
		}
		
		
		System.out.println(answer+" "+(isUnique ? "unique" : "not unique"));
	}
	
	private static boolean union(int s, int e) {
		// TODO Auto-generated method stub
		s = find(s); 
		e = find(e);
		if(s==e) return false;		
		par[e] = s;
		return true;
	}


	private static int find(int s) {
		// TODO Auto-generated method stub
		if (par[s] == s) return s;
		return par[s] = find(par[s]);
	}
}

class Road{
	int s,e,c;
	boolean mark,mst;
	public Road(int s, int e, int c) {
		
		this.s = s;
		this.e = e;
		this.c = c;
		mark = false;
		mst = false;
	}
}