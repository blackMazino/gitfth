package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 자료구조_UNIONFIND_동맹의동맹은동맹 {

	static int N,Q;
	static int par[];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		par = new int[N+1];
		for(int i=1;i<=N;i++) par[i]=i;
		
		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());
		for(int i=1;i<=Q;i++){
			st = new StringTokenizer(br.readLine());
			int gb = Integer.parseInt(st.nextToken());
			int a  = Integer.parseInt(st.nextToken());
			int b  = Integer.parseInt(st.nextToken());
			if(gb==0){
				setAlliance(a,b);
			}else{
				if(isAlliance(a,b)){
					bw.write(1+"\n");
				}else{
					bw.write(0+"\n");
				}
			}
		}
		bw.flush();
		bw.close();
		br.close();
	
		
	}
	private static boolean isAlliance(int a, int b) {
		if(find(a)==find(b)) return true;
		else return false;
	}
	private static void setAlliance(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa==pb) return;
		par[pb] = pa;
		
	}
	private static int find(int a) {
		if(par[a]==a) return a;
		else return par[a] = find(par[a]);		
	}

}
