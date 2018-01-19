import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{
	static int T,W,H,iSx,iSy,iEx,iEy;
	static char[][] board;
	static int[][] iD;
	static boolean[][] bVisit;
	static int[][] iRoute={{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		T=Integer.parseInt(br.readLine());

		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			
			iD=new int[H+1][W+1];
			board=new char[H+1][W+1];
			bVisit=new boolean[H+1][W+1];
			
			for(int i=1;i<=H;i++) {
				String sStr=br.readLine();
				for(int j=1;j<=W;j++) {
					bVisit[i][j]=false;
					board[i][j]=sStr.charAt(j-1);
					iD[i][j]=Integer.MAX_VALUE;
					
					if (board[i][j]=='S') {
						iSx=i; iSy=j;
					} else if (board[i][j]=='E') {
						iEx=i; iEy=j;
					}
				}
			}
			
			/*
			for(int i=1;i<=H;i++) {
				for(int j=1;j<=W;j++) {
					System.out.print(board[i][j]+" ");
				}
				System.out.println();
			}
			*/
			
			Queue<int[]> que=new LinkedList<int[]>();
			
			que.add(new int[]{iSx,iSy});
			iD[iSx][iSy]=0;
			
			while(!que.isEmpty()) {
				int qx=que.peek()[0];
				int qy=que.peek()[1];
				
				que.poll();
				
				for(int i=0;i<4;i++) {
					int x=qx+iRoute[i][0]; int y=qy+iRoute[i][1];
					
					if (x<1||x>H||y<1||y>W) continue;
					if (bVisit[x][y]) continue;
					if (board[x][y]=='X') continue;

					bVisit[x][y]=true;
					iD[x][y]=iD[qx][qy]+1;
					que.add(new int[]{x,y});
				}
			}
			
			int ans=iD[iEx][iEy];
			if (ans==Integer.MAX_VALUE) ans=-1;
			System.out.println("#"+tc+" "+ans);
			
		}
	}
}