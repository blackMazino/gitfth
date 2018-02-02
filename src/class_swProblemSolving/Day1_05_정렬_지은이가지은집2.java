package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//강사풀이
public class Day1_05_정렬_지은이가지은집2 {

	static int TC;
	static int X,N;
	
	static int a [];
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			a = new int [N];
			for(int i=0; i<N;i++){
				st = new StringTokenizer(br.readLine());				
				a[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(a);
			X=X*10000000;
			int s,e;
			
			int max = -1; 
			int maxw = -1;
			for(int i=0;i<N;i++){
				s = i+1; e=N-1;
				int res = -1;
				int F = X - a[i];
				while(s<=e){
					int m = (s+e)/2;
					if(F==a[m]){
						res = m;
						break;						
					}
					if(F<a[m]){
						e = m-1;						
					}else{
						s = m+1;
					}
				}
				if(res == -1){
					continue;
				}
				if(max < a[res] - a[i]){
					max = a[res] - a[i];
					maxw = i;
				}						
			}
			if(max == -1){
				System.out.println("danger");					
			}else{
				System.out.println("yes "+a[maxw]+" "+(X-a[maxw]));
			}				
//		}

		br.close();

	}

}