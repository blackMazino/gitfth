package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//BIT(Binary Indexed Tree)
public class 자료구조_IDXTREE_KOITP_구간합 {
	
	static int N,a[],Q,tn ;
	static long tree[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for(tn =1;tn<N;tn=tn+2);
		a = new int[N+1];	
		tree = new long [212121];
		for(int i=1;i<=N;i++){
			a[i]=i;
			update(i,i);
		}
		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());
		for(int i=1;i<=Q;i++){
			st = new StringTokenizer(br.readLine());
			int gb = Integer.parseInt(st.nextToken());
			int x =  Integer.parseInt(st.nextToken());
			int y =  Integer.parseInt(st.nextToken());
			if(gb==0){
				//in case of gb is 0, change value
				update(x, -a[x]);
				update(x, y);
				a[x] = y;
			}else{
				System.out.println(search(x,y));
			}
			
		}
	}
	
    public static long search(int s, int e){
    	long res = 0;
    	s = s + tn -1;
    	e = e + tn -1;
    	while (s<=e){
    		if (s%2 == 1){//홀수 (분기의 왼쪽)
    			res += tree[s];
    			s++;
    		}
    		if(e%2 == 0){//짝수
    			res += tree[e];
    			e--;
    		}
    		s /=2;
    		e /=2;	    		
    	}
    	return res;
    }
    
	private static void update(int w, int g) {
		for(int i=tn+w-1;i>0; i = i/2){
			tree[i] += g;
		}		
	}
	
}
