package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
class Map {
    long x, y;
    public Map(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
 
public class polygons_area {
 
    static int n;
    static ArrayList<Map> map[];
    static long solve = 0;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("polygons_area_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        n = Integer.parseInt(br.readLine());
         
        map = new ArrayList[n+2];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
             
            map[i] = new ArrayList<>(); 
            map[i].add(new Map(x,y));
        }
         
        // 점의 순서대로 벡터의 곱을 구해서 삼각형의 넓이를 구하여 solve 값에 더한다.
        // - 벡터값을 더하면 다각형의 넓이가 된다.
        for(int i = 1; i <= n; i++) {
            int temp = i % n + 1; // y를 x + 1로 만들고, x1까지 한번 더 가기 위해
            // 다각형의 넓이를 구하는 식(x1*y2 - x2*y1 + x2*y3 - x3*y2 + x3*y1 - x1*y3)
            // - 삼각형의 넓이를 구하는 식을 벡터별로 반복
            solve += map[i].get(0).x * map[temp].get(0).y - map[temp].get(0).x * map[i].get(0).y;
        }
        solve = Math.abs(solve); // double이나 float으로 저장하면 값이 정확히 나오지 않을 수 있으므로 String으로 출력한다.
        bw.write((solve/2) + "." + (solve%2==1?"5":"0") + "\n");  // 합을 2로 나눈 값이 다각형의 넓이
        bw.flush();
    }
}