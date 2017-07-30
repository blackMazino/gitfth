package class_algorithmNMath.DailyHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day2_Dictionary {

/*
����
��ȣ�� �Կ��̴� 212ȣ���� ���ڿ��� ���� �����ϰ� �ִ�. ������ ������ ��ȣ�� �Կ��̿��� Ư�� ������ �־���. Ư��  ������ Ư���� ���ڿ��� �̷�� �� ������ ����� ���̴�. ������ ���ϵ� �ִ� ��� ���ڿ��� N���� "a"�� M���� "z"�� �̷���� �ִ�. �׸��� �ٸ� ���ڴ� ����. �������� ���ĺ� ������� ���ϵǾ� �ִ�.

�Կ��̴� ������ �ϼ�������, ��ȣ�� ������ �ϼ����� ���ߴ�. ��ȣ�� �ڽ��� ������ ������ ���ؼ� �Կ����� ������ ���� �����ϱ�� �ߴ�. ��ȣ�� �Կ��̰� �ڸ��� ��� ���̿� ���� ������ ������ �ϱ� ������, ���ڿ� �ϳ��� ã�� �����ۿ� ����.

N�� M�� �־����� ��, �Կ����� �������� K��° ���ڿ��� �������� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�Է�
ù° �ٿ� N, M, K�� ������� �־�����. N�� M�� 100���� �۰ų� ���� �ڿ����̰�, K�� 1,000,000,000���� �۰ų� ���� �ڿ����̴�.

���
ù° �ٿ� �Կ����� �������� K��° ���ڿ��� ����Ѵ�. ���� �Կ����� ������ ���ϵǾ� �ִ� ���ڿ��� ������ K���� ������ -1�� ����Ѵ�
 * */
	static int kMax = 1000000000;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;		
//		st = new StringTokenizer(br.readLine());		
//		int TC = Integer.parseInt(st.nextToken());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());//cnt of a
			int M = Integer.parseInt(st.nextToken());//cnt of z
			int K = Integer.parseInt(st.nextToken());//K��° ���ڿ�
					
			String str = getStrByQueue(N,M,K);			
			System.out.print(str);
//		}	
	}

	private static String getStrByQueue(int N, int M, int K) {
		int L = N+M;
		Queue<Integer> q = new LinkedList<Integer>(); 	
		if(Math.pow(2, L) >= K){//���ڿ� ������ K���ٰ��ų� ���� ã���� �ִ� ���ٸ� -1			
			int C[][] = new int [L+1][L+1];// N,M <100			
			C = makePascalsTri(L);//�Ľ�Į�� �ﰢ�� �����
			
			if(C[L][M] < K){
				return "-1";
			}
			
			//print(C);
			//queue ���				
			for(int i=1;i<=L;i++){
				if(C[L-i][M] >= K) continue;
				q.offer(i);
				K-=C[L-i][M];
				M--;
			}
			String str = "";
			for(int i=1;i<=L;i++){
				if(!q.isEmpty() && q.peek() ==i){
					//System.out.print("z"); 
					str += "z";
					q.poll();
				}
				else {
					//System.out.print("a");
					str += "a";
				}
			}
			return str;				
		}else{
			return "-1";	
		}
	}

	private static int[][] makePascalsTri(int L) {
		int C[][] = new int [L+1][L+1];	
		C[0][0] = 1;
		for(int i=1;i<=L;i++){
			C[i][0]=1;//ù��° ���� 1
			for(int j=1;j<=i;j++){
				C[i][j] = Math.min(kMax, C[i-1][j]+C[i-1][j-1]);
			}
		}
		return C;
	}

	private static void print(int[][] c) {
		for(int i=1;i<c.length;i++){			
			for(int j=1;j<c.length;j++){
				System.out.print(c[i][j]);
			}	
			System.out.println();
		}	
	}
/*	
	#include "stdio.h"
	#include <queue>
	#define min(x, y) ((x)>(y)?(y):(x))
	using namespace std;
	int N, M, K, L;
	int C[201][201];
	queue<int> zpos;
	int main()
	{
	    scanf("%d %d %d", &N, &M, &K);
	    L = N + M;
	    C[0][0] = 1;
	    for (int i = 1; i <= L; i++)
	    {
	        C[i][0] = 1;
	        for (int j = 1; j <= i; j++) C[i][j] = min(1e9, C[i - 1][j] + C[i - 1][j - 1]);
	    }
	    if (C[L][M] < K)
	    {
	        printf("-1\n");
	        return 0;
	    }

	    for (int i = 1; i <= L; i++)
	    {
	        if (C[L - i][M] >= K) continue;
	        zpos.push(i);
	        K -= C[L - i][M];
	        M--;
	    }
	    for (int i = 1; i <= L; i++)
	    {
	        if (!zpos.empty() && zpos.front() == i) printf("z"), zpos.pop();
	        else printf("a");
	    }
	}
*/
}
