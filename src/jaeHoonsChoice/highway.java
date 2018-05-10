package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class City {
	int s, e, c;
	public City(int s, int e, int c) {
		this.s = s;
		this.e = e;
		this.c = c;
	}
}

public class highway {
	
	static int T, N, M, par[];
	static long solve;
	static ArrayList<City> list;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("sample/highway.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			solve = 0;
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			// u-f 를 위한 초기화
			par = new int[N+1];
			for (int i = 1; i <= N; i++) par[i] = i;
			
			// 도시 리스트 초기화
			list = new ArrayList<>();
			int a, b, c;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				list.add(new City(a, b, c));
			}
			
			// mst 이용하기 위해 비용순으로 정렬(오름차순)
			Collections.sort(list, new Comparator<City>() {

				@Override
				public int compare(City o1, City o2) {
					// TODO Auto-generated method stub
					return o1.c - o2.c;  // 오름차순 정렬
				}
			});
			
			// MST 구성
			for (int i = 0; i < M; i++) {
				City city = list.get(i);
				int s = find(city.s);
				int e = find(city.e);
				
				if (find(s) != find(e)) {
					union(s,e);
					solve += city.c;
				}
			}
			
			bw.write("#" + tc + " " + solve + "\n");
			bw.flush();
		}
		bw.close();
		br.close();
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
