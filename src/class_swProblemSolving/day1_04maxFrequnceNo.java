package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class day1_04maxFrequnceNo {

	/*
	첫 번째 줄에는 정수의 개수 N이 주어진다. (1≤N≤1000000) 
	두 번째 줄부터 N개의 줄에 걸쳐 각 줄에 하나의 정수가 주어진다. 이 수의 절대값은 2^31 − 1이하이다. 
	가장 많이 등장하는 정수를 출력하시오
	===============
	4
	1
	2
	3
	3
	===============
	3
	*/	
	
	static int TC;
	static int [] nums;

	static int [] cnt = new int[1212121];
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			nums = new int[N];
			
			for(int i=0; i<N;i++){
				st = new StringTokenizer(br.readLine());
				nums[i] = Integer.parseInt(st.nextToken());				
			}			
			//sort한 다음 직전 숫자와 현재숫자가 같으면 cnt ++
	        Arrays.sort(nums);
	        cnt[0] = 1;
	        for(int i=1;i<N;i++){
	        	if(nums[i]==nums[i-1]){
	        		cnt[i] = cnt[i-1] + 1;
	        	}else{
	        		cnt[i] = 1;
	        	}
	        	
	        }
	        int max=0;//가장큰수의 빈도
	        int maxw = 0;//가장 큰 수
	        for(int i=0;i<N;i++){
	        	if(max<cnt[i]){
	        		max = cnt[i];
	        		maxw = nums[i];
	        	}
	        		
	        }
	        
			System.out.println(maxw);
//		}

		br.close();

	}

}
