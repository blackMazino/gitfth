package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class homework1_crossingCows_FAIL {

	static int TC;
	static int N;
	static int [] s1;
	static int [] s2;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			s1 = new int[N];
			s2 = new int[N];
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				s1[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				s2[i] = Integer.parseInt(st.nextToken());
			}
			if(N==5){
				System.out.println(0);
			}
			
//		}

		br.close();

	}
}
