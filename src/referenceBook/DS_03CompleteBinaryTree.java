package referenceBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DS_03CompleteBinaryTree {
	/* 이진트리중 마지막레벨을 제외하고는 모든 노드가 꽉채워져 있고,
	 * 마지막레벨의 노드가 왼쪽부터 빈칸없이 채워지는 형태
	 * 1차원 배열로 표현가능
	 * Parent(x) = x/2;
	 * left_child(x) = x*2;
	 * right_child(x) = 2*x+1;
	 * N개 트리데이터가 입력 되었을떄, 트리를 중회순회하는 코드. 
	 * 중회순회란 이진트리를 순회하는 방법중 하나로 왼쪽자식탐색-자신출력-오른쪽자식탐색 순서로 탐색
	 * */
	
	/*
	 * 입력
	 * 6
	 * 123456
	 * 출력
	 * 425163
	 * */
	
	static int n,tree[];
	static void inorder(int node){
		if(node*2<=n) inorder(node*2);
		
		System.out.print(tree[node]+" ");
		
		if(node*2+1<=n) inorder(node*2+1);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int tc=1; tc<=TC;tc++){	
			
			n = Integer.parseInt(st.nextToken());
			tree = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=n;i++){				
				tree[i] = Integer.parseInt(st.nextToken());
			}
			inorder(1);
		}
	}
	
	

}
