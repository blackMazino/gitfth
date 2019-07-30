package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class 자료구조_IDXTREE_SdsPast41_KHeap슈퍼이벤트 {

	static int TC,Q,tn, maxVal, answer;
	static int[] treeSum;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/Past41.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
			for(int tc=1;tc<=TC;tc++){
				
				Q = Integer.parseInt(br.readLine());				
				for(tn=1;tn<100000;tn=tn+2);//tn = N+1 100,001
				treeSum = new int[212121];//10만개의 배
				maxVal = 0;
				bw.write("#"+tc+" ");
				for(int q=1;q<=Q;q++){
					st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					if(a==1){
						update(b,1);
						maxVal = Math.max(maxVal, tn+b);
					}else{
					     int s = tn;
					     int m1 = tn;
					     int m2 = maxVal;
					     int m = (m1+m2)/2;
					     long K = 0;
					     
					     // 구간합 중 K값의 구간을 빠르게 찾기위한 Parametric Search 이용
					     while(m1<=m2) {
					    	 K = get(s, m);					      
					    	 if (K>=b) {
							     answer = m - tn;
							     m2 = m; 
							     m2--;
							     m = (m1+m2)/2;
					    	 } else {
							     m1 = m; 
							     m1++;
							     m = (m1+m2)/2;
					    	 }
					     }
					     bw.write(answer+" ");
					     update(answer, 0);
					}
				}
				
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		}
	private static long get(int s, int e) {
		long result = 0;
		while(s<=e){
			if(s%2==1){
				result += treeSum[s];
				s++;
			}
			if(e%2==0){
				result += treeSum[e];
				e--;
			}
			s/=2;
			e/=2;
		}		
		return result;
	}
	private static void update(int w, int g) {
		for(int i=tn+w;i>0; i = i/2){
			if(i==tn+w){
				if(g==0) treeSum[i]--;
				else treeSum[i]++;
				
				continue;
			}
			treeSum[i] = treeSum[2*i] + treeSum[2*i+1];
		}			
	}

}
/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
 static int T, Q;
 static StringTokenizer st;
 static long[] treeSum;     // BIT 생성을 위한 1차원 배열
 static int base = 100001;  // BIT 원소 기준 값 (숫자카드가 100,000이므로 100001 설정)
 static int maxCard, ans;
 
 // BIT 값 갱신용 함수
 static void update(int x, int y) {
  if (y==0) {
   treeSum[x]--; x/=2;
  } else {
   treeSum[x]++; x/=2;
  }
  
  while(x!=0) {
   treeSum[x] = treeSum[x*2] + treeSum[x*2+1];
   x/=2;
  }
 }
 
 // BIT 이용 구간합 구하는 함수
 static long get(int l, int r) {
  long res=0;
  
  while(l<=r) {
   if(l%2==1) res += treeSum[l++];
   if(r%2==0) res += treeSum[r--];
   l/=2; r/=2;
  }
  
  return res;
 }
 
 public static void main(String[] args) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  
  T = Integer.parseInt(br.readLine());
  for (int tc=1; tc<=T; tc++) {
   // 카드 숫자가 100,000 이므로 여유 있게 2^18 1차원 배열 생성
   treeSum = new long[1<<18];
   maxCard = 0;
   
   bw.write("#" + tc + " ");
   
   Q = Integer.parseInt(br.readLine());
   for (int i=1; i<=Q; i++) {
    st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    
    // 구분자 1인 경우 해당 카드 숫자의 BIT값을 +1로 갱신함
    if (a==1) {
     update(base+b, 1);
     maxCard = Math.max(maxCard, base+b);
    } else {
    // 구분자 2인 경우 해단 순번의 참가자의 카드숫자를 찾아 출력 후 BIT값을 -1로 갱신 함
     int s = base;
     int m1 = base;
     int m2 = maxCard;
     int m = (m1+m2)/2;
     long K = 0;
     
     // 구간합 중 K값의 구간을 빠르게 찾기위한 Parametric Search 이용
     while(m1<=m2) {
      K = get(s, m);
      
      if (K>=b) {
       ans = m - base;
       m2 = m; m2--;
       m = (m1+m2)/2;
      } else {
       m1 = m; m1++;
       m = (m1+m2)/2;
      }
     }
     
     bw.write(ans + " ");
     // 해당 카드 숫자의 참가자를 -1로 갱신 함
     update(base+ans, 0);
    }
   }
   bw.newLine();
  }
  bw.flush();
  bw.close();
  br.close();
 }
}

/*

입력
3
5
1 3
1 5
1 10
2 2
2 2
7
1 1
1 1
1 1
2 2
1 1
2 3
2 1
10
1 5
1 15
1 25
1 21
1 18
1 10
2 2
1 21
2 5
2 3

출력
#1 5 10 
#2 1 1 1 
#3 10 21 18 

*/