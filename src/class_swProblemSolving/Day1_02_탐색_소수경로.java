package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Day1_02_탐색_소수경로 {
/*
형석이는 다음날 SDS에 강의를 하러 간다. 하지만 아침에 일찍 일어나야 한다는 부담감에 고급 알람 시계를 준비하였다. 최근에 화장실 사진찍기, 뺨 때리기, 냄새 뿌리기 등의 알람 시계가 나오고 있지만, 형석이는 강의를 위해 머리를 써야 끌 수 있는 알람을 준비하였다.
이 시계의 시간을 맞춰 놓고, 그 시간이 되어 울리기 시작하면, 4자리의 소수 2개가 고급 알람 시계에 표시된다. 첫 번째 적혀 있는 수는 숫자를 변경할 수 있게 되어있다. 그럼 이제 알람을 끄는 방법은 간단하다. 첫 번째 적혀 있는 소수를 두 번째 적혀 있는 소수로 변경하면 된다.
이때, 한번에 한자리만 변경할 수 있고, 한자리를 변경하였을 때, 변경된 수는 소수이어야 한다. 또한 4자리 소수이기 때문에, 경로 중간에도 4자리를 유지해야한다. (즉, 1000이상의 소수) 형석이는 알람이 계속 울리는 것이 싫기 때문에, 최대한 빠르게 알람을 끄고 싶어한다. 
형석이를 도와 어떻게 하면 최소한의 단계로 첫 번째 소수를 두 번째 소수로 변경할 수 있는지 구하시오.
예를 들어서 1033이라는 수를 8179로 변경한다면, 1033 1733 3733 3739 3779 8779 8179의 순서로 변경이 가능하다.
첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
각 테스트 케이스의 첫 번째 줄에 고급 알람 시계에 적혀 있는 두 개의 소수가 공백으로 분리되어 주어진다. (1,000 ≤ 소수 ≤ 9,999)
각 테스트 케이스마다 첫 번째 줄에 첫 번째 소수를 두 번째 소수로 변경하는 최소한의 단계수를 출력한다.
===============
3
1033 8179
1373 8017
1033 1033
===============
6
7
0
 * */
	
	static int TC;
	static int answer;
	static int sNo ;
	static int eNo ;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 한 줄에 정수 하나가 주어지는 경우
		TC = Integer.parseInt(br.readLine());
		
        int[] primes = PrimePath.getPrimes();
        List<List<Edge>> graph = PrimePath.getGraph(primes);
		
		for(int i=1; i<=TC;i++){
			answer=0;
			st = new StringTokenizer(br.readLine());
			sNo = Integer.parseInt(st.nextToken());
			eNo = Integer.parseInt(st.nextToken());
			
			sNo = Arrays.binarySearch(primes, sNo);
			eNo = Arrays.binarySearch(primes, eNo);

            int[] dist = PrimePath.dijkstra(primes.length, sNo, graph);
            System.out.println(dist[eNo]);
			//System.out.println(answer);
		}
		
		
		br.close();
	}

	public static class Edge implements Comparable<Edge> {
	    int u;
	    int v;
	    int cost;

	    public Edge(int u, int v, int cost) {
	        this.u = u;
	        this.v = v;
	        this.cost = cost;
	    }

	    public int compareTo(Edge b) {
	        return this.cost - b.cost;
	    }
	}
	
	//소수List 작성(10000까지)
	public static class PrimePath {
	    private static int[] getPrimes() {
	        boolean[] a = new boolean[10000];
	        for (int i = 3; i < a.length; i += 2) {
	            if (a[i]) continue;

	            for (int j = i; j * i < a.length; j += 2) {
	                a[i * j] = true;
	            }
	        }

	        int count = 0;
	        for (int i = 1001; i < a.length; i += 2) {
	            if (!a[i]) count++;
	        }

	        int[] res = new int[count];

	        int h = 0;

	        for (int i = 1001; i < a.length; i += 2) {
	            if (!a[i]) res[h++] = i;
	        }

	        return res;
	    }

	    private static boolean connected(int a, int b) {
	        int count = 0;

	        for (int i = 0; i < 4; i++) {
	            if ((a % 10) != (b % 10)) count++;
	            a /= 10;
	            b /= 10;
	        }

	        return count == 1;
	    }

	    private static List<List<Edge>> getGraph(int[]primes) {
	        int N = primes.length;
	        ArrayList<List<Edge>> graph = new ArrayList<List<Edge>>(N);
	        for (int i = 0; i < N; i++) {
	            graph.add(new ArrayList<Edge>());
	        }

	        for (int i = 0; i < N; i++) {
	            for (int j = i + 1; j < N; j++) {
	                if (connected(primes[i], primes[j])) {
	                    graph.get(i).add(new Edge(i, j, 1));
	                    graph.get(j).add(new Edge(j, i, 1));
	                }
	            }
	        }

	        return graph;
	    }

	    private static int[] dijkstra(int N, int s, List<List<Edge>> graph) {
	        int[] dist = new int[N];
	        Arrays.fill(dist, Integer.MAX_VALUE);
	        dist[s] = 0;

	        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
	        que.offer(new Edge(s, s, 0));

	        while (!que.isEmpty()) {
	            Edge p = que.poll();
	            if (dist[p.u] < p.cost) continue;

	            for (Edge e : graph.get(p.u)) {
	                if (dist[e.v] > p.cost + e.cost) {
	                    dist[e.v] = p.cost + e.cost;
	                    que.offer(new Edge(e.v, s, dist[e.v]));
	                }
	            }
	        }

	        return dist;
	    }
	}
}
/* 
//소수경로
import java.io.*;
import java.util.*;
   
public class source {
    static int T, A, B;
    static int[] D;
    static boolean[] is_prime;
      
    public static void main(String[] args) throws Exception {
        is_prime = new boolean[10000];
        for (int i=1001;i<10000;i+=2){
            is_prime[i] = true;
            for (int j=2;j*j<=i;j++) if (i % j == 0){
                is_prime[i] = false;
                break;
            }
        }
          
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int ts=0;ts<T;ts++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());   
              
            D = new int[10000];
            for (int i=0;i<10000;i++) D[i] = Integer.MAX_VALUE;
            Queue<Integer> que = new LinkedList<Integer>();
            que.add(A); D[A] = 0;
            while (!que.isEmpty()){
                int q = que.poll();
                for (int b=1;b<10000;b*=10){
                    for (int d=0;d<10;d++){
                        int v = q / b / 10 * b * 10 + q % b + b * d;
                        if (!is_prime[v] || D[v] < Integer.MAX_VALUE) continue;
                        D[v] = D[q]+1;
                        que.add(v);
                    }
                }
            }
            System.out.println(D[B]);
        }
    }
}
*/