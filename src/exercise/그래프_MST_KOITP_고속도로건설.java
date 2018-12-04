package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;



public class 그래프_MST_KOITP_고속도로건설 {
/*
문제
S 왕국의 새로운 정부는 모든 도시를 잇는 고속도로를 건설하려 한다. 
그러나 비싼 비용의 문제에 부딪혀, 정부는 최소 비용으로 모든 도시 간을 이동할 수 있게 하고 싶어한다. 
또한 하나의 제약이 더 있는데, 언덕 등을 깎지 않는 친환경 건설을 위해 어떤 도시끼리는 직접 도로를 이을 수 없다.
도로 후보의 목록이 주어질 때, 정부를 도와 모든 도시 간을 잇는 고속도로를 건설하는 최소 비용을 알아내자.

입력
첫 번째 줄에 도시의 수 N이 주어진다. (2 ≤ N ≤ 50,000)
두 번째 줄에 도로 후보의 수 M이 주어진다. (1 ≤ M ≤ 200,000)
세 번째 줄부터 M개의 줄에 걸쳐 각 도로 후보의 정보 s, e, c가 주어진다. 
s와 e는 도로 후보가 잇는 각 도시의 번호이고, c는 그 도로를 건설하는데 드는 비용이다.
(1 ≤ s, e ≤ N, 1 ≤ c ≤ 10,000)
항상 모든 도시를 잇는 고속도로를 건설할 수 있는 입력만 주어진다.

출력
첫 번째 줄에 모든 도시를 잇는 고속도로를 건설하는데 드는 최소 비용을 출력한다

5
8
1 2 4
1 3 9
1 4 21
2 3 8
2 4 17
3 4 16
5 2 20
5 4 30

48
*/
	
	static int N,M, par[];
	static long answer;
	static ArrayList<City> list;

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	
		
		list = new ArrayList<City>();
		answer = 0;
		int a, b, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());			
			list.add(new City(a, b, c));
		}
		Collections.sort(list, new Comparator<City>(){

			@Override
			public int compare(City o1, City o2) {
				// TODO Auto-generated method stub
				return o1.c - o2.c;  // 오름차순 정렬
			}
						
		});		

		// u-f 를 위한 초기화
		par = new int[N+1];
		for (int i = 1; i <= N; i++) par[i] = i;		
		
		//MST by Union Find
		for(City city : list){
			int s = find(city.s);
			int e = find(city.e);
			if(find(s)!=find(e)){
				union(s,e);
				answer = answer + city.c;
			}			
		}
		
		System.out.println(answer);
	}


	private static void union(int s, int e) {
		// TODO Auto-generated method stub
		
		par[s] = e;
	}


	private static int find(int s) {
		// TODO Auto-generated method stub
		if (par[s] == s) return s;
		return par[s] = find(par[s]);
	}
}

class City {
	int s,e,c;

	public City(int s, int e, int c) {
		super();
		this.s = s;
		this.e = e;
		this.c = c;
	}
	
}






//MST
/*
 * Minimum Spanning Tree
 * http://blog.naver.com/ssarang8649/220992988177
 * http://stack07142.tistory.com/54
 * 
 * 
 */