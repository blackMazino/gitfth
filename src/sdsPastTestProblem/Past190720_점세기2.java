package sdsPastTestProblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Past190720_점세기2 {
//https://snacky.tistory.com/75	
	static int TC,N,z,dupCnt;//3<=N<=20000
	static long answer;
	static long zCnt;//원점 카운팅
	static Dot[] arr; // -100만<=x,y<=100만, 중복 있음
	static LinkedList<Integer> stk;//arr의 idx를 관리
	static LinkedList<Dot> convexHull;//점들을 관리
	public static void main(String[] args) throws Exception {		
		makeTestData();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("src/sdsPastTestProblem/Past190720.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new Dot[N];
			for(int n=0;n<N;n++) {
				st = new StringTokenizer(br.readLine());
				long x = Long.parseLong(st.nextToken());
				long y = Long.parseLong(st.nextToken());
				arr[n] = new Dot(x,y);
			}
			answer = 0;
			convexHull();
			
			System.out.println(z+" "+dupCnt+" "+stk.size());
			answer = stk.size()+z-1+dupCnt;
			System.out.println("#"+tc+" "+answer);
		}
		

	}
	private static void makeTestData() {
		System.out.println(ccw(new Dot(0,0), new Dot(2,0), new Dot(1,1)));
		System.out.println(ccw(new Dot(0,0), new Dot(2,0), new Dot(1,-1)));
//		int a=0;
//		int b=0;
//		for(int i=0;i<2000;i++) {
//			if(0<=i && i<=499) {
//				System.out.println(a+" "+b);
//				a+=1000;
//				b+=1000;
//			}else if(500<=i && i<=999) {
//				System.out.println(a+" "+b);
//				b+=1000;
//			}else if(1000<=i && i<=1749) {
//				System.out.println(a+" "+b);
//				a-=1000;
//				b-=1000;
//			}else {
//				System.out.println(a+" "+b);
//				a+=1000;
//				b-=1000;
//			}						
//		}
	}
	private static void convexHull() {
		//Presort		
		for(int i=1;i<N;i++) {
			if(arr[0].y>=arr[i].y) {
				if(arr[0].x > arr[i].x) {
					Dot tmp = arr[0];
					arr[0] = arr[i];
					arr[i] = tmp;
				}				
			}			
		}
		
		Arrays.sort(arr, 1,N, new Comparator<Dot>() {

			@Override
			public int compare(Dot a, Dot b) {
				int val = ccw(arr[0], a,b);
				if(val >0) {
					return -1;
				}else if(val<0) {
					return 1;
				}else {
					double disA = sqrDistance(a, arr[0]);
					double disB = sqrDistance(b, arr[0]);
					double dis  = disA - disB;
					if(dis>0) return 1;
					if(dis<0) return -1;
					return 0;
//					long dist = ((long)Math.abs(a.x) + (long)Math.abs(a.y)) - ((long)Math.abs(b.x) + (long)Math.abs(b.y));
//					if (dist > 0) return 1;
//					if (dist < 0) return -1;
//					return 0;
				}
				//return (Math.abs(a.x)+a.y) - (Math.abs(b.x) + b.y) ;//같은 우선순위면 가까운거 우선
			}

		});
		
//		printArray();
		stk  = new LinkedList<>();
		convexHull = new LinkedList<>();
		stk.add(0);
		convexHull.add(arr[0]);
		//원점의 중복이 몇개인지 확인
		z=0;
		while(true) {
			if(arr[z].x!=arr[0].x || arr[z].y!=arr[0].y) break;
			z++;
		}
		
		dupCnt = 0;
		for(int i=z;i<N;i++ ) {
			while(stk.size() > 1 && ( ccw ( arr[stk.size()-2], arr[stk.getLast()], arr[i]) <0) ) {
				//원점으로 회귀(직전의 선분)하는 건 정렬이 반대로 되어 있다
				if(ccw(arr[0],arr[stk.getLast()],arr[i])==0 
				&& sqrDistance(arr[0], arr[stk.getLast()]) <= sqrDistance(arr[0], arr[i]) ) {
					break;
				}
				stk.removeLast();
				convexHull.removeLast();
			}
			//중복인 점은 담지 않는다. 정렬이 되어 있다는 가정
			if(stk.size()>1 && ccw(arr[stk.size()-2], arr[stk.getLast()], arr[i])==0 
		    && arr[stk.getLast()].x == arr[i].x && arr[stk.getLast()].y == arr[i].y) {
				dupCnt++;
				continue;
			}
			stk.addLast(i);
			convexHull.addLast(arr[i]);
		}				
	}

	private static double sqrDistance(Dot a, Dot b) {		
		return (Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
	}
	private static void printArray() {
		for(int i=0;i<arr.length;i++) {
			System.out.println(i+" "+arr[i].x+ " "+arr[i].y);
		}
		
	}
	public static int ccw(Dot a, Dot b, Dot c) {
		long val = (long)(a.x*b.y) - (long)(b.x*a.y)
				 + (long)(b.x*c.y) - (long)(c.x*b.y)
				 + (long)(c.x*a.y) - (long)(a.x*c.y);
		if(val > 0) return 1;
		if(val < 0) return -1;
		return 0;
	}
	
}
class Dot{
 long x,y;

public Dot(long x, long y) {
	super();
	this.x = x;
	this.y = y;
}	
}