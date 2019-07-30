package sdsPreTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 사전201901_카드고르기BYBackTracking {

/*
[입출력 예]
(입력)
2
6 3
-3 2 -10 6 9 7
8 -3
-8 2 10 8 7 -10 -1 -9




(출력)
#1 3
#2 8

(sample_input.txt 의 출력)
#1 3
#2 8
#3 3
#4 1
#5 13
#6 3
#7 34
#8 357
#9 24
#10 39111 

백준 부분집합 합 1208
https://whereisusb.tistory.com/227

비트마스크
http://oniondev.egloos.com/9839582
https://whereisusb.tistory.com/214
 */
	static int TC,N, K;// -10억<=카드값,K<=10억, 
	static int[] card;
	static boolean [] lVisited, rVisited;
	static ArrayList<Long> leftList, rightList;
	static long answer;
	public static void main(String[] args) throws Exception {
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201901.txt"));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=TC;tc++){
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	card = new int [N];
        	st = new StringTokenizer(br.readLine());
        	for(int i=0;i<N;i++){
        		card[i] = Integer.parseInt(st.nextToken());
        	}       	
        	answer = 0;        	        	

        	leftList = new ArrayList<Long>();
        	rightList  = new ArrayList<Long>();
        	backTracking(0,N/2, 0, leftList);
        	backTracking(N/2,N, 0, rightList);
        	Collections.sort(leftList);
        	Collections.sort(rightList);

        	getAllCase();
        	
        	//K가 0일때 한장도 뽑지 않는 경우도 생각해야함
        	if(K==0) answer++;
        	System.out.println("#"+tc+" "+answer);
        	
        }
	}

	private static void getAllCase() {
		int left = 0;
		int right = rightList.size()-1;
		while(left<leftList.size() && right>=0){
			long ls = leftList.get(left);
			long rs = rightList.get(right);
			if(ls+rs==K){
				long lc = 0;
				while(left<leftList.size() && leftList.get(left)==ls){
					lc++;
					left++;
				}
				long rc = 0;
				while(right>=0 && rightList.get(right)==rs){
					rc++;
					right--;
				}
				
				answer += lc*rc;
			}
			if(ls+rs>K) right--;
			if(ls+rs<K) left++;
		}
		
	}

	private static void backTracking(int sIdx, int eIdx, int sum, ArrayList<Long> list) {
		if(sIdx>=eIdx){
			list.add((long) sum);
			return;
		}
		backTracking(sIdx+1, eIdx, sum, list);
		backTracking(sIdx+1, eIdx, sum+card[sIdx], list);		
	}

}