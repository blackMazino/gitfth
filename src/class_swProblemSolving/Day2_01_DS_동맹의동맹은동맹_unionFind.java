package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//자료구조DS
public class Day2_01_DS_동맹의동맹은동맹_unionFind {
/*
문제
낙성마을에는 N명의 사람이 있다. 편의상 각 사람들을 1번부터 N번까지 번호를 매기도록 하자. 처음 이 사람들은 서로를 모르기 때문에, '적대 관계'를 갖고 있다.
하지만 언제까지나 '적대 관계'로 살아갈 수는 없는 법이다. 이 마을의 사람들은 한 두명씩 '동맹 관계'를 맺기로 하였다. 당연히 어떤 사람 A와 B가 동맹 관계를 맺으면, 
자연스럽게 A의 동맹들과 B의 동맹들도 서로 동맹 관계를 맺게 된다. 이런 관계들이 많아지다보니 점점 더 복잡한 구조의 동맹 관계가 구성되게 되었다. 
누가 누구와 동맹 관계인지 확실히 알기 위해, 이런 관계를 찾아내는 프로그램을 작성하기로 하였다.
동맹 관계를 쉽게 알아내는 프로그램을 작성하시오.
입력
첫 번째 줄에 낙성마을의 사람의 수 N이 주어진다. (1 ≤ N ≤ 100,000)
두 번째 줄에 질의의 수 Q가 주어진다. (1 ≤ Q ≤ 200,000)
세 번째 줄부터 Q개의 줄에 걸쳐 질의가 주어진다. 각 질의는 다음의 형태 중 하나로 주어진다. (1 ≤ a, b ≤ N)
0 a b : a번 사람과 b번 사람이 동맹 관계를 맺었음을 의미하는 질의이다.
1 a b : a번 사람과 b번 사람이 동맹 관계에 있는지 물어보는 질의이다.
출력
1로 시작하는 모든 질의에 대해, 각 줄에 하나씩 동맹 관계가 아니면 0, 동맹 관계이면 1을 출력한다.
=====================
7
9
0 1 3
1 1 7
0 7 6
1 3 7
0 3 7
0 4 2
0 1 3
1 1 7
1 1 1
=====================
0
0
1
1
*/
	//static int TC;
	static int N,Q;
	static int answer = 0;
	static int par[];
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		TC = Integer.parseInt(br.readLine());
//		for(int i=1;i<=TC;i++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			par = new int[N+1];
			for(int i=1;i<=N;i++) par[i]=i;//초기화 : 자기자신만 동맹
			
			st = new StringTokenizer(br.readLine());
			Q = Integer.parseInt(st.nextToken());			
			for(int i=0; i<Q;i++){
				st = new StringTokenizer(br.readLine());
				int gb = Integer.parseInt(st.nextToken());
				int a,b;
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(gb == 0){
					setAlliance(a,b);//union
				}else{
					if(isAlliance(a,b)){//find
						System.out.println(1);
					}else{
						System.out.println(0);
					}
					
				}
			}					
//		}

		br.close();

	}
	
	//find
	private static boolean isAlliance(int a, int b) {
		boolean result = false;
		if(find(a) == find(b)){
			result = true;
		}
		return result;
	}
	
	private static int find(int n) {
		if(par[n]==n) return n;
		else return par[n]=find(par[n]);//경로압축 : 가장상위부모를 찾아서 셋팅
	}

	//union
	private static void setAlliance(int a, int b) {
		int pa=find(a);
		int pb=find(b);
		if(pa==pb) return;
		
		par[pb] = pa;//b의 par는 a이다		
	}

}

/*
//동맹의동맹은동맹
import java.io.*;
import java.util.*;
   
public class source {
    static int N, Q;
    static int[] A, par;
       
    static int cp(int n) {
        if (par[n] == n) return n;
        return par[n] = cp(par[n]);
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Q = Integer.parseInt(br.readLine());
        par = new int[N+1];
        for (int i=1;i<=N;i++) par[i] = i;
        while (Q-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (t == 0){
                int p = cp(a), q = cp(b);
                if (p == q) continue;
                par[q] = p;
            }else{
                int p = cp(a), q = cp(b);
                System.out.println(p == q ? 1 : 0);
            }
        }
    }
}
*/