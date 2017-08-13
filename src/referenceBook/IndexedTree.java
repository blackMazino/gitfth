package referenceBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IndexedTree {

	/* 각 노드가 하위노드의 값들중 대표값(최소,최대값,또느 합)을 가지고 있는 트리(포화트리)
	 * 단말노드는 실제 배열의 값
	 * 그외의 노드는 하위노드들의 대표값
	 * 
	 * ex) N(1<=N<=100,000)개의 데이터가 주어졌을때, Q(1<=Q<=100,000)개의 쿼리를 수행하는 코드를 작성하라, 쿼리는 3개의 정수 C,X,Y로 이루어져 있으며, 
	 * 각각의 쿼리는 C가 0인 경우 X~Y구간의 최솟값을 출력하고 C가 1인 경우 X번쨰 데이터를 Y로 수정한다
	 * */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int tc=1; tc<TC;tc++){
			int z = Integer.parseInt(st.nextToken());
		}
	}

}
