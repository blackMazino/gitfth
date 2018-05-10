package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ghost_ship {

	static int W, H, sx, sy, ex, ey, solve[][];
	static char ship[][];
	static boolean visit[][];
	static int yy[] = {-1,0,1,0};  // 위, 오른쪽, 아래, 왼쪽 4방향 지정
	static int xx[] = {0,1,0,-1};
		
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("sample/ghost_ship_sample.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 유령선 값 초기화
		ship = new char[H+1][W+1];
		for (int i = 1; i <= H; i++) {
			String temp = br.readLine();
			for (int j = 1; j <= W; j++) {
				ship[i][j] = temp.charAt(j-1);
				
				// 시작지점
				if(temp.charAt(j-1) == 'S') {
					sx = j; sy = i;
				}
				// 종료지점
				if(temp.charAt(j-1) == 'E') {
					ex = j; ey = i;
				}
			}
		}
		
		visit = new boolean[H+1][W+1];  // 방문체크
		solve = new int[H+1][W+1];
		
		Queue<Integer> que = new LinkedList<>();
		que.add(sx); que.add(sy);  // x, y 좌표를 2개씩 빼서 넣는다.
		visit[sy][sx] = true;  // 시작점 방문체크
		solve[sy][sx] = 0;  // 시작점부터 bfs를 돌면서 1씩 더해야 하므로 초기값을 0으로 지정한다.
		solve[ey][ex] = Integer.MAX_VALUE;
		
		while (!que.isEmpty()) {
			int nx = que.poll();
			int ny = que.poll();
			
			for (int i = 0; i < 4; i++) {  // 상하좌우 4번 돌면서 확인한다.
				int tmp_y = ny + yy[i];
				int tmp_x = nx + xx[i];
				if (tmp_y > 0 && tmp_y <= H && tmp_x > 0 && tmp_x <= W &&  // 맨 끝 지점이 아니면서
					ship[tmp_y][tmp_x] != 'X' && !visit[tmp_y][tmp_x]) {  // 갈수 없는 'X' 값이 아니고, 방문하지 않았을 경우
					
					if(tmp_x == ex && tmp_y == ey) {  // 마지막 'E' 지점을 만났을 때는,
						solve[tmp_y][tmp_x] = Math.min(solve[ny][nx] + 1, solve[tmp_y][tmp_x]);  // 최소값을 지정한다.
					} else {
						solve[tmp_y][tmp_x] = solve[ny][nx] + 1;  // 아니면 이전 값에 1을 더하여 저장한다.
					}
					visit[tmp_y][tmp_x] = true;  // 방문체크
					que.add(tmp_x); que.add(tmp_y);  // 현재 값을 큐에 넣고 다음 bfs로 간다.
				}
				if(tmp_x == ex && tmp_y == ey) que.clear();  // 종료지점을 만나면 큐의 값들을 빼고, 바로 빠져나간다.
			}
		}
		
		// 종료지점의 값이 초기 지정한 max값이면 길이 없는 것으로 간주하여 -1로 출력 
		bw.write(String.valueOf(solve[ey][ex] == Integer.MAX_VALUE ? -1 : solve[ey][ex]));
		bw.flush();
	}

}
