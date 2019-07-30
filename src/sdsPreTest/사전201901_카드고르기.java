package sdsPreTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 사전201901_카드고르기 {

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
	static boolean [] visited;
	static ArrayList<Long> leftList, rightList;
	static long answer;
	public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201901.txt"));
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
        	//sort
//        	Arrays.sort(card);
        	
//        	long a= 1;
//        	for(int i=1;i<=N;i++) System.out.println(a<<i);
        	
//        	System.out.println(tc+", "+Integer.toBinaryString(tc)+" : "+Integer.bitCount(tc));

        	answer = 0;
        	
        	//풀이 1 backTracking 34개 케이스의 경우 타임아웃
//        	backTracking(0,0,0);
        	
        	//풀이 2 비트마스크 이용
//        	solve();
        	
        	//풀이 3 dfs 34개짜리 테스트케이스 타임아웃  backTracking이랑 같다
//        	visited = new boolean[N];
//        	for(int n=0;n<N;n++){
//        		visited[n] = true;
//        		dfs(n, card[n]);
//        		visited[n] = false;
//        	}
        	//풀이 4 배열을 반으로 나누어서 관리(둘다 오름차순)
        	leftList = new ArrayList<>();
        	rightList  = new ArrayList<>();
        	backTracking2(0, N/2, 0, leftList);
        	backTracking2(N/2,N  ,0, rightList);
        	Collections.sort(leftList);
        	Collections.sort(rightList);
        	getAllCase();

        	//풀이5 나누는걸 bitMasking을 이용하여 나눈다
//        	leftList = new ArrayList<>();
//        	rightList  = new ArrayList<>();
//        	divideWithBitMask();
//        	Collections.sort(leftList);
//        	Collections.sort(rightList);
//        	getAllCase2();
        	
        	//K가 0일때 한장도 뽑지 않는 경우도 생각해야함
        	if(K==0) answer++;
        	System.out.println("#"+tc+" "+answer);
        	
        }
	}

	private static void getAllCase2() {
		int L = 0; int Lsize = leftList.size()-1;
		int R = rightList.size()-1;
		while(L<=Lsize && R>=0){
			long LV = leftList.get(L);
			long RV = rightList.get(R) ;
			if(LV+RV < K){
				L++;
			}else if(LV+RV > K){
				R--;
			}else{
				long leftCnt = 0;
				long rightCnt = 0;
				while( (L<=Lsize) && (LV == leftList.get(L))){
					L++;
					leftCnt++;
				}
				while( (R>=0) && (RV == rightList.get(R))){
					R--;
					rightCnt++;
				}
				answer += leftCnt*rightCnt;
			}
		}
		
	}

	private static void divideWithBitMask() {
    	int left = N/2; 
    	int right = N-left;
    	for(int i=0;i<(1<<left);i++){//i< Math.pow(2, left)
    		long sum = 0;
    		for(int j=0;j<left;j++){
    			if( (i&(1<<j)) >0 ) sum+=card[j];// 3(=011)&4(=100) => 0, 켜져 있는 idx가 있다면, idx의 값을 더한다
    		}
    		leftList.add(sum);
    	}
    	for(int i=0;i<(1<<right);i++){
    		long sum = 0;
    		for(int j=0;j<right;j++){
    			if( (i&(1<<j)) >0 ) sum+=card[j+left];
    		}
    		rightList.add(sum);
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

	private static void backTracking2(int sIdx, int eIdx, int sum, ArrayList<Long> list) {
		if(sIdx>=eIdx){
			list.add((long) sum);
			return;
		}
		backTracking2(sIdx+1, eIdx, sum, list);
		backTracking2(sIdx+1, eIdx, sum+card[sIdx], list);
		
	}

	private static void dfs(int idx, int sum) {
		if(sum == K){
			answer++;
			return;
		}
		for(int i=idx+1;i<N;i++){
			if(!visited[i]){
				visited[i] = true;
				dfs(i, sum+card[i]);
				visited[i] = false;
			}
		}
		
	}

	private static void backTracking(int sum, int idx, int visited) {
		if(idx==N){//끝까지 온경우
			if(sum == K && visited > 0) answer ++;
			return;
		}
		backTracking(sum+card[idx], idx+1, visited+1);//현재 카드를 포함하는 경우
		backTracking(sum          , idx+1, visited);//포함하지 않는 경우
	}
	
	private static void solve() {
		// 모든 조합을 만들어 주자.
		for(int i=0;i<(1<<N);i++) {// 1<<N 1뒤에 0이 N개 1<<4 10000 = 32 (0은 모두 꺼진걸로 생각)
			// 선택될 개수가 1개 이상이면
			if(Integer.bitCount(i)>0) {
				int sum = 0;
				for(int j=0;j<N;j++) {
					// 해당 선택 index 더해주기
					if(((1<<j)&i)>0)
						sum += card[j];
				}
				if(sum==K) answer++;
			}
		}
	}
}