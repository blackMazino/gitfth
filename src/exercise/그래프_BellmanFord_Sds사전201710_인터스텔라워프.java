package exercise;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*				
[입출력 예제] 				
(입력)    	
4
3 3
2
2 1
1 2
0
4 3
2
2 1
3 1
1
3 0 2 2 0
4 2
0
1
2 0 1 0 -3
10 2
2
1 1
5 1
2
0 1 9 0 25
3 0 8 1 -6

(출력)    	
#1 noway
#2 4
#3 mininf
#4 -2

 */
//인터스텔라 워프
public class 그래프_BellmanFord_Sds사전201710_인터스텔라워프 {
 
    static int TC, X,Y,M,W;
    static long d[];
    static int[][]space; 
    static ArrayList<Way> pathlist;
    static boolean minInf;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=TC;tc++){
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(br.readLine());
            space = new int [Y][X];
            d = new long [X*Y];
            for(int m=1;m<=M;m++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                space [y][x] = -1;
            }
            W = Integer.parseInt(br.readLine());
            pathlist = new ArrayList<Way>();
            for(int w=1;w<=W;w++){
                st = new StringTokenizer(br.readLine());
                int sx = Integer.parseInt(st.nextToken());
                int sy = Integer.parseInt(st.nextToken());
                int ex = Integer.parseInt(st.nextToken());
                int ey = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                space[sy][sx] = 1;
                pathlist.add(getPath(sx,sy,ex,ey,c));
            }
             
            for(int x=0;x<X;x++){
                for(int y=0;y<Y;y++){
                    if(x==X-1 && y==Y-1){
                        break;
                    }
                    if(space[y][x]==0){
                        search(x,y,x+1,y,1);
                        search(x,y,x,y+1,1);
                        search(x,y,x-1,y,1);
                        search(x,y,x,y-1,1);
                    }
                }
            }
             
            minInf = false;
            bellmanFordForInterStelaWarp();
             
            if(minInf){
                System.out.println("#"+tc+" "+"mininf");
            }else{
            	System.out.println("#"+tc+" "+(d[X*Y -1]==Long.MAX_VALUE?"noway":d[X*Y -1]) ); 
            }
             
        }       
    }
 
     
    private static void bellmanFordForInterStelaWarp() {
        int tN = X*Y;
        Arrays.fill(d, Long.MAX_VALUE);
        d[0] = 0;
        for(int i=1;i<tN;i++){
            for(Way w : pathlist){
                if(d[w.sIdx]<Integer.MAX_VALUE){
                    if(d[w.eIdx]>d[w.sIdx]+w.c){
                        d[w.eIdx]=d[w.sIdx]+w.c;
                        if(i==tN-1){
                            minInf = true;
                            break;
                        }
                    }
                }
            }
        }
         
    }
 
 
    private static void search(int sx, int sy, int ex, int ey, int c) {
        if(ex<=X-1 && ex>=0
        && ey<=Y-1 && ey>=0
        && space[ey][ex]>=0){
            pathlist.add(getPath(sx,sy,ex,ey,c));
        }
    }
 
    private static Way getPath(int sx, int sy, int ex, int ey, int c) {
        int sIdx = sy*X+sx;
        int eIdx = ey*X+ex;     
        return (new Way(sIdx, eIdx, c));
    }
}
class Way{
    public Way(int sIdx, int eIdx, int c) {
        super();
        this.sIdx = sIdx;
        this.eIdx = eIdx;
        this.c = c;
    }
 
    int sIdx,eIdx,c;
}