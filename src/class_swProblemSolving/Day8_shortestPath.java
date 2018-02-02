package class_swProblemSolving;

import java.io.BufferedReader;  
import java.io.InputStreamReader;   
import java.util.ArrayList; 
import java.util.Comparator;    
import java.util.PriorityQueue; 
import java.util.StringTokenizer;   
        
public class Day8_shortestPath{    
    static int T,N,M;   
    static ArrayList<Integer>[] con,conv; 
    static long[] D; 
            
    public static void main(String args[]) throws Exception {   
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
                
//      T=Integer.parseInt(br.readLine());  
                
//      for(int tc=1;tc<=T;tc++) {   
            StringTokenizer st=new StringTokenizer(br.readLine());  
                    
            N=Integer.parseInt(st.nextToken()); 
            M=Integer.parseInt(st.nextToken()); 
                    
            D=new long[N+1]; 
            con=new ArrayList[N+1]; 
            conv=new ArrayList[N+1];    
                    
            for(int i=1;i<=N;i++) {  
                con[i]=new ArrayList<>(); 
                conv[i]=new ArrayList<>();    
            }   
                    
            for(int i=1;i<=M;i++) {  
                st=new StringTokenizer(br.readLine());  
                        
                int a=Integer.parseInt(st.nextToken()); 
                int b=Integer.parseInt(st.nextToken()); 
                int c=Integer.parseInt(st.nextToken()); 
                        
                con[a].add(b); con[b].add(a);   
                conv[a].add(c); conv[b].add(c); 
            }   
                    
            for(int i=1;i<=N;i++) D[i]=Long.MAX_VALUE;    
            
            PriorityQueue<long[]> que=new PriorityQueue<>(10,new Comparator<long[]>() { 
                public int compare(long[] a,long[] b) {   
                    return (int) (a[1]-b[1]);   
                }   
            }); 
            
            D[1]=0; 
                    
            que.add(new long[]{1,0});    
                    
            while(!que.isEmpty()) { 
                int q=(int)que.peek()[0];    
                int d=(int)que.peek()[1];    
                        
                que.poll(); 
                        
                if(D[q]!=d) continue;   
        
                for(int i=0;i<con[q].size();i++) {   
                    int t=con[q].get(i);    
                    int v=conv[q].get(i);   
                            
                    if (D[t]>D[q]+v) {   
                        D[t]=D[q]+v;    
                        que.add(new long[]{t, D[t]}); 
                    }   
                }   
            }   
            System.out.println(D[N]<Long.MAX_VALUE? D[N]:-1);             
//          System.out.println("#"+tc+" "+(D[N]<Integer.MAX_VALUE? D[N]:-1));    
        
//        }   
    }   
}