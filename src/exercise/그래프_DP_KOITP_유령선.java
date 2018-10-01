package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 그래프_DP_KOITP_유령선 {
/*
강희는 정신을 차려보니 낯선 유령선에 납치당해 있었다. 
강희는 유령들이 자고 있는 낮 시간에 몰래 유령선에 있는 구명보트를 이용해 탈출하려고 한다.
유령선의 갑판은 동일한 크기의 정사각형 모양 판자가 가로로 W개, 세로로H개 이어진 모양으로 되어 있다. 
강희는 현재 서 있는 위치에서 상하좌우로 움직일 수 있다. 
유령선은 매우 낡았기 때문에 강희가 딛고 있는 판자를 벗어나면 판자가 부서지고 만다. 
심지어 이미 부서져 있는 판자도 존재한다. 물론 강희는 유령이 아니기 때문에 부서진 판자는 걸어서 지나갈 수 없다.
판자가 너무 많이 부서지면 유령들이 화를 내기 때문에 강희는 가장 작은 개수의 판자를 파괴하면서 유령선에서 탈출하려고 한다. 
강희가 유령선에서 탈출하는 것을 돕는 프로그램을 작성하자.

첫 번째줄에 두 정수W 와 H 가 공백을 사이에 두고 주어진다. (2≤W,H≤1500)
두 번째 줄부터 H줄에 걸쳐 각 줄에 W개의 문자가 주어진다.

각 문자는 X, O, S, E 중 하나며 전체 문자들 중 S와 E는 정확히 하나임이 보장된다.

X는 처음부터 부서진 판자를 나타낸다.
O는 강희가 밟고 지나갈 수 있지만, 밟은 이후 부서지는 판자를 나타낸다.
S는 처음 강희가 서 있는 판자의 위치를 나타낸다.
E는 유령선의 구명보트 위치를 나타낸다

 각 문자는 X, O, S, E 중 하나며 전체 문자들 중 S와 E는 정확히 하나임이 보장된다.

 X는 처음부터 부서진 판자를 나타낸다. O는 강희가 밟고 지나갈 수 있지만, 밟은 이후 부서지는 판자를 나타낸다.
 S는 처음 강희가 서 있는 판자의 위치를 나타낸다.
 E는 유령선의 구명보트 위치를 나타낸다.
 
 강희가 판자를 최소 개수로 파괴하면서 유령선에서 탈출할 때 파괴되는 판자의 개수를 첫 줄에 출력하라.

 만약 강희가 유령선에서 탈출할 수 있는 방법이 없는 경우, -1을 출력하라.

 4 3
 OOSO
 OXXO
 OOEO
 =========
 4
 총 두 가지 탈출 경로가 존재한다.

 강희가 (좌, 좌, 하, 하, 우, 우)로 움직이는 경우 6개의 판자가 부서진다.

 강희가 (우, 하, 하, 좌)로 움직이는 경우 4개의 판자가 부서진다.

 따라서 답은 4이다
 
 3 3
 EXX
 XSO
 OOX
 =========
 -1
*/
	
	static int W,H;
	static char g[][];
	static int d[][];//S-해당 지점까지의 최소이동거리
	static boolean visited[][];
	static int sx,sy,ex,ey;
	static LinkedList<shipLoc> que;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new FileReader("sample/ghost_ship_sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		g = new char[H+1][W+1];
		visited = new boolean [H+1][W+1];
		d = new int [H+1][W+1];
		
		for(int i=1;i<=H;i++){
			st = new StringTokenizer(br.readLine());  		
			String l = st.nextToken();
			for(int j=1;j<=W;j++){
				g[i][j] = l.charAt(j-1);
				if(g[i][j]=='S'){
					sx = j;
					sy = i;
				}
				if(g[i][j]=='E'){
					ex = j;
					ey = i;
				}
			}
		}
		d[sy][sx] = 0; visited[sy][sx] = true; d[ey][ex] = Integer.MAX_VALUE;;

		//BFS로 탐색(4방향으로
		que = new LinkedList<>();
		que.add(new shipLoc(sx,sy));		
		
		while(!que.isEmpty()){
			shipLoc temp = que.removeFirst();
			int tx = temp.x; int ty = temp.y;
			
			search(tx, ty-1, temp);//상
			search(tx, ty+1, temp);//하
			search(tx-1, ty, temp);//좌
			search(tx+1, ty, temp);//우
		}
		
		System.out.println(d[ey][ex]==Integer.MAX_VALUE?-1:d[ey][ex]);
	}
	private static void search(int x, int y, shipLoc from) {
		//좌표계 안일 경우에만
		if(x>=1 && x<=W && y>=1 && y<=H){
			if(g[y][x] !='X' && !visited[y][x]){//X, 방문한곳은 skip
			
				if(g[y][x] == 'E'){
					d[y][x]= Math.min(d[from.y][from.x]+1, d[y][x]);
				}else{
					d[y][x]= d[from.y][from.x]+1;
				}				
				visited[y][x] = true;
				que.add(new shipLoc(x,y));				
			}
		}	
		if(x==ex && y == ey) que.clear();
	}

}

class shipLoc{
	public shipLoc(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	int x,y;
}