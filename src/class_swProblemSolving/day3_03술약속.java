package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class day3_03술약속 {
/*
문제
동현이는 술을 좋아한다. 그런데, 동현이는 멍청하게도 같은 날에 여러개의 술 약속을 잡았다. 약속을 잡은 모든 사람들은 다들 동현이에게 술을 먹이고 싶어 한다.

각 약속에서 만나는 사람들은 동현이가 1분 늦을 때마다 동현이에게 술을 Di 병씩 마시게 할 것이다. 
각 약속은 동현이 집에서Ti분 거리여서, 동현이는 약속 장소에 가고 오는데 총2×Ti분이 걸리게 된다. (약속 장소에 갈 때는 항상 동현이 집에서 출발한다.) 
다행히도, 오늘의 사정을 아는 사람들은 사람들은 조금의 자비를 베풀어 동현이가 집에서 약속 장소까지 오는데 걸리는 시간 정도는 빼주기로 했다.

동현이는 당연히 잡아놓은 약속들을 반드시 가야만 한다. 
여러분이 동현이가 술을 적게 마실 수 있도록 가야할 약속 순서를 정해주자. 
이 때, 동현이가 가서 벌주를 마시는 시간은 0분이라고 가정하자. 그는 술을 엄청 빨리 마시니깐!



입력
첫 번째 줄에 술 약속의 개수N이 주어진다. (1≤N≤100,000)
두 번째 줄부터 N개의 줄에 걸쳐 각 약속의 Ti와 Di가 주어진다. (1≤Ti≤2,000,000,1≤Di≤100)
출력
첫 번째 줄에 동현이가 마셔야 할 최소 술병의 수를 출력한다.

6
3 1
2 5
2 3
3 2
4 1
1 6

86

동현이가 6번, 2번, 3번, 4번, 1번, 5번 순서로 약속에 간다고 생각하자.

첫 번째 약속인 6번 약속에서는 술을 마시지 않는다.
두 번째 약속인 2번 약속에서는 [(1분 + 1분)] × (5병) = 10병을 마시게 된다.
세 번째 약속인 3번 약속에서는 [(1분 + 1분) + (2분 + 2분)] × (3병) = 18병을 마시게 된다.
네 번째 약속인 4번 약속에서는 [(1분 + 1분) + (2분 + 2분) + (2분 + 2분)] × (2병) = 20병을 마시게 된다.
다섯 번째 약속인 1번 약속에서는 [(1분 + 1분) + (2분 + 2분) + (2분 + 2분) + (3분 + 3분)] × (1병) = 16병을 마시게 된다
여섯 번째 약속인 5번 약속에서는 [(1분 + 1분) + (2분 + 2분) + (2분 + 2분) + (3분 + 3분) + (3분 + 3분)] × (1병) = 22병을 마시게 된다.
최종적으로 동현이는 (0 + 10 + 18 + 20 + 16 + 22) = 86병을 마시게 된다
*/
static int N;
static ArrayList<Plan> plans;
	    
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			plans = new ArrayList<>();
			for(int i=1;i<=N;i++){
				st = new StringTokenizer(br.readLine());	
				int t =  Integer.parseInt(st.nextToken());
				int d =  Integer.parseInt(st.nextToken());
				Plan plan = new Plan(t,d);
				plans.add(plan);
			}
			Collections.sort(plans);
			
			long sum = 0, ans = 0;
			for(Plan p : plans){
				ans = ans + sum*p.d;
				sum = sum + p.t;
			}
					
			
			System.out.println(ans << 1);//ans*2^1 bit연산자
//		}

		br.close();

	}
}

class Plan implements Comparable <Plan>{
	int t,d;
	public Plan (int t,int d){
		super();
		this.t = t;
		this.d = d;
	}
	@Override
	public int compareTo(Plan ot) {
		if(t*ot.d < ot.t*d) return -1;
		if(t*ot.d > ot.t*d) return 1;
		return 0;
	}
}

//public class Pair<A, B> {
//private A first;
//private B second;
//
//public Pair(A first, B second) {
//    super();
//    this.first = first;
//    this.second = second;
//}
//public A getFirst() {
//    return first;
//}
//
//public void setFirst(A first) {
//    this.first = first;
//}
//
//public B getSecond() {
//    return second;
//}
//
//public void setSecond(B second) {
//    this.second = second;
//}
//}