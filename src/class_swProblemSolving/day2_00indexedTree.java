package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class day2_00indexedTree {
	static long tree [];
	static int tn;
	
	static long maxTree [];
	static long minTree [];
	static long sumTree [];
	
	
	static int TC;
	static int N;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			tree = new long[2*N];
			sumTree = new long[2*N];
			maxTree = new long[2*N]; 
			minTree = new long[2*N]; 
			
			//Fill Leaf Nodes
			st = new StringTokenizer(br.readLine());
			//N부터가 Leaf Node임
			for(int a=N;a<2*N;a++){			
				int node = Integer.parseInt(st.nextToken()); 
				tree[a] = node;
				sumTree[a] = node;
				maxTree[a] = node;
				minTree[a] = node;
			}
			//Fill full idx tree
			for(int a=N-1;a>0;a--){//뒤에서(아래서)부터 채운다
				sumTree[a] = sumTree[2*a]+sumTree[2*a+1];//sumTree
				maxTree[a] = Math.max(maxTree[2*a],maxTree[2*a+1]);//maxTree
				minTree[a] = Math.min(minTree[2*a],minTree[2*a+1]);//maxTree
			}
			
			printThisTree(sumTree);
			printThisTree(maxTree);
			printThisTree(minTree);
			
//			System.out.println("#"+tc+"");
//		}

		br.close();

	}

	private static void printThisTree(long[] tree) {
		// TODO Auto-generated method stub
		System.out.println("==========================================");
		for(int i=0;i<N;i++){
			System.out.print(tree[i]+" ");
		}
		System.out.println();
		for(int i=N;i<2*N;i++){
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	public static class indexTree{
	    int s;
	    int e;

	    public indexTree(int s, int e) {
	        this.s = s;
	        this.e = e;
	    }
	    
	    public void insert (int w, int g){
	    	int i;
	    	for(i=tn + w - 1; i>0; i/=2){
	    		tree[i] += g;	    		
	    	}	    	
	    }
		
	    public int search(int s, int e){
	    	int res = 0;
	    	s = s + tn -1;
	    	e = e + tn -1;
	    	while (s<=e){
	    		if (s%2 == 1){//홀수
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
		
	}

}
