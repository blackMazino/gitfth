package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class day2_02laserTower {
/*
삼성 통신연구소는 레이저를 이용한 새로운 비밀 통신 시스템 개발을 위한 실험을 하고 있다. 
실험을 위하여 일직선 위에 N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세우고, 각 탑의 꼭대기에 레이저 송신기를 설치하였다. 
모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고, 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다. 
하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다.

예를 들어 높이가 6, 9, 5, 7, 4인 다섯 개의 탑이 수평 직선에 일렬로 서 있고, 모든 탑에서는 주어진 탑 순서의 반대 방향(왼쪽 방향)으로 동시에 레이저 신호를 발사한다고 하자. 
그러면, 높이가 4인 다섯 번째 탑에서 발사한 레이저 신호는 높이가 7인 네 번째 탑이 수신을 하고, 
높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신을 한다. 
높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신을 하지 못한다.

탑들의 개수 N과 탑들의 높이가 주어질 때, 각 각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 프로그램을 작성하라.

첫 번째 줄에 탑의 수 N이 주어진다. (1 ≤ N ≤ 500,000)

두 번째 줄에 N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 각각 주어진다. (1 ≤ 탑의 높이 ≤ 100,000,000)

첫 번째 줄에 주어진 탑들의 순서대로 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 사이에 두고 출력한다. 만약 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력한다.

====================
5
6 9 5 7 4
9
9 1 2 7 4 3 5 3 6
====================

0 0 2 2 4
0 1 1 1 4 5 4 7 4
 * */

	
	
//	static int TC;
	static int N;
	static int tower[];
	static int receiverTower[];
	static int highestTowerIdx = 1;
//	static ArrayList<Integer> stack = new ArrayList<Integer>();
	//stack으로는 linkedlist가 편한다 addLast, removeLast
	static LinkedList<Integer> stack = new LinkedList<Integer>();
	static int stackIdx = 1;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			tower = new int[N+1];
			receiverTower = new int[N+1];
			st = new StringTokenizer(br.readLine());

			for(int i=1;i<=N;i++){				
				tower[i] = Integer.parseInt(st.nextToken());
				if(i>1){
//					System.out.println(tower[i-1]+","+tower[i]);
//					/if(tower[i-1] < tower[i]){
						receiverTower[i] = getMinIdxTower(i, i-1);
//					}else{
//						receiverTower[i] = i-1;
//					}
				}else{//첫번째는 무조건 0
					stack.addLast(i);					
					receiverTower[i] = 0;
				}
				
			}
			StringBuffer sb = new StringBuffer();
			for(int i=1;i<receiverTower.length;i++){
				sb.append(receiverTower[i]);
				sb.append(" ");
					
			}
			System.out.println(sb.toString().trim());
			//System.out.println("#"+tc+"");
//		}
		br.close();
	}
	
	private static int getMinIdxTower(int s, int e) {
		int result = 0;
		if(tower[highestTowerIdx] < tower[s]){
			highestTowerIdx = s;
			stack.clear();
			stack.addLast(s);
			result = 0;
		}else{
			//우측이 좌측보다 클 때 stack쌓인 대상만 확인해본다.
			if(tower[e]<tower[s]){
				//방법1 : while문
//				boolean isLoop = true;
//				stackIdx = stack.size() -1;
//				while(isLoop){
//					if(tower[stack.get(stackIdx)]<tower[s]){
//						//stack.remove(stackIdx);
//						stack.removeLast();
//						stackIdx--;
//					}else{
//						result = stack.get(stackIdx);
//						isLoop = false;	
//					}
//				}
				//방법2 : for-loop
				int totLoopCnt = stack.size() -1;
				for(int i=totLoopCnt;i>=0;i--){
					if(tower[stack.get(i)]<tower[s]){
						stack.removeLast();
					}else{
						result = stack.get(i);
						break;
					}
				}
				
			}else{
				stack.addLast(e);	
				result = e;
			}
		}
		return result;

// 		recursion Function		
//		if(e==0) return 0;
//		if(tower[e] < tower[s]) {			
//			return getMinIdxTower(s, e-1);
//		}
//		else return e;
	}

}
