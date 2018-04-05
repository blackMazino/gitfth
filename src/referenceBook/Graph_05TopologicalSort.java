package referenceBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Graph_05TopologicalSort {
	/*
	 * 입력은 첫줄에 노드의 개수N( 2 < N <= 100) 와 간선의 개수 M이 주어지고, 그 다음줄부터 순차대로 각 간선의 출발노드와
	 * 도착노드가 적혀진다. 이와 같을 때, 위상 정렬을 하여 출력하시오. 단 같은 레벨의 노드인 경우 출력순서는 관계없다. [입력] 5
	 * 6 2 1 2 3 3 4 3 1 1 4 5 4
	 * 
	 * [출력] 5 2 3 1 4
	 * 
	 */
	static int MAX = 101;
	static int[] visited = new int[MAX];
	static int[] inputEdgeCount = new int[MAX];
	static ArrayList<Integer> ordered = new ArrayList<Integer>();
	static ArrayList<Integer>[] adj = new ArrayList[MAX];

	public static void main(String[] args) throws IOException {

		for (int i = 0; i < MAX; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		int N = Integer.valueOf(line[0]);
		int M = Integer.valueOf(line[1]);

		for (int i = 0; i < M; i++) {
			line = br.readLine().split(" ");
			int s = Integer.valueOf(line[0]);
			int e = Integer.valueOf(line[1]);
			adj[s].add(e);
			inputEdgeCount[e]++;
		}

		ArrayList<Integer> result = topologicalSort(N);

		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}

		System.out.println();

	}

	private static ArrayList<Integer> topologicalSort(int N) {
		ArrayList<Integer> startNode = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++) {
			visited[i] = 0;
			if (inputEdgeCount[i] == 0)
				startNode.add(i);
		}

		ordered.clear();
		for (int i = 0; i < startNode.size(); i++) {
			if (visited[startNode.get(i)] == 0)
				dfs(startNode.get(i));
		}

		for (int i = ordered.size() - 1; i >= 0; i--) {
			result.add(ordered.get(i));
		}

		return result;

	}

	static void dfs(int u) {
		visited[u] = 1;
		for (int v = 0; v < adj[u].size(); v++) {
			if (visited[adj[u].get(v)] == 0)
				dfs(adj[u].get(v));
		}
		ordered.add(u);
	}
}
