package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class day3_02보석 {
/*
 * 문제
세계적인 도둑 동현이는 보석점을 털기로 결심했다.

동현이가 털 보석점에는 보석이 총 N개 있다. 
각 보석은 무게 Mi와 가격 Vi를 가지고 있다. 
동현이는 가방을 K개 가지고 있고, 
각 가방에 담을 수 있는 최대 무게는 Ci이다. 
각 가방에는 최대 한 개의 보석만 넣을 수 있다.

동현이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)

다음 N개 줄에는 각 보석의 정보 Mi와 Vi가 주어진다. (1 ≤ Mi, Vi ≤ 1,000,000)

다음 K개 줄에는 가방에 담을 수 있는 최대 무게 Ci가 주어진다. (1 ≤ Ci ≤ 100,000,000)

모든 숫자는 양의 정수이다.

출력
첫째 줄에 동현이가 훔칠 수 있는 보석 가격의 합의 최대값을 출력한다.

3 2
1 65
5 23
2 99
10
2

164
*/
	static int N, K,wi,vi;
	static int [] ci;
	static PriorityQueue<Integer> q;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			Gem [] gems = new Gem[N];				
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				wi = Integer.parseInt(st.nextToken());
				vi = Integer.parseInt(st.nextToken());
				gems[i] = new Gem(wi,vi);
			}
			Arrays.sort(gems);
			
			ci = new int[K];			
			for(int i=0;i<K;i++){
				st = new StringTokenizer(br.readLine());
				ci[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(ci);
			
			long totVal = 0;
			q = new PriorityQueue<>();
			int j=0;//보석정보의 idx
			for(int i=0;i<ci.length;i++){
				while(j<N && gems[j].w <= ci[i]){
					q.add(-gems[j].v);
					j++;
				}
				if(!q.isEmpty()){
					totVal += -q.poll();
				}
			}
			
			System.out.println(totVal);
//		}
		br.close();
	}
}
class Gem implements Comparable<Gem>{
	int w;//weight
	int v;//value
	
    public Gem(int w, int v) {
        this.w = w;
        this.v = v;
    }

	@Override
    public int compareTo(Gem ot){
    	return w - ot.w;
    }
    

}

