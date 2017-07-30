package referenceBook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs {
	static int M_size = 100; //Max No. of vertices
	
	//adjacent List
	static ArrayList<Integer> AdjList[] = new ArrayList[M_size];
	//check if the vertex is visited
	static boolean visited[] = new boolean[M_size];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static void bfs (int start_vertex){
		Queue <Integer> Q = new LinkedList<Integer>();
		Q.add(start_vertex);
		visited[start_vertex] = true;
		
		while(!Q.isEmpty()){
			int curr_vertex = Q.poll();
			//find the next vertex and go
			for(int i=0; i<AdjList[curr_vertex].size();i++){
				if(!visited[AdjList[curr_vertex].get(i)]){
					visited[AdjList[curr_vertex].get(i)] = true;
					Q.add(AdjList[curr_vertex].get(i)); //add queue
				}
			}
			
		}
	}
		
}
 