package class_algorithmNMath.DailyHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day4_RobertHood {
/*
문제
로버트 후드는 로빈 후드의 동생이다. 로버트 후드는 자신의 형처럼 전설적인 인물이 되기 위해 활 쏘기를 연습하고 있다.

이번에 노팅엄에서 열린 활 쏘기 대회는 현대에 열리는 양궁과 규칙이 다르다. 양궁은 더 많은 점수를 쏜 사람이 승리하는 방식이다. 하지만, 노팅엄 활 쏘기 대회에서는 과녁에 맞은 화살 사이의 거리 중 최대값이 가장 큰 사람이 승리한다.

로버트 후드는 총 C발을 발사했고, 모든 화살은 과녁에 적중했다. 과녁을 이차원 평면으로, 화살은 점으로 나타낸다. 화살의 좌표가 주어졌을 때, 가장 먼 화살의 거리를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 로버트 후드가 발사한 화살의 수 C (2 ≤ C ≤ 100,000)가 주어진다. 다음 C개 줄에는 화살의 좌표가 주어진다. 좌표는 정수이고, 절대값은 1,000을 넘지 않는다.

출력
가장 먼 두 화살의 거리를 출력한다. 상대/절대 오차가 10-6 이내인 경우에만 정답이다.
 * */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int [][] xy = new int [C][2];
		for(int i=0;i<C;i++){			
			st =  new StringTokenizer(br.readLine());
			for(int j=0;j<2;j++){				
				xy[i][j] = Integer.parseInt(st.nextToken());
			}	
		}
		//print(xy, "input")		
		float result = 0;
		result = getMaxDistance(xy, C);//그냥 구하기
		//result = getMaxDistanceBy2Pointer(xy,C);//볼럭껍질을 이용하여 1번거르고 2pointer를 이용하여 좌표를 이동하여 매번 길이를 구한다
		System.out.println(result);
	}

	private static float getMaxDistanceBy2Pointer(int[][] xy, int c) {
		// TODO Auto-generated method stub
		
		//볼록껍질 만들기
		
		//
		return 0;
	}

	private static float getMaxDistance(int[][] xy, int C) {
		float result = 0;
		for(int i=0; i<C;i++){//nC2 = n*(n-1)/2!
			for(int j=i+1;j<C;j++){
				result = Math.max(result, calDistance(xy[i][0],xy[i][1],xy[j][0],xy[j][1]));
			}		
		}		
		return result;
	}

	private static float calDistance(int x1, int y1, int x2, int y2) {
		return (float)Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y1-y2), 2));
	}

	private static void print(int[][] xy, String title) {
		System.out.println("====="+title+"=====");
		for(int i=0;i<xy.length;i++){			
			for(int j=0;j<xy[i].length;j++){
				System.out.print(xy[i][j]+" ");
			}
			System.out.println();
		}
	}

/*
	#include <cstdio>
	#include <vector>
	#include <queue>
	#include <algorithm>
	#include "time.h"
	#include "math.h"
	using namespace std;

	#define MAXN 100000
	#define DIST(x1, y1, x2, y2) (((x1)-(x2))*((x1)-(x2))+((y1)-(y2))*((y1)-(y2)))
	typedef long long ll;

	int T, N;
	ll ox, oy, o = 0, max_dist = 0;

	int ccw(ll x1, ll y1, ll x2, ll y2, ll x3, ll y3) {
	    ll ret = (x1*y2 + x2*y3 + x3*y1) - (y1*x2 + y2*x3 + y3*x1);
	    return ret > 0 ? 1 : (ret < 0 ? -1 : 0);
	}
	struct Point {
	    ll x, y;
	    bool operator < (const Point &p) const {
	        int ret = ccw(ox, oy, x, y, p.x, p.y);
	        return ret == 0 ? abs(ox - x) + abs(oy - y) < abs(ox - p.x) + abs(oy - p.y) : ret > 0;
	    }
	    bool operator == (const Point &p) const {
	        return x == p.x && y == p.y;
	    }
	} P[MAXN + 5] = { {0,(ll)1e8} };

	int stack[MAXN + 10];
	int stack_cnt = 0;
	void stack_push(int a) { stack[stack_cnt++] = a; }
	ll stack_top(int a) { return stack_cnt - a - 1 < 0 ? -1 : stack[stack_cnt - a - 1]; }
	void stack_pop() { if (stack_cnt > 0) stack_cnt--; }

	Point max1, max2;
	int main()
	{
	    scanf("%d", &N);
	    for (int i = 1; i <= N; i++) scanf("%lld%lld", &P[i].x, &P[i].y);
	    for (int i = 1; i <= N; i++) if (P[i].y < P[o].y || (P[i].y == P[o].y && P[i].x < P[o].x)) o = i;

	    swap(P[o], P[1]);
	    ox = P[1].x, oy = P[1].y;

	    sort(P + 2, P + N + 1);

	    stack_push(N);
	    stack_push(1);
	    for (int i = 2; i <= N; i++)
	    {
	        while (stack_cnt > 2 && ccw(P[stack_top(1)].x, P[stack_top(1)].y, P[stack_top(0)].x, P[stack_top(0)].y, P[i].x, P[i].y) <= 0) stack_pop();
	        stack_push(i);
	    }
	    //stack[stack_cnt] = stack[0];
	    int idx1 = 0, idx2 = 0;
	    while (idx1 < stack_cnt) {
	        max_dist = max(max_dist, DIST(P[stack[idx1]].x, P[stack[idx1]].y, P[stack[idx2]].x, P[stack[idx2]].y));

	        if (ccw(P[stack[idx1]].x, P[stack[idx1]].y,
	            P[stack[idx1 + 1]].x, P[stack[idx1 + 1]].y,
	            P[stack[idx2 + 1]].x - P[stack[idx2]].x + P[stack[idx1]].x,
	            P[stack[idx2 + 1]].y - P[stack[idx2]].y + P[stack[idx1]].y) > 0) {
	            idx2++;
	            if (idx2 == stack_cnt) idx2 = 0;
	        }
	        else idx1++;
	    }
	    printf("%lf\n", sqrt(max_dist));
	}
*/	

}
