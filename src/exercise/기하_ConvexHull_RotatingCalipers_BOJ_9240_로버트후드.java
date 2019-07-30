package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
/*
5
-4 1
-100 0
0 4
2 -3
2 300

316.86590223626143
 */
public class 기하_ConvexHull_RotatingCalipers_BOJ_9240_로버트후드 {
	static final int LIMIT = 1000;
	static Target P;
	static int C;// 2<=C<=100000
	static Target[] arr;
	static int [] stkArr;
	static long maxDistance;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		C = Integer.parseInt(br.readLine());
		arr = new Target[C];
		P = new Target(LIMIT, LIMIT);
		for(int i=0;i<C;i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Target(x,y);
			
			if(P.x>x){
				P.x = x;
				P.y = y; 
			}else if(P.x==x && P.y>y){
				P.y = y; 
			}			
		}
		
		sortArrows();
		stkArr = convexHull();
		maxDistance = 0;
		rotatingCalipers();
		System.out.println( Math.sqrt(maxDistance) );
		System.out.println( Math.round(Math.sqrt(maxDistance)*1000000)/1000000.0 );
		System.out.println( String.format("%,.6f", Math.sqrt(maxDistance)));
		
		System.out.println( Math.round(Math.sqrt(maxDistance)*1000)/1000.0 );//반올림 : 소수점아래 수가없으면 표기 안함 5000
		System.out.println( String.format("%,.3f", Math.sqrt(maxDistance)));//반올림 : 소수점아래 수가없어도 표기함 5000.000
	}
	private static void rotatingCalipers() {
		int ni,nj;
		ni = nj = 0;
		int n = stkArr.length;
		Target zero = new Target(0,0);
		for(int i=0, j=1;i<n;i++){
			ni = (i+1)%n;
			while(true){
				nj = (j+1)%n;
				if( ccw(zero, vSubtract(arr[stkArr[ni]], arr[stkArr[i]]), vSubtract(arr[stkArr[nj]], arr[stkArr[j]])) >0){
					j = nj;
				}else{
					break;
				}				
			}
			long dis = sqrDistance(arr[stkArr[i]], arr[stkArr[j]]);
			if(dis>maxDistance){
				maxDistance = dis;
			}
		}
		
	}
	private static Target vSubtract(Target a, Target b) {
		return (new Target(a.x-b.x, a.y-b.y));
	}
	private static int[] convexHull() {
		int[] tmp = new int[C];
		int idx = 0 ;
		for(int i=0;i<C;i++){
			while(idx>1 && ccw(arr[tmp[idx-2]] , arr[tmp[idx-1]] , arr[i]) <=0){
				idx--;
			}
			tmp[idx] = i; idx++;
		}
		return Arrays.copyOf(tmp, idx);
	}
	private static void sortArrows() {
		Arrays.sort(arr, new Comparator<Target>(){

			@Override
			public int compare(Target a, Target b) {
				int val = ccw(P,a,b);
				if(val > 0) return -1;
				else if(val <0) return 1;
				else{
					long dis1 = sqrDistance(P,a);
					long dis2 = sqrDistance(P,b);
					long distance = dis1-dis2;
					if(distance > 0) return 1;
					else if(distance <0) return -1;
					else return 0;
				}
			}
			
		});
		
	}
	public static long sqrDistance(Target a, Target b) {
		return ((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
	public static int ccw(Target a, Target b, Target c) {
		long val = (long)a.x*b.y - (long)b.x*a.y
				 + (long)b.x*c.y - (long)c.x*b.y
				 + (long)c.x*a.y - (long)a.x*c.y;
		if(val > 0) return 1;
		if(val < 0) return -1;
		return 0;
	}

}

class Target{
	public Target(long x, long y) {
		super();
		this.x = x;
		this.y = y;
	}

	long x,y;
}