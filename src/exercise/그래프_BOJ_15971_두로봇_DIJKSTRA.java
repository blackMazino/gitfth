package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 그래프_BOJ_15971_두로봇_DIJKSTRA {
//Dijstra로 풀었는데 틀렸단다 모르겠다 ㅠ
	
/*https://www.acmicpc.net/problem/15971
5 1 5
1 2 1
2 3 2
3 4 3
4 5 4
=====
6

9 1 9
1 2 8
2 3 6
2 4 5
2 5 10
9 5 6
6 5 14
6 7 7
8 6 7
=====
14
*/

	static int N, r1, r2;//1<=N<=100,000
	static long d[], answer;
	static ArrayList<Integer> con[], conw[];
	static CNode[] route; // route[2].v = 1 ; 1->2 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r1= Integer.parseInt(st.nextToken());
		r2= Integer.parseInt(st.nextToken());
		con = new ArrayList[N+1];
		conw = new ArrayList[N+1];
		for(int i=1;i<=N;i++){
			con[i] = new ArrayList<>();
			conw[i] = new ArrayList<>();
		}
		for(int n=1;n<N;n++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			con[a].add(b); con[b].add(a);
			conw[a].add(c); conw[b].add(c);
		}
		
		//Dijkstra
		d = new long[N+1];
		for(int n=1;n<=N;n++){
			 d[n] = Long.MAX_VALUE;
			 if(n==r1) d[n]= 0;			
		}			
		
		route = new CNode[N+1];
		route[r1] = new CNode(0, 0);
		
		PriorityQueue<CNode> pQ = new PriorityQueue<>();
		pQ.add(new CNode(0, r1));
		while(!pQ.isEmpty()){
			CNode p = pQ.poll();
			long nowD = p.d;
			int nowV = p.v;
			if(d[nowV] < nowD) continue;
			for(int i=0;i<con[nowV].size();i++){
				int nextV = con[nowV].get(i);
				int nextD = conw[nowV].get(i);
				if(d[nextV]>d[nowV]+nextD){
					//경로 저장
					route[nextV] = new CNode(nextD,nowV);
					
					d[nextV]=d[nowV]+nextD;
					pQ.add(new CNode(d[nextV], nextV));
				}
			}
		}
		
		long totDist = d[r2];
		//하나의 edge를 두고 서로다른 방에 있으면 통신이 가능하다.
		//그러므로 r2까지 가는 경로중 가장 긴 edge를 찾아 그 edge양끝의 방에 위치하면 된다.
		
		int eV = r2;
		
		int sV = route[eV].v;
		long dis = route[eV].d;
		long maxE = 0;
//		while(eV != r1){
		while(true){
			if(sV == r1) break; //r1으로 오는 길을 저장하지 않았다. 다온거다
			maxE = Math.max(maxE, dis);
			eV = sV;
			sV = route[eV].v;
			dis =  route[eV].d;
		}
		
		answer = totDist - maxE;
		System.out.println(answer);
	}

}
class CNode implements Comparable<CNode>{
	public CNode(long d, int v) {
		super();
		this.d = d;
		this.v = v;
	}
	long d;
	int v;
	@Override
	public int compareTo(CNode o) {
		// TODO Auto-generated method stub
		return Long.compare(d, o.d);
	}
}

/*
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

typedef struct
{
    vector <int> next, cost;
} room;

typedef struct
{
    int cost, place;
} pqelem;

struct compare
{
    bool operator () (pqelem a, pqelem b)
    {
        if (a.cost > b.cost)
            return true;
        else
            return false;
    }
};

int main()
{
    int n, robotA, robotB, size;
    vector <room> input;
    vector <int> ans;
    priority_queue <pqelem, vector <pqelem>, compare> pq;
    priority_queue < int, vector <int> > find;
    
    scanf("%d %d %d", &n, &robotA, &robotB);
    
    if (n == 1 || robotA == robotB)
    {
        printf("0");
        return 0;
    }
    
    input.resize(n+1);
    ans.resize(n+1);
    
    for (int i = 1; i <= n-1; i++)
    {
        int room1, room2, cost;
        scanf("%d %d %d", &room1, &room2, &cost);
        input[room1].next.push_back(room2);
        input[room1].cost.push_back(cost);
        input[room2].next.push_back(room1);
        input[room2].cost.push_back(cost);
        ans[i] = 1000000000;
    }
    ans[n] = 1000000000;
    ans[robotA] = 0;
    
    pqelem temp;
    temp.cost = 0;
    temp.place = robotA;
    pq.push(temp);
    
    while (pq.size())
    {
        int cost = pq.top().cost, place = pq.top().place;
        pq.pop();
        
        size = input[place].next.size();
        
        for (int i = 0; i < size; i++)
        {
            if (cost + input[place].cost[i] < ans[input[place].next[i]])
            {
                ans[input[place].next[i]] = cost + input[place].cost[i];
                temp.cost = ans[input[place].next[i]];
                temp.place = input[place].next[i];
                pq.push(temp);
            }
        }
    }
    
    int cur = robotB;
    
    while (ans[cur])
    {
        for (int i = 0; i < input[cur].next.size(); i++)
        {
            if (ans[input[cur].next[i]] < ans[cur])
            {
                find.push(input[cur].cost[i]);
                cur = input[cur].next[i];
                break;
            }
        }
    }
    
    printf("%d", ans[robotB] - find.top());
    return 0;
}
*/