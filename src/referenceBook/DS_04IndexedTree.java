package referenceBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DS_04IndexedTree {

	/* 각 노드가 하위노드의 값들중 대표값(최소,최대값,또느 합)을 가지고 있는 트리(포화트리)
	 * 단말노드는 실제 배열의 값
	 * 그외의 노드는 하위노드들의 대표값
	 * 
	 * indexed tree 말단 노드의 수는 N보다 크거나 같아야한다.
	 * 
	 * ex) N(1<=N<=100,000)개의 데이터가 주어졌을때, Q(1<=Q<=100,000)개의 쿼리를 수행하는 코드를 작성하라, 쿼리는 3개의 정수 C,X,Y로 이루어져 있으며, 
	 * 각각의 쿼리는 C가 0인 경우 X~Y구간의 최솟값을 출력하고 C가 1인 경우 X번쨰 데이터를 Y로 수정한다
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
	 * 3 
	 * 
	 * 2
	 * 1
	 * */
	
	static final int MAX_TREE = 262144, MAX_N = 100001;
	static int N,Q;
	static int tree[] = new int[MAX_TREE],data[] = new int [MAX_N], i;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int tc=1; tc<=TC;tc++){
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++){				
				data[i] = Integer.parseInt(st.nextToken());
			}
			
			int tmpN;//처음으로 N보다 크거나 같은 2의 제곱수
			for(tmpN=1;tmpN<=N;tmpN*=2);
			//초기데이터 설정
			for(i=0;i<N;i++) tree[i+tmpN] = data[i];
			//초기 데이터로 트리데이터 구성
			for(int i=tmpN-1;i>=1;i--) tree[i] = Math.min(tree[i*2], tree[i*2+1]);
			//쿼리 입력 및 처리
			for(i=1;i<=Q;i++){
				int c,x,y;
				st = new StringTokenizer(br.readLine());
				c=Integer.parseInt(st.nextToken()); x=Integer.parseInt(st.nextToken()); y=Integer.parseInt(st.nextToken());
				
				//처리
				if(c==1){//수정
					tree[x+tmpN-1] = y;
					x=(x+tmpN-1)/2;
					while(x!=0){
						tree[x]=Math.min(tree[x*2], tree[x*2+1]);
						x/=2;
					}
				}else{//구간의 최소값
					int min = 2000000000;//2billions
					x+=tmpN-1; y+=tmpN-1;
					while(x<=y){
						if(x%2==1) min = Math.min(tree[x], min);
						if(y%2==0) min = Math.min(tree[y], min);
						x=(x+1)/2; y=(y-1)/2;
					}
					System.out.println(min);
				}
			
			}
		}
	}

}
