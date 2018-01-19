import java.util.Scanner;
import java.io.FileInputStream;
 
public class Solution {
    static int[] iVisit;
    static int[][] mat;
    static int Node,Answer1,Answer2,T,N,K;
     
    public static void fDFS(int a) {
        for(int i=1;i<=N;i++) {
            if((iVisit[i]==0)&&(mat[a][i])==1) {
                iVisit[i]=1;
                Node++;
                fDFS(i);
            }
        }
    }
 
    public static void main(String args[]) throws Exception {
         
        Scanner sc=new Scanner(System.in);
         
        T=sc.nextInt();
         
        for(int test_case=1;test_case<=T;test_case++) {
            N=sc.nextInt();
            K=sc.nextInt();
             
            int a,b;
 
            iVisit=new int[2001];
            mat=new int[2001][2001];
 
            for(int i=0;i<K;i++) {
                a=sc.nextInt();
                b=sc.nextInt();
                mat[a][b]=mat[b][a]=1;
            }
             
            Answer1=0;
            Answer2=0;
            for(int i=1;i<=N;i++) {
                if (iVisit[i]==0) {
                    iVisit[i]=1;
                    Node=0;
                    fDFS(i);
                     
                    if (Node>0) {
                        Answer1++;
                    }
                    if(Answer2<Node) {
                        Answer2=Node;
                    }
                }
            }
            Answer2++;
            System.out.println("#"+test_case+" "+Answer1+" "+Answer2);
        }
    }
}
