package preTest;

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
			
			int[]crossProduct = getCrossProduct(a,b);
			int dotProduct = getDotProduct(a,b);
		}
		
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
		result[1] = (x[3]*y[1] - x[1]*y[3]);
		result[2] = (x[1]*y[2] - x[2]*y[1]);
		return result;
	}

}
