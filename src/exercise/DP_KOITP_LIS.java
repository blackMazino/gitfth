package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DP_KOITP_LIS {
/*
https://koitp.org/problem/LONGEST_INCREASING_SUBSEQUENCE/read/
부분 수열(Subsequence)은 어떤 수열에서 순서를 유지하되, 그 중 일부 항만을 선택하여 만들 수 있는 수열이다. 
예를 들어,[1,3,2,4]로 이루어진 수열이 있다면,[1,3,4],[1,2,4]등은 부분 수열이 될 수 있지만,[1,2,3]은 부분수열이 될 수 없다.
최장 증가 부분 수열(Longest Increasing Subsequence)은 어떤 수열의 부분 수열 중 각 항이 이전 항에 비해 증가하는 부분 수열을 의미한다. 
예를 들어, 수열 [1,8,4,12,2,14,6]의 최장 증가 부분 수열은[1,8,12,14],[1,4,12,14]가 있다.

수열이 주어졌을 때, 해당 수열의 최장 증가 부분 수열의 길이를 알아내자.
+ 최장증가수열 나열하라
+ 합이 가장큰 최장 증가수열의 값은?

첫 번째 줄에 수열의 길이 N이 주어진다. (1≤N≤300,000)두 번째 줄에 수열의 각 항의 값이 순서대로 주어진다. 
주어지는 숫자는 32비트 정수형 이내의 숫자이다.

10
1 1 2 2 3 3 2 2 5 5


4
 */
	static int N, answer;
	static int arr[],d[];//d[i] = i번째 인덱스의 증가수열, 답은 n의 최대값 +1
	static T Tracking[];//트랙킹용
	static LinkedList<Integer> LIS;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N]; 
		d = new int [N]; //
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++){
			arr[n] = Integer.parseInt(st.nextToken());
		}
		d[0]=arr[0];
		Tracking = new T[N];
		Tracking[0] = new T(0,arr[0]);
		int idx = 0;
		LIS = new LinkedList<>();
		for(int n=1;n<N;n++){
			/*
			배열 마지막 요소보다 새로운 수가 크다면, 배열에 넣는다.
			그렇지 않다면, 그 수가 들어갈 자리에 넣는다. (이분 탐색을 통해 들어갈 자리를 찾는다)
			*/
			if(d[idx]<arr[n] ){
				d[++idx] = arr[n];
				Tracking[n] = new T(idx, arr[n]);
			}else{
				int i = lower_bound(idx,arr[n]);
				d[i] = arr[n];
				Tracking[n] = new T(i, arr[n]);
			}						
		}
		answer = idx+1;
		System.out.println(answer);
		
		//d 배열이 LIS를 뜻하는건 아니다
		/*
7
1 8 4 12 2 14 6
		 */
		for(int i=0;i<d.length;i++){
			System.out.print(d[i]+" ");
		}
		System.out.println();
		for(int i=0;i<LIS.size();i++){
			System.out.print(LIS.get(i)+" ");
		}
		
	}
	private static int lower_bound(int end, int target) {
		int start = 0;
		while(start<end){
			int mid = (start+end) / 2;
			if(d[mid] >= target){
				end = mid;
			}else{
				start = mid +1;
			}
		}
		return end;
	}

}
class T{
	public T(int idx, int val) {
		super();
		this.idx = idx;
		this.val = val;
	}

	int idx, val;
}
