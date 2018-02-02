package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day7_00_Geo_ccwNdistance {

	static int N;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			
			
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
	//ccw
    static int ccw(Point2D a, Point2D b, Point2D c) {
        long tmp  =  a.x * b.y + b.x * c.y + c.x * a.y;
             tmp -= (a.y * b.x + b.y * c.x + c.y * a.x); 
        if (tmp < 0)
            return -1;
        if (tmp > 0)
            return 1;
        return 0;
    }
    
    // p1,p2를 잇는 선분과, p2,p3를 있는 선분이 교차하는지확인 <0 : 교차, >0 : 교차안함, =0 : 확인필요(접하는지 안하는지)
    static int isCross (Point2D p1,Point2D p2,Point2D p3,Point2D p4){
    	int ccw1 = ccw(p1,p2,p3)*ccw(p1,p2,p4);
    	int ccw2 = ccw(p3,p4,p1)*ccw(p3,p4,p2);
    	return ccw1*ccw2;
    }
    
    //한선분과 한점간의 최소거리 구하기
    
    
    //두벡터로 만들어지는 평행사변형의 넓이 구하기
    //벡터 ->a, ->b가 만드는 삼각형의 넓이 : 벡터내적(dotProduct)
    //평행사변형의 넓이(a dot b)를 구해서 전체 합에 /2해도 똑같다
    //평행사변형의 넓이 : |a2*b1-a1*b2|
    static long getArea(Point2D a, Point2D b){
    	return Math.abs(a.y*b.x -a.x*b.y) ;
    }
}

class Point2D {
    long x;
    long y;
 
    Point2D(long x, long y) {
        this.x = x;
        this.y = y;
    }
}






 