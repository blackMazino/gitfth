package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Previous181201_울타리2 {

	static int TC,N,M,sx,sy,tx,ty,answer;
	static Mt sP,tP;
	static ArrayList<Mt[]> list;
	static LinkedList<Mt> poligon;
	static List<LinkedList> poligonList;	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a =Integer.parseInt(st.nextToken());
				int b =Integer.parseInt(st.nextToken());
				int c =Integer.parseInt(st.nextToken());
				int d =Integer.parseInt(st.nextToken());
				Mt tmp[] = new Mt[2];
				tmp[0] = new Mt(a,b);
				tmp[1] = new Mt(c,d);
				list.add(tmp);
			}			
			st = new StringTokenizer(br.readLine());
			int sx =Integer.parseInt(st.nextToken());
			int sy =Integer.parseInt(st.nextToken());	
			Mt sP = new Mt (sx,sy);
			st = new StringTokenizer(br.readLine());
			int tx =Integer.parseInt(st.nextToken());
			int ty =Integer.parseInt(st.nextToken());
			Mt tP = new Mt (tx,ty);
			
			//poligon grouping
			makePoligonList();
			
			//count by each poligon
			answer = 0;
			doCount();
			
			System.out.println("#"+tc+" ");
		}
	}
	
	private static void doCount() {
		for(LinkedList<Mt> poligon : poligonList){
			int cnt = 0;
			for(int i=0;i<poligon.size();){
				Mt a = poligon.get(i);				
				i++;
				Mt b = poligon.get(i);
				if(isCross(a,b,sP,tP)){
				 cnt++;	
				}				
			}
			if(cnt%2==1) answer ++;
		}
		
	}

	private static void makePoligonList() {
		poligonList = new ArrayList<>();
		Mt[] t = list.get(0);
		LinkedList<Mt> poligon = new LinkedList<>();
		poligon.add(t[0]);
		poligon.add(t[1]);
		list.remove(0);
		Mt s = t[0];
		while(true){
			for(int i=0;i<list.size();i++){
				if(poligon.getLast().x == list.get(i)[0].x
				&& poligon.getLast().y == list.get(i)[0].y){
					Mt tmp = list.get(i)[1];
					poligon.add(tmp);
					break;
				}
				if(poligon.getLast().x == list.get(i)[1].x
				&& poligon.getLast().y == list.get(i)[1].y){
					Mt tmp = list.get(i)[0];
					poligon.add(tmp);
					break;
				}				
			}
			if(poligon.getLast().x == s.x
			&& poligon.getLast().y == s.y){				
//				poligon.add(new Mt(Integer.MIN_VALUE, Integer.MIN_VALUE));
				poligonList.add(poligon);
				if(list.isEmpty()) break;
				poligon.clear();
				Mt[] n = list.get(0);
				poligon.add(n[0]);
				poligon.add(n[1]);
				list.remove(0);
				s = n[0];
			}
		}
//		poligon.add(new Mt(Integer.MIN_VALUE, Integer.MIN_VALUE));
	}

	public static boolean isCross(Mt a,Mt b,Mt c,Mt d){
		return (ccw(a,b,c)*ccw(a,b,d) < 0
			  &&ccw(c,d,a)*ccw(c,d,b) < 0 );
	}

	private static int ccw(Mt a, Mt b, Mt c) {
		long val = ((long)a.x*b.y - (long)b.x*a.y
				   +(long)b.x*c.y - (long)c.x*b.y
				   +(long)c.x*a.y - (long)a.x*c.y
				   );
		if(val>0) return 1;
		else if(val <0) return -1;
		return 0;
	}
	

}
class Mt {
	int x,y;

	public Mt(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}