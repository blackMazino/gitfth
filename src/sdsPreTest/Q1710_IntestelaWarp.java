package sdsPreTest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Q1710_IntestelaWarp{
	static int X,Y,M,W;//space size,cnt of Meteor,cnt of Warp		
	static int [][] Space;
	static List<int[]> warpInfo = new ArrayList<int[]>();	
	static List<int[]> g = new ArrayList<int[]>();
	static boolean mininf;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
					
		for(int tc=1; tc<=T;tc++){
			mininf = false;
			warpInfo.clear();//List clear
			g.clear();
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			Space = new int[X][Y];
	
			//Meteor
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());			
			
			for(int m=0; m<M;m++){
				st = new StringTokenizer(br.readLine());
				int xm = Integer.parseInt(st.nextToken());
				int ym = Integer.parseInt(st.nextToken());
				Space[xm][ym] = -1;//value of meteor location is -1; 
			}				
						
			//Warp
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			for(int w=0; w<W;w++){
				st = new StringTokenizer(br.readLine());
				int [] i = new int[5];	
				i[0] = Integer.parseInt(st.nextToken());//xs
				i[1] = Integer.parseInt(st.nextToken());//ys
				i[2] = Integer.parseInt(st.nextToken());//xe
				i[3] = Integer.parseInt(st.nextToken());//ye
				i[4] = Integer.parseInt(st.nextToken());//tw
				warpInfo.add(i);
				//Idx convergence : X*yi+xi
				int [] d = new int[3];
				d[0] = (i[1]*X)+i[0];//start Idx
				d[1] = (i[3]*X)+i[2];//end Idx
				d[2] = i[4];//weight
				g.add(d);		
				
			}			
			for(int x=0;x<X;x++){
				for(int y=0;y<Y;y++){
                   /*
					if(x==X-1 && y==Y-1){
						continue;
					}
					if(isWarpPoint(x,y, true)){
						continue;
					}*/
                    if(!((x==X-1 && y==Y-1)) && !isWarpPoint(x,y, true)){					
						//to right
						if(x+1<X){
							addWayInfo(x,y,x+1,y,1);
						}
						//to left
						if(x-1>-1){
							addWayInfo(x,y,x-1,y,1);
						}					
						//to down
						if(y+1<Y){
							addWayInfo(x,y,x,y+1,1);
						}
						//to up
						if(y-1>-1){
							addWayInfo(x,y,x,y-1,1);
						}					
                    }
				}			
			}
				
			
			//conclusion
			int totTime = BellmanFord();
			printP();
            if(mininf){
					System.out.println("#"+tc+" mininf");	
            }else{
                if(totTime == Integer.MAX_VALUE){
					System.out.println("#"+tc+" noway");				
				}else{
					System.out.println("#"+tc+" "+totTime);
				}
            }				
		}
		br.close();
	}

	


	private static void printP() {
		// TODO Auto-generated method stub
		for(int [] tmp : g){
			System.out.println(tmp[0]+","+tmp[1]+","+tmp[2]);
		}
	}




	private static int BellmanFord() {
		int result = Integer.MAX_VALUE;
		int sizeOfAllNodes =X*Y; 
		int [] distance = new int[sizeOfAllNodes];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0;

		//printList(g);
        
		for(int i=0; i<sizeOfAllNodes;i++){
            //mininf = false;
			for(int[] tmp : g){
				//System.out.println(Arrays.toString(distance));
				if(distance[tmp[0]] != Integer.MAX_VALUE ){
					if(distance[tmp[1]] > distance[tmp[0]] + tmp[2]){					
						distance[tmp[1]] = distance[tmp[0]] + tmp[2];	
						
						if (i == sizeOfAllNodes-1) {
							mininf = true;					
						}
                        if(mininf) break;
					}	
				}				
			}
//			if(distance[g.get(i)[0]] != Integer.MAX_VALUE ){
//				if(distance[g.get(i)[1]] > distance[g.get(i)[0]] + g.get(i)[2]){					
//					distance[g.get(i)[1]] = distance[g.get(i)[0]] + g.get(i)[2];					
//				}	
//			}
		}
		
		//System.out.println(Arrays.toString(distance));
		//if(distance[X*Y-1] != Integer.MAX_VALUE){
			
		
		
//		//negative cycle search
//		for(int i=0; i<g.size();i++){
//			for(int[] tmp : g){
//				if(distance[tmp[1]] > distance[tmp[0]] + tmp[2]){
//					mininf = true;
//					break;
//				}
//			}
//		}
		//}
		//System.out.println(result);
		//System.out.println(distance[warpInfo.size()-1]);
		//Arrays.sort(distance);
		result = distance[X*Y-1];
		
		return result;
	}


	private static void printList(List<int[]> aList) {
		for(int[] tmp :aList){
			for(int i=0; i<tmp.length;i++){
				System.out.print(tmp[i]+" ");
			}
			System.out.println();
		}
		
	}

	private static void addWayInfo(int xs, int ys, int xe, int ye, int tw) {	
		if(!isMeteorPoint(xe,ye) && !isMeteorPoint(xs,ys)){
//			int [] i = new int[5];
//			i[0] = xs;
//			i[1] = ys;
//			i[2] = xe;
//			i[3] = ye;
//			i[4] = 1;
			//warpInfo.add(i);	
			int [] d = new int [3];
			//Idx convergence : X*yi+xi
//			d[0] = (i[1]*X)+i[0];//start Idx
//			d[1] = (i[3]*X)+i[2];//end Idx
			d[0] = (ys*X)+xs;//start Idx
			d[1] = (ye*X)+xe;//end Idx
			d[2] = tw;//weight
			g.add(d);
		}
	}

	private static boolean isMeteorPoint(int i, int j) {
		boolean result = false;		
		if(Space[i][j] < 0){
			result = true;
		}		
		return result;		
	}


	private static boolean isWarpPoint(int i, int j, boolean isStartPoint) {
		boolean result = false;				
		for(int[] tmp : warpInfo){
			if(isStartPoint){
				if(tmp[0] == i && tmp[1]==j){
					result = true;
                    return true;
					//break;
				}				
			}else{
				if(tmp[2] == i && tmp[3]==j){
					result = true;
					break;
				}
			}
		}
		return result;
	}

}

