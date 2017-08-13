package class_algorithmNMath.Exercise;

import java.util.Scanner;

public class BinarySearch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		int N = sc.nextInt();
		int M = sc.nextInt();		
		int []A = new int [N+1];
		for(int i=1;i<=N;i++) A[i] = sc.nextInt();
		int target = getTByBS(N,M,A);
		System.out.println("answer : "+target);
		sc.close();
	}

	//N개의 배열의 M번째 가운데수
	private static int getTByBS(int N, int M, int [] A) {
		int low=1, high=N, mid=0;		
		int ans = 1;
		while(low<=high){
			mid = (low+high)/2;
			if(A[mid] < M) low = mid + 1;
			else{
				ans = mid;
				high = mid-1;
			}
			System.out.println(ans);
		}
		 return ans;//목찾음
	}
/*
int low = 1, high = N, mid;
int A[100];
int M, ans;
while (low <= high)
{
    mid = (low+high)/2;

    if (A[mid] > M) high = mid - 1;
    else if (A[mid] <= M) {
        low = mid + 1;
        ans = mid;
    }
    //else break;
}
if (low <= high) mid;
else //못찾음
*/
}
