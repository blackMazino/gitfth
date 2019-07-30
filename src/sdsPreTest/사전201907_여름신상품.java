package sdsPreTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

// BOJ 10999 https://bowbowbow.tistory.com/4 
// https://am003507.tistory.com/221

// 세그먼트는 느린데(0.3) 정확하고, 펜윅(0.1)은 빠른데 틀렸다


public class 사전201907_여름신상품 {

	static int TC, N, Q; // 1<=N,Q<=100,000
	static long[] hat, top, bottom, salesQty, sumSaleQtyTree;
	static long [] hatLazy, topLazy, bottomLazy;
	static long [] hatValue, topValue, bottomValue;	
	static long [] hatFenWick, topFenWick, bottomFenWick;
	static long totSalQty;
	static int tn, seg;;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201907.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		long startT = System.currentTimeMillis();
		TC = Integer.parseInt(br.readLine());
//		System.out.println(Long.MAX_VALUE);
		for(int tc=1;tc<=TC;tc++) {
			

//			System.out.println(startT);
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
						
			hat = new long[N+1];
			top = new long[N+1];
			bottom = new long[N+1];
			salesQty = new long[N+1];

			//segmentTree			
			for(seg = 1;seg<N;seg*=2);			
			hatLazy = new long[seg*2];
			hatValue = new long[seg*2];
			topLazy = new long[seg*2];
			topValue = new long[seg*2];
			bottomLazy = new long[seg*2];
			bottomValue = new long[seg*2];
			
			
			//FenWick
			hatFenWick = new long[N+1];
			topFenWick = new long[N+1];
			bottomFenWick = new long[N+1];;
			
			
			/*IndexedTree 선언*/
			sumSaleQtyTree = new long[212121];			
			totSalQty = 0;
			
			
			//IndexedTree
			for(tn=1;tn<N;tn+=2);

			
//			System.out.println(N+" "+Q);
			
			for(int i=1;i<=Q;i++) {
				st = new StringTokenizer(br.readLine());
				int gb = Integer.parseInt(st.nextToken());
				
				if(gb == 1) {
					int L = Integer.parseInt(st.nextToken());
					int R = Integer.parseInt(st.nextToken());
					int item =  Integer.parseInt(st.nextToken());
					int qty =  Integer.parseInt(st.nextToken());
					//일반풀이
//					for(int j=L;j<=R;j++) {
//						if(item==1) {
//							hat[j] +=qty;
//						}else if(item==2) {
//							top[j] +=qty;
//						}else {
//							bottom[j] +=qty;
//						}
//					}
					
					//FenWick
//					if(item==1) {
//						update_range_Fenwick(hatFenWick,L,R,qty);
//					}else if(item==2) {
//						update_range_Fenwick(topFenWick,L,R,qty);
//					}else {
//						update_range_Fenwick(bottomFenWick,L,R,qty);
//					}
					
					//segment Tree
					L--;R--;
					if(item==1) {
						update_range(hatLazy,hatValue,L,R,1,0,seg-1,qty);
					}else if(item==2) {
						update_range(topLazy,topValue,L,R,1,0,seg-1,qty);
					}else {
						update_range(bottomLazy,bottomValue,L,R,1,0,seg-1,qty);
					}
					
					
				}else if(gb==2) {
					int storeNo = Integer.parseInt(st.nextToken());
					int salQty = Integer.parseInt(st.nextToken());
					//일반풀이
//					int minQty = (int)Math.min(hat[storeNo], Math.min(top[storeNo], bottom[storeNo]));
					
					//segmentTree
//					long hatCnt = getSum(hatLazy,hatValue,0,storeNo-1, 1, 0, seg-1 )-getSum(hatLazy,hatValue,0,storeNo-2, 1, 0, seg-1 );
//					long topCnt = getSum(topLazy,topValue,0,storeNo-1, 1, 0, seg-1 )-getSum(topLazy,topValue,0,storeNo-2, 1, 0, seg-1 );
//					long bottomCnt = getSum(bottomLazy,bottomValue,0,storeNo-1, 1, 0, seg-1 )-getSum(bottomLazy,bottomValue,0,storeNo-2, 1, 0, seg-1 );
								
					
					//Fenwick
//					long hatCnt = getSum_Fenwick(hatFenWick,storeNo);
//					long topCnt = getSum_Fenwick(topFenWick,storeNo);
//					long bottomCnt = getSum_Fenwick(bottomFenWick,storeNo);
					
					
//					int minQty = (int)Math.min(hatCnt, Math.min(topCnt, bottomCnt));		
					
					long minQty = getSum2(storeNo-1,storeNo-1, 1, 0, seg-1);
					
					if (minQty<salQty) salQty = (int)minQty;
					
					if(salQty<=0) {
						continue;
					}
					//일반풀이
//					hat[storeNo] -= salQty;
//					top[storeNo] -= salQty;
//					bottom[storeNo] -= salQty;
					salesQty[storeNo] += salQty;
					
					//segment
					updateValue(storeNo-1, -salQty);
					
					
					//Fenwick
//					update_fenwickALL(storeNo, -salQty);
					
					
					/*IndexedTree update 호출*/
					update(storeNo, salQty);
				}else {					
					int start = Integer.parseInt(st.nextToken());
					int end = Integer.parseInt(st.nextToken());
//					System.out.println(start+" , "+end);
					/*IndexedTree search 호출*/
					totSalQty += search(start, end);					
				}
			
			}
			
			System.out.println("#"+tc+" "+totSalQty);

		}
		long endT = System.currentTimeMillis();
		System.out.println(startT+","+endT+","+(endT-startT));
		
	}

	private static void update_fenwickALL(int l, int qty) {
		while(l<=N) {
			hatFenWick[l] +=qty;
			topFenWick[l] +=qty;
			bottomFenWick[l] +=qty;			
			l += (l&-l);
		}
		
	}

	private static long getSum_Fenwick(long[] FenWickTree, int x) {
		long sum = 0;
		while(x>0) {
			sum+= FenWickTree[x];
			x -= (x & -x);
		}
		return sum;
	}

	private static void update_range_Fenwick(long[] fenwickTree,  int l, int r, int qty) {
		update_fenwick(fenwickTree, l, qty);
		update_fenwick(fenwickTree, r+1, -qty);
		
	}

	private static void update_fenwick(long[] fenwickTree, int l, int qty) {
		while(l<=N) {
			fenwickTree[l] +=qty;
			l += (l&-l);
		}
	}
	
	

	private static long getSum(long[] lazy, long[] value, int L, int R, int idx, int temp_L, int temp_R) {
	      if(lazy[idx]!=0){
	    	  value[idx]+=(temp_R-temp_L+1)*lazy[idx];

	            if(temp_R!=temp_L){
	                lazy[idx*2] +=lazy[idx];
	                lazy[idx*2+1]+=lazy[idx];
	            }

	            lazy[idx]=0;
	        }
	        if (L > temp_R || R < temp_L) return 0;


	        if (L <= temp_L && temp_R <= R) return value[idx];
	        int mid = (temp_L + temp_R) / 2;
	        return getSum(lazy,value,L, R, idx * 2, temp_L, mid) + getSum(lazy,value,L, R, idx * 2+1, mid + 1, temp_R);
	}

	private static long getSum2(int L, int R, int idx, int temp_L, int temp_R) {
		      if(hatLazy[idx]!=0){
		    	  hatValue[idx]+=(temp_R-temp_L+1)*hatLazy[idx];
	
		            if(temp_R!=temp_L){
		            	hatLazy[idx*2] +=hatLazy[idx];
		            	hatLazy[idx*2+1]+=hatLazy[idx];
		            }
	
		            hatLazy[idx]=0;
		      }
		      if(topLazy[idx]!=0){
		    	  topValue[idx]+=(temp_R-temp_L+1)*topLazy[idx];
	
		            if(temp_R!=temp_L){
		            	topLazy[idx*2] +=topLazy[idx];
		            	topLazy[idx*2+1]+=topLazy[idx];
		            }
	
		            topLazy[idx]=0;
		      }
		      if(bottomLazy[idx]!=0){
		    	  bottomValue[idx]+=(temp_R-temp_L+1)*bottomLazy[idx];
	
		            if(temp_R!=temp_L){
		            	bottomLazy[idx*2] +=bottomLazy[idx];
		            	bottomLazy[idx*2+1]+=bottomLazy[idx];
		            }
	
		            bottomLazy[idx]=0;
		      }
	        if (L > temp_R || R < temp_L) return 0;


	        if (L <= temp_L && temp_R <= R) return Math.min(hatValue[idx], Math.min(topValue[idx], bottomValue[idx]));
	        int mid = (temp_L + temp_R) / 2;
	        return getSum2(L, R, idx * 2, temp_L, mid) + getSum2(L, R, idx * 2+1, mid + 1, temp_R);
	}
	
	private static void update_range(long[] lazy, long []value, int L, int R,int idx, 
			                         int start, int end, int qty) {
		if(lazy[idx]!=0){
			value[idx] += (end-start+1)*lazy[idx];
			
			if(start != end){
				lazy[idx*2] += lazy[idx];
				lazy[idx*2+1] += lazy[idx];
			}
			
			lazy[idx] = 0;
		}
		
		if(R < start || end < L) return;
		
		if(L<=start && end<=R){
			value[idx] += (end-start+1)*qty;
			if(start != end){
				lazy[idx*2] += qty;
				lazy[idx*2+1] += qty;
			}
			return;
		}
		int mid =  (start+end)/2;
		update_range(lazy, value, L,R,idx*2  , start  ,mid, qty);
		update_range(lazy, value, L,R,idx*2+1, mid+ 1, end, qty);
		
		value[idx] = value[idx*2] + value[idx*2+1];
	}
	
	
    static void update_range2(long[] lazy, long []value, int L,int R,int idx,int temp_L,int temp_R,int num){

        if(lazy[idx]!=0){
        	value[idx]+=(temp_R-temp_L+1)*lazy[idx];

            if(temp_R!=temp_L){
                lazy[idx*2] +=lazy[idx];
                lazy[idx*2+1]+=lazy[idx];
            }

            lazy[idx]=0;
        }
        if (L > temp_R || R < temp_L) return ;

        if(L<=temp_L&&temp_R<=R){
        	value[idx]+= (temp_R-temp_L+1)*num;

            if(temp_L!=temp_R){
                lazy[idx*2]+=num;
                lazy[idx*2+1]+=num;
            }
            return;

        }
        int mid = (temp_L+temp_R)/2;

        update_range(lazy,value,L,R,idx*2,temp_L,mid,num);
        update_range(lazy,value,L,R,idx*2+1,mid+1,temp_R,num);
        value[idx] = value[idx*2]+value[idx*2+1];
    }


	private static long search(int s, int e) {
		long result = 0;
		s = tn+s-1; e= tn+e-1;
		while(s<=e) {
			if(s%2==1) {
				result += sumSaleQtyTree[s];
				s++;
			}
			
			if(e%2==0) {
				result += sumSaleQtyTree[e];
				e--;
			}
			
			s/=2;e/=2;
		}
		return result;
	}
	private static void update(int w, int g) {
		for(int i=tn+w-1;i>0;i/=2 ) {			
			sumSaleQtyTree[i] +=g;
//			System.out.println(i+" "+sumSaleQtyTree[i]);
		}		
	}

	private static void updateValue(int idx, int val) {
        int index = seg + idx;
        hatValue[index] += val;
        topValue[index] += val;
        bottomValue[index] += val;

        while (index > 1) {
            index /= 2;
            hatValue[index] = hatValue[index * 2] + hatValue[index * 2 + 1];
            topValue[index] = topValue[index * 2] + topValue[index * 2 + 1];
            bottomValue[index] = bottomValue[index * 2] + bottomValue[index * 2 + 1];
        }
//
//		for(int i=tn+w-1;i>0;i/=2 ) {
//			hatValue[i] +=g;
//			topValue[i] +=g;
//			bottomValue[i] +=g;
//		}		
	}
}
