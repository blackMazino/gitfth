package jaeHoonsChoice;

import java.io.*;
import java.util.*;

// 다익스트라 알고리즘 사용
// 1. 거리 배열 만들어서 시작점에 0, 나머지는 max값 넣는다
// 2. 처리하지 않은 정점 중, 거리값이 가장 작은 정점 고른다
// 3. 그 정점에 연결된 간선들에 대해 인접한 다른 정점의 값을 갱신한다
// 4. 2,3번 과정을 모든 정점에 대해 처리할때까지 반복

public class shortest_path {

	static int N, M;
	static ArrayList<Integer> list[];
	static ArrayList<Integer> time[];
	static long dist[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("sample/shortest_path.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];
		time = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			time[i] = new ArrayList<>();
		}

		int start, end, t;
		start = end = t = 0;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			list[start].add(end);
			list[end].add(start);
			time[start].add(t);
			time[end].add(t);
		}

		dist = new long[N + 1];

		for (int i = 1; i <= N; i++)
			dist[i] = Long.MAX_VALUE;

		PriorityQueue<long[]> que = new PriorityQueue<>(10, new Comparator<long[]>() { // 거리값, 정점번호에 대해 정렬

			@Override
			public int compare(long[] a, long[] b) {
				// TODO Auto-generated method stub
				if (a[0] - b[0] > 0)
					return 1; // 값의 차를 return하므로 음/양으로만 return하도록 한다.
				return -1;
			}
		});

		dist[1] = 0;
		que.add(new long[] { 0, 1 }); // 거리값, 정점 번호 초기 세팅

		while (!que.isEmpty()) {
			long q = que.peek()[1];
			long d = que.peek()[0];
			que.poll();

			if (dist[(int) q] != d)
				continue;
			for (int i = 0; i < list[(int) q].size(); i++) {
				int ti = list[(int) q].get(i); // 인접한 정점 번호
				int v = time[(int) q].get(i); // q -> ti 간선 가중치

				if (dist[ti] > dist[(int) q] + v) {
					dist[ti] = dist[(int) q] + v;
					que.add(new long[] { dist[ti], ti });
				}
			}
		}
		System.out.println(dist[N] < Long.MAX_VALUE ? dist[N] : -1);
	}

}
