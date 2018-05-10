package jaeHoonsChoice;

import java.io.*;
import java.util.*;

class Point2 {
	public Point2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	int x, y;
}

public class closest_point {
	static int N;
	static Point2[] A, merge_tmp;

	static int dist(Point2 a, Point2 b) {
		return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	}

	static int dfs(int s, int e) {
		if (e - s < 9) {
			int ret = Integer.MAX_VALUE;
			for (int i = s; i < e; i++)
				for (int j = i + 1; j <= e; j++) {
					ret = Math.min(ret, dist(A[i], A[j]));
				}
			Arrays.sort(A, s, e + 1, new Comparator<Point2>() {
				public int compare(Point2 a, Point2 b) {
					return a.y - b.y;
				}
			});
			return ret;
		}
		int m = s + e >> 1, div_x = A[m].x;
		int d = Math.min(dfs(s, m), dfs(m + 1, e));
		/* Merging starts */
		for (int l = s, r = m + 1, i = s; i <= e; i++) {
			if (r > e || l <= m && A[l].y < A[r].y)
				merge_tmp[i] = A[l++];
			else
				merge_tmp[i] = A[r++];
		}
		for (int i = s; i <= e; i++)
			A[i] = merge_tmp[i];
		/* Merging ended */
		ArrayList<Point2> arr = new ArrayList<>();
		for (int i = s; i <= e; i++) {
			if ((A[i].x - div_x) * (A[i].x - div_x) <= d)
				arr.add(A[i]);
		}
		int ret = d;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i + 1; j < arr.size() && j - i < 13; j++) {
				ret = Math.min(ret, dist(arr.get(i), arr.get(j)));
			}
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new Point2[N + 1];
		merge_tmp = new Point2[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			A[i] = new Point2(x, y);
		}
		Arrays.sort(A, 1, N + 1, new Comparator<Point2>() {
			public int compare(Point2 a, Point2 b) {
				return a.x - b.x;
			}
		});
		System.out.println(dfs(1, N));
	}
}
