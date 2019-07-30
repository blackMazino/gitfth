package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 기하_직선방정식_정올_1129_평면내두선분의교점 {

	static int N;
	static int [][] line;
	static LinkedList<lineEquation> lines;
	public static void main(String[] arg) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		line = new int[N+1][7]; // 2<=N<=20, X1,Y1,X2,Y2,A,B,C
		lines = new LinkedList<>();
		for(int n=1;n<=N;n++){
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
	    	// A = y1 - y2
	    	// B = x2 - x1
	    	// C = x1y2 - x2y1
			int a = y1-y2;
			int b = x2-x1;
			int c = x1*y2-x2*y1;
			lines.add(new lineEquation(x1,y1,x2,y2,a,b,c));			
		}
	}
	
	
}
class lineEquation{
	public lineEquation(int x1, int y1, int x2, int y2, int a, int b, int c) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.a = a;
		this.b = b;
		this.c = c;
	}

	int x1,y1,x2,y2,a,b,c;
}