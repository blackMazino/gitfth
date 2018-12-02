package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 자료구조_UNIONFIND_SdsPre08_연방 {
/*
(입력)
3
4 3
1 2
2 3
3 4
5
2 1 2
2 1 4
1 2
2 1 2
2 1 4
4 4
1 2
2 3
3 4
4 1
5
2 1 4
1 2
2 1 2
2 1 4
2 3 1
8 9
5 4
2 6
6 7
1 3
1 6
7 8
1 8
8 2
7 2
11
2 5 6
1 6
1 5
2 1 6
2 1 7
1 3
2 1 7
1 8
2 1 6
2 3 8
1 4
 

(출력)
#1 1110
#2 1111
#3 011101
 
(sample_input.txt에 대한 출력)
#1 111000110
#2 1000000001
#3 110011110
#4 111000101
#5 1000100000000000000110010101011100001000101000000100100010010001100000101101100111000010000001101000010110110010110000000000101000000101111110000000001000100110100000000001001000000000010000000000111110
#6 1111111111111111111111111111111111111000011010100111010001001000011111100101110000000010110001011000001011000110100011001010000010011110101000000100000010010111000100000011010100000000100100100101000100

 */
	static int TC,N,M,Q;//1<=N<=100,000, 1<=M<=100,000, 1<=Q<=200,000
	static int[] par;
	static ArrayList<Union> list;
	static ArrayList<int[]> qlist;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			par = new int[N+1];
			list = new ArrayList<>();
			list.add(new Union(0,0,true));//dummy
			qlist = new ArrayList<>();
			
			for(int i=1;i<=N;i++) par[i] = i;
			
			for(int m=1;m<=M;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
//				union(a,b);
				list.add(new Union(a,b,true));
			}
			
			Q = Integer.parseInt(br.readLine());
			for(int q=1;q<=Q;q++){
				st = new StringTokenizer(br.readLine());
				int gb = Integer.parseInt(st.nextToken());
				if(gb == 1){
					int k = Integer.parseInt(st.nextToken());
					list.get(k).isLinked = false;				
					qlist.add(new int[]{-1,k});
				}else{
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					qlist.add(new int[]{a,b});
				}
			}
			
			for(Union u:list){
				if(u.isLinked){
					union (u.a,u.b);
				}
			}

			bw.write("#");
			bw.write(String.valueOf(tc));
			bw.write(" ");

			StringBuffer sb = new StringBuffer();
			for(int i=qlist.size()-1;i>=0;i--){				
				if(qlist.get(i)[0]==-1){//자르는거면 다시 붙인다
					int k = qlist.get(i)[1];
					int a = list.get(k).a;
					int b = list.get(k).b;
					union(a,b);
				}else{
					int a= qlist.get(i)[0];
					int b= qlist.get(i)[1];
					if(find(a)==find(b)) sb.append("1");
					else sb.append("0");
				}				
			}
			sb.reverse();
			bw.write(sb.toString());
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		
	}
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa==pb) return;
		par[pb] = pa;
	}
	private static int find(int a) {
		if(par[a] == a) return a;
		return par[a] = find(par[a]);
	}

}
class Union{

	public Union(int a, int b, boolean isLinked) {
		super();
		this.a = a;
		this.b = b;
		this.isLinked = isLinked;
	}
	int a,b;
	boolean isLinked;
}