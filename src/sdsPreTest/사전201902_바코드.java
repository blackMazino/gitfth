package sdsPreTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class 사전201902_바코드 {

/*
3
101011011101001110011001001101101000010100011010101001110101000010001001001000111010010000101010
010101101110100111001100100110110100001010001101010100111010100001000100100100011101001000010101
0101011011101001110011001001101101000010100011010101001110101000010001001001000111010010000101010


3
101010000100101110001001001000100001010111001010101100010100001011011001001100111001011101101010
010101000010010111000100100100010000101011100101010110001010000101101100100110011100101110110101
0101010000100101110001001001000100001010111001010101100010100001011011001001100111001011101101010


(sample_input.txt 의 출력)
#1 2 8801234567893 8801454502919
#2 2 6937526503743 9501101530003
#3 2 0123456789012 8801234543125
#4 6 0549608274074 1328164677968 3188801750595 4192631597021 6012938515666 8898707915862
#5 9 0286814764981 0477411981035 1822146693811 2949835043524 3314561219182 3645220103871 5777440411547 6828479407489 7386280980496
#6 0
#7 0

 */
	static int TC;
	static ArrayList<String> barList;
	static StringBuffer barVal;
	static int[] c, l,g,a;
	static String str;
	public static void main(String[] args) throws Exception {
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/sdsPreTest/사전201902.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        makeGroup();
        
        TC = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=TC;tc++){
        	barList = new ArrayList<String>();
        	str = br.readLine();        	
//        	System.out.println(str);
        	int idx = 0;
        	int fIdx = str.length()-95;
//        	System.out.println(str.length());
//        	System.out.println(str.substring(0,3));
//        	System.out.println(str.substring(45,50));
//        	System.out.println(str.substring(92,95));
        	while(true){
        		if(idx>fIdx) break;    
//        		System.out.println(isValiStartIdx(idx));
        		if(isValiStartIdx(idx)){        			
        			barVal = new StringBuffer();
        			int result = exeExtractNVali(idx);
        			if(result == idx){
        				//Validation Checked! during each value check
        			}else{
        				idx = result;
        				barList.add(barVal.toString());
        			}        		
        		}
        		idx++;        		
        	}    
        	
        	//뒤에서 읽어들이기
        	StringBuffer reStr = new StringBuffer();
        	reStr.append(str);
        	str = reStr.reverse().toString();
//        	System.out.println("reverse");
//        	System.out.println(str);
        	idx = 0;
        	fIdx = str.length()-95;
        	while(true){
        		if(idx>fIdx) break;    
//        		System.out.println(isValiStartIdx(idx));
        		if(isValiStartIdx(idx)){        			
        			barVal = new StringBuffer();
        			int result = exeExtractNVali(idx);
        			if(result == idx){
        				//Validation Checked! during each value check
        			}else{
        				idx = result;
        				barList.add(barVal.toString());
        			}        		
        		}
        		idx++;        		
        	}       
        	
        	Collections.sort(barList);
        	bw.append("#"+tc+" "+barList.size());
        	for(String bar : barList){
        		bw.append(" "+bar);
        	}
        	bw.append("\n");
        }
        bw.flush();
        bw.close();
	}


	private static int exeExtractNVali(int idx) {
		int initVal = idx;
    	idx += 3;
    	int [] b = new int [6];
    	StringBuffer sb = new StringBuffer();
    	StringBuffer bGroup = new StringBuffer();
    	StringBuffer cGroup = new StringBuffer();        
    	boolean valChk = true;
    	for(int i=0;i<6;i++){
    		String val = str.substring(idx, idx+7);
    		b[i] = Integer.parseInt(val, 2);
//    		System.out.println(val+" "+b[i]+" "+(l[b[i]])+ " "+ (g[b[i]]));
    		idx+=7;
    		if(l[b[i]]>=0){
    			sb.append("0");//L
    			bGroup.append(l[b[i]]);
    		}else if(g[b[i]]>=0){
    			sb.append("1");
    			bGroup.append(g[b[i]]);
    		}else{
//    			System.out.println("L,G Value select fail : "+str.substring(initVal, initVal+95)+", "+ val);
    			valChk = false;
    			break;
    		}
    	}
//    	System.out.println(sb.toString());
    	if(!valChk) return initVal;
    	
//    	System.out.println(sb.toString());
//    	System.out.println(a[Integer.parseInt(sb.toString(),2)]);
    	int aVal = a[Integer.parseInt(sb.toString(),2)];
    	if(aVal<0){
//    		System.out.println("a select fail : "+ str.substring(initVal, initVal+95)+", "+sb.toString());
    		return initVal;
    	}
    	
    	idx+=5;//구분자
    	
    	//c
    	for(int i=0;i<6;i++){
    		String val = str.substring(idx, idx+7);
    		int j = Integer.parseInt(val, 2);
    		if(c[j]>=0){
        		cGroup.append(c[j]);
        		idx+=7;    			
    		}else{
    			valChk = false;
//    			System.out.println("c select fail : "+str.substring(initVal, initVal+95)+", "+val);
    			break;
    		}
    	}
    	if(!valChk) return initVal;    	
    	barVal.append(aVal);
    	barVal.append(bGroup.toString());   
    	barVal.append(cGroup.toString());
//    	System.out.println(barVal.toString());
		
		//마지막검증
//    	8801234567893
    	int oddSum =0;
    	int evenSum = 0;
    	int finalNo = 0;
		char [] cArr = barVal.toString().toCharArray();
		for(int i=0;i<cArr.length;i++){			
			if(i==cArr.length-1){
				finalNo = Integer.parseInt(String.valueOf(cArr[i]));
				break;
			}
			if(i%2==0) oddSum += Integer.parseInt(String.valueOf(cArr[i]));
			if(i%2==1) evenSum += Integer.parseInt(String.valueOf(cArr[i]));
		}
    	int valiSum = oddSum + 3*(evenSum);
    	int checkNo = 10-(valiSum%10==0?10:valiSum%10);
		if(checkNo == finalNo){
//			barList.add(Long.parseLong(barVal.toString()));
//			idx+=95;		
//			System.out.println("idx : "+idx);
//			System.out.println(str.substring(initVal, initVal+95)+", "+barVal.toString());
			return idx;					
		}else{
//			System.out.println("final no check : " + str.substring(initVal, initVal+95)+", "+barVal.toString());
			return initVal;
		}

	}

	private static boolean isValiStartIdx(int idx) {
		boolean result = false;
//		System.out.println(isBarcodeStructure(idx));
		if(idx==0){			
//			System.out.println(str.charAt(idx)+" "+isBarcodeStructure(idx));
			if(str.charAt(idx)=='1' && isBarcodeStructure(idx)){
//				System.out.println("start case");
				result = true;
			}
		}else if(idx==str.length()-95){		
//			System.out.println(idx==str.length()-95);
			if(str.charAt(idx-1)=='0' && str.charAt(idx)=='1' && isBarcodeStructure(idx)){
//				System.out.println("end case");
				result = true;
			}
		}else{
			if(str.charAt(idx-1)=='0' && str.charAt(idx+95)=='0' && isBarcodeStructure(idx) ){
//				System.out.println("ing case");
				result = true;
			}
		}	
//		System.out.println(str.substring(idx, idx+95) +" "+ result);
		return result;
	}

	private static boolean isBarcodeStructure(int idx) {
//		System.out.println(str.substring(idx, idx+3) +" "+str.substring(idx+45, idx+50)+" "+str.substring(idx+92, idx+95));
		if("101".equals(str.substring(idx, idx+3))
		&& "01010".equals(str.substring(idx+45, idx+50))
	    && "101".equals(str.substring(idx+92, idx+95))){
			return true;
		}
		return false;
	}

	private static void makeGroup() {
		
		
/*
        숫자 0 : 1110010 -> 64+32+16+2 = 114 
        숫자 1 : 1100110 -> 64+32+4+2  = 102         
        숫자 2 : 1101100 -> 64+32+8+4  = 108
        숫자 3 : 1000010 -> 64+2       = 66
        숫자 4 : 1011100 -> 64+16+8+4  = 92
        숫자 5 : 1001110 -> 64+8+4+2   = 78
        숫자 6 : 1010000 -> 
        숫자 7 : 1000100
        숫자 8 : 1001000
        숫자 9 : 1110100
 */
        c = new int[128];
        Arrays.fill(c, -1);
        ArrayList<Integer> cList = new ArrayList<Integer>();
        cList.add(Integer.parseInt("1110010", 2));//0
        cList.add(Integer.parseInt("1100110", 2));//1
        cList.add(Integer.parseInt("1101100", 2));//2        
        cList.add(Integer.parseInt("1000010", 2));//3
        cList.add(Integer.parseInt("1011100", 2));//4
        cList.add(Integer.parseInt("1001110", 2));//5
        cList.add(Integer.parseInt("1010000", 2));//6
        cList.add(Integer.parseInt("1000100", 2));//7
        cList.add(Integer.parseInt("1001000", 2));//8
        cList.add(Integer.parseInt("1110100", 2));//9
        for(int i=0;i<cList.size();i++){
//        	System.out.println(i+" "+cList.get(i));
        	c[cList.get(i)] = i;
        }

/*        
숫자 0 : L코드 - 0001101    G코드 - 0100111
숫자 1 : L코드 - 0011001    G코드 - 0110011
숫자 2 : L코드 - 0010011    G코드 - 0011011
숫자 3 : L코드 - 0111101    G코드 - 0100001
숫자 4 : L코드 - 0100011    G코드 - 0011101
숫자 5 : L코드 - 0110001    G코드 - 0111001
숫자 6 : L코드 - 0101111    G코드 - 0000101
숫자 7 : L코드 - 0111011    G코드 - 0010001
숫자 8 : L코드 - 0110111    G코드 - 0001001
숫자 9 : L코드 - 0001011    G코드 - 0010111
 */
        l = new int[128];
        Arrays.fill(l, -1);
        ArrayList<Integer> lList = new ArrayList<Integer>();
        lList.add(Integer.parseInt("0001101", 2));//0
        lList.add(Integer.parseInt("0011001", 2));//0
        lList.add(Integer.parseInt("0010011", 2));//0
        lList.add(Integer.parseInt("0111101", 2));//0
        lList.add(Integer.parseInt("0100011", 2));//0
        lList.add(Integer.parseInt("0110001", 2));//0
        lList.add(Integer.parseInt("0101111", 2));//0
        lList.add(Integer.parseInt("0111011", 2));//0
        lList.add(Integer.parseInt("0110111", 2));//0
        lList.add(Integer.parseInt("0001011", 2));//0
        for(int i=0;i<lList.size();i++){
//        	System.out.println(i+" "+lList.get(i));
        	l[lList.get(i)] = i;
        }
        g = new int[128];
        Arrays.fill(g, -1);
        ArrayList<Integer> gList = new ArrayList<Integer>();
        gList.add(Integer.parseInt("0100111", 2));//0
        gList.add(Integer.parseInt("0110011", 2));//0
        gList.add(Integer.parseInt("0011011", 2));//0
        gList.add(Integer.parseInt("0100001", 2));//0
        gList.add(Integer.parseInt("0011101", 2));//0
        gList.add(Integer.parseInt("0111001", 2));//0
        gList.add(Integer.parseInt("0000101", 2));//0
        gList.add(Integer.parseInt("0010001", 2));//0
        gList.add(Integer.parseInt("0001001", 2));//0
        gList.add(Integer.parseInt("0010111", 2));//0
        for(int i=0;i<gList.size();i++){
//        	System.out.println(i+" "+gList.get(i));
        	g[gList.get(i)] = i;
        }
        a = new int[64];
        Arrays.fill(a, -1);
        ArrayList<Integer> aList = new ArrayList<Integer>();
        //L:0, G:1
        aList.add(Integer.parseInt("000000", 2));//0
        aList.add(Integer.parseInt("001011", 2));//1
        aList.add(Integer.parseInt("001101", 2));//2
        aList.add(Integer.parseInt("001110", 2));//3
        aList.add(Integer.parseInt("010011", 2));//4
        aList.add(Integer.parseInt("011001", 2));//5
        aList.add(Integer.parseInt("011100", 2));//6
        aList.add(Integer.parseInt("010101", 2));//7
        aList.add(Integer.parseInt("010110", 2));//8
        aList.add(Integer.parseInt("011010", 2));//9
        for(int i=0;i<aList.size();i++){
//        	System.out.println(i+" "+aList.get(i));
        	a[aList.get(i)] = i;
        }
	}

}


