package swE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWksRe4KARQDFAVE
public class SWE_D4_7194_화섭이의미생물배양 {

	static int TC, s,t,a,b, answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			
			if(b==1){
				int gap = t-s;
				if(gap%a==0) {
					answer = gap/a;
				}
				else {
					answer =-1;
				}				
				System.out.println("#"+tc+" "+answer);
				continue;
			}
			
			answer = 0;
			while(s!=t){
				if(t%b==0){
					if(s>t/b){
						t -= a;
					}else{
						t /= b;
					}
					answer ++;
				}else{
					t -= a;
					answer ++;					
				}
				
				if(s>t){
					answer = -1;
					break;
				}
			}
			System.out.println("#"+tc+" "+answer);	
		}
	}

}
