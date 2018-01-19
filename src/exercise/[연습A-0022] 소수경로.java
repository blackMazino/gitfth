import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class PrimePath {
	static int T, A, B;
	static boolean[] is_prime;

		
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		is_prime = new boolean[10000];
		for (int i=1001;i<10000;i+=2){
		    is_prime[i] = true;
		    for (int j=2;j*j<=i;j++) if (i % j == 0){
		        is_prime[i] = false;
		        break;
		    }
		}


		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			int[] D = new int[10000];
			for (int i=0;i<10000;i++) D[i] = Integer.MAX_VALUE;
			Queue<Integer> que = new LinkedList<Integer>();
			que.add(A); D[A] = 0;
			while (!que.isEmpty()){
			    int q = que.poll();
			    for (int b=1;b<10000;b*=10){
			        for (int d=0;d<10;d++){
			            int v = q / b / 10 * b * 10 + q % b + b * d;
			            if (!is_prime[v] || D[v] < Integer.MAX_VALUE) continue;
			            D[v] = D[q]+1;
			            que.add(v);
			        }
			    }
			}
			System.out.println("#"+tc+" "+D[B]);
			
		}
		

	}
}