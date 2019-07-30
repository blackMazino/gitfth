package sdsPastTestProblem;

import java.awt.List;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Past181110_말뚝 {
/*


0	0	0	0	0	0	F	0	F	0
0	0	0	0	1	0	0	0	0	0
0	F	0	1	0	0	0	0	0	F
0	0	q	0	0	0	1	0	0	0
0	1	1	0	0	0	0	0	0	0
0	0	0	0	0	0	q	0	0	0
F	1	0	1	0	0	0	0	F	0
0	0	0	0	0	1	0	0	0	0
0	1	0	0	0	0	F	0	q	0
q	F	0	F	0	0	0	0	0	0

1
18
2 1
4 1
2 2
7 2
6 3
1 4
2 4
4 4
9 4
2 6
3 6
7 7
2 8
4 8
10 8
5 9
7 10
9 10
4
1 1
9 2
7 5
3 7

2

sample input
2
3

*/
	static int TC,N,Q, answer;
	static P[] arr;
	static P[] qArr;
	static LinkedList<Integer> stk;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Past181110.txt"));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());			
			arr = new P [N];
			
			for(int n=0;n<N;n++){
				st = new StringTokenizer(br.readLine());
				int x =  Integer.parseInt(st.nextToken());
				int y =  Integer.parseInt(st.nextToken());
				arr[n] = new P(x,y);
			}
			Q = Integer.parseInt(br.readLine());
			qArr = new P [Q];
			for(int q=0;q<Q;q++){
				st = new StringTokenizer(br.readLine());
				int x =  Integer.parseInt(st.nextToken());
				int y =  Integer.parseInt(st.nextToken());
				qArr[q] = new P(x,y);
			}					
			convexHull();
//			printConvexHull();
			answer = 0;
			
			for(int q=0;q<qArr.length;q++){
				P t1 = new P(1000000001,qArr[q].y+1);
				int crossCnt =0;
				for(int i=0;i<stk.size();i++){
					//int j = i%stk.size()+1;//마지막점->처음점으로 잇기위해
					int j = i+1;
					if(i==stk.size()-1) j =0;
					int a = stk.get(i);
					int b = stk.get(j);
					if(isCross(qArr[q], t1, arr[a], arr[b] )){
						crossCnt++;		
					}	
				}
				if(crossCnt==0 || crossCnt%2==0){
					answer ++;
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
		
	}

	private static void printConvexHull() {
		for(int s : stk){
			System.out.println(arr[s].x+","+arr[s].y);
		}
		System.out.println("=======");
	}
	private static boolean isCross(P a,P b,P c,P d) {		
		//System.out.println("ccw : "+ccw(a,b,c)+","+ccw(a,b,d)+","+ccw(c,d,a)+","+ccw(c,d,b));
		return ( ccw(a,b,c)*ccw(a,b,d) <0
			   &&ccw(c,d,a)*ccw(c,d,b) <0
				);
	}
	private static void convexHull() {
		//Presort : y좌표가 가장작은것, 똑같다면 x좌표가 작은것
		for(int i=1;i<arr.length;i++){
			if(arr[0].y >= arr[i].y){
				if(arr[0].x > arr[i].x){
					P tmp = arr[0];
					arr[0] = arr[i];
					arr[i] = tmp;
				}
			}
		}
		
		//sort by ccw
		Arrays.sort(arr, 1,N, new Comparator<P>(){

			@Override
			public int compare(P a, P b) {
				int v = ccw(arr[0],a,b);
				//시계방향에 있는 점이 우선순위다
				if(v>0) return -1;
				if(v<0) return 1;				
				return (Math.abs(a.x)+a.y) - (Math.abs(b.x) + b.y) ;//같은 우선순위면 가까운거 우선
			}
			
		});
		
		//use Stack
		stk = new LinkedList<Integer>();// 점들의 순번(index)를 관리하는 스택
		stk.addLast(0);
		for(int i=1;i<N;i++){
			while( stk.size()>1
				&& ccw(arr[stk.get(stk.size()-2)],arr[stk.getLast()],arr[i]) <=0){
				stk.removeLast();
//				int r = stk.removeLast();
//				t = arr[r];
			}
			stk.addLast(i);
		}
	}
	
	/*
	vector cross product
	a(ax,ay,az) X b(bx,by,bz)
	(i,j,k는 단위벡터) 
	|i  j  k | 
	|ax ay az| = i*|ay az| - j*|ax az| + k*|ax ay| = (ay*bz-az*by) -(ax*bz-az*bx) +(ax*by-ay*bx)  
	|bx by bz|     |by bz|     |bx bz|     |bx by| 
	좌표계로 표현하면                                                                                 ( (ay*bz-az*by), -(ax*bz-az*bx), (ax*by-ay*bx)) 
	
	| ax ay 1 |
	| bx by 1 | = ax*|by 1| - ay*|bx 1| + 1*|bx by| = ax(by-cy) - ay(bx-cx) + (bx*cy-by*cx)	 
 	| cx cy 1 |      |cy 1|      |cx 1|     |cx cy|
 	정리하면
 	ax*by - bx*ay + bx*cy - cx*by + cx*ay - ax*cy
	*/	
	//10억 이니 int 사이즈를 초과할수있다
	private static int ccw(P a, P b, P c) {
//	 long val = (long)(b.x-a.x)*(c.y-a.y) - (long)(c.x-a.x)*(b.y-a.y);
	 long val = ((long)a.x*b.y - (long)b.x*a.y
	  		    +(long)b.x*c.y - (long)c.x*b.y
	  		    +(long)c.x*a.y - (long)a.x*c.y);
	 if(val > 0) return 1;
	 if(val < 0) return -1;		
	 return  0;		
	}

}
class P {
	public P(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}


