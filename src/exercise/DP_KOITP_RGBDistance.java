package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
https://koitp.org/problem/RGB_DISTANCE/read/
1<=N<=300000
1<=cost<=100000
*/
public class DP_KOITP_RGBDistance {

	static int N;
	static ArrayList<int[]> rgb;
	static long[][] d;
	static int RED = 0;
	static int GREEN = 1;
	static int BLUE = 2;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        N = Integer.parseInt(br.readLine());
        rgb = new ArrayList<>();
        rgb.add(new int[] {0,0,0});//0 idx
        for(int i=1;i<=N;i++){
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken());
        	int g = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	rgb.add(new int []{r,g,b});
        }
        d = new long [N+1][3];
        d[1][RED]  =rgb.get(1)[RED];
        d[1][GREEN]=rgb.get(1)[GREEN];
        d[1][BLUE] =rgb.get(1)[BLUE];
        
        for(int i=2;i<=N;i++){
        	for(int j=0;j<3;j++){//0,1,2
        		d[i][j] = getMin(i-1,j)+ rgb.get(i)[j];
        	}
        }
        System.out.println(Math.min(d[N][RED], Math.min(d[N][GREEN], d[N][BLUE])));
        
	}
	private static long getMin(int preI, int exclude) {
		long result = 0;
		if(exclude == RED){
			result = Math.min(d[preI][GREEN], d[preI][BLUE]);
		}
		if(exclude == GREEN){
			result = Math.min(d[preI][BLUE], d[preI][RED]);
		}
		if(exclude == BLUE){
			result = Math.min(d[preI][RED], d[preI][GREEN]);
		}
		return result;
	}

}

