package referenceBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Dfs_stack {
	static int M_size = 100; //Max No. of vertices
	
	//adjacent List
	static ArrayList<Integer> AdjList[] = new ArrayList[M_size];
	//check if the vertex is visited
	static boolean visited[] = new boolean[M_size];
	
	//using LinkedList instead of Stack (performence issue)
	//int[] - [0] : curr vertex info, [1]:AdjList vertex order	
	static LinkedList<int[]>S = new LinkedList<>();	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<TC;tc++){
			
		}

	}

	static void dfs_stack(int start_vertex){
		int order = -1, i;
		
		for(i=0; i< M_size;i++) visited[i] = false; //visited init
		S.clear(); //Stack init
		visited[start_vertex] = true;
		S.addLast(new int[]{start_vertex, -1});

		
		//find next vertex and go
		while(!S.isEmpty()){
			int curr_vertex = S.getLast()[0];
			for(i=order+1; i<AdjList[curr_vertex].size();i++){
				int next_vertex = AdjList[curr_vertex].get(i);
				if(!visited[next_vertex]){
					visited[next_vertex] = true;
					S.addLast(new int[]{next_vertex, i});
					order = -1;
					break;
				}
			}
			if(i== AdjList[curr_vertex].size())	order = S.removeLast()[1]; //no more next_vertex
		
		}
	}
}
