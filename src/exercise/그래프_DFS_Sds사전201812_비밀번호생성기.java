package exercise;
  
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
  
public class 그래프_DFS_Sds사전201812_비밀번호생성기 {
    /*
    비밀번호 첫번째 = (일 + 초 )mod 36
    비밀번호 마지막 = (월 + 시 + 분)mod 36
    최소 숫자   = 분의 10의자리수
    최소대문자 = 초의 10의자리수
  
    2
    10/12 02:52:58
    10/22 02:52:48
  
    #1 2
    #2 10
  
    */
  
        static int TC, fst, last, minNo, minCap, answer;
        static int mon, day, hour, min, sec;
        static ArrayList<Integer> con[];  
//      static ArrayList<List<Integer>> ansList;
        static HashSet<String> ansSet;
//      static int distance[][] = new int[35][35];
//      static String [] K;
//      static boolean [] visited;
        static int [] vis;
        static boolean [] isNo;
        static int mod = 36;
        static String TimeStamp;
  
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("src/exercise/사전201812.txt"));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());
        makeKeyboard();
        for(int tc=1;tc<=TC;tc++){
            TimeStamp = br.readLine();
            makeRule(tc);
            ansSet = new HashSet<String>();
            bitMasking();
            LinkedList<Integer> pw = new LinkedList<Integer>();
            pw.add(fst);            
            vis[fst]--;
//          dfs(fst, 1, isNo[fst]?1:0,pw);
            dfs(fst, vis,pw);
//          System.out.println(combination(6,2));
            answer = 0;
            for(String s : ansSet){
//              System.out.println(s);
                answer += getCombinataion(s);
            }
            System.out.println("#"+tc+" "+answer);
        }
    }
      
    private static int getCombinataion(String s) {
        int val = 0;
        String [] sArr = s.split(",");
        int nCnt = 0;
        for(int i=0;i<sArr.length;i++){
            int idx = Integer.parseInt(sArr[i]);
            if(isNo[idx]) nCnt++;
        }
        int lCnt = 10 - nCnt;
          
        if(lCnt == minCap) {
             int dupCnt = 0;
            for(int i=0;i<9;i++){
                dupCnt = 0;
                int idx = Integer.parseInt(sArr[i]);
                if(isNo[idx]) continue;
                for(int j=i+1;j<10;j++){                 
                    if(sArr[i].equals(sArr[j])){
                        dupCnt++;
                    }
                }
                if(dupCnt>0)break;                
            }   
            return (dupCnt==0)?1:0;
        }else if(lCnt>minCap){
            int dupCnt = 0;
            for(int i=0;i<9;i++){
                int idx = Integer.parseInt(sArr[i]);
                if(isNo[idx]) continue;
                for(int j=i+1;j<10;j++){                 
                    if(sArr[i].equals(sArr[j])){
                        dupCnt++;
                    }
                }
            }   
            if(dupCnt > minCap) return 0;
            if(dupCnt == minCap) return (int)Math.pow(2,dupCnt);
            int dup = (int)Math.pow(2, dupCnt);
            int notDupCnt = lCnt-(2*dupCnt);
             
            int c = combination(notDupCnt, minCap-dupCnt);  
              
            return c;
        }else{
            return 0 ;
        }
    }
    private static int combination(int a, int b) {
        int u = 1;
        int d = 1;
        for(int i=a;i>a-b;i--){
            u *= i;
        }
        for(int i=b;i>=1;i--){
            d *= i;
        }
        return u/d;
    }
  
    private static void dfs(int i, int[] vis, LinkedList<Integer> pw) {
        if(pw.size()>9){
            return;
        }else{
//          System.out.println(i+" "+pw.getLast()+" "+pw.size());
            if(pw.size()==9){               
                for(int n:con[i]){
//                  if(i==9) System.out.println("i is 9, "+n);                    
                    if(n==last && vis[n]>0){
//                      if(i==19) System.out.println("i is 19, "+n + ","+vis[n]+ ","+checkNoCnt(pw));
//                      System.out.println("LAST!! [" +n+"]");
                        int nCnt = checkNoCnt(pw);
                        int lCnt = 10 - nCnt;
                        if(nCnt + (isNo[n]?1:0) >= minNo){
                            pw.addLast(n);                          
                            ansSet.add(print(pw));
                            pw.removeLast();
                        }
                    }
                }
            }else{
                for(int n:con[i]){
                    if(vis[n]>0 && isCanInclude(pw,n)){
                        vis[n]--;
                        pw.addLast(n);
                        dfs(n,vis,pw);
                        vis[n]++;
                        pw.removeLast();
                    }
                }
            }
        }
          
    }
  
    private static boolean isCanInclude(LinkedList<Integer> pw, int n) {
        int dupCnt = 0;
        int L = isNo[n]?0:1;
        for(int e:pw){
            if(e==n) dupCnt++;
        }               
        return (dupCnt<=L);
    }
  
    private static int checkNoCnt(LinkedList<Integer> pw) {
        int nCnt = 0;       
        for(int i=0;i<pw.size();i++){
//          System.out.print("["+i+"]"+pw.get(i)+" ");
            if(isNo[pw.get(i)]) nCnt++;
        }
//      System.out.println(nCnt);
        return nCnt;
    }
    private static String print(LinkedList<Integer> pw) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<pw.size();i++) {
            if(i>0) sb.append(",");
            sb.append(pw.get(i));           
//          System.out.print(pw.get(i)+" ");
        }
//      System.out.println("");
        return sb.toString();
    }
  
    private static void bitMasking() {
        vis = new int[36];
        for(int i=0;i<36;i++){
            vis[i] = isNo[i]?1:2;
        }
          
    }
 
    private static void makeRule(int tc) {
        char[] c = TimeStamp.toCharArray();
        mon  = Integer.parseInt(String.valueOf(c[0])) * 10 + Integer.parseInt(String.valueOf(c[1]));
        day  = Integer.parseInt(String.valueOf(c[3])) * 10 + Integer.parseInt(String.valueOf(c[4]));
        hour = Integer.parseInt(String.valueOf(c[6])) * 10 + Integer.parseInt(String.valueOf(c[7]));
        min  = Integer.parseInt(String.valueOf(c[9])) * 10 + Integer.parseInt(String.valueOf(c[10]));
        sec  = Integer.parseInt(String.valueOf(c[12])) * 10 + Integer.parseInt(String.valueOf(c[13]));
          
        fst = (day+sec)%mod;
        last = (mon+hour+min)%mod;
        minNo = Integer.parseInt(String.valueOf(c[9]));
        minCap = Integer.parseInt(String.valueOf(c[12]));
//      System.out.println(tc+" RULE : "+fst+" "+last+" "+minNo+" "+minCap);    
    }
  
    private static void makeKeyboard() {
        isNo = new boolean [36];
        Arrays.fill(isNo, 0, 10, true);
        con = new ArrayList[36];
        for(int i=0;i<36;i++) con[i] = new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            int l = i-1; int r=i+1; int dr = i+10; int dl = i+9;
            if(l>=0 && l<=9) con[i].add(l); 
            if(r<=9 && r>=0) con[i].add(r);
            if(dr<=19 && dr>=10) con[i].add(dr);
            if(dl>=10 && dl<=19)  con[i].add(dl);
        }
        for(int i=10;i<20;i++){
            int l = i-1; int r=i+1;  int dl = i+9;int dr = i+10;  int ul = i-10; int ur = i-9;   
            if(l>=10 && l<=19) con[i].add(l); 
            if(r<=19 && r>=10) con[i].add(r);                 
            if(dl>=20 && dl<=28) con[i].add(dl);
            if(dr<=28 && dr>=20) con[i].add(dr);
            if(ul>=0 && ul<=9) con[i].add(ul);
            if(ur<=9 && ur>=0) con[i].add(ur);            
                          
        }
        for(int i=20;i<29;i++){
            int l = i-1; int r=i+1; int dl = i+8; int dr = i+9;  int ul = i-10; int ur = i-9; 
            if(l>=20 && l<=28) con[i].add(l); 
            if(r<=28 && r>=20) con[i].add(r);
            if(dl>=29 && dl<=35) con[i].add(dl);
            if(dr<=35 && dr>=29) con[i].add(dr);
            if(ul>=10 && ul<=19) con[i].add(ul);
            if(ur<=19 && ur>=10) con[i].add(ur);                      
        }
        for(int i=29;i<36;i++){
            int l = i-1; int r=i+1;int ul = i-9; int ur = i-8; 
            if(l>=29 && l<=35) con[i].add(l); 
            if(r<=35 && r>=29) con[i].add(r);
            if(ul>=20 && ul<=28) con[i].add(ul);
            if(ur<=28 && ur>=20) con[i].add(ur);
        }
          
        for(int i=0;i<=35;i++){
            Collections.sort(con[i]);
        }
//      for(int i=0;i<=35;i++){      
//          for(int t : con[i]){
//              System.out.print(i+"->"+t+" ");              
//          }
//          System.out.println("");
//      }
          
    }
  
  
  
}