package class_swProblemSolving;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class PreviousSolving2_03_커지는결정 {
/*
화학자인 김박사는 실험을 하다가 우연히 새로운 결정물질을 발견하였다. 
결정물질은 정사각형 모양을 가지고 있으며, 고정된 중심점을 기준으로 일정한 속도로 그 크기가 커진다. 
단, 커지는 동안 밀도는 변하지 않는다. 
결정물질의 성질을 자세히 관찰하기 위해, 두 개의 서로 다른 결정물질을 근처에 두었더니 각각 커지다가 서로 부딪히게 되면 하나의 결정물질이 기체로 변해 증발해 버리고 다른 하나의 결정물질은 계속 원래 속도로 크기가 커진다는 것을 알게 되었다.
이 관찰을 통해 김박사는 부딪힌 두 결정물질들 가운데 항상 밀도가 낮은 결정물질이 기체로 변해 증발한다는 가설을 세운다. 
김박사는 이 가설을 증명하기 위해 다음과 같은 실험을 하기로 하였다. 
좌표축이 표시된 실험판 위에 여러 개의 서로 다른 결정물질들을 좌표축에 평행하면서 서로 닿지 않도록 배치한 후, 
시간이 흐름에 따라 결정물질들이 서로 부딪히면서 증발하는 순서를 기록하는 방식이다. 
주어진 결정물질들은 모두 좌표축(xy-좌표)에 평행하며 서로 겹치지 않으며 밀도는 서로 다르다. 
결정물질의 초기 크기는 결정 정사각형의 한 변의 길이로 주어지고, 속도는 1초당 변의 길이가 증가하는 속도이다. 
여러 개의 결정물질들이 동시에 부딪히게 되었을 경우, 부딪힌 결정물질의 쌍 마다 밀도가 낮은 결정물질이 증발하며, 
동시에 증발하는 결정물질이 두 개 이상일 때 밀도가 낮은 순으로 증발하는 것으로 정한다. 
4개의 결정물질 S1, S2, S3, S4의 초기 위치가 (2,2), (2,6), (7,6), (7,0) 이고
 초기 크기는 모두 2, 각 결정물질의 속도는 1, 1, 2, 1, 밀도는 각각 3, 5, 1, 7인 경우를 생각해보자.
  
 
[초기 결정들의 위치]
 
1초가 지나면 S1과 S2, S3은 동시에 만나게된다. 
S1의 밀도인 D1과 S3의 밀도인D3은 모두 S2의 밀도인 D2보다 작으므로 S1과 S3이 증발하고 S2만 남는다. 
단, 같은 시간에 증발하기 때문에 밀도가 작은 S3가 S1보다 먼저 증발하게 된다.
 
 
[1초 뒤 결정들의 위치]
2초 뒤에는 남은 S2와 S4가 만나게 되며, 밀도가 더 작은 S2가 증발하게 된다. 
따라서 증발 순서는 S3, S1, S2가 되며 마지막 남는 결정은 S4이다.
 
 
[2초 뒤 결정들의 위치]
결정물질의 정보(중심점 위치, 초기 크기, 커지는 속도, 밀도)가 주어질 때 결정물질들이 증발하는 순서를 계산하는 프로그램을 작성하시오. 
 
 
 
 
[입력] 
첫째 줄에는 테스트 케이스의 수 T가 주어진다. 
각 테스트 케이스마다 첫 줄에는 결정물질 개수 N이 주어진다. (2 ≤ N ≤ 1,000) 
다음 N개의 줄 중 X번째 줄에는 X번 결정물질의 정보가 주어진다. 
각 줄의 첫 수와 두번째 수는 각각 결정물질 중심점의 x좌표와 y좌표이다. 
(0 ≤ x좌표,y좌표 ≤ 1,000,000,000) 
세번째 수는 결정물질의 초기 크기이고, 
네번째 수는 결정물질이 커지는 속도이다. 마지막 다섯번째 수는 결정물질의 밀도이다. 
(1 ≤ 초기 크기, 속도, 밀도 ≤ 1,000,000,000) 
각 줄의 수는 모두 공백으로 구분되어 있다. 
 
결정물질들은 좌표축에 평행하면서 시작 시점에 서로 닿지 않도록 주어진다. 
결정물질들의 커지는 속도는 같은 것이 있을 수 있지만, 밀도는 모두 다르다.
 
 
 
[출력] 
각 케이스마다 줄의 시작에 “#x”를 출력하여야 한다. 이때 x는 케이스의 번호이다(1부터 시작). 그리고 공백을 하나 둔 후, 결정물질들이 증발하는 순서대로 결정물질의 번호를 공백으로 구분하여 출력한다. 만약 동시에 증발하는 결정물질이 여러 개 있다면 밀도가 낮은 것부터 순서대로 출력한다. 맨 마지막에 남은 결정물질의 번호를 마지막에 출력한다. 부동소수점 변수를 사용할 경우 연산 오차가 발생할 수 있음에 주의하라.
 
[입출력 예]
(입력)
5                                              ← 5 test cases in total
4                                              ← 1st case
2 2 2 1 3
2 6 2 1 5
7 6 2 2 1
7 0 2 1 7
4                                              ← 2nd case
0 0 5 1 4
1 8 3 1 2
7 1 3 1 3
11 10 9 2 1
4                                             ← 3rd case
1 1 5 1 1
1 7 3 1 3
1 12 3 1 2
1 21 9 2 4
3                                             ← 4th case
0 0 1 1 3
3 0 1 1 2
0 4 1 1 1
3                                             ← 5th case
0 0 1 999999998 3
0 1000000000 1 2 1
1000000000 0 3 1 2
 
(출력)
#1 3 1 2 4
#2 4 3 2 1
#3 1 3 2 4
#4 2 3 1
#5 3 2 1
 
*/
    static int N;
    static P[] s;
    static int[] c,v;
    static Density[] d;
    static Density2[] d2;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int tc=1;tc<=T;tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            c = new int[N+1];
            v = new int[N+1];
            d = new Density[N+1];
            s = new P[N+1];
            for(int i=1;i<=N;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int ci = Integer.parseInt(st.nextToken());
                int vi = Integer.parseInt(st.nextToken());
                int di = Integer.parseInt(st.nextToken());
                s[i] = new P(x,y);
                c[i] = ci;
                v[i] = vi;
                d[i] = new Density(i,di);   
            } 
            //밀도순으로 정렬
            Arrays.sort(d);
             
            System.out.println("#"+tc+" ");
        }
         
    }
}
class P{
    int x;
    int y;
    public P(int x, int y){
        this.x = x;
        this.y = y;
    }
}
 
//comparable을 사용(compareTo 함수, 변수1개)
class Density implements Comparable<Density>{
    int i;
    int d;
    public Density(int i,int d){
        this.i = i;
        this.d = d;
    }
    @Override
    public int compareTo(Density o) {
        // TODO Auto-generated method stub
        return d-o.d;
    }   
}
 
//Comparator를 사용(compare 함수,변수 2개)
class Density2 implements Comparator<Density2>{
    int i;
    int d;
    public Density2(int i,int d){
        this.i = i;
        this.d = d;
    }
    @Override
    public int compare(Density2 o1, Density2 o2) {      
        return o2.d - o1.d;
    }   
}
 
//둘의 차이는 아래 URL의 내용 참고
//includestudio.tistory.com/35