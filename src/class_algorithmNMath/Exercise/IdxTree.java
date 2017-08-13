package class_algorithmNMath.Exercise;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IdxTree {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		Scanner sc = new Scanner(System.in);
		int TC = //sc.nextInt();
				Integer.parseInt(st.nextToken());
		 
		for(int i=1;i<=TC;i++){			
			st = new StringTokenizer(br.readLine());
			int N = //sc.nextInt();
					Integer.parseInt(st.nextToken());//Cnt of leaf node			
			int [] tree = new int [2*N];
			//fill leaf node
			st = new StringTokenizer(br.readLine());
			for(int a=N;a<2*N;a++){
				tree[a] = //sc.nextInt();
						Integer.parseInt(st.nextToken());
			}
			//fill full idx tree
			for(int a=N-1;a>0;a--){//뒤에서(아래서)부터 채운다
				tree[a] = tree[2*a]+tree[2*a+1];
			}
			printThisTree(tree);
			
			//update tree
			st = new StringTokenizer(br.readLine());
			int n = //sc.nextInt()-1;
					Integer.parseInt(st.nextToken())-1;//update point
			int v = //sc.nextInt();
					Integer.parseInt(st.nextToken());//update value			
			int diff =  Math.abs(v - tree[n+N]);
			for(n+=N;n>0;n/=2) tree[n] += diff;
			printThisTree(tree);

			//get Sum
			int l = //sc.nextInt();
					Integer.parseInt(st.nextToken());//search left point
			int r = //sc.nextInt();
					Integer.parseInt(st.nextToken());//search right point
			int sum = 0;
			for(sum=0, l+=N, r+=N; l<=r ; l=(l+1)/2,r=(r-1)/2){
				if(l%2==1) sum+=tree[l]; //왼쪽은 홀수일때만 더해준다
				if(r%2==0) sum+=tree[r]; //오른쪽은 짝수일때만 더한다
			}
			
			System.out.println(sum);
		}
		sc.close();
	}

	private static void printThisTree(int[] tree) {
		// TODO Auto-generated method stub
		System.out.println("==========================================");
		for(int i=0;i<tree.length;i++){
			System.out.print(tree[i]+" ");
		}
	}
/*
index tree
update(int n, int v)
{
    int diff = v - tree[n+S] 
    for(n += S;n;n/=2) tree[n]+=diff;
}
int sum(int l, int r)
{
    for(sum=0,l += S,r += S;l<=r;l = (l+1)/2,r = (r-1)/2)
    {
        if (l%2==1) sum+=tree[l];
        if (r%2==0) sum+=tree[r];
return sum;
}
for(int i=S;i>0;i--) tree[i] = tree[2*i]+tree[2*i+1];
*/

/*
index tree(대표값MAX)
 update(int n, int v)
{
    n+=S;

    tree[n] = v;
    while(n=/2)
    {
        tree[n] = max(tree[n],v);
    }
}

query(int a, int b)
{
    int max_v = 0;
    left right += s;
    while(left<=right)
    {
        if (left%2==0) max_v = max(max_v,tree[left])
        ...
        left = (left+1)/2;
        ....
*/
}
