package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Previous180707_주식 {
	static int N;//1<=N<=300,000
	static int preV,nowV,nextV, cnt;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous180707.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++){
			st = new StringTokenizer(br.readLine());
			N =  Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			preV=nowV=nextV=cnt=0;
			for(int i = 1;i<=N;i++){
				nextV = Integer.parseInt(st.nextToken());
				if(nextV>nowV){
					cnt++;
					preV = nowV;
					nowV = nextV;
				}else if(nextV == nowV){
					continue;
				}else {// in case of nextV<nowV
					if(preV > nowV){//연속감소
						nowV = nextV;
					}else{
						cnt++;
						preV = nowV;
						nowV = nextV;
					}
				}					
			}
			bw.write("#"+tc+" "+cnt+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
