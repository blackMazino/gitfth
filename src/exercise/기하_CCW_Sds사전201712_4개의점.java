package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기하_CCW_Sds사전201712_4개의점 {
/*
3차원 공간 직교 좌표계 (x, y, z) 속에 원점을 포함하여 4개의 점이 있다고 하자. 						
이 4개의 점이 동일 지점에 있는지, 혹은 동일 직선상에 있는지, 혹은 동일 평면상에 있는지를 판단하는 프로그램을 작성하라.						
 
[제한조건] 				
1. 4개의 점 중 한 개는 항상 (x, y, z) 좌표로 (0, 0, 0), 즉 원점 이다.						
2. 점들의 x, y, z 좌표값은 모두 -1000 이상 1000 이하의 정수이다.						
3. 서로 다른 두 개 이상의 점이 한 지점에 있을 수도 있음에 유의하라.						
					
[입력]				
맨 처음 테스트 케이스의 개수 T가 주어지며 그 다음 T개의 테스트 케이스가 주어진다. 
각 테스트 케이스는 한 줄로 구성 되어 있으며, 9개의 정수 x1, y1, z1, x2, y2, z2, x3, y3, z3 가 공백으로 구분되어 차례대로 주어진다. 
이 정수들은 원점을 제외한 나머지 세 점의 좌표를 뜻하며 각각의 좌표가 (x1, y1, z1), (x2, y2, z2), (x3, y3, z3) 임을 의미한다.
				
[출력]				
테스트 케이스 하나 당 한 줄씩 출력한다. 맨 처음 #x (x 는 테스트케이스 번호, 1부터 시작)을 출력하고 공백을 하나 둔 후 다음을 출력한다.							
1) 네 개의 점이 모두 한 지점에 있다면(즉 네 점 모두 원점이면) 0 을 출력한다.												
2) 위의 1)의 경우를 제외하고 네 개의 점이 동일 직선상에 위치한다면 1 을 출력한다.													
3) 위의 1). 2)의 경우를 제외하고 네 개의 점이 동일 평면상에 위치한다면 2 를 출력한다.											
4) 위의 1), 2), 3) 의 경우에 모두 해당하지 않으면 3 을 출력한다.												
 													


[입출력 예]													
(입력)													
7				
0 0 0 0 0 0 0 0 0				
3 9 -6 -1 -3 2 8 24 -16				
3 3 3 1 3 5 5 7 9				
1 1 1 -2 -2 -2 1 1 1				
1 2 3 4 5 6 7 8 9				
1 2 3 -4 -5 -6 0 0 0				
1 2 1 1 2 1 2 1 2
				
(출력)				
#1 0				
#2 1				
#3 2				
#4 1				
#5 2				
#6 2				
#7 2					

*/

/*
50
-192 -944 736 -72 -354 276 -24 -118 92
0 0 0 0 0 0 0 0 0
-618 420 -582 -824 560 -776 -721 490 -679
-324 -482 -367 -576 104 -874 584 889 -403
145 395 485 215 128 -119 350 34 512
-853 490 354 770 -660 660 -725 700 -975
723 -758 -291 723 -758 -291 68 -657 -285
602 -588 -89 -374 -246 -249 893 106 573
0 0 0 247 247 247 484 484 484
-100 171 -317 -200 342 -634 -300 513 -951
57 737 -715 374 -6 -818 -189 -174 555
-233 719 -679 -993 504 -137 583 -369 -88
-510 895 -540 -306 537 -324 -102 179 -108
-200 -308 -34 -100 -154 -17 -400 -616 -68
-252 458 -680 -267 -79 387 -661 992 459
987 -871 -714 987 -871 -714 831 -544 -19
634 -110 844 634 -110 844 -66 326 493
-846 -246 -642 -282 -82 -214 -564 -164 -428
-147 -375 -252 -98 -250 -168 -49 -125 -84
-814 -476 475 -429 789 676 -814 -476 475
-509 99 -241 751 -817 837 -843 -684 214
-921 -609 268 -363 -231 88 472 -42 554
200 -749 443 611 -845 -592 767 885 960
647 -923 433 -6 345 -461 -6 345 -461
896 -816 896 112 -102 112 224 -204 224
972 764 -731 972 764 -731 358 133 -744
-723 -841 871 -992 -213 -320 -992 -213 -320
-324 -45 -585 -72 -10 -130 -288 -40 -520
-285 -385 450 -342 -462 540 -456 -616 720
-298 108 -162 227 -109 -505 452 -202 -652
888 -790 254 62 759 -371 888 -790 254
0 0 0 -781 107 -651 0 0 0
185 158 -18 555 474 -54 925 790 -90
760 91 405 0 0 0 0 0 0
-924 63 -182 -349 854 -162 -396 27 -78
-936 312 -588 -468 156 -294 -702 234 -441
-204 -153 336 -340 -255 560 -544 -408 896
565 -736 -786 94 -250 304 985 -683 -660
409 -207 -272 -26 242 818 157 226 524
-177 124 -120 798 124 -120 738 589 -570
674 466 870 131 -117 701 -917 -893 -520
-20 -252 274 -70 -882 959 -30 -378 411
526 -90 -573 566 -92 -625 166 -72 -105
329 172 -429 77 -147 -950 582 -74 766
551 444 547 296 -977 -500 296 -977 -500
-833 -566 -618 -72 -138 -714 -875 -760 695
945 -181 45 195 515 -357 945 -181 45
-58 124 78 -261 558 351 -116 248 156
31 -897 -59 31 -897 -59 31 -897 -59
679 -484 384 -566 -179 -99 -765 980 249


(sample_input.txt 의 출력)
#1 1 
#2 0
#3 1
#4 3
#5 3
#6 2
#7 2
#8 3
#9 1
#10 1
#11 2
#12 3
#13 1
#14 1
#15 3
#16 2
#17 2
#18 1
#19 1
#20 2
#21 3
#22 2
#23 3
#24 2
#25 1
#26 2
#27 2
#28 1
#29 1
#30 2
#31 2
#32 1
#33 1
#34 1
#35 2
#36 1
#37 1
#38 3
#39 3
#40 2
#41 2
#42 1
#43 2
#44 3
#45 2
#46 3
#47 2
#48 1
#49 1
#50 3



 */
		static int[]a,b,c;
		public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			for(int tc=1; tc<=T;tc++){
				a= new int [4];
				b= new int [4];
				c= new int [4];
				st = new StringTokenizer(br.readLine());
				a[1] = Integer.parseInt(st.nextToken());
				a[2] = Integer.parseInt(st.nextToken());
				a[3] = Integer.parseInt(st.nextToken());
				b[1] = Integer.parseInt(st.nextToken());
				b[2] = Integer.parseInt(st.nextToken());
				b[3] = Integer.parseInt(st.nextToken());
				c[1] = Integer.parseInt(st.nextToken());
				c[2] = Integer.parseInt(st.nextToken());
				c[3] = Integer.parseInt(st.nextToken());
				
				System.out.println("#"+tc+" "+getRelationIdxOfPoints());						
			}
			
		}
		
		private static int getRelationIdxOfPoints() {
			int result = 3;
			if(a[1] == 0 && a[2] == 0 && a[3] == 0 && b[1] == 0 && b[2] == 0 && b[3] == 0 && c[1] == 0 && c[2] == 0 && c[3] == 0 ){
				result = 0;			
			}else if(isOneLineInvolve()){//3C2 로 뽑은 두 벡터 x,y의 내적의 절대값이 |x|*|y|과 같은 경우
				result = 1;			
			}else if(isOnePlaneInvolve()){//세벡터의 삼중곱 a⋅(b×c)=b⋅(c×a)=c⋅(a×b) = 0인경우  
				result = 2;
			}else {
				//do nothing
			}	
			return result;
		}
		
		private static boolean isOnePlaneInvolve() {
			boolean result = false;
			if(getDotProduct(a, getCrossProduct(b,c)) == 0 ){
				result = true;
			}
			return result;
		}

		private static boolean isOneLineInvolve() {
			boolean result = false;
			if(Math.pow(getDotProduct(a,b),2) == (getVSize(a) * getVSize(b))
			&& Math.pow(getDotProduct(b,c),2) == (getVSize(b) * getVSize(c))
			&& Math.pow(getDotProduct(c,a),2) == (getVSize(c) * getVSize(a))){
				result = true;
			}	
			return result;
		}

		private static double getVSize(int[] x) {		
			//return Math.sqrt(Math.pow(x[1], 2) + Math.pow(x[2], 2) + Math.pow(x[3], 2));
			return (Math.pow(x[1], 2) + Math.pow(x[2], 2) + Math.pow(x[3], 2));
		}

		/* a = a1,a2,a3
		 * b = b1,b2,b3
		 * a*b = a1*b1 + a2*b2 + a3*b3
		 * */	
		private static int getDotProduct(int[] x, int[] y) {
			return (x[1]*y[1] + x[2]*y[2] + x[3]*y[3]);
		}

		/* a = a1,a2,a3
		 * b = b1,b2,b3
		 * a X b = a2*b3-a3*b2 , a3*b1 - a1*b3, a1*b2 - a2*b1
		 * 
		 * */
		private static int[] getCrossProduct(int[] x, int[] y) {
			int[] result = new int[4];
			result[1] = (x[2]*y[3] - x[3]*y[2]);
			result[2] = (x[3]*y[1] - x[1]*y[3]);
			result[3] = (x[1]*y[2] - x[2]*y[1]);
			return result;
		}

	}