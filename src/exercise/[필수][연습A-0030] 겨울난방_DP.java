import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T,N,M,ans;
	static int[] iCost, iNeedCost, in, iPar, iChil;
	static ArrayList<Integer>[] con;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			iCost=new int[N+1];
			iNeedCost=new int[N+1];
			in=new int[N+1];
			iPar=new int[N+1];
			iChil=new int[N+1];
			con=new ArrayList[N+1];
			
			for(int i=1;i<=N;i++) con[i]=new ArrayList<>();
			
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) iCost[i]=Integer.parseInt(st.nextToken());
			
			for(int i=1;i<=M;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				
				con[a].add(b);
				in[b]++;
			}
			
			Queue<Integer> que=new LinkedList<>();
			
			for(int i=1;i<=N;i++) if (in[i]==0) que.add(i);
			
			while(!que.isEmpty()) {
				int q=que.poll();
				
				for(int t:con[q]) {
					iPar[t]=q;
					iChil[q]++;
					que.add(t);
				}
			}
			
			que.clear();
			for(int i=1;i<=N;i++) if (iChil[i]==0) que.add(i);
			
			ans=0;
			iChil[0]=1;

			while(!que.isEmpty()) {
				int q=que.poll();

				ans+=iNeedCost[q];
				iCost[q]=iCost[q]>iNeedCost[q]?iCost[q]-iNeedCost[q]:0;
				iCost[iPar[q]]=iCost[iPar[q]]>iNeedCost[q]?iCost[iPar[q]]-iNeedCost[q]:0;
				
				if (q==0) break;
				if (iNeedCost[iPar[q]]<iCost[q]) iNeedCost[iPar[q]]=iCost[q];
				iChil[iPar[q]]--;
				if (iChil[iPar[q]]==0) que.add(iPar[q]);
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}
}
