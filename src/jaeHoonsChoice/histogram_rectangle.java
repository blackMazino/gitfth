package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class histogram_rectangle {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        int n;
        long result;
         
//      BufferedReader br = new BufferedReader(new FileReader("histogram_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         
        StringTokenizer st;
         
        while( 0 == 0 ) {
            st = new StringTokenizer(br.readLine());
             
            n = Integer.parseInt(st.nextToken());
             
            if(n == 0) break;
             
            int[] histogram = new int[n + 1];
            for (int i = 1; i <= n ; i++) {
                histogram[i] = Integer.parseInt(st.nextToken());
            }
             
            int L[] = new int[n+1];
            int R[] = new int[n+1];
            int temp[] = new int[n+1];
             
            Stack<Integer> stack = new Stack<>();
             
            // 작은 값을 만나면 스택에서 빼기
            // 왼쪽으로 움직일 수 있는 개수 확인하기 
            for (int i = 1; i <= n; i++) {
                while(!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                    stack.pop();
                }
                if(stack.isEmpty()) {
                    L[i] = i - 1;
                } else {
                    L[i] = i - stack.peek() - 1;
                }
                stack.push(i);
            }
             
            // 오른쪽으로 움직일 수 있는 개수 확인하기
            // 거꾸로 확인할 것
            int tmp = 1;
            for (int i = n; i >= 1; i--) {
                R[tmp] = histogram[i];
                tmp++;
            }
            stack = new Stack<>();
            for (int i = 1; i <= n; i++) {
                while(!stack.isEmpty() && R[stack.peek()] >= R[i]) {
                    stack.pop();
                }
                if(stack.isEmpty()) {
                    temp[i] = i - 1;
                } else {
                    temp[i] = i - stack.peek() - 1;
                }
                stack.push(i);
            }
             
            tmp = 1;
            R = new int[n+1];
            for (int i = n; i >= 1; i--) {
                R[tmp] = temp[i];
                tmp++;
            }   
             
            result = Long.MIN_VALUE;
            for(int i = 1; i <= n; i++) {
                long area = (long)(L[i] + R[i] + 1) * (long)histogram[i];
                result = Math.max(result, area);
            }
            bw.write(result + "\n");
        }
        bw.flush();
    }
 
}