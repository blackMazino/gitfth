package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 그래프_BFSDFS_KOITP_피크닉 {
/*
https://koitp.org/problem/USACO_2006DEC_SILVER_COWPICNIC/read/
문제없어짐 : http://snacky.tistory.com/54

소들은 피크닉을 갈 예정이다! 
각 존의 K(1 <= K <= 100)마리의 소들은 N(1 <= N <= 1,000) 개의 목초지중 하나의 목초지에서 풀을 뜯고 있다. 
이 목초지들을 목초지1 … 목초지 N이라고 명명하자. 그 목초지들은 M (1 <= M <= 10,000) 개의 단방향 길로 연결되어 있다. 
(어떠한 길도 출발지와 도착지가 같지 않다.)
소들은 그들의 피크닉동안 같은 목초지에서 모이기를 원한다. 
하지만 몇마리의 소들은 모든 목초지에 도달할 수 없을 가능성이 있다.(단방향 도로이기 때문에) 
소들을 도와 얼마나 많은 목초지에서 모든 소들이 모일 수 있는지 계산해주자.

첫번째 줄은 세개의 정수가 공백으로 구분되어 입력되어진다. :K,N,M
2번째줄부터 K+1번째 줄까지는 K마리의 소들의 처음 풀을 뜯고있는 위치가 각 줄에 하나씩 주어진다.
K+2번째 줄부터 M+K+1번째 줄까지는 단방향 도로의 정보 시작점S 와 끝점E가 입력으로 주어진다.

모든 소가 모일 수 있는 가능한 목초지의 수를 출력해주자

2 4 4
2
3
1 2
1 4
2 3
3 4

2
*/
	
	static int K,N,M;
	static int [] cow;
	static ArrayList<Integer>[] path;
	static ArrayList<CowCount> list;
	static int [][] relation;
	static boolean [] tmp;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) );        	
        StringTokenizer st = new StringTokenizer( br.readLine() ) ;
        
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        cow = new int [K+1];
        for(int i=1;i<=K;i++) {
        	st = new StringTokenizer(br.readLine()) ;
        	cow[i] = Integer.parseInt(st.nextToken());//소들의시작점
        }
        
        path = new ArrayList [N+1];
        list = new ArrayList<>();
        tmp = new boolean[N+1];
        list.add(new CowCount(0,tmp));
        for(int i =1;i<=N;i++){
        	path[i] = new ArrayList<>();
        	tmp = new boolean[N+1];
        	list.add(new CowCount(0,tmp));//idx가 목초지 번호이다.
        }
        for(int i=1;i<=M;i++) {
        	st = new StringTokenizer(br.readLine()) ;
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	path[s].add(e);//단방향
        }	
        relation = new int [N+1][K+1];//N개 목초지, 0번 idx에는 다녀간 소들의 합, 1~K번 소가 다녀갔는지, 다녀갔다면 1, 아니면 0
        
        int answer = 0;
        for(int i=1;i<=K;i++){//소를 이동시켜보자
        	//bfs(i);
        	dfs(i,cow[i]);
        }
        for(int i=1;i<=N;i++){
        	if(list.get(i).cowCnt==K){
        		answer++;
        	}
        }
        System.out.println(answer);
        
	}
	private static void dfs(int k, int n) {//소, 목초지
		if(!list.get(n).cowVisit[k]){
			list.get(n).cowVisit[k]= true;
			list.get(n).cowCnt++;
		}
		for(int x : path[n]){
			if(!list.get(x).cowVisit[k]){
				list.get(x).cowVisit[k] = true;				
				list.get(x).cowCnt++;	
				dfs(k,x);
			}
		}
		
	}
	private static void bfs(int k) {
		LinkedList<Integer> q = new LinkedList<>();
		q.add(cow[k]);
		
		while(!q.isEmpty()){
			int t = q.removeFirst();
			if(!list.get(t).cowVisit[k]){//n목초지에 i의 소가 다녀가지 않았다면
				list.get(t).cowVisit[k] = true;				
				list.get(t).cowCnt++;
			}
			for(int n : path[t]){
				if(!list.get(n).cowVisit[k]){
					list.get(n).cowVisit[k] = true;				
					list.get(n).cowCnt++;	
					q.add(n);
				}
			}		
		}		
	}

}


class CowCount{
	public CowCount( int cowCnt, boolean[] cowVisit) {
		this.cowCnt = cowCnt;
		this.cowVisit = cowVisit;
	}	
	int cowCnt;
	boolean[] cowVisit;
}