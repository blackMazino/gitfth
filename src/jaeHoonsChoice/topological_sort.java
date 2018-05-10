package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class topological_sort {
 
    static int V, E;
    static ArrayList<Integer> list[];
    static boolean visit[];
    static ArrayList<Integer> dfs_list;
    static Stack<Integer> stack;
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new FileReader("topological_sort_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
 
        list = new ArrayList[V + 1];
 
        // 인접 리스트 초기화
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }
 
        int a = 0, b = 0;
        // 인접 리스트에 값 넣기
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
 
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
             
            list[a].add(b);
        }
         
        dfs_list = new ArrayList<>();
        visit = new boolean[V+1];
        stack = new Stack<>();
 
        for(int i = 1; i <= V; i++) {
            if(!visit[i]) {
                dfs(i);  // 방문하지 않은 곳을 찾아서 dfs 돌린다.
            }
        }
 
        while(!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        bw.write("\n");
        bw.flush();
    }
 
    private static void dfs(int start) {
        // TODO Auto-generated method stub
        dfs_list.add(start);
        visit[start] = true;
         
        for(int t : list[start]) {
            if(!visit[t]) {
                dfs(t);
            }
        }
        stack.push(start);  // 스택에 넣어서 결과 출력
    }
 
}