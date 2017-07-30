package referenceBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Dfs {
	/**/
	static int M_size = 100; //Max No. of vertices
	
	//adjacent List
	static ArrayList<Integer> AdjList[] = new ArrayList[M_size];
	//check if the vertex is visited
	static boolean visited[] = new boolean[M_size];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<TC;tc++){
			
		}
	}
	
	static void dfs(int curr_vertex){
		visited[curr_vertex] = true;
		
		//find the next vertex and go
		for(int i=0; i<AdjList[curr_vertex].size();i++){
			if(!visited[AdjList[curr_vertex].get(i)]) dfs(AdjList[curr_vertex].get(i));//recursion
			
		}
		
	}

}
