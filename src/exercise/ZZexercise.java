package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ZZexercise {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=1;i<=tc;i++){
			st = new StringTokenizer(br.readLine());
			nums.add(Integer.parseInt(st.nextToken()));			
		}
		List <Integer> cardiSortedLst = cardinalitySort(nums);
		for(int r:cardiSortedLst){
			System.out.println(r);
		}
	}

	private static List<Integer> cardinalitySort(ArrayList<Integer> nums) {
		List<Integer> result = new ArrayList();
		Collections.sort(nums);
		ArrayList<Integer>[] con = new ArrayList[10000];
		for(int i=0;i<10000;i++){
			con[i] = new ArrayList<>();
		}
		for(int tmp:nums){
			String a = Integer.toBinaryString(tmp);
//			System.out.println("num:"+tmp+", binary :"+a);
			int cnt =0;
			for(int i=0;i<a.length();i++){
				
				if(a.charAt(i)=='1') cnt++;
			}
//			System.out.println(tmp +","+ cnt);
			con[cnt].add(tmp);
		}
		for(int i=0;i<10000;i++){
			if(con[i].size()>0){
				for(int t:con[i]){
					result.add(t);
				}
			}
		}
		
		
		return result;
	}

}
