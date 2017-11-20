package referenceBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DS_05BIT {
	
	/*
	 * 구간 쿼리를 위한 자료구조로, 비트연산에 많이 사용됨 2N
	 * 각 i에 대해 i를 2진수로 바꿧을때 가장 먼저 나오는 1의 위치를 이용. 그 위치의 실제값을 L[i]라고 할때, 각 인덱스가 실제로 포함하고 있는 구간은 L[i]가 된다.
	 * L[i] = i & -i
	 * 
	 * ex) 구간핪을 구하는 문제
	 * N(1<=N<=100000)개의 데이터가 주어졌을떄, Q(1<=Q<=100000)개의 쿼리를 수행하는 코드를 작성하라. 쿼리는 C,X,Y로 이루어져 있으며, 
	 * 각각의 쿼리는 C가 0인 경우 X~Y 구간의 합을 출력하고 C가 1인 경우 X번째 데이터를 Y로 수정한다.
	 * */
	
	/* 입력
	 * 10 4 
	 * 1 2 3 4 5 6 7 8 9 10
	 * 0 3 7
	 * 1 5 2
	 * 0 4 9
	 * 0 1 4
	 * 
	 * 출력
	 * 25
	 * 
	 * 36
	 * 10
	 * */
	/*
	 * 비트 단위 연산자
	 *  &(and) : 둘다 1이어야 1     1111 & 1001 = 1001 
	 *  |(or)  : 둘중 하나라도 1이면 1 1111 | 1001 = 1111
	 *  ^(xor) : 둘의 값이 같으면 0   1111 ^ 1001 = 0110
	 * */
	
	
	static final int MAX_N = 100001;
	static int N,Q;
	static long tree[] = new long [MAX_N], data[] = new long [MAX_N];
	
	static long sum(int i){
		long ans = 0;
		while(i>0){
			ans += tree[i];
			i -= (i & -i);  
		}
		return ans;
	}
	
	static void update(int i, long diff){
		while(i<=N){
			tree[i] += diff;
			i += (i & -i);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());		
		for(int tc=1; tc<=TC;tc++){
			int i;
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
						
			st = new StringTokenizer(br.readLine());	
			//초기값 설정
			for(i=1;i<=N;i++){
				data[i] = Integer.parseInt(st.nextToken());
				update(i, data[i]);
			}
			
			for(i=1;i<=Q;i++){
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				if(c == 1){					
					long num = Long.parseLong(st.nextToken());
					long diff = num - data[x];
					update(x,diff);
				}else{
					int y = Integer.parseInt(st.nextToken());
					System.out.println(sum(y) - sum(x-1));
				}
			}
		}
	}

}
