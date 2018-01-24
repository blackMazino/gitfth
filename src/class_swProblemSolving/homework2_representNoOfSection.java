package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class homework2_representNoOfSection {
/*
문제
수열 A가 주어졌을 때, 주어지는 구간의 최소값, 최대값, 합을 구하여라.
입력
첫 번째 줄에 수열의 길이 N이 주어진다. (1 ≤ N ≤ 100,000)
두 번째 줄에 수열의 각 수 Ai가 공백으로 분리되어 주어진다. (1 ≤ Ai ≤ 1,000,000,000)
세 번째 줄에 구간의 수 M이 주어진다. (1 ≤ M ≤ 100,000)
네 번째 줄부터 M개의 줄에 걸쳐 구간의 정보 a, b가 주어진다. 이는 수열의 구간 [a, b]에 대해 최소값, 최대값, 합을 구하라는 의미이다.
각 질의에 대해 최소값, 최대값, 합을 공백으로 분리하여 출력한다. 이 때, 64-bit 정수형의 범위에서 답이 나올 수 있음에 유의하시오.
===============
5
1 2 3 4 5
3
2 4
3 5
1 4
===============
2 4 9
3 5 12
1 4 10
 * */
	static int N,M,tn,a,b;
	static int [] Ai;
	static long [] sumTree, maxTree,minTree;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Ai = new int[N+1];
			st = new StringTokenizer(br.readLine());
			sumTree = new long [414141];
			maxTree = new long [414141];
			minTree = new long [414141];			
			for(tn =1; tn<N; tn*=2);
			for(int i=1;i<=N;i++){
				Ai[i] = Integer.parseInt(st.nextToken());
				insert_min(i,Ai[i]);
				insert_max(i,Ai[i]);
				insert_sum(i,Ai[i]);
//				printThisTree(sumTree);
			}
			
//			printThisTree(sumTree);
//			printThisTree(minTree);
//			printThisTree(maxTree);
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			
			for(int i=0;i<M;i++){
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				System.out.println(search(a, b,"min") +" "+
						           search(a, b,"max") +" "+
						           search(a, b,"sum") 
						           );
			}
			
//			System.out.println("#"+tc+"");
//		}

		br.close();

	}
	public static void insert_min(int w, int g){
		for(int i=tn + w -1; i>0 ; i/=2){
			if(minTree[i]==0 || minTree[i]>g){
				minTree[i] = g; 
			}
		}
	}
	public static void insert_max(int w, int g){
		for(int i=tn + w -1; i>0 ; i/=2){
			if(maxTree[i]<g){
				maxTree[i] = g;
			}			
		}
	}
	public static void insert_sum(int w, int g){
		for(int i=tn + w -1; i>0 ; i/=2){
			sumTree[i] += g;
		}
	}


    public static long search(int s, int e, String treeType){
    	long res = 0;
    	s = s + tn -1;
    	e = e + tn -1;
    	while (s<=e){
    		if (s%2 == 1){//홀수
    			if("min".equals(treeType)){
    				if(minTree[s] != 0 
    			       && (res==0||res>minTree[s]) ){   					
    					res = minTree[s];	
    				}    				
    			}
    			if("max".equals(treeType)){
    				if(res<maxTree[s]){
    					res = maxTree[s];	
    				}    				
    			}
    			if("sum".equals(treeType)){ 
    				res += sumTree[s];
    			}
    			s++;
    		}
    		if(e%2 == 0){//짝수
    			if("min".equals(treeType)){
    				if(minTree[e] != 0 
    			       && (res==0||res>minTree[e]) ){   					
    					res = minTree[e];	
    				}    				
    			}
    			if("max".equals(treeType)){
    				if(res<maxTree[e]){
    					res = maxTree[e];	
    				}    				
    			}
    			if("sum".equals(treeType)){ 
    				res += sumTree[e];
    			}
    			e--;
    		}
    		s /=2;
    		e /=2;	    		
    	}
    	return res;
    }
	private static void printThisTree(long[] tree) {
		// TODO Auto-generated method stub
		System.out.println("==========================================");
		for(int i=0;i<32;i++){
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
}
