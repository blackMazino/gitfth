package exercise;

import java.awt.List;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Previous181110_말뚝 {
/*
'convex hull'로 볼록껍질을 구하고
'점의 위치'로 교차여부를 판단하여 안인지 밖인지 확인
2차원 평면에 말뚝이 N(3<=N<=1000)개 있다( 0<=X<=10억 0<=Y<=10억)
말뚝들의 가장 바깥자리를 연결해서 울타리를 만드는데
아차, 말뚝을 몇개 (1<=Q<=100)빼버렸다
우선 먼저 주어진 말뚝 정보로 울타리를 만들고, 빠트린 말뚝정보(좌표)가 주어졌을때
울타리를 만드는 규칙에 의해(볼록껍질) 다시 만들어야 되는지, 안그래도 되는지 확인해서
다시 만들어야 되면 1, 아니면 0을 리턴하여
그 합을 구하라
*/
	static int TC,N,Q, answer;
	static P[] arr;
	static P[] qArr;
	static LinkedList<Integer> stk;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			arr = new P [N];
			qArr = new P [N];
			for(int n=0;n<N;n++){
				st = new StringTokenizer(br.readLine());
				int x =  Integer.parseInt(st.nextToken());
				int y =  Integer.parseInt(st.nextToken());
				arr[n] = new P(x,y);
			}
			for(int q=0;q<Q;q++){
				st = new StringTokenizer(br.readLine());
				int x =  Integer.parseInt(st.nextToken());
				int y =  Integer.parseInt(st.nextToken());
				qArr[q] = new P(x,y);
			}
			
			convexHull();
			
		}
		
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
				if(v>0) return -1;
				if(v<0) return 1;				
				return (Math.abs(a.x)+a.y) - (Math.abs(b.x) + b.y) ;
			}
			
		});
		
		//use Stack
		stk = new LinkedList<>();
		stk.addLast(0);
		for(int i=1;i<N;i++){
			while( stk.size()>1
				&& ccw(arr[stk.get(stk.size()-2)],arr[stk.getLast()],arr[i]) <=0
				 ){
				stk.removeLast();
			}
			stk.addLast(i);
		}
	}
	private static int ccw(P a, P b, P c) {
	 return  ( a.x*b.y - b.x*a.y
			 + b.x*c.y - c.x*b.y
			 + c.x*a.y - a.x*c.y)
			 ;		
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
