package class_swProblemSolving;

import java.util.Scanner;

public class referenceBook_parametricSearch1 {

	/*
	 * 동영상 강의파일을 USB메모리에 복사하고자 한다. 동영상 강의의 순서가 변경되면 학습자가 혼란을 느끼게 되므로 순서대로
	 * USB메모리에 복사해야 한다. M개의 같은 크기인 USB메모리에 나누어 복사한다고 할 때, USB메모리의 최소 사이즈를 구하라.
	 * [입력] 2 //전체 테스트케이스 개수 9 3 //동영상 강의 개수, USB메모리 개수 1 2 3 4 5 6 7 8 9 //각
	 * 동영상 강의 사이즈 15 5 122 243 157 145 12 19 30 37 96 88 12 3 10 174 147
	 * 
	 * [출력] #1 17 #2 321
	 * 
	 * 
	 * ▷ 첫 번째 테스트 케이스 설명: 1번 USB메모리에 1,2,3,4,5 동영상강의를, 2번 USB메모리에 6,7 동영상강의를, 3번
	 * USB메모리에 8,9를 넣으면 각 USB메모리의 크기는 15,13,17이 된다. 크기는 모두 같아야 하므로 17이 되며 17보다
	 * 작은 크기의 USB메모리는 있을 수 없다.
	 */

	static int N;
	static int M;
	static int MAXSIZE = 100000000;
	static int Video[];

	public static void main(String arg[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++){
			N = sc.nextInt();
			M = sc.nextInt();
			Video = new int[N];

			for (int i = 0; i < N; i++)
				Video[i] = sc.nextInt();

			int lb = -1, ub = MAXSIZE, m;
			while (lb + 1 < ub) {

				m = lb + (ub - lb) / 2;
				if (fmin(m))
					ub = m;
				else
					lb = m;

			}

			System.out.println("#" + test_case + " " + ub);

		}

		sc.close();

	}

	static boolean fmin(int size) {

		int usbCount = 1;
		int usbSize = 0;
		for (int i = 0; i < N; i++) {

			if (usbSize + Video[i] > size) {
				
				usbCount++;
				usbSize = Video[i];
				if (usbSize > size || usbCount > M)
					return false;

			}

			else
				usbSize += Video[i];

		}

		return true;

	}
}
