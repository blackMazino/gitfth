package sdsPreTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private class SearchNode{
	    private Board board;
	    private int moves;
	    
	    public SearchNode(Board b, int moves) {
	    	this.board = b;
	    	this.moves = moves;
	   	}
	}
	
	public Solution(Board initial) {
		Queue<SearchNode> que = new LinkedList<SearchNode>();
		ArrayList<Board> moved = new ArrayList<Board>();
		
		que.add(new SearchNode(initial, 0));
		SearchNode cm = null;
		while(!que.isEmpty()){
			cm = que.poll();
			if(moved.contains(cm.board)) continue;
			
			if(isEnd(cm.board)){
				answer = cm.moves;
				break;
			}
			
      		moved.add(cm.board);
      		
      		LinkedList<Board> adjList = getAdj(cm.board);
//      		for(ParkingLot tmp : adjList){
      		while(!adjList.isEmpty()){
      			Board tmp = adjList.removeLast();
      			if(!moved.contains(tmp)){
      				que.add(new SearchNode(tmp, cm.moves+1));
      			}
      		}     
		}
	}
	
	private LinkedList<Board> getAdj(Board board) {
		LinkedList<Board> stk = new LinkedList<Board>();
		int [][] tmp = copyArr(board.blocks);
		int tmpi=0;
		int tmpj=0;
		for(int i=1;i<6;i++){
		    for(int j=1;j<6;j++){
		        if(tmp[i][j]==0){
		            //go down
		            if(i>1 && tmp[i-1][j]!=0){
		                int val = tmp[i-1][j];
		                if(i>2 && val == tmp[i-2][j]){	                    
//		                     2
//		                     2
//		                     0
		                    tmpi = i-1;
		                    while(tmpi<5 && tmp[tmpi+1][j] == 0 ){
		                        int tail = tmpi-1;
		                        int t = tmp[tail][j];		                        
		                        tmp[tail][j] = tmp[tmpi+1][j];//0
		                        tmp[tmpi+1][j] = t;
		                        tmpi++;		             
//		                        print(tmp);
		                        stk.addLast(new Board(tmp));		                        
		                    }
		                }
		            }
		            tmp = copyArr(board.blocks);
		            //go right
		            if(j>1 && tmp[i][j-1]!=0){
		                int val = tmp[i][j-1];
		                if(j>2 && val == tmp[i][j-2]){
		                    tmpj = j-1; //220
		                    while(tmpj<5 && tmp[i][tmpj+1]==0){
		                        int tail = tmpj-1;
		                        int t = tmp[i][tail];
		                        tmp[i][tail] = tmp[i][tmpj+1];
		                        tmp[i][tmpj+1] = t;
		                        tmpj++;
//		                        print(tmp);
		                        stk.addLast(new Board(tmp)); 
		                    }
		                }		                
		            }
		            tmp = copyArr(board.blocks);
		            //go up
                    if(i<5 && tmp[i+1][j]!=0){
                        int val = tmp[i+1][j];
                        if(i<4 && val == tmp[i+2][j]){
//                          0
//                          2
//                          2
                            tmpi = i+1;
                            while(tmpi>1 && tmp[tmpi-1][j] == 0 ){
                                int tail = tmpi+1;
                                int t = tmp[tail][j];                               
                                tmp[tail][j] = tmp[tmpi-1][j];//0
                                tmp[tmpi-1][j] = t;
                                tmpi--;      
//                                print(tmp);
                                stk.addLast(new Board(tmp));                                
                            }
                        }
                    }
                    tmp = copyArr(board.blocks);
		            //go left
                    if(j<5 && tmp[i][j+1]!=0){
                        int val = tmp[i][j+1];
                        if(j<4 && val == tmp[i][j+2]){
                            tmpj = j+1; //022
                            while(tmpj>1 && tmp[i][tmpj-1]==0){
                                int tail = tmpj+1;
                                int t = tmp[i][tail];
                                tmp[i][tail] = tmp[i][tmpj-1];
                                tmp[i][tmpj-1] = t;
                                tmpj--;
//                                print(tmp);
                                stk.addLast(new Board(tmp)); 
                            }
                        }                       
                    }
		        }
		    }
		}		
		
		return stk;
	}
	
	private static int[][] copyArr(int[][] arr) {
		int [][] rArr = new int [6][6];
		for(int i=0;i<6;i++){
  			for(int j=0;j<6;j++){
  			    rArr[i][j] = arr[i][j];
  			}
  		}
		return rArr;
	}

	private boolean isEnd(Board board) {
		return (board.blocks[3][5]==1);
	}

	static int TC, N, answer;//5<=N<=10
	static int arr[][];
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//      BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201904.txt"));
      StringTokenizer st;
      TC = Integer.parseInt(br.readLine());
      for(int tc=1;tc<=TC;tc++){
          N = Integer.parseInt(br.readLine());
          arr = new int [6][6];
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
      
          }
//          print(arr);            
          answer = 0;           
          Board init = new Board(arr);
          Solution solution = new Solution(init);

          System.out.println("#"+tc+" "+answer);
      }
	}
	
	
}

class Board{
	public Board(int[][] blocks) {
		super();
		this.blocks = blocks;
	}

	int [][] blocks;
//	public Board(int [][] blocks){		
//		this.blocks = new int [6][6];
//        for (int i = 0; i < 6; i++)
//            for (int j = 0; j < 6; j++)
//    		    this.blocks[i][j] = blocks[i][j];
//	}
}