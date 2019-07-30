package sdsPreTest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


public class 사전201904_러시아워ByBFS {

    static int TC, N, answer;//5<=N<=10
    static int arr[][];
    static char charArr[][];
    static Map<String,String> tracker;
    static Queue<Node> que;
    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201904.txt"));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());
        String alphabet = ".SABCDEFGHI";
        
        for(int tc=1;tc<=TC;tc++){
            N = Integer.parseInt(br.readLine());
                        
            
            arr = new int [6][6];
            charArr = new char [6][6];
            for(int n=1;n<=N;n++){
                st = new StringTokenizer(br.readLine());
                int si = Integer.parseInt(st.nextToken());
                int sj = Integer.parseInt(st.nextToken());
                String d = st.nextToken();
                arr[si][sj] = n;
                charArr[si][sj] = alphabet.charAt(n);//charAt(0)=.
                if("S".equals(d)){ arr[si-1][sj] = n; charArr[si-1][sj] = alphabet.charAt(n); }
                if("N".equals(d)){ arr[si+1][sj] = n; charArr[si+1][sj] = alphabet.charAt(n); }
                if("E".equals(d)){ arr[si][sj-1] = n; charArr[si][sj-1] = alphabet.charAt(n); }
                if("W".equals(d)){ arr[si][sj+1] = n; charArr[si][sj+1] = alphabet.charAt(n); }                            
            }
            StringBuffer sb = new StringBuffer();
            for(int i=1;i<6;i++){
                for(int j=1;j<6;j++){
                    sb.append(alphabet.indexOf(charArr[i][j])<0?'.':charArr[i][j]);
                }
            }
            answer = Integer.MAX_VALUE;   
            tracker = new HashMap<String,String>();
            que = new LinkedList<Node>();
            setTrack(sb.toString(),null,0);//Initial
            //BFS
            while(!que.isEmpty()){
            	Node node = que.poll();
                if(isEnd(node.board)){
                    answer = Math.min(answer, node.moveCnt);
                    break;
                }
                find(node);                
            }
            if(que.isEmpty()) answer = -1;
                    

            System.out.println("#"+tc+" "+answer);
        }
    }
        
    private static void find(Node node) {
        String present = node.board;
        int moveCnt = node.moveCnt;
        for(int i=1;i<6;i++){
            for(int j=1;j<6;j++){
                if(present.charAt(getIdx(i,j))=='.'){
                    goHorizon(present, i,j, true, moveCnt);
                    goHorizon(present, i,j, false, moveCnt);
                    goVertical(present,i,j, true, moveCnt);
                    goVertical(present,i,j, false,moveCnt);                   
                }
            }
        }
    }
    
    
    private static void goVertical(String present, int i, int j, boolean asc, int moveCnt) {
        int tmp=0;
        StringBuffer sb;        
        if((asc && i>2) || (!asc && i<4)){
        	int head = asc?(i-1):(i+1);
        	int tail = asc?(i-2):(i+2);
        	if(present.charAt(getIdx(head,j)) != '.' ){
        		if(present.charAt(getIdx(head,j)) == present.charAt(getIdx(tail,j))){
        			tmp = head;
        			while( ((asc && tmp<5)||(!asc && tmp>1)) && present.charAt(getIdx(tmp+(asc?1:-1),j)) == '.'  ){
        				int end = tmp +(asc?-1:1);
                        char t = present.charAt(getIdx(end,j));
                        sb = new StringBuffer(present);
                        sb.setCharAt(getIdx(end,j), present.charAt(getIdx(tmp+(asc?1:-1),j)));
                        sb.setCharAt(getIdx(tmp+(asc?1:-1),j), t);
                        tmp = tmp+(asc?1:-1);                             
                        setTrack(sb.toString(),present, moveCnt+1);     
                        present = sb.toString();
        			}
        		}
        	}
        }      		
	}


	private static void goHorizon(String present, int i, int j, boolean asc, int moveCnt) {
        int tmp=0;
        StringBuffer sb;        
        if((asc && j>2) || (!asc && j<4)){
        	int head = asc?(j-1):(j+1);
        	int tail = asc?(j-2):(j+2);
        	if(present.charAt(getIdx(i,head)) != '.' ){
        		if(present.charAt(getIdx(i,head)) == present.charAt(getIdx(i,tail))){
        			tmp = head;
        			while( ((asc && tmp<5)||(!asc && tmp>1)) && present.charAt(getIdx(i,tmp+(asc?1:-1))) == '.'  ){
        				int end = tmp +(asc?-1:1);
                        char t = present.charAt(getIdx(i,end));
                        sb = new StringBuffer(present);
                        sb.setCharAt(getIdx(i,end), present.charAt(getIdx(i,tmp+(asc?1:-1))));
                        sb.setCharAt(getIdx(i,tmp+(asc?1:-1)), t);
                        tmp = tmp+(asc?1:-1);                             
                        setTrack(sb.toString(),present, moveCnt+1);     
                        present = sb.toString();
        			}
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
            que.add(new Node(present, moveCnt));
        }                
    }

    private static boolean isEnd(String str) {     
        return (str.charAt(14)=='S');
    }
}
class Node{
    public Node(String board, int moveCnt) {
        super();
        this.board = board;
        this.moveCnt = moveCnt;
    }
    String board;
    int moveCnt;
}
