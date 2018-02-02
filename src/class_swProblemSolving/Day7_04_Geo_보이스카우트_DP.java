package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day7_04_Geo_보이스카우트_DP {
/*
뉴 잉글랜드의 보이 스카우트 단체는 매년 스카우트 올림픽을 개최한다. 
각 스카우트 팀에게 임무를 배정하고 완료 점수를 통해 승자를 가린다. 
대회가 끝나고 나서는 모두 승자를 위해 밤새 기타를 치며 스카우트 노래를 부른다.

올해는 Maine에 있는 아름다운 숲에서 올림픽을 개최했다. 
이번에 각 팀에게 주어진 임무는 매우 어렵다. 
각 팀은 숲 안에 있는 나무 하나를 정해서 시작점으로 삼는다. 
그리고 다른 나무를 향해서 직진하고, 다음 나무에 도착하면 또 다른 나무를 향해서 직진한다. 
위와 같은 방법으로 나무 사이를 이동할 때 가장 많은 개수의 나무를 거친 팀이 승리하게 된다. 
단, 다음 나무로 이동할 때에 반시계 방향으로 돌아야만 한다. 즉, 나무에 도착했을 때에 다음 나무로 가기 위해서는 왼쪽으로 180도 미만으로 회전할 수 있다는 뜻이다. 또한, 각 팀은 마지막에 처음 나무로 돌아와야 하는데, 이 때 자신이 두번째로 들렀던 나무도 여전히 왼쪽으로 180도 미만으로 회전해서 갈 수 있어야 한다. 추가로 지나간 경로를 그렸을 때 단순 다각형을 구성해야 한다.

스카우트 팀들은 올림픽에 노트북을 들고오지 않았기 때문에 승리하기 위해서 어떤 경로로 가야 승리할 수 있는 지 계산할 수 없다. 
그래서 당신에게 승리하기 위해서 이동할 수 있는 최대 나무 개수를 구해달라고 요청했다.

Maine의 아름다운 숲에 있는 나무 개수N과 각 나무의 위치가 2차원 좌표로 주어졌을 때, 
항상 왼쪽으로 회전하며 이동할 때 지날 수 있는 나무 개수의 최대값을 구하여라.

입력
입력
첫 번째 줄에는 나무의 개수 N(3≤N≤100)이 주어진다. 다음N개의 줄에 걸쳐서 각 나무의 좌표x,y(−10^6≤x,y≤10^6)
이 주어진다. 각 좌표는 소수점 이하 최대 2자리까지 주어진다. 어떠한 세 나무도 한 직선 위에 존재하지 않음이 보장된다.

출력
출력
가장 많은 개수의 나무를 이동할 때의 나무 개수를 출력하라.

5
0 0
1.5 -0.25
0 -1
-1 0.5
0.5 1

4
*/
	static int N;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			 
			
//			System.out.println("#"+tc+"");
//		}
		br.close();
	}
}
