package sdsPreTest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 사전201906_지상전투 {

	static int TC, N, r;
	static ArrayList<Duo> list;
	static ArrayList<Duo> subList;
	static ArrayList<Integer> notEndDuoIdxList;
	static int endMin[];
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201906.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

//		for(int i=0;i<=30;i++) System.out.println((int)Math.pow(2, i));
		
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++) {
			N = Integer.parseInt(br.readLine());
			
			list = new ArrayList<>();
			notEndDuoIdxList = new ArrayList<>();
			endMin = new int[N];
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				list.add(new Duo(x1,y1,x2,y2,i,32));
				endMin[i] = 32;
				notEndDuoIdxList.add(i);
			}
//			int x1 = 2;
//			int y1 = 2;
//			int x2 = 1;
//			int y2 = 1;
//			int x3 = 3;
//			int y3 = 3;
//			System.out.println(ccw(x1,y1,x2,y2,x3,y3));
			
			
//			Duo d1 = new Duo (1,1,4,4,0);
//			Duo d2 = new Duo (1,4,4,1,0);
//			Duo d3 = new Duo (2,2,1,4,0);
//			Duo d4 = new Duo (1,4,2,2,0);
//			Duo d5 = new Duo (2,2,3,3,0);
//			Duo d6 = new Duo (2,2,5,5,0);
//			Duo d7 = new Duo (0,0,5,5,0);
//			Duo d8 = new Duo (5,5,6,6,0);
//			Duo d9 = new Duo (1,4,2,3,0);
//			System.out.println(isBattleCase(d1,d2));
//			System.out.println(isBattleCase(d1,d3));
//			System.out.println(isBattleCase(d1,d4));
//			System.out.println(isBattleCase(d1,d5));
//			System.out.println(isBattleCase(d1,d6));
//			System.out.println(isBattleCase(d1,d7));
//			System.out.println(isBattleCase(d1,d8));
//			System.out.println(isBattleCase(d1,d9));
			int r = (int)Math.pow(2, 30);
			
			//0분일때 전투확인
			for(int i=0;i<list.size();i++){
				for(int j=0;j<list.size();j++){
					if(i==j){
						continue;
					}					
					if(isBattleCase(list.get(i), list.get(j))){
						endMin[i] = 0;
						endMin[j] = 0;
					}					
				}					
			}			
			notEndDuoIdxList = new ArrayList<>();
			for(int i=0;i<N;i++){
				if(endMin[i]==32) notEndDuoIdxList.add(i);
			}			
			
			
			for(int i=1;i<=31;i++){
				r/=2;
				if(notEndDuoIdxList.isEmpty()){
					break;
				}
				for(int j=0;j<notEndDuoIdxList.size();j++){
					int k = notEndDuoIdxList.get(j);
					list.get(k).x1 = list.get(k).x1/2; 
					list.get(k).y1 = list.get(k).y1/2; 
					list.get(k).x2 = list.get(k).x2/2;
					list.get(k).y2 = list.get(k).y2/2;
					
					if(i==10){//4사분면(x>0 && y<0) -> 3사분면 (x*-1)
						if(list.get(k).x1 > 0 && list.get(k).y1 <0) {
							list.get(k).x1 *=(-1);
						}
						if(list.get(k).x2 > 0 && list.get(k).y2 <0) {
							list.get(k).x2 *=(-1);
						}
					}
					if(i==20){//3사분면(x<0 && y<0) -> 2사분면 (y*-1)
						if(list.get(k).x1 < 0 && list.get(k).y1 <0) {
							list.get(k).y1 *=(-1);
						}
						if(list.get(k).x2 < 0 && list.get(k).y2 <0) {
							list.get(k).y2 *=(-1);
						}						
					}
					if(i==30){//2사분면(x<0 && y>0) -> 1사분면 (x*-1)
						if(list.get(k).x1 < 0 && list.get(k).y1 >0) {
							list.get(k).x1 *=(-1);
						}
						if(list.get(k).x2 < 0 && list.get(k).y2 >0) {
							list.get(k).x2 *=(-1);
						}								
					}
				}
				notEndDuoIdxList = doBattle(i);
			}
			
			bw.write("#"+tc);
			for(int i=0;i<endMin.length;i++){
				bw.write(" "+endMin[i]);
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	private static ArrayList<Integer> doBattle(int t) {
		ArrayList<Integer> result =  new ArrayList<>();
		
		for(int i=0;i<notEndDuoIdxList.size();i++){
			int p1Idx = notEndDuoIdxList.get(i);
			for(int j=0;j<notEndDuoIdxList.size();j++){				
				int p2Idx = notEndDuoIdxList.get(j);
				if(p1Idx==p2Idx){
					continue;
				}					
				if(isBattleCase(list.get(p1Idx), list.get(p2Idx))){
//					System.out.println(p1Idx+" "+p2Idx+" "+t);
					if(endMin[p1Idx]==32) endMin[p1Idx] = t;
					if(endMin[p2Idx]==32) endMin[p2Idx] = t;
					
				}					
			}					
		}
		for(int i=0;i<notEndDuoIdxList.size();i++){			
			int idx = notEndDuoIdxList.get(i);
			
			if(endMin[idx]==32) result.add(idx);			
		}
		return result;
	}
	private static int ccw (int x1,int y1, int x2, int y2, int x3, int y3){
		long val = (long)x1*y2-(long)x2*y1
				 + (long)x2*y3-(long)x3*y2
				 + (long)x3*y1-(long)x1*y3;
		if(val>0) return 1;
		if(val<0) return -1;
		return 0;
	}
	
	private static int ccw (Player a, Player b, Player c){
		long val = (long)a.x*b.y-(long)b.x*a.y
				 + (long)b.x*c.y-(long)c.x*b.y
				 + (long)c.x*a.y-(long)a.x*c.y;
		if(val>0) return 1;
		if(val<0) return -1;
		return 0;
	}
	private static boolean isBattleCase(Duo d1, Duo d2){		
		boolean result = false;
//		System.out.println(d1.x1+","+d1.y1+"|"+d1.x2+","+d1.y2);
//		System.out.println(d2.x1+","+d2.y1+"|"+d2.x2+","+d2.y2);
//		System.out.println();
		if( (d1.x1 == d2.x1 && d1.y1 == d2.y1)
		 || (d1.x1 == d2.x2 && d1.y1 == d2.y2)
		 || (d1.x2 == d2.x1 && d1.y2 == d2.y1)
		 || (d1.x2 == d2.x2 && d1.y2 == d2.y2)
		 ) {
			result =  true;
		}
		

		Player a = new Player(d1.x1, d1.y1);
		Player b = new Player(d1.x2, d1.y2);
		Player c = new Player(d2.x1, d2.y1);
		Player d = new Player(d2.x2, d2.y2);
		int ab = ccw(a,b,c) * ccw(a,b,d);
		int cd = ccw(c,d,a) * ccw(c,d,b);

		//선분 - 선분교차(점 아님)
		if(ab< 0 && cd< 0){
			result = true;
		}
		
		//선분-점교차
		if(ccw(a,b,c)==0 && isInternalPoint(a,b,c)) result = true;
		if(ccw(a,b,d)==0 && isInternalPoint(a,b,d)) result = true;
		if(ccw(c,d,a)==0 && isInternalPoint(c,d,a)) result = true;
		if(ccw(c,d,b)==0 && isInternalPoint(c,d,b)) result = true;
		
		
		
		return result;
	}
	private static boolean isInternalPoint(Player a, Player b, Player c) {
		long dxAB = b.x-a.x;
		long dyAB = b.y-a.y;
		long dxAC = c.x-a.x;
		long dyAC = c.y-a.y;
		
		if( (dxAB*dxAC < 0 )|| (dyAB*dyAC < 0) ) return false;
		if ( Math.pow(dxAB, 2) + Math.pow(dyAB, 2) > Math.pow(dxAC, 2) + Math.pow(dyAC, 2)){
			return true;
		}		
		return false;
	}

}
class Duo{
	int x1,y1,x2,y2,idx,rank;

	public Duo(int x1, int y1, int x2, int y2,int idx,  int rank) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.idx = idx;
		this.rank = rank;
	}
}
class Player{
	int x,y;

	public Player(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
