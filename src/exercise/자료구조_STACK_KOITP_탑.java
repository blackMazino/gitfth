package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 자료구조_STACK_KOITP_탑 {

	static int N;
	static int[] tower;
	static LinkedList<Integer> stack;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		tower = new int[N+1];
		stack = new LinkedList<Integer>();
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++){
			tower[i] = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty() && stack.getLast() < tower[i]){
				stack.removeLast();
			}
			
			int receiveTower = 0;
			if(!stack.isEmpty()){
				receiveTower = stack.getLast();
			}
			bw.write(receiveTower + " ");
			stack.addLast(i);//다음 타워랑 비교해야되니 현재 타워를 담는다
		}
		bw.write("\n");
		bw.flush();
		bw.close();
	}

}
