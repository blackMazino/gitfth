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
              
            link = new int[N+1]; // ����� ���� ���
            vcnt = new int[N+1]; // �湮 ������ �� ����
            visit = new int[N+1]; // �湮�� ����
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++)
                link[i] = Integer.parseInt(st.nextToken()); 
  
            seq = 1; // 1�� ������ �����ؼ� �湮�� ������ ���
            for(int i=1; i<=N; i++)
                if(visit[i] == 0)
                    solve(i); // ���� �湮���� ���� ��带 ���������� ����Ŭ or ���� �湮 ��带 ã�´�.
              
            long ans = 0;
            for(int i=1; i<=N; i++)
                ans += Math.min(vcnt[i], K); // �湮 ���ɼ��� K�� �Ѿ�� ���� K�� ī��Ʈ
  
            System.out.println("#" + tc + " " + ans);       
        }
    }
      
    static void solve(int start)
    {       
        Stack<Integer> stack = new Stack<Integer> ();
        int here = start;
        while(true) 
        {
            if(visit[here] != 0) // ������ �湮�ߴ� ����� ���
            {
                if(visit[here] >= visit[start]) // �̹� ȣ�⿡�� ����Ŭ�� �̷� ���
                {
                    int cnt = seq - visit[here]; // ����Ŭ�� �̷�� ����� ��
                    for(int i=0; i<cnt; i++) 
                        vcnt[stack.pop()] = cnt - 1; // ����Ŭ ���� ���� ����Ŭ�� ���� - 1
                }
                break;
            }
  
            stack.add(here); // �湮�� ��� ���ÿ� ����
            visit[here] = seq++; // �湮�� ���� ����
            here = link[here]; // ���� �湮���� �̵�
        }
          
        while(!stack.empty()) // ����Ŭ�� �ƴ� ����� �湮 ���� ��� �� ���
        {
            int top = stack.pop();
            vcnt[top] = vcnt[link[top]] + 1; // ���� �湮 ����� �湮 ���ɼ� + 1
        }
    }
}
