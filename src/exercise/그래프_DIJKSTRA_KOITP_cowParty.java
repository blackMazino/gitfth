package exercise;

public class 그래프_DIJKSTRA_KOITP_cowParty {
/*
https://koitp.org/problem/USACO_2017FEB_SILVER_COWPARTY/read/
문제없어짐 : http://snacky.tistory.com/59
	
N 개 농장의 (편의상 1 ,2 .. , N) 의 대표 암소 한 마리가, X ( 1 <= X <>= N) 농장에서 열리는 파티에 참석하려고 한다. 
농장들은 단방향의 길들로 연결되어 있으며, 각 길마다 걸어가는데 걸리는 시간이 주어진다
모든 소들은 파티에 걸어가야 하고 파티가 마친 후에는 자기가 속한 농장으로 돌아와야 한다. 모든 소들은 게을러서 가장 최단시간으로 올수 있는 최적의 길을 선택하려고 한다.
모든 소 들 중에서 농장으로 갔다가 돌아오는 데 가장 많이 걸리는 소의 시간은 얼마인가?

첫 번째 줄 : 정수 N , M , X 가 주어진다. (1<=N<=1000)는 농장의 수, (1<=M<=N*(N-1))은 도로의 갯수, (1<=X<=N)는 파티가 열리는 농장이다.
두 번재 줄에서 M+1 번째 줄 : i+1 번째 줄은 세 정수 Ai,Bi,Ci 가 주어진다. Ai 에서 Bi 로 가는데 Ti 시간이 소요된다는 것이다.
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3			

10

4 -> 2 ( 3 )
2 -> 1 -> 3 -> 4 (7)
총 거리합은 10이고, 이 때가 가장 긴 경우이다.
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
