package referenceBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch {

 //N은 리스트 크기, M은 질문 수

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
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<TC;tc++){
			int N=Integer.parseInt(br.readLine());//array size
			int M=Integer.parseInt(br.readLine());// 
			arr = new int [N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
			
			for(int i=0;i<M;i++){
				int key = Integer.parseInt(br.readLine());//
				binarySearch(N, key);
			}
			
		}
		
		
	}

}