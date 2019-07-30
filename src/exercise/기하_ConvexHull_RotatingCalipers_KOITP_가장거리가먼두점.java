package exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
  
public class 기하_ConvexHull_RotatingCalipers_KOITP_가장거리가먼두점 {
  
    static int N;
    static int LIMIT = (int) Math.pow(10, 7);
    static int i,j, z,dupCnt;
    static long maxDistance;
    static XY[] arr;
    static int [] stkArr; 
    static LinkedList<Integer> stk;
    static XY P, farthestPoint1,farthestPoint2 ;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//      BufferedReader br = new BufferedReader(new FileReader("src/exercise/Previous181110.txt"));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine().trim());
        arr = new XY[N];
        P = new XY(LIMIT,LIMIT);
        for(int n=0;n<N;n++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            arr[n] = new XY(x,y);         
            //Presort
//	            if(P.y >= y){
//	            	if(P.x>x){
//	            		P.x = x; P.y=y; 
//	            	}
//	            }
            if(P.x>x){
            	P.x = x;
            	P.y = y;
            }else if(P.x==x && P.y > y){
            	P.y = y;
            }
        }
        maxDistance = 0;
        sortPoint();
        stkArr = convexHull();
        rotatingCalipers();
//        System.out.println(Math.round(answer*1000)/1000);
//        System.out.println(farthestPoint1.x+" "+farthestPoint1.y+" "+farthestPoint2.x+" "+farthestPoint2.y);
        System.out.println(maxDistance);
    }
    private static void sortPoint() {
    	
    	//P에 대한 상대좌표계로 전환
//        for(int i=1;i<N;i++) {
//        	arr[i] = vectorMinus(arr[i], P);
//        }
          
//        Arrays.sort(arr, 1,N, new Comparator<XY>() {
//  
//            @Override
//            public int compare(XY a, XY b) {
//                int val = ccw(arr[0], a,b);
//                if(val >0) {
//                    return -1;
//                }else if(val<0) {
//                    return 1;
//                }else {
//                    double disA = sqrDistance(a, arr[0]);
//                    double disB = sqrDistance(b, arr[0]);
//                    double dis  = disA - disB;
//                    if(dis>0) return 1;
//                    if(dis<0) return -1;
//                    return 0;
//                }
//            }
//  
//        });
		
        Arrays.sort(arr, new Comparator<XY>() {
        	  
            @Override
            public int compare(XY a, XY b) {
                int val = ccw(P, a,b);
                if(val >0) {
                    return -1;
                }else if(val<0) {
                    return 1;
                }else {
                    double disA = sqrDistance(P, a);
                    double disB = sqrDistance(P, b);
                    double dis  = disA - disB;
                    if(dis>0) return 1;
                    if(dis<0) return -1;
                    return 0;
                }
            }
  
        });
	}    
    
//    private static void rotatingCalipers() {
//        int p1 = 0;
//        int p2 = 1;
//        XY zero = new XY(0,0);
//        for(p1=0;p1<CH.size();p1++) {
//            int nextP1 = (p1+1) % CH.size(); //사이즈를 초과하면 첨부터
//            int nextP2 = (p2+1) % CH.size(); //사이즈를 초과하면 첨부터
//            while( ccw( zero, vectorMinus(CH.get(p1), CH.get(nextP1)), vectorMinus(CH.get(p2), CH.get(nextP2)) ) <0) {
//                p2 = nextP2;
//                nextP2  = (p2+1) % CH.size();
//            }
//            answer = Math.max(answer, sqrDistance(CH.get(p1),CH.get(p2)));
//        }
//             
//    }
        
	private static void rotatingCalipers() {
    	int idx1, idx2, ni, nj;
    	idx1=idx2=ni=nj=0;
    	
    	int chSize = stkArr.length;
    	if(chSize <3){
    		farthestPoint1 = arr[stkArr[0]];
    		farthestPoint2 = arr[stkArr[1]];
    		maxDistance = sqrDistance(farthestPoint1, farthestPoint2);
    		return;
    	}
    	
    	XY zero = new XY(0,0);
        for(int i=0, j=1;i<chSize;i++){
        	ni = (i+1)%chSize;
        	while(true){
        		nj = (j+1)%chSize;
        		if(ccw(zero
        			 , vectorMinus(arr[stkArr[ni]], arr[stkArr[i]])
        			 , vectorMinus(arr[stkArr[nj]], arr[stkArr[j]])) > 0){
        			j = nj;
        		}else{
        			break;
        		}
        	}
        	long thisDis = sqrDistance(arr[stkArr[i]],arr[stkArr[j]] );
        	if(maxDistance<thisDis){
        		maxDistance = thisDis;
        		idx1 = i; idx2 =j; 
        	}        	
        }        
        farthestPoint1 = arr[stkArr[idx1]];
        farthestPoint2 = arr[stkArr[idx2]];        
    }

    private static int[] convexHull() {
          
//      printArray();

        int[] CH = new int [N];
        int idx = 0;
        
        //원점의 중복이 몇개인지 확인
//      z=0;
//      while(true) {
////            if(arr[z]!=arr[0]) break;
//          if(arr[z].x!=arr[0].x || arr[z].y!=arr[0].y) break;
//          z++;
//      }
          
//        stk  = new LinkedList<>();        
//        stk.add(0);        
//        for(int i=1;i<N;i++ ) {
//            while(stk.size() > 1 && ( ccw ( arr[stk.size()-2], arr[stk.getLast()], arr[i]) <=0) ) {
          for(int i=0;i<N;i++){
            while(idx > 1 && ( ccw ( arr[CH[idx-2]], arr[CH[idx-1]], arr[i]) <=0) ) {
//                stk.removeLast();
                idx--;
            }
//          if(stk.size()>1 && ccw(arr[stk.size()-2], arr[stk.getLast()], arr[i])==0 
//          && arr[stk.getLast()].x == arr[i].x && arr[stk.getLast()].y == arr[i].y) {
//              dupCnt++;
//              continue;
//          }
//            stk.addLast(i);
            CH[idx] = i; idx++;
        }           
        return Arrays.copyOf(CH, idx);
          
    }
    public static XY vectorMinus(XY a, XY b) {
        return (new XY(a.x-b.x, a.y-b.y));
    }
      
    private static long sqrDistance(XY a, XY b) {     
        return (long) (Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
    }
    private static void printArray() {
        for(int i=0;i<arr.length;i++) {
            System.out.println(i+" "+arr[i].x+ " "+arr[i].y);
        }
          
    }
    public static int ccw(XY a, XY b, XY c) {
        long val = (long)(a.x*b.y) - (long)(b.x*a.y)
                 + (long)(b.x*c.y) - (long)(c.x*b.y)
                 + (long)(c.x*a.y) - (long)(a.x*c.y);
        if(val > 0) return 1;
        if(val < 0) return -1;
        return 0;
    }
  
}
  
class XY{
    long x,y;
  
    public XY(long x, long y) {
        super();
        this.x = x;
        this.y = y;
    }
}