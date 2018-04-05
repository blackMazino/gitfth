package referenceBook;

import java.util.Scanner;

public class Search_02ParametricSearch2 {
	/*
	 * 목재가 N개 있고, 목재 절단기가 있다. 예를 들어 목재 절단기의 폭을 10으로 설정하면 목재를 길이 10만큼 절단할 수 있다.
	 * 절단하고 남은 목재 길이의 합을 H라고 했을 때, 주어지는 목재 길이의 합 만큼 얻기 위해 설정해야 하는 목재 절단기 폭의 최대값을
	 * 구하라 [입력] 2 //전체 테스트케이스 개수 4 7 //목재 개수, 절단하고 남은 목재 길이의 합 11 14 10 17 //각
	 * 목재 사이즈 5 20 4 42 40 26 46
	 * 
	 * [출력] #1 12 #2 36 ▷ 첫 번째 테스트 케이스 설명: 목재 절단기의 폭을 12로 설정하면 두 번째 목재에 14를 12만큼
	 * 자르면 2가 남고, 네 번째 목재 17을 12만큼 자르면 5가 남게 되어 남은 목재 길이 합이 7이 된다.
	 */
	static int N;
	static int H;
	static int MAXSIZE = 100000000;
	static int Wood[];

	static boolean fmax(int size) {

		int h = 0;
		for (int i = 0; i < N; i++)
			h += (0 > (Wood[i] - size) ? 0 : (Wood[i] - size));

		if (h >= H)
			return true;

		return false;

	}

	public static void main(String arg[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			H = sc.nextInt();
			Wood = new int[N];

			for (int i = 0; i < N; i++)
				Wood[i] = sc.nextInt();

			int lb = -1, ub = MAXSIZE, m;

			while (lb + 1 < ub) {

				m = lb + (ub - lb) / 2;
				if (fmax(m))
					lb = m;
				else
					ub = m;

			}

			System.out.println("#" + test_case + " " + lb);

		}
		sc.close();
	}
}

/*
 * //min int lb=-1, ub = MAXSIZE, m;
 * 
 * while(lb+1 < ub)
 * 
 * {
 * 
 * m = lb+(ub-lb)/2;
 * 
 * if(fmin(m)) ub = m;
 * 
 * else lb = m;
 * 
 * }
 * 
 * 
 * 
 * return ub;
 * 
 * 
 * //max int lb=-1, ub = MAXSIZE, m;
 * 
 * while(lb+1 < ub)
 * 
 * {
 * 
 * m = lb+(ub-lb)/2;
 * 
 * if(fmax(m)) lb = m;
 * 
 * else ub = m;
 * 
 * }
 */
