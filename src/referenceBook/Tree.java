package referenceBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Tree {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<TC;tc++){
			
		}
		
		TreeSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}			
		});
		TreeMap<Integer,Integer> map = new TreeMap<Integer, Integer>(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}			
		});
		
		int keys[] = {1,4,2,5,7,3,1};
		int values[] = {2,3,1,5,1,4,1};
		for(int i=0;i<keys.length;i++){
			set.add(keys[i]);
			map.put(keys[i], values[i]);
		}
		
		if(set.contains(1)) set.remove(1);
		
		for(int key : set){
			System.out.print(key + " ");
		}
		System.out.println();
		
		if(map.containsKey(5)) map.remove(5);
		
		Iterator<Integer> it = map.keySet().iterator();
		while(it.hasNext()){
			Integer key = it.next();
			Integer value = map.get(key);
			
			System.out.print("("+ key + ", "+value +")");
		}
	}
}
