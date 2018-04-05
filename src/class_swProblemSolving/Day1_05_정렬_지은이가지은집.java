package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//본인풀이 : 54개 맞음(시간초과, 틀림, 0이아닌 리턴)
public class Day1_05_정렬_지은이가지은집 {

	/*
최근에 지은이는 건축에 관심이 많아 집을 짓게 되었다. 태양이는 지은이가 지은 집에 놀러갔다. 근데 아니나 다를까 집에 작은 구멍이 있는 것이다. 지은이와 태양이는 집을 보수하기로 했다.
다행히도 지은이가 집을 짓다가 남은 재료가 남아서, 이를 이용하여 집을 보수하기로 했다. 구멍이 난 곳의 너비는 x센티미터이다. 태양이와 지은이는 사이가 너무 좋아서, 남은 재료 중 하나씩 골라서 이어 붙이고,
이로 구멍을 막기로 했다. 즉, 태양이과 지은이가 고른 재료의 길이가 L1, L2이면, L1+L2가 x와 같아야 구멍을 막을 수 있다. 크기가 다르면, 또 망가질 위험이 있기 때문이다.
그럼 이제 구멍을 완벽하게 막을 수 있는 두 막대를 구하는 프로그램을 작성하시오.
첫 번째 줄에 구멍의 너비 X가 주어진다. X의 단위는 센티미터이다.
두 번째 줄에 재료의 개수 N이 주어진다. (0 ≤ N ≤ 1,000,000)
세 번째 줄부터 N개의 줄에 걸쳐 각 재료의 길아 Li가 주어진다. Li의 단위는 나노미터이다. (1 ≤ Li ≤ 100,000,000, 1cm = 10,000,000nm)
첫 번째 줄에 구멍을 완벽하게 막을 수 있는 두 조각이 없다면 'danger'(따옴표 제외)를 줄력한다. 만약, 완벽하게 막을 수 있는 경우 'yes L1 L2'를 출력한다. (L1 ≤ L2). 
정답이 여러 개인 경우에는 |L1 - L2|가 가장 큰 것을 출력한다.
===============
1
4
9999998
1
2
9999999
===============
yes 1 9999999
*/
	
	static int TC;
	
	static int X,N;
	static int raws[];
	static int L1 [] = new int[1212121];
	static int L2 [] = new int[1212121];
	static int gap [] = new int[1212121];
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			raws = new int [N];
			for(int i=0; i<N;i++){
				st = new StringTokenizer(br.readLine());				
				raws[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(raws);
			X=X*10000000;
			int idx = 0;
			int maxGap = 0;
			int finalL1 = 0;
			int finalL2 = 0;
			for(int i=0; i<N-1;i++){
				for(int j=i+1;j<N;j++){
//					System.out.println(raws[i]+"+"+raws[j]+" = "+(raws[i]+raws[j]));
					if(raws[i]+raws[j]==X){
//						System.out.println(raws[i]+"+"+raws[j]+" = "+(raws[i]+raws[j]));
						L1[idx] = raws[i];
						L2[idx] = raws[j];
						gap[idx] = Math.abs(raws[i]-raws[j]);
//						System.out.println(raws[i]+"+"+raws[j]+" = "+(raws[i]+raws[j]));
//						System.out.println("gap : "+ gap[idx]);
						
						if(maxGap < gap[idx]){
							maxGap = gap[idx];
							finalL1 = Math.min(raws[i], raws[j]);
							finalL2 = Math.max(raws[i], raws[j]);
						}
						idx++;
					}
				}				
			}
			if(idx == 0){
				System.out.println("danger");
			}else{
//				int maxGap = 0;
//				int finalIdx = 0;
//				
//				for(int i=0;i<idx;i++){
////					System.out.println("gap : "+ gap[i]);
////					System.out.println("maxGap : "+ maxGap);
////					System.out.println("finalIdx : "+ finalIdx);
////					System.out.println(maxGap < gap[i]);
////					System.out.println("======================");
//					if(maxGap <= gap[i]){
//						maxGap = gap[i];
//						finalIdx = i;
//					}
//				}
//				System.out.println("========finally==========");
////				System.out.println("gap : "+ gap[i]);
//				System.out.println("maxGap : "+ maxGap);
//				System.out.println("finalIdx : "+ finalIdx);
				
//				System.out.println("yes "+Math.min(L1[finalIdx], L2[finalIdx]) 
//				                    + " "+Math.max(L1[finalIdx], L2[finalIdx]));
				
				System.out.println("yes "+finalL1+ " "+finalL2);				
			}
			
			
//		}

		br.close();

	}

}

/* 
//지은이가지은집
import java.io.*;
import java.util.*;
   
public class source {
    static int X, N;
    static int[] A;
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine()) * (int)1e7;
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        for (int i=0;i<N;i++) A[i] = Integer.parseInt(br.readLine());
        Arrays.sort(A);
        for (int i=0,r=N-1;i<r;i++){
            while (r > i && A[i]+A[r] > X) r--;
            if (r <= i) break;
            if (A[i]+A[r] == X){
                System.out.printf("yes %d %d\n", A[i], A[r]);
                return;
            }
        }
        System.out.println("danger");
    }
}
*/