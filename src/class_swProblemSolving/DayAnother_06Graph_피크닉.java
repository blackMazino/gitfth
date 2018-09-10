package class_swProblemSolving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DayAnother_06Graph_피크닉 {
			static int K, N , M;
			static int Cow[]; 
			static ArrayList<Integer> Path[];  
			static int Meet[][];
			static  int Answer = 0;
			public static void main( String[] args ) throws Exception{
		          BufferedReader br = new BufferedReader( new InputStreamReader(System.in ) ); 
		       	
		          StringTokenizer st = new StringTokenizer( br.readLine() ) ; 
		  	    K = Integer.parseInt ( st.nextToken()  );
		      	N = Integer.parseInt ( st.nextToken()  );
		      	M = Integer.parseInt ( st.nextToken()  );
		      	
		          Meet = new int[N+1][K+1];
		      	Cow = new int[K+1];
		      	Path = new ArrayList[N+1];
		      	
		          for( int i = 1; i <= N ; i++ ) Path[i] = new ArrayList();
		          for( int i = 1; i <= K ; i++ ) Cow[i] = Integer.parseInt ( br.readLine() );
		       	
		          for( int i=0; i< M; i++) {
		     	   	 st = new StringTokenizer( br.readLine() ) ; 
		              int a = Integer.parseInt ( st.nextToken()  );
		              int b = Integer.parseInt ( st.nextToken()  );                 	 
		     	   	 
		              Path[a].add( b );
			       } 
		      	
		      	
		          for( int i = 1; i <= K ; i++ ) {
		              find( i  );
		       	}
		      	
		          for( int i = 1; i <= N ; i++ ) {
		     	   	 if(  Meet[i][ 0] == K ) Answer ++;
		      	}
		       	
		          System.out.printf( "%d\n",Answer); 
		               
			}  
		 	




			public static void find ( int cow) {
			  	   
		   	     Queue<Integer> q = new LinkedList();
		   	    
		   	     q.add( Cow[cow]  );  
		   	    
		   	 	while( !q.isEmpty() ) {
		   	        int x = q.poll();

		   	        if( Meet[x][ cow ] == 0  ) {
		   	      	        Meet[x][ cow ] = 1;
		   	      	        Meet[x][ 0] ++;
		   	        }
		   	       
			  	     for( int i  : Path[x]) {
			  	      	    
			  	        if( Meet[i][cow] == 1  ) continue;
			  	        
			  	        Meet[i][cow]  = 1;
			  	        Meet[i][0] ++;
			  	        
			  	       // System.out.printf( "aa===[%d][%d], %d\n ", i, 0, Meet[i][0]);	
			  	        
			  	        
			  	        q.add( i );
			  	     }
			  	      
		   	     }
		   	    
		   	   /*  System.out.printf( "Cow No =%d\n", cow ); 
		   	for( int i=1; i<= N; i++) { 
		 	  for( int j=0; j<= K ; j++) { 
		 	        	 System.out.printf( "%d , ", Meet[i][j]);   	
		 	  }
		 	  System.out.printf( "\n" ); 
		  	}
		  	*/
		   	    
			}
			
			
		}