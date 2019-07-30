package exercise;

import java.util.*;
import java.io.*;
 
public class 기하_ConvexHull_RotatingCalipers_BOJ_10254_고속도로 {  // BOJ 10254 고속도로
	
	static final int LIMIT = 10000000;
	static CityXY p0, farthestPoint1,farthestPoint2 ;//p0 : 시작점, p1,p2 : farthest pair
	static CityXY [] arr;
	static int TC,N; //(2 ≤ n ≤ 200,000)
	static long maxDistance;
//	static LinkedList<Integer> stk;
//	static LinkedList<XY> convexHull;
	static int [] stkArr;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine().trim());
        for(int tc=1;tc<=TC;tc++) {
        	N = Integer.parseInt(br.readLine().trim());	
        	arr = new CityXY[N]; 
        	p0 = new CityXY(LIMIT, LIMIT);
        	farthestPoint1 = new CityXY(LIMIT, LIMIT);
        	farthestPoint2 = new CityXY(LIMIT, LIMIT);
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		arr[i] = new CityXY(x, y);
        		
        		// 시작점 구하기
        		if (p0.x > x) {
        			p0.x = x; p0.y = y;
        		}
        		else if (p0.x == x && p0.y > y) {
        			p0.y = y;
        		}
//        		if(p0.y>=y) {
//        			if(p0.x>x) {
//        				p0.x = x;
//        				p0.y = y; 
//        			}
//        		}
        	}
    		sortPoint();
//    		stk  = new LinkedList<>();
//    		convexHull = new LinkedList<>();    		
//    		convexHull();
    		stkArr = convexHull_arr();
//    		System.out.println(stkArr.length);
    		maxDistance = 0;
    		rotatingCalipers();
    		System.out.println(farthestPoint1.x+" "+farthestPoint1.y+" "+farthestPoint2.x+" "+farthestPoint2.y);
    		System.out.println(Math.sqrt(maxDistance));
        }
		
	}

	private static void sortPoint() {
    	Arrays.sort(arr, new Comparator<CityXY>() {
    		@Override
    		public int compare(CityXY a, CityXY b) {
        		int val = ccw(p0, a, b);
    			if (val != 0) return -1*val;
    			else {
    				long d1 = sqrDistance(p0, a);
    				long d2 = sqrDistance(p0, b);
    				long dis = d1-d2;
    				if (dis>0) return 1;
    				else if(dis<0) return -1;
    				else return 0;
    			}
    		}
    	});   		
//        for(int i=0;i<arr.length;i++) {
//            System.out.println(i+" "+arr[i].x+ " "+arr[i].y);
//        }
          
	}
	
	
//	private static void convexHull() {
//		stk.add(0);
//		convexHull.add(arr[0]);
//		//원점의 중복이 몇개인지 확인
////		z=0;
////		while(true) {
//////			if(arr[z]!=arr[0]) break;
////			if(arr[z].x!=arr[0].x || arr[z].y!=arr[0].y) break;
////			z++;
////		}
//		
////		dupCnt = 0;
//		for(int i=1;i<N;i++ ) {
//			while(stk.size() > 1 && ( ccw ( arr[stk.size()-2], arr[stk.getLast()], arr[i]) <=0) ) {
//				stk.removeLast();
////				convexHull.removeLast();
//			}
////			if(stk.size()>1 && ccw(arr[stk.size()-2], arr[stk.getLast()], arr[i])==0 
////		    && arr[stk.getLast()].x == arr[i].x && arr[stk.getLast()].y == arr[i].y) {
////				dupCnt++;
////				continue;
////			}
//			stk.addLast(i);
////			convexHull.addLast(arr[i]);
//		}	
//		
//	}
	
	private static int[] convexHull_arr() {
		int idx = 0;
		int [] tmp = new int [N];
		for(int i=0; i<N;i++){
			while(idx>1 && ccw(arr[tmp[idx-2]] , arr[tmp[idx-1]] , arr[i]) <=0){
				idx--;
			}
			tmp[idx++] = i;
		}
//		System.out.println(idx);
		return Arrays.copyOf(tmp, idx);
	}


	private static void rotatingCalipers() {
	  	int n = stkArr.length;
    	int idx1, idx2, ni, nj;
    	long max;
    	idx1 = idx2 = ni = nj = 0; max = 0;
    	
    	if (n < 3) {
    		farthestPoint1 = arr[stkArr[0]];
    		farthestPoint2 = arr[stkArr[1]];
    		maxDistance = sqrDistance(farthestPoint1,farthestPoint2);
    		return;
    	}
    	CityXY zero = new CityXY(0,0);
    	for (int i = 0, j = 1; i < n; i++) {
    		ni = (i + 1)%n;
    		while (true) {
    			nj = (j + 1)%n;
    			if(ccw(zero
    				 , vectorMinus(arr[stkArr[ni]], arr[stkArr[i]])
    				 , vectorMinus(arr[stkArr[nj]], arr[stkArr[j]])) >0 ){
    				j = nj;
    			}else {
    				break;
    			}
    		}
    		long thisDis = sqrDistance(arr[stkArr[i]],  arr[stkArr[j]]);
    		if (max < thisDis){
    			max = thisDis;
    			idx1 = i; idx2 = j;
    		}
    	}	
    	farthestPoint1 = arr[stkArr[idx1]];
    	farthestPoint2 = arr[stkArr[idx2]];
    	maxDistance = max;
		
	}
	public static int ccw(CityXY a, CityXY b, CityXY c) {
		long val = (long)a.x*b.y - (long)b.x*a.y
				 + (long)b.x*c.y - (long)c.x*b.y
				 + (long)c.x*a.y - (long)a.x*c.y;
		if(val > 0) return 1;
		if(val < 0) return -1;
		return 0;	
	}

    public static CityXY vectorMinus(CityXY a, CityXY b) {
        return (new CityXY(a.x-b.x, a.y-b.y));
    }
	protected static long sqrDistance(CityXY a, CityXY b) {
		return ((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}

	
}

class CityXY{
	public CityXY(long x, long y) {
		super();
		this.x = x;
		this.y = y;
	}

	long x,y;
}

/*
package exercise;

import java.util.*;
import java.io.*;
 
public class 기하_ConvexHull_RotaitingCalipers_BOJ_10254_고속도로 {  // BOJ 10254 고속도로
	static final int LIMIT = 10000000;
	static CityXY p0;
	static long maxDis;
    public static void main(String[] args) throws Exception {
    	int T, N, x, y;
    	int[] hull;
    	CityXY[] p;
    	Pair pair;
        //System.setIn(new FileInputStream("src/BOJ10254.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
        	N = Integer.parseInt(br.readLine().trim());
        	p = new CityXY[N]; p0 = new CityXY(LIMIT, LIMIT);
        	
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		x = Integer.parseInt(st.nextToken());
        		y = Integer.parseInt(st.nextToken());
        		p[i] = new CityXY(x, y);
        		
        		// 시작점 구하기
        		if (p0.x > x) {
        			p0.x = x; p0.y = y;
        		}
        		else if (p0.x == x && p0.y > y) {
        			p0.y = y;
        		}
        	}
        	// convex hull을 구하기 위해 정렬
        	Arrays.sort(p, new Comparator<CityXY>() {
        		@Override
        		public int compare(CityXY p1, CityXY p2) {
            		int dir = ccw(p0, p1, p2);
        			if (dir != 0) return -1*dir;
        			else {
//        				long d1 = distance(p0, p1);
//        				long d2 = distance(p0, p2);
//        				if (d1 < d2) return -1;
//        				else if (d1 == d2) return 0;
//        				else return 1;
        				long d1 = distance(p0, p1);
        				long d2 = distance(p0, p2);
        				long dis = d1-d2;
        				if (dis>0) return 1;
        				else if(dis<0) return -1;
        				else return 0;
        			}
        		}
        	});
        	
	        for(int i=0;i<p.length;i++) {
	          System.out.println(i+" "+p[i].x+ " "+p[i].y);
	        }
        	hull = convexhull(p);
        	maxDis = 0;
        	pair = calipers(p, hull);
        	System.out.println(pair.p1.x+" "+pair.p1.y+" "+pair.p2.x+" "+pair.p2.y);
//        	System.out.println(maxDis);
        }
	}
    static int[] convexhull(CityXY[] p) {
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
    static Pair calipers(CityXY[] p, int[] hull) {
    	int n = hull.length;
    	int idx1, idx2, ni, nj;
    	long max;
    	idx1 = idx2 = ni = nj = 0; max = 0;
    	
    	if (n < 3) {
    		max = distance(p[hull[0]], p[hull[1]]);
    		return new Pair(p[hull[0]], p[hull[1]]);
    	}
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
    	
    	maxDis = max;
    	return new Pair(p[hull[idx1]], p[hull[idx2]]);
    }
    static int ccw(CityXY p1, CityXY p2, CityXY p3) {
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
    static long distance(CityXY p1, CityXY p2) {
    	return ((long)p1.x - (long)p2.x)*((long)p1.x - (long)p2.x) + ((long)p1.y - (long)p2.y)*((long)p1.y - (long)p2.y);
    }
}

class CityXY {
	int x, y;
	public CityXY(int x, int y) {
		this.x = x; this.y = y;
	}
}
class Pair {
	CityXY p1, p2;
	public Pair (CityXY p1, CityXY p2) {
		this.p1 = p1; this.p2 = p2;
	}
}

*/