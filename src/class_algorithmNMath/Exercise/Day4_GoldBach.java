package class_algorithmNMath.Exercise;

public class Day4_GoldBach {
/*	
 	문제 6588
	1742년, 독일의 아마추어 수학가 크리스티안 골드바흐는 레온하르트 오일러에게 다음과 같은 추측을 제안하는 편지를 보냈다.

	4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다.
	예를 들어 8은 3 + 5로 나타낼 수 있고, 3과 5는 모두 홀수인 소수이다. 또, 20 = 3 + 17 = 7 + 13, 42 = 5 + 37 = 11 + 31 = 13 + 29 = 19 + 23 이다.

	이 추측은 아직도 해결되지 않은 문제이다.

	백만 이하의 모든 짝수에 대해서, 이 추측을 검증하는 프로그램을 작성하시오.

	입력
	입력은 하나 또는 그 이상의 테스트 케이스로 이루어져 있다. 테스트 케이스의 개수는 100,000개를 넘지 않는다.

	각 테스트 케이스는 짝수 정수 n 하나로 이루어져 있다. (6 ≤ n < 1000000)

	입력의 마지막 줄에는 0이 하나 주어진다.

	출력
	각 테스트 케이스에 대해서, n = a + b 형태로 출력한다. 이 때, a와 b는 홀수 소수이다. 숫자와 연산자는 공백 하나로 구분되어져 있다. 만약, n을 만들 수 있는 방법이 여러가지라면, b-a가 가장 큰 것을 출력한다. 또, 두 홀수 소수의 합으로 n을 나타낼 수 없는 경우에는 "Goldbach's conjecture is wrong."을 출력한다.
	
*/	
	public static void main(String[] args) {


	}

/*	
	#include "stdio.h"
	#include "math.h"
	#define MAXN 1000000
	typedef long long ll;
	int N;
	int visited[MAXN + 1];
	int prime[MAXN + 1];
	int prime_cnt = 0;
	int main()
	{
	    ll i, j, ans = 0;
	    for (i = 2; i <= MAXN; i++)
	    {
	        if (visited[i] == 1) continue;
	        prime[prime_cnt++] = i;
	        for (j = i*i; j <= MAXN; j += i) visited[j] = 1;
	    }
	    for (; scanf("%d", &N) && N;) {
	        for (i = 0; i < prime_cnt; i++) if (visited[N - prime[i]] == 0) break;

	        if (i == prime_cnt) printf("Goldbach's conjecture is wrong.\n");
	        else printf("%d = %d + %d\n", N, prime[i], N - prime[i]);
	    }
	    return 0;
	}
*/	
}
