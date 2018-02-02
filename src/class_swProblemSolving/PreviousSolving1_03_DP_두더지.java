package class_swProblemSolving;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
 
public class PreviousSolving1_03_DP_두더지 {
/*
어떤 두더지가 N개의 방들과 M개의 굴들로 이루어진 집을 짓고 살고 있다. 
방들은 1번부터 N번까지 번호가 붙어 있고, 하나의 굴은 서로 다른 두개의 방을 직접 연결한다.
동일한 쌍의 방을 연결하는 굴이 여러 개 있을 수도 있다. 
두더지는 언제나 1번 방에서 잠을 자는데, 아침에 일어나면 먹이를 먹기 위해 특정한 방으로 이동한다. 
이 두더지는 특이한 규칙을 지키는데, 여러 굴을 지날 때는 반드시 이미 지난 굴들의 길이보다 더 긴 굴을 사용한다는 것이다. 
즉, 지나가는 굴의 길이 순서가 1, 3, 6 인 경우는 허용이 되지만, 4, 2, 5 인 경우는 허용이 되지 않는다.
매일 아침의 목적지 방이 달라질 수 있고, 두더지가 잠이 덜 깬 상태로 어떤 굴들을 이용해 목적지에 도착할 지 자신도 잘 모르므로,
이 두더지는 (1번 방을 포함한) 모든 방들에 대해서, 위의 규칙을 따르면서 이동할 수 있는 가장 긴 길의 길이를 모두 알고 싶다. 
아래 그림을 보자. 1번 방에서 2번 방으로 갈 때 가장 긴 길의 길이는 5 이다. 
또한 3번 방으로 갈 때 가장 긴 길의 길이는 4 임을 알 수 있다. 1번 방이 목적지인 경우, 가장 긴 길의 길이는 0 인 것 밖에 없다.
방의 개수와 방들을 연결하는 굴들의 길이를 입력으로 받아 1번 방에서 모든 방들 각각으로 이동하는 가장 긴 길의 길이를 계산하는 프로그램을 작성하시오. 만약 위의 규칙을 지키는 방식으로 어떤 방에 도착하는 것이 불가능한 경우 길이는 모두 -1로 정한다. 주의: 출력의 크기를 줄이기 위해 가장 긴 길의 길이들을 모두 합한 값을 출력한다.
[제한조건]
1. 목적지 A번 방으로 가는 가장 긴 길의 중간에 같은 방이 여러 번 등장할 수 있으며, 
심지어 목적지인 A번 방이 여러 번 등장할 수도 있다.
2. 굴들의 길이는 모두 서로 다르다.
[입력] 
입력 파일의 제일 첫째 줄에는 파일에 포함된 케이스의 수 T가 주어진다. 
각 케이스의 첫째 줄에 방의 개수를 나타내는 N과 굴의 개수를 나타내는 자연수 M이 공백으로 구분되어 주어진다. (2 ≤ N ≤ 100,000, 1 ≤ M ≤ 300,000) 둘째 줄부터 M개의 줄에 걸쳐 각각 3개의 자연수가 주어지는데, 첫 두 수는 굴이 있는 서로 다른 두 방의 번호이며, 세번째 수는 그 굴의 길이이다. 굴의 길이는 1 이상 1,000,000 이하의 자연수이며, 모두 서로 다르다. 동일한 쌍의 방을 연결하는 굴이 여러 개 있을 수도 있음에 주의하라.
[출력] 
각 테스트 케이스의 답을 순서대로 표준출력으로 출력하며, 각 케이스마다 줄의 시작에 “#x”를 출력하여야 한다. 
이때 x는 케이스의 번호이다. 같은 줄에 각 방에 대한 가장 긴 길의 길이의 합을 출력한다.
 
[입출력 예]
(입력)
3
3 3
1 2 1
2 3 3
1 3 2
4 3
1 2 1
2 3 3
1 3 2
3 4
1 2 1
2 3 3
1 3 2
3 1 4
(출력)
#1 9
#2 8
#3 17
 
*/
    static int N,M;
    static ArrayList<Pair3>[] con;
//  static ArrayList<Pair>tunnels; 
    static int[] rooms;
    static Map<Integer, Pair3> tunnelMap ;
    static int [] lengthOfTunnel;
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
         
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        con = new ArrayList[M+1];
//      tunnels = new ArrayList<Pair>();
        tunnelMap = new HashMap<Integer, Pair3>();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
//          tunnels.set(w, new Pair(s,e));
            tunnelMap.put(w, new Pair3(s,e));//w가 유니크하므로 map을 사용해도 무방
            lengthOfTunnel[i] = w;
        }
        //Key들만 정렬한다
        Arrays.sort(lengthOfTunnel);
        for(int w:lengthOfTunnel){
            System.out.println(tunnelMap.get(w).x +","+tunnelMap.get(w).y);
        }
         
//      //sort By key1 : List
//      List<Integer> sortedKeys = new ArrayList(tunnelMap.keySet());
//      Collections.sort(sortedKeys);
//      for(int w : sortedKeys){
//          System.out.println(tunnelMap.get(w).x +","+tunnelMap.get(w).y);
//      }
//      
//      //sort BY key2 : treeMap
//      Map<Integer, Pair> treeMap = new TreeMap<Integer,Pair>(tunnelMap);
//      for(int w : treeMap.keySet()){
//          System.out.println(treeMap.get(w).x +","+treeMap.get(w).y);
//      }
         
        rooms = new int[N+1];
        Arrays.fill(rooms, -1);
        rooms[0] = rooms[1] = 0;
        lengthOfTunnel = new int [M];
         
        for(int i=0;i<M;i++){
            int l = lengthOfTunnel[i];
            int s = tunnelMap.get(l).x;
            int e = tunnelMap.get(l).y;
            if(rooms[e]==-1){
                rooms[e] +=l;
            }else{
                 
            }
        }
         
         
        //굴에 길이에 따라 정렬(오름차순)
        //최장경로를 관리하는 배열을 선언하고 0,-1,-1로 초기화한다
        //정렬된 길이들을 for-loop로 돌면 배열을 갱신한다.(양방향이므로 시작과 끝점을 변경하여 한번더갱신해주는것에 주의)
    }
}
class Pair3 {
     
    int x,y;
    public Pair3(int x,int y){
        this.x= x;
        this.y= y;
         
    }
}