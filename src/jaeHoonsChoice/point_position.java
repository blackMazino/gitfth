package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
class Point {
    int x,y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
public class point_position {
 
    static int n;
    static ArrayList<Point> list[];
    static Point p, cp;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("point_position_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
             
            list[i] = new ArrayList<>();
            list[i].add(new Point(a,b));
        }
         
        for(int i = 1; i <= 2; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            p = new Point(a,b);  // 점과 비교할 수 있도록 확인
            a = a + (int)1e9;
            b = b + 1;
            cp = new Point(a, b);  // x축은 멀리 벗어나고, y축은 1을 더해서 겹치지 않도록 비교점 지정
            if(process(p, cp)) {
                bw.write("in\n");
            } else {
                bw.write("out\n");
            }
        }
         
        bw.flush();
    }
 
    private static boolean process(Point p, Point cp) {
        // TODO Auto-generated method stub
        int num = 0;
        for(int i = 1; i <= n; i++) {
            int j = i % n + 1;
             
            // A 에서 B를 봤을 때 C 가 왼쪽과 오른쪽에 있어야 한다 = 왼쪽(+1) * 오른쪽(-1) = -1
            // 두 선을 모두 봐야 교차가 되므로 두 선 모두 비교한다.
            if( (ccw(p, cp, list[i].get(0)) * ccw(p, cp, list[j].get(0)) == -1) &&
                (ccw(list[i].get(0), list[j].get(0), p) * ccw(list[i].get(0), list[j].get(0), cp) == -1) ) {
                num++;  // 겹치는 점
            }
        }
         
        if(num%2 == 1) {  // 겹치는 점이 홀수일 때는 안쪽
             return true;
        } else {          // 겹치는 점이 짝수일 때는 바깥쪽
            return false;
        }
    }
 
    private static int ccw(Point A, Point B, Point C) {
        // TODO Auto-generated method stub
        long cal = 0;
         
        // 삼각형의 넓이를 구해서 확인한다. +이면 왼쪽, -이면 오른쪽(세타 값으로 확인)
        // A가 B를 바라볼 때, C의 위치
        // area = ( (bx - ax) * (cy - ay) - (by - ay) * (cx - ax) ) * 1/2  -> 벡터에서 많이 쓰이므로 숙지
        cal = (long)(B.x-A.x)*(C.y-A.y) - (long)(C.x-A.x)*(B.y-A.y);
         
        if(cal > 0) {
            return 1;
        } else if(cal < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}