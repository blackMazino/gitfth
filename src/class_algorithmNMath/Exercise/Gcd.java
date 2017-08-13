package class_algorithmNMath.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//https://gist.github.com/seungdols/245275d1bca9fb02db16
public class Gcd {
	/* 유클리드 호제법으로 gcd구하기
	 * a1   b1   r1(=a1%b1)
	 * b1   r1   r2
	 * r1   r2   r3
	 * ...
	 * an   bn   rn=0 
	 * bn  0     rn+1-> return bn(=a);
	 * */		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;		
//		st = new StringTokenizer(br.readLine());		
//		int TC = Integer.parseInt(st.nextToken());
//		for(int i=1;i<=TC;i++){
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		//System.out.println(gcd(a, b));
		System.out.println(expandedEuclidGcd(a, b));
	}

	//ax+by = 1
	private static int expandedEuclidGcd(int a, int b) {
		//a, b를 서로소로 만드는게 우선, 서로소라고 가정하고 진행		
		int[]s = new int [3];
		int[]t = new int [3];
		int[]r = new int [3];
		int q =0;
		s[0]=t[1]=1;
		s[1]=t[0]=0;
		r[0]=a;
		r[1]=b;	
		while(true){
			q=r[0]/r[1];//q = r''/r'
			r[2]=r[0]%r[1];//r=r''%r'
			
			s[2]=s[0] - q*s[1];			
			t[2]=t[0] - q*t[1];
			
			if(r[2] == gcd(a,b)) break;
			
			//한칸씩 내린
			r[0]=r[1]; r[1]=r[2];
			s[0]=s[1]; s[1]=s[2];
			t[0]=t[1]; t[1]=t[2];
		}		
		return t[2];
	}

	private static int gcd(int a, int b) {//
		if(b==0) return a;
		//System.out.println(a +","+b+","+a%b);
		return gcd(b, a%b);		 
	}
/*
gcd(int a, int b)
{
    if(b==0) return a;
    return gcd(b, a%b);
}

int s1, s2, s3, t1, t2, t3, r1, r2, r3, q;
r1 = a, r2 =b;
s1 = 1, t1 = 0;
s2 = 0, t2 = 1;

while (1)
{
    r3 = r1 % r2;
    q = r1 / r2;

    s3 = s1 - q*s2;
    t3 = t1 - q*t2;

    if (r3 == gcd(a, b)) break;
    r1 = r2, r2 = r3;
}
s3, t3
*/
}
