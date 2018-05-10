package jaeHoonsChoice;

import java.io.*;
import java.util.*;

class Hull {
    int x,y;
    public Hull(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class convex_hull {

    static int T, n;
    static Hull list[], p;
	     
    static int ccw(Hull A, Hull B, Hull C) {
        // TODO Auto-generated method stub
        long cal = 0;
         
        // 삼각형의 넓이를 구해서 확인한다. +이면 왼쪽, -이면 오른쪽(세타 값으로 확인)
        // A가 B를 바라볼 때, C의 위치
        // area = ( (bx - ax) * (cy - ay) - (by - ay) * (cx - ax) ) * 1/2  -> 벡터에서 많이 쓰이므로 숙지
        cal = (long)(B.x-A.x)*(C.y-A.y) - (long)(B.y-A.y)*(C.x-A.x);
         
        if(cal > 0) {
            return 1;
        } else if(cal < 0) {
            return -1;
        } else {
            return 0;
        }
    }
	     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//    	System.setIn(new FileInputStream("sample/convex_hull.txt"));
//    	System.setIn(new FileInputStream("sample/convex_hull_sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
	    
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	n = Integer.parseInt(br.readLine());
            
            list = new Hull[n+1];               
            for(int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                 
                list[i] = new Hull(a,b);
            }
    	         
            for(int i = 1; i <= n; i++) {
                // list에 있는 점 중, y와 x가 가장 작은 것을 찾아서 첫번째 배열과 바꿔준다. 
                if(list[1].y > list[i].y || list[1].y == list[i].y && list[1].x > list[i].x) {
                    Hull temp = list[1];
                    list[1] = list[i];
                    list[i] = temp;
                }
            }
    	                 
            // 정렬 로직 : 숙지할 것
            Arrays.sort(list, 2, n+1, new Comparator<Hull>() {  // 배열, from index, to index, Comparator
            													// 첫번째는 이미 지정되어 있음(list[0] = 없음 / list[1] = 지정) -> 2번부터 시작
                @Override
                public int compare(Hull a, Hull b) {
                    // TODO Auto-generated method stub
                    int v = ccw(new Hull(list[1].x, list[1].y), a, b);  // 각도순으로 정렬
                    if(v > 0) return -1;
                    if(v < 0) return 1;
                    return (Math.abs(a.x) + a.y) - (Math.abs(b.x) + b.y);  // 거리 같을 때 가까운 순으로 정렬
                }
            });
    	         
            // 스택에 1,2번 점을 넣어두고 시작
            // i가 들어올 때 최근 2개의 벡터를 기준으로 왼쪽이면 기존 내역을 빼서 현재를 추가해주고, 오른쪽이면 스택에 더한다.
            // 가장 큰 각도로 돌기 때문에 가장 바깥으로 돌면서 이동한다.
            Stack<Integer> stack = new Stack<>();
            stack.push(1);
            for(int i = 2; i <= n; i++) {
                while(stack.size() > 1 && ccw(list[stack.get(stack.size()-2)], list[stack.peek()], list[i]) <= 0) {
                    stack.pop();
                }
                stack.add(i);
            }
             
            bw.write("#" + tc + " " + stack.size() + "\n");
            bw.flush();
		}
        bw.close();
        br.close();        
    }
 
}