package class_swProblemSolving;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;




public class source {
  
 public static int N;
 
 public static void main(String[] args) throws NumberFormatException, IOException{
  
  // input 입력처리
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  N = Integer.parseInt(br.readLine());
  
  System.out.println("[" + N + "]");
  
  int[] ibrArray = new int[N];
  
  System.out.println("[" + ibrArray.toString() + "]");
  
  for(int i = 0; i < N; i++){
   BufferedReader ibr = new BufferedReader(new InputStreamReader(System.in));
   ibrArray[i] =  Integer.parseInt(ibr.readLine());
   
   System.out.println("[" + ibrArray[i] + "]");
  
//  StringTokenizer st;
//st = new StringTokenizer(br.readLine());
//
//
//  N = Integer.parseInt(st.nextToken());
//  
//  System.out.println("[" + N + "]");
//  
//  int[] ibrArray = new int[N];
//  
//  System.out.println("[" + ibrArray.toString() + "]");
//
//st = new StringTokenizer(br.readLine());
//  
//  for(int i = 0; i < N; i++){
//
//   ibrArray[i] =  Integer.parseInt(st.nextToken());
//
//System.out.println("[" + ibrArray[i] + "]");


  }
 }
}