package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Day6_06_DP_최장증가부분수열_LIS {
/*
문제
부분 수열(Subsequence)은 어떤 수열에서 순서를 유지하되, 그 중 일부 항만을 선택하여 만들 수 있는 수열이다. 
예를 들어, [1,3,2,4]로 이루어진 수열이 있다면, 
[1,3,4],[1,2,4]등은 부분 수열이 될 수 있지만,
[1,2,3]은 부분수열이 될 수 없다.
최장 증가 부분 수열(Longest Increasing Subsequence)은 어떤 수열의 부분 수열 중 각 항이 이전 항에 비해 증가하는 부분 수열을 의미한다. 
예를 들어, 수열[1,8,4,12,2,14,6]의 최장 증가 부분 수열은
[1,8,12,14],[1,4,12,14]가 있다.
수열이 주어졌을 때, 해당 수열의 최장 증가 부분 수열의 길이를 알아내자.

입력
첫 번째 줄에 수열의 길이 
N이 주어진다. (1≤N≤300,000)

두 번째 줄에 수열의 각 항의 값이 순서대로 주어진다. 주어지는 숫자는 32비트 정수형 이내의 숫자이다.

출력
첫 번째 줄에 주어진 수열의 최장 증가 부분 수열의 길이를 출력한다.

10
1 1 2 2 3 3 2 2 5 5

4

7
1 8 4 12 2 14 6

4
*/
	static int N;
	static int []a;
	static LinkedList<Integer> lis;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			a = new int[N];
			for(int i=0; i<N;i++){
				a[i] = Integer.parseInt(st.nextToken());
			}			
			//바이너리서치 이용
			lis = new LinkedList<Integer>();
			int idx ;
			for(int i=0;i<N;i++){
				//list에 값이 현재 입력하는 값보다 크다면 치환한다.
				idx = Collections.binarySearch(lis,a[i]);
//				System.out.print(idx+"|");
				//없다면 (-(insertion Position) -1)
				/*Returns:the index of the search key, if it is contained in the list;
				 * otherwise, (-(insertion point) - 1). 
				 * The insertion point is defined as the point at which the key would be inserted into the list
				 * : the index of the first element greater than the key, or list.size() if all elements in the list are less than the specified key.
				 * Note that this guarantees that the return value will be >= 0 if and only if the key is found.*/
				if(idx <0) idx = -idx - 1;
				if(lis.size() < idx+1){
					lis.add(idx, a[i]);					
				}else{
					lis.set(idx, a[i]);
				}
//				print();
			}
			
//			lis.add(a[0]);
//			//시작자리를 0부터 시작
//			for(int i=0;i<N-1;i++){
//				for(int j=i+1;j<N;j++){
//					if(lis.getLast() < a[j]){
//						if(lis.get(lis.size() - 1) < a[j]){
//							lis.addLast(a[j]);	
//						}else{
//							lis.removeLast();
//							lis.addLast(a[j]);	
//						}						
//					}					
//				}
//			}
//			print();
			System.out.println(lis.size());		
			//강사풀이
			/* D[i] = i를 마지막으로 하는 증가부분 수열의 최대길이
			 * j번째까지라고 할때
			 * D[i] = max(D[j]+1)
			 * Reverse_D[i]를 다음과 같이 정의하자
			 * Reverse_D[A[i]] = D[i]
			 * -> D[i] = Max(Reverse_D[k]) + 1(-무한대<=k<A[i])
			 * 아.. 모르겠다..
			 * */
			
			
//		}
		br.close();
	}
	private static void print() {
		for(int a : lis){
			System.out.print(a+" ");
		}
		System.out.println();
		System.out.println("============");
	}
}

/*
//최장 증가 부분 수열(LIS) 
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;  
//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
 
 
//import java.math;
 
public class source {     
 
//    

//6
//2 1 4 3 5 7
 

     
    static int n;
    static int[] x, d; 
     
    public static void main(String[] args) throws Exception {       
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        n = Integer.parseInt(br.readLine());
     
       x = new int[n+1]; 
     //   d = new int[n+1];        
         
        ArrayList<Integer> dp = new ArrayList<Integer>();
       // dp.add(1);
         
        int ans = 0;
        int idx ;
  
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(st.nextToken()); 
             
 
            idx = Collections.binarySearch(dp, x[i]);
            if (idx < 0) {
                idx = - idx - 1;
            }
             
            if(dp.size() < idx+1) dp.add(idx, x[i]);
            else dp.set(idx, x[i]); //) = x[i];           
             
        }
         
        br.close(); 
                
       // ans = dp.size() ;
          
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
          
 
        bw.write(String.valueOf(dp.size()));
        
        
        bw.flush();
        bw.close();
    }  
}
*/