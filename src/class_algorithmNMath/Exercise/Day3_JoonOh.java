package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day3_JoonOh {
/*
문제 14437
대회가 문제 업로드 실수로 인해 지연되어버렸다. 하지만 이는 업로드 실수가 아닌 심술쟁이 해커 임준오(동탄 주민)의 소행이었다! 욱제가 이쁜 여자 사진을 보내주지 않자 심술이 난 준오는 욱제의 대회를 엉망으로 만들려고 한 것이다.
준오는 BOJ 서버가 문제를 찾을 수 없도록 문제의 이름을 암호화해버렸다. 암호화 과정은 다음과 같다.

문제 이름은 알파벳 소문자로만 이루어져 있다.
준오는 문제 이름에서 아무 문자나 골라 k(1≤k≤25)만큼 바꿔버린다
예를 들어, a를 3만큼 바꾸면 d로, z를 1만큼 바꾸면 a로 바뀐다.
k의 합이 s가 될 때까지 문자를 계속 바꾼다. (한 번 바꾼 문자를 다시 바꾸지는 않는다)
문자를 바꿀 때마다 k를 다시 고른다.
욱제는 앞으로 원래 문제의 이름을 몰라도 암호화된 문제를 빠르게 복구할 수 있도록 레인보우 테이블을 만들어 두려고 한다. 문제의 원래 이름이 주어질 때, 이름이 바뀔 수 있는 경우의 수를 구해서 욱제에게 알려주자!

입력
s(1≤s≤3000)와 알파벳 소문자로 이루어진 문제 이름(1≤길이≤3000)이 주어진다. 문제 이름에 공백은 없으며, 불가능한 입력(s<k, 25*길이<s 등)은 없다.

출력
암호화된 문제 이름의 경우의 수를 1,000,000,007로 나눈 나머지를 출력한다.
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
