package sdsPreTest; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1712_4points {
	static int[]a,b,c;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T;tc++){
			a= new int [4];
			b= new int [4];
			c= new int [4];
			st = new StringTokenizer(br.readLine());
			a[1] = Integer.parseInt(st.nextToken());
			a[2] = Integer.parseInt(st.nextToken());
			a[3] = Integer.parseInt(st.nextToken());
			b[1] = Integer.parseInt(st.nextToken());
			b[2] = Integer.parseInt(st.nextToken());
			b[3] = Integer.parseInt(st.nextToken());
			c[1] = Integer.parseInt(st.nextToken());
			c[2] = Integer.parseInt(st.nextToken());
			c[3] = Integer.parseInt(st.nextToken());
			
			System.out.println("#"+tc+" "+getRelationIdxOfPoints());						
		}
		
	}
	
	private static int getRelationIdxOfPoints() {
		int result = 3;
		if(a[1] == 0 && a[2] == 0 && a[3] == 0 && b[1] == 0 && b[2] == 0 && b[3] == 0 && c[1] == 0 && c[2] == 0 && c[3] == 0 ){
			result = 0;			
		}else if(isOneLineInvolve()){//3C2 로 뽑은 두 벡터 x,y의 내적의 절대값이 |x|*|y|과 같은 경우
			result = 1;			
		}else if(isOnePlaneInvolve()){//세벡터의 삼중곱 a⋅(b×c)=b⋅(c×a)=c⋅(a×b) = 0인경우  
			result = 2;
		}else {
			//do nothing
		}	
		return result;
	}
	
	private static boolean isOnePlaneInvolve() {
		boolean result = false;
		if(getDotProduct(a, getCrossProduct(b,c)) == 0 ){
			result = true;
		}
		return result;
	}

	private static boolean isOneLineInvolve() {
		boolean result = false;
		System.out.println("===================================");
		System.out.println(Math.abs(getDotProduct(a,b)) + "|" + (int)(getVSize(a) * getVSize(b)));
		System.out.println(Math.abs(getDotProduct(b,c)) + "|" + (int)(getVSize(b) * getVSize(c)));
		System.out.println(Math.abs(getDotProduct(c,a)) + "|" + (int)(getVSize(c) * getVSize(a)));
		
		if(Math.pow(getDotProduct(a,b),2) == (getVSize(a) * getVSize(b))
		&& Math.pow(getDotProduct(b,c),2) == (getVSize(b) * getVSize(c))
		&& Math.pow(getDotProduct(c,a),2) == (getVSize(c) * getVSize(a))){
			result = true;
		}	
		return result;
	}

	private static double getVSize(int[] x) {		
		//return Math.sqrt(Math.pow(x[1], 2) + Math.pow(x[2], 2) + Math.pow(x[3], 2));
		return (Math.pow(x[1], 2) + Math.pow(x[2], 2) + Math.pow(x[3], 2));
	}

	/* a = a1,a2,a3
	 * b = b1,b2,b3
	 * a*b = a1*b1 + a2*b2 + a3*b3
	 * */	
	private static int getDotProduct(int[] x, int[] y) {
		return (x[1]*y[1] + x[2]*y[2] + x[3]*y[3]);
	}

	/* a = a1,a2,a3
	 * b = b1,b2,b3
	 * a X b = a2*b3-a3*b2 , a3*b1 - a1*b3, a1*b2 - a2*b1
	 * 
	 * */
	private static int[] getCrossProduct(int[] x, int[] y) {
		int[] result = new int[4];
		result[1] = (x[2]*y[3] - x[3]*y[2]);
		result[2] = (x[3]*y[1] - x[1]*y[3]);
		result[3] = (x[1]*y[2] - x[2]*y[1]);
		return result;
	}

}
