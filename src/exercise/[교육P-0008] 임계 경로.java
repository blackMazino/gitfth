import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution{
	static int T,N,M;
	static String[] s;
	static ArrayList<Integer>[] con,from,fromv;
	static ArrayList<Integer> order;
	static int[] in,D;
	
	public static void main(String arg[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			s=br.readLine().split(" ");
			
			N=Integer.parseInt(s[0]);
			M=Integer.parseInt(s[1]);
			
			in=new int[N+1];
			D=new int[N+1];
			
			con=new ArrayList[N+1];
			from=new ArrayList[N+1];
			fromv=new ArrayList[N+1];
			
			for(int i=1;i<=N;i++) {
				con[i]=new ArrayList<>();
				from[i]=new ArrayList<>();
				fromv[i]=new ArrayList<>();
			}
			
			for(int i=0;i<M;i++) {
				s=br.readLine().split(" ");
				int a=Integer.parseInt(s[0]);
				int b=Integer.parseInt(s[1]);
				int c=Integer.parseInt(s[2]);
				
				con[a].add(b);
				in[b]++;
				from[b].add(a);
				fromv[b].add(c);
			}
			
			for(int i=1;i<=N;i++) {
				if (i==1||in[i]>0) D[i]=0;
				else D[i]=(int)2e9;
			}
			
			/* 위상정렬 */
			
			order=new ArrayList<>();
			Queue<Integer> que=new LinkedList<>();
			
			for(int i=1;i<=N;i++) {
				if (in[i]==0) que.add(i);
			}
			
			while (!que.isEmpty()) {
				int q=que.poll();
				
				order.add(q);
				for(int t: con[q]) {
					if (--in[t]==0) que.add(t);
				}
			}
			
			for(int n: order) {
				for(int i=0;i<from[n].size();i++) {
					int t=from[n].get(i);
					int v=fromv[n].get(i);
					
					D[n]=Math.max(D[n], D[t]+v);
				}
				D[n]=Math.min(D[n],(int)2e9);
			}
			System.out.println("#"+tc+" "+D[N]);
		}
	}
}

/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 임계경로_1039{
	static int T,N,M;
	static String[] s;
	static ArrayList<Integer>[] con,from,fromv;
	static ArrayList<Integer> order;
	static int[] in;
	static int[] D;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T=Integer.parseInt(br.readLine());

		for(int tc=1;tc<=T;tc++) {
			s=br.readLine().split(" ");
			N=Integer.parseInt(s[0]);
			M=Integer.parseInt(s[1]);
			
			con=new ArrayList[N+1];
			from=new ArrayList[N+1];
			fromv=new ArrayList[N+1];
			
			in=new int[N+1];
			D=new int[N+1];
			
			for(int i=1;i<=N;i++) {
				con[i]=new ArrayList<>();
				from[i]=new ArrayList<>();
				fromv[i]=new ArrayList<>();
			}

			for(int i=0;i<M;i++) {
				s=br.readLine().split(" ");

				int a=Integer.parseInt(s[0]);
				int b=Integer.parseInt(s[1]);
				int c=Integer.parseInt(s[2]);
//System.out.println("a=["+a+"], b=["+b+"], c=["+c+"]");		
				
				con[a].add(b);
				in[b]++;
				from[b].add(a);
				fromv[b].add(c);
			}
			
			for(int i=1;i<=N;i++) {
				if(i==1||in[i]>0) D[i]=0;
				else D[i]=(int)2e9;
			}


//System.out.print("in[] 배열값 : ");				
//			for(int i=1;i<=N;i++) {
//				System.out.print(in[i]+" ");				
//			}
//System.out.println("");				
//
//System.out.print("D[] 배열값 : ");				
//			for(int i=1;i<=N;i++) {
//				System.out.print(D[i]+" ");				
//			}
//System.out.println("");				
			
            /* 위상정렬 시작 *//*
			order=new ArrayList<>();
			
			Queue<Integer> que=new LinkedList<Integer>();
			
			int iCnt;
			iCnt=0;
			
			for(int i=1;i<=N;i++) {
				if(in[i]==0) {
					que.add(i);
				}
			}
					
			while(!que.isEmpty()) {
				int q=que.poll();
//System.out.println("q=["+q+"]");				
				order.add(q);
//System.out.println("1 order="+order);				
				for(int t: con[q]) {
//System.out.println("t=["+t+"],in["+t+"]=["+in[t]+"],"+con[q]);				
					if (--in[t]==0) que.add(t);
//System.out.println("1 que="+que);				
				}
			}

			
System.out.println("order="+order);

			for(int n:order) {
System.out.println("n=["+n+"], from["+n+"].size()=["+from[n].size()+"]");		

				for(int i=0;i<from[n].size();i++) {
					int t=from[n].get(i), v=fromv[n].get(i);
					
System.out.println("t=["+t+"], v=["+v+"], D["+n+"]=["+D[n]+"], D["+t+"]+"+v+"=["+D[t]+v+"]");				
					D[n]=Math.max(D[n], D[t]+v);
				}
				D[n]=Math.min(D[n],(int)2e9);
			}
			
			System.out.println("#"+tc+" "+D[N]);
		}
	}
}
*/

/*

2
7 9
1 2 4
1 3 2
1 4 3
2 6 3
2 7 5
3 5 1
4 6 4
5 6 2
6 7 5
7 10
6 4 5
1 6 7
4 5 5
5 7 5
5 7 3
1 4 7
1 3 2
6 4 5
5 7 6
1 2 2




(출력)
#1 12
#2 23


*/