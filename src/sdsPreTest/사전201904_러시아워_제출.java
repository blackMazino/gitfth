package sdsPreTest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;


public class 사전201904_러시아워_제출 {

    static int TC, N, answer;//5<=N<=10
    static int arr[][];
    static char init[][];
    static Map<String,String> tracker;
    static LinkedList<QueData> que;
    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201904.txt"));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());
        String alphabet = ".ABCDEFGHIJ";
        
        for(int tc=1;tc<=TC;tc++){
            N = Integer.parseInt(br.readLine());
            arr = new int [6][6];
            init = new char [6][6];
            for(int i=1;i<6;i++){
                for(int j=1;j<6;j++){
                    init[i][j] = '-';
                }
            }
            for(int n=1;n<=N;n++){
                st = new StringTokenizer(br.readLine());
                int si = Integer.parseInt(st.nextToken());
                int sj = Integer.parseInt(st.nextToken());
                int ei=0;
                int ej=0;
                String d = st.nextToken();
                if("S".equals(d)){ ei=si-1; ej=sj; }
                if("N".equals(d)){ ei=si+1; ej=sj; }
                if("E".equals(d)){ ei=si; ej=sj-1; }
                if("W".equals(d)){ ei=si; ej=sj+1; }                
                arr[si][sj] = n;
                arr[ei][ej] = n;
                init[si][sj] = alphabet.charAt(n);
                init[ei][ej] = alphabet.charAt(n);               
            }
//            print(init);           
            StringBuffer sb = new StringBuffer();
            for(int i=1;i<6;i++){
                for(int j=1;j<6;j++){
                    sb.append(init[i][j]);
                }
            }
//            System.out.println(sb.toString());
//            System.out.println(sb.toString().charAt(0));
            answer = Integer.MAX_VALUE;   
            tracker = new HashMap<String,String>();
            que = new LinkedList<QueData>();
            setTrack(sb.toString(),null,0);
            //BFS
            while(!que.isEmpty()){
                QueData qd = que.removeFirst();
                if(isEnd(qd.board)){
                    answer = qd.moveCnt;
                    break;
                }
                updateAdj(qd);                
            }
            if(que.isEmpty()) answer = -1;
                    
            System.out.println("#"+tc+" "+answer);
        }
    }
    
       
    private static void updateAdj(QueData qd) {
        String present = qd.board;
        int moveCnt = qd.moveCnt;
        int tmpi=0;
        int tmpj=0;
        StringBuffer sb;
        for(int i=1;i<6;i++){
            for(int j=1;j<6;j++){
                int idx = getIdx(i,j);
                if(present.charAt(idx)=='-'){
                    //go down
                    if(i>1 && present.charAt(getIdx(i-1,j)) != '-'){
                        char val = present.charAt(getIdx(i-1,j));
                        if(i>2 && val == present.charAt(getIdx(i-2,j))){
                            tmpi = i-1;
                            while(tmpi<5 && present.charAt(getIdx(tmpi+1,j)) == '-' ){
                                char fst = present.charAt(getIdx(tmpi+1,j));
                                char snd = present.charAt(getIdx(tmpi  ,j));
                                char trd = present.charAt(getIdx(tmpi-1,j));
                                sb = new StringBuffer(present);                                
                                sb.setCharAt(getIdx(tmpi+1,j), snd);
                                sb.setCharAt(getIdx(tmpi  ,j), trd);
                                sb.setCharAt(getIdx(tmpi-1,j), fst);
                                tmpi++;                             
                                setTrack(sb.toString(),present, moveCnt+1);   
                                present = sb.toString();
                            }
                        }                        
                    }
                    present = qd.board;
                    
                    //go right
                    if(j>1 && present.charAt(getIdx(i,j-1)) != '-'){
                        char val = present.charAt(getIdx(i,j-1));
                        if(j>2 && val == present.charAt(getIdx(i,j-2))){
                            tmpj = j-1;
                            while(tmpj<5 && present.charAt(getIdx(i,tmpj+1)) == '-' ){
                                char fst = present.charAt(getIdx(i,tmpj+1));
                                char snd = present.charAt(getIdx(i,tmpj));
                                char trd = present.charAt(getIdx(i,tmpj-1));
                                sb = new StringBuffer(present);                                
                                sb.setCharAt(getIdx(i,tmpj+1), snd);
                                sb.setCharAt(getIdx(i,tmpj)  , trd);
                                sb.setCharAt(getIdx(i,tmpj-1), fst);                                
                                tmpj++;                             
                                setTrack(sb.toString(),present, moveCnt+1);     
                                present = sb.toString();
                            }
                        }                        
                    }
                    present = qd.board;
                    
                    //go up
                    if(i<5 && present.charAt(getIdx(i+1,j)) != '-'){
                        char val = present.charAt(getIdx(i+1,j));
                        if(i<4 && val == present.charAt(getIdx(i+2,j))){
                            tmpi = i+1;
                            while(tmpi>1 && present.charAt(getIdx(tmpi-1,j)) == '-' ){
                                char fst = present.charAt(getIdx(tmpi-1,j));
                                char snd = present.charAt(getIdx(tmpi  ,j));
                                char trd = present.charAt(getIdx(tmpi+1,j));
                                sb = new StringBuffer(present);                                
                                sb.setCharAt(getIdx(tmpi-1,j), snd);
                                sb.setCharAt(getIdx(tmpi  ,j), trd);
                                sb.setCharAt(getIdx(tmpi+1,j), fst);
                                tmpi--;                             
                                setTrack(sb.toString(),present, moveCnt+1);    
                                present = sb.toString();
                            }
                        }                        
                    }
                    present = qd.board;
                    
                  //go left
                    if(j<5 && present.charAt(getIdx(i,j+1)) != '-'){
                        char val = present.charAt(getIdx(i,j+1));
                        if(j<4 && val == present.charAt(getIdx(i,j+2))){
                            tmpj = j+1;
                            while(tmpj>1 && present.charAt(getIdx(i,tmpj-1)) == '-' ){
                                char fst = present.charAt(getIdx(i,tmpj-1));
                                char snd = present.charAt(getIdx(i,tmpj));
                                char trd = present.charAt(getIdx(i,tmpj+1));
                                sb = new StringBuffer(present);                                
                                sb.setCharAt(getIdx(i,tmpj-1), snd);
                                sb.setCharAt(getIdx(i,tmpj)  , trd);
                                sb.setCharAt(getIdx(i,tmpj+1), fst);   
                                tmpj--;                             
                                setTrack(sb.toString(),present, moveCnt+1);      
                                present = sb.toString();
                            }
                        }                        
                    }
                    present = qd.board;
                    
                }
            }
        }
    }
    
    
    private static int getIdx(int i, int j) {
        return ((i-1)*5 + j-1);
    }


    private static void setTrack(String present, String previous, int moveCnt) {
        if(!tracker.containsKey(present)){
//            System.out.println(present);
            tracker.put(present, previous);
            que.addLast(new QueData(present, moveCnt));
        }
        
    }
    private static void print(char[][] array) {
        for(int i=1;i<6;i++){
            for(int j=1;j<6;j++){
                System.out.print(array[i][j]+(j==5?"":"\t"));
            }
            System.out.println("");
        }        
    }
    private static boolean isEnd(String str) {     
        return (str.charAt(14)=='A');
    }

}
class QueData{
    public QueData(String board, int moveCnt) {
        super();
        this.board = board;
        this.moveCnt = moveCnt;
    }
    String board;
    int moveCnt;
}
