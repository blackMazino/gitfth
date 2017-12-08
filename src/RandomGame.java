package com.imk.mall.by.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.util.CollectionUtils;

import com.sds.anyframe.extension.basic.util.StringUtil;

public class RandomGame {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		TestVo vo = new TestVo();
//		List<TestVo> resultReportList = new ArrayList <TestVo>();
		boolean result = false;
		System.setIn(new FileInputStream("C:/workspace/imk_pjt/src/main/java/com/imk/mall/by/test/Input.txt"));
		vo = randomGameFile();
		//vo = randomGame();
		String totResult = vo.getParam4();
		int rsltCnt = 0;
		while("new".equals(StringUtil.nvl(vo.getParam1(), ""))){
			vo = randomGameFile();
			//vo = randomGame();
			totResult = totResult+","+vo.getParam4();
			rsltCnt ++;
		}
		if("retry".equals(vo.getParam1())){
			while("retry".equals(vo.getParam1())){
				vo = retryGame(vo.getParam2(), vo.getParam3());
				totResult = totResult+","+vo.getParam4();
				rsltCnt++;
			}
		}else{
			System.out.println("******************** BYE *******************");
			System.out.println("***********Created By Daniel 2015***********");
			
		}
		
		String [] totResultArray = totResult.split(",");
		if(rsltCnt>0){
			rsltCnt++;			
			System.out.println("******************** BYE *******************");
			System.out.println("***********Created By Daniel 2015***********");//원작자는 지우면 안되욤 에러나요. 네 뻥이에요.
		}
		if(totResultArray.length >0){
			System.out.println("**************          **************");
			System.out.println("************** <report> **************");
			System.out.println("******** Totally "+rsltCnt +" played. ********");
			System.out.println("**************          **************");
			
			for(int z = 0 ; z< totResultArray.length;z++){
				int c = z+1;
				System.out.println("");
				System.out.println("***********"+c+"번째 게임");
				System.out.println(totResultArray[z]);
			}
			
		}
		
	}
	/** 
	 * [메소드 내용 개요]
	 * @param [입력정보, 파라미터 등]
	 * @return [리턴 정보]
	 * @throws Exception 
	 * @throws [예외 정보]
	 */
	
	private static TestVo randomGameFile() throws Exception {
		Scanner sc = new Scanner(System.in);
		int totCnt = sc.nextInt();
		String[] insertPsNmArray = new String[totCnt];
		for(int i=0;i<totCnt;i++){
			insertPsNmArray[i] = sc.next();
		}		
		int caseNo = sc.nextInt();
		//랜덤데이터 생성
		System.out.println("********************랜덤데이터를 생성합니다.");		
		Thread.sleep(1000);
		String[] rData = createRandomData2(totCnt, caseNo,insertPsNmArray);
		//맵핑
		String[][] fnlResult = new String [totCnt][totCnt];
		System.out.println("********************두구두구두구두구");
		Thread.sleep(1000);
		System.out.println("********************결과발표********************");
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		Thread.sleep(1000);
		String result = mkFnlResult(insertPsNmArray, rData);
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★");

		//결과저장
		StringBuffer saveStr = new StringBuffer();
		for(int k = 0 ; k< insertPsNmArray.length;k++){
			if(k == 0){
				saveStr.append(insertPsNmArray[k]);	
			}else{
				saveStr.append(",");
				saveStr.append(insertPsNmArray[k]);	
			}			
		}
		TestVo vo = new TestVo();
		vo.setParam2(saveStr.toString());
		vo.setParam3(String.valueOf(caseNo));
		vo.setParam4(result);
		
		System.out.println("********************다시 하시려면 retry를, 새로운 게임을 하시려면 new를 입력하세요. 끝내시려면 그외의 값을");
		String ulput = bufferIn();
		vo.setParam1(ulput);
		return vo;
		
	}
	private static TestVo retryGame(String str1, String str2) throws Exception {
		TestVo vo = new TestVo();
		String[] insertPsNmArray = str1.split(",");
		//랜덤데이터 생성
		System.out.println("********************랜덤데이터를 생성합니다.");		
		Thread.sleep(1000);
		String[] rData = createRandomData(insertPsNmArray.length, Integer.parseInt(str2));
		
		//맵핑
		System.out.println("********************두구두구두구두구");
		Thread.sleep(1000);
		System.out.println("********************결과발표********************");
		Thread.sleep(1000);
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		String result = mkFnlResult(insertPsNmArray, rData);
		StringBuffer saveStr = new StringBuffer();
		for(int k = 0 ; k< insertPsNmArray.length;k++){
			if(k == 0){
				saveStr.append(insertPsNmArray[k]);	
			}else{
				saveStr.append(",");
				saveStr.append(insertPsNmArray[k]);	
			}			
		}
		vo.setParam2(saveStr.toString());//결과저장
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.println("********************다시 하시려면 retry를, 끝내시려면 그외의 값을 입력하세요");
		String ulput = bufferIn();
		vo.setParam1(ulput);
		vo.setParam2(saveStr.toString());
		vo.setParam3(str2);
		vo.setParam4(result);
		return vo;
	}
	private static TestVo randomGame() throws  Exception {
		// TODO Auto-generated method stub
		boolean rslt = false;
		TestVo vo = new TestVo();
		System.out.println("********************welcome to Random Game********************");
		System.out.println("file encoding : "
				+ System.getProperty("file.encoding"));

		//참가자수 입력
		int totCnt = totCntInsert();
		if(totCnt<0){
			System.out.println("********************계속해서 잘못된 값이 입력되어 프로그램을 강제 종료합니다. 다시 시작하시기 바랍니다.");
			return null;
		}
		System.out.println("********************입력한 참가자 수는 : "+totCnt+"명 입니다.");
		String Yn = yn();
		while("N".equals(Yn)){
			Yn = yn();
		}

		//참가자이름 입력
		String[] insertPsNmArray = insertPsNm(totCnt);
		printPsNm(insertPsNmArray);
		Yn = yn();
		while("N".equals(Yn)){
			insertPsNmArray = insertPsNm(totCnt);
			printPsNm(insertPsNmArray);
			Yn = yn();
		}
		
		//케이스 선택
		int caseNo = choiceCase(totCnt);
		while(caseNo < 0){
			System.out.println("********************유효한 입력값이 아닙니다.");
			caseNo = choiceCase(totCnt);
		}
		if(caseNo == 0){
			System.out.println("********************프로그램을 종료합니다.");
		}
		System.out.println("********************"+caseNo+"번 케이스로 진행합니다.");
		
		//랜덤데이터 생성
		System.out.println("********************랜덤데이터를 생성합니다.");		
		Thread.sleep(1000);
		String[] rData = createRandomData(totCnt, caseNo);
		
		//맵핑
		String[][] fnlResult = new String [totCnt][totCnt];
		System.out.println("********************두구두구두구두구");
		Thread.sleep(1000);
		System.out.println("********************결과발표********************");
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		Thread.sleep(1000);
		String result = mkFnlResult(insertPsNmArray, rData);
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		//결과저장
		StringBuffer saveStr = new StringBuffer();
		for(int k = 0 ; k< insertPsNmArray.length;k++){
			if(k == 0){
				saveStr.append(insertPsNmArray[k]);	
			}else{
				saveStr.append(",");
				saveStr.append(insertPsNmArray[k]);	
			}			
		}
		vo.setParam2(saveStr.toString());
		vo.setParam3(String.valueOf(caseNo));
		vo.setParam4(result);
		
		System.out.println("********************다시 하시려면 retry를, 새로운 게임을 하시려면 new를 입력하세요. 끝내시려면 그외의 값을");
		String ulput = bufferIn();
		vo.setParam1(ulput);
		return vo;
	}
	private static String saveResult(String[] insertPsNmArray) {
		
		
		return null;		
	}
	private static String mkFnlResult(String[] insertPsNmArray,String[] rData) {
		String[][] result = new String[insertPsNmArray.length][rData.length];
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i<result.length; i++){			
			int j = i+1;
			System.out.println("********************"+j+"번째 참가자 "+insertPsNmArray[i]+" : "+rData[i]);	
			sb.append(j+"번째 참가자 "+insertPsNmArray[i]+" : "+rData[i]);
			sb.append("\n");
		}	
		return sb.toString();
	}
	private static int choiceCase(int totCnt) throws IOException {
		System.out.println("********************다음중 하나의 케이스를 선택하여 숫자를 입력하세요");
		System.out.println("********************1. 당첨자 1명 : 참가자 2명이상");
		System.out.println("********************2. 당첨자 1명, 심부름꾼 1명 : 참가자  2명이상");
		System.out.println("********************3. 당첨자 2명, 심부름꾼 1명 : 참가자  3명이상");
		String in = bufferIn();
		int result = -1;
		if(in.equals("1") ||in.equals("2")||in.equals("3")){
			result = Integer.parseInt(in);
			if(result == 1 && totCnt <2){
				result = -2;
			}else if(result == 2 && totCnt <2){
				result = -2;
			}else if(result == 3 && totCnt <3){
				result = -2;
			}
		}else if(in.equals("100") ){
			result = 0;
		}
		return result;
	}
	private static String bufferIn() throws IOException {
		//nputStreamReader in = new InputStreamReader(System.in, "ISO-8859-1");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader bu = new BufferedReader (in);
		String ulput = bu.readLine();
		return URLEncoder.encode(ulput, "UTF-8");
//		return ulput;
	}
//	
//	static String makeKOR(String str)throws java.io.UnsupportedEncodingException
//	{
//	 String kor="";
//	 String temp="";
//	 if(str==null)
//	  kor=null;
//	 else
//	 {
//	  kor=new String(str.getBytes("ISO-8859-1"),"EUC-KR");
//	 }
//	 return kor;
//	}


	private static String[] createRandomData(int totCnt, int caseNo) {
		String [] result = new String [totCnt];
		int [] iResult = new int [totCnt];
		int [] jResult = new int [totCnt];
		System.out.println("********************case"+caseNo+" 당첨자 정보는 "+ caseNo+"개 만들어집니다.");
		Random rand = new Random(totCnt); 
		rand.setSeed(System.currentTimeMillis());//시드값설정
		for(int i = 0 ; i<totCnt ; i++){
			int iRandNo = rand.nextInt(100);
			result[i] = String.valueOf(iRandNo);
			iResult[i] = iRandNo;
			jResult[i] = iRandNo;
		}
		boolean dupYn = chkDupYn(result);
		while(dupYn){
			System.out.println("********************랜덤으로 추출한 데이터의 중복이 있습니다. 다시 추출합니다.");
			Random rand2 = new Random(totCnt); 
			rand2.setSeed(System.currentTimeMillis());//시드값설정
			for(int i = 0 ; i<totCnt ; i++){
				int iRandNo = rand2.nextInt(100);
				result[i] = String.valueOf(iRandNo);
				iResult[i] = iRandNo;
				jResult[i] = iRandNo;
			}
			dupYn = chkDupYn(result);
		}

		Arrays.sort(iResult);
		
		System.out.println("********************내부데이터 공개");
		System.out.println("********************최대값인 분이 당첨입니다");
		for(int y = 0; y<result.length;y++){
			System.out.println("jResult["+y+"] : "+jResult[y]+" || iResult["+y+"] : "+iResult[y]);
		}
		
		result = arrangeRslt(iResult,jResult, caseNo);
		System.out.println("********************랜덤데이터 생성완료");
		System.out.println("********************************************************************************");
		return result;
	}
	
	private static String[] createRandomData2(int totCnt, int caseNo,String[] insertPsNmArray ) {
		String [] result = new String [totCnt];
		int [] iResult = new int [totCnt];
		int [] jResult = new int [totCnt];
		System.out.println("********************case"+caseNo+" 당첨자 정보는 "+ caseNo+"개 만들어집니다.");
		Random rand = new Random(totCnt); 
		rand.setSeed(System.currentTimeMillis());//시드값설정
		for(int i = 0 ; i<totCnt ; i++){
			int iRandNo = rand.nextInt(100);
			result[i] = String.valueOf(iRandNo);
			iResult[i] = iRandNo;
			jResult[i] = iRandNo;
		}
		boolean dupYn = chkDupYn(result);
		while(dupYn){
			System.out.println("********************랜덤으로 추출한 데이터의 중복이 있습니다. 다시 추출합니다.");
			Random rand2 = new Random(totCnt); 
			rand2.setSeed(System.currentTimeMillis());//시드값설정
			for(int i = 0 ; i<totCnt ; i++){
				int iRandNo = rand2.nextInt(100);
				result[i] = String.valueOf(iRandNo);
				iResult[i] = iRandNo;
				jResult[i] = iRandNo;
			}
			dupYn = chkDupYn(result);
		}

		Arrays.sort(iResult);
		System.out.println("********************랜덤데이터 생성완료");
		
		//내부데이터공개
		System.out.println("********************내부데이터 공개");
		System.out.println("********************최대값인 분이 당첨입니다");
		String [] printArr = new String[totCnt];
		for(int y = 0; y<result.length;y++){
			System.out.println("jResult["+y+"] : ("+insertPsNmArray[y]+") "+jResult[y]);
			String num = "";
			if(jResult[y]<10) num="0"+jResult[y];
			else num = String.valueOf(jResult[y]);
				
			
			printArr[y] = num+","+insertPsNmArray[y];
		}
		Arrays.sort(printArr);
		System.out.println("********************정렬");
		for(int y = 0; y<result.length;y++){
			System.out.println(printArr[y]);
		}
		
		
		
		result = arrangeRslt(iResult,jResult, caseNo);

		System.out.println("********************************************************************************");
		return result;
	}
	private static String[] arrangeRslt(int[] iResult, int[] jResult,int caseNo) {
		String [] result = new String [iResult.length];
		if(caseNo ==1){
			for(int j = 0; j< jResult.length ; j++){
				for(int i = 0 ; i<iResult.length ; i++){
					if (jResult[j]==iResult[i]){
						if(i == iResult.length -1){//최대값
							result[j] = "축! 당첨";	
						}else{
							result[j] = " - ";
						}
					}
				}	
			}			
		}else if(caseNo ==2){
			for(int j = 0; j< jResult.length ; j++){
				for(int i = 0 ; i<iResult.length ; i++){
					if (jResult[j]==iResult[i]){
						if(i == iResult.length -1){
							result[j] = "축! 당첨";	
						}else if (i == iResult.length -2){
							result[j] = "축 ! 심부름꾼";
						}else{
							result[j] = " - ";
						}
					}
				}	
			}		
		}else {
			for(int j = 0; j< jResult.length ; j++){
				for(int i = 0 ; i<iResult.length ; i++){
					if (jResult[j]==iResult[i]){
						if(i == iResult.length -1 ||i == iResult.length -2){
							result[j] = "축! 당첨";	
						}else if (i == iResult.length -3){
							result[j] = "축 ! 심부름꾼";
						}else {
							result[j] = " - ";
						}
					}
				}	
			}		
		}

		return result;
	}
	private static boolean chkDupYn(String[] input) {
		boolean result = false;
		ArrayList<String> List = new ArrayList<String>();
		for(int i = 0; i<input.length;i++){
			List.add(input[i]);
		}
		HashSet hs = new HashSet (List);//HaseSet은 중복을 없애준다.
		StringBuffer rsltStrs = new StringBuffer();			
		for(int i = 0 ; i<hs.toArray().length; i++){
			if(!"".equals(StringUtil.nvl(rsltStrs.toString(), ""))){
				rsltStrs.append(",");
				rsltStrs.append(hs.toArray()[i].toString());
			}else{
				rsltStrs.append(hs.toArray()[i].toString());
			}			
		}			
		String[] rsltStrArray = rsltStrs.toString().split(",");
		if(input.length != rsltStrArray.length){
			result = true;
		}
		return result;
	}
	private static boolean chkCaseNo(int caseNo) {
		boolean result = true ; 
		if(caseNo!=1 && caseNo!=2 && caseNo!=3){
			result = false;
		}
		return result;
	}

	private static void printPsNm(String[] insertPsNmArray) {
		System.out.println("********************참가자 정보");
		for(int i = 0 ; i<insertPsNmArray.length;i++){
			int cnt = i+1;
			String nm = insertPsNmArray[i];
			System.out.println("********************"+ cnt +"번 참가자 : "+nm);
		}
	}
	private static String[] insertPsNm(int totCnt) throws IOException {
		// TODO Auto-generated method stub
		String [] result = new String[totCnt];
		System.out.println("********************참가자 순서대로 이름을 입력하세요");
		for(int i = 0; i<totCnt; i++){
			int cnt = i+1;
			System.out.println("********************"+ cnt +"번 참가자.");	
			String ulput = bufferIn();
			if(!"".equals(StringUtil.nvl(ulput, ""))){
				result[i] = ulput;	
			}else{
				result[i] = "개똥이"+cnt;
			}
			
		}
		System.out.println("********************참가자 이름 입력이 완료 되었습니다.");
		return result;
	}

	private static String yn() throws IOException {
		System.out.println("********************맞으면 Y, 틀리면 N을 입력하세요");
		String result = null;
		String ulput = bufferIn();
		if(!"".equals(StringUtil.nvl(ulput, ""))){
			if("Y".equals(ulput) || "y".equals(ulput) || "N".equals(ulput) || "n".equals(ulput)){
				if("y".equals(ulput)){
					result = "Y";
				}else if("n".equals(ulput)){
					result = "N";
				}else{
					result = ulput;
				}
			}else{
				System.out.println("! warning 유효한 값이 아닙니다. 입력한 값 : "+ulput);
				String yn = yn();
				result = yn;
			}
		}else{
			System.out.println("! warning 유효한 값이 아닙니다. 입력한 값  : "+ulput);
			String yn = yn();
			result = yn;
		}
		return result;
	}

	private static int totCntInsert() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("********************참가자 수를 입력하세요. 2명이상********************");
		int result = 0;
		String ulput = bufferIn();
		try{
			result = Integer.parseInt(ulput);
			int count = 0;
			while(result < 2 
					//&& count <10
					){
				System.out.println("********************참가자 수 2명이상 이어야 합니다.********************");
				String ulput2 = bufferIn();
				result = Integer.parseInt(ulput2);
				count ++;
			}
//			while(result > 10 && count <10){
//				System.out.println("********************참가자 수는 최대 10명까지만 가능합니다.********************");
//				String ulput2 = bufferIn();
//				result = Integer.parseInt(ulput2);
//				count ++;
//			}
//			if(count == 10){
//				return -1;
//			}
			
		}catch(Exception e){
			System.out.println("! warning 유효한 타입이 아닙니다.");
			int totCnt = totCntInsert();
			result = totCnt;
		}
		return result;
	}

}
