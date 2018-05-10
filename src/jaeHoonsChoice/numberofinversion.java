package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
public class numberofinversion {
 
    static int N, num[];
    static long cnt = 0;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      System.setIn(new FileInputStream("sample/noi.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
         
        // merge sort를 이용하여 Inversion을 파악한다.
        merge_sort(num);
         
        bw.write(cnt + "");
        bw.flush();
    }
 
    private static void merge_sort(int[] num) {
        // TODO Auto-generated method stub
        int len = num.length;
         
        if (len == 1) return;  // 길이가 1이면 의미가 없으므로 return
         
        // 둘로 나눌 배열을 만들어서 초기화
        int arr1[] = new int[len/2];
        int arr2[] = new int[len - len/2];
         
        // 각 배열에 값 채워넣기
        for (int i = 0; i < len/2; i++) arr1[i] = num[i];
        for (int i = 0; i < (len - len/2); i++) arr2[i] = num[i+len/2];
         
        // 재귀호출하여 나눌 수 없을 때까지 나눈다.
        merge_sort(arr1);
        merge_sort(arr2);
         
        merge(arr1, arr2, num);
    }
 
    private static void merge(int[] arr1, int[] arr2, int[] num) {
        // TODO Auto-generated method stub
        int n1, n2, n3;
        n1 = n2 = n3 = 0;
         
        // 양쪽 배열에 값이 없을 때까지 루프 수행
        while (n1 < arr1.length) {
            if (n2 < arr2.length) {
                if (arr1[n1] < arr2[n2]) {  // 작은 값을 앞에 배치한다.
                    num[n3] = arr1[n1];
                    n1++;
                } else {
                    num[n3] = arr2[n2];
                    n2++;
                    cnt += arr1.length - n1;  // 앞의 값이 더 클 경우 결과 값에 더한다
                }
                n3++;
            } else {
                while (n1 < arr1.length) {
                    num[n3] = arr1[n1];
                    n1++;
                    n3++;
                }
            }
        }
         
        while (n2 < arr2.length) {
            num[n3] = arr2[n2];
            n2++;
            n3++;
        }
    }
 
}