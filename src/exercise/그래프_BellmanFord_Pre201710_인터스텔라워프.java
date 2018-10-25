package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그래프_BellmanFord_Pre201710_인터스텔라워프 {

/*
가깝고도 먼 미래, 인간은 좀더 편하게 우주 여행을 할 수 있게 되었고, 우주에 대한 호기심 또한 점점 많아졌다. 
이는 최고의 우주선 조종사 겸 엔지니어인 김군도 예외가 아니었다. 김군은 여유가 있을 때면 거대한 우주 속에서 워프 항법을 이용하여 미지의 장소를 찾아 다니곤 했다. 워프 항법은 우주 속에 있는 워프를 이용하여 시공간을 왜곡하여 초차원적으로 공간을 이동하는 방법을 뜻한다.
그러던 중 문제가 발생하였다. 안전하리라고 생각했던 곳이 사실은 운석으로 뒤덮여 있는 상당히 불안정한 공간이었다. 
김군은 서둘러 이 공간의 상황을 파악하여 이곳을 탈출할 수 있는 방법을 찾기 시작하였다.
공간의 상황을 파악한 결과 김군이 있는 지역은 아래 그림과 같은 형태로 2차원 격자 공간으로 도식화 할 수 있었다. 
이 공간을 벗어난 곳은 운석과 불안정성으로 인하여 갈 수 없음을 가정하자.

   0   1   2   3   ---X
0  S   N   N   Ws
1  N   N   M   M
2  N   N   We  E
위의 그림은 김군이 있는 지역을 도식화한 하나의 예시이다. 
예시공간의 가로(x)의 길이는 4, 세로 길이(y)는 3이며, (x,y) 좌표로 김군의 현재 위치는 (0,0) 이며 탈출 지점은 (3,2) 이다. 
(2,1) 및 (3,1) 의 위치에는 운석이 있으므로 진입할 수 없는 곳이다. 
김군의 우주선은 인접한 상하좌우의 공간 중 한 곳으로 한번에 한칸씩 이동할 수 있으며 한번 이동하는 데에 1시간이 소요된다고 한다. 
(3,0) 의 위치에는 워프 입구가 존재하는데, 워프 입구가 있는 지점에 진입하게 되면 반드시 워프 항법을 통해서만 이동이 가능하며, 
이 경우 워프의 출구(특정한 지점, 위의 그림에서는 (2,2))로 향하게 된다. 
이 때 항해하는 시간은 워프마다 다를 수 있는데, 여기서는 0시간(순간이동)임을 가정하자. 
또한 워프는 양방향이 아니므로 워프의 출구에서는 워프 항법을 이용할 수 없다.
위의 예시에서 김군이 탈출지점에 도달하는 방법 중 몇 가지를 예로 들어본다. 우선 워프 항법 없이 다음과 같은 방법으로 탈출할 수 있다.

(0,0) 출발 à (1,0) à (1,1) à (1,2) à (2,2) à (3,2) 탈출
이 경우 5시간이 소요된다.
방문한 지점을 또 방문할 수도 있으므로
(0,0) 출발 
à (0,1) à (0,2) 
à (1,2) à (1,1) 
à (0,1) à (0,2) 
à (1,2) à (2,2) à (1,2) à (2,2) à (3,2) 탈출

과 같은 방법도 있지만 이 경우는 무려 11시간이 소요될 것이다.
워프를 이용하여 탈출하는 방법도 있을 것이다.

(0,0) 출발 à (1,0) à (2,0) à (3,0) 워프항법 à (2,2) à (3,2) 탈출

이 경우는 4시간이면 탈출할 수 있다 (이 워프를 통한 워프항법 시간이 0시간이라고 했으므로). 
만일 이 워프의 워프 항법 시간이 2시간이라면 총 6시간이 소요되며 이 경우는 워프 항법을 쓰지 않는 방법보다 더 시간이 많이 든다.

워프는 시공간을 왜곡하므로, 어떤 워프는 항해할 때 시간까지 거꾸로 가게 한다. 다시 말해, 시간이 거꾸로 가는 항해를 하게 되는 워프도 존재할 수 있다. 
즉, 위의 예시의 워프의 워프 항법 시간이 -1시간이라 하면 이 워프에서 워프 항법을 하게 될 경우 1시간 과거로 가게 되므로. 총 3시간 만에 탈출할 수 있다.

김군은 가능한 한 빨리 이 지역에서 탈출하고 싶다. 아니 시간을 되돌려서라도 이 지점에 다시 오고 싶지 않은 심정이다. 
김군이 있는 지역의 상황이 주어졌을 때 김군이 얼마나 빠른 시간에 그 지역을 탈출할 수 있는지를 알아내는 프로그램을 작성하여 보자.

[제한 조건]    	
1. 2차원 격자 공간의 가로(X), 세로(Y)의 길이는 각각 1 이상 30 이하의 정수이다.
2. 운석이 있는 공간(M)의 개수는 0 이상의 정수이며 이 공간에는 진입할 수 없다.
3. 워프(W)의 개수는 0 이상의 정수이며, 워프의 출구는 운석이 있는 공간이 아니다.
4. 김군의 현위치는 항상 (0,0) 이며, 탈출지점은 항상 (X-1,Y-1) 이다. 그리고 현 위치와 탈출지점에는 운석이 존재하지 않으며, 워프의 입구 또한 존재하지 않는다.
5. 서로 다른 두 워프의 입구가 같은 지점에 있는 경우는 없다.
6. 한 워프의 입구와 출구가 같은 지점인 경우는 존재할 수 있다.
7. 탈출지점에 도착하면 다른 지점에 가지 않고 즉시 그 지역을 탈출한다.
				
[입력]    	
첫 줄에 테스트 케이스의 개수 T 가 주어지며 그 다음 T개의 테스트 케이스가 주어진다. 
각 테스트 케이스는 여러 줄로 구성되어 있으며, 첫 줄에 2차원 격자 공간의 가로길이 X 와 세로길이 Y 가 공백으로 구분되어 주어진다. 
다음 줄에는 운석이 있는 공간의 개수 M 이 주어지며, 그 다음 M 줄에 걸쳐, 
한 줄당 운석 공간의 위치좌표를 나타내는 두 개의 정수 xm, ym 이 순서대로 공백으로 구분되어 주어진다. 
xm 은 운석공간의 x좌표, ym 은 y좌표를 뜻한다 (0 ≤ xm < X, 0 ≤ ym < Y)
다음 줄에는 워프의 개수 W 가 주어지며, 그 다음 W 줄에 걸쳐 워프의 정보가 한 줄에 하나씩 주어진다. 
워프의 정보는 xs, ys, xe, ye, tw 가 순서대로 공백으로 구분되어 주어지는데, 
이는 워프의 입구 좌표가 (xs, ys), 워프의 출구 좌표가 (xe, ye), 이 워프에서 워프 항법을 사용해 이동하는 데에 걸리는 시간이 tw시간 임을 뜻한다.
(0 ≤ xs, xe < X, 0 ≤ ys, ye < Y, -10000 ≤ tw ≤ 10000)
				
[출력]    	
각 테스트 케이스에 대해 #x (x는 테스트 케이스 번호, 1부터 시작) 을 출력하고 공백을 하나 둔 후, 다음을 출력한다.
1) 만일 이동 중 시간이 무한히 거꾸로 흘러갈 수 있는 경우라면 "mininf" 를 출력한다.
2) 1의 경우가 아니면서, 김군이 탈출할 수 있는 방법이 없다면 "noway" 를 출력한다.
3) 1, 2의 경우가 모두 아니라면 김군이 탈출지점에 도달하는데 걸리는 최소시간을 정수로 출력한다. 이 최소 시간은 음수가 나올 수도 있음에 유의하라.
※ [입출력 예]의 (출력) 부분을 참고하라.
				
[입출력 예제] 				
(입력)    	
4
3 3
2
2 1
1 2
0
4 3
2
2 1
3 1
1
3 0 2 2 0
4 2
0
1
2 0 1 0 -3
10 2
2
1 1
5 1
2
0 1 9 0 25
3 0 8 1 -6

(출력)    	
#1 noway
#2 4
#3 mininf
#4 -2

 */
	static int X,Y,M,W;
	static int[][] space;
	static ArrayList<Warp> warpList;
	static ArrayList<Path> list;
	static int [] d;
	static boolean minInf;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(br.readLine());
			space = new int[X][Y];
			d = new int [X*Y];
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int xm = Integer.parseInt(st.nextToken());
				int ym = Integer.parseInt(st.nextToken());
				space[xm][ym] = -1;
			}
			W = Integer.parseInt(br.readLine());
			warpList = new ArrayList<Warp>();
			list = new ArrayList<Path>();
			for(int w=1;w<=W;w++){
				st = new StringTokenizer(br.readLine());
				int sx = Integer.parseInt(st.nextToken());
				int sy = Integer.parseInt(st.nextToken());
				space[sx][sy] = 1;
				int ex = Integer.parseInt(st.nextToken());
				int ey = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());				
				warpList.add(new Warp(sx,sy,ex,ey,c));
				list.add(getPath(sx,sy,ex,ey,c));				
			}
			
			//path search
			for(int x=0;x<X;x++){
				for(int y=0;y<Y;y++){
					if(x==X-1 && y==Y-1){
						break;
					}
					if(space[x][y] == 0){//-1 : meteor, 1 : warp sp.
						search(x,y,x+1,y,1);
						search(x,y,x,y+1,1);
						search(x,y,x-1,y,1);
						search(x,y,x,y-1,1);	
					}					
				}
			}
//			printP();
			minInf = false;
			BellmanFord();
//			System.out.println(minInf);
			String answer = minInf?"mininf":"noway";
			if(answer == "noway"){
				if(d[X*Y-1] < Integer.MAX_VALUE ){
					answer = String.valueOf(d[X*Y-1]);
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
		
	}
	private static void printP() {
		for(Path p : list){
			System.out.println(p.sIdx+","+p.eIdx+","+p.w);
		}
		
	}
	private static void BellmanFord() {
		int n = X*Y;
		Arrays.fill(d, Integer.MAX_VALUE);
		d[0] = 0;
		for(int i=0;i<n;i++){
			for(Path p : list){
				if(d[p.sIdx] < Integer.MAX_VALUE){//값이 갱신되었다면->여기로 오는 길이 있다면
					if(d[p.eIdx] > d[p.sIdx] + p.w){
						d[p.eIdx] = d[p.sIdx] + p.w;
						if(i==n-1){//끝까지 왔다면
							minInf = true;
							break;
						}	
					}				
				}
			}
		}
	}
	private static void search(int sx, int sy, int ex, int ey, int w) {
		if(ex<=X-1 && ex>=0
	    && ey<=Y-1 && ey>=0
		&& space[ex][ey]>=0 
	    ){
			list.add(getPath(sx,sy,ex,ey,w));
		}
		
	}
	private static Path getPath(int sx, int sy, int ex, int ey, int c) {
		int sIdx = X*sy + sx;
		int eIdx = X*ey + ex;		
		return (new Path(sIdx, eIdx, c));
	}

}
class Warp{
	
	public Warp(int sx, int sy, int ex, int ey, int c) {
		super();
		this.sx = sx;
		this.sy = sy;
		this.ex = ex;
		this.ey = ey;
		this.c = c;
	}
	int sx;
	int sy;
	int ex;
	int ey;
	int c;
}
//X*Y 2nd Dimension -> X*Y 1st Dimension 
class Path{
	public Path(int sIdx, int eIdx, int w) {
		super();
		this.sIdx = sIdx;
		this.eIdx = eIdx;
		this.w = w;
	}
	int sIdx;
	int eIdx;
	int w;
}