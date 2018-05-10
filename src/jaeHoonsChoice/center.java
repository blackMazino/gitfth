package jaeHoonsChoice;

import java.io.*;
import java.util.*;
 
// Max Heap과 Min Heap으로 나누어서 확인해야 하는데,
// Java에는 Heap이 없으므로 Priority Queue를 이용해서 푼다.
// 기본적으로 Min Heap으로 구현되고, Max Heap은 값에 -1을 곱해서 반대로 풀이한다.
 
public class center {
 
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        int n;
 
//      BufferedReader br = new BufferedReader(new FileReader("median_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        n = Integer.parseInt(br.readLine());
 
        PriorityQueue<Integer> maxheap = new PriorityQueue<>();
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
 
        for (int i = 1; i <= n; i++) {
            int comp = Integer.parseInt(br.readLine());
 
            // 왼쪽부터 넣는다.
            // 오른쪽이 비어있거나 오른쪽의 가장 최소값보다 작을 때
            if (minheap.isEmpty() || minheap.peek() > comp) {
                maxheap.add(-comp);
            } else {
                minheap.add(comp);
            }
             
            // 왼쪽의 개수가 오른쪽의 개수보다 2개 이상 많을 때는 맞춰준다.
            while (maxheap.size() > minheap.size() + 1) {
                minheap.add(-maxheap.poll());
            } 
            // 오른쪽의 개수가 왼쪽의 개수보다 많을 때는 맞춰준다.
            while (minheap.size() > maxheap.size()) {
                maxheap.add(-minheap.poll());
            }
             
            if(i % 2 == 1) {
                bw.write(-maxheap.peek() + "\n");
            }
        }
        bw.flush();
    }
 
}
