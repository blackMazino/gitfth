package class_swProblemSolving;

import java.util.ArrayList;
import java.util.*;

public class referenceBook_dijkstra {

//}
//class Dijkstra {  
	static final int _M_size_ = 100; // max number of vertices  
	static final int _INTMAX_ = 2087654321; // infinite  
   
	// adjacent list  
	static ArrayList<int[]> AdjList[] = new ArrayList[_M_size_];  
	// store the least cost of the vertices  
	static int visited[] = new int[_M_size_];  	
	
	// dest 값을 주지 않으면 목적지를 특정하지 않음.  
	// 목적지가 없으면 끝까지 수행  
	static void dijkstra(int start) {  
	   dijkstra(start, -1);  
	}  		    
	
	static void dijkstra(int start, int dest) {  
		 PriorityQueue<int[]> PQ = new PriorityQueue<>(new Comparator<int[]>(){
		     public int compare(int a[], int b[]) {  
		         return a[1] - b[1];  // min-heap  
		     }  
		 });  
			         	
		 for(int i=0; i<_M_size_; i++) visited[i] = _INTMAX_; //visited init
		 visited[start] = 0;        
		 PQ.add(new int[]{start, 0});  
		 while(!PQ.isEmpty()) {  
		     int curr[] = PQ.poll();  
		     if(curr[0] == dest) break; // found dest cost          
		     // true the next vertex  
		     for(int i=0; i<AdjList[curr[0]].size(); i++) {  
		         int next[] = AdjList[curr[0]].get(i).clone();  
		         next[1] += curr[1];  
		         if(next[1] < visited[next[0]]) { // is shorter  
		             visited[next[0]] = next[1];  
		             PQ.add(next);  
		         }  
		     }  		
		 }  	
	}  

}