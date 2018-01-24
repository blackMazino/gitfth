package class_swProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class homework3_중앙문자열 {
/*
문제
문자열에서 교체 연산은 문자열의 한 문자를 다른 문자로 바꾸는 연산이다. 예를 들어, 문자열 “computer”에서 4번째 문자 p를 m으로 교체하면 “commuter”가 된다.

같은 길이의 두 문자열 P와 Q의 거리 d(P,Q)는 P를 Q로 바꾸기 위한 교체 연산의 최소 개수로 정의된다. 
예를 들어 P = “computers”, Q = “consumers”라 하면, P에서 3번째 문자 m을 n으로, 4번째 문자 p를 s로, 6번째 문자 t를 m으로 바꾸면 Q가 된다. 
따라서 P와 Q 사이의 거리는 3이다.

A, B, C를 같은 길이의 문자열이라 하자. 이 때 어떤 문자열 W의 반경 r(W)는 문자열 W와 문자열 A, B, C와의 거리 중 최대값으로 정의된다. 
즉, r(W)= max{ d(W,A), d(W,B), d(W,C) } 이다. 
예를 들어, A = “computers”, B = “consumers”, C = “consensus”일 때, X = “consunsrs”라 두면,
A, B, C와 X 사이의 거리는 d(X,A) = 4, d(X,B) = 2, d(X,C) = 2이므로, X의 반경 r(X)는 4이다.

문자열 A, B, C의 중앙문자열은 A, B, C와의 반경이 최소가 되는 문자열로 정의된다. 
예를 들어, 문자열 A, B, C가 위와 같이 주어졌을 때, Y = “consuners”라 두면, Y의 반경은 3이고, 반경이 2인 문자열은 존재하지 않으므로, Y는 A, B, C의 중앙문자열이 된다.

영어 소문자들로만 구성된 문자열 A, B, C가 주어졌을 때, 이들의 중앙문자열을 구하는 프로그램을 작성하라.

입력
첫 번째 줄에 문자열 A가 주어진다.

두 번째 줄에 문자열 B가 주어진다.

세 번째 줄에 문자열 C가 주어진다.

세 문자열 A, B, C의 길이는 동일하고, 그 길이는 1 이상 100,000 이하이다.

출력
첫 번째 줄에 A, B, C의 중앙 문자열이 가지는 반경을 출력한다. 이 때, 찾은 중앙 문자열을 출력할 필요는 없다

computers
consumers
consensus

3
*
*/
	
    static int N;
    static String[] S = new String[3];
      
    static boolean proc(int m) {
        int[] H = new int[3];
        int diff = 0;
        for (int i=0;i<N;i++){
            int cnt = 0, last = 0;
            for (int j=0;j<3;j++){
                if (S[j].charAt(i) == S[(j+1)%3].charAt(i)){
                    cnt++;
                    last = j;
                }
            }
            if (cnt == 1)
                H[3 - last - (last+1)%3]++;
            else if (cnt == 0)
                diff++;
        }
        Arrays.sort(H);
        while (H[2] > m){
            H[0]++; H[1]++;
            H[2]--;
        }
        if (H[1] > m) return false;
        for (int i=0;i<diff;i++){
            int t = 0;
            for (int j=1;j<3;j++) if (H[t] < H[j]) t = j;
            for (int j=0;j<3;j++) if (t != j) H[j]++;
        }
        Arrays.sort(H);
        return H[2] <= m;
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0;i<3;i++) S[i] = br.readLine();
        N = S[0].length();
        int s = 0, e = N, ans = 0;
        while (s <= e){
            int m = s+e >> 1;
            if (proc(m)){
                e = m-1;
                ans = m;
            }
            else s = m+1;
        }
        System.out.println(ans);
    }
}
