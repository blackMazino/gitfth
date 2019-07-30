package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 기하_CCW_KOITP_단순다각형의넓이 {
/*
문제
이차원 평면에 점 N개로 이루어진 단순 다각형이 주어진다. 
이때 이 다각형이 평면에서 차지하는 면적을 구해보자. 다각형의 면적은 반드시 0보다 크다.

입력
첫째 줄에 다각형을 이루는 점의 갯수 (1≤N≤100,000)이 주어지고,
둘째 줄 부터 N개의 점의 정수 좌표(−109≤x,y≤109)가 입력으로 주어진다.

다각형의 좌표는 시계 방향 또는 반시계 방향 순서로 주어진다.

출력
주어진 단순 다각형이 이루는 넓이를 소수 둘째 자리에서 반올림 하여 소수점 첫째 자리까지 출력한다.

3
1 1
1 2
2 1

0.5
*/
	
	
	static int N;
	static LinkedList<p2D> a;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
//		System.out.println(Long.MAX_VALUE<Double.MAX_VALUE);
//		System.out.println(Double.MAX_VALUE);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			//시작점에서 한방향(시계,반시계)으로 좌표가 주어지므로
			//현재좌표, 직전좌표, 첫좌표 삼각형의 넓이는 계속 더해가면된다
			
			//한방향으로 주어지지 않았다면 정렬 해야함(convexHull에서 정렬(stack쌓기전)
			a = new LinkedList<>();
			long ans = 0;
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				a.add(new p2D(x,y));
				if(i>=2){				
					//double형의 사이즈는 제한					
					//String S = String.valueOf((Math.abs( ccw(a.get(0), a.get(i-1), a.get(i)) )/2));
					//ans += Double.parseDouble(String.format("%.1f", (Math.abs( ccw(a.get(0), a.get(i-1), a.get(i)) )/2))) ;
//					double tmp = Math.abs( ccw(a.get(0), a.get(i-1), a.get(i)) );
					
					ans += Math.abs( ccw(a.get(0), a.get(i-1), a.get(i)) );
				}
			}
			if(ans%2 == 0){
				System.out.println(ans/2);
			}else{//어차피 2로만 나눔
				String s = ans/2 +"."+5;
				System.out.println(s);
//				System.out.format("%. 1f", 3.1415);
			}

			
//			System.out.println(ans/2.0);
//		}
		br.close();
	}
	/* p1.x  p2.x  p3.x
	 * p1.y  p2.y  p3.y
	 * 
	 */
    static long ccw(p2D p1, p2D p2, p2D p3) {
        long tmp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        tmp -= (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
        return tmp;
    }
}
class p2D {
    long x;
    long y;
 
    p2D(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

/*
//단순 다각형의 넓이
import java.io.*;
import java.util.*;
  
public class source {
    static int N;
    static int[] X, Y;
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        X = new int[N+1];
        Y = new int[N+1];
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            X[i] = Integer.parseInt(st.nextToken());
            Y[i] = Integer.parseInt(st.nextToken());
        }
        //벡터 ->a, ->b가 만드는 삼각형의 넓이 : 벡터내적(dotProduct)
        //평행사변형의 넓이(a dot b)를 구해서 전체 합에 /2해도 똑같다
        //평행사변형의 넓이   https://blog.naver.com/dydrogud22/110169719859
        //정리하면 |a2*b1-a1*b2|
        long ans = 0;
        for (int i=1;i<=N;i++){
            int j = i%N+1; //현재+1~ 한바퀴 돌면 0으로 회귀한다
            ans += (long)X[i]*Y[j] - (long)X[j]*Y[i];
        }
        ans = Math.abs(ans);
        System.out.print(ans>>1); // ans/(2^1)
        System.out.printf(".%d", (int)(ans&1)*5);
        
//A.비트연산자
//1. 논리곱 (and) &  (x&1 -> 홀수면 1, 짝수면 0)
//각 비트를 비교하여 양쪽 모두 1이면 1, 아니면 0을 반환함.
//
//
//
//ex) a = 110, b= 220
//
//a = 0 1 1 0 1 1 1 0
//
//b = 1 1 0 1 1 1 0 0 
//
//a&b =  0 1 0 0 1 1 0 0
//
//
//
//2. 논리합 (or) |
//각 비트를 비교하여 어느 한쪽이 1 이면 1, 그렇지 않으면 0을 반환함.
//
//
//
//ex) a = 110, b= 220
//
//a = 0 1 1 0 1 1 1 0
//
//b = 1 1 0 1 1 1 0 0 
//
//a|b =  1 1 1 1 1 1 1 0
//
//
//
//B.시프트 연산자
//1. 왼쪽 시프트 연산자 << (x<<n = x * (2^n)
//ex) 150 << 2
//150 의 이진값을 왼쪽으로 2칸 시프트 합니다.
// 
//     1 0 0 1 0 1 1 0 : 150
//1 0 0 1 0 1 1 0 0 0 : 600
//
//위와 같은 결과. 왼쪽으로  두칸 밀면서, 비게 되는 오른쪽 두칸은 0으로 채웁니다.
//만약 데이터를 담는 자료형이 byte 타입이었다면, (8bit) 왼쪽으로 밀린 2개의 비트는 삭제됩니다.
//
//그런 경우 다음과 같은 결과가 됩니다
//    0 1 0 1 1 0 0 0 :  88
// 
//
//2. 오른쪽 시프트 연산자 >>  (x>>n = x/ (2^n)
//ex) 150 >> 2
//150의 이진값을 오른쪽으로 2칸 시프트 합니다.
//
//    (1 0 0 1 0 1 1 0)
//    1 1 (1 0 0 1 0 1 | 1 0)
//
//오른쪽으로 2비트 이동 한 후, 비게되는 왼쪽의 2개비트는 1로 채워지고, 오른쪽에서 2비트 넘어간 부분은 삭제됩니다. 
//따라서 결과는 아래가 됩니다.
//    1 1 1 0 0 1 0 1 
//
//주의점 : 무조건 왼쪽에 비는 부분이 1로 채워지는 것이 아닙니다. -> 밀기전 최초 첫째짜리 값(MSB)과 동일한 값으로 채워집니다.

int polygonArea(int X, int Y, int numPoints)
{
  int area = 0;         // Accumulates area in the loop
  int j = numPoints-1;  // The last vertex is the 'previous' one to the first
  for (int i=0; i<numPoints; i++)
    { area = area +  (X[j]+X[i]) * (Y[j]-Y[i]);
      j = i;  //j is previous vertex to i
    }
  return area/2;
}

    }
}
*/
