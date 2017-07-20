package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day3_JoonOh {
/*
���� 14437
��ȸ�� ���� ���ε� �Ǽ��� ���� �����Ǿ���ȴ�. ������ �̴� ���ε� �Ǽ��� �ƴ� �ɼ����� ��Ŀ ���ؿ�(��ź �ֹ�)�� �����̾���! ������ �̻� ���� ������ �������� ���� �ɼ��� �� �ؿ��� ������ ��ȸ�� �������� ������� �� ���̴�.
�ؿ��� BOJ ������ ������ ã�� �� ������ ������ �̸��� ��ȣȭ�ع��ȴ�. ��ȣȭ ������ ������ ����.

���� �̸��� ���ĺ� �ҹ��ڷθ� �̷���� �ִ�.
�ؿ��� ���� �̸����� �ƹ� ���ڳ� ��� k(1��k��25)��ŭ �ٲ������
���� ���, a�� 3��ŭ �ٲٸ� d��, z�� 1��ŭ �ٲٸ� a�� �ٲ��.
k�� ���� s�� �� ������ ���ڸ� ��� �ٲ۴�. (�� �� �ٲ� ���ڸ� �ٽ� �ٲ����� �ʴ´�)
���ڸ� �ٲ� ������ k�� �ٽ� ����.
������ ������ ���� ������ �̸��� ���� ��ȣȭ�� ������ ������ ������ �� �ֵ��� ���κ��� ���̺��� ����� �η��� �Ѵ�. ������ ���� �̸��� �־��� ��, �̸��� �ٲ� �� �ִ� ����� ���� ���ؼ� �������� �˷�����!

�Է�
s(1��s��3000)�� ���ĺ� �ҹ��ڷ� �̷���� ���� �̸�(1�±��̡�3000)�� �־�����. ���� �̸��� ������ ������, �Ұ����� �Է�(s<k, 25*����<s ��)�� ����.

���
��ȣȭ�� ���� �̸��� ����� ���� 1,000,000,007�� ���� �������� ����Ѵ�.
 * */
	static int kMax;
	static int mod = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		String str = st.nextToken();
		kMax = 25;
		int result = getCntMod(S,str);
		System.out.println(result);
	}

	private static int getCntMod(int S, String str) {
		int L = str.length();
		int[][] d = new int [3000][3000];
		
		return d[L][S];
	}
/*
	#include "stdio.h"
	#include "string.h"
	#define MAXN 3000
	#define P 1000000007
	typedef long long ll;
	int L, S;
	char str[MAXN+10];
	int d[MAXN + 10][MAXN + 10];
	int main() {
	    scanf("%d %s", &S, str);
	    L = strlen(str);
	    for (int i = 0; i <= 25; i++) d[1][i] = 1;
	    for (int i = 1; i <= L; i++) 
	        for (int j = 0; j <= S; j++) 
	            for (int k = 0; k <= 25 && k <= j; k++) 
	                d[i][j] = (d[i][j] + d[i - 1][j - k]) % P;
	    printf("%d\n", d[L][S]);
	}
*/	
}
