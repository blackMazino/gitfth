package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Day7_03_Geo_convexHull {
/*
2차원 평면에 N개의 점이 주어졌을 때, 이들 중 몇 개의 점을 골라 볼록 다각형을 만드는데, 나머지 모든 점을 내부에 포함하도록 할 수 있다. 이를 볼록 껍질 (Convex Hull) 이라 한다.
N개의 점이 주어질 때 볼록껍질을 이루는 점의 개수를 구하여라. 만약 볼록껍질을 이루는 한 선분에 3개 이상의 점이 포함된 경우, 양 끝점만을 센다.
첫 번째 줄에 점의 개수 N이 주어진다. (3 ≤ N ≤ 100,000)

두 번째 줄부터 N개의 줄에 걸쳐 각 점의 좌표를 의미하는 두 정수 x, y가 공백으로 분리되어 주어진다. (-40,000 ≤ x, y ≤ 40,000) 각 점은 모두 다른 위치에 있으며, 모든 점이 한 직선위에 있는 경우는 없다.

8
1 1
1 2
1 3
2 1
2 2
2 3
3 1
3 2

5
*/
	
	static int N;
	static Point3[] A;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new Point3[N];
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				A[i] = new Point3(x,y);
			}
			//y좌표가 가장 작거나, 같은경우에는 x좌표가 가장 작은점을 0번인덱스로 한다
	        for (int i=1;i<N;i++){
	            if (A[0].y > A[i].y || A[0].y == A[i].y && A[0].x > A[i].x){
	                Point3 tmp = A[0];
	                A[0] = A[i];
	                A[i] = tmp;
	            }
	        }
	        //0번인덱스가 기준점이 되는 벡터량으로 변환한다(일반벡터에서 3,2를 말하는건 기준점이 0,0일때를 말한다)
	        for (int i=N-1;i>=0;i--){
	            A[i].x -= A[0].x;
	            A[i].y -= A[0].y;
	        }
	        //각도순으로 정렬
	        Arrays.sort(A, 1, N, new Comparator<Point3>() {
	            public int compare(Point3 a, Point3 b) {
	                // sign <=> a - b
	                // negtive: a < b
	                // potivie: a > b
	                // zero: a == b
	                // priority
	                int v = ccw(new Point3(0,0),a, b);
	                if (v > 0) return -1; // 0 -> a: b ccw
	                if (v < 0) return 1;
	                return (Math.abs(a.x)+a.y) - (Math.abs(b.x)+b.y);
	            }
	        });
	        
	        
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
    static int ccw(Point3 a, Point3 b, Point3 c) {
        long k = (long)(b.x-a.x)*(c.y-a.y) - (long)(c.x-a.x)*(b.y-a.y);
        //     = a.x *by + b.x*c.y + c.x*a.y - ( a.y*b.x + b.y*c.x + c.x*a.y )
        if (k > 0) return 1;
        if (k < 0) return -1;   	
    	return 0;
    }

}

class Point3 {
    public Point3(int x, int y) {
        this.x = x;
        this.y = y;
    }
      
    int x, y;
}
/* 
//convex hull
import java.io.*;
import java.util.*;
  
class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
      
    int x, y;
}
  
public class source {
    static int N;
    static Point[] A;
      
    static int ccw(int ax, int ay, int bx, int by, int cx, int cy) {
        long v = (long)(bx-ax)*(cy-ay) - (long)(cx-ax)*(by-ay);
        if (v > 0) return 1;
        if (v < 0) return -1;
        return 0;
    }
      
    static int ccw(Point a, Point b, Point c) {
        return ccw(a.x, a.y, b.x, b.y, c.x, c.y);
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new Point[N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            A[i] = new Point(x, y);
        }
        for (int i=1;i<N;i++){
            if (A[0].y > A[i].y || A[0].y == A[i].y && A[0].x > A[i].x){
                Point tmp = A[0];
                A[0] = A[i];
                A[i] = tmp;
            }
        }
        for (int i=N-1;i>=0;i--){
            A[i].x -= A[0].x;
            A[i].y -= A[0].y;
        }
        Arrays.sort(A, 1, N, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                // sign <=> a - b
                // negtive: a < b
                // potivie: a > b
                // zero: a == b
                // priority
                int v = ccw(new Point(0, 0), a, b);
                if (v > 0) return -1; // 0 -> a: b ccw
                if (v < 0) return 1;
                return (Math.abs(a.x)+a.y) - (Math.abs(b.x)+b.y);
            }
        });
        ArrayList <Integer> stk = new ArrayList<>();//stack
        stk.add(0);
        for (int i=1;i<N;i++){
            while (stk.size() > 1 && ccw(A[stk.get(stk.size()-2)], A[stk.get(stk.size()-1)], A[i]) <= 0){
                stk.remove(stk.size()-1);
            }
            stk.add(i);
        }
        System.out.println(stk.size());
    }
}
*/