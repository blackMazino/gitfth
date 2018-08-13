package exercise;
/*
길이 N의 수열이 주어진다. 이 수열의 초기값은 1, 2, 3, ..., N이다. 그런데 이 수열의 변경이 빈번히 일어나고, 그런 도중에 어떤 연속된 부분의 합을 구하려 한다.
만약 N이 5인 경우를 생각하자. 초기에는 1, 2, 3, 4, 5 가 된다. 
이 상황에서 3번째 수를 9로 변경하고 4번째 수를 10으로 변경하면 1, 2, 9, 10, 5가 된다. 
이 때, 2번째부터 5번째까지 합을 구하라고 한다면 26을 출력하면 되는 것이다. 
또, 이 상태에서 1번째 수를 -5로 변경하고, 3번째 수를 5로 변경하면 -5, 2, 5, 10, 5가 된다. 
그 다음, 1번째부터 3번째까지 합을 구하라고 한다면 2가 된다.
이러한 질의를 해결하는 프로그램을 작성하시오.
첫 번째 줄에 정수의 개수 N이 주어진다. (1 ≤ N ≤ 100,000)
두 번째 줄에 질의의 개수 Q가 주어진다. (1 ≤ Q ≤ 200,000)
세 번째 줄부터 Q개의 줄에 걸쳐 질의의 정보가 주어진다. 각 질의는 다음 형태로 이루어진다.
0 x y : x번째 수를 y로 변경한다. (1 ≤ x ≤ N, -100,000 ≤ y ≤ 100,000)
1 x y : x번째 수부터 y번째 수까지의 합을 구한다. (1 ≤ x ≤ y ≤ N)
질의 중 1로 시작하는 질의에 대해, 구한 합을 한 줄에 하나씩 출력한다. 이 때, 답의 범위가 32-bit 정수형의 범위를 초과할 수 있음에 유의하여라.
===============
5
7
1 2 4
0 3 9
0 4 10
1 2 5
0 1 -5
0 3 5
1 1 3
===============
9
26
2
*/
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
