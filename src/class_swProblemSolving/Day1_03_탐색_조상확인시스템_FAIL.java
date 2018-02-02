package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day1_03_탐색_조상확인시스템_FAIL {
/*
Rooted tree란 tree의 한 노드가 root가 되고 나머지 노드들은 다른 노드를 부모로 가지는 것이다. 
어떤 노드의 조상은 자신, 자신의 부모, 부모의 부모, 부모의 부모의 부모, ..., root 까지를 일컫는다.
노드가 
N
N개로 이루어진 트리의 정보와 루트 
R
R이 주어졌다. 트리의 각 노드는 
1
1번부터 
N
N번까지 번호가 배정되어 있으며 모두 다르다. 이어서 
Q
Q개의 질의가 들어오는데, 질의는 다음과 같은 형태이다.
x
x, 
y
y : 노드 
x
x가 노드 
y
y의 조상이면 YES 아니면 NO
위와 같은 트리 구조라고 하자. 루트 노드는 1번 노드이다.
5번은 7번의 조상이다.
2번은 2번의 조상이다.
1번은 모든 노드의 조상이다.
10번은 9번의 조상이 아니다.
4번은 1번의 조상이 아니다.
각 질의에 올바른 대답을 하는 프로그램을 작성하라.
===============
10 1 5
1 1 2 1 1 5 6 6 8 5
5 7
2 2
1 10
10 9
4 1
===============
YES
YES
YES
NO
NO
 * */
	
	static int TC;
	static int N,R,Q,x,y;
	static int nodes[]; 
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			nodes = new int [N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++){
				nodes[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<Q;i++){
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
			}
			
			
//			System.out.println("#"+i+"");
//		}

		br.close();

	}

}