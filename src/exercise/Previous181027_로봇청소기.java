package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
로봇청소기가 청소를 한다.
로봇청소기는 반드시 아래와 같은 순서대로만 움직인다(1이 불가시, 2, 2가 불가시 3..)
1. 좌회전
2. 직진
3. 우회전
4. 뒤로 돌아 전진
1,2,3,4 불가시 정지
시작점 : >
벽 : #(편의사 바깥쪽은 모두 벽이라고 하자)
그외 : .(이동가능)
벽을 하나 더 추가해서 로봇청소기가 움직이는 가장 작은 이동범위의 값을 구해보자
단 벽은 기존 벽에 붙여서만 추가할 수 있다
ex)
# # # # #
# > . . #
# . # . #
# . # . #
# # # # #

인경우 

# # # # #
# > X . #
# . # . #
# . # . #
# # # # #
인경우 4로 가장 적은 이동거리를 가진다.

로봇청소기가 전혀 이동 못하게 되는 경우에는 1이 답이다.

3<N,M<3000

#1 1
#2 4
#3 1
#4 4
#5 22

*/
public class Previous181027_로봇청소기 {
	
	static int TC,N,M;
	static char[][]g;
	static int sx,sy, px,py, pd;
	static ArrayList<int[]> list;
	static ArrayList<int[]> cutList;
	static int [][] dTable;
	static boolean [][] visited;
	//현재방향
	static final int U = 0;
	static final int R = 1;
	static final int D = 2;
	static final int L = 3;
	//청소기 진행방향
	static final int RL = 0;//turn left
	static final int RF = 1;//go direction
	static final int RR = 2;//turn right
	static final int RB = 3;//go back	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous181027.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		makeDiretionTable();
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N =Integer.parseInt(st.nextToken());
			M =Integer.parseInt(st.nextToken());
			g = new char[N][M];//from 0,0
			
			list = new ArrayList<int[]>();
			cutList = new ArrayList<int[]>();
			for(int n=0;n<N;n++){
				String L = br.readLine();
				for(int m=0;m<M;m++){
					char c = L.charAt(m);
					g[n][m] = c;
					if(c=='>'){
						sx = m;
						sy = n;
					}
					if(c=='.'){
						list.add(new int[]{m,n});
					}
				}				
			}
			int startPointBlockcnt = 0;
			if(g[sy-1][sx]=='#')  startPointBlockcnt ++;
			if(g[sy][sx+1]=='#')  startPointBlockcnt ++;
			if(g[sy+1][sx]=='#')  startPointBlockcnt ++;
			if(g[sy][sx-1]=='#')  startPointBlockcnt ++;
			long answer = Long.MAX_VALUE;
			if(startPointBlockcnt>=3){
				answer = 1;
			}else{
				int tx = 0;
				int ty = 0;
				for(int i=0;i<list.size();i++){
					if(!isBlockPossible(list.get(i))){//단절점도 같이 찾음
						continue;				
					}
//					tx = list.get(i)[0];
//					ty = list.get(i)[1];									
//					visited = new boolean [N][M];
////					System.out.println("blockPoint : "+tx+","+ty);
//					answer = Math.min(answer, getMovingLength(tx,ty));						
					//단절점을 찾는다 : 생각못함. 찾아서 list에 add					
				}
				System.out.println("tc : "+tc+"'s cutlist.size : "+cutList.size());
//				//단절점이 있는 경우
				if(cutList.size()>0){
					for(int i=0;i<cutList.size();i++){
						tx = cutList.get(i)[0];
						ty = cutList.get(i)[1];
						visited = new boolean [N][M];
						answer = Math.min(answer, getMovingLength(tx,ty));	
						
					}
				}else{//단절점이 없는 경우 : 1회만 돌린다
					for(int i=0;i<list.size();i++){
						if(isBlockPossible(list.get(i))){
							tx = list.get(i)[0];
							ty = list.get(i)[1];
//							System.out.println(tx+","+ty);
							break;
						}					
					}
					visited = new boolean [N][M];
					answer = Math.min(answer, getMovingLength(tx,ty));					
				}						
			}
			System.out.println("#"+tc+" "+answer);
		}

	}


	private static void updateCutPoint(int tx, int ty) {
		
		char[][] tmp = new char[3][3];
		tmp[0][0] = g[ty-1][tx-1];
		tmp[0][1] = g[ty-1][tx]; 
		tmp[0][2] = g[ty-1][tx+1]; 
		tmp[1][0] = g[ty][tx-1]; 
		tmp[1][1] = g[ty][tx]; 
		tmp[1][2] = g[ty][tx+1]; 
		tmp[2][0] = g[ty+1][tx-1]; 
		tmp[2][1] = g[ty+1][tx]; 
		tmp[2][2] = g[ty+1][tx+1];
		

		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print(tmp[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("============");
		if( (g[ty-1][tx-1]=='#' &&  g[ty+1][tx]  =='#' && g[ty][tx-1]=='.') //0
		  ||(g[ty-1][tx]  =='#' && (g[ty+1][tx-1]=='#'  //1
			                      ||g[ty+1][tx]  =='#'
			                      ||g[ty+1][tx+1]=='#'))
	      ||(g[ty-1][tx+1]=='#' &&  g[ty+1][tx]  =='#') //2
	      ||(g[ty][tx-1]  =='#' && (g[ty-1][tx+1]=='#'  //3
						          ||g[ty][tx+1]  =='#'
						          ||g[ty+1][tx+1]=='#'))
	      ||(g[ty][tx+1]  =='#' && (g[ty-1][tx-1]=='#'  //4
						          ||g[ty][tx-1]  =='#'
						          ||g[ty+1][tx-1]=='#'))
	      ||(g[ty+1][tx-1]=='#' && (g[ty][tx-1]  =='#'))  //5
	      ||(g[ty+1][tx]  =='#' && (g[ty-1][tx-1]=='#'  //6
						          ||g[ty-1][tx]  =='#'
						          ||g[ty-1][tx+1]=='#'))
	      ||(g[ty+1][tx+1]=='#' && (g[ty-1][tx]  =='#'))  //7         
		  ){
			System.out.println(tx+","+ty);
			
			cutList.add(new int[]{tx,ty});
		}
		/*
		0 1 2
		3 A 5
		6 7 8	
		 */
		char[] c = new char[9];
		c[0] = g[ty-1][tx-1];
		c[1] = g[ty-1][tx]; 
		c[2] = g[ty-1][tx+1]; 
		c[3] = g[ty][tx-1]; 
		c[4] = g[ty][tx]; 
		c[5] = g[ty][tx+1]; 
		c[6] = g[ty+1][tx-1]; 
		c[7] = g[ty+1][tx]; 
		c[8] = g[ty+1][tx+1];
		//A가 단절점이 되기 위한 조건
		if(c[0]=='#' && c[7]=='#' && c[3]!='#'){
			
		}else if(c[1]=='#' && c[7]=='#' && c[3]!='#' && c[5]!='#'){
			
		}else if(c[2]=='#' && c[7]=='#' && c[5]!='#'){
			
		}else if(c[3]=='#' && c[5]=='#' && c[1]!='#' && c[7]!='#'){
			
		}else if(c[5]=='#' && c[3]=='#' && c[1]!='#' && c[7]!='#'){
			
		}else if(c[6]=='#' && c[1]=='#' && c[1]!='#' && c[7]!='#'){
			
		}
		
	}

	//현재방향과 로봇이 진행할 방향으로 다음 방향의 값을 가지고 있다
	private static void makeDiretionTable() {
		dTable = new int [4][4];//현재방향, 진행방향
		dTable[U][RL] = L;
		dTable[U][RF] = U;
		dTable[U][RR] = R;
		dTable[U][RB] = D;
		
		dTable[R][RL] = U;
		dTable[R][RF] = R;
		dTable[R][RR] = D;
		dTable[R][RB] = L;
		
		dTable[D][RL] = R;
		dTable[D][RF] = D;
		dTable[D][RR] = L;
		dTable[D][RB] = U;
		
		dTable[L][RL] = D;
		dTable[L][RF] = L;
		dTable[L][RR] = U;
		dTable[L][RB] = R;		          		
	}
	private static long getMovingLength(int bx, int by) {
		g[by][bx]='X';//막고
		long result = 0;
//		result = doClean();
		px = sx;
		py = sy;
		pd = R;
		while(true){
			if(go(RL)){
				visited[py][px] = true;
				result ++;
			}else if(go(RF)){
				visited[py][px] = true;
				result ++;
			}else if(go(RR)){
				visited[py][px] = true;
				result ++;
			}else if(go(RB)){
				visited[py][px] = true;
				result ++;
			}else{
				
			}
			if(px==sx && py==sy){
				if(pd == R){//방향까지 일치해야함					
					break;
				}else{//방향이 일치하지 않더라도 바로 다음 스텝이 이미 가본 곳이면 break
					int xx=px;
					int yy=py;
					int dd=pd;
					if(go(RL)){
					}else if(go(RF)){
					}else if(go(RR)){
					}else if(go(RB)){						
					}else{
						//do nothing
					}
					if(visited[py][px]){						
						break;
					}else{
						px = xx;
						py = yy;
						pd = dd;
					}
				}					
			}	
		}
//		System.out.println("blockPoint : "+bx+","+by+"'s result is "+result);
		g[by][bx]='.';//원복
		return result;
	}
	

	private static boolean go(int d) {
		int nD = dTable[pd][d];
		if(nD == U){
			if(g[py-1][px]=='#' || g[py-1][px]=='X'){
				return false;
			}else{
				py--;				
			}
		}else if(nD == R){
			if(g[py][px+1]=='#' || g[py][px+1]=='X'){
				return false;
			}else{
				px++;				
			}			
		}else if(nD == D){
			if(g[py+1][px]=='#' || g[py+1][px]=='X'){
				return false;
			}else{
				py++;				
			}						
		}else{//nD == L
			if(g[py][px-1]=='#' || g[py][px-1]=='X'){
				return false;
			}else{
				px--;				
			}					
		}		
		pd = nD;
		return true;
	}
	
	private static boolean isBlockPossible(int[] xy) {
		//상하좌우 체크하여 #이 있다면 true;
		int x = xy[0];
		int y = xy[1];
		if(g[y-1][x]=='#' 
		|| g[y][x+1]=='#'
		|| g[y+1][x]=='#'
		|| g[y][x-1]=='#'){		
			updateCutPoint(x,y);
			return true;
		}else{
			return false;	
		}
	}

}


