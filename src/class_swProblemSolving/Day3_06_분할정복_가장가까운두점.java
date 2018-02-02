package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Day3_06_분할정복_가장가까운두점 {
/*
문제
2
2차원 평면상에 
n
n개의 점이 주어졌을 때, 이 점들 중 가장 가까운 두 점을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 n(2≤n≤500,000)이 주어진다. 다음 n개의 줄에는 차례로 각 점의 x,y좌표가 주어진다. 
각각의 좌표는 절댓값이 10,000을 넘지 않는 정수이다.

출력
첫째 줄에 가장 가까운 두 점의 거리의 제곱을 출력한다.

4
0 0
10 10
0 10
10 0

100
*
*/
	static int N;
	static Pair[] xy, mergeTemp;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			xy = new Pair[N+1];
			mergeTemp = new Pair[N+1];
			for(int i=1;i<=N;i++){
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				xy[i] = new Pair(x,y);
			}

			//X축에 정렬
			Arrays.sort(xy, 1, N+1, new Comparator<Pair>(){
				@Override
				public int compare(Pair o1, Pair o2) {
					return o2.x - o1.x;
				}	
			});
			
			System.out.println(dfs(1,N));
//		}
		br.close();
	}

	private static int dfs(int s, int e) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int getDistance(Pair pair, Pair pair2) {
		return (int)( Math.pow( (pair2.getX()-pair.getX()) , 2) 
				    + Math.pow( (pair2.getY()-pair.getY()) , 2) );		
	}
}
class Pair implements Comparator <Pair> {

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	//아래 두줄 드래그하여 커서올려 둔 상태에서 우클릭> source> Generate getter setter 
	int x;
	int y;
	
	public Pair(int x,int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public int compare(Pair o1, Pair o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int compareX(Pair o1, Pair o2) {		
		return o2.x - o1.x;
	}	

	public int compareY(Pair o1, Pair o2) {
		return o2.y - o1.y;
	}	

}

/*

import java.io.*;
import java.util.*;
  
class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
  
    int x, y;
}
  
public class source{
    static int N;
    static Point[] A, merge_tmp;
      
    static int dist(Point a, Point b) {
        return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
    }
      
    static int dfs(int s, int e) {
        if (e-s < 9){
            int ret = Integer.MAX_VALUE;
            for (int i=s;i<e;i++)  for (int j=i+1;j<=e;j++){
                ret = Math.min(ret, dist(A[i], A[j]));
            }
            Arrays.sort(A, s, e+1, new Comparator<Point>() {
                public int compare(Point a, Point b) {
                    return a.y - b.y;
                }
            });
            return ret;
        }
        int m = s+e >> 1, div_x = A[m].x;
        int d = Math.min(dfs(s, m), dfs(m+1, e));
        // Merging starts 
        for (int l=s,r=m+1,i=s;i<=e;i++){
            if (r > e || l <= m && A[l].y < A[r].y) merge_tmp[i] = A[l++];
            else merge_tmp[i] = A[r++];
        }
        for (int i=s;i<=e;i++) A[i] = merge_tmp[i];
        // Merging ended 
        ArrayList <Point> arr = new ArrayList<>();
        for (int i=s;i<=e;i++){
            if ((A[i].x-div_x)*(A[i].x-div_x) <= d)
                arr.add(A[i]);
        }
        int ret = d;
        for (int i=0;i<arr.size();i++){
            for (int j=i+1;j<arr.size()&&j-i<13;j++){
                ret = Math.min(ret, dist(arr.get(i), arr.get(j)));
            }
        }
        return ret;
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new Point[N+1]; merge_tmp = new Point[N+1];
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            A[i] = new Point(x, y);
        }
        Arrays.sort(A, 1, N+1, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                return a.x - b.x;
            }
        });
        System.out.println(dfs(1, N));
    }
}
*/