package sdsPreTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 사전201905_광선검 {

	static int TC, N,L,H;// 1<=N<=5000, L>H, 1<=H<=300000, 좌표 -20000~20000
	static ArrayList<Dron> list;
	static int [] destroyedOrder;
	static double RadtoDeg = 180/Math.PI;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201905.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		
		System.out.println(Math.atan2(1,Math.sqrt(3))*RadtoDeg);
		System.out.println(Math.atan2(Math.sqrt(3),1)*RadtoDeg);
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			destroyedOrder = new int[N+1];
			list = new ArrayList <>();
			Map map = new HashMap<>();
			
			for(int n=1;n<=N;n++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				double distance = getLength(x,y);
				double deg =  (Math.atan2(x, y) * RadtoDeg) + 270 ;
				deg = (deg>=360? deg-360:deg);	//deg%360;			
				if(H<=distance && distance<=L) {//경계에 위치해도 드론은 파괴된다고 가정한다
					list.add(new Dron(x,y,n,deg));	
				}else {
					destroyedOrder[n] = 0;	
				}
				
//				System.out.println(x+" "+y+" "+ deg+" "+ distance);
			}
			
			Collections.sort(list, new Comparator<Dron>() {

				@Override
				public int compare(Dron a, Dron b) {
					if(a.deg-b.deg > 0) return 1;
					if(a.deg-b.deg < 0) return -1;
					return 0;
				}
				
			});
			
			int order = 0;			
			for(int i=0;i<list.size();i++) {
				int n = list.get(i).no;
				int x = list.get(i).x;
				int y = list.get(i).y;
				double deg = list.get(i).deg;
//				System.out.println(n+" "+i+" "+x+","+y+" deg : "+deg );
				if(i==0) {
					order = i+1;
					destroyedOrder[n] = order;
				}else {
					if(list.get(i-1).deg == list.get(i).deg) {
						destroyedOrder[n] = order;
					}else {
						order = i+1;
						destroyedOrder[n] = order;
					}
				}
			}
//			System.out.println("=======");
		
			bw.write("#"+tc);
			for(int i=1;i<=N;i++) {
				bw.write(" "+destroyedOrder[i]);
			}
			bw.newLine();			
		}
		bw.flush();
		bw.close();		

	}
	private static double getLength(int x, int y) {		 
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

}
class Dron{

	public Dron(int x, int y, int no, double deg) {
		super();
		this.x = x;
		this.y = y;
		this.no= no;
		this.deg = deg;
	}
	int x,y, no;	
	double deg;
}
