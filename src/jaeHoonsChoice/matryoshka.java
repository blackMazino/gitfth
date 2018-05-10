package jaeHoonsChoice;

import java.io.*;
import java.util.*;

public class matryoshka {
	static int N;
    static int[] x, d; 
    
    public static void main(String[] args) throws Exception {        
        // TODO Auto-generated method stub
    	//System.setIn(new FileInputStream("sample/matryoshka.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
    
        x = new int[N+1]; 
        d = new int[N+1];        
        int ans = 0;
        
        d[0] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            x[i] = Integer.parseInt(st.nextToken()); 
            
            if (i==1) { 
            	d[1] = 1; 
            	continue;
            }
            
            for (int j = i-1; j > 0; j--) {
                if (x[i] > x[j]) {
                    d[i] = Math.max(d[i], d[j]+1);
                } 
                else { 
                	if (d[i] < 2 )
                        d[i] = 1; 
                }
            }
            ans = Math.max(ans,  d[i]);
        }
        bw.write(ans + "\n");
       
        bw.flush();
        bw.close();
        br.close();
    }
}
