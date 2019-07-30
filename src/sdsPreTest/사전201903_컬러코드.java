package sdsPreTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 사전201903_컬러코드 {

	
/*
#1 3 3 1 2 3 1 2 4
#2 3 6 1 7 4 5 1 2 8 3 1 3 7 3 6 7 9
#3 2 3 6 2 1 10 3 1 4 7 3 2 8 7 3 5 7 10
*/
	static int TC,Q,N;// 1<=Q<=5, 1<=N<=10
	static ArrayList<char[][]> pan, question;
	public static void main(String[] args) throws Exception {
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/exercise/사전201903.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=TC;tc++){        
        	N = Integer.parseInt(br.readLine());
        	pan = new ArrayList<>();
        	for(int n=1;n<=N;n++){
        		char [][] p = new char[8][8];        		
        		for(int i=0;i<8;i++){
        			String l = br.readLine();
        			for(int j=0;j<8;j++){
        				p[i][j]=l.charAt(j);
        			}        			
        		}
        		pan.add(p);
        	}
        	Q = Integer.parseInt(br.readLine());
        	question = new ArrayList<>();
        	for(int q=1;q<=Q;q++){
        		char [][] p = new char[8][8];        		
        		for(int i=0;i<8;i++){
        			String l = br.readLine();
        			for(int j=0;j<8;j++){
        				p[i][j]=l.charAt(j);
        			}        			
        		}
        		question.add(p);
        	}
//        	print(pan);
//        	print(question);
        	bw.write("#"+tc);
        	
//        	for(char [][] q :question){
//        		for(int i=0;i<pan.size();i++){
//        			char[][] p = pan.get(i);
//        			if(!checkP(q,p)){
//        				p = rotate(p);
//        			}
//        		}       		
//        	}        	
        	bw.newLine();
        }
        bw.flush();
        bw.close();

	}
	private static char[][] rotate(char[][] p) {
		char result [][] = new char[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				int ni = (7-j);
				int nj = i;
				result[ni][nj] = p[i][j];
			}
		}
		return result;
	}
	private static boolean checkP(char[][] q, char[][] p) {
		boolean result = true;
		for(int j=1;j<=8;j++){
			boolean lineOk = true;
			for(int k=1;k<=8;k++){				
				if(q[j][k]!=(p[j][k]=='.'?q[j][k]:p[j][k])){
					lineOk = false;
					break;						
				}
			}
			if(!lineOk){
				result = false;
				break;
			}
		}
		return result;
	}
	private static void print(ArrayList<char[][]> list) {
		for(char[][] c : list){
			for(int i=0;i<c.length;i++){
				for(int j=0;j<c[i].length;j++){
					System.out.print(c[i][j]);
				}
				System.out.println();
			}
			System.out.println("==================");
		}
		System.out.println("##################");
	}

}
