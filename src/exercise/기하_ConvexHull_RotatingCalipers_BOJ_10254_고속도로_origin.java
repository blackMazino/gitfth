package exercise;

import java.util.*;
import java.io.*;
 
public class 기하_ConvexHull_RotatingCalipers_BOJ_10254_고속도로_origin {  // BOJ 10254 고속도로
	static final int LIMIT = 10000000;
	static CityPoint p0;
    public static void main(String[] args) throws Exception {
    	int T, N, x, y;
    	int[] hull;
    	CityPoint[] p;
    	Pair pair;
        //System.setIn(new FileInputStream("src/BOJ10254.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
        	N = Integer.parseInt(br.readLine().trim());
        	p = new CityPoint[N]; p0 = new CityPoint(LIMIT, LIMIT);
        	
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		x = Integer.parseInt(st.nextToken());
        		y = Integer.parseInt(st.nextToken());
        		p[i] = new CityPoint(x, y);
        		
        		// 시작점 구하기
        		if (p0.x > x) {
        			p0.x = x; p0.y = y;
        		}
        		else if (p0.x == x && p0.y > y) {
        			p0.y = y;
        		}
        	}
        	// convex hull을 구하기 위해 정렬
        	Arrays.sort(p, new Comparator<CityPoint>() {
        		@Override
        		public int compare(CityPoint p1, CityPoint p2) {
            		int dir = ccw(p0, p1, p2);
        			if (dir != 0) return -1*dir;
        			else {
        				long d1 = distance(p0, p1);
        				long d2 = distance(p0, p2);
        				if (d1 < d2) return -1;
        				else if (d1 == d2) return 0;
        				else return 1;
        			}
        		}
        	});
        	
        	hull = convexhull(p);
        	pair = calipers(p, hull);
        	System.out.println(pair.p1.x+" "+pair.p1.y+" "+pair.p2.x+" "+pair.p2.y);
        }
	}
    static int[] convexhull(CityPoint[] p) {
    	int n = p.length, idx = 0;
    	int[] hull = new int[n];
    	
    	for (int i = 0; i < n; i++) {
    		while (idx >= 2 && ccw(p[hull[idx-2]], p[hull[idx-1]], p[i]) <= 0) {
    			idx--;
    		}
    		hull[idx++] = i;
    	}
    	
    	return Arrays.copyOf(hull, idx);
    }
    static Pair calipers(CityPoint[] p, int[] hull) {
    	int n = hull.length;
    	int idx1, idx2, ni, nj;
    	long max;
    	idx1 = idx2 = ni = nj = 0; max = 0;
    	
    	if (n < 3) return new Pair(p[hull[0]], p[hull[1]]);
    	for (int i = 0, j = 1; i < n; i++) {
    		ni = (i + 1)%n;
    		while (true) {
    			nj = (j + 1)%n;
    			if (ccw(p[hull[ni]].x - p[hull[i]].x, p[hull[ni]].y - p[hull[i]].y, p[hull[nj]].x - p[hull[j]].x, p[hull[nj]].y - p[hull[j]].y) > 0)
        			j = nj;
    			else break;
    		}
    		if (max < distance(p[hull[i]], p[hull[j]])) {
    			max = distance(p[hull[i]], p[hull[j]]);
    			idx1 = i; idx2 = j;
    		}
    	}	
    	return new Pair(p[hull[idx1]], p[hull[idx2]]);
    }
    static int ccw(CityPoint p1, CityPoint p2, CityPoint p3) {
    	long cal = ((long)p1.x*(long)p2.y + (long)p2.x*(long)p3.y + (long)p3.x*(long)p1.y) 
    				- ((long)p1.x*(long)p3.y + (long)p2.x*(long)p1.y + (long)p3.x*(long)p2.y);
    	if (cal < 0) return -1;
    	else if (cal == 0) return 0;
    	else return 1;
    }
    static int ccw(int x1, int y1, int x2, int y2) {
    	long cal = ((long)x1 * (long)y2) - ((long)x2 * (long)y1);
    	if (cal < 0) return -1;
    	else if (cal == 0) return 0;
    	else return 1;
    }
    static long distance(CityPoint p1, CityPoint p2) {
    	return ((long)p1.x - (long)p2.x)*((long)p1.x - (long)p2.x) + ((long)p1.y - (long)p2.y)*((long)p1.y - (long)p2.y);
    }
}

class CityPoint {
	int x, y;
	public CityPoint(int x, int y) {
		this.x = x; this.y = y;
	}
}
class Pair {
	CityPoint p1, p2;
	public Pair (CityPoint p1, CityPoint p2) {
		this.p1 = p1; this.p2 = p2;
	}
}