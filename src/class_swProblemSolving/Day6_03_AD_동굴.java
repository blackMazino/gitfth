package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//못품 다시풀기
public class Day6_03_AD_동굴 {
/*
벌레 한 마리가 동굴을 지나려고 한다. 모두가 알다시피 동굴은 석순과 종유석이 굉장히 많은 공간이다. 이 벌레는 이렇게 장애물이 많은 동굴을 지나갈 것이다. 동굴의 길이는 N미터이고, 높이는 H미터이다. N은 항상 짝수이고, 장애물은 석순과 종유석이 번갈아 가면서 등장하고, 첫 장애물을 항상 석순이다.

아래 예제는 N=14, H =5의 예이다.



남자다운 이 벌레는 장애물을 굳이 피하지 않는다. 즉, 처음 정한 높이에서 일직선으로 장애물을 부수면서 지나간다. 벌레가 아래에서 4번째 구간으로 지나가면, 아래 그림과 같이 8개의 장애물을 부수게 된다.



하지만, 첫 번째나 다섯 번째 구간으로 날아간다면 벌레는 7개의 장애물만 부시면 된다.

벌레는 아픔을 느끼는 남자이기 때문에, 최소한의 장애물만 부시고 싶어한다. 여러분은 이 벌레가 최소 몇 개의 장애물만 부수고도 통과할 수 있는지 구하고, 그러한 구간이 총 몇 개 있는지 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 N과 H가 주어진다. N은 항상 짝수이다. (2 ≤ N ≤ 200,000, 2 ≤ H ≤ 500,000)

두 번째 줄부터 N개의 줄에 걸쳐 장애물의 크기가 순서대로 주어진다. 장애물의 크기는 H보다 작은 양수이다.

출력
첫 번째 줄에 개똥벌레가 파괴해야 하는 장애물의 최소값과 그러한 구간의 수를 공백으로 구분하여 출력하시오.

14 5
1
3
4
2
2
4
3
4
3
3
3
2
3
3

7 2


 * */
	static int N,H;
	static ArrayList<Integer> odd,even;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			odd = new ArrayList<>();
			even = new ArrayList<>();
			for(int i=0; i<N;i++){
				st = new StringTokenizer(br.readLine());
				int h = Integer.parseInt(st.nextToken()); 
				if(i%2 == 0 ){
					even.add(h);//0,2,4.. 석순
				}else{
					odd.add(H-h+1);//1,3,5.. 종유석
				}
			}
			Collections.sort(even);
			Collections.sort(odd);
			int ans = Integer.MAX_VALUE;
			int anscnt = 0;
			for(int i=1; i<=H;i++){
				int pEven = 0;
				while (pEven < even.size() && even.get(pEven) < i) pEven++;
				int pOdd = 0;
				while (pOdd < odd.size() && odd.get(pOdd) <= i) pOdd++;
				
				int cnt = even.size()-pEven + pOdd;
				if(ans > cnt){
					ans = cnt;
					anscnt = 1;
				}else if(ans == cnt){
					anscnt ++;
				}
				
			}		 
			System.out.println(ans + " "+ anscnt);
			
			int now = 0;
			
//		}
		br.close();
	}
}


/*
//동굴
import java.io.*;
import java.util.*;
   
// 정렬을 이용한 동굴 풀이

  
public class source {
    static int N, H;
    static ArrayList<Integer> A, B;
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        A = new ArrayList<Integer>();
        B = new ArrayList<Integer>();
        for (int i=0;i<N;i++){
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) A.add(h); //0,2,4.. 석순
            else B.add(H-h+1); //1,3,5.. 종유석
        }
        Collections.sort(A);
        Collections.sort(B);
        int ans = Integer.MAX_VALUE, anscnt = 0;
        for (int i=1,pt1=0,pt2=0;i<=H;i++){
            while (pt1 < A.size() && A.get(pt1) < i) pt1++;
            while (pt2 < B.size() && B.get(pt2) <= i) pt2++;
              
            
            // (A.size() - pt1) : 높이 i를 지나는 석순의 개수
            // pt2 : 높이 i를 지나는 종유석 개수
             
            int cnt = (A.size() - pt1) + pt2;
            if (ans > cnt){
                ans = cnt;
                anscnt = 1;
            }
            else if (ans == cnt)
                anscnt++;
        }
        System.out.println(ans + " " + anscnt);
    }
}
*/