package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day1_01_탐색_nQuenn_쉬운풀이 {

    static int N;      //N*N할때의 N;
    static int cols[]; //i행에 들어갈 퀸의 좌표.
    static int answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 한 줄에 정수 하나가 주어지는 경우
		N = Integer.parseInt(br.readLine());
		cols = new int[N];
		answer = 0;
		back_tracking(0);		
		
		System.out.println("answer : "+answer);
		br.close();

	}

	public static void back_tracking(int level){
        //back_tracking 함수
        //level: 현재의 따질 행의 위치
         
        if(level == N){
            //현재 따질 위치가 N열인가?
            //N-1까지 행이 있는데 N열까지 왔다는건
            //N-1까지 모두 조건을 만족한다는 얘기이므로 답을 출력.
            for(int i=0;i<N;i++){
                System.out.print(cols[i]);
            }
            System.out.println("");
            answer++;
        }
        else{
            //현재 상황 level-1까지는 모두 조건대로 되어있고
            //level열에 퀸을 놓는 상황.
         
            for(int i=0;i<N;i++){//퀸을 0번부터 N-1번까지 놓는 경우를 전부 따져본다.
                 
                cols[level]=i; //퀸을 i에 놓는다.
                if(isPossible(level)){//퀸을 i에 놓은것이 가능한가?
                    back_tracking(level+1); //그렇다면 퀸을 그 자리에 넣고 다음 행으로 진입
                     
                }
            }
        }
         
    }
	public static boolean isPossible(int level){
	    for(int i=0;i<level;i++){
	        //level 위치에 현재 시험할 퀸이 놓여져 있는 상태이고
	        //우리는 그 퀸과 i= 0 ~ level-1 에 놓여있는 퀸이 문제를 일으키는지만 보면 된다.
	         
	        if(cols[i]==cols[level] || Math.abs(level-i)== Math.abs(cols[level]-cols[i])){
	            //i번째 퀸의 위치와 level위치의 퀸이 같은 일직선상에 있는경우
	            //또는
	            //i번째 퀸의 위치와 level위치의 퀸이 대각선상에 있는 경우.
	            //밑변과 높이가 같으면 대각선상에 있다고 볼수 있다.
	            return false;
	            //이 경우는 불가능 하므로 false를 리턴
	        }
	    }
	    return true;
	    //위의 경우를 제외하면 가능하므로 true 리턴
	}



}
//class N_Queens{
//    private int N;      //N*N할때의 N;
//    private int cols[]; //i행에 들어갈 퀸의 좌표.
//     
//    public N_Queens(int N){
//        this.N=N;
//        cols = new int[N];
//    }
//     
//}

