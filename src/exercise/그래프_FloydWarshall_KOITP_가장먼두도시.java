package exercise;
/*
N개의 도시가 있고, 임의의 두 도시 사이에는 항상 도로가 있다. 
도시는 1번부터 N번까지 차례대로 번호가 매겨져있다. 
a번 도시에서 b번 도시로 가는 도로의 길이와 b번 도시에서 a번 도시로 가는 도로의 길이가 다를 수 있다. 
어떤 도시에서 다른 도시로 가는데, 직접 연결된 도로를 통해 가는 것보다, 
다른 도시들을 거쳐가는 것이 좋을 때가 있다. 
a번 도시에서 b번 도시로 가는 거리란, a번 도시에서 하나 혹은 여러 도로를 거쳐 b번 도시로 가는 최단 거리를 의미한다. 
마찬가지로 a번 도시에서 b번 도시로 가는 거리와 b번 도시에서 a번 도시로 가는 거리가 다를 수 있음에 유의하라.

도시의 도로 정보가 주어졌을 때, 거리가 가장 먼 두 도시 사이의 거리를 구하는 프로그램을 작성하시오.

입력의 첫 줄에 도시의 수 N이 주어진다. 
(1 ≤ N ≤ 300) 
그리고 다음 N개의 줄에 도로의 정보를 나타내는 음이 아닌 정수 N개가 공백으로 구분되어 주어진다. 
i+1번째 줄에서 j번째로 주어지는 수는 i번 도시에서 j번 도시로 가는 도로의 길이를 의미하며, 
이 수는 1,000,000 보다 크지 않다. i+1번째 줄에서 i번째 수는 항상 0이다.

거리가 가장 먼 두 도시 사이의 거리를 출력한다.

4
0 5 3 1 (1번에서 1,2,3,4로 가는 길의 길이)
2 0 8 6 (2번에서 1,2,3,4로 가는 길의 길이)..
8 5 0 4
4 9 8 0
===============
9


6
0 8 1 7 13 18
13 0 13 5 3 5
16 17 0 8 14 5
5 7 15 0 18 10
10 15 13 11 0 9
1 8 1 20 4 0
==============
15

*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 그래프_FloydWarshall_KOITP_가장먼두도시 {

    static int N;
    static int road[][];
    static int solve;
    
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        road = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                road[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    road[i][j] = Math.min(road[i][j], road[i][k] + road[k][j]);
                }
            }
        }
         
        solve = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                solve = Math.max(solve, road[i][j]);
            }
        }
        
        System.out.println(solve);
	}

}
