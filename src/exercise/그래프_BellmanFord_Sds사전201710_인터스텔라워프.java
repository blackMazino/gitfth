package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그래프_BellmanFord_Sds사전201710_인터스텔라워프 {

/*				
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
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/사전201710.txt"));
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