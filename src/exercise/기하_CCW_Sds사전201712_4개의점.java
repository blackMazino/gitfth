package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기하_CCW_Sds사전201712_4개의점 {
/*
[입출력 예]													
(입력)													
7				
0 0 0 0 0 0 0 0 0				
3 9 -6 -1 -3 2 8 24 -16				
3 3 3 1 3 5 5 7 9				
1 1 1 -2 -2 -2 1 1 1				
1 2 3 4 5 6 7 8 9				
1 2 3 -4 -5 -6 0 0 0				
1 2 1 1 2 1 2 1 2
				
(출력)				
#1 0				
#2 1				
#3 2				
#4 1				
#5 2				
#6 2				
#7 2					

(sample_input.txt 의 출력)
#1 1 
#2 0
#3 1
#4 3
#5 3
#6 2
#7 2
#8 3
#9 1
#10 1
#11 2
#12 3
#13 1
#14 1
#15 3
#16 2
#17 2
#18 1
#19 1
#20 2
#21 3
#22 2
#23 3
#24 2
#25 1
#26 2
#27 2
#28 1
#29 1
#30 2
#31 2
#32 1
#33 1
#34 1
#35 2
#36 1
#37 1
#38 3
#39 3
#40 2
#41 2
#42 1
#43 2
#44 3
#45 2
#46 3
#47 2
#48 1
#49 1
#50 3



 */
		static Point3D a,b,c;
		public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader br = new BufferedReader(new FileReader("src/exercise/사전201712.txt"));
			StringTokenizer st;
			int TC = Integer.parseInt(br.readLine());
			for(int tc=1; tc<=TC;tc++){
				st = new StringTokenizer(br.readLine());
				int ax = Integer.parseInt(st.nextToken());
				int ay = Integer.parseInt(st.nextToken());
				int az = Integer.parseInt(st.nextToken());
				int bx = Integer.parseInt(st.nextToken());
				int by = Integer.parseInt(st.nextToken());
				int bz = Integer.parseInt(st.nextToken());
				int cx = Integer.parseInt(st.nextToken());
				int cy = Integer.parseInt(st.nextToken());
				int cz = Integer.parseInt(st.nextToken());
				a = new Point3D(ax,ay,az);
				b = new Point3D(bx,by,bz);
				c = new Point3D(cx,cy,cz);								
				System.out.println("#"+tc+" "+getRelationIdxOf3Point());						
			}
			
		}
		
		private static int getRelationIdxOf3Point() {
			if(a.x == 0 && a.y==0 && a.z==0
			&& b.x == 0 && b.y==0 && b.z==0
			&& c.x == 0 && c.y==0 && c.z==0){
				return 0;
			}else if(isOneLine()){
				return 1;
			}else if(isOnePlane()){
				return 2;
			}else{
				return 3;	
			}
			
		}

		private static boolean isOneLine() {
			boolean result = false;
			if(Math.pow(getDotProduct(a,b),2) == (getV2Size(a) * getV2Size(b))
			&& Math.pow(getDotProduct(b,c),2) == (getV2Size(b) * getV2Size(c))
			&& Math.pow(getDotProduct(c,a),2) == (getV2Size(c) * getV2Size(a))){
				result = true;
			}	
			return result;
		}

		private static boolean isOnePlane() {
			boolean result = false;
			if(getDotProduct(a, getCrossProduct(b,c)) == 0 ){
				result = true;
			}
			return result;
		}
		

		private static double getV2Size(Point3D n) {		
			return (Math.pow(n.x, 2) + Math.pow(n.y, 2) + Math.pow(n.z, 2));
		}

		/* dot Prodoct or Inner Product(내적)
		 * a = ax,ay,az
		 * b = bx,by,bz
		 * a*b = ax*bx + ay*by + az*bz
		 * a*b cos
		 * */	
		private static int getDotProduct(Point3D n, Point3D m) {
			return (n.x*m.x + n.y*m.y + n.z*m.z);
		}

		/* cross Product or Outer Product(외적)
		 * a = ax,ay,az
		 * b = bx,by,bz
		 * a X b = ay*bz-az*by , az*bx - az*bz, az*by - ay*bx
		 * a*b sin
		 * */
		private static Point3D getCrossProduct(Point3D n, Point3D m) {
			int x = n.y*m.z - n.z*m.y;
			int y = -(n.x*m.z - n.z*m.x);
			int z = n.x*m.y - n.y*m.x;
			return (new Point3D(x,y,z));
		}

}
class Point3D{
	public Point3D(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	int x,y,z;
}
