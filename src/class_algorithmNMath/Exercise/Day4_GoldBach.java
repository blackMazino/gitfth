package class_algorithmNMath.Exercise;

public class Day4_GoldBach {
/*	
 	���� 6588
	1742��, ������ �Ƹ��߾� ���а� ũ����Ƽ�� ������� �����ϸ�Ʈ ���Ϸ����� ������ ���� ������ �����ϴ� ������ ���´�.

	4���� ū ��� ¦���� �� Ȧ�� �Ҽ��� ������ ��Ÿ�� �� �ִ�.
	���� ��� 8�� 3 + 5�� ��Ÿ�� �� �ְ�, 3�� 5�� ��� Ȧ���� �Ҽ��̴�. ��, 20 = 3 + 17 = 7 + 13, 42 = 5 + 37 = 11 + 31 = 13 + 29 = 19 + 23 �̴�.

	�� ������ ������ �ذ���� ���� �����̴�.

	�鸸 ������ ��� ¦���� ���ؼ�, �� ������ �����ϴ� ���α׷��� �ۼ��Ͻÿ�.

	�Է�
	�Է��� �ϳ� �Ǵ� �� �̻��� �׽�Ʈ ���̽��� �̷���� �ִ�. �׽�Ʈ ���̽��� ������ 100,000���� ���� �ʴ´�.

	�� �׽�Ʈ ���̽��� ¦�� ���� n �ϳ��� �̷���� �ִ�. (6 �� n < 1000000)

	�Է��� ������ �ٿ��� 0�� �ϳ� �־�����.

	���
	�� �׽�Ʈ ���̽��� ���ؼ�, n = a + b ���·� ����Ѵ�. �� ��, a�� b�� Ȧ�� �Ҽ��̴�. ���ڿ� �����ڴ� ���� �ϳ��� ���еǾ��� �ִ�. ����, n�� ���� �� �ִ� ����� �����������, b-a�� ���� ū ���� ����Ѵ�. ��, �� Ȧ�� �Ҽ��� ������ n�� ��Ÿ�� �� ���� ��쿡�� "Goldbach's conjecture is wrong."�� ����Ѵ�.
	
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
