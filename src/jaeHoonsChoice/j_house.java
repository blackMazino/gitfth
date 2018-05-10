package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class j_house {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
    	System.setIn(new FileInputStream("sample/j_house.txt"));
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
    	
        int x, n;
        int go, back;
        int sum, x_cal;
        int result1, result2;
        
 
        x = Integer.parseInt(bw.readLine());
        n = Integer.parseInt(bw.readLine());
 
        if (n > 0) {
            int list[] = new int[n];
 
            // 리스트에 넣고 정렬
            for (int i = 0; i < n; i++) {
                list[i] = Integer.parseInt(bw.readLine());
            }
            Arrays.sort(list);
 
            go = 0;
            back = n-1;
            result1 = result2 = 0;
            x_cal = x * 10000000;
 
            while (go < back) {
                sum = list[go] + list[back];
 
                // 정렬되어 있으므로, 처음으로 만나는 값이 |L1 - L2|가 가장 큰 값
                if (sum == x_cal) {
                    result1 = list[go];
                    result2 = list[back];
 
                    System.out.println("yes " + result1 + " " + result2);
 
                    break;
                }
 
                // 합계가 구멍의 넓이보다 크면 list의 이전값으로 이동
                else if (sum > x_cal) {
                    back--;
                }
 
                // 합계가 구멍의 넓이보다 작으면 list의 다음값으로 이동
                else if (sum < x_cal) {
                    go++;
                }
            }
 
            if (result1 == 0 || result2 == 0) {
                System.out.println("danger");
            }
        } else {
            System.out.println("danger");
        }
    }
 
}
