package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day7_02_Geo_점의위치 {
/*
이차원 평면에서 점 N개로 이루어진 다각형이 주어진다.

그리고 평면위의 점 P1, P2가 주어졌을때 점 P1, P2가 다각형의 외부에 있는지, 내부에 있는지 판별하는 프로그램을 작성하시오.

점 P1, P2 는 주어진 다각형의 선분 위에 존재하지는 않는다.

첫째 줄에 다각형을 이루는 점의 갯수 (1 ≦ N ≦ 100,000)이 주어지고,

둘째 줄 부터 N개의 점의 정수 좌표(-10^9 ≦ x, y ≦ 10^9)가 입력으로 주어진다.

다각형의 좌표는 시계 방향 또는 반시계 방향 순서로 주어진다.

마지막 두 줄에는 점 P1, p2 의 좌표가 주어진다.

출력
첫째 줄에는 점 P1가 주어진 다각형 안에 존재한다면 "in", 밖에 존재한다면 "out"을 출력한다.

둘째 줄에는 점 P2가 주어진 다각형 안에 존재한다면 "in", 밖에 존재한다면 "out"을 출력한다.

4
1 1
1 3
3 3
3 1
0 0
2 2

out
in
*/
	static int N;
    static Point[] A;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
	        A = new Point[N+1];
	        for (int i=1;i<=N;i++){
	            st = new StringTokenizer(br.readLine());
	            int x = Integer.parseInt(st.nextToken());
	            int y = Integer.parseInt(st.nextToken());
	            A[i] = new Point(x, y);
	        }
	      //주어진 점을 p3라고 하고 무한히 먼 점 하나 p4를 잡고
          //다각형의 모든 선분과ccw를 돌려 교차하는지 본다 -> 홀수개랑 겹침 : IN, 짝수개랑 겹침 : OUT
	        for (int t=0;t<2;t++){
	            st = new StringTokenizer(br.readLine());
	            int x = Integer.parseInt(st.nextToken());
	            int y = Integer.parseInt(st.nextToken());
	            int cnt = proc(x, y);
	            System.out.println((cnt&1) == 1 ? "in" : "out");//cnt&1 홀수면 1, 짝수면 0
	        }
			 
			
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
	private static int proc(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

}
class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
  
    int x, y;
}
/*
//점의 위치
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
      
    static int ccw(Point a, Point b, Point c) {
        long k = (long)(b.x-a.x)*(c.y-a.y) - (long)(c.x-a.x)*(b.y-a.y);
        if (k > 0) return 1;
        if (k < 0) return -1;
        return 0;
    }
      
    static boolean is_cross(Point a, Point b, Point c, Point d) {
        return ccw(a, b, c) * ccw(a, b, d) < 0 &&
                ccw(c, d, a) * ccw(c, d, b) < 0;
    }
      
    static int proc(int x1, int y1) {
        int x2 = (int)1e9 + 1, y2 = y1 + 1;
        Point p = new Point(x1, y1);
        Point q = new Point(x2, y2);
        int ret = 0;
        for (int i=1;i<=N;i++){
            int j = i%N + 1;
            if (is_cross(p, q, A[i], A[j]))
                ret++;
        }
        return ret;
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new Point[N+1];
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            A[i] = new Point(x, y);
        }
        for (int t=0;t<2;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cnt = proc(x, y);
            System.out.println((cnt&1) == 1 ? "in" : "out");
        }
    }
}*/
 