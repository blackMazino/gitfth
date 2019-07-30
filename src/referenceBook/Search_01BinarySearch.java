package referenceBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Search_01BinarySearch {

 /* 입력
  * 2 (테스트케이스)
  * 10 5
  * 2 3 4 5 6 7 9 11 14 17 20 //데이터
  * 1 6 10 20 25 //쿼리
  * 15 5
  * 2 7 9 12 14 17 20 25 30 33 38 41 46 57 80
  * 2 20 100 30 80
  * 
  * 
  * 출력
  * #1 -1 3(4?) -1 9 -1
  * #2  0 6 -1 8 14
  * 
  * */


	static int N,M,arr[];
	
	static int binarySearch(int size, int key){
		int lb =-1, ub = size-1, m;
		while(lb+1<ub){
			m = lb + (ub-lb)/2;
			if(arr[m]>=key) ub = m;
			else lb = m;
		}		
		return ub>=size? -1: arr[ub]==key ? ub : -1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());		
		for(int tc=1; tc<=TC;tc++){
			st = new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());//array size
			int M=Integer.parseInt(st.nextToken());// 
			arr = new int [N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++){				
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			System.out.print("#"+tc);
			for(int i=0;i<M;i++){
				
				int key = Integer.parseInt(st.nextToken());//
				System.out.print(" "+binarySearch(N, key));
			}
			System.out.println();
		}
		
		
	}

}
