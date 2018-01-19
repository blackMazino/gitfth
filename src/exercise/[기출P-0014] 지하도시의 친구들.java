import java.io.*;
import java.util.*;
  
public class Solution {
    static int T, N, K, seq;
    static int[] link, visit, vcnt;
    public static void main(String[] args) throws Exception 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
              
            link = new int[N+1]; // 연결된 다음 노드
            vcnt = new int[N+1]; // 방문 가능한 총 노드수
            visit = new int[N+1]; // 방문한 순번
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++)
                link[i] = Integer.parseInt(st.nextToken()); 
  
            seq = 1; // 1번 노드부터 시작해서 방문한 순번을 기록
            for(int i=1; i<=N; i++)
                if(visit[i] == 0)
                    solve(i); // 아직 방문하지 않은 노드를 시작점으로 사이클 or 기존 방문 노드를 찾는다.
              
            long ans = 0;
            for(int i=1; i<=N; i++)
                ans += Math.min(vcnt[i], K); // 방문 가능수가 K를 넘어가는 노드는 K로 카운트
  
            System.out.println("#" + tc + " " + ans);       
        }
    }
      
    static void solve(int start)
    {       
        Stack<Integer> stack = new Stack<Integer> ();
        int here = start;
        while(true) 
        {
            if(visit[here] != 0) // 이전에 방문했던 노드인 경우
            {
                if(visit[here] >= visit[start]) // 이번 호출에서 사이클을 이룬 경우
                {
                    int cnt = seq - visit[here]; // 사이클을 이루는 노드의 수
                    for(int i=0; i<cnt; i++) 
                        vcnt[stack.pop()] = cnt - 1; // 사이클 내의 노드는 사이클의 노드수 - 1
                }
                break;
            }
  
            stack.add(here); // 방문한 경로 스택에 저장
            visit[here] = seq++; // 방문한 순번 저장
            here = link[here]; // 다음 방문지로 이동
        }
          
        while(!stack.empty()) // 사이클이 아닌 경로의 방문 가능 노드 수 계산
        {
            int top = stack.pop();
            vcnt[top] = vcnt[link[top]] + 1; // 기존 방문 노드의 방문 가능수 + 1
        }
    }
}
