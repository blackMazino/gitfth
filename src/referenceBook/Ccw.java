package referenceBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ccw {

	static int ax,ay,bx,by,cx,cy;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));				
		StringTokenizer st ;
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T;tc++){
			st = new StringTokenizer(br.readLine());
			
			ax=Integer.parseInt(st.nextToken());
			ay=Integer.parseInt(st.nextToken());
			bx=Integer.parseInt(st.nextToken());
			by=Integer.parseInt(st.nextToken());
			cx=Integer.parseInt(st.nextToken());
			cy=Integer.parseInt(st.nextToken());

			int ccw = getCcwResult();
		}
		

	}
	private static int getCcwResult() {		
		//7return (x1*y2 + x2*y3 + x3*y1) - (y1*x2+y2*x3+y3*x1);
		return (ax*by + bx*cy + cx*ay) - (ay*bx + by*cx +cy*ax);
		// a + b + c - ( a + b + c)
		// ax + bx + cx - (ay + by + cy)
		// ax*b + bx*c + cx*a - (ay*b + by*c + cy*a)
		// ax*by + bx*cy + cx*ay - (ay*bx + by*cx + cy*ax)
	}

}
