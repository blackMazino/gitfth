package referenceBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CompleteBinaryTree {
	/* 1차원 배열로 표현가능
	 * Parent(x) = x/2;
	 * left_child(x) = x*2;
	 * right_child(x) = 2*x+1;
	 * */
	static int n,tree[];
	static void inorder(int node){
		if(node*2<=n) inorder(node*2);
		
		System.out.println(tree[node]);
		
		if(node*2+1<=n) inorder(node*2+1);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int tc=1; tc<TC;tc++){	
			
			n = Integer.parseInt(st.nextToken());
			tree = new int[n+1];
			for(int i=1;i<=n;i++){
				st = new StringTokenizer(br.readLine());
				tree[i] = Integer.parseInt(st.nextToken());
			}
			inorder(1);
		}
	}
	
	

}
