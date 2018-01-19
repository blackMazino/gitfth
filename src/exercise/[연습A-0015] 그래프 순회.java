import java.io.*; 
import java.util.*; 
 
public class Solution { 
    static int V; 
    static int[][] mat; 
 
    public static void dfs(int idx) { 
        Stack<Integer> s = new Stack(); 
        s.add(idx); 
        boolean[] chk = new boolean[V + 1]; 
 
        while (!s.isEmpty()) { 
            int k = s.pop(); 
            if (!chk[k]) { 
                chk[k] = true; 
                System.out.print(k + " "); 
                for (int i = V; i >= 1; i--) { 
                    if (mat[k][i] == 1 && !chk[i]) 
                        s.add(i); 
                } 
            } 
        } 
        System.out.println(); 
    } 
 
    public static void bfs(int idx) { 
        Queue<Integer> s = new LinkedList(); 
        s.add(idx); 
        boolean[] chk = new boolean[V + 1]; 
 
        while (!s.isEmpty()) { 
            int k = s.poll(); 
            if (!chk[k]) { 
                chk[k] = true; 
                System.out.print(k + " "); 
                for (int i = 1; i <= V; i++) { 
                    if (mat[k][i] == 1 && !chk[i]) 
                        s.add(i); 
                } 
            } 
        } 
        System.out.println(); 
    } 
 
    public static void main(String[] args) throws Exception { 
 
    //System.setIn(new FileInputStream("adv_3_graph.txt")); 
 
    Scanner sc = new Scanner(System.in); 
 
    int T = sc.nextInt(); 
 
    for (int t = 1; t <= T; t++) { 
        System.out.print("#" + t + " "); 
        int Answer = 0; 
        V = sc.nextInt(); 
        int E = sc.nextInt(); 
        int S = sc.nextInt(); 
 
        int[] arr = new int[V + 1]; 
        mat = new int[V + 1][V + 1]; 
 
        for (int i = 0; i < E; i++) { 
            int s = sc.nextInt(); 
            int e = sc.nextInt(); 
            mat[s][e] = 1; 
            mat[e][s] = 1; 
        } 
 
        dfs(S); 
        bfs(S); 
 
        } 
    } 
}
