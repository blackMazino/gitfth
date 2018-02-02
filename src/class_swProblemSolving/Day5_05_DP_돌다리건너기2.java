package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day5_05_DP_돌다리건너기2 {
/*
절대반지를 얻기 위하여 반지원정대가 출발한다. 원정대가 지나가야할 다리는 두 개의 인접한 돌다리로 구성되어 있다. 하나는 <악마의 돌다리>이로 다른 하나는 <천사의 돌다리>이다.

아래 그림 1은 길이가 6인 다리의 한 가지 모습을 보여준다. 그림에서 위의 가로줄은 <악마의 돌다리>를 표시하는 것이고, 아래의 가로줄은 <천사의 돌다리>를 표시한다. 두 돌다리의 길이는 항상 동일하며, 각 칸의 문자는 해당 돌에 새겨진 문자를 나타낸다. 두 다리에 새겨진 각 문자는 {R, I, N, G, S} 중 하나이다.

출 R I N G S R 도
발 G R G G N S 착

반지원정대가 소유하고 있는 마법의 두루마리에 <악마의 돌다리>와 <천사의 돌다리>를 건너갈 때 반드시 순서대로 밟고 지나가야할 문자들이 적혀있다. 이 순서대로 지나가지 않으면 돌다리는 무너져 반지원정대는 화산 속으로 떨어지게 된다.

다리를 건널 때 다음의 제한 조건을 모두 만족하면서 건어야 한다.

1) 왼쪽(출발지역)에서 오른쪽(도착지역)으로 다리를 지나가야 하며, 반드시 마법의 두루마리에 적힌 문자열의 순서대로 모두 밟고 지나가야 한다.

2) 반드시 <악마의 돌다리>와 <천사의 돌다리>를 번갈아가면서 돌을 밟아야 한다. 단, 출발은 어떤 돌다리에서 시작해도 된다.

3) 반드시 한 칸 이상 오른쪽으로 전진해야하며, 건너뛰는 칸의 수에는 상관이 없다. 만일 돌다리의 모양이 그림 1고 같고 두루마리의 문자열이 "RGS"라면 돌다리를 건너갈 수 있는 경우는 다음의 3가지 뿐이다. (아래 그림에서 크게 표현된 문자는 밟고 지나가는 돌다리를 나타낸다.)

출 R^ I N  G S^ R 도
발 G  R G^ G N  S 착

출 R^ I N G  S^ R 도
발 G  R G G^ N  S 착

출 R I  N G^ S R  도
발 G R^ G G  N S^ 착

아래의 세 방법은 실패한 방법이다.

출 R^ I N G  S R 도
발 G  R G G^ N S 착

출 R^ I N G^ S R  도
발 G  R G G  N S^ 착

출 R^ I N G S^ R 도
발 G^ R G G N  S 착

왜냐하면 첫 번째는 문자열 "RGS"를 모두 밟고 지나가야 하는 조건 1)을 만족하지 않으면, 두 번째는 번갈아가면서 돌을 밟아야 하는 조건 2)를, 세 번째는 앞으로 전진을 하여야하는 조건 3)을 만족하지 않기 때문이다.

마법의 두루마리에 적힌 문자열과 두 다리의 돌에 새겨진 문자열이 주어졌을 때, 돌다리를 통과할 수 있는 모든 가능한 방법의 수를 계산하는 프로그램을 작성하시오. 예를 들어, 그림 1의 경우는 통과하는 방법이 3가지가 있으므로 3을 출력해야 한다.

입력
첫 번째 줄에는 마법의 두루마리에 적힌 문자열(R, I, N, G, S 로만 구성된)이 주어진다. 이 문자열의 길이는 최소 1, 최대 20 이다.

두 번째 줄에는 각각 <악마의 돌다리>와 <천사의 돌다리>를 나타내는 같은 길이의 문자열이 주어진다. 그 길이는 1 이상, 100 이하이다.

출력
첫째 줄에 마법의 두루마리에 적힌 문자열의 순서대로 다리를 건너갈 수 있는 방법의 수를 출력한다. 그러한 방법이 없으면 0을 출력한다.

답은 항상 부호있는 32비트 정수형으로 표현할 수 있는 범위 내에 존재한다.

RGS
RINGSR
GRGGNS

RINGS
SGNIRSGNIR
GNIRSGNIRS

GG
GGGGRRRR
IIIIGGGG

3
0
16

*/
	
	static String scroll,angel,devil;
	static char [] s,A1, A2;
	static int N,M;
	static int  [][] D1,D2;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			//st = new StringTokenizer(br.readLine());
			
			scroll =br.readLine();
			devil = br.readLine();
			angel = br.readLine();
			M = scroll.length();
			N = devil.length();
			s = new char[22];
			for(int k=0;k<M;k++){
				s[k] = scroll.charAt(k);
			}
			A1 = new char[109];
			A2 = new char[109];
			for(int i=0;i<N;i++){
				A1[i] = devil.charAt(i);
				A2[i] = angel.charAt(i);			
			}	
			D1 = new int[22][109];
			D2 = new int[22][109];
//			for(int i=0;i<M;i++){
//				System.out.print(s[i]);
//			}
//			System.out.println();
//			for(int i=0;i<N;i++){
//				System.out.print(A1[i]);
//			}
//			System.out.println();
//			for(int i=0;i<N;i++){
//				System.out.print(A2[i]);
//			}
//			System.out.println();
			
			D1[0][0] = D2[0][0] = 1;
			for(int k=0;k<=M;k++){
				for(int j=0;j<N;j++){
					if(k<M && A2[j+1]==s[k+1]){
//						if(A2[j+1]==s[k+1]){
							D2[k+1][j+1] += D1[k][j];
//						}
					}
					if(k<M && A1[j+1]==s[k+1]){
							D1[k+1][j+1] += D2[k][j];
					}
					D1[k][j+1] +=D1[k][j];
					D2[k][j+1] +=D2[k][j];
					//System.out.print(D1[k][j]+","+D2[k][j] + " | ");
				}				
//				System.out.println();
			}			
			System.out.println((D1[M][N]+D2[M][N]));
//		}
		br.close();
	}
	
}

/*
//돌다리건너기(java소스없음)
#include <bits/stdc++.h>
using namespace std;
  
int N, M;
char S[22], A[2][109];
int D[22][2][109];
  
int main()
{
    scanf("%s%s%s", S+1, A[0]+1, A[1]+1);
    N = strlen(A[0]+1); M = strlen(S+1);
    D[0][0][0] = D[0][1][0] = 1;
    for (int k=0;k<=M;k++){
        for (int i=0;i<2;i++) for (int j=0;j<N;j++){
            if (k < M && A[i^1][j+1] == S[k+1]) D[k+1][i^1][j+1] += D[k][i][j];
            D[k][i][j+1] += D[k][i][j];
        }
    }
    printf("%d\n", D[M][0][N]+D[M][1][N]);
}
 * */
