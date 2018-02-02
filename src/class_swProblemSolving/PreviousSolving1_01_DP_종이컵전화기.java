package class_swProblemSolving;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class PreviousSolving1_01_DP_종이컵전화기 {
/*
수진이네 동네는 원 모양이며, 원 위에 N개의 집이 동일한 간격으로 위치해 있다. 
집들은 한 집에서 시작하여 시계방향으로 1번부터 N번까지 번호가 붙어 있다. 
각 집에는 종이컵이 하나씩 있으며, 서로 다른 2개의 종이컵을 연결하는 실들이 존재한다. 
즉, 하나의 실 양 끝에 연결된 종이컵 끼리 통신이 가능하며, 이런 방법으로 집들간에 대화를 나눈다.
 
아래 그림의 (a)와 (b)는 4 개의 실이 4 쌍의 집을 연결하고 있는 경우의 예시이다. 
실은 언제나 팽팽하게 연결되어 있으므로 항상 직선으로 표현됨에 주의하라. 
그림 (a)의 경우 4개의 실이 한 점에서 교차하고 있다. 그림 (b)도 4 개의 실로 집을 연결하는 예시이지만 교차하는 방식이 다르다.
 
한 집에는 단 하나의 종이컵만 있어서 최대 다른 한 집하고만 대화를 할 수 있다.
또, 두 개 이상의 실이 교차하는 경우에는 그들 중 단 하나만을 사용할 수 있다. 
이러한 상황이 주어졌을 때 최대로 동시에 대화가 가능한 집의 쌍의 수를 알고자 한다. 
위의 (a)의 경우는 단 한 쌍만 대화가 가능하며 ( (1,5), (2,6), (3,7), (4,8) 중 한 쌍 ), 
(b)의 경우는 두 쌍만 대화가 가능하다 ( (3,8) 그리고 (5,7) )
 
집들의 개수와 실들이 연결된 쌍들을 입력으로 받아 대화가 동시에 가능한 집 쌍의 최대 수를 출력하는 프로그램을 작성하라.
 
[입력]
 첫 줄에 케이스의 개수 T가 주어지고, 이후 차례로 T개의 테스트 케이스가 주어진다. 
 각 케이스의 첫번째 줄에는 집의 개수 N 과 실의 개수 M 이 차례로 주어진다. (2 ≤ N ≤ 200, 1 ≤ M ≤ 500, N, M 은 정수) 
 이후 M개의 줄에는 두 개의 서로 다른 집 번호가 주어진다. 한 쌍의 집을 연결하는 실의 개수는 최대 한 개이다.
 
[출력]
 
 
각 케이스마다 줄의 시작에 “#x”를 출력하고 (x는 케이스의 번호이며 1부터 시작함.) 
공백을 하나 둔 뒤 동시에 대화가 가능한 집의 쌍의 최대 수를 출력한다.
 
 
[입출력 예]
(입력)
2
8 4
1 5
6 2
4 8
7 3
8 4
1 5
2 5
7 5
3 8
 
(출력)
#1 1
#2 2
 
(sample_input.txt에 대한 출력)
 
#1 4
#2 3
#3 4
#4 4
#5 3
#6 15
#7 15
#8 14
#9 15
#10 16
*/
    static int N,M;
    static ArrayList<Integer>[] con;
    static int []visited;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
         
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        con = new ArrayList[M+1];
        visited = new int[N+1];
        for(int i=1;i<=M;i++){
            con[i] = new ArrayList<>();
        }
        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            con[s].add(e);
            con[e].add(s);
        }
        Arrays.sort(con);
         
        for(int i=1;i<=N;i++){
            visited[i] = 1;
            for(int j=0;j<con[i].size();j++){
                int si = i;
                int ei = con[i].get(j);
                while(si<ei){
                     
                }
            }
                 
             
        }
         
         
        //전체범위 = 부분범위의 최대쌍
        //D[i][j] : i~j까지의 최대상
        // 1) i-j 연결 간선이 있는 경우 : D[i+1][j-1] + 1;
        // 2) i-j 연결 간선이 없는 경우 : max(D[i][k] + D[k+1][j])  i<=k<j
        int i,j,n;
        n = N;
        for(i=1;i<=n;i++){//범위
            for(j=2;j<=n-i;j++){//범위의 시작값
                 
                for(int k=j;k<j+i;k++){
                     
                }
            }
             
        }
         
         
         
         
         
         
         
         
    }
 
}