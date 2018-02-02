package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day5_02_DP_인접한비트의개수 {
/*
0과 1로 이루어진 수열 S가 주어진다. S의 첫 수를 s_1, 마지막 수를 s_N이라고 할 때, S의 인접한 비트의 개수는 다음과 같이 구할 수 있다.

s_1 x s_2 + s_2 x s_3 + s_3 x s4 + ... + s(N-1) x s_N
위의 식을 이용하면 수열 S에서 인접한 1의 개수를 구할 수 있다. 예를들어, 011101101의 인접한 비트의 개수는 3이 되고, 111101101은 4, 010101010은 0이 된다.

수열 S의 크기 N과 K가 주어졌을 때, 인접한 비트의 개수가 k인 수열 S의 개수를 구하는 프로그램을 작성하시오.

예를 들어, N이 5이고, K가 2이면, 수열 S가 될 수 있는 수열은 다음과 같이 6가지가 있다.

11100
01110
00111
10111
11101
11011
입력
첫 번째 줄에 테스트 케이스의 수 T가 주어진다. (1 ≤ T ≤ 1,000)

각 테스트 케이스의 첫 번째 줄에 세 정수 t, N, K가 공백으로 분리되어 주어진다.
t는 테스트 케이스의 번호이다. (1 ≤ N ≤ 100)

출력
각 테스트 케이스에 대해 테스트 케이스 번호와 인접한 비트의 개수가 k인 수열 S의 개수를 사이에 공백을 두고 한 줄에 하나씩 출력한다.

이 값은 2,147,483,647보다 작거나 같다.

10
1 5 2
2 20 8
3 30 17
4 40 24
5 50 37
6 60 52
7 70 59
8 80 73
9 90 84
10 100 90

1 6
2 63426
3 1861225
4 168212501
5 44874764
6 160916
7 22937308
8 99167
9 15476
10 23076518
 * */
	
	static int N,K,TC;
	static int D0[][], D1[][];//마지막자리가 0, 1인 케이스
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken()); 
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			D0 = new int[N+1][N+1];
			D1 = new int[N+1][N+1];
			
			/*
			 * D0[i][j] : 마지막자리수가 0이고, i자리수(N)의 인접한 비트의 갯수(K)가 j
			 * D1[i][j] : 마지막자리수가 1이고, i자리수(N)의 인접한 비트의 갯수(K)가 j
			 * 
			 * 그렇다면 D[i][j] = D0[i][j] + D1[i][j]로 표현할 수 있다
			 * 만약 D의 마지막 자리수가 0이라면 
			 * D0[i][j] = i-1자리의 인접한 비트의 수가 j이면서 마지막이 0인 경우의 수 
			 *          + i-1자리의 인접한 비트의 수가 j이면서 마지막이 1인 경우의 수 
			 *          = D0[i-1][j] + D1[i-1][j]
			 * 만약 D의 마지막 자리수가 1이라면 
			 * D1[i][j] = i-1자리의 인접한 비트의 수가 j이면서 마지막이 0인 경우의 수 
			 *          + i-1자리의 인접한 비트의 수가 j-1이면서(D의 마지막자리수가 1이므로 j-1) 마지막이 1인 경우의 수
			 *          = D0[i-1][j] + D1[i-1][j-1] 
			 * */
			//초기값
			D0[1][0] = 1;
			D1[1][0] = 1;
			for(int i=2;i<=N;i++){
				//인접한 비트의 수가 0인 경우초기화
				D0[i][0] = D0[i-1][0] + D1[i-1][0];
				D1[i][0] = D0[i-1][0];//j가 0미만인건 의미가 없으니깐 뺌
				for(int j=1;j<=i;j++){
					D0[i][j] =  D0[i-1][j] + D1[i-1][j];
					D1[i][j] =  D0[i-1][j] + D1[i-1][j-1];
				}
			}
			
			
			System.out.println(tmp+" "+(D0[N][K]+D1[N][K]));
		}
		br.close();
	}
}

/*
//인접한 비트의 개수
import java.util.Scanner;
public class source{
    static int k, n;
    static int[][][] cache;
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        double T1 = System.currentTimeMillis();
        cache = new int[101][100][2];
// DP... 
// 앞이 0, 1인경우
// 이번거까 0, 1일 때..
        cache[1][0][0] = 1;
        cache[1][0][1] = 1;
          
          
        for(int i=2;i<101;i++)
        {
            for(int j=0;j<i;j++) 
            {
                cache[i][j][0] = cache[i-1][j][0] + cache[i-1][j][1];
                if(j!=0)
                {
                    cache[i][j][1] = cache[i-1][j][0] + cache[i-1][j-1][1];
                }
                else
                {
                    cache[i][j][1] = cache[i-1][j][0];  
                }
            }
        }
           
          
        for(int t=0; t<T; t++) {
            sc.nextInt(); 
            n = sc.nextInt();
            k = sc.nextInt();
                  
            System.out.println(t+1 + " " +( cache[n][k][0] +cache[n][k][1]));
        }
        sc.close(); 
    }
}
*/
