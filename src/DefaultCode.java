import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class DefaultCode {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;		
		st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int[] arr = new int [] {1,2,3,4,5,8,9,10};
		System.out.println(Arrays.binarySearch(arr, c));

		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
//		list.add(2);
//		list.add(3);
		list.add(4);
//		list.add(5);
		list.add(6);
		list.add(7);
//		list.add(8);
//		list.add(9);
		list.add(10);
		System.out.println(Arrays.binarySearch(list.toArray(), c));
		Collections.sort(list);
//		for(int tc=1; tc<=TC;tc++){
//			int z = Integer.parseInt(st.nextToken());
//		}
	}
}
