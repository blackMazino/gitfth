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
			a= new int [3];
			b= new int [3];
			c= new int [3];
			st = new StringTokenizer(br.readLine());
			a[0] = Integer.parseInt(st.nextToken());
			a[1] = Integer.parseInt(st.nextToken());
			a[2] = Integer.parseInt(st.nextToken());
			b[0] = Integer.parseInt(st.nextToken());
			b[1] = Integer.parseInt(st.nextToken());
			b[2] = Integer.parseInt(st.nextToken());
			c[0] = Integer.parseInt(st.nextToken());
			c[1] = Integer.parseInt(st.nextToken());
			c[2] = Integer.parseInt(st.nextToken());
			
			int[]crxProduct = getCrossProduct(a,b);
		}
		
	}
	
	/* a = a1,a2,a3
	 * b = b1,b2,b3
	 * a X b = a2*b3-a3*b2 , a3*b1 - a1*b3, a1*b2 - a2*b1
	 * 
	 * */
	private static int[] getCrossProduct(int[] x, int[] y) {
		int[] result = new int[3];
		result[0] = (x[1]*y[2] - x[2]*y[1]);
		result[1] = (x[2]*y[0] - x[0]*y[2]);
		result[2] = (x[0]*y[1] - x[1]*y[0]);
		return result;
	}

}
