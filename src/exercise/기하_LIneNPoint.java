package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 기하_LIneNPoint {

	static Point cPoint;//교점
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		st = new StringTokenizer(br.readLine());
		
//		double x1 = Double.parseDouble(st.nextToken());
//		double y1 = Double.parseDouble(st.nextToken());
//		double x2 = Double.parseDouble(st.nextToken());
//		double y2 = Double.parseDouble(st.nextToken());
//		double x3 = Double.parseDouble(st.nextToken());
//		double y3 = Double.parseDouble(st.nextToken());
//		double x4 = Double.parseDouble(st.nextToken());
//		double y4 = Double.parseDouble(st.nextToken());
//		Point a = new Point(x1,y1);
//		Point b = new Point(x2,y2);
//		Point c = new Point(x3,y3);
//		Point d = new Point(x4,y4);
//		System.out.println(isCross(a,b,c,d));
//		System.out.println(ccw(a,b,c) +","+ isInternalPoint(a,b,c));
//		System.out.println(ccw(a,b,d) +","+ isInternalPoint(a,b,d));
//		System.out.println(ccw(c,d,a) +","+ isInternalPoint(c,d,a));
//		System.out.println(ccw(c,d,b) +","+ isInternalPoint(c,d,b));
		
		//선분과 점의 최단거리
		DistanceBetweenLineNPoint();
				
		st = new StringTokenizer(br.readLine());
		//두선분의 교점 구하기
		PointBetween2Lines();
	}

	private static void PointBetween2Lines() {
		double x1 = Double.parseDouble(st.nextToken());
		double y1 = Double.parseDouble(st.nextToken());
		double x2 = Double.parseDouble(st.nextToken());
		double y2 = Double.parseDouble(st.nextToken());
		double x3 = Double.parseDouble(st.nextToken());
		double y3 = Double.parseDouble(st.nextToken());
		double x4 = Double.parseDouble(st.nextToken());
		double y4 = Double.parseDouble(st.nextToken());
		Point a = new Point(x1,y1);
		Point b = new Point(x2,y2);
		Point c = new Point(x3,y3);
		Point d = new Point(x4,y4);
		if(isCross(a,b,c,d)){//a,b,c,d가 아닌 한점에서 만나는 경우
			cPoint = getCrossPoint(new Line(a,b), new Line(c,d));
			return;
		}else{
			//포함시키는 경우(전체 또는 부분)
			
			//한점이 포함되되는 경우
			if(ccw(a,b,c)==0 && isInternalPoint(a,b,c)){
				cPoint = c;
				return;
			}
			if(ccw(a,b,d)==0 && isInternalPoint(a,b,d)){
				cPoint = d;
				return;
			}
			if(ccw(c,d,a)==0 && isInternalPoint(c,d,a)){
				cPoint = a;
				return;
			}
			if(ccw(c,d,b)==0 && isInternalPoint(c,d,b)){
				cPoint = b;
				return;
			}
			
			//그외 : 평행일 경우 
		}
		
	}

	private static boolean isInternalPoint(Point a, Point b, Point c) {
		double dxAB = b.x-a.x;
		double dyAB = b.y-a.y;
		double dxAC = c.x-a.x;
		double dyAC = c.y-a.y;
		
		if( (dxAB*dxAC < 0 )|| (dyAB*dyAC < 0) ) return false;
		if ( Math.pow(dxAB, 2) + Math.pow(dyAB, 2) > Math.pow(dxAC, 2) + Math.pow(dyAC, 2)){
			return true;
		}		
		return false;
	}

	private static Point getCrossPoint(Line L1, Line L2) {
		//교점
		double ax=0;
		double ay=0;
		
		
		return new Point (ax,ay);		
	}



	private static void DistanceBetweenLineNPoint() {
		
		double x1 = Double.parseDouble(st.nextToken());
		double y1 = Double.parseDouble(st.nextToken());
		double x2 = Double.parseDouble(st.nextToken());
		double y2 = Double.parseDouble(st.nextToken());
		Point p1 = new Point(x1,y1);
		Point p2 = new Point(x2,y2);
				
		double x3 = Double.parseDouble(st.nextToken());
		double y3 = Double.parseDouble(st.nextToken());
		Point p = new Point(x3,y3);// 주어진 점
		
		double minDistance = getMinDistancePoint(new Line(p1,p2) , p);
		System.out.println(minDistance);				
	}

	private static double getMinDistancePoint(Line L, Point p) {
		//교점
		double ax=0;
		double ay=0;
		
		//수직인 경우		
		if(L.p1.x == L.p2.x){
			ax = L.p1.x;
			ay = p.y;   
		}else if(L.p1.y == L.p2.y){//수평인 경우
			ax = p.x;
			ay = L.p1.y;
		}else{
			//기울기 M = y1-y2 / x2-x1 ( x1 != x2)
			//y절편 K = y1-m*x1
			double m1 = (L.p1.y - L.p2.y) / (L.p2.x-L.p1.x);
			double k1 = L.p1.y - m1 * L.p1.x;
			
			//수선의 방정식 m2*m1 = -1
			double m2 = (-1.0)/m1;
			double k2 = p.y - m2*p.x;
			
			//y = m1 * x + k1
			//y = m2 * x + k2
			//x = (k2-k1) / (m1-m2)
			ax = (k2-k1) / (m1-m2);
			ay = m1 * ax + k1;			
		}
		
		//범위체크
		if( Math.min(L.p1.x, L.p2.x) <= ax  &&  ax <=Math.max(L.p1.x, L.p2.x)
		 && Math.min(L.p1.y, L.p2.y) <= ay  &&  ay <=Math.max(L.p1.y, L.p2.y)){
			cPoint = new Point(ax,ay);//교점이 있다
			return getDistance(p,cPoint);
		}else{
			return Math.min(getDistance(L.p1, new Point(ax,ay))
					      , getDistance(L.p2, new Point(ax,ay)));
		}			
	}

	private static double getDistance(Point a, Point b) {
		return Math.sqrt( Math.pow(b.x-a.x, 2)+ Math.pow(b.y-a.y, 2));
	}

	
	private static boolean isCross(Point a, Point b, Point c, Point d) {
		return (ccw(a,b,c) * ccw(a,b,d) < 0 
		     && ccw(c,d,a) * ccw(c,d,b) < 0);		
	}

	private static int ccw(Point a, Point b, Point c) {
		double val = a.x*b.y-b.x*a.y
				   + b.x*c.y-c.x*b.y
				   + c.x*a.y-a.x*c.y;
		if(val>0) return 1;
		if(val<0) return -1;		
		return 0;
	}


}
class Point{
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	double x,y;
}

class Line{
	public Line(Point p1, Point p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	Point p1, p2;
}
//class LineEquation{
//	public LineEquation(Point p1, Point p2, double m, double k) {
//		super();
//		this.p1 = p1;
//		this.p2 = p2;
//		this.m = m;
//		this.k = k;
//	}
//	Point p1,p2;
//	double m,k;
//}