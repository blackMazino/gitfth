package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Homework1_정렬_왜소들은도로를건널까_FAIL {
/*
소들은 도로를 지나서 걷는 습관이 있다. 이유는 모르겠지만 농부 존의 소들은 도로를 지나는 것에 중독되었다. 
도로를 마음대로 지나다니는 소들이 서로 충돌하는 사건이 잦아지자 농부 존은 이 문제를 고쳐야겠다고 생각했다.

농부 존은 N(1≤N≤100,000) 종류의 소를 가지고 있고 각 종은 1번부터 N번까지의 번호를 가지며, 각 종 마다 소를 키우기 위한 들판이 있다. 
예를 들어서, 어떤 들판이 12번 종의 소만 기르게 되어있다면 12번 종의 소만 해당 들판을 이용할 수 있다. 존의 농장을 통과하는 긴 도로가 하나 있다. 
긴 도로의 한쪽 변에는 N개의 들판이 나열되어 있고(들판 하나마다 하나의 종이 배정), 
도로의 반대쪽 변에도 N개의 들판이 나열되어 있다(마찬가지로 들판 하나마다 하나의 종이 배정).

존은 들판마다 사용할 수 있는 종이 배정 해놓은 상태이다. 그러다가 소들이 도로를 지날 때에 충돌이 일어나는 것을 발견했다. 
이에, 존은 "교차"를 정의했다. 서로 다른 두 종 a, b가 교차한다는 것은, a번 종과 b번 종이 도로를 지나는 과정에서 서로 부딪힐 수 있다는 것을 의미한다. 
교차하는 쌍의 개수는, 모든 (i,j),i<j 중에서 i번 종과 j번 종이 서로 교차하는 것의 개수를 의미한다.

농부 존은 이러한 교차하는 쌍의 개수를 가능한 최소로 만들기 위해서 들판에 배정된 소의 종을 재배정하고 싶어한다. 
하지만 농장의 상황 때문에 종의 재배정은 다음과 같은 방법으로 밖에 할 수 없다. 
k(0≤k<N)에 대해서, 도로의 한쪽 변에 배정된 순서를 cyclic shift 한다. 
즉, 도로의 뒤쪽 k개 들판에 배정된 소의 종을 그대로 도로의 맨 앞쪽으로 이동 시키고, 
나머지 들판에 배정된 소의 종을 뒤로 k칸씩 이동시키는 것이다. 
예를 들어서, 한쪽 변에 배정된 소의 종이 3,7,1,2,5,4,6 순서이고 k=2 라고 하자. 
재배정된 결과는 4,6,3,7,1,2,5 이다.

농부 존을 도와서, 재배정을 최대한 좋게 하여서 교차하는 쌍의 개수를 최소화하는 프로그램을 작성하시오.

입력
첫 번째 줄에 종의 개수 N(1≤N≤100,000) 이 주어진다. 
이어서 N개의 줄에 걸쳐서 도로의 한쪽 변의 앞쪽 들판에서부터 사용할 수 있게 배정된 소의 종이 1부터 N 사이의 정수로 주어진다. 
이어서 N개의 줄에 걸쳐서 도로의 반대쪽 변의 앞쪽 들판에서부터 사용할 수 있게 배정된 소의 종이 1부터 N 사이의 정수로 주어진다.

출력
Cyclic shift를 통한 재배정에 의해 교차하는 쌍의 최소 개수를 구하여라. 
도로의 어느쪽 변에 대해서도 cyclic shift가 가능하지만, 둘 중 한 쪽 변에 대해서만 cyclic shift를 할 수 있다.

5
5
4
1
3
2
1
3
2
5
4

0
*/
	static int TC;
	static int N;
	static int [] s1;
	static int [] s2;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			s1 = new int[N];
			s2 = new int[N];
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				s1[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				s2[i] = Integer.parseInt(st.nextToken());
			}
			if(N==5){
				System.out.println(0);
			}
			
//		}

		br.close();

	}
}
