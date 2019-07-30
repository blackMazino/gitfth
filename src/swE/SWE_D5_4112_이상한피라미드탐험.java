package swE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWE_D5_4112_이상한피라미드탐험 {

	static int TC, answer, end, endF, lmt;
	static ArrayList<Integer> con[];	
	static int floor[], dis[], lIdx[], rIdx[];
	
	static ArrayList<ArrayList> lList, rList; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		
		con = new ArrayList[20001];
		for(int i=0;i<con.length;i++) {
			con[i] = new ArrayList<>();
		}
		
		floor = new int[20001];
		Arrays.fill(floor, Integer.MAX_VALUE);
		floor[0] = 0;
		int f = 1;
		int i = 1;
		while(i<=10000) {	
			if(i==1) {
				floor[i] = f;
//				con[i] = new ArrayList<>();
				con[i].add(i+1);
				con[i].add(i+2);
				i++;
				f++;
				continue;
			}
			for(int j=1;j<=f;j++) {				
//				con[i] = new ArrayList<>();
				floor[i] = f;
				if(j==1) {
					con[i].add(i+1);
				}else if(j==f) {
					con[i].add(i-1);
				}else {
					con[i].add(i+1);
					con[i].add(i-1);
				}
				con[i].add(i+f);
				con[i].add(i+f+1);				
				i++;
			}		
			f++;
		}
		lmt = floor[10000]+1;
		
		
		//1-2-4-7...
		lList = new ArrayList<>();
		lIdx = new int[20000];
		int no = 1;
		int gap = 1;
		int index = 0;
		while(floor[no] < lmt) {
			AddList(no, gap, index, lList, lIdx);
			gap++;
			no += gap;
			index++;
		}
		
		//1-3-6-10...
		rList = new ArrayList<>();
		rIdx = new int[20000];
		no = 1;
		gap = 2;		
		index = 0;
		while(floor[no] < lmt) {
			AddList(no, gap, index, rList, rIdx);
			no += gap-1;
			gap++;
			index++;
		}
		
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			if(a==b) {
				System.out.println("#"+tc+" "+0);
				continue;
			}
			
			/*
			 bfs
			dis = new int[20001];
			Arrays.fill(dis, Integer.MAX_VALUE);
			answer = Integer.MAX_VALUE;
			LinkedList<Piramid> list = new LinkedList<>();
			if(a<b) {
				end = b;
				endF = floor[end];
//				dfs(a,1);
				list.add(new Piramid(a,1));
			}else {
				end = a;
				endF = floor[end];
//				dfs(b,1);
				list.add(new Piramid(b,1));
			}
			
			while(!list.isEmpty()) {
				Piramid p = list.removeLast();
//				System.out.println(p.v+" , "+p.dis);
				if(p.v==end) {
					answer = Math.min(answer, p.dis);
//					System.out.println(answer);
				}else if(endF < floor[p.v]){
					 
				}else {
					for(int j=0;j<con[p.v].size();j++) {
						int t = con[p.v].get(j);
						if(floor[t]>floor[end]){
							continue;
						}
						if(dis[t] > p.dis) {
							dis[t] = p.dis;
							list.addLast(new Piramid(t, p.dis+1));	
						}
											
					}
				}

			}
			*/
			
			if(a<b) {
				search(a,b);			
			}else {
				search(b,a);
			}						
			System.out.println("#"+tc+" "+answer);
			
		}

	}
	private static void search(int s, int e) {
		int lVal = 0;
		ArrayList<Integer> lLine = lList.get(lIdx[s]);
		for(int i=0;i<lLine.size();i++) {
			if( floor[lLine.get(i)] == floor[e]) {
				lVal = lLine.get(i);
				break;
			}
		}
		
		int rVal = 0;
		ArrayList<Integer> rLine = rList.get(rIdx[s]);
		for(int i=0;i<rLine.size();i++) {
			if( floor[rLine.get(i)] == floor[e]) {
				rVal = rLine.get(i);
				break;
			}
		}		
		if(e<lVal){
			answer = floor[e]-floor[s] + (lVal - e);
		}else if(e>rVal){
			answer = floor[e]-floor[s] + (e-rVal);
		}else{
			answer = floor[e]-floor[s];
		}				
	}
	
	private static void AddList(int no, int gap, int i, ArrayList<ArrayList> list, int[] idx) {
		ArrayList<Integer> line = new ArrayList<>();
		int thisF = floor[no];
		idx[no] = i;
		while(thisF < lmt) {
//			System.out.println(no+", "+ floor[no]);
			thisF = floor[no];
			line.add(no);
			no +=gap;
			gap ++;	
			idx[no] = i;
		}		
		list.add(line);
		
	}
	private static void dfs(int a, int d) {
		
		for(int i=0;i<con[a].size();i++) {
			
			int t = con[a].get(i);
			if(endF < floor[t]) {
				return;
			}
			if(t == end) {
				answer = Math.min(answer, d);
				return;
			}
			dfs(t,d+1);
		}
		
	}

}
class Piramid{
	public Piramid(int v, int dis) {
		super();
		this.v = v;
		this.dis = dis;
	}
	int v;
	int dis;
}


