package referenceBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ParametricSearch {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			String salunp = st.nextToken();
			String salunpAft = st.nextToken();
			
			System.out.println(Float.parseFloat(salunp));
			System.out.println(Float.parseFloat(salunpAft));
			
			if(Float.parseFloat(salunp) == Float.parseFloat(salunpAft)) {
				System.out.println(salunp+" and "+salunpAft +" is same.");

			}
			else System.out.println(salunp+" and "+salunpAft +" is not same.");
		}
	}

}
