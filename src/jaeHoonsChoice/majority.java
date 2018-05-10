package jaeHoonsChoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
 
public class majority {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        int n;
        int temp = 0;
        int result = 0;
        int next = 0;
        ArrayList list = new ArrayList();
 
//      BufferedReader bw = new BufferedReader(new FileReader("number_sample.txt"));
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
 
        n = Integer.parseInt(bw.readLine());
 
        int num[] = new int[n + 1];
 
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(bw.readLine());
        }
 
        Arrays.sort(num);
 
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                next = num[i];
                list.add(num[i]);
            } else {
                if (next == num[i]) {
                    list.add(num[i]);
                } else if (i == n && temp < list.size()) {
                    result = num[i];
                }
                else if (next != num[i]) {
                    if (temp == 0) {
                        temp = list.size();
                        list.clear();
                        list.add(num[i]);
                        next = num[i];
                        result = num[i-1];
                    } else if (temp > 0 && temp < list.size()) {
                        temp = list.size();
                        list.clear();
                        list.add(num[i]);
                        next = num[i];
                        result = num[i-1];
                    } else {
                        list.clear();
                        list.add(num[i]);
                        next = num[i];
                    }
                }
            }
 
        }
 
        System.out.println(result);
    }
 
}
