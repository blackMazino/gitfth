package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
// 처음부터 스택에 쌓아가면서 확인하고, 레이저를 받을 가능성이 있는 탑만 스택에 쌓는다.
// 1. 스택이 비어있으면 0 출력
// 2. 스택의 맨 위부터 입력받은 탑을 비교해가면서 탑의 높이가 더 크면 스택은 pop
// 3. 자신을 푸시한다.
// 스택의 맨 위에 값이 남아있으면 해당 값을 출력하여 가장 높은 탑을 확인한다.
 
// 빠른 입출력이 없으면 시간초과 발생
 
public class top {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
 
        int n;
        int comp[];
 
//      BufferedReader br = new BufferedReader(new FileReader("tower_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
 
        n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
 
        comp = new int[n + 1];
 
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            comp[i] = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && comp[stack.peek()] < comp[i]) {
                stack.pop();
            }
 
            if (stack.isEmpty()) {
                bw.write("0 ");
            } else {
                bw.write(stack.peek() + " ");
            }
            stack.push(i);
        }
        bw.write("\n");
        bw.flush();
    }
}