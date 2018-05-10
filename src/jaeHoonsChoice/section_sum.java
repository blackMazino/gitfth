package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class section_sum {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        int n, q;
        long sum = 0;
 
//      BufferedReader br = new BufferedReader(new FileReader("section_sum_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        StringTokenizer st;
 
        n = Integer.parseInt(br.readLine());
        q = Integer.parseInt(br.readLine());
 
        // 2의 n제곱으로 tree를 구성해야 하므로, 실제 트리 개수 구하기.
        int a = 1;
        for (int i = 2;; i *= 2) {
            if (n <= i)
                break;
            a++;
        }
 
        int B = (1 << a) - 1; // B+1부터 트리가 구성된다.
        long tree[] = new long[1 << (a + 1)];
 
        // 트리의 맨 아래 노드 채우기
        for (int i = B + 1; i <= B + n; i++) {
            tree[i] = i - B;
        }
 
        // 트리의 위쪽 부분 채우기
        int len = tree.length - 1;
        for (int i = len; i > 1; i -= 2) {
            tree[(i - 1) / 2] = tree[i] + tree[i - 1];
        }
 
        // 구간합 더하기
        // L <= R 일 때
        // L이 왼쪽 자식일 경우 부모노드로 이동
        // L이 오른쪽 자식일 경우 합산에 더하고 오른쪽으로 옮긴 다음 부모노드로 올림
        // R이 왼쪽 자식일 경우 합산에 더하고 왼쪽으로 옮긴 다음 부모노드로 올림
        // R이 오른쪽 자식일 경우 부모노드로 이동
        for (int tc = 1; tc <= q; tc++) {
            int l = 0, r = 0;
            int index = 0, val = 0;
            long temp = 0;
             
            st = new StringTokenizer(br.readLine());
            // 0으로 시작 : 숫자 변경
            if (Integer.parseInt(st.nextToken()) == 0) {
                // 숫자를 변경하고, 해당 부모노드의 합을 변경한다.
                index = B + Integer.parseInt(st.nextToken());
                val = Integer.parseInt(st.nextToken());
 
                temp = tree[index];
                tree[index] = val;
 
                while (index > 1) {
                    index = index / 2;
                    tree[index] = tree[index] + val - temp;
                }
            }
            // 1로 시작 : 질의에 답변
            else {
                l = B + Integer.parseInt(st.nextToken());
                r = B + Integer.parseInt(st.nextToken());
 
                while (l <= r) {
                    // l이 왼쪽 자식일 때
                    if (l % 2 == 0) {
                        l = l / 2;
                    }
                    // l이 오른쪽 자식일 때
                    else {
                        sum += tree[l];
                        l = (l + 1) / 2;
                    }
 
                    // r이 왼쪽 자식일 때
                    if (r % 2 == 0) {
                        sum += tree[r];
                        r = (r - 1) / 2;
                    }
                    // r이 오른쪽 자식일 때
                    else {
                        r = r / 2;
                    }
                }
 
                bw.write(sum + "\n");
                sum = 0;
            }
        }
        bw.flush();
    }
 
}