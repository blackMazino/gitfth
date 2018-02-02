package class_swProblemSolving;
import java.io.BufferedReader;  
import java.io.FileInputStream; 
import java.io.InputStreamReader;   
import java.util.ArrayList; 
import java.util.Arrays;    
import java.util.Collections;   
import java.util.Comparator;    
import java.util.StringTokenizer;
 
public class PreviousSolving2_02_Graph_연방 {
/*
19세기에 N개의 나라가 있었다. 
각 나라는 서로 뜻이 맞는 다른 나라들과 교류를 맺으며 연방을 형성하였다. 
서로 교류를 맺은 두 나라는 같은 연방 소속이며, 한 연방 안에 속하는 모든 나라는 서로 직간접적으로 교류가 가능해야 한다. 
또한 한 나라는 둘 이상의 연방에 동시에 속할 수 없다고 한다.
그러나 갖가지 재난과 기근이 발생하여 경제 사정이 악화됨에 따라 교류 관계를 끊는 나라가 생기기 시작하였다. 
특정 연방에 속한 어떤 나라가 같은 연방 소속의 다른 모든 나라와의 직간접적인 교류가 전부 끊어지면 
그 나라는 그 연방에서 탈퇴하여 새로운 연방을 형성하게 된다. 
아래의 그림은, 나라를 정점, 직접 교류관계를 간선으로 표시했을 때의 나라 간 교류 관계 및 연방 형성에 대한 예시이다.
 
[그림 1]
그림 1의 경우 1, 2, 3, 4 의 나라가 한 연방에 속함을 알 수 있다. 
1-2, 2-3, 3-4 는 직접적인 교류관계에 있음을 뜻한다. 
1 과 4 는 2, 3 을 통해 교류를 할 수 있으므로 간접적인 교류관계에 있다. 
그런데 2-3 사이의 교류 관계가 끊어질 경우 직간접적인 교류관계가 단절되는 곳이 생겨 연방이 둘로 나누어진다. 
즉 1과 2 가 같은 연방, 그리고 3과 4가 별도의 연방을 형성하게 된다.
 
[그림 2]
하지만 그림 2의 경우는 2-3 사이의 교류가 끊어진다 하더라도 2-4 사이의 직접 교류관계가 존재하므로 연방이 둘로 나뉘지 않고 하나로 유지될 수 있다.
여러분에게는 먼저 각 나라의 초기 교류 상태(간선 정보)가 M 개 주어지며 이어서 처리, 혹은 질문에 관련한 구문이 혼합되어 Q 개 주어진다. 
처리와 질문 구문에 대한 설명은 아래와 같다.
 
* 처리 구문
"1 K" 의 형식으로 주어지며 M개의 초기 직접 교류 상태 중 K 번째 교류가 끊어졌음을 알린다. (K 는 1 이상 M 이하의 자연수)
* 질문 구문
"2 A B" 의 형식으로 주어지며 A 나라와 B 나라가 현재 같은 연방 소속인지를 묻는 구문이다.
 
이와 같이 각 나라의 교류 상황이 주어질 때 질문 구문에 대한 답을 출력하는 프로그램을 작성하시오.
[제한조건]
1. 나라의 수 N 은 1 이상 100000(10만) 이하의 자연수이며 각 나라는 1 부터 N까지의 번호로 구분된다.
2. 초기 교류 상태를 나타내는 간선 정보의 수 M 은 1 이상 100000(10만) 이하의 자연수이다.
3. 처리 구문과 질문 구문은 합하여 Q 개 이며, Q 는 1 이상 200000(20만) 이하의 자연수이다.
4. 초기 교류 상태에서부터 연방이 여러 개 존재할 수도 있다.
 
[입력]
맨 처음 테스트 케이스의 개수 T가 주어지며, 그 다음 줄부터 T 개의 테스트 케이스가 주어진다.
각 테스트 케이스는 여러 개의 줄로 이루어져 있으며 첫 줄에는 나라의 수 N, 초기 교류 상태를 나타내는 간선의 수 M이 공백으로 구분되어 주어진다. 그 다음 M개의 줄에 걸쳐 초기에 직접적인 교류를 맺는 두 나라의 번호가 순서 없이 공백으로 구분되어 주어진다. 그 다음 줄에는 구문의 수 Q가 주어지며, 이후 Q 줄에 걸쳐 처리 구문 혹은 질문 구문이 문제에 설명된 형식에 맞추어 한 줄에 한 구문씩 주어진다.
 
[출력]
각각의 테스트 케이스에 대하여 #x (x는 테스트 케이스 번호, 1부터 시작) 을 출력하고 공백을 하나 둔 다음, 질문 구문에 대한 답을 순서대로 출력한다. 질문에 대한 답이 "같은 연방 소속이다" 인 경우 1을, "같은 연방 소속이 아니다"인 경우 0을 출력하며 테스트 케이스에서 먼저 나온 질문 구문에 대한 답을 먼저 출력한다. 구문에 대한 답과 답 사이에는 공백을 주지 않는다.
 
[입출력 예]
(입력)
3      // 테스트 케이스의 개수
4 3    // 첫번째 케이스 시작 : N 과 M 의 값
1 2    // M개의 초기 직접 교류 (간선) 정보 : 1번째정보
2 3    // 2번째 정보
3 4    // 3번째 정보
5      // Q 의 값
2 1 2  // Q 개의 구문
2 1 4
1 2
2 1 2
2 1 4
4 4    // 두번째 케이스의 시작
1 2
2 3
3 4
4 1
5
2 1 4
1 2
2 1 2
2 1 4
2 3 1
8 9    // 세번째 케이스의 시작
5 4
2 6
6 7
1 3
1 6
7 8
1 8
8 2
7 2
11
2 5 6
1 6
1 5
2 1 6
2 1 7
1 3
2 1 7
1 8
2 1 6
2 3 8
1 4
 
(출력)
#1 1110
#2 1111
#3 011101
*/
    static int T,N,M,Q; 
    static int[][] Edge;    
    static int[] p; 
    static ArrayList<Integer> cut;    
    static ArrayList<int[]> que;  
          
          
    public static void main(String[] args) throws Exception {   
        //System.setIn(new FileInputStream("input.ini")); 
              
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
              
        T = Integer.parseInt(br.readLine());    
              
        for(int tc=1; tc<=T; tc++) { 
            StringTokenizer st = new StringTokenizer(br.readLine());    
            N = Integer.parseInt(st.nextToken());     
            M = Integer.parseInt(st.nextToken());     
                  
            p = new int[N+1];   
            for(int i=1; i<=N; i++)  
                p[i] = i;   
                  
            Edge = new int[M+1][3]; 
            for(int i=1; i<=M; i++) {    
                StringTokenizer st1 = new StringTokenizer(br.readLine());   
                Edge[i][0] = Integer.parseInt(st1.nextToken()); 
                Edge[i][1] = Integer.parseInt(st1.nextToken()); 
                Edge[i][2] = 1; 
            }   
                  
            Q = Integer.parseInt(br.readLine());    
            cut = new ArrayList<>();  
            que = new ArrayList<>();  
            int kind;   
            for(int i=1; i<=Q; i++) {    
                StringTokenizer st2 = new StringTokenizer(br.readLine());   
                kind = Integer.parseInt(st2.nextToken());   
                      
                if ( kind == 1) {   
                    int v = Integer.parseInt(st2.nextToken());  
                    Edge[v][2] = 0; 
                    que.add(new int[]{-1,v});   
                }   
                else if ( kind == 2 ) { 
                    int v1 = Integer.parseInt(st2.nextToken()); 
                    int v2 = Integer.parseInt(st2.nextToken()); 
                    que.add(new int[]{Math.min(v1, v2), Math.max(v1, v2) });    
                }   
            }   
                  
            for(int i=1; i<=M; i++) {    
                if ( Edge[i][2] == 1 ) {    
                    merge(Edge[i][0],Edge[i][1]);   
                }   
            }   
            //print();  
                  
            StringBuilder sb = new StringBuilder(); 
            for(int i = que.size()-1; i>=0; i--) {   
                if ( que.get(i)[0] == -1 ) {    
                    int n1 = Edge[que.get(i)[1]][0];    
                    int n2 = Edge[que.get(i)[1]][1];    
                    merge(n1,n2);   
                    //print();  
                }   
                else {  
                    int n1 = que.get(i)[0]; 
                    int n2 = que.get(i)[1]; 
                          
                    if ( find(n1) == find(n2) ) {   
                        sb.append(Integer.toString(1)); 
                    }   
                    else    
                        sb.append(Integer.toString(0)); 
                          
                }   
            }   
            sb.reverse();   
            System.out.println("#"+tc+" "+sb.toString());   
                  
        }   
        br.close(); 
    }   
          
    public static int find(int z) { 
        if(p[z]==z) return p[z];    
        p[z] = find(p[z]);  
        return p[z];    
    }   
          
    public static void merge(int x, int y) {//union    
        int px = find(x);   
        int py = find(y);   
              
        if(px == py) return;    
              
        p[py] = px; 
              
    }   
          
    public static void print() {    
        for(int i=1; i<=N; i++) {    
            System.out.print(p[i]+ " ");  
        }   
        System.out.println();   
    }   
          
      
}